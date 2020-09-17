<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Company form</title>
</head>
<div class="header_resize" style="display: flex; flex-direction: row; align-items: baseline;">
	<div class="logo">
		<h1>
			<a href="<c:url value='/staff/home_page_staff.jsp' />">Event Catering System</a>
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
<body>
	<table>
		<tr>
			<td>
				<table border="1" class="myTable">
					<tr>
						<td>Event Name:</td>
						<td><c:out value="${event.name}" /></td>
					</tr>

					<tr>
						<td>Date:</td>
						<td><c:out value="${event.date}" /></td>
					</tr>

					<tr>
						<td>Start Time:</td>
						<td><c:out value="${event.startTime}" /></td>
					</tr>

					<tr>
						<td>Duration:</td>
						<td><c:out value="${event.duration}" /></td>
					</tr>

					<tr>
						<td>Hall Name:</td>
						<td><c:out value="${event.hallName}" /></td>
					</tr>

					<tr>
						<td>Estimated Attendees:</td>
						<td><c:out value="${event.estAttendees}" /></td>
					</tr>

					<tr>
						<td>foodType:</td>
						<td><c:out value="${event.foodType}" /></td>
					</tr>

					<tr>
						<td>meal:</td>
						<td><c:out value="${event.meal}" /></td>
					</tr>

					<tr>
						<td>mealFormality:</td>
						<td><c:out value="${event.mealFormality}" /></td>
					</tr>
					<tr>
						<td>drinkType:</td>
						<td><c:out value="${event.drinkType}" /></td>
					</tr>
					<tr>
						<td>entertainmentItems:</td>
						<td><c:out value="${event.entertainmentItems}" /></td>
					</tr>
					<tr>
						<td>eventStatus:</td>
						<td><c:out value="${event.eventStatus}" /></td>
					</tr>
					<tr>
						<td>estCost:</td>
						<td><c:out value="${event.estCost}" /></td>
					</tr>

					<tr>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>