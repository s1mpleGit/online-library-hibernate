<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Block page</title>
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
        .text {
            text-align: center;
            color: #ff0000;
            background: rgba(255, 255, 255, 0.2);
        }
        .btn {
            display: inline-block;
            float: right;
        }
        .fld {
            width: 100%;
            height: 100%;
            display: flex;
            align-items: center;
            justify-content: center;
            text-align: center;
        }
    </style>
</head>
<header>
    <c:if test="${sessionScope.user.status.toString() == 'BLOCK'}">
        <div class="btn">
            <form action="<c:url value="/logout" />" method="post">
                <input type="submit" value="Log out"/>
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
            <form action="<c:url value="/card" />" method="post">
                <input type="submit" value="Show cards"/>
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
<c:if test="${sessionScope.user.role.toString() == 'ADMIN'}">
    <div class="text">
        <h1>Blocked users</h1>
    </div>
</c:if>
<br>
<c:if test="${sessionScope.user.status.toString() == 'BLOCK'}">
    <h1 class="text">${sessionScope.user.name}, you are blocked by admin</h1>
    <div class="fld">
        <form action="<c:url value="/writeMessage" />" method="post">
            <input name="userId" type="hidden" value="${sessionScope.user.id}">
            <label for="message">
                <textarea id="message" name="message" rows="5" cols="40" required placeholder="Type your message to admin"></textarea>
            </label> <br>
            <input class="text" type="submit" value="Send message"/>
        </form>
    </div>
</c:if>
<c:if test="${sessionScope.user.role.toString() == 'ADMIN'}">
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Surname</th>
            <th>Phone</th>
            <th>Email</th>
            <th>Status</th>
            <th>Message</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <c:if test="${user.status.toString() == 'BLOCK'}">
                <tr>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.phone}</td>
                    <td>${user.email}</td>
                    <td>${user.status}</td>
                    <td>${user.message}</td>
                    <td>
                        <form action="<c:url value="/unblock"/>" method="post">
                            <input name="userId" type="hidden" value="${user.id}">
                            <input type="submit" value="Unblock user">
                        </form>
                    </td>
                    <td>
                        <form action="<c:url value="/deleteUser"/>" method="post">
                            <input name="userId" type="hidden" value="${user.id}">
                            <input type="submit" value="Delete user">
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