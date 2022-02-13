//index 기본데이터
function Index() {
    var url = "http://codebyte.kro.kr/project/Index";
    $.ajax({
        type: "POST",
        url: url,
        success: function (data) {
            data.updatelist.forEach(function (element) {
                var $li = $('<li></li>');
                $li.append("> " + element.text + "<n>" + element.date + "</n>");
                $li.appendTo($('.updates > ul'));
            });
            var count = 0;
            data.top3.forEach(function (element) {
                count++;
                $('.top_' + count).text(element.name);
                $('.p_' + count).text("(" + element.point + " point)");
                $('.top_' + count).css({
                    "margin-top": (100 - element.margin) + "px"
                });
            })
            $('.Basic').text(data.Basic);
            $('.Custom').text(data.Custom);
            $('.User').text(data.User);
        },
        error: function (request, status, error) {
            //        alert('code:' + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error); //에러 상태에 대한 세부사항 출력
        }
    });
}
//index 기본데이터
Index();