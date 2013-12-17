<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page pageEncoding="UTF-8" %>
<jsp:include flush="false" page="top.jsp"/>
<hr>
<a href="${pageContext.request.contextPath}/" title="<spring:message code="label.mainPage"/>"> <spring:message
        code="label.mainPage"/></a> >
<spring:message code="label.authorList"/>
<hr>
<table width="100%">
    <tr valign="top">
        <th width="80%"><p align="left"><spring:message code="label.authorList"/></p></th>
        <th width="20%"><sec:authorize ifAnyGranted="ROLE_ADMIN">
            <p align="right"><input value="<spring:message code="label.authorCreate"/>"
                                    onClick="location.href='${pageContext.request.contextPath}/author/add/'"
                                    type="button"/></p>

        </sec:authorize>
        </th>
    </tr>
</table>

<c:if test="${!empty authorList}">
    <table class="view" width="100%">
        <tr>
            <th width="30%"><spring:message code="label.firstname"/></th>
            <th width="30%"><spring:message code="label.lastname"/></th>
            <th width="40%"><spring:message code="label.actions"/></th>
        </tr>
        <c:forEach items="${authorList}" var="author">
            <tr>
                <td>${author.firstname}</td>
                <td>${author.lastname}</td>
                <td>
                    <sec:authorize ifAnyGranted="ROLE_ADMIN">
                        <a href="${pageContext.request.contextPath}/author/edit/${author.id}"><spring:message
                                code="label.edit"/></a>
                        <a href="${pageContext.request.contextPath}/author/delete/${author.id}"><spring:message
                                code="label.delete"/></a>
                    </sec:authorize>
                    <a href="${pageContext.request.contextPath}/author/booksAuthor/${author.id}"><spring:message
                            code="label.bookList"/></a>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>