// 아이디 찾기 js
function findId() {

	let memberName = document.getElementById('memberName').value;
	let memberPhone = document.getElementById('memberPhone').value;

	if (memberName == '' || memberPhone == '') {
		swal({
					text: "가입된 정보를 입력해주세요.",
					icon: "warning",
  					button: "확인",
				})
		return false;
	}

	$.ajax({
		type: "POST",
		url: "/login/forgotId",
		data: {
			"memberName": memberName,
			"memberPhone": memberPhone
		},
		success: function(data) {
			if (data != "") {
				swal({
					title: "아이디 찾기 완료",
					text: "가입된 아이디는 " + data + " 입니다.",
					icon: "success",
  					button: "로그인으로 이동",
				}).then(() => {
					location.href = '/login/login';
				})

			} else {
				swal({
					text: "입력하신 정보로 해당하는 아이디를 찾을 수 없습니다.",
					icon: "warning",
  					button: "확인",
				})
			}

		}, error: function() {
			swal({
					text: "다시 시도해주세요.",
					icon: "error",
  					button: "확인",
				})
		}

	}); 
}

