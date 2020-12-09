import scipy as sp
import np

SR = 64            # Sample rate in hertz
stepSize = 1       # Step size in samples
offDelay = 2       # Evaluation delay in seconds: tolerates delay after detecting
onDelay = 2        # Evaluation delay in seconds: tolerates delay before detecting

NFFT = windowLength = 256
locoBand = np[0.5, 3]
freezeBand = np[3, 8]

f_res = SR / NFFT
f_nr_LBs = int(np.round(locoBand[0] / f_res))
f_nr_LBe = int(np.round(locoBand[1] / f_res))
f_nr_FBs = int(np.round(freezeBand[0] / f_res))
f_nr_FBe = int(np.round(freezeBand[1] / f_res))

d = NFFT/2

freezeTH = 1.5
powerTH = 2 ** 11.5

def find(X, n=None, direction=None):
    if direction != None and n != None:
        if direction == "last": return np.where(X)[-n:]
        else: return np.where(X)[:n]
    return np.where(X)


def numIntegration(x):
    """
    Do numerical integration of x
    """
    return (np.sum(x[1:])/SR+np.sum(x[:-1])/SR)/2


def fi(data): # Moore's Algorithm
    """
    Compute the freezing index
    """

    jPos = windowLength
    i_max = len(data) // stepSize

    time = np.zeros(i_max, dtype=int)
    freezeIndex = np.zeros(i_max)

    for i in range(i_max):
        jStart = i * stepSize
        jPos = jStart + windowLength

        # Time (sample nr) of this window
        time[i] = jStart

        # get the signal in the window
        y = data[jStart:jPos].astype(float, casting="unsafe", copy=True)
        y -= np.mean(y)  # make signal zero-mean (mean normalization)

        # compute FFT (Fast Fourier Transform)
        Y = sp.fft.fft(y, NFFT)
        Pyy = Y * np.conj(Y) / NFFT

        # --- calculate sumLocoFreeze and freezeIndex ---
        areaLocoBand = numIntegration(Pyy[f_nr_LBs:f_nr_LBe])
        areaFreezeBand = numIntegration(Pyy[f_nr_FBs:f_nr_FBe])
        
        # Extension of Baechlin to handle low-energy situations (e.g. standing)
        freezeIndex[i] = areaFreezeBand / areaLocoBand if areaFreezeBand + areaLocoBand >= powerTH else 0

    return freezeIndex, time

def fIndex(data):
    FIs = []

    for iaxis in range(1, 5):
        freeze, time = fi(data[:, iaxis])
        FIs.append(freeze)
    
    return FIs

def moore(data, iaxis):
    # Moore's algorithm
    quot, time = fi(data[:, iaxis])
    
    # Classification
    lframe = (quot > freezeTH).astype(int)
    return lframe, time


def classify(data):
    lframes = []

    for iaxis in range(1, 5):
        lframe, time = moore(data, iaxis)
        lframes.append(lframe)

    return lframes


def inform(data):
    info = []

    for iaxis in range(1, 5):
        lframe, time = moore(data, iaxis)

        #######################################################################
        # We do not want to compute performance on the "non experiment" part,
        # e.g. when the sensors are attached on body or the user is not yet
        # doing the task.
        # Therefore we remove the non-experiment parts, which correspond
        # to label '0'.
        # After transformation, there are only frames corresponding to the
        # experiment with label 0=no freeze, 1=freeze
        #######################################################################

        # Ground truth of the frames
        gtframe = data[time, 4]    # 0=no experiment, 1=no freeze, 2=freeze
        # Identify the part of the data corresponding to the experiment
        xp = np.where(gtframe != 0)

        # Remove the non experiment part from the ground truth and classification
        gtframe2 = gtframe[xp]-1       # subtract 1 to have 0 or 1 as labels
        lframe2 = lframe[xp]           # 0=no freeze, 1=freeze

        tp, tn, fp, fn, totFreeze = countTxFx(
            gtframe2, lframe2, offDelay*SR/stepSize, onDelay*SR/stepSize)
        info.append([tp, tn, fp, fn, totFreeze])

    return info




