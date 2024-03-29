var myChart;
$(document).ready(function () {

$('#errorMessage').hide();

  $('#startDatePicker').datepicker(

    {
      'dateFormat': 'yy-mm-dd',
      changeMonth: true,
    }
  );

  $('#endDatePicker').datepicker(
    {
      'dateFormat': 'yy-mm-dd',
      'defaultDate': 'today'
    }
  );

  $("#startDatePicker").datepicker("setDate", -365);
  $("#endDatePicker").datepicker("setDate", 'today');

  drawChart();

  $("#startDatePicker").on("change", function () {
    drawChart(true);
  });

  $("#endDatePicker").on("change", function () {
    drawChart(true);
  });

});

function removeData(chart) {
  chart.data.labels.pop();
  chart.data.datasets.forEach((dataset) => {

    dataset.data.pop();
  });
  chart.update();
}

function renderConfig(chartlabels, icData, clinicData) {
  var ctx = document.getElementById('lcpsChartId');

  var config = {
    type: 'line',
    data: {
      labels: chartlabels,
      datasets: [{
        label: 'IC Covid',
        data: icData,
        borderWidth: 0.5,
        borderColor: "red",
        backgroundColor: 'rgba(255,204,204,1.0)',
        fill: true,
        tension: 0.1
      },
      {
        label: 'Clinic Covid',
        data: clinicData,
        borderColor: "green",
        backgroundColor: 'rgba(204,229,255,1.0)',
        fill: true,
        tension: 0.1
      }

      ]
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,

      plugins: {
        title: {
          display: true,
          font: {
            family: "verdana",
            size: 18
          },
          text: "LCPS Covid Hospital Data"
        },
      }

    }


  };

  return config;;

}

function drawChart(updateChart) {
  $.ajax({
    "url": '/api/v2/lcps/betweenDates',
    type: 'GET',
    dataType: "json",
    data: {
      'startDate': $("#startDatePicker").val(),
      'endDate': $("#endDatePicker").val()
    },
    "contentType": "application/json",
  }
  ).fail(function (jqXHR, textStatus) {
   $('#errorMessage').show();
    console.log("Ajax error call" + textStatus);
  })
    .done(function (data) {
 $('#errorMessage').hide();
      var icData = [];
      var clinicData = [];
      var chartlabels = [];
      var allData = data;
      for (var i in allData) {
        icData.push(allData[i].icBedsNl);
        clinicData.push(allData[i].kliniekBedsNl);
        chartlabels.push(allData[i].datum);
      }


      if (updateChart) {
        // myChart.config = config;
        myChart.data.datasets[0].data = icData;
        myChart.data.datasets[1].data = clinicData;
        myChart.data.labels = chartlabels;

        console.log(myChart.config.data);
        myChart.update();
      } else {
        var ctx = document.getElementById("lcpsChartId");
        var config = renderConfig(chartlabels, icData, clinicData);
        myChart = new Chart(ctx, config);
      }
    });

}
