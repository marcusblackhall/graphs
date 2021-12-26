var myChart;
$(document).ready(function(){
console.log("fetching data for lcps data");

$('#startDatePicker').datepicker(

 { 'dateFormat' : 'yy-mm-dd',
  changeMonth: true,
    }
 );

 $('#endDatePicker').datepicker(
  { 'dateFormat' : 'yy-mm-dd',
    'defaultDate' : 'today' }
  );
console.log("date is " + $("#startdatePicker").val());

$( "#startDatePicker" ).datepicker( "setDate",  -60 );
$( "#endDatePicker" ).datepicker( "setDate",  'today' );

  drawChart();

    $("#startDatePicker").on("change",function(){
      drawChart(true);
      });

      $("#endDatePicker").on("change",function(){
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

function renderConfig(chartlabels,icData,clinicData){
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
                backgroundColor: 'rgba(255,102,102,1.0)',
                fill: true,
                tension: 0.1
            },
            {
                            label: 'Clinic Covid',
                            data: clinicData,
                            borderColor: "green",
                            backgroundColor: "lightGreen",
                            fill: true,
                            tension: 0.1
                        }

            ]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
//            scales: {
//                xAxis: [{
//                type: "string",
//                ticks: {
//                         major: true,
//                         display: true,
//                         value: 90,
//                         autoSkip: true,
//                         maxTicksLimit: 10
//
//                    }
//                }]
//            },
            plugins : {
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

function drawChart(updateChart){
$.ajax( {
        "url": '/api/v1/lcps/betweenDates',
        type: 'GET',
        dataType: "json",
        data: {
            'startDate' : $("#startDatePicker").val()  ,
            'endDate' : $("#endDatePicker").val()
        },
        "contentType": "application/json",
        }
    ).fail(function( jqXHR, textStatus){
        console.log("Ajax error call" + textStatus);
    })
    .done( function(data){

         var icData = [];
         var clinicData = [];
         var chartlabels = [];
         var allData = data;
         for (var i in allData){
          icData.push(allData[i].icBedsNl);
          clinicData.push(allData[i].kliniekBedsNl);
          chartlabels.push( allData[i].datum);
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
            var config = renderConfig(chartlabels,icData,clinicData);
            myChart = new Chart(ctx,config);
         }
    });

}
