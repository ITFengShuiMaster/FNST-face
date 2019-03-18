$(function() {

	$('#dg').datagrid({
		url : '/',
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
				field : 'date',
				title : '日期',
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
				field : 'id',     //"<a style='text-decoration:none;' href='javascript:void(0)' onclick=showDetail("+row.id+");>详细信息</a>";
				title : '操作',
				width : 80,
				formatter: function(value,row,index){
					
						return "<button class='btn btn-success btn-xs' data-toggle='modal' data-target='#changeChar'>修改</button><button class='btn btn-danger btn-xs' data-toggle='modal' data-target='#deleteChar'>删除</button>"
					
					
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



