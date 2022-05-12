<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="container">

	<div class="row">
		<h3 class="text-center">List of Films</h3>
		<hr>
		<div class="container text-left">


			<a href="films-form.jsp" class="btn btn-success">Add New Film</a>
		</div>
		<br>
		<div class="table-responsive">

			<table class="table table-bordered " id="films-grid">
				<thead>
					<tr>
						<th>Title</th>
						<th>Director</th>
						<th>Stars</th>
						<th>Year</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>

				</tbody>

			</table>
		</div>
	</div>
</div>
</body>
</html>

<script type="text/javascript"
	src='<c:url value="./static/index-script.js"/>'></script>

