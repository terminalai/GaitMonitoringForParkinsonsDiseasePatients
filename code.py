import np

SR = 64            # Sample rate in hetrz
stepSize = 32      # Step size in samples
offDelay = 2       # Evaluation delay in seconds: tolerates delay after detecting
onDelay = 2        # Evaluation delay in seconds: tolerates delay before detecting

NFFT = windowLength = 256
locoBand = np[0, 5, 3]
freezeBand = np[3, 8]

f_res = SR / NFFT
f_nr_LBs = np.round(locoBand[0] / f_res)
f_nr_LBe = np.round(locoBand[1] / f_res)
f_nr_FBs = np.round(freezeBand[0] / f_res)
f_nr_FBe = np.round(freezeBand[1] / f_res)

d = NFFT/2

freezeTH = 1.5
powerTH = 2 ** 11.5


def numIntegration(x):
    """
    Do numerical integration of x
    """
    return (np.sum(x[1:])/SR+np.sum(x[:-1])/SR)/2





def fi(data):
    """
    Compute the freezing index
    """
    
    
    jPos = windowLength
    i_max = len(data) // stepSize + 1
    
    time = np.zeros(i_max)
    sumLocoFreeze = np.zeros(i_max)
    freezeIndex = np.zeros(i_max)
    
    for i in range(i_max):
        jStart = i * stepSize
        jPos = jStart + windowLength
        
        # Time (sample nr) of this window
        time[i] = jPos
        
        # get the signal in the window
        y = data[jStart:jPos]
        y -= np.mean(y) # make signal zero-mean (mean normalization)
        
        # compute FFT (Fast Fourier Transform)
        Y = np.fft.fft(y, NFFT)
        Pyy = Y * np.conj(Y) / NFFT
        
        
        # --- calculate sumLocoFreeze and freezeIndex ---
        areaLocoBand = numIntegration(Pyy[f_nr_LBs:f_nr_LBe])
        areaFreezeBand = numIntegration(Pyy[f_nr_FBs:f_nr_FBe])
        
        sumLocoFreeze[i] = areaFreezeBand + areaLocoBand
        freezeIndex[i] = areaFreezeBand / areaLocoBand
    
    return sumLocoFreeze, freezeIndex, time



def classify(data):
    lframes = []
    for isensor in [1, 2]:
        for iaxis in [1, 2, 3]:
            # Moore's algorithm
            sum, quot, time = fi(data[:,1+isensor*3+iaxis])
            # Extension of Baechlin to handle low-enery situations (e.g. standing)
            quot[sum < powerTH] = 0
            # Classification
            lframe = (quot > freezeTH).T
            lframes.append(lframe)
    return lframes




"""
Count the true pos, false pos, etc in frames

gtframe: column vector of ground truth of frame
lframe: column vector classification result of frame
offDelay/onDelay: tolerance for latency in the algorithm. Specified in frames.

The latency-tolerance can only be used with binary labels : 0=nothing, 1=event

Returns: [TP TN FP FN Nev]
Nev: number of events in the ground truth data


def countTxFx(gtframe, lframe, offDelay, onDelay):
    
    Count the true pos, false pos, etc in frames

    gtframe: column vector of ground truth of frame
    lframe: column vector classification result of frame
    offDelay/onDelay: tolerance for latency in the algorithm. Specified in frames.

    The latency-tolerance can only be used with binary labels : 0=nothing, 1=event

    Returns: [TP TN FP FN Nev]
    Nev: number of events in the ground truth data
    
    
    # Want here to create labels tolerating algorithm latency in the
    # transitions from nothing->event and event->nothing. 
    # For this we need gtframedelayoff and grframedelayon that are 
    # variants of gtframe with delay.
    # This is built using a help 'labels' array.
    
    # Convert the frame labels to the format: [fromsample tosample]
    f = np.vstack((1, np.where(gtframe[1:]-gtframe[:-1]), np.size(gtframe, 1))) # add a discontinuity at the start and end
    # convert
    labels = np.array([])        # [fromframe toframe] where there is an event
    for li in range(np.size(f, 1)-1):
        if gtframe[f[li]+1] == 1: labels = np.vstack((labels, np[f[li]+1, f[li+1]]))
    
    # Labels for delay
    gtframedelayoff = np.zeros(np.size(gtframe, 1), 1)
    gtframedelayon = np.zeros(np.size(gtframe, 1), 1)
    s = np.arange(np.size(labels, 1)) + 1
    #for li in range(np.size(labels, 1)):
        #s_index = 
"""
    
    
    
__all__ = [fi, classify]
