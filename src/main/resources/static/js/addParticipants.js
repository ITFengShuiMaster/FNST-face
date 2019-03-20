$(function() {

    $('#add').datagrid({
        url : '/employee.json/',
        method:'get',
        title : '',
        striped : true,
        nowrap : true,
        rownumbers : true,
        fitColumns : true,
        fit:true,
        singleSelect:true,
        toolbar:$("#tb"),
        columns : [[
            {
                field : 'name',
                title : '姓名',
                width : 80
            },
            {
                field : 'jobNumber',
                title : '工号',
                width : 60,


            },
            {
                field : 'id',
                title : '操作',
                width : 80,
                formatter: function(value,row,index){
                    if(row.isadd==false) {
                        return "<button class='btn btn-success btn-xs'  onclick=addpart(getQueryParam(),"+row.id+");>添加</button>";
                    }else{
                        return "<button class='btn btn-danger btn-xs'  onclick=delart(getQueryParam(),"+row.id+");>删除</button>"
                    }

                }
            }
        ]],



    });
});

function search(){
    params={};
    //没有填的就不传给服务器端
    if ($("#keyword").val()!=""){
        params["searchType"]=$("#searchType").val();
        params["keyword"]=$("#keyword").val();
    }


    $('#dg').datagrid({
        queryParams: params
    });

}
function getQueryParam() {
    var obj = $('#addparticipants').dialog('options');
    var queryParams = obj["queryParams"];

    return queryParams["id"];        }

function addpart(meetingid,employeeid){
    console.log(meetingid,employeeid);

    $.ajax({
        url : '',
        type : '',
        dataType: "json",
        data : {
            'meetingid':meetingid,
            'employeeid':employeeid
        },
        beforeSend : function () {
            $.messager.progress({
                text : '正在添加中...,按Esc取消。'
            });
        },
        success : function (data) {
            $.messager.progress('close');
            if (data.result=='true') {
                $.messager.alert('提示', "添加成功！");
                $("#add").datagrid("reload");
            }
            else {
                $.messager.alert('提示', data.errorMessage);
            }
        }
    });
}
function delpart(meetingid,employeeid){
    console.log(meetingid,employeeid);

    $.ajax({
        url : '',
        type : '',
        dataType: "json",
        data : {
            'meetingid':meetingid,
            'employeeid':employeeid
        },
        beforeSend : function () {
            $.messager.progress({
                text : '正在删除...,按Esc取消。'
            });
        },
        success : function (data) {
            $.messager.progress('close');
            if (data.result=='true') {
                $.messager.alert('提示', "删除成功！");
                $("#add").datagrid("reload");
            }
            else {
                $.messager.alert('提示', data.errorMessage);
            }
        }
    });
}

