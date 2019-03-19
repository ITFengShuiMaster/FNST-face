function getMedia() {
    let constraints = {
        video: { width: 500, height: 500 },
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
    ctx.drawImage(video, 0, 0, 200, 200);

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
        },
        success: function (data) {
            console.log(data);
            if (data.faces.length !== 0) {
                haveFaceAndAjax(data.faces[0].face_token, baseStr);
            }
        },
        error: function (data) {
            console.log(data);
        }
    });
}

function haveFaceAndAjax(faceToken, bStr) {
    $.ajax({
        url: "http://127.0.0.1:8080/u_meeting//signIn/2",
        type: "POST",
        dataType: "json",
        data: {
            onlineImgFaceToken: faceToken,
            onlineImgFaceBase64_2: bStr
        },
        success: function (data) {
            console.log(data);
            if (data.code == 1 && data.data.meetingUser !== null) {
                console.log("未签到");
                $("#u_id").html(data.data.user.jobNumber);
                $("#u_name").html(data.data.user.name);
                displayNow(1);
            } else if (data.data.meetingUser == null) {
                console.log("已簽到");
                displayNow(2);
            } else {
                // 顯示錯誤信息

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
    } else if (code == 2) {
        $("#again").attr("class", "");
        timeOutDO($("#again"))
    } else {
        $("#fail").attr("class", "");
        timeOutDO($("#fail"))
    }
}

function timeOutDO(atr) {
    setTimeout(function () {
        atr.attr("class", "display_none");
    }, 3000);
}

$(function () {
    getMedia();
})