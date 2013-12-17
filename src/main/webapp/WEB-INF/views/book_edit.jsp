<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include flush="false" page="top.jsp"/>
<hr>
<a href="${pageContext.request.contextPath}/" title="<spring:message code="label.mainPage"/>"> <spring:message
        code="label.mainPage"/></a> >
<a href="${pageContext.request.contextPath}/book/index/" title="<spring:message code="label.bookList"/>">
    <spring:message code="label.bookList"/></a> >
<spring:message code="label.bookEdit"/>
<hr>
<form:form method="post"
           action="${pageContext.request.contextPath}/book/save"
           commandName="book">
    <form:errors path="*" cssClass="errorblock" element="div"/>
    <form:hidden path="id"/>
    <h3><spring:message code="label.createMsg"/> <input type="submit" value="<spring:message code="label.apply"/>"/>
    </h3>


    <table>
        <tr>
            <td><form:label path="name"><spring:message code="label.bookName"/></form:label></td>
            <td><form:input width="100" path="name"/></td>
            <td><form:errors path="name" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="shortDescription"><spring:message
                    code="label.bookShortDescription"/></form:label></td>
            <td><form:textarea style="width:200px" path="shortDescription"/></td>
            <td><form:errors path="shortDescription" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="year"><spring:message code="label.bookYear"/></form:label></td>
            <td><form:input style="width:200px"  pattern="[0-9]{4}" title="111" path="year"/></td>
            <td><form:errors path="year" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="authors"><spring:message code="label.bookAuthors"/></form:label></td>
            <td>
                <form:select style="width:200px" size="10" items="${allAuthors}" itemValue="id" itemLabel="fullName"
                             path="authors"/>
            </td>
            <td><form:errors path="authors" cssClass="error"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>