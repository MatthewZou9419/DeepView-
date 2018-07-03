from datetime import datetime
from jqdatasdk import *
from sys import *

auth('18800209419','Zh123456')
# code1 = '000001.XSHE'
# code2 = '000002.XSHE'
# start = '2017-1-1'
# end   = '2018-1-1'
code1=argv[1]
code2=argv[2]
start=argv[3]
end=argv[4]
name1 = get_security_info(code1).display_name
name2 = get_security_info(code2).display_name
diff_df = get_price([code1, code2], start, end, fields='close').close
diff_df.insert(0, 'date', [v.strftime('%Y-%m-%d') for v in diff_df.index])
diff_df['sub'] = diff_df[code1] - diff_df[code2]
print(name1)
print(name2)
print(diff_df.values.tolist())