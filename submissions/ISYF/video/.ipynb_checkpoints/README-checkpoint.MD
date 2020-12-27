# ISYFdigital 2021 Elevator Pitch - Gait Monitoring and Analysis for Parkinson's Patients  (NUSH Team)

Parkinson’s disease (PD) is a neurodegenerative disorder that affects the dopamine producing neurons in the substantia nigra, an area of the brain, leading to shaking, stiffness and difficulty walking. Parkinson’s patients frequently exhibit the debilitating condition freezing of gait (FOG), which is when patients cannot move their feet forward despite the intention to walk. While the feet remain in place, the torso still has forward momentum, making falls very common. At the start, FOG can be triggered by stress, tight spaces or a sudden change in direction. As the disease progresses, this happens more frequently, a fact extremely detrimental to the patient’s health and mental well-being. 
 
The aim of this project is to identify the parameter which is most suitable for classification of FOG in PD patients. It also aims to compare multiple machine learning models based on acceleration data from accelerometers placed on the thigh. 

Public datasets of PD patients will be analysed to extract the motion pattern of PD patients. A Freeze Index value is postulated and used to predict FOG based on these parameters. Ultimately, a prototype that fulfils all these requirements will be developed.

This prototype was developed using the Arduino Nano 33 BLE, tested against the best SVM model, which was found using simplistic signal processing algorithms that were then compared using Linear and Gaussian SVM models built using the ThunderSVM tool created by Xtra Computing at NUS. The built-in LED lights up upon the predicted freeze event.

Due to time limitations, only accelerometers were studied in this experiment. Multiple sensors can be worked on to achieve maximum accuracy. Additionally, only postulated freeze index values have been analysed based on the public datasets. Parameters such as Stride Length and Stride Duration have not been worked on. In the future, such parameters can be taken into account when computing the general gait freeze moment. The sensitivity of the algorithm should also be improved. Since the prototype has already been built, the next phase is to test it in a laboratory.

Furthermore, by connecting this system to earbuds and implementing biofeedback via audio, the system will also be able to mediate FOG. Another method involves connecting the system to an App such that notifications can be sent to the caregiver’s phone to alert them to a fall. This can be developed via Android App Development with Java/Kotlin or via frameworks like Kivy or Flutter for Python and Dart Programming respectively.

We would like to thank Professor Arthur Tay, Mr Lim Yeow Heng and Mr Lim Teck Choow for their support and encouragement in this study. The full source code and references can be found here: https://github.com/ThePyProgrammer/GaitMonitoringForParkinsonsDiseasePatients
