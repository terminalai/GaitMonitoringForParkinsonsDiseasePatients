def sigmoid(z):
    return 1/(1 + np.exp(-z));

def logReg(iterations=50):
    global theta
    alpha = 10
    J = float("inf")
    for i in range(iterations):
        h = sigmoid(theta @ X)[np.newaxis].T
        J_temp = (-Y.T @ np.log(h)) - ((1 - Y).T @ np.log(1 - h))
        if isnan(J_temp) or isinf(J_temp): break
        elif J_temp > J: print(J_temp)
        J = J_temp
        theta = theta - alpha/m * (X @ (h - Y)).T[0]
    print(theta)
