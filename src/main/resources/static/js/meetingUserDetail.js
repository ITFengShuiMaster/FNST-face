$(function () {

    var meetingID =  window.parent.document.getElementById('meetingID');
    var id=meetingID.value;
    $('#id').attr("value",id);
    getVideo(id);
    /* 获得会议id */

});
function Signin() {
    // let id= $('#id').val();
    $('#signinview').dialog({
        title: "考勤",
        width:600,
        height:400,
        closed: false,
        cache: false,
        modal: true,
        href:"./signIn.html",
        onClose: function () {

    }
    });

}
function getVideo(id){
    console.log(id);
    var lists = [
        {
            name: "刘备",
            img_url: "D:\\new file\\简历照片.JPG"//图片路径
        },
        {
            name: "刘备",
            img_url: "D:\\new file\\简历照片.JPG"//图片路径
        },
        {
            name: "刘备",
            img_url: "D:\\new file\\简历照片.JPG"//图片路径
        },
        {
            name: "刘备",
            img_url: "D:\\new file\\简历照片.JPG"//图片路径
        }
    ];

var visitors = [
    {
        name: "刘备",
        img_url: "D:\\new file\\简历照片.JPG"//图片路径
    },
    {
        name: "刘备",
        img_url: "D:\\new file\\简历照片.JPG"//图片路径
    }
];

var meeting_people_details = $('.meeting_people_detail_all ul');
var meeting_visitors_details = $('.meeting_visitor_detail_all ul');
    $('.meeting_people_detail_all ul li').remove();
    $('.meeting_visitor_detail_all ul li').remove();
for (let index = 0; index < lists.length; index++) {
    const element = lists[index];
    var meeting_people_detail = "";

    if (element.img_url !== "") {
        meeting_people_detail = `<li class="meeting_people_detail">
                                        <div class="meeting_people_detail_title">
                                            <h3>` + element.name + `</h3>
                                        </div>
                                       <div class="meeting_people_detail_content">
                                            <img src="` + element.img_url + `">
                                        </div>
                                        `;
    } else {
        // 若img_url 为空， 显示默认图片
    }
    meeting_people_details.append(meeting_people_detail);
}

for (let index = 0; index < visitors.length; index++) {
    const element = visitors[index];
    var meeting_visitor_detail = "";

    meeting_visitor_detail = `<li class="meeting_visitor_detail">
                                    <div class="meeting_visitor_detail_title">
                                        <h3>` + element.name + `</h3>
                                    </div>
                                    <div class="meeting_visitor_detail_content">
                                        <img src="` + element.img_url + `">
                                    </div>
                                    `;

    meeting_visitors_details.append(meeting_visitor_detail);
}
}