const urlParams = new URL(location.href).searchParams;
	
const studyNo = urlParams.get('studyNo');
const date = urlParams.get('todoListStartDate');
const todoListId = urlParams.get('todoListId');

const todoListStartDate = document.querySelector('#todoModify-btn');

todoListStartDate.addEventListener('click', () => {
  location.href = `todoModify?todoListId=${todoListId}&todoListStartDate=${date}&studyNo=${studyNo}`;	
});

const stopwatchPlay = document.querySelector('#stopwatch').addEventListener('click', ()=> {
	location.href = `stopwatch?todoListId=${todoListId}`
});



// let popUrl = `todoList?todoListStartDate=${clickDate}`;