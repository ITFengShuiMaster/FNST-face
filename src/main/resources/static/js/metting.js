$(function() {

	$('#meetinglist').datagrid({
		url : '/test.json/',
		method:'get',
		title : '会议列表',
		striped : true,
		nowrap : true,
		rownumbers : true,
		fitColumns : true,
		fit:true,
		singleSelect:true,
		toolbar:$("#toolbar"),
		columns : [[		
			{
				field : 'name',
				title : '会议名称',
				width : 80
			},
			{
				field : 'meetingTime',
				title : '会议日期',

			},		
			{
				field : 'id',     //"<a style='text-decoration:none;' href='javascript:void(0)' onclick=showDetail("+row.id+");>详细信息</a>";
				title : '操作',
				width : 80,
				formatter: function(value,row,index){



						return "<button class='btn btn-success btn-xs'  onclick=addparts("+row.id+");>添加人员</button>&nbsp;<button class='btn btn-success btn-xs' data-toggle='modal' onclick=details("+row.id+")>考勤信息</button>&nbsp;<button class='btn btn-danger btn-xs'  onclick=delmt("+row.id+")>删除</button>"
					
					

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
	

	$('#meetinglist').datagrid({
		queryParams: params
	});

}
function addmeeting() {
	$('#meetingdlog').dialog({
		title: "新增会议",
		iconCls:'icon-add-new',
		width:300,
		height:200,
		closed: false,
		cache: false,
		modal: true,
		href:"./addmeeting.html"
	});
}

function details(id) {

	var inputObject =  window.parent.document.getElementById('frame');
	var meetingID =  window.parent.document.getElementById('meetingID');
	meetingID.value=id;

	inputObject.src = "./participants.html";
	// console.log("ddd");
	// console.log($('#frame').attr("src"));
	// $('#frame').attr("src",'./participants.html');


// 	$('#meetingdetails').dialog({
// 		title: "考勤信息",
//
// 		width:800,
// 		height:600,
// 		closed: false,
// 		cache: false,
// 		modal: true,
// 		href:"./meeting_details.html"
// 	});
// }
}
function deleteMeeting(id){
    $.ajax({
                url : '/meeting/delete?id='+id,
                type : 'get',
                beforeSend : function () {
                    $.messager.progress({
                        text : '正在删除...,按Esc取消。'
                    });
                },
                success : function (data) {
                    console.log(data);
                    $.messager.progress('close');
                    if (data.code==1) {
                        $.messager.alert('提示', "删除成功！");
                        $("#meetinglist").datagrid("reload");
                    }
                    else {
                        $.messager.alert('提示', data.message);
                    }
                }
            });

}

function addparts(id){

	$('#addparticipants').dialog({
		title: "添加名单",

		width:800,
		height:650,
		closed: false,
		cache: false,
		modal: true,
		queryParams: { "id":id},
		href:"/addParticipants.html"
	});

}
function delmt(id){
	$.ajax({
		url : '',
		type : '',
		dataType: "json",
		data : {"id":id},
		beforeSend : function () {
			$.messager.progress({
				text : '正在删除中...,按Esc取消。'
			});
		},
		success : function (data) {
			$.messager.progress('close');
			if (data.result=='true') {
				$.messager.alert('提示', "删除成功！");
				$("#meetinglist").datagrid("reload");
				cancel();
			}
			else {
				$.messager.alert('提示', data.errorMessage);
			}
		}
	});

}
function mtreLoad(){
	$("#meetinglist").datagrid("reload");

}