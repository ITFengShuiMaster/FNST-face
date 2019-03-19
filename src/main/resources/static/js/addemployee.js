$(function(){

});


function save(){
    if (!$("#employform").form("validate")){
        $.messager.alert('提示', "红色区域为必填项。");
        return false;
    }
    $.ajax({
        url : '',
        type : 'post',
        dataType: "json",
        data : $("#employform").serialize(),
        beforeSend : function () {
            $.messager.progress({
                text : '正在保存中...,按Esc取消。'
            });
        },
        success : function (data) {
            $.messager.progress('close');
            if (data.result=='true') {
                $.messager.alert('提示', "保存成功！");
                $("#employlist").datagrid("reload");
                cancel();
            }
            else {
                $.messager.alert('提示', data.errorMessage);
            }
        }
    });
}

function cancel(){
    $('#employeedlog').dialog('close');
}