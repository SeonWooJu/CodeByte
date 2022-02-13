var ch = true;
//로그인 체크
function Login_Check(){
    var url = "http://codebyte.kro.kr/project/Login_Check";
    $.ajax({
        type: "POST",
        url: url,
        dataType: "json",
        async: false,
        success: function (data) {
            if(data.ck==true){
                $('.login-out, .open-info').show();
                $('.open-login, .open-join').hide();
            }else if(data.ck==false){
                $('.open-login, .open-join').show();
                $('.login-out, .open-info').hide();
            }
        },
        error: function (request, status, error) {
//                    alert('code:' + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error); //에러 상태에 대한 세부사항 출력
        }
    });
}
//로그인 체크

//로그아웃
$('.login-out').click(function(){
    var url = "http://codebyte.kro.kr/project/Login_Out";
    $.ajax({
        type: "POST",
        url: url,
        dataType: "json",
        async: false,
        success: function (data) {
            if(data.ck==true){
                $('.login-out, .open-info').show();
                $('.open-login, .open-join').hide();
            }else if(data.ck==false){
                $('.open-login, .open-join').show();
                $('.login-out, .open-info').hide();
            }
        },
        error: function (request, status, error) {
//                    alert('code:' + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error); //에러 상태에 대한 세부사항 출력
        }
    });
    window.location.reload();
});
//로그아웃

//로그인Erorr 출력처리
function lError(str) {
    $('.login-content > #n > b').text(str);
    $('.login-content > #n > b').css({
        'color': 'red'
    });
}
//회원가입 출력처리

//로그인 Erorr 출력처리
function jError(str) {
    $('.join-content > #n > b').text(str);
    $('.join-content > #n > b').css({
        'color': 'red'
    });
}
//회원가입 Erorr 출력처리

//login-modal
$('.open-login').click(function () {
    $('.login-modal').fadeIn();
});
$('.close-login').click(function () {
    $('.login-modal').fadeOut();
});
//login-modal

//join-modal
$('.open-join').click(function () {
    $('.join-modal').fadeIn();
});
$('.close-join').click(function () {
    $('.join-modal').fadeOut();
});
//join-modal

//info-modal
$('.open-info').click(function(){
    $('.info-modal').fadeIn();
    var url = "http://codebyte.kro.kr/project/Info_Values";
    $.ajax({
        type: "POST",
        url: url,
        dataType: "json",
        async: false,
        success: function (data) {
            $('.info-content > .name').text('이름:' + data.name);
            $('.info-content > .id').text('아이디:' + data.id);
            $('.info-content > .email').text('이메일:' + data.email);
        },
        error: function (request, status, error) {
//                    alert('code:' + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error); //에러 상태에 대한 세부사항 출력
        }
    });
});
$('.close-info').click(function(){
    $('.info-modal').fadeOut();
});
//info-modal

//로그인 데이터 체크
function Login_Check_Value() {

    if ($('.login-content > #Id').val() == "") {
        lError('아이디가 입력되지 않았습니다.');
        $('.login-content > #Id').focus();
        return false;
    } else if ($('.login-content > #Id').val().length <= 4) {
        lError('아이디가 짧습니다.');
        $('.login-content > #Id').focus();
        return false;
    } else if ($('.login-content > #Id').val().length >= 16) {
        lError('아이디가 깁니다.');
        $('.login-content > #Id').focus();
        return false;
    }
    if ($(".login-content > #Pw").val() == "") {
        lError('비밀번호가 입력되지 않았습니다.');
        $('.login-content > #Pw').focus();
        return false;
    } else if ($('.login-content > #Pw').val().length <= 4) {
        lError('비밀번호가 짧습니다.');
        $('.login-content > #Pw').focus();
        return false;
    } else if ($('.join-content > #Pw').val().length >= 21) {
        lError('비밀번호가 깁니다.');
        $('.join-content > #Pw').focus();
        return false;
    }
    if(Login()){
        lError('아이디/비밀번호가 일치하지 않습니다.');
        return false;
    }
    
    window.location.reload();
    return true;
}
//로그인 데이터 체크

