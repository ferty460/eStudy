$(document).ready(function() {
    $(".gapsOk").click(function(e) {
        e.preventDefault();

        let $this = $(this);

        let taskId = $this.closest('.text-btns').find('input[name^="gaps_id"]').val();
        let result = $this.closest('.theory-lesson-content').find('div[id^="gaps_result"]');
        let flag = $this.closest('.theory-lesson-content').find('input[name^="gaps_flag"]').val();

        let gaps = {};
        $this.closest('.theory-lesson-content').find(".gaps-bar1.gaps-input1").each(function() {
            gaps[this.id] = $(this).val();
        });
        let values = Object.values(gaps);
        let valuesString = values.join(', ');

        $.ajax({
            type: "POST",
            url: "/check-gaps",
            data: JSON.stringify(gaps),
            contentType: "application/json",
            success: function(response) {
                if (response === "Да") {
                    window.FlashMessage.success('Верно!');
                    result.html('');
                    result.empty();
                    result.removeClass('wrong');
                    result.append('<span class="correct">' + valuesString + '</span>');
                    if (flag === 'true') {
                        $.ajax({
                            url: '/practical/gaps/answer/create',
                            type: 'POST',
                            data: JSON.stringify({
                                userAnswer: gaps,
                                gapsId: taskId
                            }),
                            contentType: "application/json",
                            success: function (response) {
                                console.log('Ответ успешно отправлен!');
                            },
                            error: function () {
                                console.log('Произошла ошибка при отправке ответа!');
                            }
                        });
                    } else if (flag === 'isTried') {
                        $.ajax({
                            url: '/practical/gaps/answer/update',
                            type: 'POST',
                            data: JSON.stringify({
                                userAnswer: gaps,
                                gapsId: taskId
                            }),
                            contentType: "application/json",
                            success: function (response) {
                                console.log('Ответ успешно отправлен!');
                            },
                            error: function () {
                                console.log('Произошла ошибка при отправке ответа!');
                            }
                        });
                    }
                }
                if (response === "Нет") {
                    window.FlashMessage.error('Неверно!');
                    result.html('');
                    result.empty();
                    result.removeClass('wrong');
                    result.append('<span class="wrong">' + valuesString + '</span>');
                    if (flag === 'true') {
                        $.ajax({
                            url: '/practical/gaps/answer/create',
                            type: 'POST',
                            data: JSON.stringify({
                                userAnswer: gaps,
                                gapsId: taskId
                            }),
                            contentType: "application/json",
                            success: function (response) {
                                console.log('Ответ успешно отправлен!');
                            },
                            error: function () {
                                console.log('Произошла ошибка при отправке ответа!');
                            }
                        });
                    } else if (flag === 'isTried') {
                        $.ajax({
                            url: '/practical/gaps/answer/update',
                            type: 'POST',
                            data: JSON.stringify({
                                userAnswer: gaps,
                                gapsId: taskId
                            }),
                            contentType: "application/json",
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
            error: function(xhr, status, error) {
                console.error(error);
            }
        });
    });
});