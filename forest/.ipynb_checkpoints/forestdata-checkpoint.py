import pandas as pd


def opsd():
    return pd.read_csv('https://raw.githubusercontent.com/jenfly/opsd/master/opsd_germany_daily.csv', sep=",")

def presidents():
    return pd.read_csv('https://sololearn.com/uploads/files/president_heights_party.csv', index_col='name')

def sacramento_crime():
    return pd.read_csv('http://samplecsvs.s3.amazonaws.com/SacramentocrimeJanuary2006.csv')

def titanic():
    return pd.read_csv('https://raw.githubusercontent.com/datasciencedojo/datasets/master/titanic.csv')

def titanic_test():
    return pd.read_csv('https://raw.githubusercontent.com/justmarkham/pandas-videos/master/data/titanic_test.csv')

def titanic_train():
    return pd.read_csv('https://raw.githubusercontent.com/justmarkham/pandas-videos/master/data/titanic_train.csv')

def kyphosis():
    return pd.read_csv('https://raw.githubusercontent.com/raorao/datasciencedojo/master/Datasets/kyphosis.csv')

def hive_sample():
    return pd.read_csv('https://raw.githubusercontent.com/olympiacos23/datasciencedojo/master/Datasets/HiveSampleData.csv')

def oxygen():
    return pd.read_csv('https://raw.githubusercontent.com/selva86/datasets/master/oxygen.csv')

def ozone():
    return pd.read_csv('https://raw.githubusercontent.com/selva86/datasets/master/ozone.csv')

def phone_transcripts():
    return pd.read_csv('https://raw.githubusercontent.com/selva86/datasets/master/phone_transcripts.csv')

def text_classification():
    return pd.read_csv('https://raw.githubusercontent.com/selva86/datasets/master/text_classfication.csv')

def tourists():
    return pd.read_csv('https://raw.githubusercontent.com/selva86/datasets/master/tourists.csv')

def diamonds():
    return pd.read_csv('https://raw.githubusercontent.com/selva86/datasets/master/diamonds.csv')

def life_expectancy():
    return pd.read_csv('https://raw.githubusercontent.com/selva86/datasets/master/Life_Expectancy_Data.csv')

def yahoo():
    return pd.read_csv('https://raw.githubusercontent.com/selva86/datasets/master/yahoo.csv')

def earthquake():
    return pd.read_csv('https://raw.githubusercontent.com/plotly/datasets/master/earthquake.csv')

def flightdata():
    return pd.read_csv('https://raw.githubusercontent.com/plotly/datasets/master/flightdata.csv')

def vortex():
    return pd.read_csv('https://raw.githubusercontent.com/plotly/datasets/master/vortex.csv')

def USArrests():
    return pd.read_csv('https://raw.githubusercontent.com/selva86/datasets/master/USArrests.csv')

def wage():
    return pd.read_csv('https://raw.githubusercontent.com/selva86/datasets/master/Wage.csv')

def letter_recogniton():
    return pd.read_csv('https://raw.githubusercontent.com/selva86/datasets/master/LetterRecognition.csv')

def ionosphere():
    return pd.read_csv('https://raw.githubusercontent.com/selva86/datasets/master/Ionosphere.csv')

def glass():
    return pd.read_csv('https://raw.githubusercontent.com/selva86/datasets/master/Glass.csv')

def german_credit():
    return pd.read_csv('https://raw.githubusercontent.com/selva86/datasets/master/GermanCredit.csv')

def college():
    return pd.read_csv('https://raw.githubusercontent.com/selva86/datasets/master/College.csv')

def tedtalks():
    return pd.read_json('https://raw.githubusercontent.com/algolia/datasets/master/tedtalks/talks.json')

def movies():
    return pd.read_json('https://raw.githubusercontent.com/algolia/datasets/master/movies/records.json')

def german_credit():
    return pd.read_csv('https://raw.githubusercontent.com/selva86/datasets/master/GermanCredit.csv')

def pollution():
    return pd.read_csv('https://raw.githubusercontent.com/jbrownlee/Datasets/master/pollution.csv')

def sonar():
    return pd.read_csv('https://raw.githubusercontent.com/jbrownlee/Datasets/master/sonar.csv')

def adult():
    return pd.read_csv('https://raw.githubusercontent.com/jbrownlee/Datasets/master/adult-all.csv')

def smsspam():
    return pd.read_csv('https://raw.githubusercontent.com/stedy/Machine-Learning-with-R-datasets/master/sms_spam.csv')

def credit():
    return pd.read_csv('https://raw.githubusercontent.com/stedy/Machine-Learning-with-R-datasets/master/credit.csv')

def concrete():
    return pd.read_csv('https://raw.githubusercontent.com/stedy/Machine-Learning-with-R-datasets/master/concrete.csv')

def snsdata():
    return pd.read_csv('https://raw.githubusercontent.com/stedy/Machine-Learning-with-R-datasets/master/snsdata.csv')

def insurance():
    return pd.read_csv('https://raw.githubusercontent.com/stedy/Machine-Learning-with-R-datasets/master/insurance.csv')

