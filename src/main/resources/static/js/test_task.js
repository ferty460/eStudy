$(document).ready(function() {
    $(document).on('click', '.testOk   ', function(e) {
        e.preventDefault();

        let $this = $(this);

        let testId = parseInt($this.closest('.text-btns').find('input[name^="test_id"]').val());
        let answerInput = $this.closest('.theory-lesson-content').find('input[name^="test_answer"]:checked');
        let answerId = answerInput.val();
        let value = answerInput.closest('.test-item').find('.label').text();
        let flagInput = $this.closest('.theory-lesson-content').find('input[name^="test_flag"]');
        let flag = flagInput.val();

        $.ajax({
            url: '/practical/test/findTestItem',
            type: 'GET',
            data: { id: testId },
            success: function(data) {
                let resultDiv = $this.closest('.text-btns').find('div[id^="test_result"]');
                resultDiv.html('');
                resultDiv.empty();
                resultDiv.removeClass('wrong');
                if (data === parseInt(answerId)) {
                    window.FlashMessage.success('Верно!');
                    resultDiv.append('<span class="correct">' + value + '</span>');
                    if (flag === 'true') {
                        $.ajax({
                            url: '/practical/test/answer/create',
                            type: 'POST',
                            data: {userAnswer: answerId, test_id: testId},
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
                            url: '/practical/test/answer/update',
                            type: 'POST',
                            data: {userAnswer: answerId, test_id: testId},
                            success: function (response) {
                                console.log('Ответ успешно отправлен!');
                            },
                            error: function () {
                                console.log('Произошла ошибка при отправке ответа!');
                            }
                        });
                    }
                } else {
                    window.FlashMessage.error('Неверно!');
                    resultDiv.append('<span class="wrong">' + value + '</span>');
                    if (flag === 'true') {
                        $.ajax({
                            url: '/practical/test/answer/create',
                            type: 'POST',
                            data: {userAnswer: answerId, test_id: testId},
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
                            url: '/practical/test/answer/update',
                            type: 'POST',
                            data: {userAnswer: answerId, test_id: testId},
                            success: function (response) {
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
                console.log('Произошла ошибка при запросе test');
            }
        });
    });
});