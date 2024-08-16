//버튼 2를 누르면 transform='translate(-100vw)
    
document.addEventListener('DOMContentLoaded', function() {
	document.querySelector('.btn2').addEventListener('click', function() {
	document.querySelector('.banner-container').style.transform = 'translate(-100vw)';
    });

document.querySelector('.btn3').addEventListener('click', function() {
    document.querySelector('.banner-container').style.transform = 'translate(-200vw)';
    });
    document.querySelector('.btn1').addEventListener('click', function() {
	document.querySelector('.banner-container').style.transform = 'translate(0)';
    });
});

document.addEventListener('DOMContentLoaded', function() {
    let currentIndex = 0;
    const totalBanners = 3; // 총 배너 개수
    const container = document.querySelector('.banner-container');

    function moveToBanner(index) {
        if (index >= totalBanners) {
            currentIndex = 0; // 마지막 배너 후 첫 배너로 돌아가기
        } else {
            currentIndex = index;
        }
        container.style.transform = `translate(-${100 * currentIndex}vw)`;
    }

    // 자동 배너 전환 함수
    function autoSlide() {
        currentIndex++;
        moveToBanner(currentIndex);
    }

    // 5초마다 배너 자동 전환 (5000ms = 5초)
    setInterval(autoSlide, 3000);

    document.querySelector('.btn1').addEventListener('click', function() {
        moveToBanner(0); // 첫 번째 배너로 이동
    });

    document.querySelector('.btn2').addEventListener('click', function() {
        moveToBanner(1); // 두 번째 배너로 이동
    });

    document.querySelector('.btn3').addEventListener('click', function() {
        moveToBanner(2); // 세 번째 배너로 이동
    });
});


        

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