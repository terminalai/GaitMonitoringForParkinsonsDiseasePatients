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

## Sensitivity and Specificity

![Sensitivity and Specificity](https://upload.wikimedia.org/wikipedia/commons/thumb/e/e7/Sensitivity_and_specificity.svg/525px-Sensitivity_and_specificity.svg.png)

### Sensitivity
- ability to correctly identify freezing
<img src="../../sensitivity.svg" />

### Specificity
- ability to correctly identify not freezing
<img src="../../images/specificity.svg" />

## Precision and Recall

![Precision and Recall](https://upload.wikimedia.org/wikipedia/commons/thumb/2/26/Precisionrecall.svg/525px-Precisionrecall.svg.png)

### Precision
- how many identified freezing data points are correct
<img src="../../images/precision.svg" />

### Recall
- how many correct freezing data points are identified
<img src="../../images/recall.svg" />

## Accuracy Scores

### Accuracy
- how accurate the data results are
<img src="../../images/accuracy.svg" />

### F<sub>n</sub> Score
- ability to correctly identify freezing
<img src="../../images/fn.svg" />

#### F<sub>1</sub> Score
The F<sub>1</sub> score is the harmonic average of the precision and recall, where an F<sub>1</sub> score reaches its best value at _1_ (perfect precision and recall) and worst at _0_.

![f<sub>1</sub>](../../images/f1.svg)
