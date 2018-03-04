(function (window, $) {
    function initEvent() {
        $('.del-btn').on('click', deleteDc);
        $('#zk-conf-sync-btn').on('click', function (e) {
            var beforeDo = function () {
                $('#zk-conf-sync-btn').text('同步中...');
                $('#zk-conf-sync-btn').prop('disabled', true);
            };
            var afterDo = function () {
                $('#zk-conf-sync-btn').text('同步到ZooKeeper');
                $('#zk-conf-sync-btn').prop('disabled', false);
            };
            zkConfSync(beforeDo, afterDo);
        });
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
                url: 'workers/' + id,
                type: 'DELETE',
                success: function (result) {
                    window.location.reload();
                }
            });
        }
    }

    function zkConfSync(beforDo, afterDo) {
        $.smkConfirm({
                text: '确认同步？',
                accept: '确定',
                cancel: '取消'
            },
            function (res) {
                if (!res) return;
                beforDo();
                doSync()
            }
        );
        var doSync = function () {
            $.ajax({
                url: 'workers/zk-conf-sync',
                type: 'POST',
                success: function (result) {
                    afterDo();
                    $.smkAlert({text: '同步成功'});
                },
                error: function (jqXHR, statusText, errorThrown) {
                    afterDo();
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