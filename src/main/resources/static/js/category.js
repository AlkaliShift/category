$(document).ready(function () {
    layui.use(['tree', 'util'], function () {
        var tree = layui.tree
            , layer = layui.layer
            , util = layui.util
            , data = [{title: 'Category', id: 100, spread: true}];

        tree.render({
            elem: '#test'
            , data: data
            , id: "tree"
            , edit: ['add', 'update', 'del']
            , click: function (obj) {
                var tempData = search(obj, data);
                reloadTree(tempData);
            }
        });

        function reloadTree(temp) {
            tree.reload("tree", {
                data: temp
            });
        }

        function search(obj, temp) {
            for (i in temp) {
                var id = temp[i].id;
                if (obj.data.id === id) {
                    var children = getList(obj.data.id).categories;
                    if (children == null) {
                        return;
                    }
                    temp[i].children = [];
                    for (j in children) {
                        temp[i].children.push({
                            title: children[j].categoryName,
                            id: children[j].categoryId,
                            spread: true
                        });
                    }
                } else {
                    search(obj, temp[i].children);
                }
            }
            return temp;
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
            if (result.statusCode === 1) {
                flag = 1;
                data = result;
            } else {
                flag = 2;
            }
        },
        error: function () {
        }
    });

    if (flag == 1) {
        return data;
    } else if (flag == 2) {
        return null;
    } else {
        return "Connection failed.";
    }
}

var data1 = [{
    title: '江西'
    , id: 1
    , children: [{
        title: '南昌'
        , id: 1000
        , children: [{
            title: '青山湖区'
            , id: 10001
        }, {
            title: '高新区'
            , id: 10002
        }]
    }, {
        title: '九江'
        , id: 1001
    }, {
        title: '赣州'
        , id: 1002
    }]
}, {
    title: '广西'
    , id: 2
    , children: [{
        title: '南宁'
        , id: 2000
    }, {
        title: '桂林'
        , id: 2001
    }]
}, {
    title: '陕西'
    , id: 3
    , children: [{
        title: '西安'
        , id: 3000
    }, {
        title: '延安'
        , id: 3001
    }]
}]