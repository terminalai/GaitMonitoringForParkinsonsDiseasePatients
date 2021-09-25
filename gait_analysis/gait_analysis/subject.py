from .algorithms import classify, inform, fIndex


class Subject:
    def __init__(self, name, *arrs):
        self.name = name
        self.ind = int(name)
        self.results = []
        self.add(*arrs)

    def add(self, *arrs):
        for df in arrs:
            self.results.append(df.copy().to_numpy())
        return self

    @staticmethod
    def parse(arr, names):
        assert len(arr) == len(names)
        name_lst = names
        sort = arr
        subjects = []
        names_covered = []
        for i in range(len(arr)):
            name = name_lst[i][1:3]
            if name in names_covered:
                subjects[-1].add(sort[i])
            else:
                subjects.append(Subject(name, sort[i]))
                names_covered.append(name)

        return subjects
    
    def analyze(self, *inds):
        if len(inds) == 0:
            inds = list(range(len(self.results)))
            
        freezeIndices = []
        for i in inds:
            FIs = fIndex(self.results[i])
            freezeIndices.append(FIs)
        return freezeIndices

    def predict(self, *inds):
        if len(inds) == 0:
            inds = list(range(len(self.results)))
        lframess = []
        for i in inds:
            lframes = classify(self.results[i])
            lframess.append(lframes)
        return lframess

    def info(self, *inds):
        if len(inds) == 0:
            inds = list(range(len(self.results)))
        infos = []
        for i in inds:
            info = inform(self.results[i])
            infos.append(info)
        return infos

    def __str__(self):
        return self.name + " " + str(len(self.results))

    def __repr__(self):
        return self.name + " " + str(len(self.results))


__all__ = [Subject]
