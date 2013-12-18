<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" %>
<jsp:include flush="false" page="top.jsp"/>
<hr>
<a href="${pageContext.request.contextPath}/book/index"><spring:message code="label.bookList"/></a>
<br> <br>
<a href="${pageContext.request.contextPath}/author/index"><spring:message code="label.authorList"/></a>
<hr>
<table class="view" >
    <tr >
        <th>Пользователь</th>
        <th>Пароль</th>
    </tr>
    <tr>
        <td>admin</td>
        <td>123</td>
    </tr>
    <tr>
        <td>user</td>
        <td>123</td>
    </tr>
</table>
<p>Ссылка на работающие приложения <a href="http://goo.gl/Q7ZrFU">Amazon cloud</a>.</p>
<p>Ссылка на проект в <a href="https://github.com/pryadko/BooksCatalog.git">Github</a>.</p>
<p>Архив с исходным кодом <a href="https://github.com/pryadko/BooksCatalog/archive/master.zip">тут</a>.</p>

</body>
</html>
