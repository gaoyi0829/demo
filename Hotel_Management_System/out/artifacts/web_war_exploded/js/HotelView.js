window.onload = function () {
    datevalue();
    insmaxnum();
    selroom();
    in_mindate();
    showtop();
    application();
    $("#selroom").click(function () {
        selroom();
    })
    $(document).on('click', '.buybutton', function () {
        var user_id = getCookie("cusid");
        var type = getCookie("type");
        if (user_id != null && user_id != "") {
            if (type == "管理员") {
                alert("请以顾客身份订房！")
                window.location.reload()
            }
        } else {
            var flag = confirm("请先登陆！");
            if (flag) {
                $("#login").click();
            }
        }
    });
    $(document).on('click', '#window-span-close', function () {
        $("#div-room_info").css("display", "none");
        $("#window-middle-divimg").html("");
        $("#window-middle-divspan").html("");
    })
    $(document).on('click', '.a-room_type', function () {
        $.ajax({
            url: 'selroombytype',
            type: 'post',
            data: {'room_type': this.name},
            dataType: 'json',
            success: function (data) {
                $("#window-span-room_type").html(data.room_type);
                var img = "<img src='HotelImg/" + data.room_img + "' id='window-middle-divimg-img'>";
                var area = "<span class='window-span-text'>建筑面积：" + data.room_area + "平方米</span><br>"
                var floor = "<span class='window-span-text'>楼层：17-18层</span><br>"
                var bedtype = "<span class='window-span-text'>床型：" + data.bed_type + "</span><br>"
                var window = "<span class='window-span-text'>窗户：" + data.window + "</span><br>"
                var addbed = "<span class='window-span-text'>加床：不可加床</span><br>"
                $("#window-middle-divimg").append(img);
                $("#window-middle-divspan").append(area);
                $("#window-middle-divspan").append(floor);
                $("#window-middle-divspan").append(bedtype);
                $("#window-middle-divspan").append(window);
                $("#window-middle-divspan").append(addbed);
            }
        });
        $("#div-room_info").css("display", "block");
    });
}

function selroom() {
    $.ajax({
        url: 'selroom',
        type: 'post',
        data: {
            'in_date': document.getElementById("in_date").value,
            'out_date': document.getElementById("out_date").value,
            'cusnum': $("#cusnum").val()
        },
        dataType: 'json',
        success: function (data) {
            $("#roomtable tr:not(:first)").empty();
            if (null != data && "" != data) {
                $.each(data, function () {
                    var tr = $("<tr align='center'/>");
                    var roomtype = "<td><a class='a-room_type' name='" + this.room_type + "'><img src='HotelImg/" + this.room_img + "' class='roomimg'><span class='span-room_type' >" + this.room_type + "</span></a></td>";
                    var bedtype = "<td>" + this.bed_type + "</td>";
                    var food = "<td>" + this.food + "</td>";
                    var service = "<td>" + this.room_service + "</td>";
                    var maxnum = "<td>" + this.maxnum + "人</td>";
                    var price = "<td>¥&nbsp;<span class='span-price'>" + this.price + "</span></td>";
                    var buy = "<td><input type='button' class='buybutton' value='预订' name='" + this.room_type + "' onclick='SetCookie(\"room_type\",\""+this.room_type+"\"),SetCookie(\"price\",\""+this.price+"\"),window.location.href=\"HOS_ordersList_customer.html\"'></td>";
                    $("<td/>").html(roomtype).appendTo(tr);
                    $("<td/>").html(bedtype).appendTo(tr);
                    $("<td/>").html(food).appendTo(tr);
                    $("<td/>").html(service).appendTo(tr);
                    $("<td/>").html(maxnum).appendTo(tr);
                    $("<td/>").html(price).appendTo(tr);
                    $("<td/>").html(buy).appendTo(tr);
                    $('#roomtable').append(tr);
                })
            } else {
                alert("对不起，没有找到符合条件的房间，请更改后重新搜索！");
            }
        }
    });
}

function insmaxnum() {
    $.ajax({
        url: 'getmaxnum',
        type: 'post',
        dataType: 'text',
        success: function (data) {
            var num = data;
            for (var i = 1; i <= num; i++) {
                var opt = "<option value='" + i + "'>" + i + "</option>";
                $("#cusnum").append(opt);
            }
        }
    });
}

function datevalue() {
    $("#in_date").val(getCookie("in_date"));
    $("#out_date").val(getCookie("out_date"));
    $("#out_date").attr("min", getCookie("out_date"))
}