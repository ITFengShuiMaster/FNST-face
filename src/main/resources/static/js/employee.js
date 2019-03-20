$(function() {

	$('#employeelist').datagrid({
		url : '/test.json',
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
					
						return "<a style='text-decoration:none;' href='javascript:void(0)' onclick=showDetail("+row.id+");>详细信息</a>";
					
					
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

