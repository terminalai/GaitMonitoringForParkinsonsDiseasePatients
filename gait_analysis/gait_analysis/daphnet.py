from .subject import Subject
from glob import glob
import pandas as pd

daphnet = []
daphnet_thigh = []
daphnet_shank = []
daphnetNames = []

for i in sorted(glob("daphnet/*.txt")):
    daphnetNames.append(i.split("\\")[-1])
    daphnet.append(pd.read_csv(i, sep=" ", names=["time", "shank_h_fd", "shank_v", "shank_h_l", "thigh_h_fd", "thigh_v", "thigh_h_l", "trunk_h_fd", "trunk_v", "trunk_h_l", "annotations"]))
    daphnet_thigh.append(daphnet[-1][["time", "thigh_h_fd", "thigh_v", "thigh_h_l", "annotations"]])
    daphnet_shank.append(daphnet[-1][["time", "shank_h_fd", "shank_v", "shank_h_l", "annotations"]])

subjects = Subject.parse(daphnet_thigh, daphnetNames)

hdaphnet = [subject.analyze() for subject in subjects]

hyp = []
for i in hdaphnet:
    for j in i[0]:
        hyp.append(j)

print(hyp)
