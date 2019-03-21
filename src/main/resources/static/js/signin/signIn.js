function getMedia() {

    let constraints = {
        video: { width: 250, height: 330 },
        audio: true
    };

    // 获得video摄像头区域
    let video = document.getElementById("video");
    let promise = navigator.mediaDevices.getUserMedia(constraints);
    promise.then(function (MediaStrem) {
        video.srcObject = MediaStrem;
        video.play();
    })
}

function takePhoto() {
    let video = document.getElementById("video");
    let canvas = document.getElementById("canvas");
    let ctx = canvas.getContext("2d");
    var meetingID = window.parent.document.getElementById('mid');
    var id = meetingID.value;

    ctx.drawImage(video, 0, 0, 200, 225);

    var imgData = canvas.toDataURL();
    var baseStr = imgData + "";
    baseStr = baseStr.slice(baseStr.indexOf(",") + 1);
    console.log(baseStr);

    $.ajax({
        url: "https://api-cn.faceplusplus.com/facepp/v3/detect",
        type: "POST",
        dataType: "json",
        data: {
            api_key: "-62uv_akklJjwoQuZawRjy-dbxoPhvWT",
            api_secret: "CTTqho-9btsW-DVPJcLeCBIFHIt6LH_A",
            image_base64: baseStr
        },beforeSend : function () {
            $.messager.progress({
                text : '正在识别...,按Esc取消。'
            });
        },

        success: function (data) {
            console.log(data);
            if (data.faces.length !== 0) {
                haveFaceAndAjax(data.faces[0].face_token, baseStr, id);
            }
        },
        error: function (data) {

            console.log(data);
        }
    });
}

function haveFaceAndAjax(faceToken, bStr, meetingId) {
    $.ajax({
        url: "http://127.0.0.1:8080/u_meeting/signIn/" + meetingId,
        type: "POST",
        dataType: "json",
        data: {
            onlineImgFaceToken: faceToken,
            onlineImgFaceBase64_2: bStr
        },
        success: function (data) {

            $.messager.progress('close');
            console.log(data);
            if (data.code == 1 && data.rows.meetingUser !== null) {
                // $.messager.progress('close');
                console.log("未签到");
                $("#u_id").html(data.rows.user.jobNumber);
                $("#u_name").html(data.rows.user.name);
                displayNow(1);
            } else if (data.rows == null) {
                displayNow(data.code);

            } else if (data.rows.meetingUser == null) {
                // $.messager.progress('close');
                console.log("已簽到");
                $("#u_id").html(data.rows.user.jobNumber);
                $("#u_name").html(data.rows.user.name);
                displayNow(2);
            }

        },
        error: function (data) {
            console.log(data);
        }
    });
}

function displayNow(code) {
    if (code == 1) {
        $("#success").attr("class", "");
        timeOutDO($("#success"))
        setTimeout(function () {
            parent.getList();
        },2000)

        $('#signinview').dialog()
    } else if (code == 2) {
        $("#again").attr("class", "");
        timeOutDO($("#again"))
    } else {
        if (code == 60002) {
            $("#noEplee").attr("class", "");
            timeOutDO($("#noEplee"));
        } else {
            $("#fail").attr("class", "");
            timeOutDO($("#fail"));
        }

    }
}

function timeOutDO(atr) {
    setTimeout(function () {
        $("#u_id").html("");
        $("#u_name").html("");
        atr.attr("class", "signin_display_none");
    }, 3000);
}

$(function () {
    console.log($('#id').attr("value"));

    getMedia();


})