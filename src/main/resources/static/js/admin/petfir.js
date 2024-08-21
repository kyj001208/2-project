google.charts.load("current", { packages: ["corechart"] });
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
	fetch('/api/salesData?startDate=2024-08-07&endDate=2024-08-13')
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.json();
		})
		.then(data => {
			console.log(data); // 데이터를 콘솔에서 확인

			// 날짜와 판매량을 담은 2차원 배열 생성
			var chartData = [['Date', 'Sales']];
			for (const [dateStr, sales] of Object.entries(data)) {
				var dateObj = new Date(dateStr);
				chartData.push([dateObj, sales]);
			}

			var dataTable = google.visualization.arrayToDataTable(chartData);

			var options = {
				title: 'Daily Sales',
				hAxis: { title: 'Date', format: 'yyyy-MM-dd' },
				vAxis: { title: 'Sales' },
				legend: { position: 'none' }
			};

			var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
			chart.draw(dataTable, options);
		})
		.catch(error => console.error('Fetch error:', error));
}