"""
Count the true pos, false pos, etc in frames

gtframe: column vector of ground truth of frame
lframe: column vector classification result of frame
offDelay/onDelay: tolerance for latency in the algorithm. Specified in frames.

The latency-tolerance can only be used with binary labels : 0=nothing, 1=event

Returns: [TP TN FP FN Nev]
Nev: number of events in the ground truth data
"""

def countTxFx(gtframe, lframe, offDelay, onDelay):
    """
    Count the true pos, false pos, etc in frames

    gtframe: column vector of ground truth of frame
    lframe: column vector classification result of frame
    offDelay/onDelay: tolerance for latency in the algorithm. Specified in frames.

    The latency-tolerance can only be used with binary labels : 0=nothing, 1=event

    Returns: [TP TN FP FN Nev]
    Nev: number of events in the ground truth data
    """

    # Want here to create labels tolerating algorithm latency in the
    # transitions from nothing->event and event->nothing.
    # For this we need gtframedelayoff and grframedelayon that are
    # variants of gtframe with delay.
    # This is built using a help 'labels' array.

    # Convert the frame labels to the format: [fromsample tosample]
    print(np.sum(gtframe == 1))
    f = np.hstack((np.array([0]), np.where(gtframe[1:]-gtframe[:-1])[0], np.array(np.size(gtframe, 0)-1)))[np.newaxis].T # add a discontinuity at the start and end
    # convert
    labels = np.empty((0, 2), int)       # [fromframe toframe] where there is an event
    for li in range(np.size(f, 0)-1):
        if gtframe[f[li]+1] == 1:
            print(np[f[li][0]+1, f[li+1][0]])
            labels = np.append(labels, np[f[li][0]+1, f[li+1][0]], axis=1)

    # Labels for delay
    gtframedelayoff = np.zeros(len(gtframe))
    gtframedelayon = np.zeros(len(gtframe))
    print(labels)
    print(labels.shape)
    s = np.arange(np.size(labels, 0)) + 1           # s: 1, 2, ..., frame number

    for li in range(np.size(labels, 1)):
        s_index = find(s>=labels[li,0], 1, 'first')
        e_index = find(s<=labels[li,1], 1, 'last')
        # reference vectors with Off delay
        e_indexOff = find(s<=labels[li,2]+offDelay, 1, 'last')
        gtframedelayoff[s_index:e_indexOff] = 1

        # reference vectors with On delay
        s_indexOn = find(s>=(labels[li,1]+onDelay), 1, 'first')
        gtframedelayon[s_indexOn:e_index] = 1

    res_vec = np.zeros(np.size(gtframe,1),6); # TP TPd TN TNd FP FN

    # mark all correct detected (TP) and not detected (TN) time-slots
    i_TX = find(gtframe == lframe); # all correct time-slots

    i_TP = find(lframe[i_TX] == 1); # correct detected
    res_vec[i_TX[i_TP], 1] = 1;

    i_TN = find(lframe[i_TX] == 0); # correct not detected
    res_vec[i_TX[i_TN], 3] = 1;

    # mark all false detected (FP) and missed (FN) time-slots
    i_FX = find(gtframe != lframe); # all wrong time-slots

    i_FP = find(lframe[i_FX] == 1); # wrong detected
    res_vec[i_FX[i_FP], 5] = 1;

    i_FN = find(lframe[i_FX] == 0); # missed
    res_vec[i_FX[i_FN], 6] = 1;


    # TPd : time-slots true due to the off delay
    i_X = find(res_vec[:,5] == gtframedelayoff);
    i_TPd = find(res_vec[i_X, 5] == 1);
    res_vec[i_X[i_TPd], 2] = 1;
    res_vec[i_X[i_TPd], 5] = 0;

    # TNd : time-slots true due to the on delay
    i_X = find(res_vec[:,6] != gtframedelayon);
    i_TNd = find(res_vec[i_X,6] == 1);
    res_vec[i_X[i_TNd], 4] = 1;
    res_vec[i_X[i_TNd], 6] = 0;

    # sum up result
    TP = np.sum(res_vec[:,1]) + np.sum(res_vec[:,2]);
    TN = np.sum(res_vec[:,3]) + np.sum(res_vec[:,4]);
    FP = np.sum(res_vec[:,5]);
    FN = np.sum(res_vec[:,6]);

    return TP, TN, FP, FN, np.size(labels, 1)
