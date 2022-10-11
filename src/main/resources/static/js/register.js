window.onload = function() {  
  
  if(document.getElementById("duplicationCheck")) {

        const $duplication = document.getElementById("duplicationCheck");

        $duplication.onclick = function() {
            let memberId = document.getElementById("memberId").value.trim();

            fetch("/login/idDupCheck", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                },
                body: JSON.stringify({memberId: memberId})
            })
                .then(result => result.text())
                .then(result => alert(result))
                .catch((error) => error.text().then((res) => alert(res)));

        }
    }
	// 아이디 유효성 검사
	$('#memberId').keyup(function(){
		 $("#duplicationCheck").hide();

    	 const $memberId = $("#memberId").val();
      	 const $numId = $memberId.search(/[0-9]/g);
		 const $engId = $memberId.search(/[a-z]/ig);
		 const $speId = $memberId.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
		  
		 if($memberId.length < 1 || $memberId.length > 15){
		 	$("#memberIdCheck").html("15자리 이내로 입력해주세요.");
		  	$("#memberIdCheck").attr('color','red');
		  	$("#register").attr("disabled", true); //설정
		 }else if($memberId.search(/\s/) != -1){
		  	$("#memberIdCheck").html("아이디는 공백 없이 입력해주세요.");
		  	$("#memberIdCheck").attr('color','red');
		  	$("#register").attr("disabled", true); //설정
		 }else if($numId < 0 || $engId  < 0){
			$("#memberIdCheck").html("영문,숫자 혼합하여 입력해주세요.");
			$("#memberIdCheck").attr('color','red');
			$("#register").attr("disabled", true); //설정
		 }else if($speId > 0 ){
			$("#memberIdCheck").html("특수문자를 제외하고 입력해주세요.");
			$("#memberIdCheck").attr('color','red');
			$("#register").attr("disabled", true); //설정
		 }else {
			$("#memberIdCheck").html("사용가능한 아이디입니다.");
			$("#memberIdCheck").attr('color','black');
			$("#duplicationCheck").show();
		    
		 }
	 })
    
	
	const $confrimMsg = document.getElementById("memberPwdCheckBtn");		//확인 버튼
	/* 비밀번호 재확인 */
	$confrimMsg.onclick = function() {
		
		const $password = $("#memberPwd").val();								//비밀번호
    	const $passwordConfirm = $("#memberPwdCheck").val();					//비밀번호 확인 값

		 if ($password != "" || $passwordConfirm != ""){
        	if ($password == $passwordConfirm) {
            	$("#checkPw").html('비밀번호가 일치합니다.');
            	$("#checkPw").attr('color','green');
            }else {
            	$("#checkPw").html('비밀번호가 불일치합니다.');
                $("#checkPw").attr('color','red');
            }
       	}
	  	 /* 비밀번호 유효성 */
      	 const number = $password.search(/[0-9]/g);
		 const english = $password.search(/[a-z]/ig);
		 const SC = $password.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
		
		 	if($password.length < 8 || $password.length > 16) {
		  		$("#passwordCheck").html("8자리 ~ 16자리 이내로 입력해주세요.");
		  		$("#passwordCheck").attr('color','red');
		
		 	}else if($password.search(/\s/) != -1) {
		 	 	$("#passwordCheck").html("비밀번호는 공백 없이 입력해주세요.");
		  	 	$("#passwordCheck").attr('color','red');
		  
		  
		 	}else if(number < 0 || english < 0 || SC < 0 ) {
				$("#passwordCheck").html("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
				$("#passwordCheck").attr('color','red');
		  
		 	}else {
				$("#passwordCheck").html("적절한 비밀번호입니다.");
				$("#passwordCheck").attr('color','green');
				
			if($password == $passwordConfirm) {
				 $("#register").attr("disabled", false); //설정
				 window.alert("적절한 비밀번호입니다.");
			 } else {
				 $("#register").attr("disabled", false); //설정
			 }
		    return true;
		    
		 }
		 return true;
	}
		
		
}
		 