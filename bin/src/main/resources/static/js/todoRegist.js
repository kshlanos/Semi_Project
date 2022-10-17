const urlParams = new URL(location.href).searchParams;

const studyNo = urlParams.get('studyNo');
const date = urlParams.get('todoListStartDate');
const todoListId = document.querySelector('#todoListId').innerText;
          
document.querySelector('#regist').addEventListener('click', ()=> {
          	location.href=`todoRegist?todoListId=${todoListId}&todoListStartDate=${date}&studyNo=${studyNo}`	
          			
          });

const totalTime = document.querySelector('#totalTime').innerHTML;

let hour = Math.floor(totalTime / 3600);
let minute = Math.floor((totalTime / 60) % 60);

document.querySelector('#hour').innerHTML = hour > 9 ? hour : '0' + hour;
document.querySelector('#minute').innerHTML = minute > 9 ? minute : '0' + minute;

console.log(hour);
console.log(minute);

// let popUrl = `todoList?todoListStartDate=${clickDate}`;

// todoRegist html에 삽입하는것이 아닌 todoList에 삽입되는 js