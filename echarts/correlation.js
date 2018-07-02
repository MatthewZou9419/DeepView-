app.title = '相关性分析';
/////////////////后端传入
var secuNames = ['上证指数', '万科Ａ', '工业指数', '世纪星源'];
var xSecuName = secuNames;
var ySecuName = secuNames.reverse();
/////////////////后端传入
var chartData =[[1.000, 0.206, 0.905, 0.473], [0.206, 1.000, 0.123, 0.095], [0.905, 0.123, 1.000, 0.540], [0.473, 0.095, 0.540, 1.000]];
var data = [];
for (var i = 0; i < chartData.length; i++) {
    for (var j = 0; j < chartData.length; j++) {
        data.push([i,chartData.length-j-1,chartData[i][j]]);
    }
}

option = {
    tooltip: {
        position: 'top'
    },
    animation: false,
    grid: {
        height: '50%',
        y: '10%'
    },
    xAxis: {
        type: 'category',
        data: xSecuName,
        splitArea: {
            show: true
        }
    },
    yAxis: {
        type: 'category',
        data: ySecuName,
        splitArea: {
            show: true
        }
    },
    visualMap: {
        min: 0,
        max: 1,
        precision: 3,
        calculable: true,
        orient: 'horizontal',
        left: 'center',
        bottom: '15%'
    },
    series: [{
        name: 'Punch Card',
        type: 'heatmap',
        data: data,
        label: {
            normal: {
                show: true,
            }
            
        },
        itemStyle: {
            emphasis: {
                shadowBlur: 10,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
        }
    }]
};