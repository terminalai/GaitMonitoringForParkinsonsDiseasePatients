from ._importable import LazyImport, _import_statements



# ADD YOUR IMPORTS BELOW
# TODO: in this file you can add your most important modules and objects

# If you are missing an import, please contribute via creating a pull request.
# If you contribute, we can quickly collect the 80% most frequent imports
# Before you create a pull request, please read the following:

# 0) It is always best to first create a GitHub issue before creating a pull request.
# This way you can be sure that your proposal is valid and will be integrated.

# 1) The imported name should be an unambiguous standard convention and highly specific.
# Usually, you want to use the names that are proposed in the library's documentation.
# However, there should be no or little confusion with other libraries
# e.g. 'import dash_html_components as html' is a 'good' counter example
# because 'html' is not specific enough for the dash context.
# Also, it is ambiguous with e.g. IPython.display.HTML.
# A potential resolution might be 'import dash_html_components as dhc'

# 2) General imports e.g. 'from sklearn.preprocessing import *' are not allowed/possible
# because we want to make sure that there is no accidental masking of imported names

# 3) If you disagree with the conventions, you can always adjust your local pyforest

### Helper
sys = LazyImport("import sys", "LIBRARY: sys — System-specific parameters and functions, derived from 'import sys', part of The Python Standard Library")
os = LazyImport("import os", "LIBRARY: os — Miscellaneous operating system interfaces, derived from 'import os', part of The Python Standard Library")
io = LazyImport("import io", "LIBRARY: io — Core tools for working with streams, derived from 'import io', part of The Python Standard Library")
re = LazyImport("import re", "LIBRARY: re - regex operations, derived from 'import re', part of The Python Standard Library")
glob = LazyImport("import glob", "LIBRARY: glob — Unix style pathname pattern expansion, derived from 'import glob', part of The Python Standard Library")
Path = LazyImport("from pathlib import Path", "CLASS: pathlib.Path — represents concrete paths of the system’s path flavour, derived from 'from pathlib import Path', part of The Python Standard Library")
pathlib = LazyImport("import pathlib", "LIBRARY: pathlib — Object-oriented filesystem paths, derived from 'import pathlib', part of The Python Standard Library")
pickle = LazyImport("import pickle", "LIBRARY: pickle — Python object serialization, derived from 'import pickle', part of The Python Standard Library")
tqdm = LazyImport("import tqdm", "PACKAGE: tqdm — A Fast Extensible Progress Bar for Python and CLI, derived from 'import tqdm', from https://github.com/tqdm/tqdm, Documentation: https://tqdm.github.io/")
sh = LazyImport("import sh", "PACKAGE: sh — a unique subprocess wrapper that maps your system programs to Python functions dynamically, derived from 'import sh', from https://github.com/amoffat/sh, Documentation: http://amoffat.github.io/sh/")
inspect = LazyImport("import inspect", "LIBRARY: inspect — Inspect live objects, derived from 'import inspect', part of The Python Standard Library")
stat = LazyImport("import stat", "LIBRARY: stat — Interpreting os.stat(), os.fstat() and os.lstat() results, derived from 'import stat', part of The Python Standard Library")
subprocess = LazyImport("import subprocess", "LIBRARY: subprocess — Subprocess management, derived from 'import subprocess', part of The Python Standard Library")


### Basics
math = LazyImport("import math", "LIBRARY: math — Mathematical functions, derived from 'import math', part of The Python Standard Library")
cmath = LazyImport("import cmath", "LIBRARY: cmath — Mathematical functions for complex numbers, derived from 'import cmath', part of The Python Standard Library")
statistics = LazyImport("import statistics", "LIBRARY: statistics — Mathematical statistics functions, derived from 'import statistics', part of The Python Standard Library")
random = LazyImport("import random", "LIBRARY: random — Generate pseudo-random numbers, derived from 'import random', part of The Python Standard Library")
choice = LazyImport("from random import choice", "FUNCTION: random.choice(seq) — Returns a random element from seq, derived from 'from random import choice', part of The Python Standard Library")
randint = LazyImport("from random import randint", "FUNCTION: random.randint(a, b) — Computes a random integer between a and b (both inclusive), derived from 'from random import randint', part of The Python Standard Library")
randrange = LazyImport("from random import randrange", "FUNCTION: random.randrange(start, stop[, step=1]) — Returns a randomly selected element from range(start, stop, step), derived from 'from random import randrange', part of The Python Standard Library")
shuffle = LazyImport("from random import shuffle", "FUNCTION: random.shuffle(x) — Shuffle sequence x in place, derived from 'from random import shuffle', part of The Python Standard Library")


### Type based manipultation
collections = LazyImport("import collections", "LIBRARY: collections — Container datatypes, derived from 'import collections', part of The Python Standard Library")
functools = LazyImport("import functools", "LIBRARY: functools — Higher-order functions and operations on callable objects, derived from 'import functools', part of The Python Standard Library")
itertools = LazyImport("import itertools", "LIBRARY: itertools — Functions creating iterators for efficient looping, derived from 'import itertools', part of The Python Standard Library")
typing = LazyImport("import typing", "LIBRARY: typing — Support for type hints, derived from 'import typing', part of The Python Standard Library")
textwrap = LazyImport("import textwrap", "LIBRARY: textwrap — Text wrapping and filling, derived from 'import textwrap', part of The Python Standard Library")
stringprep = LazyImport("import stringprep", "LIBRARY: stringprep — Internet String Preparation, derived from 'import stringprep', part of The Python Standard Library")
reprlib = LazyImport("import reprlib", "LIBRARY: reprlib — Alternate repr() implementation, derived from 'import reprlib', part of The Python Standard Library")
numbers = LazyImport("import numbers", "LIBRARY: numbers — Numeric abstract base classes, derived from 'import numbers', part of The Python Standard Library")
decimal = LazyImport("import decimal", "LIBRARY: decimal — Decimal fixed point and floating point arithmetic, derived from 'import decimal', part of The Python Standard Library")
Decimal = LazyImport("from decimal import Decimal", "CLASS: decimal.Decimal(value=0) — Decimal wrapper class, derived from 'from decimal import Decimal', part of The Python Standard Library")
fractions = LazyImport("import fractions", "LIBRARY: fractions — Rational numbers, derived from 'import fractions', part of The Python Standard Library")
Fraction = LazyImport("from fractions import Fraction", "CLASS: Fraction — creates a fraction, derived from 'from fractions import Fraction', part of The Python Standard Library")
operator = LazyImport("import operator", "LIBRARY: operator — Standard operators as functions, derived from 'import operator', part of The Python Standard Library")
arr = LazyImport("from array import array as arr", "CLASS arr derived from array.array — a base array, derived from 'from array import array as arr', part of The Python Standard Library")
array_funcs = LazyImport("import array as array_funcs", "LIBRARY: array_funcs derived from array — Efficient arrays of numeric values, derived from 'import array as array_funcs', part of The Python Standard Library")


