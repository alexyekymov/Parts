<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:if test="${necessary == true}">
        <title>Necessary Parts</title>
    </c:if>
    <c:if test="${necessary == false}">
        <title>Optional Parts</title>
    </c:if>
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
            <c:url value="/filter" var="url">
                <c:param name="necessary" value="true"/>
            </c:url>

            <c:url value="/filter" var="optional">
                <c:param name="necessary" value="false"/>
            </c:url>

            <span>Select filter: </span>
            <select onchange="top.location=this.value">
                <c:if test="${necessary == true}">
                    <option value="${url}">necessary</option>
                    <option value="${optional}">optional</option>

                </c:if>
                <c:if test="${necessary == false}">
                    <option value="${optional}">optional</option>
                    <option value="${url}">necessary</option>
                </c:if>
            </select>
        </div>
    </caption>
    <br>
    <tr>
        <th style="width: 40%;">name</th>
        <th style="width: 20%;">necessary</th>
        <th style="width: 20%;">quantity</th>
        <th style="width: 20%;" colspan="2">action</th>
    </tr>
    <c:forEach var="part" items="${necessaryParts}" varStatus="i">
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
                <c:url value="/filter" var="url">
                    <c:param name="page" value="${i.index}"/>
                    <c:param name="necessary" value="${necessary}">${necessary}</c:param>
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
