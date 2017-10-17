
function doCharge(id) {



    $.ajax({
        type: "POST",
        url: '/deposits/'+id +'/charge',

        "statusCode": {
            401: function () {

            },
            404: function () {

            },
            200: function () {
                var term = $('#col-term-'+id).text();

                term = term.split('/');
                console.log(term[0] + " " + term[1]);
                if(term[0]<term[1]-1){
                    $('#col-term-'+id).text( (++term[0]) + "/" + term[1]);
                } else if(term[0]==term[1]-1){
                    $('#col-term-'+id).text( (++term[0]) + "/" + term[1]);
                    $('#col-status-'+id).text("Completed");
                    $('#btn-chrg-'+id).addClass('disabled');
                    $('#btn-delete-'+id).removeClass('disabled');
                }

                console.log("okey");
            }
        }
    });

}