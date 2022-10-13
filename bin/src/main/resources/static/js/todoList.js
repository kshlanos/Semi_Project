const date = document.querySelector('#startDate').innerHTML;
const todoListId = document.querySelector('#todoListId').innerHTML;

const todoListStartDate = document.querySelector('#todoModify-btn');
todoListStartDate.addEventListener('click', () => {
  location.href = `todoModify?todoListId=${todoListId}&todoListStartDate=${date}`;	
});



// let popUrl = `todoList?todoListStartDate=${clickDate}`;