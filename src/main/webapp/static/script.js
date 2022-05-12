$(document).ready(function() {

	$("#myForm").submit(function(event) {
		event.preventDefault();

		let filmId = null;
		let endpoint = './rest/films';
		if ($("#film-id").val().length !== 0) {
			filmId = $("#film-id").val();
			endpoint = endpoint + '/' + filmId;
		}

		let data = {
			"id": filmId,
			"title": $("#title").val(),
			"director": $("#director").val(),
			"stars": $("#stars").val(),
			"year": $("#year").val(),
			"review": $("#review").val(),
		}

		$.ajax({
			type: filmId ? 'PUT' : 'POST',
			url: endpoint,
			contentType: "application/json",
			dataType: 'json',
			data: JSON.stringify(data), // access in body
		}).done(function() {
			window.location.href = './';
			console.log('SUCCESS');
		});


	});

});

$("button").click(function() {
	event.preventDefault();


});

$("#myForm").submit(function(event) {
	event.preventDefault();
	alert("Handler for .submit() called.");
	event.preventDefault();

	let endpoint = './rest/films'
		+ $("#film-id").val();


	let data = JSON.stringify($("#myForm").serializeArray());

	$.ajax({
		type: 'PUT',
		url: endpoint,
		contentType: 'application/json',
		data: data, // access in body
	}).done(function() {
		console.log('SUCCESS');
	}).fail(function(msg) {
		console.log('FAIL');
	}).always(function(msg) {
		console.log('ALWAYS');
	});


});


function validateForm() {
	let x = document.forms["myForm"]["isbn"].value;
	if (!isValidIsbn(x)) {
		alert("Invalid ISBN");
		return false;
	}
}
(function() {
	'use strict'

	// Fetch all the forms we want to apply custom Bootstrap validation styles to
	var forms = document.querySelectorAll('.needs-validation')

	// Loop over them and prevent submission
	Array.prototype.slice.call(forms).forEach(function(form) {
		form.addEventListener('submit', function(event) {
			if (!form.checkValidity()) {
				event.preventDefault()
				event.stopPropagation()
			}

			form.classList.add('was-validated')
		}, false)
	})
})()

var isValidIsbn = function(str) {

	var sum, weight, digit, check, i;

	str = str.replace(/[^0-9X]/gi, '');

	if (str.length != 10 && str.length != 13) {
		return false;
	}

	if (str.length == 13) {
		sum = 0;
		for (i = 0; i < 12; i++) {
			digit = parseInt(str[i]);
			if (i % 2 == 1) {
				sum += 3 * digit;
			} else {
				sum += digit;
			}
		}
		check = (10 - (sum % 10)) % 10;
		return (check == str[str.length - 1]);
	}

	if (str.length == 10) {
		weight = 10;
		sum = 0;
		for (i = 0; i < 9; i++) {
			digit = parseInt(str[i]);
			sum += weight * digit;
			weight--;
		}
		check = (11 - (sum % 11)) % 11
		if (check == 10) {
			check = 'X';
		}
		return (check == str[str.length - 1].toUpperCase());
	}
}

function isNumber(evt) {
	evt = (evt) ? evt : window.event;
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if ((charCode > 31 && charCode < 48) || charCode > 57) {
		return false;
	}
	return true;
}

