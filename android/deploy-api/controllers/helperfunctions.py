import json
import os
import pandas as pd
import ast
from nltk.corpus import wordnet as wn
from textblob import TextBlob




diseases_json = json.load(open(os.path.dirname(os.path.abspath(__file__))+'/data/disease_json.json', 'r'))

# convert json string to pandas Dataframe
diseases = pd.DataFrame(diseases_json)

# input: a string with the name of a single disease
# output: returns a string carrying the coressponding treatment info

def get_treatment_info(disease_name):

    treat = diseases.loc[diseases['name']==disease_name.upper()]['treatment_info'].tolist()
    if treat:
        return treat[0]
    else:
        return "No treatment found."

# input: a list containing multiple strings representing various symptoms
# output: returns a list of strings consisting of three most probable diseases according the symptoms provided as input

def get_disease_names(tag_list):
    d = []
    for tag in tag_list:
        if (tag.upper() in diseases["name"].tolist()):
            d.append(tag)
            return d
    for tag in tag_list:
        limit = 0
        for syn in wn.synsets(tag):
            for lemma in syn.lemmas():
                tag_list.append(lemma.name())
            limit=limit+1
        if(limit>5):
            break
    s_no = diseases["s_no"]
    diseases["matched_symptoms"] = pd.Series()
    p = []
    for i in s_no:
        disease_symptom_list = ast.literal_eval(diseases.loc[diseases["s_no"]==i]["symptoms"].tolist()[0])
        p.append(len(set(tag_list)&set(disease_symptom_list)))

    diseases["matched_symptoms"] = p
    matched_diseases = diseases.sort_values(by=["matched_symptoms"], ascending=False)[["name"]][0:3]["name"].tolist()

    return matched_diseases

def get_disease_dicts(tag_list):
    matched_diseases = get_disease_names(tag_list)
    matched_disease_list = []

    for disease in matched_diseases:
        matched_disease_dict = {}
        matched_disease_dict["disease_name"] = disease
        matched_disease_dict["treatment_info"]  = get_treatment_info(disease)
        matched_disease_list.append(matched_disease_dict)

    return json.dumps(matched_disease_list)



# if __name__ == '__main__':
#     import sys
#     # python bot.py "I am an engineer"
#     info = sys.argv[1]
#     print(get_disease_dicts(info))
#     sys.stdout.flush()
