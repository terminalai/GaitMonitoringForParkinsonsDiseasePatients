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

def precision(*args):
    if len(args) == 3:
        y, h, m = args
        tp, fp, tn, fn = TxFx(y, h, m)
        
    elif len(args) == 2:
        tp, fp = args
    
    elif len(args) == 4:
        tp, fp, tn, fn = args
    
    else: return
    
    return tp/(tp+fp)


def recall(*args):
    if len(args) == 3:
        y, h, m = args
        tp, fp, tn, fn = TxFx(y, h, m)
        
    elif len(args) == 2:
        tp, fn = args
    
    elif len(args) == 4:
        tp, fp, tn, fn = args
    
    else: return
    
    return tp/(tp+fn)


def specificity(*args):
    if len(args) == 3:
        y, h, m = args
        tp, fp, tn, fn = TxFx(y, h, m)
        
    elif len(args) == 2:
        tn, fp = args
    
    elif len(args) == 4:
        tp, fp, tn, fn = args
    
    else: return
    
    return tn/(tn+fp)

def fn(n, *args):
    r = recall(*args)
    p = precision(*args)
    return (1+n**2)*(r)*(p)/(r+n**2*p)


def f1(*args):
    r = recall(*args)
    p = precision(*args)
    return 2*(r)*(p)/(r+p)
