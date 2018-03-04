(function (window, $) {
    function initEvent() {
        $('.del-btn').on('click', deleteDc);
        $('#mq-conf-sync-btn').on('click', mqConfSync);
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
                url: 'task-queues/' + id,
                type: 'DELETE',
                success: function (result) {
                    window.location.reload();
                }
            });
        }
    }

    function mqConfSync() {
        $.smkConfirm({
            text: '确认同步？',
            accept: '确定',
            cancel: '取消'
        }, function (res) {
            if (!res) return;
            doSync()
        });
        var doSync = function () {
            $.ajax({
                url: 'task-queues/mq-conf-sync',
                type: 'POST',
                success: function (result) {
                    $.smkAlert({text: '同步成功'});
                },
                error: function (jqXHR, statusText, errorThrown) {
                    $.smkAlert({
                        text: '同步失败, http status:' + jqXHR.status + ', status text:' + statusText,
                        type: 'error'
                    });
                }
            });
        }
    }

    initEvent();

}(window, jQuery));