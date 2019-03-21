$(function () {
    var meetingID = window.parent.document.getElementById('meetingID');
    var id = meetingID.value;
    $('#id').attr("value", id);
    getList();

});


function getList(){


    let id=$('#id').attr("value");

    $.ajax({
        url: '/u_meeting/' + id,
        type: 'get',
        beforeSend: function () {
            // $.messager.progress({
            //     text: '正在获取参会人员信息...,'
            // });
        },
        success: function (data) {
            console.log(data);
            // $.messager.progress('close');
            if (data.code == 1) {
                doReturnData(data.rows);
            } else {
                // $.messager.alert('提示', data.message);
            }
        }
    });
}
    /* 获得会议id */


function Signin() {
    // let id= $('#id').val();
    $('#signinview').dialog({
        title: "考勤",
        width:700,
        height:450,
        closed: false,
        cache: false,
        modal: true,
        href:"./temp.html",
        onclose:function(){
            alert();
        }

    });

}

function doReturnData(users) {
    console.log(users);

    const defaultImg = "http://127.0.0.1:8080/photo.png"
    let meeting_people_details = $('.meeting_people_detail_all ul');
    let meeting_visitors_details = $('.meeting_visitor_detail_all ul');
    $('.meeting_people_detail_all ul li').remove();
    $('.meeting_visitor_detail_all ul li').remove();
    for (let index = 0; index < users.length; index ++) {
        let user = users[index].user;
        let mUser = users[index].meetingUser;

        if (mUser != null) {
            if (mUser.isVisited) {
                // 是访客
                var meeting_visitor_detail = `<li class="meeting_visitor_detail">
                                    <div class="meeting_visitor_detail_title">
                                        <h3>` + user.name + `</h3>
                                    </div>
                                    <div class="meeting_visitor_detail_content">
                                        <img src="` + mUser.imgUrl + `">
                                    </div>
                                    `;

                meeting_visitors_details.append(meeting_visitor_detail);
            } else {
                // 不是访客
                if (mUser.isAttend) {
                    // 已经出席
                    let meeting_people_detail = `<li class="meeting_people_detail">
                                        <div class="meeting_people_detail_title">
                                            <h3>` + user.name + `</h3>
                                        </div>
                                       <div class="meeting_people_detail_content">
                                            <img src="` + mUser.imgUrl + `">
                                        </div>
                                        `;

                    meeting_people_details.append(meeting_people_detail);
                } else {
                    // 没有出席
                    let meeting_people_detail = `<li class="meeting_people_detail">
                                        <div class="meeting_people_detail_title">
                                            <h3>` + user.name + `</h3>
                                        </div>
                                       <div class="meeting_people_detail_content">
                                            <img src="` + defaultImg + `">
                                        </div>
                                        `;

                    meeting_people_details.append(meeting_people_detail);
                }
            }
        }
    }
}
