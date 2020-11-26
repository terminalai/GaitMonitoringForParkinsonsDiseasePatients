def accuracy(y, h, m):
    return sum(y == h)/m

def TxFx(y, h, m):
    tp = fp = tn = fn = 0
    for i in range(m):
        actual = y[i]
        pred = h[i]
        if actual == 1 and pred == 1:
            tp += 1
        elif actual == 0 and pred == 1:
            fp += 1
        elif actual == 0 and pred == 0:
            tn += 1
        else:
            fn += 1

    return tp, fp, tn, fn

def precision(y, h, m):
    tp, fp, tn, fn = TxFx(y, h, m)
    return tp/(tp+fp)

def recall(y, h, m):
    tp, fp, tn, fn = TxFx(y, h, m)
    return tp/(tp+fn)

def specificity(y, h, m):
    tp, fp, tn, fn = TxFx(y, h, m)
    return tn/(tn+fp)

def f1(y, h, m):
    recall = recall(y, h, m)
    precision = precision(y, h, m)
    return 2*(recall)*(precision)/(recall+precision)
