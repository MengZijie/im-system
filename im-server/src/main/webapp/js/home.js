/**
 * Created by ob on 17-3-24.
 */
$.ajax({
    url: 'user/getsession',
    type: 'get',
    success: function (data) {
        alert(data);
    }
})
