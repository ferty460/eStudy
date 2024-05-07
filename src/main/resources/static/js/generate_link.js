$(document).ready(function() {
    $('#generate').click(function(e) {
        e.preventDefault();
        let $this = $(this);
        let block = $this.closest('.news-inputs__block');

        let courseId = block.find('input[name="course_id"]').val();

        $.ajax({
            url: '/generateAccessLink',
            type: 'POST',
            data: { courseId: courseId },
            success: function(response) {
                block.find('input[name="value"]').val(response);
            },
            error: function(xhr, status, error) {
                console.error(error);
            }
        });
    });
});