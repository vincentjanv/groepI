<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    Document   : editprofile
    Created on : Feb 26, 2013, 1:46:01 PM
    Author     : Ben Oeyen
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title><spring:message code='text.viewprofile' /></title>
        <link href="/css/blue.css" rel="stylesheet" />

    </head>
    <body>
        <div id="wrapper">
            <div id="topmenu" class="column dark">
                <jsp:include page="/topmenu"   />
            </div>
            <div id="content" class="column light">
                <h2> <spring:message code='text.myprofile' /> </h2>

                <section>
                    <div class="quarter"><img src="/images/noprofile.png" width='150' class="profilepic"/></div>
                    <div class="three-quarter">
                        <c:choose>
                            <c:when test="${userObject != null}">
                                <div class="row">
                                    <c:out value="Profile of user #${userObject.id}"/>
                                </div>
                                <form method="post" action="/profile/editUser" class="mainstyle tooltips validate">
                                <div class="row">
                                    <span><spring:message code='text.name'/></span>
                                    <input type="text" class="required" name="name" placeholder="" title="Gelieve uw volledige naam in te geven" value="${userObject.name}"/>
                                </div>
                                <div class="row">
                                    <span><spring:message code='text.mail'/></span>
                                    <input type="text" class="required" name="email" placeholder="" title="Gelieve een geldig e-mailadres in te geven. Dit wordt ook uw gebruikernsaam." value="${userObject.email}"/>
                                </div>
                                <div class="row">
                                    <span><spring:message code='text.dateofbirth'/></span>
                                    <input type="datetime" class="required dateregister" name="BirthDate" placeholder="" title="Gelieve uw geboortedatum in te geven" value="${userObject.dateOfBirth}"/>
                                </div>
                                <div class="row">
                                    <span></span>
                                    <input type="submit" class="button" value="<spring:message code='text.save'/>" />
                                    <input type="submit" class="button" value="<spring:message code='text.cancel'/>" />
                                </div>
                                </form>
                            </c:when>
                            <c:when test="${userObject == null}">
                                <jsp:forward page="/error/invaliduser" />
                            </c:when>

                        </c:choose>
                    </div>
                </section>
            </div></div>
        <script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
        <script src="/js/functions.js"></script>
    </body>
</html>
