(function (window, $) {
    function initEvent() {
        $('.edit-btn').on('click', editUser);
        $('.del-btn').on('click', deleteUser);
        $('#new-btn').on('click', addUser);
        $('#btn-ok').on('click', saveUser);
    }

    function editUser(event) {
        var id = $(this).parent().siblings('.item-id').text();
        var username = $(this).parent().siblings('.item-username').text();
        var memo = $(this).parent().siblings('.item-memo').text();
        var roleId = $(this).parent().siblings('.item-roleName').data('id');

        $('#edit-dialog .modal-title').text('修改');
        $('#edit-dialog').find('#userId').val(id);
        $('#edit-dialog').find('#username').val(username).attr('readonly', 'true');
        $('#edit-dialog').find('#userMemo').val(memo);
        $('#edit-dialog').find('#userRole').val(roleId);
        $('#edit-dialog').modal();
    }

    function deleteUser(event) {
        var id = $(this).parent().siblings('.item-id').text();
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
                url: 'users/'+id,
                type: 'DELETE',
                success: function (result) {
                    window.location.reload();
                }
            });
        }
    }

    function addUser() {
        $('#edit-dialog .modal-title').text('新增');
        $('#edit-dialog').find('#userId').val(0);
        $('#edit-dialog').find('#username').val('').removeAttr('readonly');
        $('#edit-dialog').find('#userMemo').val('');
        $('#edit-dialog').find('#userRole').val(3);
        $('#edit-dialog').modal();
    }

    function saveUser() {
        var id = $('#edit-dialog').find('#userId').val();
        var username = $('#edit-dialog').find('#username').val();
        var memo = $('#edit-dialog').find('#userMemo').val();
        var roleId = $('#edit-dialog').find('#userRole').val();

        memo = $.trim(memo);
        if(!memo){
            $.smkAlert({text: '用户名称不能为空', type: 'warning'});
            return;
        }
        if(memo.indexOf(' ') > -1){
            $.smkAlert({text: '用户名称不能有空格', type: 'warning'});
            return;
        }

        if(id == 0) {
            username = $.trim(username);
            if(!username){
                $.smkAlert({text: '用户帐号不能为空', type: 'warning'});
                return;
            }
            if(username.indexOf(' ') > -1){
                $.smkAlert({text: '用户帐号不能有空格', type: 'warning'});
                return;
            }
        }

        // document.getElementById('edit-form').submit();

        $.ajax({
            url: 'users?id=' + id + '&username=' + username + '&memo=' + memo + '&roleId=' + roleId,
            type: 'POST',
            success: function (result) {
                $('#edit-dialog').hide();
                if (result.code == 200) {
                    $.smkAlert({text: result.msg, type: 'success', time: 3});
                } else if (result.code == 600) {
                    $.smkAlert({text: result.msg, type: 'warning', time: 3});
                } else {
                    $.smkAlert({text: result.msg, type: 'danger', time: 3});
                }

                setTimeout(function () {
                    window.location.href = '/users';
                }, 3000);
            }
        });
    }

    initEvent();

}(window, jQuery));