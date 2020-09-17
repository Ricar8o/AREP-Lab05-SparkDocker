var apiclient = (function () {
return{
    
    postMessage: function (nombre, contenido) {
        jQuery.ajax({
            url: "/api/v1/setMessage",
            type: "POST",
            data: JSON.stringify({
            'user' : nombre,
            'message': contenido
            }),
        });
    }
    
};
})();