 function login(){
	var frm = document.loginForm;
	if (!frm.memberId.value) { //아이디를 입력하지 않으면.
		swal({
			  title: "아이디를 입력하세요.",
			  //text: "15자리내의 영문,숫자",
			  icon: "warning",
			  button: "확인",
			});

		frm.memberId.focus();
		return false;
	}
	if (!frm.memberPwd.value) { //패스워드를 입력하지 않으면.
		swal({
			  title: "비밀번호를 입력하세요.",
			  //text: "16자리내의 영문,숫자,특수문자",
			  icon: "warning",
			  button: "확인",
			});
		frm.memberPwd.focus();
		return false;
	}
	document.loginForm.submit(); //유효성 검사가 통과되면 서버로 전송.
}


window.onload = function() {

    /* 화면에 랜더링 된 태그들이 존재하지 않는 경우 에러 발생 가능성이 있어서 if문으로 태그가 존재하는지 부터 확인하고 이벤트를 연결한다. */
    if(document.getElementById("register")) {
        const $regist = document.getElementById("register");
        $regist.onclick = function() {
            location.href = "/login/register";
        }
    }
}

//아이디 비밀번호 유효성 검사 (공백일 경우 경고창 표시)

  
    
 

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
