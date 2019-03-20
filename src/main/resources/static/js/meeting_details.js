
$(function(){


    var cardview = $.extend({}, $.fn.datagrid.defaults.view, {
        renderRow: function(target, fields, frozen, rowIndex, rowData){
            var cc = [];
            cc.push('<td colspan=' + fields.length + ' style="padding:0px;border:0;">');
            if (!frozen){
                cc.push('<img src="' + rowData.src + '" style="height:200px;float:left">');
                cc.push('<div style="float:left" >');
                for(var i=0; i<fields.length; i++){
                    var copts = $(target).datagrid('getColumnOption', fields[i]);
                    cc.push('<p><span class="c-label">' + copts.title + ':</span> ' + rowData[fields[i]] + '</p>');

                }
                cc.push('</div>');
            }
            cc.push('</td>');
            return cc.join('');
        }
    });

    $('#tt').datagrid({
        url : '/arr.json',
        title : '',
        method:'get',
        striped : true,
        nowrap : true,
        rownumbers : true,
        fitColumns : true,
        fit:true,
        singleSelect:true,
        toolbar:$("#mdtb"),
        showHeader: false,
        columns:[[
            {field:'jobNumber',title:'工号',width:80},
            {field:'name',title:'姓名',width:100,sortable:true},
            {field:'src',title:'照片',width:80,align:'right',sortable:true},
            {field:'isArr',title:'是否到场',width:80,align:'right',sortable:true},

        ]],

        view: cardview
    });



});


function mdreLoad(){
    $("#tt").datagrid("reload");

}

