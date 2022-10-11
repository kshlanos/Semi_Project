const totalTime = document.querySelector('#totalTime').value;

const hour = Math.floor(totalTime / 3600);
const minutes = Math.floor((totalTime / 60) % 60);

console.log(hour);

document.querySelector('#stopwatch-hour').value = hour;
document.querySelector('#stopwatch-minute').value = minutes;

$(document).on("keyup", "input[name^=stopwatchHour]", function() {
    var val= $(this).val();

    if(val.replace(/[0-9]/g, "").length > 0) {
        alert("최소 0시간부터 최대 24시간까지 입력가능합니다.");
        $(this).val('');
    }

    if(val < 0 || val > 24) {
        alert("최소 0시간부터 최대 24시간까지 입력가능합니다.");
        $(this).val('');
    }
});

$(document).on("keyup", "input[name^=stopwatchMinute]", function() {
    var val= $(this).val();

    if(val.replace(/[0-9]/g, "").length > 0) {
        alert("최소 0시간부터 최대 60분까지 입력가능합니다.");
        $(this).val('');
    }
    
    if(val < 0 || val > 60) {
        alert("최소 0시간부터 최대 60분까지 입력가능합니다.");
        $(this).val('');
    }
});
    
document.querySelector('#todoModify-btn').addEventListener('click', function() {
	
	document.querySelector('#resultTime').value = 
	(parseInt(document.querySelector('#stopwatch-hour').value)*3600) +
	(parseInt(document.querySelector('#stopwatch-minute').value)*60);
	
	$('#todoModify-btn').submit();
         setTimeout(function() { 
			 self.opener = self;  
             window.close();

          }, 100);

});				// 창이 안닫히는 문제 발생.. 보안상의 문제라고함

// 자바스크립트에서 totalTime을 시 분 초 로 각각 변환 성공시키고 따로 저장 후에
// 해당 시 분 초를 html에 js로 input의 기본값으로 삽입 그리고 form의 
// action으로 값을 보낼때도 시 분 초 로 입력된 값을 다시 초 단위로 분해하여
// Controller로 전송 되도록 한다.122420 1200 21  1221 

// $(function() {

//     $("#send").click( function() {

//          $('#send_form').submit();
//          setTimeout(function() {   
//              window.close();

//           }, 100);

//       });
