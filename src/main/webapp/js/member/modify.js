let checkId = true;
let checkEmail = true;
let checkNickName = true;

function enableSubmit() {
	if (checkId && checkEmail && checkNickName) {
		$("#signupSubmit").removeAttr("disabled");
	} else {
		$("#signupSubmit").attr("disabled", "");
	}
}

// email 다시 입력하면 중복 재확인 
$("#inputEmail").keyup(function() {
	checkEmail = false;
	$("#availableEmailMessage").addClass("d-none")
	$("#notAvailableEmailMessage").addClass("d-none")
	enableSubmit();
})

//nickName 다시 입력하면 중복 재확인
$("#inputNickName").keyup(function() {
	checkEmail = false;
	$("#availableNickNameMessage").addClass("d-none")
	$("#notAvailableNickNameMessage").addClass("d-none")
	enableSubmit();
})

// 이메일 중복확인 버튼이 클릭되면
$("#checkEmailBtn").click(function() {
	const email = $("#inputEmail").val();
	$.ajax("/member/checkEmail/" + email, {
		success: function(data) {

			if (data.available) {
				$("#availableEmailMessage").removeClass("d-none");
				$("#notAvailableEmailMessage").addClass("d-none");
				checkEmail = true;
			} else {
				$("#availableEmailMessage").addClass("d-none");
				$("#notAvailableEmailMessage").removeClass("d-none");
				checkEmail = false;
			}
		},
		complete: enableSubmit
	});
});



// 닉네임 중복 확인 버튼 클릭 시
$("#checkNickNameBtn").click(function() {
	const nickName = $("#inputNickName").val();

	$.ajax("/member/checkNickName/" + nickName, {
		success: function(data) {
			// `{"available" : true}`
			if (data.available) {
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

$("#inputPassword", "#inputPasswordCheck").keyup(function() {
	const pw1 = $("#inputPassword").val();
	const pw2 = $("#inputPasswordCheck").val();

	if (pw1 === pw2) {
		$("#modifyButton").removeClass("disabled");

	} else {
		$("#modifyButton").addClass("disalbed");
	}

});


