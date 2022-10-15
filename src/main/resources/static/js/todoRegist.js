const urlParams = new URL(location.href).searchParams;

const studyNo = urlParams.get('studyNo');
const date = urlParams.get('todoListStartDate');
const todoListId = document.querySelector('#todoListId').innerText;
          
document.querySelector('#regist').addEventListener('click', ()=> {
          	location.href=`todoRegist?todoListId=${todoListId}&todoListStartDate=${date}&studyNo=${studyNo}`	
          			
          });


// let popUrl = `todoList?todoListStartDate=${clickDate}`;