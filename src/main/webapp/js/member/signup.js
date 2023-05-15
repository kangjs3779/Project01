let checkId = false;
let checkEmail = false;
let checkNickName = false;
let checkPassword = false;

function enableSubmit() {
	if(checkId && checkEmail && checkNickName && checkPassword) {
		$("#signupSubmit").removeAttr("disabled");
	} else { 
		$("#signupSubmit").attr("disabled", "");
	}
}

//id 중복확인 버튼이 클릭되면
$("#checkIdButton").click(function() {
	const userid = $("#inputId").val();
	//입력한 ID와 ajax 요청 보내서
	$.ajax("/member/checkId/" + userid, {
		success: function(data) {
			// `("available": true)`
			
			if (data.available) {
				//사용가능하다는 메세지 출력
				$("#availableIdMessage").removeClass("d-none");
				$("#notAvailableMessage").addClass("d-none");
				checkId = true;
			} else {
				//사용가능하지 않다는 메세지 출력
				$("#notAvailableMessage").removeClass("d-none");
				$("#availableIdMessage").addClass("d-none");
				checkId = false;
			}
		},
		complete: enableSubmit
	})
})

// inputId에 키보드 입력 발생 시
$("#inputId").keyup(function() {
	//아이지 중복확인 다시
	checkId = false;
	$("#availableIdMessage").addClass("d-none")
	$("#notAvailableIdMessage").addClass("d-none")
	//submit버튼 비활성화
	enableSubmit();
})


//별명 중복 확인 버튼이 클릭되면
$("#checkNickNameButton").click(function() {
	const nickName = $("#inputNickname").val();
	
	$.ajax("/member/checkNickName/" + nickName, {
		success: function(data) {
			if(data.available) {
				$("#availableNickNameMessage").removeClass("d-none");
				$("#notAvailableNickNameMessage").addClass("d-none");
				checkNickName = true;
			} else {
				$("#notAvailableNickNameMessage").removeClass("d-none");
				$("#availableNickNameMessage").addClass("d-none");
				checkNickName = false;
			}
		},
		complete: enableSubmit
	})
})

//inputNickName에 키보드 입력 발생 시
$("#inputNickname").keyup(function() {
	//닉네임 중복확인 다시
	checkNickName = false;
	$("#availableNickNameMessage").addClass("d-none")
	$("#notAvailableNickNameMessage").addClass("d-none")
	//submit버튼 비활성화
	enableSubmit();
})

//email 중복 확인 버튼
$("#checkEmailButton").click(function() {
	const email = $("#inputEmail").val();
	
	$.ajax("/member/checkEmail/" + email, {
		success: function(data) {
			if(data.available) {
				$("#availableEmailMessage").removeClass("d-none");
				$("#notAvailableEmailMessage").addClass("d-none");
				checkEmail = true;
			} else {
				$("#notAvailableEmailMessage").removeClass("d-none");
				$("#availableEmailMessage").addClass("d-none");
				checkEmail = false;
			}
		}
	})
})

//inputEmail에 키보드 입력 발생 시
$("#inputEmail").keyup(function() {
	//닉네임 중복확인 다시
	checkNickName = false;
	$("#availableEmailMessage").addClass("d-none")
	$("#notAvailableEmailMessage").addClass("d-none")
	//submit버튼 비활성화
	enableSubmit();
})

//패스워드, 패스워드 체크 인풋에 키업 이벤트 발생하면
$("#inputPassword, #inputPasswordCheck").keyup(function() {
	// 패스워드에 입력한 값
	const pw1 = $("#inputPassword").val();
	// 패스워드 확인에 입력한 값이
	const pw2 = $("#inputPasswordCheck").val();

	if (pw1 === pw2) {
		//같으면
		//submit버튼 활성화
		$("#signupSubmit").removeAttr("disabled");
		//패스워드가 같다는 메세지 출력
		$("#passwordFailText").addClass("d-none")
		$("#passwordSuccessText").removeClass("d-none");
		checkPassword = true;
	} else {
		//그렇지않으면 submit버튼 비활성화
		$("#signupSubmit").attr("disabled")
		//패스워드가 다르다는 메세지 출력
		$("#passwordSuccessText").addClass("d-none")
		$("#passwordFailText").removeClass("d-none");
		checkPassword = false;
	}
	
	enableSubmit();
})