### Random Objects
pprint = LazyImport("from pprint import pprint", "FUNCTION: pprint — Data pretty printer, derived from 'from pprint import pprint', part of The Python Standard Library")
importlib = LazyImport("import importlib", "LIBRARY: importlib — implementation of import, derived from 'import importlib', part of The Python Standard Library")
bisect = LazyImport("import bisect", "LIBRARY: bisect — Array bisection algorithm, derived from 'import bisect', part of The Python Standard Library")
bisect_right = LazyImport("from bisect import bisect_right", "FUNCTION bisect_right(a, x, lo=0, hi=len(a)) — Locates the insertion point for x in a to maintain sorted order which comes before any existing entries of x in a, derived from 'from bisect import bisect_right', part of The Python Standard Library")
bisect_left = LazyImport("from bisect import bisect_left", "FUNCTION bisect_left(a, x, lo=0, hi=len(a)) — Locates the insertion point for x in a to maintain sorted order which comes before any existing entries of x in a, derived from 'from bisect import bisect_left', part of The Python Standard Library")
#, " — , derived from 'import ', part of The Python Standard Library"
cf = LazyImport("import cufflinks as cf", "PACKAGE cf derived from cufflinks — Productivity Tools for Plotly + Pandas, derived from 'import cufflinks as cf', from https://github.com/santosjorge/cufflinks")
cufflinks = LazyImport("import cufflinks", "PACKAGE cufflinks — Productivity Tools for Plotly + Pandas, derived from 'import cufflinks', from https://github.com/santosjorge/cufflinks")
ucd = LazyImport("import unicodedata as ucd", "LIBRARY ucd derived from unicodedata — Unicode Database, derived from 'import unicodedata as ucd', part of The Python Standard Library")
unicodedata = LazyImport("import unicodedata", "LIBRARY unicodedata — Unicode Database, derived from 'import unicodedata', part of The Python Standard Library")


### Google 
google = LazyImport("import googlesearch as google", "PACKAGE google derived from googlesearch — The google package, derived from 'import googlesearch as google', from https://github.com/MarioVilas/googlesearch, Documentation: https://python-googlesearch.readthedocs.io/en/latest/")
googlesearch = LazyImport("import googlesearch", "PACKAGE googlesearch — The google package, derived from 'import googlesearch', from https://github.com/MarioVilas/googlesearch")
convert = LazyImport("from google_currency import convert", "FUNCTION google_currency.convert(original_currency_type, new_currency_type, original_amount) — simple function to convert the currency of one country to other, derived from 'from google_currency import convert', from https://github.com/om06/google-currency")
Translator = LazyImport("from googletrans import Translator", "CLASS googletrans.Translator — class to imitate Google Translate, derived from 'from googletrans import Translator', from https://github.com/ssut/py-googletrans, Documentation: https://py-googletrans.readthedocs.io/en/latest/")
'''
search = LazyImport("from googlesearch import search")
images = LazyImport("from googlesearch import search_images as images")
news = LazyImport("from googlesearch import search_news as news")
videos = LazyImport("from googlesearch import search_videos as videos")
shop = LazyImport("from googlesearch import search_shop as shop")
books = LazyImport("from googlesearch import search_books as books")
apps = LazyImport("from googlesearch import search_apps as apps")
lucky = LazyImport("from googlesearch import lucky")
hits = LazyImport("from googlesearch import hits")
ngd = LazyImport("from googlesearch import ngd")
'''
geopy = LazyImport("import geopy", "PACKAGE geopy — Geocoding library for Python, derived from 'import geopy', from https://github.com/geopy/geopy, Documentation: https://geopy.readthedocs.io/")
GoogleV3 = LazyImport("from geopy import GoogleV3", "CLASS geopy.GoogleV3 — Geocoder using the Google Maps v3 API, derived from 'from geopy import GoogleV3', from https://github.com/geopy/geopy, Documentation: https://geopy.readthedocs.io/en/stable/#googlev3")
geodesic = LazyImport("from geopy.distance import geodesic", "CLASS geopy.distance.geodesic — Calculate the geodesic distance between two points, derived from 'from geopy.distance import geodesic', from https://github.com/geopy/geopy, Documentation: https://geopy.readthedocs.io/en/stable/#module-geopy.distance")
geodist = LazyImport("from geopy import distance as geodist", "SUBPACKAGE geodist derived from geopy.distance — Calculating distance between two points, derived from 'import geopy.distance as geodist', from https://github.com/geopy/geopy, Documentation: https://geopy.readthedocs.io/en/stable/#module-geopy.distance")
youtube_dl = LazyImport("import youtube_dl", "PACKAGE youtube_dl — Command-line program to download videos from YouTube.com and other video sites, derived from 'import youtube_dl', from https://github.com/ytdl-org/youtube-dl")
YouTube = LazyImport("from pytube import YouTube", "CLASS pytube.YouTube(url) — A lightweight YouTube wrapper for downloading YouTube videos, derived from 'from pytube import YouTube', from https://github.com/nficano/pytube, Documentation: https://python-pytube.readthedocs.io/en/latest/api.html#youtube-object")
pafy = LazyImport("import pafy", "PACKAGE pafy — Python library to download YouTube content and retrieve metadata, derived from 'import pafy', from https://github.com/mps-youtube/pafy, Documentation: https://pythonhosted.org/pafy/")
lxml = LazyImport("import lxml", "PACKAGE lxml — The lxml XML toolkit for Python, derived from 'import lxml', from https://github.com/lxml/lxml, Documentation: https://lxml.de/")
etree = LazyImport("from lxml import etree", "SUBPACKAGE lxml.etree — The ElementTree API, derived from 'from lxml import etree', from https://github.com/lxml/lxml, Documentation: https://lxml.de/")
smtplib = LazyImport("import smtplib", "LIBRARY smtplib — SMTP protocol client, derived from 'import smtplib', part of The Python Standard Library")


### File Manipulation
difflib = LazyImport("import difflib", "LIBRARY difflib — Helpers for computing deltas, derived from 'import difflib', part of The Python Standard Library")
tablib = LazyImport("import tablib", "PACKAGE tablib — Python Module for Tabular Datasets in XLS, CSV, JSON, YAML, derived from 'import tablib', from https://github.com/jazzband/tablib, Documentation: https://tablib.readthedocs.io/")
pickle = LazyImport("import pickle", "LIBRARY pickle — Python object serialization, derived from 'import pickle', part of The Python Standard Library")
csv = LazyImport("import csv", "LIBRARY csv — CSV File Reading and Writing, derived from 'import csv', part of The Python Standard Library")
fileinput = LazyImport("import fileinput", "LIBRARY fileinput — Iterate over lines from multiple input streams, derived from 'import fileinput', part of The Python Standard Library")


