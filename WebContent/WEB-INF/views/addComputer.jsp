<jsp:include page="head.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<h1><spring:message code="addComputer"/></h1>
				<form action="addComputer" method="POST" id="form" name="form" onsubmit="return confirm('Do you really want to submit the form?') && verifForm();">
					<fieldset>
						<div class="form-group">
							<label for="computerName"><spring:message code="computerName"/></label> <input
								type="text" class="form-control" id="computerName" name="computerName"
								placeholder="<spring:message code="computerName"/>" onblur="verifNomComputer(this)">
						</div>
						<div id="nameError" style="display:none;">
							<p><spring:message code="errorName"/></p>
						</div>
						<div class="form-group">
							<label for="introduced"><spring:message code="introducedDate"/></label> <input
								type="date" class="form-control" id="introduced" name="introduced"
								placeholder="<spring:message code="introducedDate"/>" onblur="verifIntroducedDate(this) & verifDiscountedDate(this, document.getElementById('discontinued'))">		
						</div>
						<div id="introducedError" style="display:none;">
								<p><spring:message code="errorIntroduced"/></p>
						</div>
						<div class="form-group">
							<label for="discontinued"><spring:message code="discontinuedDate"/></label> <input
								type="date" class="form-control" id="discontinued" name="discontinued"
								placeholder="<spring:message code="discontinuedDate"/>" onblur="verifDiscountedDate(document.getElementById('introduced'),this)">
						</div>
						<div id="discontinuedIntroError" style="display:none;">
								<p id="discontinuedText"><spring:message code="errorDisconIntro"/></p>
						</div>
						<div id="discontinuedTodayError" style="display:none;">
								<p id="discontinuedText"><spring:message code="errorDisconToday"/></p>
						</div>
						<div class="form-group">
							<label for="companyId"><spring:message code="company"/></label> <select
								class="form-control" id="companyId" name="companyId">
								<option value="0">---</option>
								<c:forEach var="company" items="${companies}">
									<option value="${company.id}">${company.name}</option>
								</c:forEach>
							</select>
						</div>
					</fieldset>
					<div class="actions pull-right">
						<input type="submit" value="<spring:message code="add"/>" class="btn btn-primary">
						<spring:message code="or"/> <a href="dashboard" class="btn btn-default"><spring:message code="cancel"/></a>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>

<script src="js/verifForm.js"></script>

</body>
</html>