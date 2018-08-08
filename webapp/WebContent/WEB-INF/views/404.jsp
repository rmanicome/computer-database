<jsp:include page="head.jsp" />
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <section id="main">
        <div class="container">
            <div class="alert alert-danger">
                <spring:message code="error.404"/>
                <br/>
                <!-- stacktrace -->
            </div>
        </div>
    </section>

    <script src="../js/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/dashboard.js"></script>

</body>
</html>