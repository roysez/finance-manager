
function doCharge(id) {



    $.ajax({
        type: "POST",
        url: '/deposits/'+id +'/charge',

        "statusCode": {
            401: function () {

            },
            404: function () {
                console.log('404');
            },
            200: function () {
                var term = $('#col-term-'+id).text();

                term = term.split('/');
                console.log(term[0] + " / " + term[1]);

                if(term[0]<term[1]-1){
                    var percenteges = $('#col-perc-'+id).text().split('%')[0];
                    var income = $('#col-income-'+id).text().split('$')[0].trim();
                    var sum = $('#col-sum-'+id).text().split('$')[0].trim();

                    income = parseInt(income) + sum*percenteges/100;
                    $('#col-income-'+id).text(income + " $");
                    console.log(percenteges + " ! " + income);
                    $('#col-term-'+id).text( (++term[0]) + "/" + term[1]);

                } else if(term[0]==term[1]-1){
                    var percenteges = $('#col-perc-'+id).text().split('%')[0];
                    var income = $('#col-income-'+id).text().split('$')[0].trim();
                    var sum = $('#col-sum-'+id).text().split('$')[0].trim();

                    income = parseInt(income) + sum*percenteges/100;
                    $('#col-income-'+id).text(income + " $");
                    $('#col-term-'+id).text( (++term[0]) + "/" + term[1]);
                    $('#col-status-'+id).text("Completed");
                    $('#btn-chrg-'+id).addClass('disabled');
                    $('#btn-delete-'+id).removeClass('disabled');
                }


            }
        }
    });

}

function deleteDeposit(id) {
    $.ajax({
        type: 'DELETE',
        url: '/deposits/'+id,

        "statusCode": {
            200: function(){
                console.log('deleted');
                $('#row-'+id).remove();
            }
        }
    });
}