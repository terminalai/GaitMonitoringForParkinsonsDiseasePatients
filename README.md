# Gait Monitoring for Parkinson's Disease Patients
**Mentor**: Arthur Tay

To monitor gait patterns to detect freezing of gait. Done as part of Singapore Science Mentorship Programme.

## Project Description
The objective of this project is to monitor the gait patterns for people with Parkinson Disease. We will analyse the gait for PD patients during freezing and non-freezing (normal) events. Various parameters (e.g. cadence, step lengths, stride lengths, etc) related to gait will be derived from motion sensors (accelerometers, gyroscopes, magnetometers) using public datasets of PD patients. We will then investigate which of these parameters is most suitable for classification for freezing of gait in PD patients.

## Final Product
To make use of signal processing algorithms in extracting gait parameters from motion sensors and identifying most suitable parameters for classification of freezing of gait in PD patients.

## Methodology
Students will first need to review and understands how to extract gait parameters from motion sensors. There are different algorithms in the literature and they will need to investigate which is most suitable. They will then make use of a PD patient public dataset to test out their methods. If time permits, they will learn basic machine learning tools for classification.

## [GaitDB Dataset](https://www.physionet.org/content/gaitdb/1.0.0/)

### Data Description

Walking stride interval time series included are from 15 subjects: 5 healthy young adults (23 - 29 years old), 5 healthy old adults (71 - 77 years old), and 5 older adults (60 - 77 years old) with Parkinson's disease. The file name indicates old (o), young (y) or Parkinson's disease (pd). For the old and young subjects, the age (in years) is also included in the filename.

Subjects walked continuously on level ground around an obstacle-free path. The stride interval was measured using ultra-thin, force sensitive resistors placed inside the shoe. The analog force signal was sampled at 300 Hz with a 12 bit A/D converter, using an ambulatory, ankle-worn microcomputer that also recorded the data. Subsequently, the time between foot-strikes was automatically computed. The method for determining the stride interval is a modification of a previously validated method that has been shown to agree with force-platform measures, a “gold” standard.

Data were collected from the healthy subjects as they walked in a roughly circular path for 15 minutes, and from the subjects with Parkinson’s disease as they walked for 6 minutes up and down a long hallway.

## [Daphnet Dataset](https://archive.ics.uci.edu/ml/datasets/Daphnet+Freezing+of+Gait#:~:text=Data%20Set%20Information%3A,on%20generating%20many%20freeze%20events.)
(suggested by Prof Tay)

### Data Description

The Daphnet Freezing of Gait Dataset Freezing of Gait in users with Parkinson disease (hereafter Daphnet Freezing of Gait Dataset) is a dataset devised to benchmark automatic methods to recognize gait freeze from wearable acceleration sensors placed on legs and hip.

The dataset was recorded in the lab with emphasis on generating many freeze events. Users performed there kinds of tasks: straight line walking, walking with numerous turns, and finally a more realistic activity of daily living (ADL) task, where users went into different rooms while fetching coffee, opening doors, etc.

This dataset is the result of a collaboration between the Laboratory for Gait and Neurodynamics, Tel Aviv Sourasky Medical Center, Israel and the Wearable Computing Laboratory, ETH Zurich, Switzerland. Recordings were run at the Tel Aviv Sourasky Medical Center in 2008. The study was approved by the local Human Subjects Review Committee, and was performed in accordance with the ethical standards of the Declaration of Helsinki.

This dataset was collected as part of the EU FP6 project Daphnet, grant number 018474-2.
Additional effort to publish this dataset was supported in part by the EU FP7 project CuPiD, grant number 288516.

### Data Attributes
1. Time of sample in millisecond
2. Ankle (shank) acceleration - horizontal forward acceleration (mg)
3. Ankle (shank) acceleration - vertical (mg)
4. Ankle (shank) acceleration - horizontal lateral (mg)
5. Upper leg (thigh) acceleration - horizontal forward acceleration (mg)
6. Upper leg (thigh) acceleration - vertical (mg)
7. Upper leg (thigh) acceleration - horizontal lateral (mg)
8. Trunk acceleration - horizontal forward acceleration (mg)
9. Trunk acceleration - vertical (mg)
10. Trunk acceleration - horizontal lateral (mg)
11. Annotations (see Annotations section)

#### Annotations
The meaning of the annotations are as follows:
0: not part of the experiment. For instance the sensors are installed on the user or the user is performing activities unrelated to the experimental protocol, such as debriefing
1: experiment, no freeze (can be any of stand, walk, turn)
2: freeze

### LICENCE

Use of this dataset in publications must be acknowledged by referencing the following publication:

Marc Bächlin, Meir Plotnik, Daniel Roggen, Inbal Maidan, Jeffrey M. Hausdorff, Nir Giladi, and Gerhard Tröster, Wearable Assistant for Parkinson's Disease Patients With the Freezing of Gait Symptom. IEEE Transactions on Information Technology in Biomedicine, 14(2), March 2010, pages 436-446

This paper describes the dataset in details. It explain the data acquisition protocol, the kind of sensor used and their placement, and the nature of the data acquired. It also provides baseline results for the automated detection of freezing of gait, against which newer methods can be benchmarked. In particular it describes detection sensitivity/specificity for 3 sensor placements and 4 kinds of derived sensor signals, it analyzes detection latency, and provides first insight into user specific v.s. user independent performance.

We also appreciate if you inform us (daniel.roggen@ieee.org) of any publication using this dataset for cross-referencing purposes.
