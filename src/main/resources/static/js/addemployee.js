$(function(){




});


function getQueryParam() {
    var obj = $('#employeedlog').dialog('options');
    var queryParams = obj["queryParams"];

    return queryParams["id"];
}
function save(){
    var url="/user";
    var type="post";
    console.log(getQueryParam());

    if(getQueryParam()!=null && getQueryParam()!=""){
        url="/user/update";
    }
    if (!$("#employform").form("validate")){
        $.messager.alert('提示', "红色区域为必填项。");
        return false;
    }
    // console.log($("[name='name']").attr("value") + " " + $("input[name='sex'][checked]").val() + " " + $("[name='jobNumber']").attr("value"));
    console.log($("input[name='imgFile']")[0].files[0]);
    var form = new FormData();

    var name = $("[name='name']").attr("value");
    var sex = 0;
    var jobNumber = $("[name='jobNumber']").attr("value");
    var imgFile = $("input[name='imgFile']")[0].files[0];
    var id = getQueryParam();

    if (name != null && name != "") {
        form.append("name", name);
    }
    if (sex != null) {
        form.append("sex",1);
    }
    if (jobNumber != null && jobNumber != "") {
        form.append("jobNumber", jobNumber);
    }
    // form.append("name",$("[name='name']").attr("value"));
    // form.append("sex",1);
    // form.append("jobNumber", $("[name='jobNumber']").attr("value"));
    if (imgFile != null) {
        form.append("imgFile", imgFile);
    }
    // form.append("imgFile", $("input[name='imgFile']")[0].files[0]);
    if (id != null && id != "") {
        form.append("id", id);
    }

    $.ajax({
        url:url,
        type:type,
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
            $("#employeelist").datagrid("reload");
            console.log(data);
            $.messager.progress('close');
            if (data.code == 1) {
                $.messager.alert('提示', "保存成功！");

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