### Image Stuff
Image = LazyImport("from PIL import Image")
PIL = LazyImport("import PIL")
opencv = LazyImport("import cv2 as opencv", "PACKAGE opencv derived from cv2 — Open Source Computer Vision Library, derived from 'import cv2 as opencv', from https://github.com/opencv/opencv, Documentation: https://docs.opencv.org/master/")
cv2 = LazyImport("import cv2", "PACKAGE cv2 — Open Source Computer Vision Library, derived from 'import cv2', from https://github.com/opencv/opencv, Documentation: https://docs.opencv.org/master/")


### Python Time
cal = LazyImport("import calendar as cal", "LIBRARY cal derived from calendar — General calendar-related functions, derived from 'import calendar as cal', part of The Python Standard Library")
calendar = LazyImport("import calendar", "LIBRARY calendar — General calendar-related functions, derived from 'import calendar', part of The Python Standard Library")
dt = LazyImport("import datetime as dt", "LIBRARY dt derived from datetime — Basic data and time types, derived from 'import datetime as dt', part of The Python Standard Library")
datetime = LazyImport("from datetime import datetime", "SUBLIBRARY datetime.datetime — Basic data and time types, derived from 'from datetime import datetime', part of The Python Standard Library")
time = LazyImport("import time", "LIBRARY time — Time access and conversions, derived from 'import time', part of The Python Standard Library")
relativedelta = LazyImport("from dateutil.relativedelta import relativedelta", "CLASS dateutil.relativedelta.relativedelta — relativedelta instance, derived from 'from dateutil.relativedelta import relativedelta', from https://github.com/dateutil/dateutil, Documentation: https://dateutil.readthedocs.io/en/stable/relativedelta.html")


## Audio
playsound = LazyImport('from playsound import playsound', "FUNCTION playsound.playsound('/path.to/a/sound/file/you/want/to/play.mp3 | URL', block=True) — Function to playing sound file with filetype WAVE and MP3, derived from 'from playsound import playsound', from https://github.com/TaylorSMarks/playsound")
sa = LazyImport('import simpleaudio as sa', "PACKAGE sa derived from simpleaudio — A cross-platform dependency-free asynchronous simple audio playback Python extension, derived from 'import simpleaudio as sa', from https://github.com/hamiltron/py-simple-audio, Documentation: https://simpleaudio.readthedocs.io/en/latest/")
simpleaudio = LazyImport('import simpleaudio', "PACKAGE simpleaudio — A cross-platform dependency-free asynchronous simple audio playback Python extension, derived from 'import simpleaudio', from https://github.com/hamiltron/py-simple-audio, Documentation: https://simpleaudio.readthedocs.io/en/latest/")
fc = LazyImport("from simpleaudio import functionchecks as fc", "SUBPACKAGE fc derived from simpleaudio.functionchecks — Quick Function Check for simpleaudio package, derived from 'import simpleaudio.functionchecks as fc', from https://github.com/hamiltron/py-simple-audio, Documentation: https://simpleaudio.readthedocs.io/en/latest/")
winsound = LazyImport('import winsound', "LIBRARY winsound — Sound-playing interface for Windows, derived from 'import winsound', from The Python Standard Library")
#beep = LazyImport('from winsound import Beep as beep')
sd = LazyImport('import sounddevice as sd', "PACKAGE sd derived from sounddevice — Play and Record Sound with Python, derived from 'import sounddevice as sd', from https://github.com/spatialaudio/python-sounddevice, Documentation: https://python-sounddevice.readthedocs.io/en/0.3.15/")
sounddevice = LazyImport('import sounddevice', "PACKAGE sounddevice — Play and Record Sound with Python, derived from 'import sounddevice', from https://github.com/spatialaudio/python-sounddevice, Documentation: https://python-sounddevice.readthedocs.io/en/0.3.15/")
sf = LazyImport('import soundfile as sf', "PACKAGE sf derived from soundfile — SoundFile is an audio library based on libsndfile, CFFI and NumPy, derived from 'import soundfile as sf', from https://github.com/bastibe/SoundFile")
soundfile = LazyImport('import soundfile', "PACKAGE soundfile — SoundFile is an audio library based on libsndfile, CFFI and NumPy, derived from 'import soundfile', from https://github.com/bastibe/SoundFile")
AudioSegment = LazyImport('from pydub import AudioSegment', "CLASS pydub.AudioSegment — Classes that contain Audio Segments, derived from 'from pydub import AudioSegment', from https://github.com/jiaaro/pydub, Documentation: https://github.com/jiaaro/pydub/blob/master/API.markdown")
pydub = LazyImport('import pydub', "PACKAGE pydub — Manipulate audio with a simple and easy high level interface, derived from 'import pydub', from https://github.com/jiaaro/pydub, Documentation: https://github.com/jiaaro/pydub/blob/master/API.markdown")
playwav = LazyImport('from pydub.playback import play as playwav', "FUNCTION playwav derived from pydub.playback.play — Play WAV-based AudioSegment Objects, derived from 'from pydub.playback import play as playwav', from https://github.com/jiaaro/pydub, Documentation: https://github.com/jiaaro/pydub#playback")
wave = LazyImport('import wave', "LIBRARY wave — Read and write WAV files, derived from 'import wave', from The Python Standard Library")
pyaudio = LazyImport('import pyaudio', "PACKAGE pyaudio — Python Bindings for PortAudio, derived from 'import pyaudio', from https://github.com/jleb/pyaudio, Documentation: http://people.csail.mit.edu/hubert/pyaudio/docs/")


## Windows
wincl = LazyImport('import win32com.client as wincl', "PACKAGE wincl derived from win32com.client — Python for Windows (pywin32) Extensions, derived from 'import win32com.client as wincl', from https://github.com/mhammond/pywin32, Documentation: http://timgolden.me.uk/pywin32-docs/contents.html")


### Shell Stuff yeet
shell = LazyImport('from shell import shell', "FUNCTION shell.shell — Shortcut function to quickly run a command, derived from 'from shell import shell', from https://github.com/toastdriven/shell, Documentation: https://shell.readthedocs.io/en/latest/shell_api.html#module-shell")
Shell = LazyImport('from shell import Shell', "CLASS shell.Shell — Class to run multiple commands and extend the behaviour, derived from 'from shell import Shell', from https://github.com/toastdriven/shell, Documentation: https://shell.readthedocs.io/en/latest/shell_api.html#module-shell")


### Auto-complete
jedi = LazyImport("import jedi", "PACKAGE jedi — Awesome autocompletion, static analysis and refactoring library for Python, derived from 'import jedi', from https://github.com/davidhalter/jedi, Documentation: https://jedi.readthedocs.io/en/latest/")
setup_readline = LazyImport("from jedi.utils import setup_readline", "FUNCTION setup_readline — Function that sets up readline to use Jedi in a Python interactive shell, derived from 'from jedi.utils import setup_readline', from https://github.com/davidhalter/jedi, Documentation: https://jedi.readthedocs.io/en/latest/docs/usage.html")
readline = LazyImport("import readline", "LIBRARY readline — GNU readline interface, derived from 'import readline', from The Python Standard Library")
rlcompleter = LazyImport("import rlcompleter", "LIBRARY rlcompleter — Completion function for GNU readline, derived from 'import rlcompleter', from The Python Standard Library")


