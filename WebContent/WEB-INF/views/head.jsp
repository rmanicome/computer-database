<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="applicationName"/></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application - <spring:message code="applicationName"/> </a>
			 <a class="navbar-brand pull-right" href="?lang=<spring:message code="alter_language"/>">
			<img src="images/flag_<spring:message code="alter_language"/>.png"
			style="height:50px;width:50px;margin:-15px;" alt="change language icon" />
			</a>
		</div>
	</header>