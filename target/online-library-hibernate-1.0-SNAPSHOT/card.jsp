<%@ page import="java.time.LocalDate" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>${sessionScope.user.name}'s books</title>
    <link rel="icon" href="images/favicon.ico" type="image/ico">
    <style>
        body {
            background-image: url(images/background.jpg);
            background-attachment: fixed;
            background-repeat: no-repeat;
        }
        table {
            width: 100%;
        }
        td, th {background: rgba(255, 255, 255, 0.5); color: black; text-align: center; border: black}
        .btn {
            display: inline-block;
            float: right;
        }
        .text {
            text-align: center;
            background: rgba(255, 255, 255, 0.2);
        }
    </style>
</head>
<header>
    <c:if test="${sessionScope.user.role.toString() == 'USER'}">
        <div class="btn">
            <form action="<c:url value="/logout" />" method="post">
                <input type="submit" value="Logout"/>
            </form>
        </div>
        <div class="btn">
            <form action="<c:url value="/user.jsp" />" method="post">
                <input type="submit" value="My account"/>
            </form>
        </div>
        <div class="btn">
            <form action="<c:url value="/book" />" method="post">
                <input type="submit" value="Show books"/>
            </form>
        </div>
    </c:if>
    <c:if test="${sessionScope.user.role.toString() == 'ADMIN'}">
        <div class="btn">
            <form action="<c:url value="/logout" />" method="post">
                <input type="submit" value="Logout"/>
            </form>
        </div>
        <div class="btn">
            <form action="<c:url value="/user.jsp" />" method="post">
                <input type="submit" value="Account"/>
            </form>
        </div>
        <div class="btn">
            <form action="<c:url value="/book" />" method="post">
                <input type="submit" value="Show books"/>
            </form>
        </div>
        <div class="btn">
            <form action="<c:url value="/blocked" />" method="post">
                <input type="submit" value="Show blocked"/>
            </form>
        </div>
        <div class="btn">
            <form action="<c:url value="/admin.jsp" />" method="post">
                <input type="submit" value="Admin page"/>
            </form>
        </div>
    </c:if>
</header>
<body>
<br>
<c:if test="${sessionScope.user.role.toString() == 'USER'}">
<div class="text">
    <h1>Your books</h1>
</div>
</c:if>
<c:if test="${sessionScope.user.role.toString() == 'ADMIN'}">
    <div class="text">
        <h1>Overdue books</h1>
    </div>
</c:if>
<br>
<c:if test="${!empty userBooks && sessionScope.user.role.toString() == 'USER'}">
    <table>
        <thead>
        <tr>
            <th></th>
            <th>Title</th>
            <th>Author</th>
            <th>Description</th>
            <th>Return date</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${userBooks}" var="userBook">
            <c:if test="${userBook.user.id == sessionScope.user.id}">
                <tr>
                    <td><img src="${userBook.book.imageUri}" alt="no image"></td>
                    <td>${userBook.book.title}</td>
                    <td>${userBook.book.author.name}</td>
                    <td>${userBook.book.description}</td>
                    <td>${userBook.return_date}</td>
                    <td>
                        <form action="<c:url value="/returnBook"/>" method="post">
                            <input name="userBookId" type="hidden" value="${userBook.id}">
<%--                            <input name="userId" type="hidden" value="${sessionScope.user.id}">--%>
                            <input type="submit" value="Return book">
                        </form>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<br>
<c:if test="${!empty userBooks and sessionScope.user.role.toString() == 'ADMIN'}">
    <table>
        <thead>
        <tr>
            <th></th>
            <th>Title</th>
            <th>Author</th>
            <th>User email</th>
            <th>User phone</th>
            <th>Date return</th>
            <th>Today</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${userBooks}" var="userBook">
            <c:if test="${userBook.return_date gt LocalDate.now()}">
                <tr>
                    <td><img src="${userBook.book.imageUri}" alt="no image"></td>
                    <td>${userBook.book.title}</td>
                    <td>${userBook.book.author.name}</td>
                    <td>${userBook.user.email}</td>
                    <td>${userBook.user.phone}</td>
                    <td>${userBook.return_date}</td>
                    <td>${LocalDate.now()}</td>
                    <td>
                        <form action="<c:url value="/blockUser"/>" method="post">
                            <input name="userId" type="hidden" value="${userBook.user.id}">
                            <input type="submit" value="Block user">
                        </form>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>
