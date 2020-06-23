$(document).ready(function () {


    $("#formLogin").submit(function (event) {

        event.preventDefault();

        loginFields = {
            username: $('#username').val(),
            password: $('#password').val()
        };
        //console.log(loginFields);
        //console.log($('username').val());

        $.ajax({
            accepts: {
                json: 'application/json'
            },
            statusCode: {
                404: function () {
                    alert("page not found");
                }
            },
            type: "POST",
            contentType: 'application/json',
            // beforeSend: function (xhr) {
            //     xhr.setRequestHeader(header, token)
            // },
            url: 'http://api.dev.local:8080/users/login',
            data: JSON.stringify(loginFields),
            dataType: 'json',
            timeout: 100000,
            //            async: true,
            //            crossDomain: true,
            success: function (data, textStatus, jQxhr) {
                console.log(data.token);
            },
            error: function (jqXhr, textStatus, errorThrown) {
                $("#loginCard").addClass("animate__animated animate__shakeX");
                $("#loginCard").removeClass("animate__animated animate__shakeX");

                //console.log(errorThrown);
                //console.log(jqXhr.responseJSON);
            }
        }).fail(function (a, b) {
            $.toast({
                text: "Verifique sua conexão de rede", // Text that is to be shown in the toast
                heading: 'ERRO INESPERADO', // Optional heading to be shown on the toast
                icon: 'error', // Type of toast icon
                showHideTransition: 'fade', // fade, slide or plain
                allowToastClose: true, // Boolean value true or false
                hideAfter: 3000, // false to make it sticky or number representing the miliseconds as time after which toast needs to be hidden
                stack: 5, // false if there should be only one toast at a time or a number representing the maximum number of toasts to be shown at a time
                position: 'mid-center', // bottom-left or bottom-right or bottom-center or top-left or top-right or top-center or mid-center or an object representing the left, right, top, bottom values
                textAlign: 'center', // Text alignment i.e. left, right or center
                loader: true, // Whether to show loader or not. True by default
            });

        });


    });

});