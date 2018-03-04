(function (window, $) {
    $.fn.select2.defaults.set( "theme", "bootstrap" );
    $('select[name="serverId"]').select2();

    function initEvent() {
        $('#btn-ok').on('click', saveItem);
    }

    function saveItem(event) {
        document.getElementById('btn-ok').disabled=true;
        if(!$('input[name="name"]').val()){
            $.smkAlert({text: '名称不能为空！！', type: 'warning'});
            document.getElementById('btn-ok').disabled = false;
            return;
        }
        document.getElementById('edit-form').submit();
    }

    initEvent();

}(window, jQuery));