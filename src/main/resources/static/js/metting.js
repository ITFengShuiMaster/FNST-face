$(function() {

	$('#meetinglist').datagrid({
		url : '/meeting/list/',
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
				field : 'createTime',
				title : '会议日期',
				width : 60
			},
			{
				field : 'id',
				title : '操作',
				width : 80,
				formatter: function(value,row,index){
					return "<button class='btn btn-success btn-xs'  onclick=addparts("+row.id+");>添加人员" +
						"</button>&nbsp;<button class='btn btn-success btn-xs' data-toggle='modal' onclick=details("+row.id+",'"+row.name+"')>考勤信息" +
						"</button>&nbsp;<button class='btn btn-danger btn-xs'  onclick=delmt("+row.id+")>删除</button>"
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
function details(id,mname) {
	console.log(mname);
	var inputObject = window.parent.document.getElementById('frame');
	var meetingID = window.parent.document.getElementById('meetingID');
	var meetingName = window.parent.document.getElementById('meetingName');
	console.log(mname);
	meetingID.value = id;
	meetingName.value=mname;

	inputObject.src = "./meetingUserDetail.html";

}
//     $('#participants').datagrid({
// 		url: '/u_meeting/' + meetingId,
// 		method: 'get',
// 		title: '参会名单',
// 		striped: true,
// 		nowrap: true,
// 		rownumbers: true,
// 		fitColumns: true,
// 		fit: true,
// 		singleSelect: true,
// //            toolbar:$("#toolbar"),
// 		columns: [[
// 			{
// 				field: 'name',
// 				title: '姓名',
// 				width: 80
// 			},
// 			{
// 				field: 'sex',
// 				title: '性别',
// 				width: 80
// 			},
//
// 			{
// 				field: 'jobNumber',
// 				title: '工号',
// 				width: 60
// 			},
// 			{
// 				field: 'id',
// 				title: '操作',
// 				width: 80,
// 				formatter: function (value, row, index) {
// 					return "<a style='text-decoration:none;' href='javascript:void(0)' onclick=showDetail(" + row.id + ");>详细信息</a><button class='btn btn-danger btn-xs' data-toggle='modal'onclick='deleteMeeting(" + row.id + ")'>删除</button>";
// 				}
// 			}
// 		]],
//
// 		pagination: true,
// 		pageSize: 20,
// 		pageList: [20, 30, 40],
// 		pageNumber: 1,
// 	});
// 	var inputObject =  window.parent.document.getElementById('frame');
// 	var meetingID =  window.parent.document.getElementById('meetingID');
// 	meetingID.value=meetingId;
//
// 	inputObject.src = "./meetingUserDetail.html";

	// $('#meetingdetails').dialog({
	// 	title: "考勤信息",
	// 	width:800,
	// 	height:600,
	// 	closed: false,
	// 	cache: false,
	// 	modal: true,
	// 	href:"./meeting_details.html"
	// });

function delmt(id){
    $.ajax({
                url : '/meeting/'+id,
                type : 'delete',
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

function mtreLoad(){
	$("#meetinglist").datagrid("reload");
}

function searchMeeting(){
    var keyword = $('#keyword').val();
    console.log(keyword);

    $('#meetinglist').datagrid({
    		url : '/meeting/search?name='+keyword,
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
    				field : 'createTime',
    				title : '会议日期',
    				width : 60
    			},
    			{
    				field : 'id',
    				title : '操作',
    				width : 80,
    				formatter: function(value,row,index){
    					return "<button class='btn btn-success btn-xs'  onclick=addparts("+row.id+");>添加人员" +
    						"</button>&nbsp;<button class='btn btn-success btn-xs' data-toggle='modal' onclick=details("+row.id+",'"+row.name+"')>考勤信息" +
    						"</button>&nbsp;<button class='btn btn-danger btn-xs'  onclick=delmt("+row.id+")>删除</button>"
    				}
    			}
    		]],
    	});
}