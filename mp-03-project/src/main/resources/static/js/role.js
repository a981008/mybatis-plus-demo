let table = layui.table;

var tableIns = table.render({
    elem: '#roleList', // table的id
    url: '/role/list', // 数据接口
    page: true,  //开启分页
    response: {
        statusName: 'code', //规定返回的状态码字段为code
        statusCode: 200 //规定成功的状态码味200
    },
    parseData: function (res) { //res 即为原始返回的数据
        return {
            "code": res.code, //解析接口状态
            "msg": res.msg, //解析提示文本
            "count": res.data.count, //解析数据长度
            "data": res.data.records //解析数据列表
        };
    },
    cols: [[ // 表头
        {field: 'roleName', title: '角色'},
        {field: 'createTime', title: '创建时间'},
        {field: 'modifiedTime', title: '修改时间'},
        {title: '操作', toolbar: '#barDemo'}
    ]]
});

/**
 * 按条件查询
 */
function query() {
    tableIns.reload({
        where: { // 设定异步数据接口的额外参数，任意设
            roleName: $("#roleName").val()
        },
        page: {
            curr: 1 //重新从第 1 页开始
        }
    });
}

function toAdd() {
    openLayer('/role/toAdd', '新增角色');
    showTree('/role/listResource/2', 'resource');
    submit('addSubmit', 'POST', addIds);
}

let addIds = function (field) {
    let checkedData = layui.tree.getChecked('resource');
    field.resourceIds = getIds(checkedData, []);
}

function getIds(checkedData, arr) {
    for (let i in checkedData) {
        arr.push(checkedData[i].id);
        arr = getIds(checkedData[i].children, arr);
    }
    return arr;
}


//工具条事件
table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
    var data = obj.data; //获得当前行数据
    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
    var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

    let roleId = data.roleId;

    if (layEvent === 'detail') { //查看
        openLayer('/role/toDetail/' + roleId, '角色详情');
        showTree('/role/listResource/0/' + roleId, 'resource', false);
    } else if (layEvent === 'del') { //删除
        layer.confirm('真的删除么', function (index) {
            obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
            myDelete('/role/' + roleId);
            layer.close(index);
        });
    } else if (layEvent === 'edit') { //编辑
        //do something
        openLayer('/role/toUpdate/' + roleId, '编辑角色');
        layui.form.render();
        showTree('/role/listResource/1/' + roleId, 'resource');
        submit('updateSubmit', 'PUT', addIds);
    }
});

/**
 * 显示树
 */
function showTree(url, id, showCheckbox) {
    if (typeof (showCheckbox) == 'undefined') {
        showCheckbox = true;
    }
    $.ajax({
        url: url,
        async: false,
        type: 'get',
        success: function (res) {
            if (res.code === 200) {
                layui.tree.render({
                    elem: '#' + id,
                    data: res.data,
                    id: id,
                    showCheckbox: showCheckbox
                })
            }
        }
    })
}