### GUI
tk = LazyImport("import tkinter as tk", "LIBRARY tk derived from tkinter — Python interface to Tcl/Tk, derived from 'import tkinter as tk', from The Python Standard Library")
Tkinter = LazyImport("import tkinter as Tkinter", "LIBRARY Tkinter derived from tkinter — Python interface to Tcl/Tk, derived from 'import tkinter as Tkinter', from The Python Standard Library")
tkinter = LazyImport("import tkinter", "LIBRARY tkinter — Python interface to Tcl/Tk, derived from 'import tkinter', from The Python Standard Library")
tkFont = LazyImport("import tkinter.font as tkFont", "SUBLIBRARY tkFont derived from tkinter.font — Tkinter font wrapper, derived from 'import tkinter.font as tkFont', from The Python Standard Library")
ttk = LazyImport("from tkinter import ttk", "SUBLIBRARY tkinter.ttk — Tk themed widgets, derived from 'from tkinter import ttk', from The Python Standard Library")
turtle = LazyImport("import turtle", "LIBRARY turtle — Turtle graphics based on tkinter, derived from 'import turtle', from The Python Standard Library")
teek = LazyImport("import teek", "PACKAGE teek — A pythonic and user-friendly alternative to tkinter, derived from 'import teek', from https://github.com/Akuli/teek, Documentation: https://teek.readthedocs.io/en/latest/")

qt = LazyImport("import PyQt5 as qt", "PACKAGE qt derived from PyQt5 — Module that uses Qt from C++, derived from 'import PyQt5 as qt', from https://github.com/baoboa/pyqt5, Documentation: ")
Qt = LazyImport("import PyQt5 as Qt", "PACKAGE Qt derived from PyQt5 — Module that uses Qt from C++, derived from 'import PyQt5 as Qt', from https://github.com/baoboa/pyqt5, Documentation: ")
PyQt = LazyImport("import PyQt5 as PyQt", "PACKAGE PyQt derived from PyQt5 — Module that uses Qt from C++, derived from 'import PyQt5 as PyQt', from https://github.com/baoboa/pyqt5, Documentation: ")
pyqt = LazyImport("import PyQt5 as pyqt", "PACKAGE pyqt derived from PyQt5 — Module that uses Qt from C++, derived from 'import PyQt5 as pyqt', from https://github.com/baoboa/pyqt5, Documentation: ")
PyQt4 = LazyImport("import PyQt5 as PyQt4", "PACKAGE PyQt4 derived from PyQt5 — Module that uses Qt from C++, derived from 'import PyQt5 as PyQt5', from https://github.com/baoboa/pyqt5, Documentation: ")
PyQt5 = LazyImport("import PyQt5", "PACKAGE PyQt5 — Module that uses Qt from C++, derived from 'import PyQt5', from https://github.com/baoboa/pyqt5, Documentation: ")

pygame = LazyImport("import pygame", "PACKAGE pygame — Free and Open Source library for making multimedia applications like games built on top of the excellent SDL library, derived from 'import pygame', from https://github.com/pygame/pygame, Documentation: https://www.pygame.org/docs/")
mixer = LazyImport("from pygame import mixer", "SUBPACKAGE pygame.mixer — Pygame module for loading and playing sounds, derived from 'from pygame import mixer', from https://github.com/pygame/pygame, Documentation: https://www.pygame.org/docs/ref/mixer.html")

kivy = LazyImport("import kivy", "PACKAGE kivy — Open source UI framework written in Python, derived from 'import kivy', from https://github.com/kivy/kivy, Documentation: https://kivy.org/doc/stable/")
kivyApp = LazyImport("from kivy.app import App as kivyApp", "CLASS kivyApp derived from kivy.app.App — Base for creating Kivy applications, derived from 'from kivy.app import App as kivyApp', from https://github.com/kivy/kivy, Documentation: https://kivy.org/doc/stable/api-kivy.app.html")
kivyuilds = LazyImport("import kivy.uix as kivyuilds", "SUBPACKAGE kivyuilds derived from kivy.uix — Classes for creating and managing Widgets, derived from 'import kivy.uix as kivyuilds', from https://github.com/kivy/kivy, Documentation: https://kivy.org/doc/stable/api-kivy.uix.html")

### Web Stuff
urllib2 = LazyImport("import urllib.request as urllib2", "LIBRARY urllib2 derived from urllib.request — Extensive library for opening URLs, derived from 'import urllib.request as urllib2', from The Python Standard Library")
urllib = LazyImport("import urllib", "LIBRARY urllib — URL handling modules, derived from 'import urllib', from The Python Standard Library")
urlopen = LazyImport("from urllib.request import urlopen", "FUNCTION urllib.request.urlopen(url...) — Opens the URL url which can be either a string or a Request object, derived from 'from urllib.request import urlopen', from The Python Standard Library")
requests = LazyImport("import requests", "PACKAGE requests — A simple, yet elegant HTTP library, derived from 'import requests', from https://github.com/psf/requests, Documentation: https://requests.readthedocs.io/en/master/")
json = LazyImport("import json", "LIBRARY json — JSON encoder and decoder, derived from 'import json', from The Python Standard Library")
BeautifulSoup = LazyImport("from bs4 import BeautifulSoup", "CLASS bs4.BeautifulSoup — HTML/XML Formatter on Python, derived from 'from bs4 import BeautifulSoup', from https://www.crummy.com/software/BeautifulSoup/, Documentation: https://www.crummy.com/software/BeautifulSoup/bs4/doc/")
scrapy = LazyImport("import scrapy", "PACKAGE scrapy — A fast high-level web crawling & scraping framework for Python, derived from 'import scrapy', from https://github.com/scrapy/scrapy, Documentation: https://docs.scrapy.org/en/latest/index.html")

newspaper = LazyImport("import newspaper", "PACKAGE newspaper — News, full-text, and article metadata extraction in Python 3, derived from 'import newspaper', from https://github.com/codelucas/newspaper, Documentation: https://newspaper.readthedocs.io/en/latest/")
Article = LazyImport("from newspaper import Article", "CLASS newspaper.Article — Article scraping and curation, derived from 'from newspaper import Article', from https://github.com/codelucas/newspaper, Documentation: https://newspaper.readthedocs.io/en/latest/user_guide/quickstart.html#news-articles")
fulltext = LazyImport("fromn newspaper import fulltext", "FUNCTION newspaper.fulltext — Extract full text from an article, derived from 'from newspaper import fulltext', from https://github.com/codelucas/newspaper, Documentation: https://newspaper.readthedocs.io/en/latest/")
wikipedia = LazyImport("import wikipedia", "PACKAGE wikipedia — A Pythonic wrapper for the Wikipedia API, derived from 'import wikipedia', from https://github.com/goldsmith/Wikipedia, Documentation: https://wikipedia.readthedocs.io/en/latest/")

