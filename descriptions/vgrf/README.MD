## [VGRF Dataset](https://physionet.org/content/gaitpdb/1.0.0/)
### Data Description

Parkinson's disease (PD) is one of the most common movement disorders, affecting approximately 1 million Americans (estimates range between 4 and 6.5 million people worldwide) and about 1% of older adults. In the US alone, 60,000 new cases are diagnosed each year. PD is a chronic and progressive neurological disorder that results in tremor, rigidity, slowness, and postural instability. A disturbed gait is a common, debilitating symptom; patients with severe gait disturbances are prone to falls and may lose their functional independence.

This database contains measures of gait from 93 patients with idiopathic PD (mean age: 66.3 years; 63% men), and 73 healthy controls (mean age: 66.3 years; 55% men). The database includes the vertical ground reaction force records of subjects as they walked at their usual, self-selected pace for approximately 2 minutes on level ground. Underneath each foot were 8 sensors ([Ultraflex Computer Dyno Graphy, Infotronic Inc.](http://www.infotronic.nl/#CDG)) that measure force (in Newtons) as a function of time. The output of each of these 16 sensors has been digitized and recorded at 100 samples per second, and the records also include two signals that reflect the sum of the 8 sensor outputs for each foot. For details about the format of the data, please see [this note](https://physionet.org/content/gaitpdb/1.0.0/format.txt).

With this information, one can investigate the force record as a function of time and location, derive measures that reflect the center-of-pressure as a function of time, and determine timing measures (e.g., stride time, swing time) for each foot as functions of time. Thus, one can study the stride-to-stride dynamics and the variability of these time series.

This database also includes demographic information, measures of disease severity (i.e., using the Hoehn & Yahr staging and/or the Unified Parkinson's Disease Rating Scale) and other related measures (available in HTML or xls spreadsheet format).

A subset of the database includes measures recorded as subjects performed a second task (serial 7 subtractions) while walking, as in the figure above, which shows excerpts of swing time series from a patient with PD (lower panels) and a control subject (upper panels), under usual walking conditions (at left) and when performing serial 7 subtractions (at right). Under usual walking conditions, variability is larger in the patient with PD (Coefficient of Variation = 2.7%), compared to the control subject (CV = 1.3%). Variability increases during dual tasking in the subject with PD (CV = 6.5%), but not in the control subject (CV = 1.2%). From Yogev et al. (reference [4] below).

### Data format

Each line contains 19 columns:

| Column | Description |
|--------|-------------|
| 1 | Time (in seconds)|
| 2-9 | **Vertical ground reaction force** (VGRF, in Newton) on each of 8 sensors located under the left foot |
| 10-17 |VGRF on each of the 8 sensors located under the right foot |
| 18 | Total force under the left foot |
| 19 | Total force under the right foot |


When a person is comfortably standing with both legs parallel to each
other, sensor locations inside the insole can be described (according
to the [Infotronic website](http://www.infotronic.nl/)) as lying
approximately at the following (X,Y) coordinates, assuming that the
origin (0,0) is just between the legs and the person is facing towards
the positive side of the Y axis:

| Sensor | X    | Y    |
|--------|------|------|
| L1     | -500 | -800 |
| L2     | -700 | -400 |
| L3     | -300 | -400 |
| L4     | -700 | 0    |
| L5     | -300 | 0    |
| L6     | -700 | 400  |
| L7     | -300 | 400  |
| L8     | -500 | 800  |
| R1     | 500  | -800 |
| R2     | 700  | -400 |
| R3     | 300  | -400 |
| R4     | 700  | 0    |
| R5     | 300  | 0    |
| R6     | 700  | 400  |
| R7     | 300  | 400  |
| R8     | 500  | 800  |

The X and Y numbers are in an arbitrary coordinate system reflecting
the relative (arbitrarily scaled) positions of the sensors within each
insole. During walking, the sensors inside each insole remain at the
same relative position, but the two feet are no longer parallel to
each other. Thus, this coordinate system enables a calculation of a
proxy for the location of the center of pressure (COP) under each
foot.


### Data file names
These follow a common convention, e.g., ```GaCo01_02.txt```  or  ```JuPt03_06.txt```, where

- Ga, Ju or Si – indicate the study from which the data originated:
 - Ga - Galit Yogev et al ([dual tasking in PD; Eur J Neuro, 2005](https://onlinelibrary.wiley.com/doi/abs/10.1111/j.1460-9568.2005.04298.x))
 - Ju – Hausdorff et al ([RAS in PD; Eur J Neuro, 2007](https://onlinelibrary.wiley.com/doi/abs/10.1111/j.1460-9568.2007.05810.x))
 - Si - Silvi Frenkel-Toledo et al ([Treadmill walking in PD; Mov Disorders, 2005](https://onlinelibrary.wiley.com/doi/abs/10.1002/mds.20507))

- Co or Pt: Control subject or a PD Patient respectively

- 01: Subject number in the group

- A walk number of 10  (for the "Ga" study) indicates a dual-task walking,
where the subject was engaged in serial-7 subtraction while walking.

- A walk number of 01 refers to a usual, normal walk. 

The sampling rate was 100 Hz.
