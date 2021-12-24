$(document).ready(function(){
console.log("fetching data for lcps data");
var myChart;
 $.ajax( {
        "url": '/api/v1/lcps/betweenDates',
        type: 'GET',
        dataType: "json",
        data: {
            'startDate' : "2021-01-01",
            'endDate' : "2021-12-20"
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
         myChart = renderChart(chartlabels,icData,clinicData);
    });

    $("#selPeriod").on("change",function(){
      console.log("selected " + $(this).val());
      var period = $(this).val();
       $.ajax( {
        "url": '/selectTestsByPeriod',
        type: 'POST',
        data: JSON.stringify({"period" : period}),
        dataType: 'json',
        "contentType": "application/json"
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
          icData.push(allData[i].noPositive);
          chartlabels.push( allData[i].dateReport.substring(0,10));
         }


myChart.data.labels = chartlabels;
myChart.data.datasets[0].data = chartdata;


          myChart.update();


    });

    });
});

function removeData(chart) {
    chart.data.labels.pop();
    chart.data.datasets.forEach((dataset) => {

        dataset.data.pop();
    });
   chart.update();
}

function renderChart(labels,icData,clinicData){
var ctx = document.getElementById('lcpsChartId');

const config = {
  type: 'line',
   data: {
            labels: labels,
            datasets: [{
                label: 'IC Covid',
                data: icData,
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
            scales: {
                xAxes: [{
                type: "string",
                ticks: {
                         major: true,
                         display: true,
                         value: 90,
                         autoSkip: true,
                         maxTicksLimit: 30

                    }
                }]
            },
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

return new Chart(ctx,config);

}
