<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Companies List</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
<link href="myStyle.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.error {
	color: red;
	font-size: medium;
}
.success{
	color: green; 
	font-size: medium
}
</style>
</head>
<body>
	<div class="header_resize" style="display: flex; flex-direction: row; align-items: baseline;">
		<div class="logo">
			<h1>
				<a href="<c:url value='/admin/home_page_admin.jsp' />">Event Catering System</a>
			</h1>
		</div>
		<div style="margin-left: 20px">
			Welcome
			<c:out value='${LOGIN_USER.lastName}' />
			<c:out value='${LOGIN_USER.firstName}' />
		</div>
		<div style="margin-left: 20px" class="logout">
			<a href="<c:url value='/UserController?action=logout' />">Log Out</a>
		</div>
	</div>

	<form name="companyForm" action="<c:url value='/UserController?searchUser' />" method="get">
		<table>
			<tr>
				<td>Last Name (*):</td>
				<td><input name="lastName" value="<c:out value='${user.lastName}'/>" class="text45"></td>
				<td><span class="error"> <c:out value='${errorMsgs.errorMsg}' /></span></td>
			</tr>
			

		</table>
		<input name="action" value="searchUser" type="hidden"> <input type="submit" value="Search User"><br><br>
		<span class="success"> <c:out value='${errorMsgs.userDeleteSuccess}' /></span>
	</form>
		<table border="1" class="myTable">
		<tr class="myTableRow">
			<th class="myTable20">Last Name</th>
			<th class="myTable20">First Name</th>
			<th class="myTable35">Username</th>
			<th class="myTable20">Role</th>
			<th class="myTable20">View</th>
			<th class="myTable20">Delete?</th>
		</tr>

		<c:forEach items="${Users}" var="item" varStatus="status">
			<tr class="myTableRow">
				<td class="myTable20 "><c:out value="${item.lastName}" /></td>
				<td class="myTable35 "><c:out value="${item.firstName}" /></td>
				<td class="myTable20 "><c:out value="${item.username}" /></td>
				<td class="myTable30 "><c:out value="${item.role}" /></td>
				<td><a href="<c:url value='/UserController?action=getUser&username=${item.username}&queryStr=${user.lastName}' />">View</a></td>
				<td><a href="<c:url value='/UserController?action=deleteUser&username=${item.username}&lastName=${user.lastName}' />" onclick="return confirm('Are you sure?')">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	

</body>
</html>