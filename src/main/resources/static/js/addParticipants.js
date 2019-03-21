$(function() {

    $('#add').datagrid({
        url : '/u_meeting/' + getQueryParam(),
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
                width : 80,
                formatter: function(value,row,index){
                    return row.user.name;
                }
            },
            {
                field : 'jobNumber',
                title : '工号',
                width : 60,
                formatter: function(value,row,index){
                    return row.user.jobNumber;
                }

            },
            {
                field : 'id',
                title : '操作',
                width : 80,
                formatter: function(value,row,index){
                    if(row.meetingUser == null) {
                        return "<button class='btn btn-success btn-xs'  onclick=addpart(getQueryParam(),"+row.user.id+");>添加</button>";
                    }else{
                        return "<button class='btn btn-danger btn-xs'  onclick=delpart(getQueryParam(),"+row.user.id+");>删除</button>"
                    }

                }
            }
        ]],



    });

    $('#add').datagrid("getData");
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

function addpart(meetingId,userId){
    console.log("会议ID: " + meetingId," 员工Id: " + userId);

    $.ajax({
        url : '/u_meeting',
        type : 'post',
        dataType: "json",
        data : {
            'meetingId':meetingId,
            'userId':userId
        },
        beforeSend : function () {
            $.messager.progress({
                text : '正在添加中...,按Esc取消。'
            });
        },
        success : function (data) {
            $.messager.progress('close');
            if (data.code == 1) {
                $.messager.alert('提示', "添加成功！");
                $("#add").datagrid("reload");
            }
            else {
                $.messager.alert('提示', data.msg);
            }
        }
    });
}
function delpart(meetingId,userId){
    console.log(meetingId,userId);

    $.ajax({
        url : '/u_meeting/remove/' + meetingId + "/" + userId,
        type : 'delete',
        dataType: "json",
        beforeSend : function () {
            $.messager.progress({
                text : '正在删除...,按Esc取消。'
            });
        },
        success : function (data) {
            $.messager.progress('close');
            if (data.code == 1) {
                $.messager.alert('提示', "删除成功！");
                $("#add").datagrid("reload");
            }
            else {
                $.messager.alert('提示', data.msg);
            }
        }
    });
}