pyyaml = LazyImport("import pyyaml", "PACKAGE pyyaml — The next generation YAML parser and emitter for Python, derived from 'import pyyaml', from https://github.com/yaml/pyyaml, Documentation: https://pyyaml.org/wiki/PyYAMLDocumentation")
yaml = LazyImport("import pyyaml as yaml", "PACKAGE yaml derived from pyyaml — The next generation YAML parser and emitter for Python, derived from 'import pyyaml as yaml', from https://github.com/yaml/pyyaml, Documentation: https://pyyaml.org/wiki/PyYAMLDocumentation")

webbrowser = LazyImport("import webbrowser", "LIBRARY webbrowser — Convenient Web-browser controller, derived from 'import webbrowser', from The Python Standard Library")
wb = LazyImport("import webbrowser as wb", "LIBRARY wb derived from webbrowser — Convenient Web-browser controller, derived from 'import webbrowser as wb', from The Python Standard Library")
openweb = LazyImport("from webbrowser import open as openweb", "FUNCTION openweb derived from webbrowser.open — Display url using the new default browser, derived from 'from webbrowser import open as openweb', from The Python Standard Library")


### Debugging
pdb = LazyImport("import pdb", "LIBRARY pdb — The Python Debugger, derived from 'import pdb', from The Python Standard Library")


### Data Wrangling
numpy = LazyImport("import numpy", "PACKAGE numpy — The fundamental package for scientific computing with Python, derived from 'import numpy', from https://github.com/numpy/numpy, Documentation: https://numpy.org/doc")
la = LazyImport("import numpy.linalg as la", "PACKAGE la derived from numpy.linalg — Linear Algebra for Python and primarily NumPy, derived from 'import numpy.linalg as la', from https://github.com/numpy/numpy, Documentation: https://numpy.org/doc/1.18/reference/routines.linalg.html")
np = LazyImport("import np", "PACKAGE np — np-altered NumPy for more functionality, derived from 'import np', from https://github.com/k7hoven/np")

scipy = LazyImport("import scipy", "PACKAGE scipy — open-source software for STEM, derived from 'import scipy', from https://github.com/scipy/scipy, Documentation: https://docs.scipy.org/doc/")
#|----> Internal Scipy ----------------------------------------#
sio = LazyImport("from scipy import io as sio")                #
special = LazyImport("from scipy import special")              #
fftpack = LazyImport("from scipy import fftpack")              #
optimize = LazyImport("from scipy import optimize")            #
op = LazyImport("from scipy import optimize as op")            #
opt = LazyImport("from scipy import optimize as opt")          #
ndimage = LazyImport("from scipy import ndimage")              #
interpolate = LazyImport("from scipy import interpolate")      #
stats = LazyImport("from scipy import stats")                  #
signal = LazyImport("from scipy import signal")                #
integrate = LazyImport("from scipy import integrate")          #
csgraph = LazyImport("from scipy.sparse import csgraph")       #
spatial = LazyImport("from scipy import spatial")              #
#--------------------------------------------------------------#

sympy = LazyImport("import sympy", "PACKAGE sympy — A computer algebra system written in pure Python, derived from 'import sympy', from https://github.com/sympy/sympy, Documentation: https://docs.sympy.org/dev/documentation-style-guide.html")
sp = LazyImport("import sympy as sp", "PACKAGE sp derived from sympy — A computer algebra system written in pure Python, derived from 'import sympy as sp', from https://github.com/sympy/sympy, Documentation: https://docs.sympy.org/dev/documentation-style-guide.html")

pandas = LazyImport("import pandas", "PACKAGE pandas — powerful Python data analysis toolkit, derived from 'import pandas', from https://github.com/pandas-dev/pandas, Documentation: https://pandas.pydata.org/pandas-docs/stable/")
pd = LazyImport("import pandas as pd", "PACKAGE pd derived from pandas — powerful Python data analysis toolkit, derived from 'import pandas as pd', from https://github.com/pandas-dev/pandas, Documentation: https://pandas.pydata.org/pandas-docs/stable/")
read_csv = LazyImport("from pandas import read_csv", "FUNCTION pandas.read_csv — Read a comma-separated values (csv) file into DataFrame, derived from 'from pandas import read_csv', from https://github.com/pandas-dev/pandas, Documentation: https://pandas.pydata.org/pandas-docs/stable/reference/api/pandas.read_csv.html")

pandas_datareader = LazyImport("import pandas_datareader", "PACKAGE pandas_datareader — Extract data from a wide range of Internet sources into a pandas DataFrame, derived from 'import pandas_datareader', from https://github.com/pydata/pandas-datareader, Documentation: https://pydata.github.io/pandas-datareader/stable/index.html")
pd_dr = LazyImport("import pandas_datareader as pd_dr", "PACKAGE pd_dr derived from pandas_datareader — Extract data from a wide range of Internet sources into a pandas DataFrame, derived from 'import pandas_datareader as pd_dr', from https://github.com/pydata/pandas-datareader, Documentation: https://pydata.github.io/pandas-datareader/stable/index.html")
pdr = LazyImport("import pandas_datareader as pdr", "PACKAGE pdr derived from pandas_datareader — Extract data from a wide range of Internet sources into a pandas DataFrame, derived from 'import pandas_datareader as pdr', from https://github.com/pydata/pandas-datareader, Documentation: https://pydata.github.io/pandas-datareader/stable/index.html")
DataReader = LazyImport("from pandas_datareader.data import DataReader", "Class DataReader — reading data from a wide range of Internet sources, derived from 'from pandas_datareader.data import DataReader', from https://github.com/pydata/pandas-datareader, Documentation: https://pydata.github.io/pandas-datareader/stable/index.html")

theano = LazyImport("import theano", "PACKAGE theano — a Python library that allows you to define, optimize, and evaluate mathematical expressions involving multi-dimensional arrays efficiently, derived from 'import theano', from https://github.com/Theano/Theano")
#T = LazyImport("import theano.tensor as T")

sm = LazyImport("import statsmodels.api as sm", "PACKAGE sm derived from statsmodels.api — statistical modeling and econometrics in Python, derived from 'import statsmodels.api as sm', from https://github.com/statsmodels/statsmodels, Documentation: https://www.statsmodels.org/stable/")
smf = LazyImport("import statsmodels.formula.api as smf", "SUBPACKAGE smf derived from statsmodels.formula.api — statistical modeling and econometrics in Python, derived from 'import statsmodels.formulas.api as sm', from https://github.com/statsmodels/statsmodels, Documentation: https://www.statsmodels.org/stable/")

