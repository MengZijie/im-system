/**
 * Created by ob on 17-3-16.
 */
var usernameFlag = false;
var passwordFlag = false;

$(document).ready(function () {
    $("#username-input").blur(function () {
        var username = $("#username-input").val();
        var errorArea = '#username-input-area .input-error-msg';
        if (isEmpty(username)) {
            $(errorArea).empty().append(ERROR_MSG.inputEmptyError);
        } else {
            usernameFlag = true;
        }
    });

    $("#password-input").blur(function () {
        var password = $("#password-input").val();
        var errorArea = '#password-input-area .input-error-msg'
        if (isEmpty(password)) {
            $(errorArea).empty().append(ERROR_MSG.inputEmptyError);
        } else {
            passwordFlag = true;
            $(errorArea).empty();
        }
    });

    $("#login-form button").click(function () {
        if (usernameFlag && passwordFlag) {
            var user = {
                username: $("#username-input").val(),
                password: $("#password-input").val()
            };
            $.ajax({
                url: '/user/login',
                type: 'post',
                data: user,
                success: function (data) {
                    window.location.href = data;
                }
            })
        } else {
            alert("请认真填写表单");
        }
    });

});