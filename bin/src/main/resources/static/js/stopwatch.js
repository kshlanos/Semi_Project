let hour = 0;
let minute = 0;
let seconds = 0;

const changeeHour = document.getElementById('');
const changeMinute = document.getElementById('');
const changSeeconds = document.getElementById('');
const buttonStart = document.getElementById('');
const buttonPause = document.getElementById('');

buttonStart.onclick = function() {
    setInterval(stopwatch, 1000)
}

function stopwatch() {
    seconds--;
    changeSeconds.textContent = seconds;

    if(seconds < 0) {
        minute--;
        change
    }
}