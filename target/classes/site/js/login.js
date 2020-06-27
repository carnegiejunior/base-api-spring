$(document).ready(function () {


    $("#formLogin").submit(function (event) {

        event.preventDefault();

        loginFields = {
            username: $('#username').val(),
            password: $('#password').val()
        };

        $("#loginCard").removeClass("animate__animated animate__shakeX");

        $.ajax({
                accepts: {
                    json: 'application/json'
                },

                // statusCode: {
                //     401: function () {
                //         alert("page not found");
                //     }
                // },
                type: "POST",
                contentType: 'application/json',
                // beforeSend: function (xhr) {
                //     //xhr.setRequestHeader(header, token);
                //     xhr.overrideMimeType("application/json; charset=uf8");
                // },
                url: 'http://api.dev.local:8080/users/login',
                data: JSON.stringify(loginFields),
                dataType: 'json',
                timeout: 100000,
                //            async: true,
                //            crossDomain: true,
                success: function (data, textStatus, jQxhr) {
                    localStorage.setItem("token", data.token);
                    localStorage.setItem("tokenProvider", data.bearer);
                    localStorage.setItem("expireIn", data.expireIn);
                    $(location).prop('href', '/home.html')
                },
                error: function (jqXhr, textStatus, errorThrown) {
                    $("#loginCard").addClass("animate__animated animate__shakeX");
                    localStorage.removeItem("token");
                    localStorage.removeItem("tokenProvider");
                    localStorage.removeItem("expireIn");
                }
            })
            .catch(function (data) {
                if (data.status == 401) {
                    return;
                }
                $.toast({
                    text: "Verifique sua conexão de rede",
                    heading: 'ERRO INESPERADO',
                    icon: 'error',
                    showHideTransition: 'fade',
                    allowToastClose: true,
                    hideAfter: 3000,
                    stack: false,
                    position: 'mid-center',
                    textAlign: 'center',
                    loader: false,
                });
                localStorage.clear();

            })
            .fail(function (data) {
                localStorage.clear();
            });


    });

});