dask = LazyImport("import dask", "PACKAGE dask — Parallel computing with task scheduling, derived from 'import dask', from https://github.com/dask/dask, Documentation: https://dask.org/")
dd = LazyImport("from dask import dataframe as dd", "SUBPACKAGE dd derived from dask.dataframe — Dataframes implementing the Pandas API, derived from 'from dask import dataframe as dd', from https://github.com/dask/dask, Documentation: https://dask.org/")
da = LazyImport("from dask import array as da", "SUBPACKAGE da derived from dask.array — Arrays implementing the Numpy API, derived from 'from dask import array as da', from https://github.com/dask/dask, Documentation: https://dask.org/")

pyspark = LazyImport("import pyspark", "PACKAGE pyspark — Python API for Apache Spark, derived from 'import pyspark', from https://github.com/apache/spark/tree/master/python, Documentation: https://spark.apache.org/docs/latest/api/python/pyspark.html")
spark = LazyImport("import pyspark as spark", "PACKAGE spark derived from pyspark — Python API for Apache Spark, derived from 'import pyspark as spark', from https://github.com/apache/spark/tree/master/python, Documentation: https://spark.apache.org/docs/latest/api/python/pyspark.html")
SparkContext = LazyImport("from pyspark import SparkContext", "CLASS pyspark.SparkContext — Main entry point for Spark functionality, derived from 'from pyspark import SparkContext', from https://github.com/apache/spark/tree/master/python, Documentation: https://spark.apache.org/docs/latest/api/python/pyspark.html#pyspark.SparkContext")

xlrd = LazyImport("import xlrd", "PACKAGE xlrd — retrieving information from a spreadsheet, derived from 'import xlrd', from https://github.com/python-excel/xlrd, Documentation: https://xlrd.readthedocs.io/en/latest/?badge=latest")
open_workbook = LazyImport("from xlrd import open_workbook", "FUNCTION xlrd.open_workbook — open a spreadsheet file for data extraction, derived from 'from xlrd import open_workbook', from https://github.com/python-excel/xlrd, Documentation: https://xlrd.readthedocs.io/en/latest/api.html")
xlwt = LazyImport("import xlwt", "PACKAGE xlwt — library for writing data and formatting information to older Excel files, derived from 'import xlwt', from https://github.com/python-excel/xlwt, Documentation: https://xlwt.readthedocs.io/en/latest/")
easyxf = LazyImport("from xlwt import easyxf", "FUNCTION xlwt.easyxf — a function to create and configure XFStyle objects, derived from 'from xlwt import easyxf', from https://github.com/python-excel/xlwt, Documentation: https://xlwt.readthedocs.io/en/latest/api.html")
openpyxl = LazyImport("import openpyxl", "PACKAGE openpyxl — library to read/write Excel 2010 xlsx,xlsm files, derived from 'import openpyxl', from https://bitbucket.org/openpyxl/openpyxl/src/default/, Documentation: https://openpyxl.readthedocs.io/en/stable/")
Workbook = LazyImport("from openpyxl import Workbook", "CLASS openpyxl.Workbook — a class that represents a workbook, derived from 'from openpyxl import Workbook', from https://bitbucket.org/openpyxl/openpyxl/src/default/, Documentation: https://openpyxl.readthedocs.io/en/stable/api/openpyxl.workbook.workbook.html#openpyxl.workbook.workbook.Workbook")
Worksheet = LazyImport("from openpyxl.worksheet.worksheet import Worksheet", "CLASS openpyxl.worksheet.worksheet.Worksheet — a class that represents a sheet in a workbook, derived from 'from openpyxl.worksheet.worksheet import Worksheet', from https://bitbucket.org/openpyxl/openpyxl/src/default/, Documentation: https://openpyxl.readthedocs.io/en/stable/api/openpyxl.worksheet.worksheet.html#openpyxl.worksheet.worksheet.Worksheet")
#load_workbook = LazyImport("from openpyxl import load_workbook")

sqlite3 = LazyImport("import sqlite3", "PACKAGE sqlite3 — An Embeddable SQL Database Engine, derived from 'import sqlite3', from http://www.sqlite.org/, Documentation: https://www.sqlite.org/docs.html")
mysql = LazyImport("import sqlite3 as mysql", "PACKAGE mysql derived from sqlite3 — An Embeddable SQL Database Engine, derived from 'import sqlite3 as mysql', from http://www.sqlite.org/, Documentation: https://www.sqlite.org/docs.html")
sql = LazyImport("import sqlite3 as sql", "PACKAGE sql derived from sqlite3 — An Embeddable SQL Database Engine, derived from 'import sqlite3 as sql', from http://www.sqlite.org/, Documentation: https://www.sqlite.org/docs.html")


### Data Structures
dstructs = LazyImport("import dstructs", "MODULE dstructs — A library for data structures, derived from 'import dstructs', self-created")
queues = LazyImport("from dstructs import queues", "SUBMODULE queues — A library for queues, derived from 'from dstructs import queues', self-created")
queue = LazyImport("import queue", "LIBRARY queue — A synchronised queue class, derived from 'import queue', from The Python Standard Library")


### Data Visualization and Plotting
matplotlib = LazyImport("import matplotlib", "PACKAGE matplotlib — A comprehensive library for creating static, animated, and interactive visualizations in Python, derived from 'import matplotlib', from https://github.com/matplotlib/matplotlib, Documentation: https://matplotlib.org/contents.html")
mpl = LazyImport("import matplotlib as mpl", "PACKAGE mpl derived from matplotlib — A comprehensive library for creating static, animated, and interactive visualizations in Python, derived from 'import matplotlib as mpl', from https://github.com/matplotlib/matplotlib, Documentation: https://matplotlib.org/contents.html")
plt = LazyImport("import matplotlib.pyplot as plt", "SUBPACKAGE plt derived from matplotlib.pyplot — A comprehensive library for creating static, animated, and interactive visualizations in Python, derived from 'import matplotlib.pyplot as plt', from https://github.com/matplotlib/matplotlib, Documentation: https://matplotlib.org/api/index.html#the-pyplot-api")
pyplot = LazyImport("from matplotlib import pyplot", "SUBPACKAGE matplotlib.pyplot — A comprehensive library for creating static, animated, and interactive visualizations in Python, derived from 'from matplotlib import pyplot', from https://github.com/matplotlib/matplotlib, Documentation: https://matplotlib.org/api/index.html#the-pyplot-api")

matkinter = LazyImport("import matkinter", "MODULE matkinter — A library for tkinter data visualizations, derived from 'import matkinter', self-created")

