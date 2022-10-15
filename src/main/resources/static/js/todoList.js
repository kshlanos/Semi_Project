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
          	console.log(12);
          });
}

document.querySelector('#delete-btn').addEventListener('click', ()=> {
	
	location.href=`todoDelete?todoListId=${todoListId}`
	
});

// let popUrl = `todoList?todoListStartDate=${clickDate}`;