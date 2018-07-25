<jsp:include page="head.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<section id="main">
	<div class="container">
		<h1 id="homeTitle">${size} <spring:message code="dashboardText"/></h1>
		<div id="actions" class="form-horizontal">
			<div class="pull-left">
				<form id="searchForm" action="dashboard" method="GET" class="form-inline">

					<input type="search" id="searchbox" name="search"
						class="form-control" placeholder="<spring:message code="search"/>" value="${search}"/> 
					<input type="submit" id="searchsubmit" value="<spring:message code="filter"/>"
						class="btn btn-primary" />
				</form>
			</div>
			<div class="pull-right">
				<a class="btn btn-success" id="addComputer" href="addComputer"><spring:message code="addComputer"/></a> <a class="btn btn-default" id="editComputer" href="#"
					onclick="$.fn.toggleEditMode();"><spring:message code="edit"/></a>
			</div>
		</div>
	</div>

	<form id="deleteForm" action="deleteComputer" method="POST">
		<input type="hidden" name="selection" value="">
	</form>

	<div class="container" style="margin-top: 10px;">
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->

					<th class="editMode" style="width: 60px; height: 22px;"><input
						type="checkbox" id="selectall" /> <span
						style="vertical-align: top;"> - <a href="#"
							id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
								class="fa fa-trash-o fa-lg"></i>
						</a>
					</span></th>
					<th><spring:message code="computerName"/></th>
					<th><spring:message code="introducedDate"/></th>
					<!-- Table header for Discontinued Date -->
					<th><spring:message code="discontinuedDate"/></th>
					<!-- Table header for Company -->
					<th><spring:message code="company"/></th>

				</tr>
			</thead>

			<tbody id="results">
				<c:forEach var="computer" items="${computers}">
					<tr>
						<td class="editMode"><input type="checkbox" name="cb"
							class="cb" value="${computer.id}"></td>
						<td><a href="editComputer?computer=${computer.id}" onclick="">${computer.name}</a></td>
						<td>${computer.introducedDate}</td>
						<td>${computer.discontinuedDate}</td>
						<td>${computer.company.name}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</section>

<footer class="navbar-fixed-bottom">
	<div class="container text-center">
		<ul class="pagination">
			<c:choose>
				<c:when test="${search != null}">
					<li><a href="dashboard?search=${search}&page=${page==1||page==null ? 1 : page-1}" aria-label="Previous"> <span
							aria-hidden="true">&laquo;</span>
					</a></li>
					<li><a href="dashboard?search=${search}&page=${page < 3 ? 1 : page > pageMax-3 ? pageMax-4 : page-2}">${page < 3 ? 1 : page > pageMax-3 ? pageMax-4 : page-2}</a></li>
					<li><a href="dashboard?search=${search}&page=${page < 3 ? 2 : page > pageMax-3 ? pageMax-3 : page-1}">${page < 3 ? 2 : page > pageMax-3 ? pageMax-3 : page-1}</a></li>
					<li><a href="dashboard?search=${search}&page=${page < 3 ? 3 : page > pageMax-3 ? pageMax-2 : page}">${page < 3 ? 3 : page > pageMax-3 ? pageMax-2 : page}</a></li>
					<li><a href="dashboard?search=${search}&page=${page < 3 ? 4 : page > pageMax-3 ? pageMax-1 : page+1}">${page < 3 ? 4 : page > pageMax-3 ? pageMax-1 : page+1}</a></li>
					<li><a href="dashboard?search=${search}&page=${page < 3 ? 5 : page > pageMax-3 ? pageMax : page+2}">${page < 3 ? 5 : page > pageMax-3 ? pageMax : page+2}</a></li>
					<li><a href="dashboard?search=${search}&page=${page+1 > pageMax ? page : page+1}" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="dashboard?page=${page==1||page==null ? 1 : page-1}" aria-label="Previous"> <span
							aria-hidden="true">&laquo;</span>
					</a></li>
					<li><a href="dashboard?page=${page < 3 ? 1 : page > pageMax-3 ? pageMax-4 : page-2}">${page < 3 ? 1 : page > pageMax-3 ? pageMax-4 : page-2}</a></li>
					<li><a href="dashboard?page=${page < 3 ? 2 : page > pageMax-3 ? pageMax-3 : page-1}">${page < 3 ? 2 : page > pageMax-3 ? pageMax-3 : page-1}</a></li>
					<li><a href="dashboard?page=${page < 3 ? 3 : page > pageMax-3 ? pageMax-2 : page}">${page < 3 ? 3 : page > pageMax-3 ? pageMax-2 : page}</a></li>
					<li><a href="dashboard?page=${page < 3 ? 4 : page > pageMax-3 ? pageMax-1 : page+1}">${page < 3 ? 4 : page > pageMax-3 ? pageMax-1 : page+1}</a></li>
					<li><a href="dashboard?page=${page < 3 ? 5 : page > pageMax-3 ? pageMax : page+2}">${page < 3 ? 5 : page > pageMax-3 ? pageMax : page+2}</a></li>
					<li><a href="dashboard?page=${page+1 > pageMax ? page : page+1}" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:otherwise>
			</c:choose>
			
		</ul>

		<div class="btn-group btn-group-sm pull-right" role="group">
			<button type="button" class="btn btn-default" id="button1" value="10">10</button>
			<button type="button" class="btn btn-default" value="50">50</button>
			<button type="button" class="btn btn-default" value="100">100</button>
		</div>
	</div>
</footer>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/dashboard.js"></script>

</body>
</html>