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