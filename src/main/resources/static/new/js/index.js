$(function () {
	// 轮播图
	$('.rslides').responsiveSlides({
		auto: true,
		pager: true,
		nav: true
	})
	// 滚动公告
	marquee()
	// 图标
	var myChart1 = echarts.init($('#chart1')[0])
	var myChart2 = echarts.init($('#chart2')[0])
	var myChart3 = echarts.init($('#chart3')[0])
	var myChart4 = echarts.init($('#chart4')[0])
	myChart1.setOption(getOption(1))
	myChart2.setOption(getOption(2))
	myChart3.setOption(getOption(3))
	myChart4.setOption(getOption(4))
})
// 轮播图方法
function marquee() {
	var scrollWidth = $('.notice').width()
	var textWidth = $('.notice p').width()
	var i = scrollWidth
	setInterval(function () {
		i--
		if (i < -textWidth) {
			i = scrollWidth
		}
		$('.notice p').animate({ left: i + 'px' }, 3)
	}, 5)
}
// 图表配置
function getOption(chartId) {
	return (option = {
		color: getColors(),
		title: {
			show: false
		},
		tooltip: {
			show: false
		},
		series: [
			{
				type: 'pie',
				radius: ['40%', '75%'],
				avoidLabelOverlap: false,
				label: {
					// show: false,
					// position: 'inside',
					// formatter: function (data) {
					// 	var _str = data.name.slice(0, 5) + '\n' + data.name.slice(5) + '\n' + data.value + '\n' + data.percent + '%'
					// 	return data.name.length > 5 ? _str : data.name + '\n数量：' + data.value + '\n' + data.percent + '%'
					// }
					formatter: '{b}:\n{c}个'
				},
				data: chartsInfo[chartId].data
			}
		]
	})
}
function getColors() {
	var colors = ['rgba(255,99,132,1)', 'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)', 'rgba(75, 192, 192, 1)', 'rgba(255, 159, 64, 1)', 'rgba(153, 102, 255, 1)']
	colors.sort(function () {
		return 0.5 - Math.random()
	})
	return colors
}
