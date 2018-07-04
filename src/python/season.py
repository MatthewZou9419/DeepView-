import pandas as pd
from jqdatasdk import *
from datetime import datetime
from sys import *

auth('18800209419','Zh123456')
# code = '000001.XSHE'
# dur_year = 5  # 年份数
code=argv[1]
dur_year=int(argv[2])
name = get_security_info(code).display_name
today = datetime.today()
start = '%d-1-1' % (today.year - dur_year + 1)
price_df = get_price(code, start, today, fields='close')  # 股价
price_df.insert(0, 'year', [datetime.strftime(x,'%Y') for x in price_df.index])
date_list = [datetime.strftime(x,'%m-%d') for x in list(pd.date_range(start='2016-1-1', end='2016-12-31'))]  # 闰年日历
result_df = pd.DataFrame(date_list, columns=['date'])
for year, data in price_df.groupby('year')['close']:  # 按年份分组
    price_temp = data.to_frame(year)
    price_temp['date'] = [datetime.strftime(x,'%m-%d') for x in price_temp.index]
    result_df = pd.merge(result_df, price_temp, how='left', on='date')
result_df.fillna(method='ffill', inplace=True)  # 向前填充空值
print(name)
print(result_df.values.tolist())