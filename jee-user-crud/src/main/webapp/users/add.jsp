<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserAdd</title>
</head>
<body>

<div id="wrapper">
    <%@ include file="/header.jsp" %>

    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
            </nav>

            <div class="container-fluid">
                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">User CRUD</h1>
                    <a href="${pageContext.request.contextPath}/user/list"
                       class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
                        <i class="fas fa-download fa-sm text-white-50"></i>Lista użytkowników
                    </a>
                </div>
                <div class="card shadow m-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Dodaj użytkownika</h6>
                    </div>
                    <div class="card-body">
                        <div class="table">
                            <form action="/user/add" method="POST">
                                Nazwa:<br>
                                <input type="text" name="userName" class="form-control"
                                       placeholder="Nazwa użytkownika"/><br>
                                Email:<br>
                                <input type="email" name="email" class="form-control"
                                       placeholder="Email użytkownika"/><br>
                                Hasło:<br>
                                <input type="password" name="password" class="form-control"
                                       placeholder="Hasło użytkownika"/><br>
                                <input type="submit" value="Wyślij">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/footer.jsp" %>

</body>
</html>
