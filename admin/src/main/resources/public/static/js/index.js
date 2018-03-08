/**
 * Created by yancongliu on 2017/7/21.
 */

(function (window, $) {

    window.dataRangeConf = {
        startDate: moment().startOf('day'),
        endDate: moment(),
        //minDate: '01/01/2012',    //最小时间
        autoUpdateInput: false,
        autoApply: false,
        // maxDate: moment(), //最大时间
        // dateLimit: {
        //     days: 7 //起止时间的最大间隔
        // },
        // showDropdowns: true,
        // showWeekNumbers: true, //是否显示第几周
        // timePicker: true, //是否显示小时和分钟
        // timePickerIncrement: 60, //时间的增量，单位为分钟
        // timePicker12Hour: false, //是否使用12小时制来显示时间
        ranges: {
            //'最近1小时': [moment().subtract('hours',1), moment()],
            '今日': [moment().startOf('day'), moment()],
            '昨日': [moment().subtract('days', 1), moment()],
            '最近7日': [moment().subtract('days', 6), moment()],
            // '最近30日': [moment().subtract('days', 29), moment()]
        },
        opens: 'center', //日期选择框的弹出位置
        // buttonClasses: ['btn btn-default'],
        applyClass: 'btn-small btn-primary',
        cancelClass: 'btn-small',
        ranges: {
            '今日': [moment(), moment()],
            '昨日': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
            '最近七日': [moment().subtract(6, 'days'), moment()]
        },
        locale: {
            format: 'YYYY-MM-DD',
            separator: ' 至 ',
            applyLabel: '确定',
            cancelLabel: '清除',
            fromLabel: '起始时间',
            toLabel: '结束时间',
            customRangeLabel: '自定义',
            daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
            monthNames: ['一月', '二月', '三月', '四月', '五月', '六月',
                '七月', '八月', '九月', '十月', '十一月', '十二月'],
            firstDay: 1
        }
    };

    window.jqTableLange = {
        'sEmptyTable': '没有数据',
        'sLoadingRecords': '数据加载中，请您稍候...',
        'sProcessing': '数据处理中，请您稍候...',
        'sSearch': '在结果中查找:',
        'sLengthMenu': '每页显示：_MENU_条',
        'sZeroRecords': '未找到匹配的记录',
        'paginate': {
            'sFirst': '首页',
            'sLast': '末页',
            'sNext': '下一页',
            'sPrevious': '上一页'
        },
        'sInfo': '第_START_-_END_条，共_TOTAL_条记录',
        'sInfoEmpty': '第0-0条，共0条',
        'sInfoFiltered': '(从共_MAX_条记录中查找)'
    };
    
    function reLoginTip() {
        $.smkConfirm({
            text: '登录过期，重新登录?',
            accept: '确定',
            cancel: '取消'
        }, function (res) {
            if (res) window.location.reload();
        });
    }
    $.ajaxSetup({
        complete:function(jqXHR, statusText){
            if (jqXHR.status === 302) {
                reLoginTip();
            }
        },
        error: function (jqXHR, statusText, errorThrown) {
            var rspJson = jqXHR.responseJSON;
            if (rspJson) {
                console.log('ajax error: ' + JSON.stringify(rspJson, null, 4));
                if (rspJson.status == 403 || rspJson.status == 401) {
                    $.smkAlert({
                        text: '您没有权限访问这部分数据， 请尝试重新登录或联系管理员开通权限。',
                        type: 'warning',
                    });
                }
                else {
                    $.smkAlert({
                        text: '请求错误：' + rspJson.status + ' ' + rspJson.error,
                        type: 'danger',
                    });
                }
            }
            else if(jqXHR.status == 403 || jqXHR.status == 401){
                $.smkAlert({
                    text: '您没有权限访问这部分数据， 请尝试重新登录或联系管理员开通权限。',
                    type: 'warning',
                });
            }
            else if(jqXHR.status == 400){
                $.smkAlert({
                    text: '请求错误。',
                    type: 'warning',
                });
            }
            else if (statusText == 'error' && jqXHR.status == 0 && errorThrown == '') {
                // 访问服务时，若CAS登录重定并发生跨域访问错误， 则刷新页面以便重新登录
                console.log('ajax error: ' + statusText + " http code: " + jqXHR.status);
                if (jqXHR.status === 0) {
                    reLoginTip();
                }
            }
            else {
                console.log(statusText);
                console.log(errorThrown);
                var msg = statusText;
                // CC在出错时可能返回html报错信息，非JSON格式的
                if (statusText == 'parsererror') {
                    msg = '请求Chanel Center接口出错，且返回非JSON数据';
                }
                $.smkAlert({
                    text: '请求错误：' + msg,
                    type: 'danger',
                });
            }
        },
        headers: {
            // "X-CSRF-TOKEN": getCsrfCookie(),
        }
    });

    /*function initUser() {
        $('#headerUserName').html($('#hiddenUserId').val());
    }*/

    function initView() {
    }

    //initUser();
    initView();
}(window, jQuery));