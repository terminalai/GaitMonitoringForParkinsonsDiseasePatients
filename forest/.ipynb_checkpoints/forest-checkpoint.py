import scipy.optimize as op
from .lazy import *

def sigmoid(z):
    return 1/(1 + np.exp(-z));


def linReg(X, y, theta, λ=0):
    m = y.T.shape
    h = (X*theta)-y
    regTheta = theta[:,:]
    regTheta[1] = 0
    J = (dot(h, h) + λ*dot(regTheta, regTheta))/(2*m)
    grad = (X.T*h + λ*regTheta)/m
    return J, grad

def logReg(theta, X, Y, Lambda):
    m = len(Y)
    y = Y[:,np.newaxis]
    h = sigmoid(X @ theta)
    cost = sum((-y * np.log(h)) - ((1-y)*np.log(1-h)))/m
    regCost= cost + Lambda/(2*m) * (theta @ theta - theta[0]**2)
    
    # compute gradient
    j_0 = 1/m * (X.transpose() @ (h - y))[0]
    j_1 = ((X.transpose() @ (h - y))[1:] + Lambda* theta[1:])/m
    grad= np.vstack((j_0[:,np.newaxis],j_1))
    return regCost[0], grad

def fminunc(f, X, y, initial_theta=None):
    return op.fmin_tnc(func=lambda theta, X, y: tuple(f(theta)), x0 = np.zeros(X.shape[1]) if initial_theta is None else initial_theta, args = (X, y))[0]

def gradientDescent(func, theta, alpha=0.01, num_iters=10):
    J_history =[]
    for i in range(num_iters):
        cost, grad = func(theta)
        theta = theta - (alpha * grad)
        J_history.append(cost)
    return theta, J_history

import numpy.linalg as la
def div(a, b):
    return la.solve(b.T, a.T).T

class optimset:
    def __init__(self, **kwargs):
        self.diction = kwargs

    def __getitem__(self, key): return self.diction[key]
    def __setitem__(self, key, value): self.diction[key] = value
    
    

