$(document).ready(function() {
    loadRecommendedList();
	loadNewList();
	loadreasonablyProduct();
	loadTodayProduct('123');
	
});

document.addEventListener('DOMContentLoaded', function() {
    let currentIndex = 0;
    const totalBanners = 3; // 총 배너 개수
    const banners = document.querySelectorAll('.banner');
    
    function moveToBanner(index) {
        // 현재 활성 배너의 클래스를 제거
        banners[currentIndex].classList.remove('active');
        
        // 새 배너의 클래스를 추가
        currentIndex = (index + totalBanners) % totalBanners; // 범위 내 인덱스 조정
        banners[currentIndex].classList.add('active');
    }

    // 자동 배너 전환 함수
    function autoSlide() {
        moveToBanner(currentIndex + 1);
    }

    // 3초마다 배너 자동 전환 (3000ms = 3초)
    setInterval(autoSlide, 3000);


    // 초기 배너 표시
    moveToBanner(currentIndex);
});
////////////////////////**/ *///////////////////////
function loadRecommendedList() {
	console.log("Loading recommended products..."); // 디버깅용 로그
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
///////////////오늘만이가격/////////////////////////////////////////
function loadTodayProduct(productNo) {
	
    $.get(`/public/index/today/${productNo}`, function(data) {
        $("#col-6").html(data);
    });
}
        

/**/
async function fetchTimerInfo() {
           try {
               const response = await fetch('/public/timer-info');
               const timerData = await response.json();

               // 타이머 정보 업데이트
               document.getElementById('timer').textContent = 
                   `${String(timerData.hoursLeft).padStart(2, '0')}:${String(timerData.minutesLeft).padStart(2, '0')}:${String(timerData.secondsLeft).padStart(2, '0')}`;
           } catch (error) {
               console.error('Error fetching timer info:', error);
           }
       }

       // 초기 타이머 정보 업데이트
       fetchTimerInfo();

       // 1초마다 타이머 정보 업데이트
       setInterval(fetchTimerInfo, 1000);