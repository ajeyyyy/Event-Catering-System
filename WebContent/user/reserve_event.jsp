<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Company form</title>
</head>
<div class="header_resize">
<div class="header_resize" style="display: flex; flex-direction: row; align-items: baseline;">
		<div class="logo">
			<h1>
				<a href="<c:url value='/user/home_page_user.jsp' />">Event Catering System</a>
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
	<div class="menu_nav"></div>
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

	<table>
		<tr>
			<td>
				<form name="companyForm" action="<c:url value='/EventController' />" method="post">
					<table style="width: 100%">


						<tr>
							<td>Card No (*):</td>
							<td><input name="cardNumber" value="<c:out value='${card.cardNumber}' />" class="text45"></td>
							<td style="color: red;"><c:out value='${errorMsgs.cardNumberError}' /></td>
						</tr>
						<tr>
							<td>Expairy Date (*):</td>
							<td><input name="expDate" value="<c:out value='${card.expDate}' />" class="text45"></td>
							<td style="color: red;"><c:out value='${errorMsgs.expDateError}' /></td>
						</tr>
						<tr>
							<td>Pin (*):</td>
							<td><input name="pin" value="<c:out value='${card.pin}' />" class="text45"></td>
							<td style="color: red;"><c:out value='${errorMsgs.pinError}' /></td>
						</tr>
						<tr>
							<td colspan="2">(*) Mandatory field</td>
						</tr>
						<tr>
							<td colspan="2"></td>
						</tr>
					</table>
					<input name="action" value="reserveEvent" type="hidden">
					<input name="hallName" value="<c:out value="${event.hallName}" />" type="hidden">
					<input name="duration" value="<c:out value="${event.duration}" />" type="hidden">
					 <input name="id" value=<c:out value="${event.id}"/> type="hidden"> <input
						type="submit" value="Reserve event">
				</form>
			</td>
		</tr>
	</table>
</body>
</html>