var main = {
    init : function () {
        var _this = this;

        // 1. '저장' 페이지이므로 #btn-save 이벤트만 남깁니다.
        $('#btn-save').on('click', function () {
            _this.save();
        });
    },
    save : function () {
        // 2. DTO에 맞게 데이터 수집
        var data = {
            content: $('#content').val(),
            scope: $('input[name="scope"]:checked').val(),
            memoDate: $('#memoDate').val()
        };

        // 3. 날짜 유효성 검사
        if (!data.memoDate) {
            alert('날짜를 입력해주세요.');
            return;
        }

        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        $.ajax({
            type: 'POST',
            url: '/home/memos',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('메모가 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            // 5. GlobalExceptionHandler의 에러 메시지 표시
            alert(error.responseJSON.message || JSON.stringify(error));
        });
    }

    // 6. 'update'와 'delete' 기능은 이 파일에서 제거합니다.
};

main.init();