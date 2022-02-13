$.ajax({
    dataType: "json",
    data: {
        srvcYear:"",
        odr:"",
        cmpnyNm:"",
        consultStatCd:""
    },
    type: "POST",
    async: false,
    url: "https://www.nosa.or.kr:8443/wic/wcm/lsWicWcm.ajax",
    success: function (data) {
        console.log(data);
    },
    error: function (xhr, status, error) {
        console.log(status, error, xhr);
    },
    complete: function (data) {},
});
