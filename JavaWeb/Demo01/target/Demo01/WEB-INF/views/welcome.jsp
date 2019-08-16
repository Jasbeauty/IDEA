<%@page contentType="text/html;charset=UTF-8" %>

<%--使用EL表达式访问request当中的user对象的userName属性--%>
<h3>访问request作用范围域中的user对象：${requestScope.user.userName} </h3>

<h3>访问session作用范围域中的user对象：${sessionScope.user.loginName} </h3>
<br>