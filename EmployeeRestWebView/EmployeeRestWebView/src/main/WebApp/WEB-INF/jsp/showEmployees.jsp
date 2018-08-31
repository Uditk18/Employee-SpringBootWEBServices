<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h3 align="center">Resume</h3>



	<jstl:forEach var="emp" items="${requestScope.viewEmployee}">
		<table border="1" align="center">
			<tr>
				<td>
				<b>Employee Name : </b> ${emp.empName}
				</td>
			</tr>
			<tr>
				<td>
				<b>Employee ID : </b> ${emp.empId}
				</td>
			</tr>
			
			</br>
		</table>
	</jstl:forEach>


	<%-- 
	<button type="reset" value="reset">Clear</button>
	
	<button type="Submit" value="submit"><a href="submit.app?itemId=${xyz.id}">Submit</a></button>
 --%>
</body>
</html>