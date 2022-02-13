function Question_List(){
    var url = "http://codebyte.kro.kr/project/Question";
    $.ajax({
        type: "POST",
        url: url,
        dataType: "json",
        async: false,
        success: function (data) {
            data.question.forEach(function (element){
                var $li = $('<tr></tr>');
                $li.append('<td>' + element.number + '</td>' + '<td>' + element.title + '</td>' + '<td>' + element.point + '</td>' + '<td><input type="button" onclick="question('+element.cnumber+');" value="풀기" id="btn"></td>');
                $li.appendTo($('.question'));
            });
        },
        error: function (request, status, error) {
//                    alert('code:' + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error); //에러 상태에 대한 세부사항 출력
        }
    });
}

function question(number) {
    var url = "http://codebyte.kro.kr/project/Question_Solve";
    $.ajax({
        type: "POST",
        url: url,
        dataType: "json",
        data: {
            number: number
        },
        async: false,
        success: function (data) {
            $('.solve-content > .title').text(data.title);
            $('.solve-content > .text').text(data.text);
            $('.solve-content > .in').text('입력 값:'+data.in);
            $('.solve-content > .out').text('출력 값:'+data.out);
            $('.solve-content > #cod > .code').text(data.kind);
            $('.btns > input:last-child').removeAttr("onclink");
            $('.btns > input:last-child').attr("onclick", "com("+number+");");
        },
        error: function (request, status, error) {
//                    alert('code:' + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error); //에러 상태에 대한 세부사항 출력
        }
    });
    $('.solve-modal').fadeIn();
}

$('.close-solve').click(function(){
    $('.solve-modal').fadeOut();
});

function com(number){
    $('.btns > input:last-child').attr("disabled","disabled");
    var url = "http://codebyte.kro.kr/project/Compiler";
    $.ajax({
        type: "POST",
        url: url,
        dataType: "json",
        data: {
            number: number,
            code: $('.code').val()
        },
        async: false,
        success: function (data) {
            alert(data.ty);
        },
        error: function (request, status, error) {
//                    alert('code:' + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error); //에러 상태에 대한 세부사항 출력
        }
    });
    window.location.reload();
}


Question_List();
