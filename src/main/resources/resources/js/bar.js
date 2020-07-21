var LineChart = {

	init : function() {
		var iport = $("#totalImport").val();
		var iport = iport.substring(1, iport.length - 1);
		var iport = iport.split(", ")
		var labelsIP = [];
		var labelIP = [];
		var labelEp =[];
		var emport = $("#totalEmport").val();
		var emport = emport.substring(1, emport.length - 1);
		var emport = emport.split(", ")
		
		for (let i = 0; i < emport.length; i++) {
			var item = emport[i].split("=");
			labelEp.push(parseInt(item[1]))
		}
		for (let i = 0; i < iport.length; i++) {
			var item = iport[i].split("=");
			labelsIP.push("Tháng" + item[0])
			labelIP.push(parseInt(item[1]))
		}
		console.log(labelsIP);
		console.log(labelIP);
		Chart.defaults.global.defaultFontColor = '#000000';
		Chart.defaults.global.defaultFontFamily = 'Arial';
		var lineChart = document.getElementById('line-chart');
		var myChart = new Chart(lineChart, {
			type : 'line', // bar, horizontalBar, pie, line, doughnut, radar,
							// polarArea
			data : {
				labels : labelsIP,
				datasets : [ {
					label : 'Nhập Kho',
					data : labelIP,
					backgroundColor : 'rgba(0, 128, 128, 0.3)',
					borderColor : 'rgba(0, 128, 128, 0.7)',
					borderWidth : 1
				}, {
					label : 'Xuất Kho',
					data : labelEp,
					backgroundColor : 'rgba(0, 128, 128, 0.7)',
					borderColor : 'rgba(0, 128, 128, 1)',
					borderWidth : 1
				} ]
			},
			options : {
				scales : {
					yAxes : [ {
						ticks : {
							beginAtZero : true
						}
					} ]
				},
			}
		});
	},
	bar : function() {
		Chart.defaults.global.defaultFontColor = '#000000';
		Chart.defaults.global.defaultFontFamily = 'Arial';
		var lineChart = document.getElementById('bar-chart');
		var myChart = new Chart(lineChart, {
			type : 'bar', // bar, horizontalBar, pie, line, doughnut, radar,
							// polarArea
			data : {
				labels : [ "Jan", "Feb", "Mar", "Apr", "May", "June" ],
				datasets : [ {
					label : 'Nhập Kho',
					data : [ 80, 30, 63, 20, 110, 3 ],
					backgroundColor : 'rgba(0, 128, 128, 0.3)',
					borderColor : 'rgba(0, 128, 128, 0.7)',
					borderWidth : 1
				}, {
					label : 'Xuất Kho',
					data : [ 18, 72, 10, 39, 19, 75 ],
					backgroundColor : 'rgba(0, 128, 128, 0.7)',
					borderColor : 'rgba(0, 128, 128, 1)',
					borderWidth : 1
				} ]
			},
			options : {
				scales : {
					yAxes : [ {
						ticks : {
							beginAtZero : true
						}
					} ]
				},
			}
		});
	}
}
