$(document).ready(function() {
    $('.chapters-list a').click(function(e) {
        e.preventDefault();
        let chapterId = $(this).attr('href').substring(1);

        // Удаляем класс active-chapter у всех элементов списка
        $('.chapters-list li').removeClass('active-chapter');

        // Добавляем класс active-chapter к элементу списка, соответствующему открытому разделу
        $(this).parent().addClass('active-chapter');

        // Скрываем все блоки разделов, кроме того, на который указывает ссылка, и показываем его
        $('.theory-lesson-main').hide();
        $('#' + chapterId).show();
    });
});