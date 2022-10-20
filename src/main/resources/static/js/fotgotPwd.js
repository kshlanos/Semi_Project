function findPwd() {
			
	let memberName = document.getElementById('memberName').value;
	let memberId = document.getElementById('memberId').value;
	let memberEmail = document.getElementById('memberEmail').value;

	if (memberName == '' || memberId == ''|| memberEmail == '') {
		swal({
					text: "가입된 정보를 입력해주세요.",
					icon: "warning",
  					button: "확인",
				})
		return false;
	}
		
			
			$.ajax({
				type : "POST",
				url : "/login/forgotPassword",
				data : {
					"memberId" :  $("#memberId").val(),
					"memberName" : $("#memberName").val(),
					"memberEmail" : $("#memberEmail").val()
				},
				success : function(data){
					if(data == ""){
						//console.log("없음!")
						swal('입력하신 정보로 해당하는 아이디를 찾을 수 없습니다.',"error");
					}else{
						swal({
								title: "이메일 전송 완료",
								text: "회원님의 임시 비밀번호를 이메일로 발송하였습니다. 확인부탁드립니다.",
								icon: "success",
		  						button: "로그인으로 이동",
							}).then(() => {
								location.href = '/login/login';
							})
						
						$findOk.show();	
					}
				}, error: function() {
			swal({
					text: "다시 시도해주세요.",
					icon: "error",
  					button: "확인",
				})
		}
			})
		}
	