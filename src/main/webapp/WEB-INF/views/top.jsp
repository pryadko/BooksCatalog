<%@page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title><spring:message code="label.booksCatalog"/></title>
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
</head>
<body>
<table style="width: 100%">
    <tr  style="vertical-align:top">
        <td  style="width:95%;text-align: center" rowspan="2" >
            <h1><div  style="text-align: center;border: 1" ><spring:message code="label.booksCatalog"/></div>
            </h1>
        </td>
        <td  style="text-align:center">

            <a href="${pageContext.request.contextPath}/?lang=en">en</a>
            |
            <a href="${pageContext.request.contextPath}/?lang=ru">ru</a>

        </td>
    </tr>

    <tr  style="width:5%">
        <td  style="text-align:center">
            <sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
                <a href="${pageContext.request.contextPath}/spring_security_login"><spring:message
                        code="label.login"/></a>
            </sec:authorize>
            <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER">
                <a href="<c:url value="/j_spring_security_logout" />"><spring:message code="label.logout"/></a>
                <sec:authentication property="principal.username"/>
                <sec:authorize ifAnyGranted="ROLE_USER">
                    (<spring:message code="label.user"/>)
                </sec:authorize>
                <sec:authorize ifAnyGranted="ROLE_ADMIN">
                    (<spring:message code="label.admin"/>)
                </sec:authorize>
            </sec:authorize>
        </td>
    </tr>

</table>