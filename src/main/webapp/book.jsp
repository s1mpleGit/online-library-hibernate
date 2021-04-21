<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Books</title>
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

        td, th {
            background: rgba(255, 255, 255, 0.5);
            color: black;
            text-align: center;
            border: black
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
    <c:if test="${sessionScope.user.role == null}">
        <div class="btn">
            <form action="<c:url value="/index.jsp" />" method="post">
                <input type="submit" value="Login"/>
            </form>
        </div>
        <div class="btn">
            <form action="<c:url value="/registration.jsp" />" method="post">
                <input type="submit" value="Registration"/>
            </form>
        </div>
    </c:if>
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
            <form action="<c:url value="/user.jsp" />" method="post">
                <input type="submit" value="Account"/>
            </form>
        </div>
        <div class="btn">
            <form action="<c:url value="/admin.jsp" />" method="post">
                <input type="submit" value="Admin page"/>
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
<c:if test="${sessionScope.user.role.toString() == 'USER'}">
    <div class="text">
        <h1>Hey, ${sessionScope.user.name}, choose the book you like and enjoy reading</h1>
    </div>
</c:if>
<br>
<c:if test="${sessionScope.user.role.toString() == 'ADMIN'}">
    <div class="fld">
        <form action="<c:url value="/createBook" />" method="post">
            <div class="text">
                <h1>Create new book</h1>
            </div>
            <label>
                Title<br>
                <input name="title" type="text" minlength="1" maxlength="20" required
                       placeholder="Enter book title"/>
            </label> <br>
            <label>
                Author<br>
                <select name="author">
                    <c:forEach items="${authors}" var="author">
                        <option value="${author.id}">${author.name}</option>
                    </c:forEach>
                </select>
            </label> <br>
            <label for="description">
                <textarea id="description" name="description" rows="3" cols="20">Description</textarea>
            </label> <br>
            <label>
                Image<br>
                <input name="imageUri" type="text" required placeholder="Enter link to image"/>
            </label> <br>
            <input class="text" type="submit" value="Create book"/>
        </form>
    </div>
</c:if>
<br>
<c:if test="${!empty books}">
    <table>
        <thead>
        <tr>
            <th></th>
            <th>Title</th>
            <th>Author</th>
            <th>Description</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${books}" var="book">
            <tr>
                <td><img src="${book.imageUri}" alt="no image"></td>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.description}</td>
                <td>
                    <c:if test="${sessionScope.user.role.toString() == 'USER'}">
                        <form action="<c:url value="/takeBook"/>" method="post">
                            <input name="bookId" type="hidden" value="${book.id}">
                            <input name="userId" type="hidden" value="${sessionScope.user.id}">
                            <input type="submit" value="Take book">
                        </form>
                    </c:if>
                    <c:if test="${sessionScope.user.role.toString() == 'ADMIN'}">
                        <form action="<c:url value="/deleteBook"/>" method="post">
                            <input name="bookId" type="hidden" value="${book.id}">
                            <input type="submit" value="Delete book">
                        </form>
                        <form action="<c:url value="/admin"/>" method="post">
                            <input name="bookId" type="hidden" value="${book.id}">
                            <input name="bookImageUri" type="hidden" value="${book.imageUri}">
                            <input name="bookTitle" type="hidden" value="${book.title}">
                            <input name="bookAuthorId" type="hidden" value="${book.author.id}">
                            <input name="bookAuthor" type="hidden" value="${book.author}">
                            <input name="bookDescription" type="hidden" value="${book.description}">
                            <input type="submit" value="Manage book">
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>