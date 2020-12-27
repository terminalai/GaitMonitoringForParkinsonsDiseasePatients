# From original SVM Analysis

## rbf
### freezeXY
[[306054   3141]
 [ 27351   5705]]
              precision    recall  f1-score   support

         0.0       0.92      0.99      0.95    309195
         1.0       0.64      0.17      0.27     33056

    accuracy                           0.91    342251
   macro avg       0.78      0.58      0.61    342251
weighted avg       0.89      0.91      0.89    342251

Accuracy: 0.9109074918700019
Balanced Accuracy: 0.5812136385291775
Average Precison: 0.19121987643776361
Neg Brier Score: 0.08909250812999817
F1: 0.27230203808887404
-log loss: nan
Precision: 0.64492425955234
Recall: 0.17258591481122942
Jaccard: 0.1576097466640882
Roc Auc: 0.5812136385291775




### freezeXZ
[[306491   2704]
 [ 27510   5546]]
              precision    recall  f1-score   support

         0.0       0.92      0.99      0.95    309195
         1.0       0.67      0.17      0.27     33056

    accuracy                           0.91    342251
   macro avg       0.79      0.58      0.61    342251
weighted avg       0.89      0.91      0.89    342251

Accuracy: 0.9117197612278708
Balanced Accuracy: 0.579515302630553
Average Precison: 0.19316567912920285
Neg Brier Score: 0.08828023877212923
F1: 0.26853241659807287
-log loss: nan
Precision: 0.6722424242424242
Recall: 0.1677758954501452
Jaccard: 0.15508948545861298
Roc Auc: 0.579515302630553




### freezeYZ
[[306471   2724]
 [ 27792   5264]]
              precision    recall  f1-score   support

         0.0       0.92      0.99      0.95    309195
         1.0       0.66      0.16      0.26     33056

    accuracy                           0.91    342251
   macro avg       0.79      0.58      0.60    342251
weighted avg       0.89      0.91      0.89    342251

Accuracy: 0.9108373678966606
Balanced Accuracy: 0.5752174717136607
Average Precison: 0.186144127835901
Neg Brier Score: 0.08916263210333936
F1: 0.2565052139167723
-log loss: nan
Precision: 0.6589884827240862
Recall: 0.15924491771539206
Jaccard: 0.1471212968138625
Roc Auc: 0.5752174717136607




### freeze
[[306009   3186]
 [ 24572   8484]]
              precision    recall  f1-score   support

         0.0       0.93      0.99      0.96    309195
         1.0       0.73      0.26      0.38     33056

    accuracy                           0.92    342251
   macro avg       0.83      0.62      0.67    342251
weighted avg       0.91      0.92      0.90    342251

Accuracy: 0.9188957811664539
Balanced Accuracy: 0.6231755978625881
Average Precison: 0.2583817379786876
Neg Brier Score: 0.08110421883354614
F1: 0.37937664892903455
-log loss: nan
Precision: 0.7269922879177377
Recall: 0.25665537270087124
Jaccard: 0.23409304122289057
Roc Auc: 0.6231755978625881




## linear
### freezeXY
[[309195      0]
 [ 33056      0]]
              precision    recall  f1-score   support

         0.0       0.90      1.00      0.95    309195
         1.0       0.00      0.00      0.00     33056

    accuracy                           0.90    342251
   macro avg       0.45      0.50      0.47    342251
weighted avg       0.82      0.90      0.86    342251

Accuracy: 0.9034159140513833
Balanced Accuracy: 0.5
Average Precison: 0.09658408594861666
Neg Brier Score: 0.09658408594861666
F1: 0.0
-log loss: 3.335896148156
Precision: 0.0
Recall: 0.0
Jaccard: 0.0
Roc Auc: 0.5




### freezeXZ
[[  2258 306937]
 [  3113  29943]]
              precision    recall  f1-score   support

         0.0       0.42      0.01      0.01    309195
         1.0       0.09      0.91      0.16     33056

    accuracy                           0.09    342251
   macro avg       0.25      0.46      0.09    342251
weighted avg       0.39      0.09      0.03    342251

Accuracy: 0.09408591939833631
Balanced Accuracy: 0.456564655531656
Average Precison: 0.08960849373531253
Neg Brier Score: 0.9059140806016637
F1: 0.16188205527442584
-log loss: nan
Precision: 0.08888328188078841
Recall: 0.9058264762826719
Jaccard: 0.08806946025359345
Roc Auc: 0.456564655531656




### freezeYZ
[[308862    333]
 [ 32780    276]]
              precision    recall  f1-score   support

         0.0       0.90      1.00      0.95    309195
         1.0       0.45      0.01      0.02     33056

    accuracy                           0.90    342251
   macro avg       0.68      0.50      0.48    342251
weighted avg       0.86      0.90      0.86    342251

Accuracy: 0.903249369614698
Balanced Accuracy: 0.5036362386606559
Average Precison: 0.09956165541015304
Neg Brier Score: 0.09675063038530202
F1: 0.016396851329273727
-log loss: nan
Precision: 0.45320197044334976
Recall: 0.00834946757018393
Jaccard: 0.0082661954535925
Roc Auc: 0.5036362386606559