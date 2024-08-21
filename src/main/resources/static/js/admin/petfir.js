google.charts.load('current', { packages: ['corechart'] });
google.charts.setOnLoadCallback(initChart);

let chart;
let dataTable;
let options;

function initChart() {
	dataTable = new google.visualization.DataTable();
	dataTable.addColumn('date', 'Date');
	dataTable.addColumn('number', 'Sales');

	options = {
		title: 'Daily Sales',
		curveType: 'function',
		legend: { position: 'bottom' },
		hAxis: {
			title: 'Date',
			format: 'MM-dd',
			gridlines: { count: 7 },
			minorGridlines: { count: 0 }
		},
		vAxis: {
			title: 'Sales'
		},
		pointSize: 5,
		explorer: {
			actions: ['dragToZoom', 'rightClickToReset'],
			axis: 'horizontal',
			keepInBounds: true,
			maxZoomIn: 4.0
		}
	};

	chart = new google.visualization.LineChart(document.getElementById('chart_div'));

	updateChart();

	const fiveMinutes = 300000;
	setInterval(updateChart, fiveMinutes);
}

function updateChart() {
	let endDate = new Date();
	let startDate = new Date(endDate);
	startDate.setDate(startDate.getDate() - 7);

	let url = `/api/salesData?startDate=${formatDate(startDate)}&endDate=${formatDate(endDate)}`;

	fetch(url)
		.then(response => response.json())
		.then(data => {
			dataTable.removeRows(0, dataTable.getNumberOfRows());

			Object.entries(data).forEach(([date, sales]) => {
				let dateObj = new Date(date + 'T00:00:00');
				dataTable.addRow([dateObj, sales]);
			});

			// 날짜순으로 정렬
			dataTable.sort([{ column: 0 }]);

			chart.draw(dataTable, options);
		})
		.catch(error => console.error('Error:', error));
}

function formatDate(date) {
	return date.toISOString().split('T')[0];
}

function downloadExcel() {
    let endDate = new Date();
    let startDate = new Date(endDate);
    startDate.setDate(startDate.getDate() - 7);

    let url = `/api/downloadSalesReport?startDate=${formatDate(startDate)}&endDate=${formatDate(endDate)}`;

    fetch(url, { method: 'GET' })
        .then(response => response.blob())
        .then(blob => {
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.href = url;
            a.download = 'sales_report.xlsx'; // 다운로드할 파일 이름
            document.body.appendChild(a);
            a.click();
            document.body.removeChild(a);
            window.URL.revokeObjectURL(url);
        })
        .catch(error => console.error('Error downloading the file:', error));
}