def used_cars():
    return pd.read_csv('https://raw.githubusercontent.com/stedy/Machine-Learning-with-R-datasets/master/usedcars.csv')

def movie_actors():
    return pd.read_json('https://raw.githubusercontent.com/algolia/datasets/master/movies/actors.json')

def streeteasy():
    return pd.read_csv('https://raw.githubusercontent.com/Codecademy/datasets/master/streeteasy/streeteasy.csv')

def streeteasy_brooklyn():
    return pd.read_csv('https://raw.githubusercontent.com/Codecademy/datasets/master/streeteasy/brooklyn.csv')

def streeteasy_manhattan():
    return pd.read_csv('https://raw.githubusercontent.com/Codecademy/datasets/master/streeteasy/manhattan.csv')

def streeteasy_queens():
    return pd.read_csv('https://raw.githubusercontent.com/Codecademy/datasets/master/streeteasy/queens.csv')

def uiuc_gpa():
    return pd.read_csv('https://raw.githubusercontent.com/wadefagen/datasets/master/gpa/uiuc-gpa-dataset.csv')

def uiuc_students():
    return pd.read_csv('https://raw.githubusercontent.com/wadefagen/datasets/master/students-by-state/uiuc-students-by-state.csv')

def twonorm():
    return pd.read_csv('https://raw.githubusercontent.com/mikeizbicki/datasets/master/csv/ida/twonorm_data.csv')

def waveform():
    return pd.read_csv('https://raw.githubusercontent.com/mikeizbicki/datasets/master/csv/ida/waveform_data.csv')

def pima_indian_diabetes():
    return pd.read_csv('https://raw.githubusercontent.com/mikeizbicki/datasets/master/csv/uci/pima-indians-diabetes.csv')

def ilpd():
    return pd.read_csv('https://raw.githubusercontent.com/mikeizbicki/datasets/master/csv/uci/Indian%20Liver%20Patient%20Dataset%20(ILPD).csv')

def stocks():
    return pd.read_csv('https://raw.githubusercontent.com/skathirmani/datasets/master/stock-prices.csv')

def movie_ratings():
    return pd.read_csv('https://raw.githubusercontent.com/skathirmani/datasets/master/ratings.csv')

def restaurants():
    return pd.read_csv('https://raw.githubusercontent.com/skathirmani/datasets/master/restaurants.csv')

def modi_tweets():
    return pd.read_csv('https://raw.githubusercontent.com/skathirmani/datasets/master/narendramodi_tweets.csv')

def deliveries():
    return pd.read_csv('https://raw.githubusercontent.com/skathirmani/datasets/master/deliveries.csv')

def ellipse():
    return pd.read_csv('https://raw.githubusercontent.com/skathirmani/datasets/master/ellipse.csv')

def car_data():
    return pd.read_csv('https://raw.githubusercontent.com/skathirmani/datasets/master/car_data.csv')

def delhi_2014():
    return pd.read_csv('https://raw.githubusercontent.com/mwermelinger/Learn-to-code-for-data-analysis/master/2_Cleaning_up_our_act/Delhi_DEL_2014.csv')

def beijing_2014():
    return pd.read_csv('https://raw.githubusercontent.com/mwermelinger/Learn-to-code-for-data-analysis/master/2_Cleaning_up_our_act/Beijing_PEK_2014.csv')

def brasilia_2014():
    return pd.read_csv('https://raw.githubusercontent.com/mwermelinger/Learn-to-code-for-data-analysis/master/2_Cleaning_up_our_act/Brasilia_BSB_2014.csv')

def capetown_2014():
    return pd.read_csv('https://raw.githubusercontent.com/mwermelinger/Learn-to-code-for-data-analysis/master/2_Cleaning_up_our_act/CapeTown_CPT_2014.csv')

def london_2014():
    return pd.read_csv('https://raw.githubusercontent.com/mwermelinger/Learn-to-code-for-data-analysis/master/2_Cleaning_up_our_act/London_2014.csv')

def moscow_2014():
    return pd.read_csv('https://raw.githubusercontent.com/mwermelinger/Learn-to-code-for-data-analysis/master/2_Cleaning_up_our_act/Moscow_SVO_2014.csv')

def WHO_POP_2014():
    return pd.read_csv('https://raw.githubusercontent.com/mwermelinger/Learn-to-code-for-data-analysis/master/2_Cleaning_up_our_act/WHO%20POP%20TB%20all.csv')

def countries():
    return pd.read_csv('https://raw.githubusercontent.com/arangodb/example-datasets/master/Countries/countries.csv')

def mcdonalds_france():
    return pd.read_csv('https://raw.githubusercontent.com/arangodb/example-datasets/master/McDonalds/france.csv')

def regions():
    return pd.read_csv('https://raw.githubusercontent.com/arangodb/example-datasets/master/Regions/regions.csv')

def airports():
    return pd.read_csv('https://raw.githubusercontent.com/arangodb/example-datasets/master/Airports/airports.csv')

def bezirke():
    return pd.read_csv('https://raw.githubusercontent.com/arangodb/example-datasets/master/Bezirke/bezirke.csv')

def GeoLiteCity():
    return pd.read_csv('https://raw.githubusercontent.com/arangodb/example-datasets/master/Cities/GeoLiteCity.csv')

