<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title>Hatbazar</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <jsp:include page="_resources.jsp"/>
    <decorator:head/>
</head>
<body>
<jsp:include page="flashMessage.jsp" flush="true" />
<jsp:include page="header.jsp" flush="true" />
<decorator:body/>
<jsp:include page="footer.jsp" flush="true"/>
</body>
</html>