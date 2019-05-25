<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
    <link href="<c:url value="/resources/style.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<h3>Parts-list</h3>
<table class="main-table">
    <caption class="style">
        <form action="<c:url value="/"/>" method="GET">
            <input type="submit" value="<< Back to all parts">
        </form>
        <div>
            <span>Searching by : «${search}»</span>
        </div>
    </caption>
    <br>
    <tr>
        <th style="width: 40%;">name</th>
        <th style="width: 20%;">necessary</th>
        <th style="width: 20%;">quantity</th>
        <th style="width: 20%;" colspan="2">action</th>
    </tr>
    <c:forEach var="part" items="${searchList}" varStatus="i">
    <tr>
        <td>${part.name}</td>
        <td>
            <c:if test="${part.necessary == true}">
                Yes
            </c:if>
            <c:if test="${part.necessary == false}">
                No
            </c:if>
        </td>
        <td>${part.quantity}</td>
        <td><a href="/edit/${part.id}">edit</a></td>
        <td><a href="/delete/${part.id}">delete</a></td>
    </tr>
    </c:forEach>
    <tr>
        <td colspan="3">
            <a href="<c:url value="/add"/>">Add new part</a>
        </td>
        <td colspan="2">
            <c:forEach begin="${1}" end="${pagesCount}" step="1" varStatus="i">
                <c:url value="/search" var="url">
                    <c:param name="page" value="${i.index}"/>
                    <c:if test="${!empty search}">
                        <c:param name="search" value="${search}">${search}</c:param>
                    </c:if>
                </c:url>
                <a href="${url}">${i.index}</a>
            </c:forEach>
        </td>
    </tr>
</table>
<br>
<br>
<table class="bottom-table">
    <tr>
        <th style="width: 40%;">Can be assembled: </th>
        <th style="width: 20%;">${assembledPC}</th>
        <th style="width: 40%;"> computers</th>
    </tr>
</table>
</body>
</html>