def coronavirus():
    return pd.read_csv('https://raw.githubusercontent.com/RamiKrispin/coronavirus/master/csv/coronavirus.csv')

def covid_owid():
    return pd.read_csv('https://raw.githubusercontent.com/owid/covid-19-data/master/public/data/owid-covid-data.csv')

def population():
    return pd.read_csv('https://raw.githubusercontent.com/rafikahmed/worldometers/master/population_dataset.csv')

def superhero():
    return pd.read_csv('https://raw.githubusercontent.com/mafudge/datasets/master/superhero/superhero-movie-dataset-1978-2012.csv', header=0)

def marvel():
    return pd.read_csv('https://raw.githubusercontent.com/fivethirtyeight/data/master/comic-characters/marvel-wikia-data.csv')

def dc():
    return pd.read_csv('https://raw.githubusercontent.com/fivethirtyeight/data/master/comic-characters/dc-wikia-data.csv')

def usbirths94_03():
    return pd.read_csv('https://raw.githubusercontent.com/fivethirtyeight/data/master/births/US_births_1994-2003_CDC_NCHS.csv')

def usbirths00_14():
    return pd.read_csv('https://raw.githubusercontent.com/fivethirtyeight/data/master/births/US_births_2000-2014_SSA.csv')

def avengers():
    return pd.read_csv('https://raw.githubusercontent.com/fivethirtyeight/data/master/avengers/avengers.csv')

def starwars():
    return pd.read_csv('https://raw.githubusercontent.com/fivethirtyeight/data/master/star-wars-survey/StarWars.csv')

def tarantino():
    return pd.read_csv('https://raw.githubusercontent.com/fivethirtyeight/data/master/tarantino/tarantino.csv')

def churn():
    return pd.read_csv('https://raw.githubusercontent.com/albayraktaroglu/Datasets/master/churn.csv')

def shanghai_sp():
    return pd.read_csv('https://raw.githubusercontent.com/zilinskyjan/datasets/master/china/shanghai_sp_correlation.csv')

def uber():
    return pd.read_csv('https://raw.githubusercontent.com/ChitturiPadma/datasets/master/uber.csv')

def output_volatility():
    return pd.read_csv('https://raw.githubusercontent.com/zilinskyjan/datasets/master/blogs/output%20volatility%20dataset.csv')

def eurobarometer():
    return pd.read_csv('https://raw.githubusercontent.com/zilinskyjan/datasets/master/economic_sentiment/eurobarometer.csv')

def growth_gdp_public_spending():
    return pd.read_csv('https://raw.githubusercontent.com/zilinskyjan/datasets/master/fiscal/growht_gdp_public_spending.csv')

def fires():
    return pd.read_csv('https://raw.githubusercontent.com/datanews/track-fires/master/track-fires.csv')

def illegal_hotel_inspections():
    return pd.read_csv('https://raw.githubusercontent.com/datanews/illegal-hotel-inspections/master/inspections.csv')

def fashion_mnist():
    return pd.read_csv('https://raw.githubusercontent.com/trekhleb/homemade-machine-learning/master/data/fashion-mnist-demo.csv')

def mnist():
    return pd.read_csv('https://raw.githubusercontent.com/trekhleb/homemade-machine-learning/master/data/mnist-demo.csv')

def whr2017():
    return pd.read_csv('https://raw.githubusercontent.com/trekhleb/homemade-machine-learning/master/data/world-happiness-report-2017.csv')

class LicensePlates:
    @staticmethod
    def accepted_plates():
        return pd.read_csv('https://raw.githubusercontent.com/datanews/license-plates/master/accepted-plates.csv')

    @staticmethod
    def rejected_plates():
        return pd.read_csv('https://raw.githubusercontent.com/datanews/license-plates/master/rejected-plates.csv')

def ufo():
    return pd.read_csv('https://raw.githubusercontent.com/justmarkham/pandas-videos/master/data/ufo.csv')

def chipotle():
    return pd.read_csv('https://raw.githubusercontent.com/justmarkham/pandas-videos/master/data/chipotle.tsv')

def imdb_1000():
    return pd.read_csv('https://raw.githubusercontent.com/justmarkham/pandas-videos/master/data/imdb_1000.csv')

class MovieLens:
    @staticmethod
    def users():
        user_cols = ['user_id', 'age', 'gender', 'occupation', 'zip_code']
        return pd.read_table('http://bit.ly/movieusers', sep='|', header=None, names=user_cols)

    @staticmethod
    def movies():
        movie_cols = ['movie_id', 'title']
        return pd.read_table("https://raw.githubusercontent.com/justmarkham/pandas-videos/master/data/u.item", sep='|', header=None, names=movie_cols, usecols=[0, 1])

    @staticmethod
    def ratings():
        rating_cols = ['user_id', 'movie_id', 'rating', 'timestamp']
        ratings = pd.read_table('https://raw.githubusercontent.com/justmarkham/pandas-videos/master/data/u.data', sep='\t', header=None, names=rating_cols)

def drinks():
    return pd.read_csv('http://bit.ly/drinksbycountry')

