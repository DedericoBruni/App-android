def get_text(string):
    string = string.upper()
    tickers = []
    i = 0
    word = ''
    for i in range(len(string)):
        if string[i] == ',' or string[i] == ' ':
            tickers.append(word)
            word = ''
        else:
            word = word + string[i]
            if i == len(string) - 1:
                tickers.append(word)

    return tickers

def get_w(string):
    string = string
    w = []
    i = 0
    word = ''
    for i in range(len(string)):
        if string[i] == ',' or string[i] == ' ':
            w.append(word)
            word = ''
        else:
            word = word + string[i]
            if i == len(string) - 1:
                w.append(word)
    i = 0
    for i in range(len(w)):
        w[i] = float(w[i])
    return w

def portfolio_std(tickers, weights,Year,Month,Day):
    import pandas as pd
    import numpy as np
    import pandas_datareader as pdr
    import datetime as dt
    tickers = get_text(tickers)
    weights = get_w(weights)
    Year = int(Year)
    Month = int(Month)
    Day = int(Day)
    dim = len(tickers)
    start = dt.datetime(Year, Month, Day)
    data = pd.DataFrame()

    i = 0
    for i in range(dim):
        df = pdr.get_data_yahoo(tickers[i], start)
        df['Prev'] = df['Close'].shift(1)
        df['Returns'] = df['Close'] / df['Prev'] - 1
        df = df.dropna()
        df1 = pd.DataFrame(df['Returns']).copy()
        data[tickers[i]] = df1['Returns']
    weights = np.array(weights, dtype=np.float32)
    cov = data.cov().to_numpy()
    risk = round((np.sqrt(weights.dot(cov).dot(weights)))*100,4)
    return risk

def portfolio_corr(tickers,Year,Month,Day):
    import pandas as pd
    import pandas_datareader as pdr
    import datetime as dt
    tickers = get_text(tickers)
    Year = int(Year)
    Month = int(Month)
    Day = int(Day)
    dim = len(tickers)
    data = pd.DataFrame()
    start = dt.datetime(Year, Month, Day)

    i = 0
    for i in range(dim):
        df = pdr.get_data_yahoo(tickers[i], start)
        df['Prev'] = df['Close'].shift(1)
        df['Returns'] = df['Close'] / df['Prev'] - 1
        df = df.dropna()
        df1 = pd.DataFrame(df['Returns']).copy()
        data[tickers[i]] = df1['Returns']

    corr = data.corr()
    return corr.mean().mean()



def portfolio_return(tickers,weights,Year,Month,Day):
    import pandas as pd
    import numpy as np
    import pandas_datareader as pdr
    import datetime as dt
    tickers = get_text(tickers)
    weights = get_w(weights)
    dim = len(tickers)
    Year = int(Year)
    Month = int(Month)
    Day = int(Day)
    start = dt.datetime(Year, Month, Day)
    data = pd.DataFrame()

    i = 0
    for i in range(dim):
        df = pdr.get_data_yahoo(tickers[i], start)
        df['Prev'] = df['Close'].shift(1)
        df['Returns'] = df['Close'] / df['Prev'] - 1
        df = df.dropna()
        df1 = pd.DataFrame(df['Returns']).copy()
        data[tickers[i]] = df1['Returns']
    weights = np.array(weights, dtype=np.float32)
    return round((1+(data.mean().dot(weights)))**252 -1,4)

def find_best_allocation(tickers,Year,Month,Day):
    import pandas as pd
    import numpy as np
    import pandas_datareader as pdr
    import datetime as dt
    tickers = get_text(tickers)
    Year = int(Year)
    Month = int(Month)
    Day = int(Day)
    def returns(tickers,Year,Month,Day):
        dim = len(tickers)
        start = dt.datetime(Year, Month, Day)
        data = pd.DataFrame()

        i = 0
        for i in range(dim):
            df = pdr.get_data_yahoo(tickers[i], start)
            df['Prev'] = df['Close'].shift(1)
            df['Returns'] = df['Close'] / df['Prev'] - 1
            df = df.dropna()
            df1 = pd.DataFrame(df['Returns']).copy()
            data[tickers[i]] = df1['Returns']
        return data
    def port_returns_s(data,weights):
            weights = np.array(weights, dtype=np.float32)
            exp_ret = data.mean().to_numpy()
            return exp_ret.dot(weights)
    def port_std_s(data,weights):
        cov = data.cov().to_numpy()
        risk = np.sqrt(weights.dot(cov).dot(weights))
        return risk
    sharp_list=[]
    w_list = []
    N = 7000
    i = 0
    data = returns(tickers,Year,Month,Day)
    for i in range(N):
        w = np.random.random(len(tickers))
        w = w/w.sum()
        w_list.append(w)
        ret = port_returns_s(data,w)
        std = port_std_s(data,w)
        sr = ret/std
        sharp_list.append(sr)
    max_sr = np.max(sharp_list)
    alloc = w_list[np.argmax(sharp_list)]
    return alloc