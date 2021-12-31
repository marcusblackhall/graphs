$(document).ready(function(){

  $('#lcpsTableId').dataTable( {
   "sDom" : "lrtip",
   "order" : [["0","desc"]],
    "columns": [
              { "data": "datum" },
              { "data": "icBedsNl" },
              { "data": "icBedsInt" },
              { "data": "kliniekBedsNl" },
              { "data": "icBedsNieuwNl" },
              { "data": "kliniekNieuwNl" }
       ],

        "serverSide" : true,

        "ajax" : {
         "processing": true,
        "url" : "/api/v1/lcps/datatables",
         "type": 'POST',
         "dataType": "json",
         "contentType": "application/json",
         "data": function(d) {
                  return JSON.stringify(d);
                 }

        },

    } );

});


