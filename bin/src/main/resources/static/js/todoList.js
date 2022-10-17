const urlParams = new URL(location.href).searchParams;

const studyNo = urlParams.get('studyNo');
const date = urlParams.get('todoListStartDate');
const todoListId = document.querySelector('#todoListId').innerText;

const todoListStartDate = document.querySelector('#todoModify-btn');

if(todoListStartDate){
todoListStartDate.addEventListener('click', () => {
          location.href = `todoModify?todoListId=${todoListId}&todoListStartDate=${date}&studyNo=${studyNo}`;	
          });
}

if(document.querySelector('#stopwatch')) {
const stopwatchPlay = document.querySelector('#stopwatch').addEventListener('click', ()=> {
          	location.href = `stopwatch?todoListId=${todoListId}`
          });
}

if(document.querySelector('#delete-btn')){
document.querySelector('#delete-btn').addEventListener('click', ()=> {
	
	location.href=`todoDelete?todoListId=${todoListId}`
	
	});
}

	const toDayTime = new Date();
	
	const toDayYear = toDayTime.getFullYear();
	const toDayMonth = ('0' + (toDayTime.getMonth() + 1)).slice(-2);
	const toDayDay = ('0' + (toDayTime.getDate())).slice(-2);
	const toDayToDay = toDayYear + '-' + toDayMonth + '-' + toDayDay;
	
if(date <= toDayToDay) {
	document.querySelector('#todoModify-btn').style.display = 'none';
}

const totalTime = document.querySelector('#totalTime').innerHTML;

let hour = Math.floor(totalTime / 3600);
let minute = Math.floor((totalTime / 60) % 60);

document.querySelector('#hour').innerHTML = hour > 9 ? hour : '0' + hour;
document.querySelector('#minute').innerHTML = minute > 9 ? minute : '0' + minute;

console.log(hour);
console.log(minute);

// let popUrl = `todoList?todoListStartDate=${clickDate}`;