import pandas as pd
from tabulate import tabulate
import numpy as np
from GraphTrend import Trend

class Table:  
    def __init__ (self):
        self.topics = None
        self.slope = None
        self.start_year = None
        self.end_year = None
        self.table = None
    def fit(self, data, topics, index, start_year, end_year):
        self.start_year = start_year
        self.end_year = end_year
        mark = []
        for y in range(start_year, end_year+1):
            year_count = []
            word_counts = data.loc[lambda x: x.date.dt.year == y].content.apply(Trend.remove_punctuation).str.lower().str.split().explode().value_counts()
            total = 0
            for word in topics[index]:
                frequency = word_counts.get(word, 0)
                year_count.append(frequency)   
                total += frequency        
            mark.append([i/ total *100 for i in year_count])
        mark =  np.array(mark)
        self.topics = np.array([topics[index]]).T
        slope = np.array([mark[i+1]- mark[i] for i in range(len(mark)-1)]).T
        self.slope = np.round(slope, decimals= 2)
    def slope_table(self):
        merged_array = np.concatenate((self.topics, self.slope), axis = 1)
        features = ["key_words"]+["{}-{}".format(i, i + 1) for i in range(self.start_year, self.end_year)]
        table = pd.DataFrame(merged_array, columns= features)
        numeric_columns = table.columns.difference(['key_words'])  
        table[numeric_columns] = table[numeric_columns].apply(pd.to_numeric,errors='coerce')
        table['total'] = table.iloc[:,1:].sum(axis = 1)
        self.table = table
        return self.table
    def printTable(self):
        if self.table is None:
            self.slope_table()
        print(tabulate(self.table, headers='keys', tablefmt='psql'))
    def get_hottopic(self,n = 2):
        if self.table is None:
            self.slope_table()
        return self.table.nlargest(n,'total')["key_words"]