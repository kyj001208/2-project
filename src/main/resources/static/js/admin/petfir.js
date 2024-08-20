google.charts.load("current", { packages: ["corechart"] });
google.charts.setOnLoadCallback(drawChart);
function drawChart() {
	var data = google.visualization.arrayToDataTable([
		['Task', 'Hours per Day'],
		['Work', 10],
		['Eat', 1],
		['Commute', 1],
		['Watch TV', 1],
		['Sleep', 7]
	]);

	var options = {
		title: 'My Daily Activities',
		pieHole: 0.4,
	};

	var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
	chart.draw(data, options);
}