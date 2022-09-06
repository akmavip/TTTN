<%@ page pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
          var data = google.visualization.arrayToDataTable([
            ['THÁNG', 'DOANH SỐ', { role: 'style' } ],
            <c:forEach var="c" items="${rpMonth}">
             ["${c[0]}", "${c[2]}", 'opacity: 0.2'],
           </c:forEach>
          ]);

        var options = {
          chart: {
            title: 'REVENUE BY MONTH',
          }
        };

        var chart = new google.charts.Bar(document.getElementById('columnchart_material'));

               
        
        chart.draw(data, google.charts.Bar.convertOptions(options));
      }
    </script>
</head>
<body>
	<div id="columnchart_material" style="width: 800px; height: 500px;"></div>
</body>
</html>
