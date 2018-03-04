(function (window, $) {
    function initEvent() {
        $('#btn-ok').on('click', saveItem);
    }

    function saveItem(event) {
        document.getElementById('btn-ok').disabled=true;

        if(!$('input[name="name"]').val() || !$('input[name="remark"]').val()
            || !$('input[name="priority"]').val()){
            $.smkAlert({text:'名称、备注、优先级 都不能为', type: 'warning'});
            document.getElementById('btn-ok').disabled=false;
            return;
        }

        document.getElementById('edit-form').submit();
    }

    initEvent();

}(window, jQuery));