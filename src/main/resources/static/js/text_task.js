$(document).ready(function() {
    $('#text_customOk').click(function(e) {
        e.preventDefault();

        let id = $('input[name="text_id"]').val();
        let answer = $('input[name="text_answer"]').val();

        $.ajax({
            url: '/practical/text/findTextTask',
            type: 'GET',
            data: { id: id },
            success: function(data) {
                if (data === answer) {
                    window.FlashMessage.success('Верно!');
                    $('#text_customOk').hide();
                    $('#text_result').append('<span class="correct">' + data + '</span>');
                } else {
                    window.FlashMessage.error('Неверно!');
                    $('#text_customOk').hide();
                    $('#text_result').append('<span class="wrong">' + answer + '</span> | <span class="correct">' + data + '</span>');
                }
            },
            error: function() {
                alert('Произошла ошибка при запросе textTask');
            }
        });
    });
});