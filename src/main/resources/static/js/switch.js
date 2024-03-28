document.addEventListener('DOMContentLoaded', function() {
    // Скрываем секции изначально
    let elementsToHide = document.querySelectorAll('.my-learning, .my-courses, .bg, .my-favorites');
    elementsToHide.forEach(function(element) {
        element.style.display = 'none';
    });

    // Обработчик клика для toLearningList
    document.getElementById('toLearningList').addEventListener('click', function() {
        // Показываем секцию myLearning
        document.querySelector('.my-learning').style.display = 'block';
        // Скрываем секцию myCourses
        document.querySelector('.my-courses').style.display = 'none';
        document.querySelector('.my-favorites').style.display = 'none';
        // Делаем фон темнее
        document.querySelector('.bg').style.display = 'block';
    });

    // Обработчик клика для toTeachingList
    document.getElementById('toTeachingList').addEventListener('click', function() {
        // Показываем секцию myCourses
        document.querySelector('.my-courses').style.display = 'block';
        // Скрываем секцию myLearning
        document.querySelector('.my-learning').style.display = 'none';
        document.querySelector('.my-favorites').style.display = 'none';
        // Делаем фон темнее
        document.querySelector('.bg').style.display = 'block';
    });

    // Обработчик клика для toTeachingList
    document.getElementById('toFavoriteList').addEventListener('click', function() {
        // Показываем секцию myCourses
        document.querySelector('.my-favorites').style.display = 'block';
        // Скрываем секцию myLearning
        document.querySelector('.my-learning').style.display = 'none';
        document.querySelector('.my-courses').style.display = 'none';
        // Делаем фон темнее
        document.querySelector('.bg').style.display = 'block';
    });

    // Обработчик клика вне секций
    document.addEventListener('click', function(event) {
        let target = event.target;
        let isInsideRolesItem = target.closest('.roles__item');
        let isInsideMySections = target.closest('.my-learning, .my-courses');
        if (!isInsideRolesItem && !isInsideMySections) {
            // Скрываем секции
            document.querySelector('.my-learning').style.display = 'none';
            document.querySelector('.my-courses').style.display = 'none';
            document.querySelector('.my-favorites').style.display = 'none';
            // Убираем фон
            document.querySelector('.bg').style.display = 'none';
        }
    });
});