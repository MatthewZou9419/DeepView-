from datetime import datetime
import pandas as pd
import numpy as np
from sys import *
from jqdatasdk import *

auth('18800209419','Zh123456')
# 判断日期是星期几
def get_weekday(date_list):
    weekday_list = []
    for d in date_list:
        weekday_list.append(d.weekday() + 1)
    return weekday_list

# 计算每月收益
def get_month_ratio(price_df):
    month_ratio_df = pd.DataFrame()
    month_ratio_df['year_month'] = np.unique(price_df.year_month)
    month_ratio_df['month'] = [v[len(v)-2:] for v in month_ratio_df.year_month]
    month_ratio_df['ratio'] = [round(v.close[-1] / v.pre_close[0] - 1, 4) \
                               for month, v in price_df.groupby('year_month')]
    month_ratio_list = []
    month_stat = []
    for month in np.unique(month_ratio_df.month):
        tmp = month_ratio_df[month_ratio_df.month==month]
        month_ratio_list.append(tmp[['year_month', 'ratio']].values.tolist())
        month_stat.append([sum(tmp.ratio > 0), sum(tmp.ratio <= 0), round(tmp.ratio.mean(), 4)])
    # 返回历年每个月的收益，1-12月平均收益，涨跌统计
    return month_ratio_list, month_stat

# 计算每周收益
def get_week_ratio(price_df):
    weekday_stat = []
    for weekday in range(1, 5+1):
        tmp = price_df[price_df.weekday==weekday]
        weekday_stat.append([sum(tmp.ratio > 0), sum(tmp.ratio <= 0), round(tmp.ratio.mean(), 4)])
    return weekday_stat

# code = '601238.XSHG'
code = argv[1]
name = get_security_info(code).display_name
start = get_security_info(code).start_date
end   = datetime.today()
price_df = get_price(code, start, end, fields=['close', 'pre_close'])
price_df['ratio'] = (price_df.close / price_df.pre_close - 1).round(4)
price_df.insert(0, 'year_month', [v.strftime('%Y-%m') for v in price_df.index])
price_df.insert(1, 'weekday', get_weekday(price_df.index))
#print(price_df)
month_ratio_list, month_stat = get_month_ratio(price_df)
weekday_stat = get_week_ratio(price_df)
print(name)
print(month_ratio_list)
print(month_stat)
print(weekday_stat)