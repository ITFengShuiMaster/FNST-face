$(function() {

	$('#dg').datagrid({
		url : '/',
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
				field : 'idCard',
				title : '工号',
				width : 60,
				formatter:function(value,row,index){
					if(value==undefined){
						return '';
					}else{
						return parseToDate(value).format("yyyy-MM-dd");
					}
				}
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
		
		pagination : true,
		pageSize : 20,
		pageList : [20, 30, 40],
		pageNumber : 1,
		
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



