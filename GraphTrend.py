import string 
import matplotlib.pyplot as plt
import tabulate 
import numpy as np
import pandas as pd
class Trend:
    def __init__(self):
        self.topics = None
        self.freq_topic = None
        self.x = None

    def remove_punctuation(text):
        translator = str.maketrans('', '', string.punctuation)
        return text.translate(translator)
    
    def fit(self,topic, data, time_dur = "year"):
            self.topic = topic
            freq_topic = []
            if time_dur == "year":
                x = sorted(data.date.dt.year.unique())
                start_year = min(x)
                end_year =  max(x)
                for j in range(len(self.topics)):
                    trends = []
                    for i in range(start_year, end_year+1):
                        word_counts = data.loc[lambda x: x.date.dt.year == i].content.apply(self.remove_punctuation).str.lower().str.split().explode().value_counts()
                        total_frequency = word_counts.reindex(self.topics[j], fill_value=0).sum()
                        trends.append(total_frequency)
                    freq_topic.append(trends)
            elif time_dur == "month":
                x = [year *1 + month/12 for year in range(2018,2025) for month in range(1,13) ]
                for j in range(len(self.topics)):
                    trends = []
                    for i in range(2018, 2025):
                        for m in range(1,13):
                            word_counts = data.loc[lambda x: (x.date.dt.year == i) & (x.date.dt.month == m)].content.apply(self.remove_punctuation).str.lower().str.split().explode().value_counts()
                            total_frequency = word_counts.reindex(self.topics[j], fill_value=0).sum()/word_counts.sum()
                            trends.append(total_frequency)
                    freq_topic.append(trends)
            self.x =x
            self.freq_topic = freq_topic
            
    def plot_trend(self):
            if self.x is None or self.freq_topic is None:
                raise Exception("Fit model before!")
            legend = []
            c = ['red','green','blue','orange', 'purple']
            for i in range(len(self.freq_topic)):
                plt.plot(self.x, self.freq_topic[i], color = c[i])
                legend.append(f"topic{i+1}")
            plt.xlabel("Time")
            plt.ylabel("frequency")
            plt.legend(legend)
            plt.gca().xaxis.set_major_locator(plt.MaxNLocator(integer=True))
            plt.tight_layout()
            plt.grid(True)
            return plt
   