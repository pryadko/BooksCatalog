<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" %>
<jsp:include flush="false" page="top.jsp"/>
<hr>
<a href="${pageContext.request.contextPath}/" title="
    <spring:message code="label.mainPage"/>"><spring:message code="label.mainPage"/></a> >
<a href="${pageContext.request.contextPath}/author/index" title="
    <spring:message code="label.authorList"/>"><spring:message code="label.authorList"/></a> >
<spring:message code="label.booksByAuthor"/>
<hr>
<table width="100%">
    <tr valign="top">
        <th width="80%"><p align="left"><spring:message code="label.bookList"/></p></th>
        <th width="20%"></th>
    </tr>
</table>
<c:if test="${!empty bookList}">
    <table class="view" width="100%">
        <tr >
            <th align="left"><spring:message code="label.bookName"/></th>
            <th align="left"><spring:message code="label.bookShortDescription"/></th>
            <th align="left"><spring:message code="label.bookYear"/></th>
            <th  width="15%" align="left"><spring:message code="label.bookAuthors"/></th>
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

            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>