<jsp:include page="header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="container col-md-5">
	<div class="card">
		<div class="card-body">
			<form name="myForm"  id="myForm">
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

	function isNumber(evt) {
		evt = (evt) ? evt : window.event;
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if ((charCode > 31 && charCode < 48) || charCode > 57) {
			return false;
		}
		return true;
	}
</script>