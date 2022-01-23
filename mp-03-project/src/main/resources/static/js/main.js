/**
 * 打开选项卡，进入相应主页
 */
function showTab(url, name, id) {
    let length = $("li[lay-id=" + id + "]").length;
    let element = layui.element;
    if (length === 0) {
        let fullUrl = "/" + url;
        let height = $(window).height() - 185;
        let content = '<iframe style="width: 100%; height: ' + height + 'px" src="' + fullUrl + '" frameborder="0" scrolling="no">'
        element.tabAdd('menu', {
            title: name,
            content: content,
            id: id
        });
    }
    element.tabChange("menu", id);
}