/**
 * Created by ob on 17-3-16.
 */

var ERROR_MSG = {
    inputEmptyError: '<span class="new badge red" data-badge-caption="输入不能为空"></span>',
    inputAlreadyExists: '<span class="new badge red" data-badge-caption="已经存在，请更换"></span>',
    msgNotEqualError: '<span class="new badge red" data-badge-caption="输入不一致，请重新输入"></span>'
}

function isEmpty(str) {
    return str == null || str == undefined || str == '';
}

function isNotEmpty(str) {
    return !isEmpty(str);
}