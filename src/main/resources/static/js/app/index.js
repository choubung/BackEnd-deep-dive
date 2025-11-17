var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });

        $('#btn-logout').on('click', function (e) {
            e.preventDefault(); // <a> 태그의 링크 이동을 막음
            _this.logout();
        });
    },
    save : function () {
        // 1. [수정] DTO에 맞게 데이터 수집
        var data = {
            content: $('#content').val(),
            scope: $('input[name="scope"]:checked').val(),
            memoDate: $('#memoDate').val()
        };

        // 1-1. 날짜 유효성 검사
        if (!data.memoDate) {
            alert('날짜를 입력해주세요.');
            return;
        }

        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        $.ajax({
            type: 'POST',
            url: '/home/memos', // 2. [수정] MemoController URL
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
            beforeSend : function(xhr) {
                xhr.setRequestHeader(header, token);
            }
        }).done(function() {
            alert('메모가 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            // 3. [수정] 에러 메시지 개선 (GlobalExceptionHandler와 연동)
            alert(error.responseJSON.message || JSON.stringify(error));
        });
    },
    update : function () {
        // 4. [수정] DTO에 맞게 데이터 수집 (MemoUpdateRequestDto)
        var data = {
            content: $('#content').val(),
            scope: $('input[name="scope"]:checked').val(),
            memoDate: $('#memoDate').val()
        };

        // 4-1. 날짜 유효성 검사
        if (!data.memoDate) {
            alert('날짜를 입력해주세요.');
            return;
        }

        var id = $('#id').val();
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        $.ajax({
            type: 'PUT',
            url: '/home/memos/'+id, // 5. [수정] MemoController URL
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
            beforeSend : function(xhr) {
                xhr.setRequestHeader(header, token);
            }
        }).done(function() {
            alert('메모가 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(error.responseJSON.message || JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        $.ajax({
            type: 'DELETE',
            url: '/home/memos/'+id, // 6. [수정] MemoController URL
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            beforeSend : function(xhr) { // 💡 (여기도)
                xhr.setRequestHeader(header, token);
            }
        }).done(function() {
            alert('메모가 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(error.responseJSON.message || JSON.stringify(error));
        });
    },

    logout : function () {
        // 9. <meta> 태그에서 CSRF 파라미터 이름과 토큰 값을 읽어옴
        var token = $("meta[name='_csrf']").attr("content");
        var paramName = $("meta[name='_csrf_parameter']").attr("content");

        // 10. 동적으로 <form>을 생성
        var $form = $('<form></form>');
        $form.attr('action', '/logout');
        $form.attr('method', 'POST');

        // 11. 폼에 CSRF 토큰(hidden input)을 추가
        $form.append($('<input/>', {
            type: 'hidden',
            name: paramName,
            value: token
        }));

        // 12. 폼을 body에 추가하고 즉시 submit
        $form.appendTo('body');
        $form.submit();
    }
};

main.init();