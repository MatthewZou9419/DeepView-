from datetime import datetime
from jqdatasdk import *
from sys import *
import statsmodels.api as sm

auth('18800209419','Zh123456')
# code1 = '000001.XSHE'
# code2 = '000002.XSHE'
# start = '2017-1-1'
# end   = '2018-1-1'
code1 = argv[1]
code2 = argv[2]
start = argv[3]
end   = argv[4]
name1 = get_security_info(code1).display_name
name2 = get_security_info(code2).display_name
regression_panel = get_price([code1, code2], start, end, fields=['close', 'pre_close'])
close_df = regression_panel.close
pre_close_df = regression_panel.pre_close
ratio_df = close_df / pre_close_df - 1
ratio_df.insert(0, 'date', [v.strftime('%Y-%m-%d') for v in ratio_df.index])
xdat = sm.add_constant(ratio_df[code1])
ydat = ratio_df[code2]
result = sm.OLS(ydat, xdat).fit()
alpha = round(result.params[0], 3)
beta = round(result.params[1], 3)
count = int(result.nobs)
r2 = round(result.rsquared, 3)
print(name1)
print(name2)
print([count, alpha, beta, r2])
print(ratio_df.values.tolist())