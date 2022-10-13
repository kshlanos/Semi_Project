/**
 * 공지사항, 이벤트 게시판 관련 동작 이벤트
 */
 
 
 
	if(document.getElementById("updateNotice")) {
		const $update = document.getElementById("updateNotice");
		$update.onclick = function() {
			location.href = "/admin/updateNoticeMain";
		}
	}

	if(document.getElementById("deleteNotice")){
		const $update = document.getElementById("deleteNotice");
		$update.onclick = function() {
			location.href = "/admin/"
		}
	}

 