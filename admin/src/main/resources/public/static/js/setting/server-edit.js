(function (window, $) {
    function initEvent() {
        $('#btn-ok').on('click', saveItem);
    }

    function saveItem(event) {
        document.getElementById('btn-ok').disabled = true;
        document.getElementById('edit-form').submit();
    }

    /**
     * 通过后端渲染到页面上的 hibernate validator 表单字段校验错误信息，对具体表单控件做出提示
     */
    function showValidErrors() {
        $("#errors>span").each(function (i, item) {
            var name = $(item).data('name');
            var msg = $(item).data('msg');
            var node = document.getElementsByName(name);
            if(node && node.length>0){
                node[0].style.borderColor = "#a94442";
                node[0].title = msg;
            }
        });
    }

    initEvent();
    showValidErrors();

}(window, jQuery));