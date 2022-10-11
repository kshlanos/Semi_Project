window.onload = function() {

    /* 화면에 랜더링 된 태그들이 존재하지 않는 경우 에러 발생 가능성이 있어서 if문으로 태그가 존재하는지 부터 확인하고 이벤트를 연결한다. */
    if(document.getElementById("register")) {
        const $regist = document.getElementById("register");
        $regist.onclick = function() {
            location.href = "/login/register";
        }
    }
    

  
    
  /*  if(document.getElementById("memberPwdCheckBtn")){
		
		const $password = document.getElementById("memberPwd");					//비밀번호 
		const $passwordConfirm = document.getElementById("memberPwdCheck");		//비밀번호 확인 값
		const $confrimMsg = document.getElementById("memberPwdCheckBtn");		//확인 메세지
		const $SC = ["!","@","#","$","%"];
        const $check_SC = 0;
        
        
        
	    
		$confrimMsg.onclick = function() {
			
			if($password.value.length < 6 || $password.value.length >16){
                window.alert("비밀번호는 6글자 이상, 16글자 이하만 이용 가능합니다.");
                document.getElementById("memberPwd").value='';
                document.getElementById("memberPwdCheck").value='';
            }
            
             for(var i=0;i<$SC.values.length;i++){
                if($password.value.indexOf($SC.value[i]) != -1){
                    $check_SC.valueOf= 1;
                }
            }
           
             if($check_SC.value == 0){
                window.alert('!,@,#,$,% 의 특수문자가 들어가 있지 않습니다.')
                document.getElementById("memberPwd").value='';
                document.getElementById("memberPwdCheck").value='';
            }
			
	    		if($password.value == $passwordConfirm.value){
	    			window.alert("비밀번호가 일치합니다");
	    			
	    		}else{
	    			window.alert("비밀번호가 일치하지 않습니다");
	    			document.getElementById("memberPwd").value='';
                	document.getElementById("memberPwdCheck").value='';
	    		}
	    	}
		
	}*/

    if(document.getElementById("login")) {
        const $login = document.getElementById("login");
        $login.onclick = function() {
            location.href = "/member/login";
        }
    }

    if(document.getElementById("logout")) {
        const $logout = document.getElementById("logout");
        $logout.onclick = function() {
            location.href = "/member/logout";
        }
    }

    if(document.getElementById("updateMember")) {
        const $update = document.getElementById("updateMember");
        $update.onclick = function() {
            location.href = "/member/update";
        }
    }
    
    if(document.getElementById("deleteMember")) {
        const $update = document.getElementById("deleteMember");
        $update.onclick = function() {
            location.href = "/member/delete";
        }
    }

	/* ------------------------------------------------------------- */

    if(document.getElementById("writeBoard")) {
        const $writeBoard = document.getElementById("writeBoard");
        $writeBoard.onclick = function() {
            location.href = "/board/regist";
        }
    }

    if(document.getElementById("writeThumbnail")) {
        const $writeThumbnail = document.getElementById("writeThumbnail");
        $writeThumbnail.onclick = function() {
            location.href = "/thumbnail/regist";
        }
    }
}