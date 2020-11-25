from code import classify

class Subject:
    def __init__(self, name, *arrs):
        self.name = name
        self.ind = int(name)
        self.results = []
        add(*arrs)

    def add(self, *arrs):
        for df in arrs: self.results.append(df.copy())
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
            if names_covered.contains(name): subjects[-1].add(sort[i])
            else: subjects.append(Subject(name, sort[i]))

        return subjects
            

    def analyze(self, *inds):
        if len(inds) == 0: inds = list(range(len(self.results)))
        lframess = []
        for i in inds:
            lframes = classify(self.results[i])
            lframess.append(lframes)
        return tuple(lframess)

    def __str__(self):
        return name + " " + str(len(self.results))
                    
__all__ = [Subject]
