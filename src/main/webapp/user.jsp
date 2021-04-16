<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Account</title>
    <link rel="icon" href="images/favicon.ico" type="image/ico">
    <style>
        body {
            background-image: url(images/background.jpg);
            background-attachment: fixed;
            background-repeat: no-repeat;
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
            <form action="<c:url value="/book" />" method="post">
                <input type="submit" value="Show books"/>
            </form>
        </div>
        <div class="btn">
            <form action="<c:url value="/card" />" method="post">
                <input type="submit" value="My books"/>
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
<div class="fld">
    <form action="<c:url value="/user" />" method="post">
        <div class="text">
            <h1>Edit user info</h1>
        </div>
        <input name="userId" type="hidden" value="${sessionScope.user.id}">
        <label>
            New password<br>
            <input name="newPassword" type="password" minlength="1" maxlength="20" required
                   placeholder="Enter new password"/>
        </label> <br>
        <label>
            New name<br>
            <input name="newName" type="text" minlength="1" maxlength="20" required placeholder="Enter new name"/>
        </label> <br>
        <label>
            New surname<br>
            <input name="newSurname" type="text" minlength="1" maxlength="20" required placeholder="Enter new surname"/>
        </label> <br>
        <label>
            New email<br>
            <input name="newEmail" type="email" required placeholder="Enter new email"/>
        </label> <br>
        <label>
            New Phone<br>
            <input name="newPhone" type="tel" maxlength="12" minlength="12" pattern="375(25|29|33|44){1,1}[0-9]{7,7}"
                   required placeholder="375*code*1234567"/>
        </label> <br>
        <input class="text" type="submit" value="Update"/>
    </form>
</div>
</body>
</html>
