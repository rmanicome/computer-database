<jsp:include page="head.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<div class="label label-default pull-right">id: ${computer.id}</div>
				<h1>Edit Computer</h1>

				<form action="editComputer?computer=${computer.id}" method="POST" id="form" name="form" onsubmit="return verifForm();">
					<input type="hidden" value="${computer.id}" id="id" name="id"/>
					<!-- TODO: Change this value with the computer id -->
					<fieldset>
						<div class="form-group">
							<label for="computerName">Computer name</label> <input
								type="text" class="form-control" id="computerName" name="computerName"
								placeholder="Computer name" value="${computer.name}" onblur="verifNomComputer(this)">
						</div>
						<div id="nameError" style="display:none;">
							<p>The name is incorrect</p>
						</div>
						<div class="form-group">
							<label for="introduced">Introduced date</label> <input
								type="date" class="form-control" id="introduced" name="introduced"
								placeholder="Introduced date" value="${computer.introducedDate}" onblur="verifIntroducedDate(this)">
						</div>
						<div id="introducedError" style="display:none;">
								<p>The introduced date can't be after today's date</p>
						</div>
						<div class="form-group">
							<label for="discontinued">Discontinued date</label> <input
								type="date" class="form-control" id="discontinued" name="discontinued"
								placeholder="Discontinued date" value="${computer.discontinuedDate}" onblur="verifDiscountedDate(document.getElementById('introduced'),this)">
						</div>
						<div id="discontinuedError" style="display:none;">
								<p id="discontinuedText">The discontinued date is incorrect</p>
						</div>
						<div class="form-group">
							<label for="companyId">Company</label> <select
								class="form-control" id="companyId" name="companyId">
								<option value="0">---</option>
								<c:forEach var="company" items="${companies}">
									<c:choose>
									 <c:when test="${company.id == computer.company.id}"><option value="${company.id}" selected="selected">${company.name}</option></c:when>
									 <c:otherwise><option value="${company.id}">${company.name}</option></c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</fieldset>
					<div class="actions pull-right">
						<input type="submit" value="Edit" class="btn btn-primary">
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