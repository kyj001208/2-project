/**
 * 
 */
function faqFetchApi(url){
	return 	fetch(`${url}`)
				.then(response=>response.text())
				.then(data=>{
					const divTag=document.querySelector("#list-data-area");
					divTag.innerHTML=data;
					listEvent();
				})
				.catch(error => {
					console.log('Error fetching FAQ:', error);
				});
}
function listEvent(){
	const titles=document.querySelectorAll('.faq-list-wrap>.faq-list>dl dt');
    titles.forEach(dtTag => {
        dtTag.addEventListener('click', function() {
            
            // 모든 dt의 active 클래스를 제거하고 content를 숨김
            titles.forEach(dt => {
                if (dt !== this) {
                    dt.classList.remove('active');
                }
            });

            // 클릭된 dt에 active 클래스를 추가하고 content를 토글함
            this.classList.toggle('active');
           
        });
    });
}