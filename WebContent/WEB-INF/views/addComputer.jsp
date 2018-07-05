<jsp:include page="head.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<h1>Add Computer</h1>
				<form action="addComputer" method="POST" onsubmit="return verifForm(this)">
					<fieldset>
						<div class="form-group">
							<label for="computerName">Computer name</label> <input
								type="text" class="form-control" id="computerName" name="computerName"
								placeholder="Computer name" onblur="verifNomComputer(this)">
						</div>
						<div class="form-group">
							<label for="introduced">Introduced date</label> <input
								type="date" class="form-control" id="introduced" name="introduced"
								placeholder="Introduced date" onblur="verifIntroducedDate(this)">
						</div>
						<div class="form-group">
							<label for="discontinued">Discontinued date</label> <input
								type="date" class="form-control" id="discontinued" name="discontinued"
								placeholder="Discontinued date" onblur="verifDiscountedDate(introduced,this)">
						</div>
						<div class="form-group">
							<label for="companyId">Company</label> <select
								class="form-control" id="companyId" name="companyId">
								<option value="0">---</option>
								<c:forEach var="company" items="${companies}">
									<option value="${company.id}">${company.name}</option>
								</c:forEach>
							</select>
						</div>
					</fieldset>
					<div class="actions pull-right">
						<input type="submit" value="Add" class="btn btn-primary">
						or <a href="dashboard" class="btn btn-default">Cancel</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>

<script src="js/verifForm.js"></script>

</body>
</html>