def fmincg(f, X, options=optimset(MaxIter=200, GradObj='on')):

    if options['MaxIter']:
        length = options['MaxIter']
    else:
        length = 100    
    
    RHO = 0.01                            # a bunch of constants for line searches
    SIG = 0.5       # RHO and SIG are the constants in the Wolfe-Powell conditions
    INT = 0.1    # don't reevaluate within 0.1 of the limit of the current bracket
    EXT = 3.0                    # extrapolate maximum 3 times the current bracket
    MAX = 20                         # max 20 function evaluations per line search
    RATIO = 100                                      # maximum allowed slope ratio

    # FIXME
    red = 1

    i = 0                                            # zero the run length counter
    ls_failed = 0                             # no previous line search has failed
    fX = np.array([])
    f1, df1 = f(X)                      # get function value and gradient
    i = i + (length<0)                                            # count epochs?!
    s = -df1                                        # search direction is steepest
    d1 = np.dot(-s.T, s)                                                 # this is the slope
    z1 = red/(1-d1)                                  # initial step is red/(|s|+1)
    
    while i < abs(length):                           # while not finished
        i = i + (length>0)                           # count iterations?!
        
        X0 = X; f0 = f1; df0 = df1                   # make a copy of current values                                            # begin line search
        X = X + np.multiply(z1,s)                   # begin line search    
        f2, df2 = f(X)
        i = i + (length<0)                                          # count epochs?!
        d2 = np.dot(df2.T, s)
        f3 = f1
        d3 = d1
        z3 = -z1             # initialize point 3 equal to point 1
        if length>0:
            M = MAX
        else:
            M = min(MAX, -length-i)
        success = 0
        limit = -1                     # initialize quanteties
        while True:
            while ((f2 > f1+np.dot(np.dot(z1,RHO),d1)) or (d2 > np.dot(-SIG, d1))) and (M > 0):
                limit = z1                                         # tighten the bracket
                if f2 > f1:
                    z2 = z3 - (0.5*np.dot(np.dot(d3,z3),z3))/(np.dot(d3, z3)+f2-f3)                 # quadratic fit
                else:
                    A = 6*(f2-f3)/z3+3*(d2+d3)                                 # cubic fit
                    B = 3*(f3-f2)-np.dot(z3,(d3+2*d2))
                    z2 = (np.sqrt(np.dot(B, B)-np.dot(np.dot(np.dot(A,d2),z3),z3))-B)/A       # numerical error possible - ok!
                if isnan(z2) | isinf(z2):
                    z2 = z3/2                  # if we had a numerical problem then bisect
                z2 = max(min(z2, INT*z3),(1-INT)*z3)  # don't accept too close to limits
                z1 = z1 + z2                                           # update the step
                X = X + np.multiply(z2,s)
                f2, df2 = f(X)
                M = M - 1
                i = i + (length<0)                           # count epochs?!
                d2 = np.dot(np.transpose(df2),s)
                z3 = z3-z2                    # z3 is now relative to the location of z2    
            if (f2 > f1+np.dot(z1*RHO,d1)) or (d2 > -SIG*d1):
                break                                                # this is a failure
            elif d2 > SIG*d1:
                success = 1
                break                                             # success
            elif M == 0:
                break                                                          # failure
            A = 6*(f2-f3)/z3+3*(d2+d3)                      # make cubic extrapolation
            B = 3*(f3-f2)-np.dot(z3, (d3+2*d2))
            z2 = -np.dot(np.dot(d2,z3),z3)/(B+np.sqrt(np.dot(B,B)-np.dot(np.dot(np.dot(A,d2),z3),z3)))        # num. error possible - ok!
            if z2 is not float or isnan(z2) or isinf(z2) or z2 < 0:   # num prob or wrong sign?
                if limit < -0.5:                               # if we have no upper limit
                    z2 = z1 * (EXT-1)                 # the extrapolate the maximum amount
                else:
                    z2 = (limit-z1)/2                                   # otherwise bisect
            elif (limit > -0.5) and (z2+z1 > limit):          # extraplation beyond max?
                z2 = (limit-z1)/2                                               # bisect
            elif (limit < -0.5) and (z2+z1 > z1*EXT):       # extrapolation beyond limit
                z2 = z1*(EXT-1.0)                           # set to extrapolation limit
            elif z2 < -z3*INT:
                z2 = -z3*INT
            elif (limit > -0.5) and (z2 < (limit-z1)*(1.0-INT)):   # too close to limit?
                z2 = (limit-z1)*(1.0-INT)
            f3 = f2; d3 = d2; z3 = -z2                  # set point 3 equal to point 2
            z1 = z1 + z2
            X = X + np.multiply(z2,s)                      # update current estimates
            f2, df2 = f(X)
            M = M - 1
            i = i + (length<0)                             # count epochs?!
            d2 = np.dot(df2.T,s)
        if success:                                         # if line search succeeded
            f1 = f2
##            print (fX.T).shape
##            print isinstance(f1, np.generic)
            fX = np.append((fX.T, [float(f1)]) ,1).T
##            fX = np.concatenate(([fX.T], [f1]) ,1).T            
            print("Iteration %i | Cost: %f \r" %(i,f1)),            
            s = np.multiply((np.dot(df2.T,df2)-np.dot(df1.T,df2))/(np.dot(df1.T,df1)), s) - df2      # Polack-Ribiere direction
            tmp = df1
            df1 = df2
            df2 = tmp                         # swap derivatives
            d2 = np.dot(df1.T,s)
            if d2 > 0:                                      # new slope must be negative
                s = -df1                              # otherwise use steepest direction
                d2 = np.dot(-s.T,s)
            z1 = z1 * min(RATIO, d1/(d2-sys.float_info.min))          # slope ratio but max RATIO
            d1 = d2
            ls_failed = 0                              # this line search did not fail
        else:
            X = X0
            f1 = f0
            df1 = df0  # restore point from before failed line search
            if ls_failed or (i > abs(length)): # line search failed twice in a row
                break                             # or we ran out of time, so we give up
            tmp = df1
            df1 = df2
            df2 = tmp                         # swap derivatives
            s = -df1                                                    # try steepest
            d1 = np.dot(-s.T,s)
            z1 = 1/(1-d1)                     
            ls_failed = 1                                    # this line search failed
    print()
    return X, fX, i


