$("#inputPassword", "#inputPasswordCheck").keyup(function() {
	const pw1 = $("#inputPassword").val();
	const pw2 = $("#inputPasswordCheck").val();

	if (pw1 === pw2) {
		$("#modifyButton").removeClass("disabled");

	} else {
		$("#modifyButton").addClass("disalbed");
	}

});

