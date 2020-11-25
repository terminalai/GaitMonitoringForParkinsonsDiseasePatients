import np

SR = 64            # Sample rate in hertz
stepSize = 32      # Step size in samples
offDelay = 2       # Evaluation delay in seconds: tolerates delay after detecting
onDelay = 2        # Evaluation delay in seconds: tolerates delay before detecting

NFFT = windowLength = 256
locoBand = np[0, 5, 3]
freezeBand = np[3, 8]

f_res = SR / NFFT
f_nr_LBs = int(np.round(locoBand[0] / f_res))
f_nr_LBe = int(np.round(locoBand[1] / f_res))
f_nr_FBs = int(np.round(freezeBand[0] / f_res))
f_nr_FBe = int(np.round(freezeBand[1] / f_res))

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
        y = data[jStart:jPos].astype(float, casting="unsafe", copy=True)
        y -= np.mean(y)  # make signal zero-mean (mean normalization)

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
            sum, quot, time = fi(data[:, 1+isensor*3+iaxis])
            # Extension of Baechlin to handle low-enery situations (e.g. standing)
            quot[sum < powerTH] = 0
            # Classification
            lframe = (quot > freezeTH).astype(int).T
            lframes.append(lframe)
    return lframes


