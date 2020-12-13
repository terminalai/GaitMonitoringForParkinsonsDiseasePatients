# Gait Monitoring for Parkinson's Disease Patients
**Mentor**: Arthur Tay

To monitor gait patterns to detect freezing of gait. Done as part of Singapore Science Mentorship Programme.

## Table of Contents
- [Gait Monitoring for Parkinson's Disease Patients](#gait-monitoring-for-parkinson-s-disease-patients)
  * [Project Description](#project-description)
    + [Final Product](#final-product)
    + [Methodology](#methodology)
  * [Introduction](#introduction)
    + [Parkinson’s Disease and Freezing of Gait](#parkinson-s-disease-and-freezing-of-gait)
    + [Contribution](#contribution)
  * [Related work](#related-work)
    + [Utilisation of IMUs in analysis](#utilisation-of-imus-in-analysis)
    + [Machine learning approaches to detecting PD](#machine-learning-approaches-to-detecting-pd)
    + [Gait parameters to be analysed](#gait-parameters-to-be-analysed)
  * [GaitDB Dataset](https://www.physionet.org/content/gaitdb/1.0.0/)
    + [Data Description](#data-description)
    + [Method Used](#method-used)
    + [Analysis](https://nbviewer.jupyter.org/github/ThePyProgrammer/GaitMonitoringForParkinsonsDiseasePatients/blob/main/GaitDB%20Analysis.ipynb)
  * [Daphnet Dataset](https://archive.ics.uci.edu/ml/datasets/Daphnet+Freezing+of+Gait)
    + [Data Description](#data-description-1)
    + [Data Attributes](#data-attributes)
      - [Annotations](#annotations)
    + [LICENCE](#licence)
    + [Analysis](https://nbviewer.jupyter.org/github/ThePyProgrammer/GaitMonitoringForParkinsonsDiseasePatients/blob/main/Daphnet%20Analysis.ipynb)
    + [Set-Up and Imports](https://nbviewer.jupyter.org/github/ThePyProgrammer/GaitMonitoringForParkinsonsDiseasePatients/blob/main/Daphnet%20Analysis.ipynb#Set-Up-and-Imports)
    + [Get Data](https://nbviewer.jupyter.org/github/ThePyProgrammer/GaitMonitoringForParkinsonsDiseasePatients/blob/main/Daphnet%20Analysis.ipynb#Get-Data)
    + [Plots](https://nbviewer.jupyter.org/github/ThePyProgrammer/GaitMonitoringForParkinsonsDiseasePatients/blob/main/Daphnet%20Analysis.ipynb#Plots)
    + [Signal Processing Algorithm](https://nbviewer.jupyter.org/github/ThePyProgrammer/GaitMonitoringForParkinsonsDiseasePatients/blob/main/Daphnet%20Analysis.ipynb#Signal-Processing-Algorithm)
      - [Subject Identification and Analysis](https://nbviewer.jupyter.org/github/ThePyProgrammer/GaitMonitoringForParkinsonsDiseasePatients/blob/main/Daphnet%20Analysis.ipynb#Subject-Identification-and-Analysis)
      - [Getting Hypothesis](https://nbviewer.jupyter.org/github/ThePyProgrammer/GaitMonitoringForParkinsonsDiseasePatients/blob/main/Daphnet%20Analysis.ipynb#Getting-Hypothesis)
  * [VGRF Dataset](https://physionet.org/content/gaitpdb/1.0.0/)
    + [Data Description](#data-description-2)
    + [Data format](#data-format)
    + [Data file names](#data-file-names)
    + [Analysis](https://nbviewer.jupyter.org/github/ThePyProgrammer/GaitMonitoringForParkinsonsDiseasePatients/blob/main/VGRF%20Analysis.ipynb)
  * [References](#references)



## Project Description
The objective of this project is to monitor the gait patterns for people with Parkinson Disease. We will analyse the gait for PD patients during freezing and non-freezing (normal) events. Various parameters (e.g. cadence, step lengths, stride lengths, etc) related to gait will be derived from motion sensors (accelerometers, gyroscopes, magnetometers) using public datasets of PD patients. We will then investigate which of these parameters is most suitable for classification for freezing of gait in PD patients.

### Final Product
To make use of signal processing algorithms in extracting gait parameters from motion sensors and identifying most suitable parameters for classification of freezing of gait in PD patients.

### Methodology
Students will first need to review and understands how to extract gait parameters from motion sensors. There are different algorithms in the literature and they will need to investigate which is most suitable. They will then make use of a PD patient public dataset to test out their methods. If time permits, they will learn basic machine learning tools for classification.

## Introduction

### Parkinson’s Disease and Freezing of Gait

Parkinson’s Disease (PD) is a progressive nervous system disorder that affects the basal ganglia, resulting in the progressive loss of dopamine neurotransmitters and poorer connection between the central nervous system and muscles <sup>[1]</sup>. This leads to many gait abnormalities, mainly tremors, bradykinesia, rigidity and a loss of postural reflexes. Secondary motor symptoms that are evoked include stride length reduction, shuffling gait, step festination and freezing <sup>[2,3]</sup>. Freezing of Gait (FOG) is one of the most debilitating effects of PD and is defined as a brief, episodic absence or marked reduction of forward progression of the feet despite the intention to walk <sup>[3]</sup>. It has serious social and clinical consequences for patients as it leads to falls <sup>[4]</sup>, interferes with daily activities and significantly impacts quality of life <sup>[5]</sup>. While FOG can happen anytime, it happens most often during turns, before gait initiation, in tight quarters such as doorways and in stressful situations <sup>[6]</sup>. It is triggered by visual stimulation.

Currently, FOG characterization is done using two main methods. In the first method, FOG is quantified by gait tests such as the timed up and go (TUG) test and the Hoehn and Yahr (H&Y) scale. The TUG test measures in seconds how long a patient takes to stand up from their chair, walk 3m past a line, turn around, walk back and sit down <sup>[7]</sup>. On the other hand, the H&Y scale has 5 stages to indicate levels of disability <sup>[8]</sup>: 

- Stage 1: Symptoms on one side of the body only. 
- Stage 2: Symptoms on both sides of the body; no impairment of balance. 
- Stage 3: Balance impairment; mild to moderate disease; physically independent. 
- Stage 4: Severe disability, but still able to walk or stand unassisted. 
- Stage 5: Wheelchair-bound or bedridden unless assisted.

In the second method, individual questionnaires are used. The Freezing Of Gait Questionnaire (FOG-Q) is a notable one, comprising 6 questions and utilising a 5-point scale to rank symptom severity <sup>[9]</sup>. However, both methods are highly inefficient in measuring FOG as it is highly sensitive to environmental triggers, medication and the patient’s mental state. Therefore, there has been research into using wearable inertial measurement units (IMUs) to display exactly the gait types of PD patients. 

### Contribution

This study aims to compare all the ways of measuring FOG and determine the best parameter to utilise while creating an algorithm for data analysis. The comfort of the patient and the ease and accuracy in which the parameter can predict FOG will be taken into account in this study.

## Related work

### Utilisation of IMUs in analysis
This section reports previous studies which have explored the application of motion sensors on PD patients to accurately predict FOG. Ferster et al. <sup>[3]</sup> placed 9-axis IMUs (comprising 3D accelerometers, 3D gyroscopes and 3D magnetometers) on both ankles of the subjects to extract gait features such as stride length and stride duration. Moreover, as FOG exhibits unique frequency ranges, they introduce and discuss frequency features such as dominant frequency, dominant frequency amplitude and the inverse of the dominant frequency slope of the acceleration data to quantify changes in gait quality. Ferster was able to show specific changes in the stride duration, stride length, dominant frequency and the inverse of the dominant frequency slope with up to four seconds prior to FoG on all subjects. Baechlin et al. <sup>[10]</sup> proposed placing accelerometers at three different parts of the body: the ankle, the thigh and the lower back to more accurately predict FOG. Alam et al. <sup>[11]</sup> analyzed the vertical ground reaction force using force insoles in patients’ shoes to display gait cycles. Pinto et al. <sup>[12]</sup> again utilised accelerometers and gyroscopes to determine stride time, this time placing the accelerometer at the shank. Many works have also tried utilising motion capture systems to annotate FOG events, synchronising sensor data and computer analysis to make way for machine learning algorithms. Kuhner et al. <sup>[13]</sup> performed this experiment, setting up 12 cameras as well as utilising an inertial measurement suit to create a ‘live’ system that reduces the latency of data processing.

The above literature confirms the success of utilising accelerometers, gyroscopes and force insoles to effectively differentiate FOG from normal gait and has greatly helped in designing the proposed approach for this study.

### Machine learning approaches to detecting PD
Aich et al. <sup>[14]</sup> have done a comprehensive review of the following four different types of machine learning algorithms: 

- support vector machine (SVM)
- k-nearest neighbour (kNN)
- decision tree (DT)
- Naïve Bayes (NB) 

in classifying patients with FOG or no FOG. They found that the SVM classifier with radial basis function provides the highest accuracy of 91.42% as well as the highest sensitivity and specificity of 90.89% and 91.21% respectively. 

Eom et al. [] used thresholds to classify FOG and non-FOG states. The number of thresholds varied, increasing by two for every dimension added. The range between two thresholds was used to determine FOG states for 1D data while six thresholds were required for 3D data. Additionally, three FOG states (FOG: 1, non-FOG: 0) were “AND” operated to determine final FOG.

### Gait parameters to be analysed
The frequency-based features discussed by Baechlin et. al <sup>[10]</sup> are reliable, but they have one major drawback. The large amount of calculations required to perform the Fourier transform needed to analyse frequency-based data means that a digital signal processing chip is required, making it impossible to fit in a small, lightweight unobtrusive system. This would make it impractical for real life detection. For the patients with gait problems, a bulky and obtrusive system may worsen the gait disturbances. Additionally, most experiments utilised a relatively long window length (4 seconds <sup>[10]</sup>, 6 seconds [], and 10 seconds []) for detection, leading to a long delay in FOG detection. Eom et al. [] suggested a simple and fast time-domain method for FOG detection that was comparable to the traditional frequency-domain method with a calculation load of 1,154 times less. This has practical clinical applications.

Previous researchers also have reported the importance of five parameters for the detection of PD. Most notably, Hollman et al. <sup>[15]</sup> have proposed five major domains of gait based on factor analysis:

1. Rhythm - step and stride time
2. Phase - gait cycle
3. Variability - step-to-step variability
4. Pace - gait speed, stride and step length
5. Base of support - step width.

Others have confirmed the importance of determining the spatiotemporal parameters. Alcock et al. <sup>[16]</sup>, Coste et al. <sup>[17]</sup> and Schlachetzki et al. <sup>[18]</sup> have all discussed the importance of stride length, stride time and gait velocity in distinguishing PD patients from healthy older adults. This narrowing down of gait parameters has helped in the focus of this study.


## [GaitDB Dataset](https://www.physionet.org/content/gaitdb/1.0.0/)

### Data Description

Walking stride interval time series included are from 15 subjects: 5 healthy young adults (23 - 29 years old), 5 healthy old adults (71 - 77 years old), and 5 older adults (60 - 77 years old) with Parkinson's disease. The file name indicates old (o), young (y) or Parkinson's disease (pd). For the old and young subjects, the age (in years) is also included in the filename.

Subjects walked continuously on level ground around an obstacle-free path. The stride interval was measured using ultra-thin, force sensitive resistors placed inside the shoe. The analog force signal was sampled at 300 Hz with a 12 bit A/D converter, using an ambulatory, ankle-worn microcomputer that also recorded the data. Subsequently, the time between foot-strikes was automatically computed. The method for determining the stride interval is a modification of a previously validated method that has been shown to agree with force-platform measures, a “gold” standard.

Data were collected from the healthy subjects as they walked in a roughly circular path for 15 minutes, and from the subjects with Parkinson’s disease as they walked for 6 minutes up and down a long hallway.

### Method Used
We calculated the variance of the data and were able to find a correlation between the variance and the diagnosis. 

![variance](images/graphs/varianceOfStrideTime.png)

Based on this result, we employed a rather simplistic classification algorithm using Logistic Regression to find a value that corresponded. 

The result was not as accurate as would be reliable, but that was mainly due to some corner cases. Below are the results.

| Precision | Recall or Sensitivity | Specificity | Accuracy | F1 Score |
| --- | --- | --- | --- | --- |
| 0.80 | 0.80 | 0.90 | 0.867 | 0.80 |

### [Analysis](https://nbviewer.jupyter.org/github/ThePyProgrammer/GaitMonitoringForParkinsonsDiseasePatients/blob/main/GaitDB%20Analysis.ipynb)


## [Daphnet Dataset](https://archive.ics.uci.edu/ml/datasets/Daphnet+Freezing+of+Gait)
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

- **0** - not part of the experiment. For instance the sensors are installed on the user or the user is performing activities unrelated to the experimental protocol, such as debriefing
- **1** - experiment, no freeze (can be any of stand, walk, turn)
- **2** - freeze

### LICENCE

Use of this dataset in publications must be acknowledged by referencing the following publication:

Marc Bächlin, Meir Plotnik, Daniel Roggen, Inbal Maidan, Jeffrey M. Hausdorff, Nir Giladi, and Gerhard Tröster, Wearable Assistant for Parkinson's Disease Patients With the Freezing of Gait Symptom. IEEE Transactions on Information Technology in Biomedicine, 14(2), March 2010, pages 436-446

This paper describes the dataset in details. It explain the data acquisition protocol, the kind of sensor used and their placement, and the nature of the data acquired. It also provides baseline results for the automated detection of freezing of gait, against which newer methods can be benchmarked. In particular it describes detection sensitivity/specificity for 3 sensor placements and 4 kinds of derived sensor signals, it analyzes detection latency, and provides first insight into user specific v.s. user independent performance.

We also appreciate if you inform us (daniel.roggen@ieee.org) of any publication using this dataset for cross-referencing purposes.

### [Analysis](https://nbviewer.jupyter.org/github/ThePyProgrammer/GaitMonitoringForParkinsonsDiseasePatients/blob/main/Daphnet%20Analysis.ipynb)

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

### [Analysis](https://nbviewer.jupyter.org/github/ThePyProgrammer/GaitMonitoringForParkinsonsDiseasePatients/blob/main/VGRF%20Analysis.ipynb)


## References
[1] Braak H, Ghebremedhin E, Rüb U, Bratzke H, Del Tredici K. Stages in the development of Parkinson's disease-related pathology. Cell Tissue Res. 2004 Oct;318(1):121-34. doi: 10.1007/s00441-004-0956-9. Epub 2004 Aug 24. PMID: 15338272.

[2] Jankovic J. Parkinson’s disease: clinical features and diagnosis. Journal of Neurology, Neurosurgery & Psychiatry 2008;79:368-376.

[3] Ferster, M. L., Mazilu, S., & Tröster, G. (2015). Gait Parameters Change Prior to Freezing in Parkinson's Disease: A Data-Driven Study with Wearable Inertial Units. Proceedings of the 10th EAI International Conference on Body Area Networks. doi:10.4108/eai.28-9-2015.2261411

[3] Nutt JG, Bloem BR, Giladi N, et al. Freezing of gait: moving forward on a mysterious clinical phenomenon. Lancet Neurol. 2011;10:734–44.

[4] B. R. Bloem, J. M. Hausdorff, J. E. Visser, N. Giladi. Falls and freezing of gait in parkinson's disease: A review of two interconnected, episodic phenomena. Movement Disorders, vol. 19, no. 8, pp. 871–884, 2008.

[5] Moore O, Peretz C, Giladi N. Freezing of gait affects quality of life of peoples with Parkinson's disease beyond its relationships with mobility and gait. Mov Disord. 2007 Nov 15;22(15):2192-5. doi: 10.1002/mds.21659. PMID: 17712856.

[6] Giladi, N., & Hausdorff, J. M. (2006). The role of mental function in the pathogenesis of freezing of gait in Parkinson's disease. Journal of the Neurological Sciences, 248(1-2), 173-176. doi:10.1016/j.jns.2006.05.015

[7] Podsiadlo D, Richardson S. The timed “Up & Go”: a test of basic functional mobility for frail elderly persons. J Am Geriatr Soc. 1991;39(2):142–148.

[8] M. M. Hoehn and M. D. Yahr. Parkinsonism: onset, progression and mortality. Neurology, vol. 17, no. 5, pp. 427–42, May 1967.

[9] Rozenfeld, S., Miskovic, A., Nitsch, K. P., & Ehrlich-Jones, L. (2017). Measurement Characteristics and Clinical Utility of the Freezing of Gait Questionnaire in Individuals With Parkinson Disease. Archives of Physical Medicine and Rehabilitation, 98(10), 2106-2107. doi:10.1016/j.apmr.2017.04.027

[10] Bachlin, M., Plotnik, M., Roggen, D., Maidan, I., Hausdorff, J., Giladi, N., & Troster, G. (2010). Wearable Assistant for Parkinson’s Disease Patients With the Freezing of Gait Symptom. IEEE Transactions on Information Technology in Biomedicine, 14(2), 436-446. doi:10.1109/titb.2009.2036165

[11] Alam, M. N., Garg, A., Munia, T. T., Fazel-Rezai, R., & Tavakolian, K. (2017). Vertical ground reaction force marker for Parkinson’s disease. Plos One, 12(5). doi:10.1371/journal.pone.0175951

[12] Pinto, C., Schuch, C. P., Balbinot, G., Salazar, A. P., Hennig, E. M., Kleiner, A. F., & Pagnussat, A. S. (2019). Movement smoothness during a functional mobility task in subjects with Parkinson’s disease and freezing of gait – an analysis using inertial measurement units. Journal of NeuroEngineering and Rehabilitation, 16(1). doi:10.1186/s12984-019-0579-8

[13]  Kuhner, A., Schubert, T., Maurer, C., & Burgard, W. (2017). An online system for tracking the performance of Parkinson's patients. 2017 IEEE/RSJ International Conference on Intelligent Robots and Systems (IROS). doi:10.1109/iros.2017.8205977

[14]  Aich, S., Pradhan, P., Park, J., Sethi, N., Vathsa, V., & Kim, H. (2018). A Validation Study of Freezing of Gait (FoG) Detection and Machine-Learning-Based FoG Prediction Using Estimated Gait Characteristics with a Wearable Accelerometer. Sensors, 18(10), 3287. doi:10.3390/s18103287

[15] Eom, G., Kwon, Y., Park, S. H., Kim, J., Ho, Y., Jeon, H., . . . Jeon, H. S. (2014). A practical method for the detection of freezing of gait in patients with Parkinson’s disease. Clinical Interventions in Aging, 1709. doi:10.2147/cia.s69773

[] Hollman, J. H., Mcdade, E. M., & Petersen, R. C. (2011). Normative spatiotemporal gait parameters in older adults. Gait & Posture, 34(1), 111-118. doi:10.1016/j.gaitpost.2011.03.024

[] Coste, C. A., Sijobert, B., Pissard-Gibollet, R., Pasquier, M., Espiau, B., & Geny, C. (2014). Detection of Freezing of Gait in Parkinson Disease: Preliminary Results. Sensors, 14(4), 6819-6827. doi:10.3390/s140406819

[] Alcock, L., Galna, B., Perkins, R., Lord, S., & Rochester, L. (2018). Step length determines minimum toe clearance in older adults and people with Parkinson’s disease. Journal of Biomechanics, 71, 30–36. https://doi.org/10.1016/j.jbiomech.2017.12.002

[] Schlachetzki, J. C., Barth, J., Marxreiter, F., Gossler, J., Kohl, Z., Reinfelder, S., . . . Klucken, J. (2017). Wearable sensors objectively measure gait parameters in Parkinson’s disease. Plos One, 12(10). doi:10.1371/journal.pone.0183989

[] Moore, S. T., Macdougall, H. G., &amp; Ondo, W. G. (2008). Ambulatory monitoring of freezing of gait in Parkinson's disease. Journal of Neuroscience Methods, 167(2), 340-348. doi:10.1016/j.jneumeth.2007.08.023

[] Morris, T. R., Cho, C., Dilda, V., Shine, J. M., Naismith, S. L., Lewis, S. J., & Moore, S. T. (2012). A comparison of clinical and objective measures of freezing of gait in Parkinson's disease. Parkinsonism & Related Disorders, 18(5), 572-577. doi:10.1016/j.parkreldis.2012.03.001

[] Goldberger, A., Amaral, L., Glass, L., Hausdorff, J., Ivanov, P. C., Mark, R., ... & Stanley, H. E. (2000). PhysioBank, PhysioToolkit, and PhysioNet: Components of a new research resource for complex physiologic signals. Circulation [Online]. 101 (23), pp. e215–e220.
