$(function() {
	var myChart = echarts.init(document.getElementById('report'));
	common.ajax({
		url : $('#basePath').val() + '/orderPieReport/count',
		success : function(data) {
			var option = {
				title : {
					text : '商户类别订单数',
					subtext : '昨日订单',
					x : 'center'
				},
				tooltip : {
					trigger : 'item',
					formatter : "{a} <br/>{b} : {c} ({d}%)"
				},
				legend : {
					orient : 'vertical',
					left : 'left',
				},
				series : [ {
					radius : '55%',
					center : [ '50%', '60%' ],
					itemStyle : {
						emphasis : {
							shadowBlur : 10,
							shadowOffsetX : 0,
							shadowColor : 'rgba(0, 0, 0, 0.5)'
						}
					}
				} ]
			};
			$.extend(true, option, data); // 深度拷贝，递归，
			myChart.setOption(option);
		},
		type : 'GET'
	});
});