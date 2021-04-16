from thundersvm import SVC

clf = SVC(kernel="linear")
clf.load_from_file("./model")

def predict(x, y, z):
    clf.predict([y, z])

