/* 스터디 찾기 목록 관련 동작 이벤트 */
 
 if(document.getElementById("studyCategory"))
 $('.mainCategory').change(function(){
	var value = $(this).val();

	$('.subCategory').hide();
	$('.subCategory.subCategory-' + value).show();
 }).change();
 