from collections import Counter
from tqdm import tqdm 
import spacy
import random 
import numpy as np

class LDA:
    def __init__(self, alpha, beta, stop_words = [], seed =42):
        self.alpha = alpha
        self.beta = beta
        np.random.seed(seed)
        random.seed(seed)
        self.stop_words = stop_words
        self.nlp = spacy.load('en_core_web_sm')
        self.vocab = None
        self.vocab_idx_str = None
        self.nkw = None
        self.nk = None

    def generate_frequencies(self, data):
        freqs = Counter()
        all_stopwords = self.nlp.Defaults.stop_words
        for stopword in self.stop_words:
            all_stopwords.add(stopword)
        for doc in data:
            tokens = self.nlp.tokenizer(doc)
            for token in tokens:
                token_text = token.text.lower()
                if token_text not in all_stopwords and token.is_alpha:
                    freqs[token_text] +=1
        return freqs
    def get_vocab(self, data, freq_threshold = 3):
        if self.vocab is not None and self.vocab_idx_str is not None:
            return
        freqs = self.generate_frequencies(data)
        vocab = {}
        vocab_idx_str ={}
        vocab_idx = 0

        for word in freqs:
            if freqs[word] >= freq_threshold:
                vocab[word] = vocab_idx
                vocab_idx_str[vocab_idx] = word
                vocab_idx += 1
        self.vocab = vocab
        self.vocab_idx_str = vocab_idx_str

    
    def tokenize_dataset(self, data, vocab):
        if self.vocab is None:
            self.get_vocab(data)
        nr_tokens = 0
        nr_docs = 0
        docs = []
        for doc in data:
            tokens = self.nlp.tokenizer(doc)
            if len(tokens)>1:
                doc = []
                for token in tokens:
                    token_text = token.text.lower()
                
                    if token_text in vocab:
                        doc.append(token_text)
                        nr_tokens += 1
                nr_docs += 1
                docs.append(doc)        
        #Numericalize
        corpus = []
        for doc in docs:
            corpus_d = []
            for token in doc:
                corpus_d.append(vocab[token])
            corpus.append(np.array(corpus_d))
        return docs, corpus
    def fit(self, data, num_topics = 5, num_iter = 100):
        # Initialize counts and Z
        if self.vocab is None:
            self.get_vocab(data)
        _, corpus = self.tokenize_dataset(data, self.vocab)
        vocab_size = len(self.vocab)
        Z =[]
        num_docs = len(corpus)
        for doc_idx, doc in enumerate(corpus):
            Zd = np.random.randint(low =0, high = num_topics, size = len(doc))
            Z.append(Zd)
        ndk = np.zeros((num_docs, num_topics))
        for d in range(num_docs):
            for k in range(num_topics):
                ndk[d, k] = np.sum(Z[d] == k)
        
        nkw = np.zeros((num_topics, vocab_size))
        for doc_idx, doc in enumerate(corpus):
            for i, word in enumerate(doc):
                topic = Z[doc_idx][i]
                nkw[topic, word] += 1
        nk = np.sum(nkw, axis =1)
        topic_list = [i for i in range(num_topics)]
        
        for _ in tqdm(range(num_iter)):
            for doc_idx, doc in enumerate(corpus):
                for i in range(len(doc)):
                    word = doc[i]
                    topic = Z[doc_idx][i]

                  
                    ndk[doc_idx, topic] -= 1
                    nkw[topic, word] -= 1
                    nk[topic] -=1
                    
                    p_z = (ndk[doc_idx, :] + self.alpha)*(nkw[:, word] +self.beta) / (nk[:] + self.beta * vocab_size)
                    topic = random.choices(topic_list, weights = p_z, k=1)[0]

                    Z[doc_idx][i] = topic
                    ndk[doc_idx, topic] += 1
                    nkw[topic, word] += 1
                    nk[topic] += 1
        self.nkw = nkw
        self.nk = nk
    def get_topic(self, num_words = 10):
        if self.nkw is None or self.nk is None:
            raise Exception ("Need to fit before!")
        phi = self.nkw/self.nk[:, np.newaxis]
        topic_words =[]
        for k in range(len(self.nkw)):
            most_common_word = np.argsort(phi[k])[::-1][:num_words]
            words = []
            for word in most_common_word:
                words.append(self.vocab_idx_str[word])
            topic_words.append(words)
        return topic_words
