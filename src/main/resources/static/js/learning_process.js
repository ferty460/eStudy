
$(document).ready(function() {
    let activeLesson = $('.learning-process-lesson-active');
    if (activeLesson.length > 0) {
        let activeLessonId = activeLesson.attr('id').replace('lesson-link', '');
        $('#content-lesson' + activeLessonId).show();
    }
    $('.learning-process-content-lesson').each(function() {
        var firstItem = $(this).find('.learning-process-item-first');
        if (firstItem.length > 0) {
            var contentBlockId = 'content' + firstItem.attr('data-index');
            var contentBlock = $('#' + contentBlockId);
            contentBlock.show();
        }
    });
    $('.learning-process-lesson').click(function() {
        let lessonId = $(this).attr('id').replace('lesson-link', '');
        $('.learning-process-lesson').removeClass('learning-process-lesson-active');
        $(this).addClass('learning-process-lesson-active');
        $('.learning-process-content-lesson').hide();
        $('#content-lesson' + lessonId).show();
    });
    $('[class*="learning-process-item"]').click(function() {
        $(this).closest('.learning-process-content-lesson').find('[class*="learning-process-item"]').removeClass('learning-process-item-first');
        $(this).addClass('learning-process-item-first');
        $(this).closest('.learning-process-content-lesson').find('.learning-process-content-block').hide();
        let contentBlockId = 'content' + $(this).attr('data-index');
        let contentBlock = $('#' + contentBlockId);
        contentBlock.show();
    });
});