<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Registration</title>
    <link rel="icon" href="images/favicon.ico" type="image/ico">
    <style>
        body {
            background-image: url(images/background.jpg);
            background-attachment: fixed;
            background-repeat: no-repeat;
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
<body>
<br>
<div class="fld">
    <form action="<c:url value="/registration" />" method="post">
        <div class="text">
            <h1>Registration form</h1>
        </div>
        <label>
            Login<br>
            <input name="login" type="text" minlength="1" maxlength="20" placeholder="Enter login"/>
        </label> <br>
        <label>
            Password<br>
            <input name="password" type="password" minlength="1" maxlength="20" required placeholder="Enter password"/>
        </label> <br>
        <label>
            Name<br>
            <input name="name" type="text" minlength="1" maxlength="20" required placeholder="Enter name"/>
        </label> <br>
        <label>
            Surname<br>
            <input name="surname" type="text" minlength="1" maxlength="20" required placeholder="Enter surname"/>
        </label> <br>
        <label>
            Email<br>
            <input name="email" type="email" maxlength="30" required placeholder="example@example.com"/>
        </label> <br>
        <label>
            Phone<br>
            <input name="phone" type="tel" maxlength="12" minlength="12" pattern="375(25|29|33|44){1,1}[0-9]{7,7}"
                   required placeholder="375*code*1234567"/>
        </label> <br>
        <input class="text" type="submit" value="Registration"/>
    </form>
</div>
</body>
</html>
