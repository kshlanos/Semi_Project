const totalTime = document.querySelector('#originTime').textContent;

let hour = Math.floor(totalTime / 3600);
let minute = Math.floor((totalTime / 60) % 60);
let seconds = totalTime % 60;

document.getElementById('hour').innerHTML = hour > 9 ? hour : '0' + hour;
document.getElementById('minute').innerHTML = minute > 9 ? minute : '0' + minute;
document.getElementById('seconds').innerHTML = seconds > 9 ? seconds : '0' + seconds;


const changeHour = document.getElementById('hour');
const changeMinute = document.getElementById('minute');
const changeSeconds = document.getElementById('seconds');
const buttonStart = document.getElementById('start-btn');
const buttonPause = document.getElementById('pause-btn');
let stopwatchInterval;

buttonStart.onclick = function() {
    clearInterval(stopwatchInterval)
    stopwatchInterval = setInterval(stopwatch, 1000)
}

buttonPause.onclick = function() {
    clearInterval(stopwatchInterval)
}

function stopwatch() {
		
    seconds--;
    changeSeconds.textContent = seconds > 9 ? seconds : '0' + seconds

    if(seconds < 0) {
        minute--;
        changeMinute.textContent = minute > 9 ? minute : '0' + minute
        seconds = 60
        changeSeconds.textContent = "00"
    }
    
    if(minute < 0) {
        hour--;
        changeHour.textContent = hour > 9 ? hour : '0' + hour
        minute = 60
        changeMinute.textContent = "00"
        
    }
    
    if(hour == '00' && minute == '00' && seconds == '00') {
		buttonPause.onclick();
	}
    
    document.querySelector('#result').value = (hour*3600)+(minute*60)+seconds;
   
}

document.querySelector('#save').addEventListener('click', function() {
	
	$('#save').submit();

});