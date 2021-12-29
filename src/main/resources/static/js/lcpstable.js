$(document).ready(function(){
console.log("setting up lcps table");

  $('#lcpsTableId').dataTable( {
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
                 var x = JSON.stringify(d);
                 console.log("trying with " + x);
                        return x;
                 }

        },

    } );


});


