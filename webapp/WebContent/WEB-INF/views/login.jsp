<jsp:include page="head.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="UTF-8" %>
<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<c:if test="${param.error!=null}">
					<p class="form-control" style="background:#fba;">${param.error}</p>
				</c:if>
				<h1><spring:message code="login"/></h1>
				<form action="login" method="POST" id="form" name="f" onsubmit="return verifLoginForm();">
					<fieldset>
						<div class="form-group">
							<label for="userName"><spring:message code="userName"/></label> <input
								type="text" class="form-control" id="username" name="username"
								placeholder="<spring:message code="userName"/>" onblur="verifNomUser(this)">
						</div>
						<div id="userNameError" style="display:none;">
							<p><spring:message code="error.userName"/></p>
						</div>
						<div class="form-group">
							<label for="password"><spring:message code="password"/></label> <input
								type="password" class="form-control" id="password" name="password"
								placeholder="<spring:message code="password"/>" onblur="verifPassword(this)">		
						</div>
						<div id="passwordError" style="display:none;">
								<p><spring:message code="error.password"/></p>
						</div>
					</fieldset>
					<div class="actions pull-right">
						<input type="submit" value="<spring:message code="login"/>" class="btn btn-primary">
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
			</div>
		</div>
	</div>
</section>

<script src="js/verifForm.js"></script>

</body>
</html>