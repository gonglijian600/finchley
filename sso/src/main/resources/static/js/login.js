$(document).ready(function(){
    //登录
    $('#login').click(function() {
        var kaptcha_code = $.trim($('#kaptcha_code').val());//验证码
        var username = $.trim($('#username').val());
        var password = $('#password').val();
        var remember = $("input[name='remember-me']").is(":checked");
        $.ajax({
            type: "post",
            url: "../doLogin",
            dataType: "json",
            async: true,
            contentType: "application/x-www-form-urlencoded",
            data: {
                 'kaptcha_code':kaptcha_code, 'username':username,'password':password,'remember-me': remember
            },
            success: function(obj) {
                if (obj.code == 512){
                    auth(obj.data);
                }else {
                    alert(obj.message);
                }
            },
            error: function () {
                console.log("login error");
            }
        });
    })


    function auth(scopes){
        var form=$("<form>");//定义一个form表单
        $(document.body).append(form);
        form.attr("method","post");
        form.attr("action","../oauth/authorize");
        var approval=$("<input>");
        approval.attr("type","hidden");
        approval.attr("name","user_oauth_approval");
        approval.attr("value","true");
        for(var i = 0;i<scopes.length;i++){
            var input = $("<input>");
            input.attr("type","hidden");
            input.attr("name",""+scopes[i]+"")
            input.attr("value","true");
            form.append(input);
        }
        form.append(approval);
        form.submit();//表单提交
    }

})
