function Point_Check(){
    var url = "http://codebyte.kro.kr/project/Login_Check";
    $.ajax({
        type: "POST",
        url: url,
        dataType: "json",
        async: false,
        success: function (data) {
            if(data.ck==true){
                if(Point()<20){
                    alert('포인트 제한이 걸려있습니다.(20 point 이상)');
                    location.href='http://codebyte.kro.kr/project/';
                }
            }
        },
        error: function (request, status, error) {
//                    alert('code:' + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error); //에러 상태에 대한 세부사항 출력
        }
    });
}

function Point(){
    var url = "http://codebyte.kro.kr/project/Point_Check";
    var number = 0;
    $.ajax({
        type: "POST",
        url: url,
        dataType: "json",
        async: false,
        success: function (data) {
            number = data.t;
        },
        error: function (request, status, error) {
//                    alert('code:' + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error); //에러 상태에 대한 세부사항 출력
        }
    });
    return number;
}

function Custom_Question_List(){
    var url = "http://codebyte.kro.kr/project/Custom_Question";
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

function aError(str) {
    $('.add-content > #n > b').text(str);
    $('.add-content > #n > b').css({
        'color': 'red'
    });
}

function Add_Check_Value(){
    var ck_1 = /[ㄱ-ㅎㅏ-ㅣ가-힣.\-]/;
    var ck_2 = /[\{\}\[\]\/?.,;:|\)*~`!^\-+<>@\#$%&\\\=\(\'\"]/;
    
    if ($('.add-content > .title').val() == "") {
        aError('제목이 입력되지 않았습니다.');
        $('.add-content > .title').focus();
        return false;
    } else if ($('.add-content > .title').val().length <= 4) {
        aError('제목이 짧습니다.');
        $('.add-content > .title').focus();
        return false;
    } else if ($('.add-content > .title').val().length >= 26) {
        aError('제목이 깁니다.');
        $('.add-content > .title').focus();
        return false;
    }
    if ($('.add-content > .text').val() == "") {
        aError('설명이 입력되지 않았습니다.');
        $('.add-content > .text').focus();
        return false;
    } else if ($('.add-content > .text').val().length <= 9) {
        aError('설명이 짧습니다.');
        $('.add-content > .text').focus();
        return false;
    } else if ($('.add-content > .text').val().length >= 221) {
        aError('설명이 깁니다.');
        $('.add-content > .text').focus();
        return false;
    }
    if ($('.add-content > .in_1').val() == "") {
        aError('1차 입력 값이 입력되지 않았습니다.');
        $('.add-content > .in_1').focus();
        return false;
    } else if (ck_1.test($(".add-content > .in_1").val()) != false || ck_2.test($(".add-content > .in_1").val()) != false) {
        aError('입출력 값은 영문/숫자만 사용 가능합니다.');
        $('.add-content > .in_1').focus();
        return false;
    } else if ($('.add-content > .in_1').val().length <= 0) {
        aError('1차 입력 값이 짧습니다.');
        $('.add-content > .in_1').focus();
        return false;
    } else if ($('.add-content > .in_1').val().length >= 21) {
        aError('1차 입력 값이 깁니다.');
        $('.add-content > .in_1').focus();
        return false;
    }
    if ($('.add-content > .out_1').val() == "") {
        aError('1차 출력 값이 입력되지 않았습니다.');
        $('.add-content > .out_1').focus();
        return false;
    } else if (ck_1.test($(".add-content > .out_1").val()) != false || ck_2.test($(".add-content > .out_1").val()) != false) {
        aError('입출력 값은 영문/숫자만 사용 가능합니다.');
        $('.add-content > .out_1').focus();
        return false;
    } else if ($('.add-content > .out_1').val().length <= 0) {
        aError('1차 출력 값이 짧습니다.');
        $('.add-content > .out_1').focus();
        return false;
    } else if ($('.add-content > .out_1').val().length >= 21) {
        aError('1차 출력 값이 깁니다.');
        $('.add-content > .out_1').focus();
        return false;
    }
    if ($('.add-content > .in_2').val() == "") {
        aError('2차 입력 값이 입력되지 않았습니다.');
        $('.add-content > .in_2').focus();
        return false;
    } else if (ck_1.test($(".add-content > .in_2").val()) != false || ck_2.test($(".add-content > .in_2").val()) != false) {
        aError('입출력 값은 영문/숫자만 사용 가능합니다.');
        $('.add-content > .in_2').focus();
        return false;
    } else if ($('.add-content > .in_2').val().length <= 0) {
        aError('2차 입력 값이 짧습니다.');
        $('.add-content > .in_2').focus();
        return false;
    } else if ($('.add-content > .in_2').val().length >= 21) {
        aError('2차 입력 값이 깁니다.');
        $('.add-content > .in_2').focus();
        return false;
    }
    if ($('.add-content > .out_2').val() == "") {
        aError('2차 출력 값이 입력되지 않았습니다.');
        $('.add-content > .out_2').focus();
        return false;
    } else if (ck_1.test($(".add-content > .out_2").val()) != false || ck_2.test($(".add-content > .out_2").val()) != false) {
        aError('입출력 값은 영문/숫자만 사용 가능합니다.');
        $('.add-content > .out_2').focus();
        return false;
    } else if ($('.add-content > .out_2').val().length <= 0) {
        aError('2차 출력 값이 짧습니다.');
        $('.add-content > .out_2').focus();
        return false;
    } else if ($('.add-content > .out_2').val().length >= 21) {
        aError('2차 출력 값이 깁니다.');
        $('.add-content > .out_2').focus();
        return false;
    }
    if (add()) {
        aError('문제가 생성되지 않습니다.');
        $('.add-content > .title').focus();
        return false;
    }
    
    window.location.reload();
    return true;
}

function add(){
    var url = "http://codebyte.kro.kr/project/Add_Question";
    var ty = "";
    $.ajax({
        type: "POST",
        url: url,
        dataType: "json",
        data: {
            title: $('.add-content > .title').val(),
            text: $('.add-content > .text').val(),
            kind: $('.add-content > input[name="data_type"]:checked').val(),
            in_1: $('.add-content > .in_1').val(),
            out_1: $('.add-content > .out_1').val(),
            in_2: $('.add-content > .in_2').val(),
            out_2: $('.add-content > .out_2').val()
        },
        async: false,
        success: function (data) {
            ty = data.ty;
        },
        error: function (request, status, error) {
//                    alert('code:' + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error); //에러 상태에 대한 세부사항 출력
        }
    });
    return ty;
}

$('.open-add').click(function(){
    $('.add-modal').fadeIn();
});
$('.close-add').click(function(){
    $('.add-modal').fadeOut();
});

Custom_Question_List();
Point_Check();