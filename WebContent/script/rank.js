function Rank_List(){
    var url = "http://codebyte.kro.kr/project/Rank_Values";
    $.ajax({
        type: "POST",
        url: url,
        dataType: "json",
        async: false,
        success: function (data) {
            data.ranklist.forEach(function (element){
                var $li = $('<tr></tr>');
                $li.append("<td>" + element.rank + "</td>" + "<td>" + element.name + "</td>" + "<td>" + element.point + "</td>");
                $li.appendTo($('.rank'));
            });
        },
        error: function (request, status, error) {
//                    alert('code:' + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error); //에러 상태에 대한 세부사항 출력
        }
    });
}

Rank_List();