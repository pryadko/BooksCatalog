<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" %>
<jsp:include flush="false" page="top.jsp"/>
<hr>
<a href="${pageContext.request.contextPath}/" title="<spring:message code="label.mainPage"/>"> <spring:message
        code="label.mainPage"/></a> >
<a href="${pageContext.request.contextPath}/author/index/" title="<spring:message code="label.authorList"/>">
    <spring:message code="label.authorList"/></a> >
<spring:message code="label.authorCreate"/>
<hr>
<form:form method="post"
           action="${pageContext.request.contextPath}/author/save"
           commandName="author">
    <form:errors path="*" cssClass="errorblock" element="div"/>
    <h3><spring:message code="label.createMsg"/> <input type="submit"
                                                        value="<spring:message code="label.apply"/>"/></h3>
    <table>
        <tr>
            <td><form:label path="firstname"><spring:message code="label.firstname"/></form:label></td>
            <td><form:input path="firstname"/></td>
            <td><form:errors path="firstname" cssClass="error"/></td>
        </tr>
        <tr>
            <form:hidden path="id"/>
            <td width="150"><form:label path="lastname"><spring:message code="label.lastname"/></form:label></td>
            <td><form:input path="lastname"/></td>
            <td><form:errors path="lastname" cssClass="error"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>