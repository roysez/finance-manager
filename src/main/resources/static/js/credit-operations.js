

$( document ).ready(function() {
  //  $('#error-alert').hide();
    console.log( "ready!" );
});

function doCharge(id) {

    if($('#btn-delete-'+id).hasClass("disabled")==true) {
        $.ajax({
            type: "POST",
            url: '/credits/' + id + '/charge',

            "statusCode": {
                400: function () {
                    $('#error-alert').show();
                    $('#error-alert-text').text('You do not have enough money to pay for a portion of the loan');
                },
                404: function () {
                    console.log('404');
                },
                200: function () {

                    console.log("charged");
                    location.reload();

                }
            }
        });
    }

}

function deleteCredit(id) {

    if($('#btn-delete-'+id).hasClass("disabled")==false) {

        $.ajax({
            type: 'DELETE',
            url: '/credits/' + id,

            "statusCode": {
                200: function () {
                    console.log('deleted');
                    $('#row-' + id).remove();

                }
            }
        });
    }
}