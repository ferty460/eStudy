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
    let taskId = $('input[name="sort_id"]').val();
    let correctOrderUrl = '/sorting-tasks/' + taskId + '/correct-order';

    // Отправляем запрос на сервер для получения правильного порядка элементов
    $.getJSON(correctOrderUrl, function(correctOrder) {
        // Обработка кнопки "Ответить"
        $('.sortOk').click(function() {
            // Получаем элементы сортировки
            let sortItems = $('.sort-item1');

            // Получаем содержимое элементов сортировки
            let itemContents = sortItems.map(function() {
                return $(this).find('.sort_label').text();
            }).get();

            // Сравниваем порядок элементов на странице с правильным порядком
            let isInCorrectOrder = itemContents.every(function(content, index) {
                return content === correctOrder[index];
            });

            // Выводим результат в консоль
            if (isInCorrectOrder) {
                window.FlashMessage.success('Верно!');
                $('#sort_check').hide();
                $('#sort_result').append('<span class="correct">Верно!</span>');
            } else {
                window.FlashMessage.error('Неверно!');
                $('#sort_check').hide();
                $('#sort_result').append('<span class="wrong">Неверно!</span>');
            }
        });
    }).fail(function(jqXHR, textStatus, errorThrown) {
        console.error('Ошибка при получении правильного порядка элементов:', errorThrown);
    });
});