### 板块划分

* 实时股价：
    * echarts：http://echarts.baidu.com/examples/editor.html?c=candlestick-sh-2015  
    * api：http://localhost:8080/chart/realTimePrice?secuCode=000001.XSHG
    * 返回数据：{证券名，绘制数据[[日期，开盘价，收盘价，最低价，最高价]，...]}

* 股价异动：表格（以后可能会增加http://echarts.baidu.com/examples/editor.html?c=area-simple ）

* 走势预测：http://echarts.baidu.com/examples/editor.html?c=candlestick-sh-2015

* 相关性分析(n支股票相关性)：
    * echarts：http://echarts.baidu.com/examples/editor.html?c=heatmap-cartesian
    * api：http://localhost:8080/chart/correlation?secuCode=000001.XSHG&secuCode=000002.XSHE&secuCode=000004.XSHG&secuCode=000005.XSHE&startDate=2017-1-1&endDate=2018-1-1
    * 返回数据：{secuNames[证券名1，证券名2, ...]，绘制数据[n行n列]}

* 回归分析(两支股票之间)：
    * http://echarts.baidu.com/examples/editor.html?c=scatter-linear-regression
    * api：http://localhost:8080/chart/regression?secuCode1=000001.XSHG&secuCode2=000002.XSHE&startDate=2017-1-1&endDate=2018-1-1
    * 返回数据：{证券名1，证券名2，图例参数[样本数，alpha，beta，x^2]，绘制数据[[日期，股价1，股价2]，...]}

* 价差分析(两支股票比较)：
    * echarts：http://echarts.baidu.com/examples/editor.html?c=line-stack
    * api：http://localhost:8080/chart/priceCompare?secuCode1=000001.XSHE&secuCode2=000002.XSHE&startDate=2017-1-1&endDate=2018-1-1
    * 返回数据：{证券名1，证券名2，绘制数据[[日期，股价1，股价2，差值]，...]}

* 季节图表(显示一支股票同一天不同年份的股价)：
    * echarts：http://echarts.baidu.com/examples/editor.html?c=line-stack
    * api：http://localhost:8080/chart/season?secuCode=000001.XSHG&yearNum=3
    * 返回值：{证券名，绘制数据[[今年1月1号股价，去年..., ...]，[今年1月2号股价，去年..., ...]，...]}

* 历史波动率：
    * echarts：http://echarts.baidu.com/examples/editor.html?c=line-stack
    * api：http://localhost:8080/chart/history?secuCode=000001.XSHE&monthNum=6
    * 返回值：{证券名，图例[hv5, hv15, hv30]，绘制数据[[日期，hv5波动率，hv15波动率，hv30波动率]，...}

* 历史回看：http://echarts.baidu.com/examples/editor.html?c=pie-custom

* 股票估值：http://echarts.baidu.com/examples/editor.html?c=line-stack

### 环境配置
* jqdatasdk安装：https://www.joinquant.com/post/12479
