<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Calculator Application</title>
    <link rel="stylesheet"
          type="text/css" href="<%=request.getContextPath()%>/css/main.css" />
</head>
<body>
<f:view>
    <h:form id="calcForm">
        <h4>Parser</h4>
        <%--<h:messages infoClass="infoClass" errorClass="errorClass" layout="table" globalOnly="true"/>--%>

                <h4><h:outputText value="#{parserController.rateStatus}" /></h4>
                <h4><h:outputText value="#{parserController.nameOf}" /></h4>
        <div>
            <h:commandButton action="#{parserController.CourseD}"  value="GetCourse" />
            <h:commandButton action="#{parserController.dBHelper}"  value="GetDBData" />
            <h:commandButton action="HOME" value="Home" immediate="true"/>
        </div>
    </h:form>
</f:view>
</body>
</html>