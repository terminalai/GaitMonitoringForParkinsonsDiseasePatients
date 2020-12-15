# RBF

## freezeXY
[[305868   2994]
 [ 27771   5618]]
              precision    recall  f1-score   support

         0.0       0.92      0.99      0.95    308862
         1.0       0.65      0.17      0.27     33389

    accuracy                           0.91    342251
   macro avg       0.78      0.58      0.61    342251
weighted avg       0.89      0.91      0.89    342251

Accuracy: 0.9101098316732457
Balanced Accuracy: 0.5792826789332332
Average Precison: 0.19090521982574316
Neg Brier Score: 0.08989016832675434
F1: 0.26751744006095096
-log loss: nan
Precision: 0.6523455643288435
Recall: 0.1682590074575459
Jaccard: 0.15441277519720747
Roc Auc: 0.5792826789332332

## freezeXZ
[[306295   2567]
 [ 27868   5521]]
              precision    recall  f1-score   support

         0.0       0.92      0.99      0.95    308862
         1.0       0.68      0.17      0.27     33389

    accuracy                           0.91    342251
   macro avg       0.80      0.58      0.61    342251
weighted avg       0.89      0.91      0.89    342251

Accuracy: 0.9110740363066872
Balanced Accuracy: 0.5785213519558282
Average Precison: 0.19429884686723775
Neg Brier Score: 0.0889259636933128
F1: 0.26621983267835186
-log loss: nan
Precision: 0.6826162215628091
Recall: 0.16535385905537753
Jaccard: 0.1535487818444766
Roc Auc: 0.5785213519558282

## freezeYZ
[[306210   2652]
 [ 28176   5213]]
              precision    recall  f1-score   support

         0.0       0.92      0.99      0.95    308862
         1.0       0.66      0.16      0.25     33389

    accuracy                           0.91    342251
   macro avg       0.79      0.57      0.60    342251
weighted avg       0.89      0.91      0.88    342251

Accuracy: 0.909925756243225
Balanced Accuracy: 0.5737714525862389
Average Precison: 0.18580956935660647
Neg Brier Score: 0.090074243756775
F1: 0.25272700829010525
-log loss: nan
Precision: 0.6628099173553719
Recall: 0.15612926412890474
Jaccard: 0.1446408257262562
Roc Auc: 0.5737714525862387

## freeze
[[305622   3240]
 [ 24757   8632]]
              precision    recall  f1-score   support

         0.0       0.93      0.99      0.96    308862
         1.0       0.73      0.26      0.38     33389

    accuracy                           0.92    342251
   macro avg       0.83      0.62      0.67    342251
weighted avg       0.91      0.92      0.90    342251

Accuracy: 0.9181974632652644
Balanced Accuracy: 0.6240190680037442
Average Precison: 0.26030883953610273
Neg Brier Score: 0.08180253673473561
F1: 0.3814321380437905
-log loss: nan
Precision: 0.727088948787062
Recall: 0.2585282578094582
Jaccard: 0.23566026918561794
Roc Auc: 0.6240190680037443



# linear

## freezeXY
[[   367 308495]
 [   620  32769]]
              precision    recall  f1-score   support

         0.0       0.37      0.00      0.00    308862
         1.0       0.10      0.98      0.17     33389

    accuracy                           0.10    342251
   macro avg       0.23      0.49      0.09    342251
weighted avg       0.34      0.10      0.02    342251

Accuracy: 0.09681783252642066
Balanced Accuracy: 0.49130962157272573
Average Precison: 0.09605093062162347
Neg Brier Score: 0.9031821674735794
F1: 0.17492986843826153
-log loss: nan
Precision: 0.0960224342444559
Recall: 0.9814310102129444
Jaccard: 0.09584829942319617
Roc Auc: 0.49130962157272573

## freezeXZ
[[308862      0]
 [ 33389      0]]
              precision    recall  f1-score   support

         0.0       0.90      1.00      0.95    308862
         1.0       0.00      0.00      0.00     33389

    accuracy                           0.90    342251
   macro avg       0.45      0.50      0.47    342251
