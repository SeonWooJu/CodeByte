function Question_Log(){
    var url = "http://codebyte.kro.kr/project/Question_Log";
    $.ajax({
        type: "POST",
        url: url,
        dataType: "json",
        async: false,
        success: function (data) {
            data.loglist.forEach(function (element){
                var $li = $('<tr></tr>');
                $li.append('<td>' + element.number + '</td><td>' + element.time + '</td><td><input type="button" onclick="code(\''+element.data+'\');" value="보기" id="btn"></td><td>' + element.ty + '</td>');
                $li.appendTo($('.log'));
            });
        },
        error: function (request, status, error) {
//                    alert('code:' + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error); //에러 상태에 대한 세부사항 출력
        }
    });
}

function code(data){
    var url = "http://codebyte.kro.kr/project/Code_Log";
    $.ajax({
        type: "POST",
        url: url,
        data: {
            code_number: data
        },
        dataType: "json",
        async: false,
        success: function (data) {
            $('.code').text(data.code);
        },
        error: function (request, status, error) {
//                    alert('code:' + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error); //에러 상태에 대한 세부사항 출력
        }
    });
    $('.code-modal').fadeIn();
}

$('.close-code').click(function(){
    $('.code-modal').fadeOut();
});

Question_Log();