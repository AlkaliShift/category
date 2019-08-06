$(document).ready(function () {
    layui.use(['tree', 'util'], function () {
        var tree = layui.tree
            , layer = layui.layer
            , util = layui.util
            , data = [{title: 'Category', id: 100}];

        // $("#test").val("");
        tree.render({
            elem: '#test'
            , data: data
            , id: "tree"
            , edit: ['add', 'update', 'del']
            , click: function (obj) {
                //var children = getList(obj.data.id).categories;
                obj.data.children = {title: "1", id: 101};
                var temp = [];
                temp.title = obj.data.title;
                temp.id = obj.data.id;
                temp.children = obj.data.children;

                reloadTree(temp);
            }
        });

        function reloadTree(temp){
            tree.reload("tree",{
                data: temp
            });
        }
    });
});

//get category list
function getList(parentId) {
    var flag = 0;
    var data;
    $.ajax({
        type: "GET",
        url: "/category/list?parentId=" + parentId,
        dataType: "json",
        async: false,
        success: function (result) {
            flag = 1;
            data = result;
        },
        error: function () {
        }
    });

    if (flag == 1) {
        return data;
    } else {
        return "Connection failed.";
    }
}

var data1 = [{
    title: '江西'
    ,id: 1
    ,children: [{
        title: '南昌'
        ,id: 1000
        ,children: [{
            title: '青山湖区'
            ,id: 10001
        },{
            title: '高新区'
            ,id: 10002
        }]
    },{
        title: '九江'
        ,id: 1001
    },{
        title: '赣州'
        ,id: 1002
    }]
},{
    title: '广西'
    ,id: 2
    ,children: [{
        title: '南宁'
        ,id: 2000
    },{
        title: '桂林'
        ,id: 2001
    }]
},{
    title: '陕西'
    ,id: 3
    ,children: [{
        title: '西安'
        ,id: 3000
    },{
        title: '延安'
        ,id: 3001
    }]
}]