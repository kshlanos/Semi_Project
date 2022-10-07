var year = new Date().getFullYear();
var day = new Date().getDay()
var month = new Date().getMonth()
var date = new Date().getDate()

var weekdays = [ "일" , "월" , "화", "수", "목" , "금" , "토" ]

var months = ["1월" , "2월" , "3월" , "4월" , "5월" , "6월" , "7월" , "8월" , "9월" , "10월" ,"11월" , "12월" ]



var checkLeapYear = (setYear) =>{ 
  if( setYear%400 == 0){
    return true;
  }
  else if(setYear%100 == 0){
    return false;
  }
  else if(setYear%4 == 0){
    return true
  }
  else{
    return false
  }
}

var monthdays = (checkYear) => {
  return {
  "1월" : 31,
  "2월" : checkLeapYear(year) ? 29 : 28 , 
  "3월" : 31,
  "4월" : 30,
  "5월" : 31,
  "6월" : 30,
  "7월" : 31,
  "8월" : 31,
  "9월" :30,
  "10월" : 31,
  "11월" : 30 , 
  "12월" :31
}
}

const setTopDate=(setMonth,setYear)=>{
  document.querySelector('[data-selected="full-date"]').innerHTML = `${setYear}년 ${months[setMonth]}`;
}

setTopDate(month,year);

var monthdate;

const setFirstDay=(setYear, setMonth ) => {
  var monthdate = new Date(setYear, setMonth , 1).getDay()
  setFirstColspan(monthdate);
}

const setFirstColspan = (monthdate) =>{
  var ele = document.getElementsByTagName("table")[0].rows[2]
   ele.innerHTML = ""
  if(monthdate>0){
  var data = document.createElement("td")
   ele.appendChild(data)
   ele.cells[0].setAttribute( "colspan", "" +(monthdate ))
  }
   setCalendarData(monthdate)
  setLastColspan()
}

const setLastColspan = () => {
  var ele_len = document.getElementsByTagName("table")[0].rows
  var ele = document.getElementsByTagName("table")[0].rows[(ele_len.length)-1]
  console.log(7-(ele.cells.length)+1)
  if(7-(ele.cells.length) > 0){
  var data = document.createElement("td")
   ele.appendChild(data)
   ele.cells[ele.cells.length-1].setAttribute( "colspan", "" +(7-(ele.cells.length)+1))
  }
}

var row;

const setCalendarData = (monthdate) => {
   var count = 1;
   for(var i = monthdate;i<=6;i++){
     var data = document.createElement("td")
     var text = document.createTextNode(count);
     count++;
     data.appendChild(text)
     document.getElementsByTagName("table")[0].rows[2].appendChild(data);
   }  
    var tempMonthDays  = monthdays(year)[months[month]]
    for(var i = count;i<=tempMonthDays;i+=7){
      row = document.createElement("tr");
      for(var j =0;j<7&&count<=tempMonthDays;j++){
        var data = document.createElement("td")
        var text = document.createTextNode(count);
        count++;
        data.appendChild(text)
        row.append(data)
      }
     document.getElementsByTagName("table")[0].appendChild(row);
   } 
}

 setFirstDay(year, month);

const changeMonth = (operation) =>{
  var ele =  document.getElementsByTagName("table")[0]
  var len =  Object.keys(ele.rows).length
  len--;
  while(len>2){
     ele.removeChild( document.getElementsByTagName("table")[0].rows[len])
     len--;
  }
  if(operation == "next"){
  if(month+1> 11){
    year = year+1
    month = 0
  }
  else {
    month = month+1
  }}
  if(operation == "prev"){
  if(month-1<0){
     year = year-1
     month = 11
   }
   else {
     month = month-1
   }
  }
  
  setTopDate(month,year);
  setFirstDay(year , month)
  count = 1;
  row = "undefined";
}

function showWhere(e) {

const showMonth = e.view.month*1 + 1;
const showDay = e.target.innerText*1;

let showYear;
if(!e.path[9]) {
  showYear = (e.path[7].all[40].innerText).slice(0, 4)*1;
} else {
  showYear = (e.path[8].all[40].innerText).slice(0, 4)*1;
};

let clickDate;

if(isNaN(showDay) || showDay == 0) {
  clickDate = null;
} else {
  clickDate = `${showYear}-${showMonth}-${showDay}`;
}

console.log(clickDate);
}

	$('.popUp').on("click", function(e){
	
		e.preventDefault();
	
		let popUrl = "todoList";
		let popOption = "width = 800px, height=700px, top=300px, left=300px, scrollbars=yes";
		
		window.open(popUrl," TodoList ",popOption);
	
	});
