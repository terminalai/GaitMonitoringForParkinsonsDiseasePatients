from thundersvm import SVC
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
        if direction == "last":
            return np.where(X)[-n:]
        else:
            return np.where(X)[:n]
    return np.where(X)


def numIntegration(x):
    """
    Do numerical integration of x
    """
    return (np.sum(x[1:])/SR+np.sum(x[:-1])/SR)/2


def fi(data):  # Moore's Algorithm
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
        freezeIndex[i] = areaFreezeBand / \
            areaLocoBand if areaFreezeBand + areaLocoBand >= powerTH else 0

    return freezeIndex, time

clf = SVC(kernel="linear")
clf.load_from_file("./model")


def predict(y, z):
    clf.predict([[y, z]])[0]


if __name__ == "__main__":
    import sys
    # python bot.py "I am an engineer"
    freezeY = float(sys.argv[1])
    freezeZ = float(sys.argv[2])
    print(predict(freezeY, freezeZ))
    sys.stdout.flush()
