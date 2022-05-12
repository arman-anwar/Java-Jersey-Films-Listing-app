$(document).ready(function() {
	let endpoint = './rest/films';

	$.ajax({
		url: endpoint,
		contentType: "application/json",
		dataType: 'json',
		success: function(result) {
			//console.log(result.film);
			result.film.forEach(element => {
				//		console.log(element)
				let datarow = `<tr> 				<td> ${element.title} </td>		
				<td> ${element.director} </td>
				<td> ${element.stars} </td>
				<td> ${element.year} </td>
				<td><a href="films-form.jsp?id=${element.id}">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="./delete?id=${element.id}" class="delete-film" data-id="${element.id}">Delete</a></td>
				</tr>`;

				$('#films-grid  tr:last').after(datarow);
			});

		}
	})

	$("table").on("click", ".delete-film", function(event) {
		event.preventDefault();
		let filmId = $(this).data('id')

		let endpoint = './rest/films/' + filmId;

		$.ajax({
			type: 'DELETE',
			url: endpoint,
		}).done(function() {
			window.location.href = './';
			console.log('SUCCESS');
		});

	});

});