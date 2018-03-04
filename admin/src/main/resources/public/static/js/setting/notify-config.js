(function (window, $) {
    function initEvent() {
        $('.del-btn').on('click', deleteDc);
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
                url: 'notify-configs/' + id,
                type: 'DELETE',
                success: function (result) {
                    window.location.reload();
                }
            });
        }
    }

    initEvent();

}(window, jQuery));