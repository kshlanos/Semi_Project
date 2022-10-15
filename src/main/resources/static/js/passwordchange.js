
	function updatePwd() {

		     let pwd = document.getElementById('pwd').value;
		     let pwd2 = document.getElementById('pwd2').value;
		     let pwd3 = document.getElementById('pwd3').value;

		
		    if( pwd == '' ){
				swal('warning', '현재 비밀번호를 입력하세요!');
				 return false;
			}
			 if( pwd2 == ''){
				swal('warning', '새로운 비밀번호를 입력하세요!');
				 return false;
			}
		
			if( pwd2 !== pwd3 ){
				swal('warning', '비밀번호 확인이 올바르지 않습니다.');
				 return false;
			}    
		    var passwordRegExp =/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/; //비밀번호 유효성 검사
			if (!passwordRegExp.test(pwd2)) {
				swal('warning', '비밀번호는 영문 대소문자와 숫자 8~16자리로 입력해야합니다!');
				return false;
			}

			//유효성 검사 만족 후 비동기식 처리
			$.ajax({
				type: "POST",
				url: '/mypage/passwordchange',
				data: { 
						"pwd" : pwd,
						"pwd2": pwd2
				},
				success: function(data) {
					if (data != "") {
						swal({
						title: '변경 완료!',
						text: '비밀번호를 변경했습니다.',
						icon: 'success'
					}).then(() => {
					location.href = '/main';
				})
						
					} else {
						swal('warning', '현재 비밀번호가 일치하지 않습니다.');
					}
				}, error: function() {
					swal('error', 'error! ㅈ같네!');
				}
			});

		  };

	
