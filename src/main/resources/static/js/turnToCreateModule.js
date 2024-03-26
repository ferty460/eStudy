document.addEventListener('DOMContentLoaded', function() {
    // Скрываем секции изначально
    document.getElementById('newModule').style.display = 'none';
    document.querySelector('.bg').style.display = 'none';

    // Обработчик клика для toLearningList
    document.getElementById('toNewModule').addEventListener('click', function() {
        // Показываем секцию myLearning
        document.getElementById('newModule').style.display = 'block';
        // Делаем фон темнее
        document.querySelector('.bg').style.display = 'block';
    });

    // Обработчик клика вне секций
    document.querySelector('.bg').addEventListener('click', function() {
        // Показываем секцию myLearning
        document.getElementById('newModule').style.display = 'none';
        // Делаем фон темнее
        document.querySelector('.bg').style.display = 'none';
    });
});