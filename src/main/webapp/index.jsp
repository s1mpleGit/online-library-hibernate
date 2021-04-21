<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
    <title>Online library</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
            height: 50%;
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
    <div class="btn">
        <form action="<c:url value="/book" />" method="post">
            <input type="submit" value="Show books"/>
        </form>
    </div>
    <div class="btn">
        <form action="<c:url value="/registration.jsp" />" method="post">
            <input type="submit" value="Registration"/>
        </form>
    </div>
</header>
<body>
<br>
<div class="text">
    <h1>Welcome to online library! Please log in or create account for using all features</h1>
</div>
<br>
<div class="fld">
    <form action="<c:url value="/index" />" method="post">
        <label>
            Login<br>
            <input name="login" type="text" minlength="1" maxlength="20" required placeholder="Enter login"/>
        </label> <br>
        <label>
            Password<br>
            <input name="password" type="password" minlength="1" maxlength="20" required placeholder="Enter password"/>
        </label> <br>
        <input type="submit" value="Log in" class="form"/>
    </form>
</div>
</body>
</html>