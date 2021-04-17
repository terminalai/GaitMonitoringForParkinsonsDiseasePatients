from thundersvm import SVC

def predict(y, z):
    clf = SVC(kernel="linear")
    clf.load_from_file("./model")
    clf.predict([[y, z]])[0]


if __name__ == "__main__":
    import sys
    # python bot.py "I am an engineer"
    freezeY = float(sys.argv[1])
    freezeZ = float(sys.argv[2])
    print(predict(freezeY, freezeZ))
    sys.stdout.flush()
