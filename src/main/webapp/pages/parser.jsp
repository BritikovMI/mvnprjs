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
    <table width="100%">
        <tr align="center"><td colspan="2">
        <h4>Parser</h4>
        <%--<h:messages infoClass="infoClass" errorClass="errorClass" layout="table" globalOnly="true"/>--%>
        </td>
        </tr><tr>
        <td colspan="1"></td>
        <td colspan="1" align="center">
        <h1>Hello, the name is: </h1>
        <h4><h:outputText value="#{parserController.name}" /></h4>
        <h3>Your table</h3>
        </td>
        </tr><tr align="center">
        <td colspan="1">

                <h4><h:outputText value="#{parserController.rateStatus}" /></h4>
        </td><td colspan="1">
                <h4><h:outputText value="#{parserController.response}" /></h4>
        </td>
        </tr><tr align="center">
            <td colspan="1">
            <h:commandButton action="#{parserController.CourseD}"  value="GetCourse" />
            </td><td colspan="1">
            <h:commandButton action="#{parserController.dBHelper}"  value="GetDBData" />
            </td>
        </tr>
        <tr align="center">
            <td colspan="1">
            <h:commandButton action="HOME" value="Home" immediate="true"/>
            </td>
    </tr>
    </table>
    </h:form>
</f:view>
</body>
</html>