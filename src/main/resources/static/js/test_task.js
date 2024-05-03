$(document).ready(function() {
    if ($('input[name="flag"]').val() === 'false') {
        document.getElementById('test_update').submit();
    }
    $('#test_customOk').click(function(e) {
        e.preventDefault();

        let id = $('input[name="test_id"]').val();
        let answer = $('input[name="test_answer"]:checked').val();
        let value = $('input[name="test_answer"]:checked').closest('.test-item').find('.label').text();

        $.ajax({
            url: '/practical/test/findTestItem',
            type: 'GET',
            data: { id: id },
            success: function(data) {
                if (data === parseInt(answer)) {
                    window.FlashMessage.success('Верно!');
                    $('#test_customOk').hide();
                    $('#test_result').append('<span class="correct">' + value + '</span>');
                } else {
                    window.FlashMessage.error('Неверно!');
                    $('#test_customOk').hide();
                    $('#test_result').append('<span class="wrong">' + value + '</span> | <span class="correct">' + $('#answer2' + data).find('.label').text() + '</span>');
                }
            },
            error: function() {
                alert('Произошла ошибка при запросе test');
            }
        });
    });
});