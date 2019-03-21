$(function(){

});


function save(){
    if (!$("#employform").form("validate")){
        $.messager.alert('提示', "红色区域为必填项。");
        return false;
    }
    // console.log($("[name='name']").attr("value") + " " + $("input[name='sex'][checked]").val() + " " + $("[name='jobNumber']").attr("value"));
    console.log($("input[name='imgFile']")[0].files[0]);

    var form = new FormData();
    form.append("name",$("[name='name']").attr("value"));
    form.append("sex",1);
    form.append("jobNumber", $("[name='jobNumber']").attr("value"));
    form.append("imgFile", $("input[name='imgFile']")[0].files[0]);

    $.ajax({
        url:"/user",
        type:"post",
        dataType: "json",
        data:form,
        beforeSend : function () {
            $.messager.progress({
                text : '正在保存中...,按Esc取消。'
            });
        },
        processData:false,
        contentType:false,
        success : function (data) {
            console.log(data);
            $.messager.progress('close');
            if (data.code == 1) {
                $.messager.alert('提示', "保存成功！");
                $("#employlist").datagrid("reload");
                cancel();
            }
            else {
                console.log("error ++++++++++++++++++");
                $.messager.alert('提示', data.message);
            }
        }
    });
    // $.ajax({
    //     url : '/user',
    //     type : 'post',
    //     dataType: "json",
    //     data : {
    //         name: $("[name='name']").attr("value"),
    //         sex: 1,
    //         jobNumber: $("[name='jobNumber']").attr("value"),
    //         // imgFile: $("input[name='imgFile']")[0].files[0]
    //     },
    //     beforeSend : function () {
    //         $.messager.progress({
    //             text : '正在保存中...,按Esc取消。'
    //         });
    //     },
    //     success : function (data) {
    //         console.log(data);
    //         $.messager.progress('close');
    //         if (data.result=='true') {
    //             $.messager.alert('提示', "保存成功！");
    //             $("#employlist").datagrid("reload");
    //             cancel();
    //         }
    //         else {
    //             console.log("error ++++++++++++++++++");
    //             $.messager.alert('提示', data.errorMessage);
    //         }
    //     }
    // });
}

function cancel(){
    $('#employeedlog').dialog('close');
}