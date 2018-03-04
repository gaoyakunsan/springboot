(function (window, $) {
    $.fn.select2.defaults.set( "theme", "bootstrap" );
    $('#pool-sel').select2();

    function initEvent() {
        $('#btn-ok').on('click', saveItem);
    }

    function saveItem(event) {
        document.getElementById('btn-ok').disabled=true;
        var selPoolIds = '';
        $('#pool-sel > :selected').each(function () {
            if(selPoolIds === '')  selPoolIds += $(this).val();
            else selPoolIds += ',' + $(this).val();
        });
        $('input[name="poolList"]').val(selPoolIds);

        // 校验表单数据
        if(!$('input[name="notifyUrl"]').val() || !$('input[name="name"]').val()  || !selPoolIds){
            $.smkAlert({text: '名称、URL和任务池都不能为空！！', type: 'warning'});
            document.getElementById('btn-ok').disabled = false;
            return;
        }
        if(!$('input[name="notifyItems"]').is(':checked')){
            $.smkAlert({text: '未选择通知事项！！', type: 'warning'});
            document.getElementById('btn-ok').disabled = false;
            return;
        }

        document.getElementById('edit-form').submit();
    }

    initEvent();

}(window, jQuery));