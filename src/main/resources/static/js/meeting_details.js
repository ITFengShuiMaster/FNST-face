
$(function(){


    var cardview = $.extend({}, $.fn.datagrid.defaults.view, {
        renderRow: function(target, fields, frozen, rowIndex, rowData){
            var cc = [];
            cc.push('<td colspan=' + fields.length + ' style="padding:5px;border:0;">');
            if (!frozen){
                cc.push('<img src="images/' + rowData.src + '.png" style="height:150px;float:left">');
                cc.push('<div style="float:left">');
                for(var i=0; i<fields.length; i++){
                    cc.push('<p>' + fields[i] + ': ' + rowData[fields[i]] + '</p>');
                }
                cc.push('</div>');
            }
            cc.push('</td>');
            return cc.join('');
        }
    });

    $('#tt').datagrid({
        url : '/',
        title : '参会名单',
        striped : true,
        nowrap : true,
        rownumbers : true,
        fitColumns : true,
        fit:true,
        singleSelect:true,
        toolbar:$("#toolbar"),
        showHeader: false,
        columns:[[
            {field:'jobNumber',title:'工号,width:80},
            {field:'name',title:'姓名',width:100,sortable:true},
            {field:'src',title:'照片',width:80,align:'right',sortable:true},
            {field:'isArr',title:'是否到场',width:80,align:'right',sortable:true},

        ]],
        pagination : true,
        pageSize : 20,
        pageList : [20, 30, 40],
        pageNumber : 1,
        view: cardview
    });



});




