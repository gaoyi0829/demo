function openwindown(src,spanhtml) {
    $("#popup").attr("src",src)
    $(".modal").css("display","block");
    $("#container-top-span").append(spanhtml);
}
function closewindown() {
    $(".modal").css("display","none")
    $("#container-top-span").empty();
}