class SimpleNetwork:
    
    def __init__(self):
        # seeding for random number generation
        np.random.seed(1)
        
        #converting weights to a 3 by 1 matrix with values from -1 to 1 and mean of 0
        self.synaptic_weights = 2 * np.random.random((3, 1)) - 1

    def sigmoid(self, x):
        #applying the sigmoid function
        return 1 / (1 + np.exp(-x))

    def sigmoid_derivative(self, x):
        #computing derivative to the Sigmoid function
        return self.sigmoid(x) * (1 - self.sigmoid(x))

    def train(self, training_inputs, training_outputs, training_iterations):
        
        #training the model to make accurate predictions while adjusting weights continually
        for iteration in range(training_iterations):
            #siphon the training data via  the neuron
            output = self.think(training_inputs)

            #computing error rate for back-propagation
            error = training_outputs - output
            
            #performing weight adjustments
            adjustments = np.dot(training_inputs.T, error * self.sigmoid_derivative(output))

            self.synaptic_weights += adjustments

    def think(self, inputs):
        #passing the inputs via the neuron to get output   
        #converting values to floats
        
        inputs = inputs.astype(float)
        output = self.sigmoid(np.dot(inputs, self.synaptic_weights))
        return output



class NeuralNetwork:
    def __init__(self, x, y):
        self.input      = x
        self.weights1   = np.random.rand(self.input.shape[1],4) 
        self.weights2   = np.random.rand(4,1)                 
        self.y          = y
        self.output     = np.zeros(self.y.shape)

    def sigmoid(self, x):
        #applying the sigmoid function
        return 1 / (1 + np.exp(-x))

    def sigmoid_derivative(self, x):
        #computing derivative to the Sigmoid function
        return self.sigmoid(x) * (1 - self.sigmoid(x))

    def feedforward(self):
        self.layer1 = self.sigmoid(np.dot(self.input, self.weights1))
        self.output = self.sigmoid(np.dot(self.layer1, self.weights2))

    def backprop(self):
        # application of the chain rule to find derivative of the loss function with respect to weights2 and weights1
        d_weights2 = np.dot(self.layer1.T, (2*(self.y - self.output) * self.sigmoid_derivative(self.output)))
        d_weights1 = np.dot(self.input.T,  (np.dot(2*(self.y - self.output) * self.sigmoid_derivative(self.output), self.weights2.T) * self.sigmoid_derivative(self.layer1)))

        # update the weights with the derivative (slope) of the loss function
        self.weights1 += d_weights1
        self.weights2 += d_weights2



