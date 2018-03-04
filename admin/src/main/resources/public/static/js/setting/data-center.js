(function (window, $) {
    function initEvent() {
        $('.edit-btn').on('click', editDc);
        $('.del-btn').on('click', deleteDc);
        $('#new-btn').on('click', addDc);
        $('#btn-ok').on('click', saveDc);
    }

    function editDc(event) {
        var id = $(this).parent().parent().find('.item-id').text();
        var name = $(this).parent().parent().find('.item-name').text();
        $('#edit-dialog .modal-title').text('修改');
        $('#edit-dialog').find('#dc-id').val(id);
        $('#edit-dialog').find('#dc-name').val(name);
        $('#edit-dialog').modal();
    }

    function deleteDc(event) {
        var id = $(this).parent().parent().find('.item-id').text();
        $.smkConfirm({
            text: '确认删除？',
            accept: '确定',
            cancel: '取消'
        }, function (res) {
            if (!res) return;
            doDelete(id)
        });
        var doDelete = function (id) {
            $.ajax({
                url: 'data-centers/'+id,
                type: 'DELETE',
                success: function (result) {
                    window.location.reload();
                }
            });
        }
    }

    function addDc() {
        $('#edit-dialog .modal-title').text('新增');
        $('#edit-dialog').find('#dc-id').val(0);
        $('#edit-dialog').find('#dc-name').val('');
        $('#edit-dialog').modal();
    }

    function saveDc() {
        var id = $('#edit-dialog').find('#dc-id').val();
        var name = $('#edit-dialog').find('#dc-name').val();
        name = $.trim(name);
        if(!name){
            $.smkAlert({text: '名称不能为空', type: 'warning'});
            return;
        }
        if(name.indexOf(' ') > -1){
            $.smkAlert({text: '名称中间不能有空格', type: 'warning'});
            return;
        }

        document.getElementById('edit-form').submit();

        // $.ajax({
        //     url: 'data-center?id='+id + '&name='+name,
        //     type: 'POST',
        //     success: function (res) {
        //         // $.smkAlert({text: '操作成功！'});
        //         // $('#edit-dialog').modal('hide');
        //         window.history.forward();
        //         window.location.reload();
        //     }
        // });
    }

    initEvent();

}(window, jQuery));