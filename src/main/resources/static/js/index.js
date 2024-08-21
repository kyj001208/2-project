$(document).ready(function() {
    // 로드할 함수들
    loadRecommendedList();
    loadNewList();
    loadreasonablyProduct();
    loadTodayProduct('23');
   
    // 배너 슬라이드
    let currentIndex = 0;
    const totalBanners = 3; // 총 배너 개수
    const banners = document.querySelectorAll('.banner');

    function moveToBanner(index) {
        if (banners.length > 0) {
            banners[currentIndex].classList.remove('active');
            currentIndex = (index + totalBanners) % totalBanners;
            banners[currentIndex].classList.add('active');
        }
    }

    function autoSlide() {
        moveToBanner(currentIndex + 1);
    }

    if (banners.length > 0) {
        setInterval(autoSlide, 3000);
        moveToBanner(currentIndex);
    }

    // 타이머 코드
function initTimer() {
    const timerElement = document.getElementById('timer');

    if (!timerElement) {
        console.error("타이머 요소를 찾을 수 없습니다.");
        return;
    }

    const now = new Date();
    const start = new Date();
    start.setHours(10, 0, 0, 0); // 오전 10시로 설정
    const end = new Date(start.getTime() + 24 * 60 * 60 * 1000); // 24시간 후

    if (now >= start && now <= end) {
        startTimer();
    } else if (now < start) {
        const delay = start - now;
        setTimeout(startTimer, delay);
    } else {
        timerElement.textContent = "타이머는 이미 종료되었습니다.";
    }

    function startTimer() {
        const interval = setInterval(() => {
            const now = new Date();
            const timeLeft = end - now;

            if (timeLeft <= 0) {
                clearInterval(interval);
                timerElement.textContent = "타이머 종료";
            } else {
                const hours = Math.floor((timeLeft / (1000 * 60 * 60)) % 24);
                const minutes = Math.floor((timeLeft / (1000 * 60)) % 60);
                const seconds = Math.floor((timeLeft / 1000) % 60);

                timerElement.textContent = 
                    `${String(hours).padStart(2, '0')}:${String(minutes).padStart(2, '0')}:${String(seconds).padStart(2, '0')}`;
            }
        }, 1000);
    }
}

    //상품리스트
    function loadRecommendedList() {
        /*console.log("Loading recommended products...");*/
        $.get(`/public/index/recommendedProduct`, function(data) {
            $("#col-5").html(data);
        });
    }

    function loadNewList() {
        $.get(`/public/index/newProduct`, function(data) {
            $("#col-7").html(data);
        });
    }

    function loadreasonablyProduct() {
        $.get(`/public/index/reasonablyProduct`, function(data) {
            $("#col-8").html(data);
        });
    }

    function loadTodayProduct(productNo) {
        $.get(`/public/index/today/${productNo}`, function(data) {
            $("#col-6").html(data);
            
            // 동적 내용 삽입 후 타이머 초기화
            initTimer();
        });
    }
});
