function Login_Check_(){
    var url = "http://codebyte.kro.kr/project/Login_Check";
    $.ajax({
        type: "POST",
        url: url,
        dataType: "json",
        async: false,
        success: function (data) {
            if(data.ck==false){
                alert('로그인 후 이용가능한 서비스 입니다.');
                location.href='http://codebyte.kro.kr/project/';
            }
        },
        error: function (request, status, error) {
//                    alert('code:' + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error); //에러 상태에 대한 세부사항 출력
        }
    });
}

Login_Check_();