seaborn = LazyImport("import seaborn", "PACKAGE seaborn — A Python visualization library based on matplotlib, derived from 'import seaborn', from https://github.com/mwaskom/seaborn, Documentation: https://seaborn.pydata.org/")
sns = LazyImport("import seaborn as sns", "PACKAGE sns derived from seaborn — A Python visualization library based on matplotlib, derived from 'import seaborn as sns', from https://github.com/mwaskom/seaborn, Documentation: https://seaborn.pydata.org/")
eli5 = LazyImport("import eli5", "PACKAGE eli5 — A library for debugging/inspecting machine learning classifiers and explaining their predictions, derived from 'import eli5', from https://github.com/TeamHG-Memex/eli5/, Documentation: http://eli5.readthedocs.io/")

plotly = LazyImport("import plotly", "PACKAGE plotly — The interactive open-source browser-based graphing library for Python (includes Plotly Express), derived from 'import plotly', from https://github.com/plotly/plotly.py, Documentation: https://plotly.com/python/")
py = LazyImport("import plotly as py", "PACKAGE py derived from plotly — The interactive open-source browser-based graphing library for Python (includes Plotly Express), derived from 'import plotly as py', from https://github.com/plotly/plotly.py, Documentation: https://plotly.com/python/")
go = LazyImport("import plotly.graph_objs as go", "SUBPACKAGE go derived from plotly.graph_objs — Graph Objects, derived from 'import plotly.graph_objs as go', from https://github.com/plotly/plotly.py, Documentation: https://plotly.com/python/")
px = LazyImport("import plotly.express as px", "SUBPACKAGE px derived from plotly.express — Plotly Express, derived from 'import plotly.express as px', from https://github.com/plotly/plotly.py, Documentation: https://plotly.com/python/")
pio = LazyImport("import plotly.io as pio", "SUBPACKAGE pio derived from plotly.io — File IO for Plotly, derived from 'import plotly.io as pio', from https://github.com/plotly/plotly.py, Documentation: https://plotly.com/python/")
py1 = LazyImport("import plotly.plotly as py1", "SUBPACKAGE py1 derived from plotly.plotly — Plotly inbuilt subpackage, derived from 'import plotly.plotly as py1', from https://github.com/plotly/plotly.py, Documentation: https://plotly.com/python/")
plottools = LazyImport("from plotly import tools as plottools", "SUBPACKAGE plottools derived from plotly.tools — Tools for plotly, derived from 'import plotly.tools as plottools', from https://github.com/plotly/plotly.py, Documentation: https://plotly.com/python/")

dash = LazyImport("import dash", "PACKAGE dash — A Python framework for building analytical web applications, derived from 'import dash', from https://github.com/plotly/dash, Documentation: http://dash-docs.herokuapp.com/")
dhc = LazyImport("import dash_html_components as dhc", "SUBPACKAGE dhc derived from dash.dash_html_components — A Python framework for composing a HTML layout using Python structures, derived from 'import dash.dash_html_components as dhc', from https://github.com/plotly/dash, Documentation: http://dash-docs.herokuapp.com/dash-html-components")
dcc = LazyImport("import dash_core_components as dcc", "SUBPACKAGE dcc derived from dash.dash_core_components — A core set of components, derived from 'import dash.dash_core_components as dcc', from https://github.com/plotly/dash, Documentation: http://dash-docs.herokuapp.com/dash-core-components")
dbc = LazyImport("import dash_bootstrap_components as dbc", "SUBPACKAGE dbc derived from dash.dash_bootstrap_components_components — A Python framework for composing a HTML layout using Python structures, derived from 'import dash.dash_bootstrap_components as dbc', from https://github.com/plotly/dash, Documentation: https://dash-bootstrap-components.opensource.faculty.ai/")

bokeh = LazyImport("import bokeh", "PACKAGE bokeh — Interactive Data Visualization in the browser, from Python, derived from 'import bokeh', from https://github.com/bokeh/bokeh, Documentation: https://docs.bokeh.org/en/latest/")

altair = LazyImport("import altair", "PACKAGE altair — Declarative statistical visualization library for Python, derived from 'import altair', from https://github.com/altair-viz/altair, Documentation: https://altair-viz.github.io/")
alt = LazyImport("import altair as alt", "PACKAGE alt derived from altair — Declarative statistical visualization library for Python, derived from 'import altair as alt', from https://github.com/altair-viz/altair, Documentation: https://altair-viz.github.io/")

pydot = LazyImport("import pydot", "PACKAGE pydot — Python interface to Graphviz's Dot language, derived from 'import pydot', from https://github.com/pydot/pydot")
pydot_ng = LazyImport("import pydot_ng", "PACKAGE pydot_ng — Python interface to Graphviz's Dot language, derived from 'import pydot_ng', from https://github.com/pydot/pydot-ng")


### Machine Learning
sklearn = LazyImport("import sklearn", "PACKAGE sklearn — Machine learning in Python, derived from 'import sklearn', from https://github.com/scikit-learn/scikit-learn, Documentation: https://scikit-learn.org/stable/modules/classes.html")
OneHotEncoder = LazyImport("from sklearn.preprocessing import OneHotEncoder", "CLASS sklearn.preprocessing.OneHotEncoder — Encode categorical features as a one-hot numeric array, derived from 'from sklearn.preprocessing import OneHotEncoder', from https://github.com/scikit-learn/scikit-learn, Documentation: https://scikit-learn.org/stable/modules/classes.html")
# , "CLASS sklearn. — , derived from 'from sklearn. import ', from https://github.com/scikit-learn/scikit-learn, Documentation: https://scikit-learn.org/stable/modules/classes.html")
train_test_split = LazyImport("from sklearn.model_selection import train_test_split" , "FUNCTION sklearn.model_selection.train_test_split — Split arrays or matrices into random train and test subsets, derived from 'from sklearn.model_selection import train_test_split', from https://github.com/scikit-learn/scikit-learn, Documentation: https://scikit-learn.org/stable/modules/classes.html")
cross_val_score = LazyImport("from sklearn.model_selection import cross_val_score" , "FUNCTION sklearn.model_selection.cross_val_score — Evaluate a score by cross validation, derived from 'from sklearn.model_selection import cross_val_score', from https://github.com/scikit-learn/scikit-learn, Documentation: https://scikit-learn.org/stable/modules/classes.html")
StratifiedKFold = LazyImport("from sklearn.model_selection import StratifiedKFold" , "CLASS sklearn.model_selection.StratifiedKFold — Stratified K-Folds cross-validator, derived from 'from sklearn.model_selection import StratifiedKFold', from https://github.com/scikit-learn/scikit-learn, Documentation: https://scikit-learn.org/stable/modules/classes.html")
LogisticRegression = LazyImport("from sklearn.linear_model import LogisticRegression")
DecisionTreeClassifier = LazyImport("from sklearn.tree import DecisionTreeClassifier")
KNeighborsClassifier = LazyImport("from sklearn.neighbours import KNeighborsClassifier")
LinearDiscriminantAnalysis = LazyImport("from sklearn.discriminant_analysis import LinearDiscriminantAnalysis")
GaussianNB = LazyImport("from sklearn.naive_bayes import GaussianNB")
SVC = LazyImport("from sklearn.svm import SVC")
classification_report = LazyImport("from sklearn.metrics import classification_report")
confusion_matrix = LazyImport("from sklearn.metrics import confusion_matrix")
accuracy_score = LazyImport("from sklearn.metrics import accuracy_score")
make_scorer = LazyImport("from sklearn.metrics import make_scorer")
TSNE = LazyImport("from sklearn.manifold import TSNE")
svm = LazyImport("from sklearn import svm")
SVC = LazyImport("from sklearn.svm import SVC")
make_pipeline = LazyImport("from sklearn.pipeline import make_pipeline")
GradientBoostingClassifier = LazyImport("from sklearn.ensemble import GradientBoostingClassifier")
GradientBoostingRegressor = LazyImport("from sklearn.ensemble import GradientBoostingRegressor")
RandomForestClassifier = LazyImport("from sklearn.ensemble import RandomForestClassifier")
RandomForestRegressor = LazyImport("from sklearn.ensemble import RandomForestRegressor")
TfidfVectorizer = LazyImport("from sklearn.feature_extraction.text import TfidfVectorizer")
datasets = LazyImport("from sklearn import datasets")
load_iris = LazyImport("from sklearn.datasets import load_iris")
Iris = LazyImport("from sklearn.datasets import load_iris as Iris")
load_diabetes = LazyImport("from sklearn.datasets import load_diabetes")
Diabetes = LazyImport("from sklearn.datasets import load_diabetes as Diabetes")
load_digits = LazyImport("from sklearn.datasets import load_digits")
Digits = LazyImport("from sklearn.datasets import load_digits as Digits")
load_boston = LazyImport("from sklearn.datasets import load_boston")
Boston = LazyImport("from sklearn.datasets import load_boston as Boston")
load_breast_cancer = LazyImport("from sklearn.datasets import load_breast_cancer")
BreastCancer = LazyImport("from sklearn.datasets import load_breast_cancer as BreastCancer")




