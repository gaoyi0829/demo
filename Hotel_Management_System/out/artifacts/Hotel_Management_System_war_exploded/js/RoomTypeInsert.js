var flag = false;
$(document).ready(function () {
    $('#img-upload').click(function () {
        $('#file').click();
    });
})
function roomtypeins() {
    var formData = new FormData(document.forms.namedItem("form-ifo"));
    if (flag == true) {
        $.ajax({
            url: 'http://localhost:8080/Hotel_Management_System_war_exploded/roomtypeinsert',
            type: 'post',
            data: formData,
            processData: false,
            contentType: false,
            dataType: 'text',
            success: function () {
                alert("插入成功!");
            },
            error: function () {
                alert('插入失败!');
            }
        });
    }
}
function showimg() {
    $('#room_img').html("");
    var room_img = document.getElementById("file").value;
    var room_img1 = room_img.split("\\");
    alert(room_img1[room_img1.length - 1]);
    var img = "<img src='http://localhost:8080/download/" + room_img1[room_img1.length - 1] + "' style='width: 145px;height: 200px;vertical-align:bottom;'/>";
    $('#room_img').append(img);
}
function check(){
    for(var i=0;i<document.getElementById("form-ifo").elements.length-1;i++)
    {
        if(document.getElementById("form-ifo").elements[i].value=="")
        {
            alert("有未填项，无法提交！");
            document.getElementById("form-ifo").elements[i].focus();
            flag = false;
            return false;
        }
    }
    flag = true;
    return true;
}