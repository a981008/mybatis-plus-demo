let table = layui.table;

var tableIns = table.render({
    elem: '#customerList', // table的id
    url: '/customer/list', // 数据接口
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
        {field: 'realName', title: '真实姓名'},
        {field: 'sex', title: '性别'},
        {field: 'age', title: '年龄'},
        {field: 'phone', title: '手机号码'},
        {field: 'createTime', title: '创建时间'},
        {title: '操作', toolbar: '#barDemo'}
    ]]
});

/**
 * 按条件查询
 */
function query() {
    tableIns.reload({
        where: { //设定异步数据接口的额外参数，任意设
            realName: $("#realName").val(),
            phone: $("#phone").val()
        },
        page: {
            curr: 1 //重新从第 1 页开始
        }
    });
}

function toAdd() {
    openLayer('/customer/toAdd', '新增用户');
    layui.form.render();
    submit('addSubmit', 'POST');
}



//工具条事件
table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
    var data = obj.data; //获得当前行数据
    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
    var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

    let customerId = data.customerId;

    if (layEvent === 'detail') { //查看
        openLayer('/customer/toDetail/' + customerId, '用户详情');
    } else if (layEvent === 'del') { //删除
        layer.confirm('真的删除行么', function (index) {
            obj.del(); //删除对应行（tr）的DOM结构，并更新缓存

            myDelete('/customer/' + customerId);

            layer.close(index);
        });
    } else if (layEvent === 'edit') { //编辑
        //do something
        openLayer('/customer/toUpdate/' + customerId, '编辑用户');
        layui.form.render();
        submit('updateSubmit', 'PUT');
    }
});
