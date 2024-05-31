$(document).ready(function() {
    $(".theme-block").click(function() {
        event.preventDefault();
        $.ajax({
            url: '/toggle-theme',
            type: 'POST',
            success: function() {
                var newTheme = $('#theme').attr('href') === '/css/light.css' ? 'dark' : 'light';
                var newStylesheet = '/css/' + newTheme + '.css';
                $('#theme').attr('href', newStylesheet);
            },
            error: function() {
                console.log('Произошла ошибка при запросе темы');
            }
        });
    });
});