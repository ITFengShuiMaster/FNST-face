
$(function(){
    alert($('#param').val());
});

function save(){
    if (!$("#meetingform").form("validate")){
        $.messager.alert('提示', "红色区域为必填项。");
        return false;
    }
    $.ajax({
        url : '/meeting',
        type : 'creat',
        dataType: "json",
        data : $("#meetingform").serialize(),
        beforeSend : function () {
            $.messager.progress({
                text : '正在保存中...,按Esc取消。'
            });
        },
        success : function (data) {
            $.messager.progress('close');
            if (data.result=='true') {
                $.messager.alert('提示', "保存成功！");
                $("#meetinglist").datagrid("reload");
                cancel();
            }
            else {
                $.messager.alert('提示', data.errorMessage);
            }
        }
    });
}

function cancel(){
    $('#meetingdlist').dialog('close');
}