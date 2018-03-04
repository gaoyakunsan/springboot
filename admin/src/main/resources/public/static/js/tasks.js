/**
 * Created by yancongliu on 2017/7/27.
 */

(function (window, $) {
    function initEvent() {
        $('#createTime').on('apply.daterangepicker', function (ev, picker) {
            if (picker.endDate.unix() - picker.startDate.unix() > 7 * 24 * 3600) {
                $.smkAlert({text: '查询时间长度不要超过7天.', type: 'warning', time: 10,})
                return;
            }
            $(this).val(picker.startDate.format('YYYY-MM-DD') + ' 至 ' + picker.endDate.format('YYYY-MM-DD'));
            $("input[name='timeStart']").val(picker.startDate.format('YYYY-MM-DD'));
            $("input[name='timeEnd']").val(picker.endDate.format('YYYY-MM-DD'));
        });

        $('.date-range').daterangepicker(
            window.dataRangeConf,
            function (start, end, label) {
            }
        );

        $('#createTime').on('cancel.daterangepicker', function (ev, picker) {
            $("input[name='timeStart']").val('');
            $("input[name='timeEnd']").val('');
        });
    }

    initEvent();
}(window, jQuery));