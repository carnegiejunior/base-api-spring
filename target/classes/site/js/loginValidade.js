let m = function () {
    let token = localStorage.getItem("token");
    if (token == null || token.trim() == "") {
        window.location.replace('/login.html');
        localStorage.setItem("token", null);
    }
};
m();