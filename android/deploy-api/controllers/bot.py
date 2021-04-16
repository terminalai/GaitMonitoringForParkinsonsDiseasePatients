from nltk.corpus import wordnet as wn
from textblob import TextBlob
import random
from helperfunctions import get_treatment_info, get_disease_names


greetings = ("hello", "hi", "greetings", "sup", "what's up",)

greeting_response = ["What can I do for you today?",
                        "Hello! Please start by uploading the image.",
                        "Hi there! Starting by uploading the image.",
                        "Welcome to derma D. What can I do today?"]

confused_response = ["Sorry, can you please repeat that?",
                        "Try uploading an image first?",
                        "Say that again? You can always try uploading an image though.",
                        "Upload image by clicking on the camera/gallery button!",
                        "Sorry, didn't understand? Maybe try uploading an image"]

goodbye = ("bye", "goodbye", "thank you", "thanks",)

goodbye_response = ["Bye! Pleased to be at your assistance.",
                        "Get well soon!",
                        "Take care and get some rest!",
                        "Hope I could be of some help!"]

affirmative = ["yes","okay","sure","ok","yeah","yup"]
negative = ["no","nope","none","not","never","nah"]

def check_greeting(sentence):

    """If any of the words in the user's input was a greeting, return a greeting response"""
    for word in sentence.words:
        if word.lower() in greetings:
            return random.choice(greeting_response)

def check_goodbye(sentence):

    """If any of the words in the user's input was a goodbye, return a goodbye response"""
    for word in sentence.words:
        if word.lower() in goodbye:
            return random.choice(goodbye_response)

def did_not_understand():
    return random.choice(confused_response)

def check_affirmation(sentence):
    for word in sentence.words:
        if word.lower() in affirmative:
            return True

def check_negative(sentence):
    for word in sentence.words:
        if word.lower() in negative:
            return True

def response(sentence):
    parsed = TextBlob(saying)
    resp = check_greeting(parsed)
    if not resp:
        resp = check_goodbye(parsed)
    if check_affirmation(parsed):
        resp = "positive"
    if check_negative(parsed):
        resp = "negative"
    if not resp:
        resp = did_not_understand()
    return resp
# Get all the synonyms for different words

# Specify utterances:
# 1. Greetings: Hi, Hello, What's up, Hey,
# 2. Treatment: Select the image
# 2. Option: What would you like me to diagnose/examine?
# 3. Results fetched from the API from bot itself, provided with the dataset
# 3. Show the diseases and some mild recommendation of medicine
# 4. Would you like me to find you the nearest derma?
# 5. User : {Yes/ NO}
# 6. If "yes" then find three nearest dermas with their addresses [made in the form of cardview]
# 7. Else, "Goodbye for now then! Get well soon"

if __name__ == '__main__':
    import sys
    # python bot.py "I am an engineer"
    saying = sys.argv[1]
    print(response(saying))
    sys.stdout.flush()
