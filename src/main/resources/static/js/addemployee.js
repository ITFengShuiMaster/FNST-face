$(function(){

});


function save(){
    if (!$("#employform").form("validate")){
        $.messager.alert('提示', "红色区域为必填项。");
        return false;
    }
    console.log($("[name='name']").attr("value") + " " + $("[name='sex']").attr("value") + " " + $("[name='jobNumber']").attr("value"));
    $.ajax({
        url : '/user',
        type : 'post',
        dataType: "json",
        data : {
            name: $("[name='name']").attr("value"),
            sex: 1,
            jobNumber: $("[name='jobNumber']").attr("value")

        },
        beforeSend : function () {
            $.messager.progress({
                text : '正在保存中...,按Esc取消。'
            });
        },
        success : function (data) {
            console.log(data);
            $.messager.progress('close');
            if (data.result=='true') {
                $.messager.alert('提示', "保存成功！");
                $("#employlist").datagrid("reload");
                cancel();
            }
            else {
                console.log("error ++++++++++++++++++");
                $.messager.alert('提示', data.errorMessage);
            }
        }
    });
}

function cancel(){
    $('#employeedlog').dialog('close');
}