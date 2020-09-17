var basic = (function () {
return{

    sendMessage: function(){
        var user = document.getElementById("user").value;
        var message = document.getElementById("message").value;
        apiclient.postMessage(user,message);
    }
    
};
})();