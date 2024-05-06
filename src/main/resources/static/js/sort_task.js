$('.sort-elements1').sortable();
$('.sort-elements').sortable({
    update: function(event, ui) {
        // Обновление значения позиции для каждого элемента
        $('.sort-elements .sort-item').each(function(index) {
            let elementId = $(this).find('input[name="elementId"]').val();
            // Отправка AJAX-запроса на сервер для обновления позиции
            $.ajax({
                url: '/practical/sort/update-position',
                type: 'POST',
                data: {
                    elementId: elementId,
                    position: index
                },
                success: function(response) {
                    console.log(response);
                    location.reload();
                },
                error: function(xhr, status, error) {
                    alert('Произошла ошибка при запросе textTask');
                }
            });
        });
    }
});

$(document).ready(function() {

    $('.sortOk').click(function() {
        let $this = $(this);

        let taskId = $this.closest('.text-btns').find('input[name^="sort_id"]').val();
        let correctOrderUrl = '/sorting-tasks/' + taskId + '/correct-order';
        let resultDiv = $this.closest('.text-btns').find('div[id^="sort_result"]');
        let flagInput = $this.closest('.theory-lesson-content').find('input[name^="sort_flag"]');
        let flag = flagInput.val();
        let sortItems = $this.closest('.theory-lesson-content').find('.sort-item1');

        $.getJSON(correctOrderUrl, function(correctOrder) {

            let itemContents = sortItems.map(function() {
                return $(this).find('.sort_label').text();
            }).get();

            console.log(itemContents);

            let isInCorrectOrder = itemContents.every(function(content, index) {
                return content === correctOrder[index];
            });

            if (isInCorrectOrder) {
                window.FlashMessage.success('Верно!');
                resultDiv.html('');
                resultDiv.empty();
                resultDiv.removeClass('wrong');
                resultDiv.append('<span class="correct">' + itemContents + '</span>');
                if (flag === 'true') {
                    $.ajax({
                        url: '/practical/sort/answer/create',
                        type: 'POST',
                        data: {userAnswer: JSON.stringify(itemContents), sort_id: taskId},
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
                        url: '/practical/sort/answer/update',
                        type: 'POST',
                        data: {userAnswer: JSON.stringify(itemContents), sort_id: taskId},
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
                resultDiv.html('');
                resultDiv.empty();
                resultDiv.removeClass('wrong');
                resultDiv.append('<span class="wrong">' + itemContents + '</span>');
                if (flag === 'true') {
                    $.ajax({
                        url: '/practical/sort/answer/create',
                        type: 'POST',
                        data: {userAnswer: JSON.stringify(itemContents), sort_id: taskId},
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
                        url: '/practical/sort/answer/update',
                        type: 'POST',
                        data: {userAnswer: JSON.stringify(itemContents), sort_id: taskId},
                        success: function (response) {
                            console.log('Ответ успешно отправлен!');
                        },
                        error: function () {
                            console.log('Произошла ошибка при отправке ответа!');
                        }
                    });
                }
            }
        }).fail(function(jqXHR, textStatus, errorThrown) {
            console.error('Ошибка при получении правильного порядка элементов:', errorThrown);
        });
    });
});