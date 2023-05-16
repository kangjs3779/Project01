const toast = new bootstrap.Toast(document.querySelector("#liveToast"));

$("#likeIcon").click(function() {
	// 게시물 번호 request body에 추가
	const boardId = $("#boardIdText").text().trim();
	// const data = {boardId : boardId};
	const data = {boardId};
	
	$.ajax("/like", {
		method: "post",
		contentType: "application/json",
		data: JSON.stringify(data),
		
		success: function(data) {
			if (data.like) {
				//map타입의 result변수안에는 like라는 키와 그 값이 담겨있음
				//눌렀던 정보가 있으면 false, 처음 누르는 거면 true이다
				//그래서 like라는 값이 true이면 꽉 찬 하트 이미지를 더한다
				
				// 꽉 찬 하트
				$("#likeIcon").html(`<i class="fa-solid fa-heart"></i>`);
			} else {
				//like라는 값이 false이면 빈 하트의 이미지를 더한다
				
				// 빈 하트
				$("#likeIcon").html(`<i class="fa-regular fa-heart"></i>`);
			}
			//좋아요 수 업데이트
			$("#likeNumber").text(data.count);
			//map타입의 result변수 안에있는 count키값을 통해 그 value에 접근을 한다
		},
		error: function(jqXHR) {
			console.log("좋아요 실패");
			console.log(jqXHR);
			console.log(jqXHR.responseJSON);
			//success는 알아서 String으로 변환을 해주는데 error는 jqXHR로 넘어오는 것을 
			//json에서 받아서 쓴다
			//jqXHR
			//$("body").prepend(jqXHR.responseJSON.message);
			$(".toast-body").text(jqXHR.responseJSON.message);
			toast.show();
		}
		//complete:,
	});
});


