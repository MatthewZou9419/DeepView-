from datetime import datetime
from jqdatasdk import *
import sys

auth('18800209419','Zh123456')
# code  = '000001.XSHG'  # 证券代码
code  = sys.argv[1]  # 证券代码
start = get_security_info(code).start_date
end   = datetime.today()
df = get_price(code, start, end, fields=['open', 'close', 'low', 'high'])
df.insert(0, 'date', [v.strftime('%Y-%m-%d') for v in df.index])
df.reset_index(drop=True, inplace=True)
print(df.values.tolist())
