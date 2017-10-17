
function deleteTransaction(id) {



    $.ajax({
        type: "DELETE",
        url: '/transactions/'+id,

        "statusCode": {
            401: function () {

            },
            404: function () {

            },
            200: function () {
                $("#row-"+id).remove();
                console.log("okey");
            }
        }
    });

}