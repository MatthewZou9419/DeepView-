from datetime import datetime
from jqdatasdk import *
from sys import *
import pandas as pd

auth('18800209419','Zh123456')
# code = '000001.XSHE'
# dur_month = 6  # 月份数
code=argv[1]
dur_month=int(argv[2])
name = get_security_info(code).display_name
windows = [5, 15, 30]  # 窗口大小
today = datetime.today()
price_df = get_price(code, end_date=today, count=dur_month*21+max(windows), fields='close')
for day in windows:
    price_df['HV%d'%day] = price_df.close.rolling(day).std()
del price_df['close']
price_df.insert(0, 'date', [v.strftime('%Y-%m-%d') for v in price_df.index])
price_df.dropna(inplace=True)
print(name)
print(windows)
print(price_df.values.tolist())