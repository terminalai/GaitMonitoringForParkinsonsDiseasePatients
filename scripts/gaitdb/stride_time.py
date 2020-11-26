import np # main library for numeric calculations
import pandas as pd # main library for data analysis
import matplotlib.pyplot as plt # main library for data plotting
from glob import glob # check files
from math import *
from .logReg import *

gaitdb = []
gaitDBnames = []
for i in sorted(glob("gaitdb/*.txt")):
    gaitDBnames.append(i.split("\\")[-1])
    gaitdb.append(pd.read_csv(i, sep="\t", names=["time", "stride_time"]))


variances = []
for i in range(15):
    var = gaitdb[i].var()["stride_time"]
    print(gaitDBnames[i][:-5]+":", var)
    variances.append(var)

X = np.array([[1]*15, variances])
Y = np.array([0]*5 + [1]*5 + [0]*5)[np.newaxis].T
theta = np.array([0, 1])
m = 15

logReg(5000000)  # tried twice
logReg(5000000)

theta0, theta1 = tuple(theta)
for i in range(15):
    print(gaitDBnames[i][:-5]+":", sigmoid(theta0 + theta1*variances[i]))

h = sigmoid(theta @ X)
np.round(h)

fig = plt.figure()
ax = fig.add_axes([0,0,1,1])
ax.set_ylabel("Probability of FoG")
ax.set_title("Bar Graph of FoG per patient")
ax.bar([i[:-4] for i in gaitDBnames], h)
plt.show()

