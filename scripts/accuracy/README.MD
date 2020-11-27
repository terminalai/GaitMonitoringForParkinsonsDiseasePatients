# Algorithm Evaluation

## The base understanding

### _m_
- number of data results


### Condition Positive: _p_ 
- number of positive assessments
- y = h (prediction is the same as the actual)

### Condition Negative: _n_ 
- number of negative assessments
- y ≠ h (prediction is not the same as the actual)


### True Positive: _tp_ 
- number of true positives
- y = 1 (has freezing of gait)
- h = 1 (predicted freezing of gait)

### False Positive: _fp_ 
- number of false positives
- y = 0 (has no freezing of gait)
- h = 1 (predicted freezing of gait)

### True Negative: _tn_ 
- number of true negatives
- y = 0 (has no freezing of gait)
- h = 0 (predicted no freezing of gait)

### False Negative: _fn_ 
- number of false negatives
- y = 1 (has freezing of gait)
- h = 0 (predicted no freezing of gait)

## Scoring System

### Sensitivity
- ability to correctly identify freezing
<img src="https://latex.codecogs.com/svg.latex?\Large&space;sensitivity=\frac{tp}{tp+fn}" />

### Specificity
- ability to correctly identify not freezing
<img src="https://latex.codecogs.com/svg.latex?\Large&space;specificity=\frac{tn}{tn+fp}" />



![F<sub>1</sub> Score](https://upload.wikimedia.org/wikipedia/commons/2/26/Precisionrecall.svg)

### Precision
- how many identified freezing data points are correct
<img src="https://latex.codecogs.com/svg.latex?\Large&space;precision=\frac{tp}{tp+fp}" />
![precision](../../images/precision.svg)

### Recall
- how many correct freezing data points are identified
<img src="https://latex.codecogs.com/svg.latex?\Large&space;recall=\frac{tp}{tp+fn}" />
![recall](../../images/recall.svg)

### Accuracy
- how accurate the data results are
<img src="https://latex.codecogs.com/svg.latex?\Large&space;accuracy=\frac{tp+tn}{m}" />

### F<sub>n</sub> Score
- ability to correctly identify freezing
<img src="https://latex.codecogs.com/svg.latex?\Large&space;sensitivity=\frac{tp}{tp+fn}" />

#### F<sub>1</sub> Score
The F<sub>1</sub> score is the harmonic average of the precision and recall, where an F<sub>1</sub> score reaches its best value at _1_ (perfect precision and recall) and worst at _0_.

![f<sub>1</sub>](../../images/f1.svg)
