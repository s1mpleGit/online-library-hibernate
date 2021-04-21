<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Admin page</title>
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

        .text {
            text-align: center;
            width: auto;
            height: auto;
            background: rgba(255, 255, 255, 0.2);
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
            <form action="<c:url value="/blocked" />" method="post">
                <input type="submit" value="Show blocked"/>
            </form>
        </div>
    </c:if>
</header>
<body>
<br>
<c:if test="${sessionScope.user.role.toString() == 'ADMIN' && empty requestScope.book}">
    <h1 class="text">Hello, ${sessionScope.user.name}! Manage online library wisely</h1>
</c:if>
<br>
<c:if test="${sessionScope.user.role.toString() == 'ADMIN' && empty requestScope.book}">
    <div class="fld">
        <form action="<c:url value="/createAuthor" />" method="post">
            <div class="text">
                <h1>Create new author</h1>
            </div>
            <label>
                <input name="author" type="text" minlength="1" maxlength="20" required
                       placeholder="Enter author's name"/>
            </label>
            <input class="text" type="submit" value="Create author"/>
        </form>
    </div>
</c:if>
<c:if test="${sessionScope.user.role.toString() == 'ADMIN' && !empty requestScope.book}">
    <div class="fld">
        <form action="<c:url value="/updateBook" />" method="post">
            <div class="text">
                <h1>Edit book info</h1>
            </div>
            <input name="bookId" type="hidden" value="${requestScope.book.id}">
            <label>
                Current title: ${requestScope.book.title} <br>
                New title<br>
                <input name="title" type="text" minlength="1" maxlength="20" required
                       placeholder="Enter book title"/>
            </label> <br>
            <label>
                New author<br>
                <select name="author">
                    <c:forEach items="${authors}" var="author">
                        <option value="${author.id}">${author.name}</option>
                    </c:forEach>
                </select>
            </label> <br>
            <label for="description">
                <textarea id="description" name="description" rows="5" cols="40"
                          required>${requestScope.book.description}</textarea>
            </label> <br>
            <label>
                Current image: <br> <img src="${requestScope.book.imageUri}" alt="no image"> <br>
                New image <br>
                <input name="imageUri" type="text" required placeholder="Enter link to image"/>
            </label> <br>
            <input class="text" type="submit" value="Update book"/>
        </form>
    </div>
</c:if>
</body>
</html>
