$(document).ready(function() {
    $('.textOk').click(function(e) {
        e.preventDefault();

        let $this = $(this);

        let id = $(this).closest('.theory-lesson-content').find('input[name^="text_id"]').val();
        let answer = $(this).closest('.theory-lesson-content').find('input[name^="textUserAnswer"]').val();
        let flagInput = $this.closest('.theory-lesson-content').find('input[name^="text_flag"]');
        let flag = flagInput.val();

        $.ajax({
            url: '/practical/text/findTextTask',
            type: 'GET',
            data: { id: id },
            success: function(data) {
                let $result = $this.closest('.theory-lesson-content').find('div[id^="text_result"]');
                if (data === answer) {
                    $result.html('');
                    $result.empty();
                    $result.removeClass('wrong');
                    window.FlashMessage.success('Верно!');
                    $result.parent().removeClass('wrong');
                    $result.append('<span class="correct">' + data + '</span>');
                    if (flag === 'true') {
                        $.ajax({
                            url: '/practical/text/answer/create',
                            type: 'POST',
                            data: {userAnswer: answer, text_id: id},
                            success: function (response) {
                                console.log('Ответ успешно отправлен!');
                            },
                            error: function () {
                                console.log('Произошла ошибка при отправке ответа!');
                            }
                        });
                        flagInput.val('isTried');
                    } else if (flag === 'isTried') {
                        $.ajax({
                            url: '/practical/text/answer/update',
                            type: 'POST',
                            data: {userAnswer: answer, text_id: id},
                            success: function (response) {
                                console.log('Ответ успешно отправлен!');
                            },
                            error: function () {
                                console.log('Произошла ошибка при отправке ответа!');
                            }
                        });
                    }
                } else {
                    $result.html('');
                    $result.empty();
                    $result.removeClass('wrong');
                    window.FlashMessage.error('Неверно!');
                    $result.parent().removeClass('correct');
                    $result.append('<span class="wrong">' + answer + '</span>');
                    if (flag === 'true') {
                        $.ajax({
                            url: '/practical/text/answer/create',
                            type: 'POST',
                            data: {userAnswer: answer, text_id: id},
                            success: function () {
                                console.log('Ответ успешно отправлен!');
                            },
                            error: function () {
                                console.log('Произошла ошибка при отправке ответа!');
                            }
                        });
                        flagInput.val('isTried');
                    } else if (flag === 'isTried') {
                        $.ajax({
                            url: '/practical/text/answer/update',
                            type: 'POST',
                            data: {userAnswer: answer, text_id: id},
                            success: function () {
                                console.log('Ответ успешно отправлен!');
                            },
                            error: function () {
                                console.log('Произошла ошибка при отправке ответа!');
                            }
                        });
                    }
                }
            },
            error: function() {
                alert('Произошла ошибка при запросе textTask');
            }
        });
    });
});