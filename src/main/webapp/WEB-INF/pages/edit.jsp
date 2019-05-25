<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:if test="${empty part.name}">
        <title>Add</title>
    </c:if>
    <c:if test="${!empty part.name}">
        <title>Edit</title>
    </c:if>
    <link href="<c:url value="/resources/style.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<c:if test="${empty part.name}">
    <c:url value="/add" var="var"/>
    <h3>Add new part</h3>
</c:if>
<c:if test="${!empty part.name}">
    <c:url value="/edit" var="var"/>
    <h3>Edit part</h3>
</c:if>
<br>
<br>
<div align="center">
    <form action="${var}" method="POST">
        <c:if test="${!empty part.name}">
            <input type="hidden" name="id" value="${part.id}">
        </c:if>

        <c:if test="${empty part.name}">
            <label for="name">Name</label>
            <input type="text" placeholder="name" name="name" id="name">

            <label for="necessary">Necessary</label>
            <select name="necessary" id="necessary">
                <option value="${true}">Yes</option>
                <option value="${false}">No</option>
            </select>

            <label for="quantity">Quantity</label>
            <input type="number" maxlength="11" placeholder="quantity" name="quantity" id="quantity">

            <input type="submit" value="Add new part">
        </c:if>
        <c:if test="${!empty part.name}">
            <label for="name">Name</label>
            <input type="text" placeholder="name" value="${part.name}" name="name" id="name">

            <label for="necessary">Necessary</label>
            <select name="necessary" id="necessary">
                <option value="${part.necessary}">
                    <c:if test="${part.necessary == true}">Yes</c:if>
                    <c:if test="${part.necessary == false}">No</c:if>
                </option>
                <option value="${!part.necessary}">
                    <c:if test="${part.necessary != true}">Yes</c:if>
                    <c:if test="${part.necessary != false}">No</c:if>
                </option>
            </select>

            <label for="quantity">Quantity</label>
            <input type="number" maxlength="11" placeholder="quantity" value="${part.quantity}" name="quantity" id="quantity">

            <input type="submit" value="Edit part">
        </c:if>
    </form>
</div>
</body>
</html>