make_biclusters = LazyImport("from sklearn.datasets import make_biclusters")
make_blobs = LazyImport("from sklearn.datasets import make_blobs")
make_checkerboard = LazyImport("from sklearn.datasets import make_checkerboard")
make_circles = LazyImport("from sklearn.datasets import make_circles")
make_classification = LazyImport("from sklearn.datasets import make_classification")
make_regression = LazyImport("from sklearn.datasets import make_regression")

#diabetes = load_diabetes()
#digits = load_digits()
#iris = load_iris()
#iris_df = pd.DataFrame(iris.data, columns = iris.feature_names)
#diabetes_df = pd.DataFrame(diabetes.data, columns = diabetes.feature_names)


creme = LazyImport("import creme")
Pipeline = LazyImport("from creme.compose import Pipeline")
preprocessing = LazyImport("from creme import preprocessing")
StandardScaler = LazyImport("from creme.preprocessing import StandardScaler")
KMeans = LazyImport("from creme.cluster import KMeans")
BernoulliNB = LazyImport("from creme.naive_bayes import BernoulliNB")
ComplementNB = LazyImport("from creme.naive_bayes import ComplementNB")
MultinomialNB = LazyImport("from creme.naive_bayes import MultinomialNB")
Gaussian = LazyImport("from creme.proba import Gaussian")
Multinomial = LazyImport("from creme.proba import Multinomial")
Accuracy = LazyImport("from creme.metrics import Accuracy")
F1 = LazyImport("from creme.metrics import F1")
Precision = LazyImport("from creme.metrics import Precision")
MAE = LazyImport("from creme.metrics import MAE")
Recall = LazyImport("from creme.metrics import Recall")
OneVsRestClassifier = LazyImport("from creme.multiclass import OneVsRestClassifier")
OneVsAllClassifier = LazyImport("from creme.multiclass import OneVsRestClassifier as OneVsAllClassifier")
LinearRegression = LazyImport("from creme.linear_model import LinearRegression")
progressive_val_score = LazyImport("from creme.model_selection import progressive_val_score")
KNeighborsRegressor = LazyImport("from creme.neighbors import KNeighborsRegressor")
SGD = LazyImport("from creme.optim import SGD")
Stochastic = LazyImport("from creme.optim import SGD as Stochastic")
SoftmaxRegression = LazyImport("from creme.linear_model import SoftmaxRegression")

Airline = LazyImport("from creme.datasets import Airline")
Phishing = LazyImport("from creme.datasets import Phishing")
ChickWeights = LazyImport("from creme.datasets import ChickWeights")
Elec2 = LazyImport("from creme.datasets import Elec2")
MovieLens100K = LazyImport("from creme.datasets import MovieLens100K")
HTTP = LazyImport("from crem.datasets import HTTP")
Higgs = LazyImport("from creme.datasets import Higgs")
Bikes = LazyImport("from creme.datasets import Bikes")
MaliciousURL = LazyImport("from creme.datasets import MaliciousURL")
ImageSegments = LazyImport("from creme.datasets import ImageSegments")
SMSSpam = LazyImport("from creme.datasets import SMSSpam")
SMTP = LazyImport("from creme.datasets import SMTP")
Taxis = LazyImport("from creme.datasets import Taxis")
TREC07 = LazyImport("from creme.datasets import TREC07")
TrumpApproval = LazyImport("from creme.datasets import TrumpApproval")


torch = LazyImport("import torch")
tc = LazyImport("import tensor_comprehensions as tc")

# Gradient Boosting Decision Tree
xgb = LazyImport("import xgboost as xgb")
lgb = LazyImport("import lightgbm as lgb")

# Deep Learning
tf = LazyImport("import tensorflow as tf")
tensorflow = LazyImport("import tensorflow")
keras = LazyImport("import keras")
keras2 = LazyImport("import tensorflow.keras as keras2")

# ------ Sentiment Analysis --------
tweepy = LazyImport("import tweepy")
TextBlob = LazyImport("from textblob import TextBlob")


# NLP
nltk = LazyImport("import nltk")
gensim = LazyImport("import gensim")
spacy = LazyImport("import spacy")



### Own Modules
pytools = LazyImport("import pytools")

#######################################
### Complementary, optional imports ###
#######################################
# Why is this needed? Some libraries patch existing libraries
# Please note: these imports are only executed if you already have the library installed
# If you want to deactivate specific complementary imports, do the following:
# - uncomment the lines which contain `.__on_import__` and the library you want to deactivate

pandas_profiling = LazyImport("import pandas_profiling")
pd.__on_import__(pandas_profiling)  # adds df.profile_report attribute to pd.DataFrame

eda = LazyImport("import edaviz as eda")
pd.__on_import__(eda)  # adds GUI to pd.DataFrame when IPython frontend can display it


##################################################
### dont make adjustments below this line ########
##################################################
def lazy_imports():
    return _import_statements(globals(), was_imported=False)


def active_imports():
    return _import_statements(globals(), was_imported=True)

