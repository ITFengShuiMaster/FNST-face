$(function() {

	$('#employeelist').datagrid({
		url : '/user',
		method:'get',
		title : '员工列表',
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
				title : '姓名',
				width : 80
			},
			{
				field : 'sex',
				title : '性别',
				width : 80
			},
			{
				field : 'jobNumber',
				title : '工号',
				width : 60
			},		
			{
				field : 'id',
				title : '操作',
				width : 80,
				formatter: function(value,row,index){

					return "<button class='btn btn-success btn-xs' data-toggle='modal' onclick=showPhoto("+row.src+")>查看照片</button>&nbsp;<button class='btn btn-success btn-xs' data-toggle='modal' onclick=remake("+row.id+")>修改信息</button>&nbsp;<button class='btn btn-danger btn-xs'  onclick=delemp("+row.id+")>删除</button>"
					
					
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
	

	$('#employeelist').datagrid({
		queryParams: params
	});

}

function addemployee(){
	$('#employeedlog').dialog({
		title: "员工添加",
		iconCls:'icon-add-new',
		width:400,
		height:400,
		closed: false,
		cache: false,
		modal: true,
		href:"./addemployee.html"
	});
}

function showPhoto(imgUrl){
	$('#img').dialog({
		title: "照片",
		iconCls:'icon-add-new',
		width:400,
		height:400,
		closed: false,
		cache: false,
		modal: true,
		queryParams: { "imgUrl":imgUrl},
		href:"./img.html"
	});


}
function empreLoad(){
	$("#employeelist").datagrid("reload");

}