weighted avg       0.81      0.90      0.86    342251

Accuracy: 0.9024429439212741
Balanced Accuracy: 0.5
Average Precison: 0.09755705607872585
Neg Brier Score: 0.09755705607872585
F1: 0.0
-log loss: 3.369501345921487
Precision: 0.0
Recall: 0.0
Jaccard: 0.0
Roc Auc: 0.5

## freezeYZ
[[   886 307976]
 [   587  32802]]
              precision    recall  f1-score   support

         0.0       0.60      0.00      0.01    308862
         1.0       0.10      0.98      0.18     33389

    accuracy                           0.10    342251
   macro avg       0.35      0.49      0.09    342251
weighted avg       0.55      0.10      0.02    342251

Accuracy: 0.09843068391326833
Balanced Accuracy: 0.4926439773526615
Average Precison: 0.09627908336430471
Neg Brier Score: 0.9015693160867316
F1: 0.17533347409044625
-log loss: nan
Precision: 0.09625621372271684
Recall: 0.9824193596693522
Jaccard: 0.09609069471093991
Roc Auc: 0.4926439773526615

## freeze
[[308862      0]
 [ 33389      0]]
              precision    recall  f1-score   support

         0.0       0.90      1.00      0.95    308862
         1.0       0.00      0.00      0.00     33389

    accuracy                           0.90    342251
   macro avg       0.45      0.50      0.47    342251
weighted avg       0.81      0.90      0.86    342251

Accuracy: 0.9024429439212741
Balanced Accuracy: 0.5
Average Precison: 0.09755705607872585
Neg Brier Score: 0.09755705607872585
F1: 0.0
-log loss: 3.369501345921487
Precision: 0.0
Recall: 0.0
Jaccard: 0.0
Roc Auc: 0.5



# sigmoid

## freezeXY
[[153461 155401]
 [ 24702   8687]]
              precision    recall  f1-score   support

         0.0       0.86      0.50      0.63    308862
         1.0       0.05      0.26      0.09     33389

    accuracy                           0.47    342251
   macro avg       0.46      0.38      0.36    342251
weighted avg       0.78      0.47      0.58    342251

Accuracy: 0.4737692512220563
Balanced Accuracy: 0.3785174728733543
Average Precison: 0.08594907833179068
Neg Brier Score: 0.5262307487779436
F1: 0.08797986600971253
-log loss: nan
Precision: 0.05294110477304861
Recall: 0.2601755069034712
Jaccard: 0.04601408972932888
Roc Auc: 0.37851747287335435

## freezeXZ
[[251006  57856]
 [ 20827  12562]]
              precision    recall  f1-score   support

         0.0       0.92      0.81      0.86    308862
         1.0       0.18      0.38      0.24     33389

    accuracy                           0.77    342251
   macro avg       0.55      0.59      0.55    342251
weighted avg       0.85      0.77      0.80    342251

Accuracy: 0.7701014752330891
Balanced Accuracy: 0.5944558948426477
Average Precison: 0.1279696819163481
Neg Brier Score: 0.22989852476691083
F1: 0.24202606760623077
-log loss: nan
Precision: 0.1783918884376154
Recall: 0.3762316930725688
Jaccard: 0.13767329716696805
Roc Auc: 0.5944558948426478

## freezeYZ
[[     0 308862]
 [     0  33389]]
              precision    recall  f1-score   support

         0.0       0.00      0.00      0.00    308862
         1.0       0.10      1.00      0.18     33389

    accuracy                           0.10    342251
   macro avg       0.05      0.50      0.09    342251
weighted avg       0.01      0.10      0.02    342251

Accuracy: 0.09755705607872585
Balanced Accuracy: 0.5
Average Precison: 0.09755705607872585
Neg Brier Score: 0.9024429439212741
F1: 0.17777127036524334
-log loss: nan
Precision: 0.09755705607872585
Recall: 1.0
Jaccard: 0.09755705607872585
Roc Auc: 0.5

## freeze


