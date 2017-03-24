var usernameFlag = false;
var nicknameFlag = false;
var emailFlag = false;
var passwordFlag = false;
var rePasswordFlag = false;

$(document).ready(function () {
    $("#username-input").blur(function () {
        var username = $("#username-input").val();
        var errorArea = '#username-input-area .input-error-msg';
        if (isEmpty(username)) {
            $(errorArea).empty().append(ERROR_MSG.inputEmptyError);
        } else {
            $(errorArea).empty();
            $.ajax({
                url: '/user/get',
                data: {username: username},
                type: 'post',
                async: false,
                success: function (data) {
                    var list = JSON.parse(data);
                    if (list[0].length > 0) {
                        $(errorArea).append(ERROR_MSG.inputAlreadyExists);
                    }
                    else {
                        usernameFlag = true;
                    }
                }
            })
        }
    });

    $("#nickname-input").blur(function () {
        var nickname = $("#nickname-input").val();
        var errorArea = '#nickname-input-area .input-error-msg'
        if (isEmpty(nickname)) {
            $(errorArea).empty().append(ERROR_MSG.inputEmptyError);
        } else {
            nicknameFlag = true;
            $(errorArea).empty();
        }
    });

    $("#email-input").blur(function () {
        var email = $("#email-input").val();
        var errorArea = '#email-input-area .input-error-msg';
        if (isEmpty(email)) {
            $(errorArea).empty().append(ERROR_MSG.inputEmptyError);
        } else {
            $(errorArea).empty();
            $.ajax({
                url: '/user/get',
                data: {email: email},
                type: 'post',
                async: false,
                success: function (data) {
                    var list = JSON.parse(data);
                    if (list[0].length > 0) {
                        $(errorArea).append(ERROR_MSG.inputAlreadyExists);
                    }
                    else {
                        emailFlag = true;
                    }
                }
            })
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

    $("#re-password-input").blur(function () {
        var password = $("#password-input").val();
        var rePassword = $("#re-password-input").val();
        var errorArea = '#re-password-input-area .input-error-msg'
        if (isEmpty(rePassword)) {
            $(errorArea).empty().append(ERROR_MSG.inputEmptyError);
        } else if (password != rePassword) {
            $(errorArea).empty().append(ERROR_MSG.msgNotEqualError);
        } else {
            rePasswordFlag = true;
            $(errorArea).empty();
        }
    });

    $("#register-form button").click(function () {
        if (usernameFlag && nicknameFlag && emailFlag && passwordFlag && rePasswordFlag) {
            var user = {
                username: $("#username-input").val(),
                nickName: $("#nickname-input").val(),
                email: $("#email-input").val(),
                password: $("#password-input").val()
            };
            $.ajax({
                url: '/user/register',
                type: 'post',
                data: user,
                success: function (data) {
                    window.location.href = data;
                }
            })
        } else {
            alert("请认真填写表单");
        }
    })

})