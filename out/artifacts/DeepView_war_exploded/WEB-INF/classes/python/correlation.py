from datetime import datetime
from jqdatasdk import *
from sys import *

auth('18800209419','Zh123456')
# code_list = ['000001.XSHE', '000002.XSHE', '000004.XSHE', '000005.XSHE']  # 股票数量可有多个，此处列举4个
# start = '2017-1-1'
# end   = '2018-1-1'
start=argv[1]
end=argv[2]
code_list=argv[3:]
name_list = []
for code in code_list:
    name_list.append(get_security_info(code).display_name)
corr_panel = get_price(code_list, start, end, fields=['close', 'pre_close'])
ratio_df = corr_panel.close / corr_panel.pre_close - 1
corr_df = ratio_df.corr().applymap('{:.3f}'.format)
corr_df.columns = name_list; corr_df.index = name_list
print(name_list)
print(corr_df.values.tolist())