
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

                console.log("okey");
            }
        }
    });

}