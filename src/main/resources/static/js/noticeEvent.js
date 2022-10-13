/**
 * 공지사항, 이벤트 게시판 관련 동작 이벤트
 */
 
 
 
	if(document.getElementById("updateNotice")) {
		const $update = document.getElementById("updateNotice");
		$update.onclick = function() {
			location.href = "/admin/updateNoticeMain";
		}
	}

	if(document.getElementById("removeNotice")){
		const $update = document.getElementById("removeNotice");
		$update.onclick = function() {
			location.href = "/admin/noticeMain"
		}
	}
	
	    if(document.getElementById("updateUser")) {
        const $update = document.getElementById("updateUser");
        $update.onclick = function() {
            location.href = "/admin/userListAdmin";
        }
    }
    
    if(document.getElementById("deleteUser")) {
        const $update = document.getElementById("deleteUser");
        $update.onclick = function() {
            location.href = "/admin/userListAdmin";
        }
    }

 