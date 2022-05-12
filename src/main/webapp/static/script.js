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