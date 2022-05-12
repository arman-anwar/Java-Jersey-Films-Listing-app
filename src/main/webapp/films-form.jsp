<jsp:include page="header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="container col-md-5">
	<div class="card">
		<div class="card-body">
			<form name="myForm" onsubmit="return validateForm();" id="myForm">
				<caption>
					<h2>Film Data</h2>
				</caption>

				<input type="hidden" name="id"
					value="<%=request.getParameter("id")!= null ? request.getParameter("id") : ""%>"
					id="film-id" />

				<fieldset class="form-group">
					<label>Title</label> <input type="text" id="title"
						value="<c:out value='${film.getTitle()}' />" class="form-control"
						name="title" required>
					<div class="valid-feedback">Valid.</div>
					<div class="invalid-feedback">Please enter valid title</div>
				</fieldset>

				<fieldset class="form-group">
					<label for="director" class="form-label">Director</label> <input
						type="text" value="<c:out value=''  />" class="form-control"
						name="director" id="director" required>
					<div class="valid-feedback">Valid.</div>
					<div class="invalid-feedback">Please enter valid Director</div>
				</fieldset>

				<fieldset class="form-group">
					<label>Stars</label> <input type="text" id="stars"
						value="<c:out value='' />" class="form-control" name="stars">
				</fieldset>

				<fieldset class="form-group">
					<label>Review</label> <input type="text" id="review"
						class="form-control" name="stars">
				</fieldset>


				<fieldset class="form-group">
					<label>Year</label> <input type="text" id="year"
						value="<c:out value='${film.getYear()}' />" class="form-control"
						onkeypress="return isNumber(event)" name="year" required>
				</fieldset>


				<button type="submit" class="btn btn-success">Save</button>
			</form>
		</div>
	</div>
</div>
</body>
</html>

<script>
	$(document)
			.ready(
					function() {

						//	$("#film-id").val(99);
						console.log("Document ready!");
						let endpoint = './rest/films/'
								+ $("#film-id").val();

						$.ajax({
							url : endpoint,
							contentType : "application/json",
							dataType : 'json',
							success : function(result) {

								$("#director").val(result.director);
								$("#title").val(result.title);
								$("#stars").val(result.stars);
								$("#review").val(result.review);
								$("#year").val(result.year);

							}
						})

					});

	function validateForm() {
	//	let x = document.forms["myForm"]["isbn"].value;
	//	if (!isValidIsbn(x)) {
	//		alert("Invalid ISBN");
		//	return false;
//		}
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
</script>