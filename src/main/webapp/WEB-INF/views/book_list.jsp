<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page pageEncoding="UTF-8" %>
<jsp:include flush="false" page="top.jsp"/>
<hr>
<a href="${pageContext.request.contextPath}/" title="<spring:message code="label.mainPage"/>"> <spring:message
        code="label.mainPage"/></a> >
<spring:message code="label.bookList"/>
<hr>
<form:form method="post"
           action="${pageContext.request.contextPath}/book/find"
           commandName="findBook">
    <form:label path="name"><spring:message code="label.bookEnterName"/></form:label>
    <form:input path="name"/>
    <input type="submit" value="<spring:message code="label.bookFind"/>"/>
</form:form>
<table width="100%">
    <tr valign="top">
        <th width="80%"><p align="left"><spring:message code="label.bookList"/></p></th>
        <th width="20%">
        <th width="20%"><sec:authorize ifAnyGranted="ROLE_ADMIN">
            <p align="right"><input value="<spring:message code="label.bookCreate"/>"
                                    onClick="location.href='${pageContext.request.contextPath}/book/add/'"
                                    type="button"/></p>
        </sec:authorize>
        </th>
    </tr>
</table>
<c:if test="${!empty bookList}">
    <table class="view" width="100%">
        <tr>
            <th align="left"><spring:message code="label.bookName"/></th>
            <th align="left"><spring:message code="label.bookShortDescription"/></th>
            <th align="left"><spring:message code="label.bookYear"/></th>
            <th width="15%" align="left"><spring:message code="label.bookAuthors"/></th>
            <sec:authorize ifAnyGranted="ROLE_ADMIN">
                <th align="left"><spring:message code="label.actions"/></th>
            </sec:authorize>

        </tr>
        <c:forEach items="${bookList}" var="book">
            <tr>
                <td align="left">${book.name}</td>
                <td align="left">${book.shortDescription}</td>
                <td align="left">${book.year}</td>
                <td align="left">
                    <c:if test="${!empty bookList}">
                        <c:forEach items="${book.authors}" var="author">
                            <li>${author.fullName}</li>
                        </c:forEach>
                    </c:if>
                </td>
                <sec:authorize ifAnyGranted="ROLE_ADMIN">
                    <td align="left"><a href="${pageContext.request.contextPath}/book/edit/${book.id}"><spring:message
                            code="label.edit"/></a> <a
                            href="${pageContext.request.contextPath}/book/delete/${book.id}"><spring:message
                            code="label.delete"/></a></td>
                </sec:authorize>

            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>