//회원가입 데이터 체크
function Join_Check_Value() {
    var IdPw_1 = /[ㄱ-ㅎㅏ-ㅣ가-힣.\-]/;
    var IdPw_2 = /[\{\}\[\]\/?.,;:|\)*~`!^\-+<>@\#$%&\\\=\(\'\"]/;
    var IdPw_3 = /[0-9]/;
    var IdPw_4 = /[a-zA-Z]/;
    var Email = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

    if ($('.join-content > #Id').val() == "") {
        jError('아이디가 입력되지 않았습니다.');
        $('.join-content > #Id').focus();
        return false;
    } else if (IdPw_1.test($(".join-content > #Id").val()) != false || IdPw_2.test($(".join-content > #Id").val()) != false) {
        jError('아이디는 영문과 숫자 조합만 가능합니다.');
        $('.join-content > #Id').focus();
        return false;
    } else if ((IdPw_3.test($(".join-content > #Id").val()) != true && IdPw_4.test($(".join-content > #Id").val()) != false)||(IdPw_3.test($(".join-content > #Id").val()) != false && IdPw_4.test($(".join-content > #Id").val()) != true)) {
        jError('아이디는 영문과 숫자 조합만 가능합니다.');
        $('.join-content > #Id').focus();
        return false;
    } else if ($('.join-content > #Id').val().length <= 4) {
        jError('아이디가 짧습니다.');
        $('.join-content > #Id').focus();
        return false;
    } else if ($('.join-content > #Id').val().length >= 16) {
        jError('아이디가 깁니다.');
        $('.join-content > #Id').focus();
        return false;
    }
    if ($(".join-content > #Pw").val() == "") {
        jError('비밀번호가 입력되지 않았습니다.');
        $('.login-content > #Pw').focus();
        return false;
    } else if (IdPw_1.test($(".join-content > #Pw").val()) != false || IdPw_2.test($(".join-content > #Pw").val()) != false) {
        jError('비밀번호는 영문과 숫자 조합만 가능합니다.');
        $('.join-content > #Pw').focus();
        return false;
    } else if ((IdPw_3.test($(".join-content > #Pw").val()) != true && IdPw_4.test($(".join-content > #Pw").val()) != false)||(IdPw_3.test($(".join-content > #Pw").val()) != false && IdPw_4.test($(".join-content > #Pw").val()) != true)) {
        jError('비밀번호는 영문과 숫자 조합만 가능합니다.');
        $('.join-content > #Pw').focus();
        return false;
    } else if ($('.join-content > #Pw').val().length <= 4) {
        jError('비밀번호가 짧습니다.');
        $('.join-content > #Pw').focus();
        return false;
    } else if ($('.join-content > #Pw').val().length >= 21) {
        jError('비밀번호가 깁니다.');
        $('.join-content > #Pw').focus();
        return false;
    }
    if ($(".join-content > #Pw_Ck").val() == "") {
        jError('비밀번호 확인이 입력되지 않았습니다.');
        $('.join-content > #Pw_Ck').focus();
        return false;
    } else if ($(".join-content > #Pw").val() != $(".join-content > #Pw_Ck").val()) {
        jError('비밀번호와 비밀번호 확인이 다릅니다.');
        $(".join-content > #Pw_Ck").focus();
        return false;
    }
    if ($('.join-content > #Name').val() == "") {
        jError('이름이 입력되지 않았습니다.');
        $('.join-content > #Name').focus();
        return false;
    } else if ($('.join-content > #Name').val().length <= 1) {
        jError('이름이 짧습니다.');
        $('.join-content > #Name').focus();
        return false;
    } else if ($('.join-content > #Name').val().length >= 11) {
        jError('이름이 깁니다.');
        $('.join-content > #Name').focus();
        return false;
    }
    if ($(".join-content > #Email").val() == "") {
        jError('이메일이 입력되지 않았습니다.');
        $(".join-content > #Email").focus();
        return false;
    } else if (Email.test($(".join-content > #Email").val()) != true) {
        jError('올바른 이메일 형식이 아닙니다.');
        $(".join-content > #Email").focus();
        return false;
    }
    if(ch){
        jError('아이디가 중복확인 되지 않았습니다.');
        return false;
    }
    if(Join()){
        jError('데이터 형식을 지켜주시기 바랍니다.');
        return false;
    }
    alert('회원가입이 완료되었습니다.');
    window.location.reload();
    return true;
}
//회원가입 데이터 체크

//로그인 데이터 전송
function Login() {
    var url = "http://codebyte.kro.kr/project/Index_Login";
    var tf = true;
    $.ajax({
        type: "POST",
        url: url,
        dataType: "json",
        data: {
            id: $('.login-content > #Id').val(),
            pw: $('.login-content > #Pw').val()
        },
        async: false,
        success: function (data) {
            tf = data.tf;
        },
        error: function (request, status, error) {
//                    alert('code:' + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error); //에러 상태에 대한 세부사항 출력
        }
    });
    return tf;
}
//로그인 데이터 전송

//회원정보 데이터 전송
function Join() {
    var url = "http://codebyte.kro.kr/project/Index_Join";
    var tf = true;
    $.ajax({
        type: "POST",
        url: url,
        dataType: "json",
        data: {
            id: $('.join-content > #Id').val(),
            pw: $('.join-content > #Pw').val(),
            name: $('.join-content > #Name').val(),
            email: $('.join-content > #Email').val()
        },
        async: false,
        success: function (data) {
            tf = data.tf;
        },
        error: function (request, status, error) {
//                    alert('code:' + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error); //에러 상태에 대한 세부사항 출력
        }
    });
    return tf;
}
//회원정보 데이터 전송

//아이디 중복확인
function Id_Ck() {
    var url = "http://codebyte.kro.kr/project/Index_Idck";
    $.ajax({
        type: "POST",
        url: url,
        dataType: "json",
        data: {
            Id: $('.join-content > #Id').val()
        },
        success: function (data) {
            if (data.Id_Ck == 0) {
                $('.join-content > #n > b').text('사용가능한 아이디입니다.');
                $('.join-content > #n > b').css({
                    'color': 'blue'
                });
                ch = false;
            } else if (data.Id_Ck == 1) {
                jError('사용불가능한 아이디입니다.');
                ch = true;
            }
        },
        error: function (request, status, error) {
            //        alert('code:' + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error); //에러 상태에 대한 세부사항 출력
        }
    });
}
//아이디 중복확인

//아이디 변경시 중복확인 경고
$(".join-content > #Id").on("propertychange change keyup paste input", function(){
	ch = true;
});
//아이디 변경시 중복확인 경고
Login_Check();