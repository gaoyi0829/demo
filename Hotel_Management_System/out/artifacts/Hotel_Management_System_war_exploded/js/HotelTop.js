function openwindown(src, spanhtml) {
    // $("#popup").attr("src",src)
    // $(".modal").css("display","block");
    // $("#container-top-span").append(spanhtml);
    layui.use('layer', function () {
        var carousel = layui.carousel,
            layer = layui.layer,
            $ = layui.jquery;
        //建造实例
        var index = parent.layer.open({
            closeBtn: 1,
            type: 2,
            title: spanhtml,
            area: ['400px', '400px'],
            shadeClose: true, //点击遮罩关闭
            content: [src, 'no'],
            success: function (layero, index) {}
        })
        //form.render(); //弹出层加载的content是静态代码，加载后需再渲染一遍form
    });
}

function closewindown() {
    $(".modal").css("display", "none")
    $("#container-top-span").empty();
}