class NeuralNet():
    '''
    A two layer neural network
    from https://heartbeat.fritz.ai/building-a-neural-network-from-scratch-using-python-part-1-6d399df8d432
    '''
        
    def __init__(self, layers=[13,8,1], learning_rate=0.001, iterations=100):
        self.params = {}
        self.layers = layers
        self.learning_rate = learning_rate
        self.iterations = iterations
        self.loss = []
        self.sample_size = None
        self.X = None
        self.y = None
                
    def init_weights(self):
        '''
        Initialize the weights from a random normal distribution
        '''
        np.random.seed(1) # Seed the random number generator
        self.params["W1"] = np.random.randn(self.layers[0], self.layers[1]) 
        self.params['b1']  =np.random.randn(self.layers[1],)
        self.params['W2'] = np.random.randn(self.layers[1],self.layers[2]) 
        self.params['b2'] = np.random.randn(self.layers[2],)
        
    def relu(self,Z):
        '''
        The ReLufunction performs a threshold
        operation to each input element where values less 
        than zero are set to zero.
        '''
        return np.maximum(0, Z)
        
        
    def sigmoid(self,Z):
        '''
        The sigmoid function takes in real numbers in any range and 
        squashes it to a real-valued output between 0 and 1.
        '''
        return 1.0 / (1.0 + np.exp(-Z))
    
    def entropy_loss(self,y, yhat):
        nsample = len(y)
        loss = -1/nsample * (np.sum(np.multiply(np.log(yhat), y) + np.multiply((1 - y), np.log(1 - yhat))))
        return loss

    
    def forward_propagation(self):
        '''
        Performs the forward propagation
        '''
        
        Z1 = self.X.dot(self.params['W1']) + self.params['b1']
        A1 = self.relu(Z1)
        Z2 = A1.dot(self.params['W2']) + self.params['b2']
        yhat = self.sigmoid(Z2)
        loss = self.entropy_loss(self.y,yhat)

        # save calculated parameters     
        self.params['Z1'] = Z1
        self.params['Z2'] = Z2
        self.params['A1'] = A1

        return yhat,loss

    
    def back_propagation(self,yhat):
        '''
        Computes the derivatives and update weights and bias according.
        '''
        def dRelu(x):
            x[x<=0] = 0
            x[x>0] = 1
            return x
        
        dl_wrt_yhat = -(np.divide(self.y,yhat) - np.divide((1 - self.y),(1-yhat)))
        dl_wrt_sig = yhat * (1-yhat)
        dl_wrt_z2 = dl_wrt_yhat * dl_wrt_sig

        dl_wrt_A1 = dl_wrt_z2.dot(self.params['W2'].T)
        dl_wrt_w2 = self.params['A1'].T.dot(dl_wrt_z2)
        dl_wrt_b2 = np.sum(dl_wrt_z2, axis=0)

        dl_wrt_z1 = dl_wrt_A1 * dRelu(self.params['Z1'])
        dl_wrt_w1 = self.X.T.dot(dl_wrt_z1)
        dl_wrt_b1 = np.sum(dl_wrt_z1, axis=0)

        #update the weights and bias
        self.params['W1'] = self.params['W1'] - self.learning_rate * dl_wrt_w1
        self.params['W2'] = self.params['W2'] - self.learning_rate * dl_wrt_w2
        self.params['b1'] = self.params['b1'] - self.learning_rate * dl_wrt_b1
        self.params['b2'] = self.params['b2'] - self.learning_rate * dl_wrt_b2

        
    def fit(self, X, y):
        '''
        Trains the neural network using the specified data and labels
        '''
        self.X = X
        self.y = y
        self.init_weights() #initialize weights and bias


        for i in range(self.iterations):
            yhat, loss = self.forward_propagation()
            self.back_propagation(yhat)
            self.loss.append(loss)
            
            
    def predict(self, X):
        '''
        Predicts on a test data
        '''
        Z1 = X.dot(self.params['W1']) + self.params['b1']
        A1 = self.relu(Z1)
        Z2 = A1.dot(self.params['W2']) + self.params['b2']
        pred = self.sigmoid(Z2)
        return np.round(pred)              

                                
    def acc(self, y, yhat):
        '''
        Calculates the accutacy between the predicted valuea and the truth labels
        '''
        acc = int(sum(y == yhat) / len(y) * 100)
        return acc


    def plot_loss(self):
        '''
        Plots the loss curve
        '''
        plt.plot(self.loss)
        plt.xlabel("Iteration")
        plt.ylabel("logloss")
        plt.title("Loss curve for training")
        plt.show()
