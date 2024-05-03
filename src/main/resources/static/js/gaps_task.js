$(document).ready(function() {
    $("#gaps_check").click(function(e) {
        e.preventDefault();

        let gaps = {};
        $(".gaps-bar1.gaps-input1").each(function() {
            gaps[this.id] = $(this).val();
        });

        $.ajax({
            type: "POST",
            url: "/check-gaps",
            data: JSON.stringify(gaps),
            contentType: "application/json",
            success: function(response) {
                if (response === "Да") {
                    window.FlashMessage.success('Верно!');
                    $('#gaps_check').hide();
                    $('#gaps_result').append('<span class="correct">Верно!</span>');
                }
                if (response === "Нет") {
                    window.FlashMessage.error('Неверно!');
                    $('#gaps_check').hide();
                    $('#gaps_result').append('<span class="wrong">Неверно!</span>');
                }
            },
            error: function(xhr, status, error) {
                console.error(error);
            }
        });
    });
});