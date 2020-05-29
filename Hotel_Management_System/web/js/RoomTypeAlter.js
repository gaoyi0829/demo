var htmlvalue = null;
var room_type = null;
window.onload = function selinfo() {
    room_type = getCookie("room_type");
    $.ajax({
        type: "post",
        url: "http://localhost:8080/Hotel_Management_System_war_exploded/roomtypeinfo",
        data: {"room_type": room_type},
        dataType: "json",
        success: function (data) {
            room_type = data.room_type;
            var img = "<img src='HotelImg/" + data.room_img + "' style='width: 145px;height: 200px;vertical-align:bottom;'/>";
            $('#img-room_type').append(img);
            $('#room_type').val(data.room_type);
            $('#bed_type').val(data.bed_type);
            $("#food").val(data.food);
            $('#room_service').val(data.room_service);
            $('#maxnum').val(data.maxnum);
            $('#price').val(data.price);
            $('#room_area').val(data.room_area);
            $('#bed_length').val(data.bed_length);
            $('#window').val(data.window);
        }
    })
    $(".input").attr("disabled", true);
    $(".span").css("cursor", "pointer");
    $(".opbutton").css("display", "none");
}
$(document).ready(function () {
    $('#img-upload').click(function () {
        $('#file').click();
    });
})

function updatasub(varname,butcla) {
    var upval = $("#"+varname).val();
    $.ajax({
        url: 'http://localhost:8080/Hotel_Management_System_war_exploded/roomtypeupdata',
        type: 'post',
        data: {'upval':upval,'varname':varname,"room_type":room_type},
        dataType: 'text',
        success: function () {
            alert("更新成功!");
        },
        error: function () {
            alert('更新失败!');
        }
    });
    $("#" + varname).attr("disabled", true);
    $("." + butcla).css("display", "none");
}

function edit(butcla, inputid) {
    $("#" + inputid).attr("disabled", false);
    $("." + butcla).css("display", "");
    htmlvalue = document.getElementById(inputid).value;
}
function canceledit(butcla, inputid) {
    $("#" + inputid).attr("disabled", true);
    $("." + butcla).css("display", "none");
    document.getElementById(inputid).value = htmlvalue;
}

function showimg() {
    $('#poster').html("");
    var formData = new FormData();
    formData.append('file',$("#file")[0].files[0]);
    formData.append('room_type',room_type);
    $.ajax({
        url: 'http://localhost:8080/Hotel_Management_System_war_exploded/roomimgupdata',
        type: 'post',
        data: formData,
        processData: false,
        contentType: false,
        dataType: 'text',
        success: function () {
            alert("上传成功!");
        },
        error: function () {
            alert('上传失败!');
        }
    });
    var room_img = document.getElementById("file").value;
    var room_img1 = room_img.split("\\");
    var img = "<img src='HotelImg/"+room_img1[room_img1.length - 1]+"' style='width: 145px;height: 200px;vertical-align:bottom;'/>";
    $('#img-room_type').append(img);
}
