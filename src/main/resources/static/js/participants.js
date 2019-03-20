$(function() {

    $('#participants').datagrid({
        url : '/',
        method:'post',
        queryParams: {

            name: 'easyui',
            subject: 'datagrid'
                        },
        title : '参会名单',
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
                field : 'isArr',
                title : '是否到场',
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

        pagination : true,
        pageSize : 20,
        pageList : [20, 30, 40],
        pageNumber : 1,

    });
});



