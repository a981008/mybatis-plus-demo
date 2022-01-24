
/**
 * 公共弹出
 */
function openLayer(url, title) {
    $.ajaxSettings.async = false;
    $.get(url, function (res) {
        layer.open({
            type: 1,
            title: title,
            area: ['800px', '450px'],
            content: res
        });
    });
    $.ajaxSettings.async = true;
}

/**
 * 公共提交
 */
function submit(filter, type, func) {
    layui.form.on('submit(' + filter + ')', function (data) {
        if (typeof(func) != 'undefined') {
            func(data.field);
        }
        $.ajax({
            url: data.form.action,
            async: false,
            type: type,
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(data.field),
            success: function (res) {
                if (res.code === 200) {
                    layer.closeAll();
                    query();
                } else {
                    layer.alert(res.msg);
                }
            }
        });
        return false;
    });
}


/**
 * 公共删除
 */
function myDelete(url) {
    let br = false;
//向服务端发送删除指令
    $.ajax({
        url: url,
        async: false,
        type: 'delete',
        success: function (res) {
            if (res.code === 200) {
                query();
                br = true;
            } else {
                layer.alert(res.msg);
            }
        }
    });

    return br;
}

