var flag = false;

window.onload = function seltype() {
    $.ajax({
        url: 'http://localhost:8080/Hotel_Management_System_war_exploded/seltype',
        type: 'post',
        dataType: 'json',
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var opt = "<option value='" + data[i] + "'>" + data[i] + "</option>"
                $("#room_type").append(opt);
            }
        }
    });
}

function roomins() {
    var formData = new FormData(document.forms.namedItem("form-ifo"));
    if (flag == true) {
        $.ajax({
            url: 'http://localhost:8080/Hotel_Management_System_war_exploded/roominsert',
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

function check() {
    for (var i = 0; i < document.getElementById("form-ifo").elements.length - 1; i++) {
        if (document.getElementById("form-ifo").elements[i].value == "") {
            alert("有未填项，无法提交！");
            document.getElementById("form-ifo").elements[i].focus();
            flag = false;
            return false;
        }
    }
    flag = true;
    return true;
}