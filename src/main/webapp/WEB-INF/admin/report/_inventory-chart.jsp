<%@ page  pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<html>
  <head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['LOẠI', 'GIÁ TRỊ'],
          <c:forEach var="a" items="${data}">
             ["${a[0]}",${a[2]}],
          </c:forEach>
        ]);

        var options = {
          title: 'INVENTORY BY CATEGORY',
          titleTextStyle: {
		        color: "orangered",
		        fontName: "Impact",
		        fontSize: 20,
		    },
          is3D: true,
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
        chart.draw(data, options);
      }
    </script>
  </head>
  <body>
    <div id="piechart_3d" style="width: 900px; height: 500px;"></div>
  </body>
</html>