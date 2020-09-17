<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User form</title>
<link href="../myStyle.css" rel="stylesheet" type="text/css" />
<link href="../style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.error {
	color: red;
	font-size: medium;
}

.success {
	color: green;
	font-size: medium
}

table tr td:nth-child(3) {
	color: red;
	font-size: medium;
}
</style>
</head>
<body>
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
	<span class="error"><c:out value='${errorMsgs.errorMsg}' /></span>
	<span class="success"> <c:out value='${errorMsgs.successUpdate}' /></span>
	<table>
		<tr>
			<td>
				<form name="companyForm" action="<c:url value='/EventController?updateEvent' />" method="post">
					<table style="width: 100%">
						<tr>
							<td>date (*):</td>
							<td><input type="date" id="datePicker" name="date" value="<c:out value='${event.date}' />" class="text16"></td>
							<td><c:out value='${errorMsgs.dateError}' /></td>
						</tr>

						<tr>
							<td>Start Time (*):</td>
							<td><input type="time" name="startTime" value="<c:out value='${event.startTime}'/>" class="text45"></td>
							<td><c:out value='${errorMsgs.startTimeError}' /></td>
						</tr>

						<tr>
							<td>Duration (*):</td>
							<td><select name="duration" class="text45" id="duration">
									<option value="<c:out value='${event.duration}'/>" selected='selected'>${event.duration}hours</option>
									<option value="2">2 hours</option>
									<option value="3">3 hours</option>
									<option value="4">4 hours</option>
									<option value="5">5 hours</option>
									<option value="6">6 hours</option>
									<option value="7">7 hours</option>
									<option value="8">8 hours</option>

							</select></td>
							<td><c:out value='${errorMsgs.durationError}' /></td>
						</tr>
						<tr>
							<td>Hall Name (*):</td>
							<td><select name="hallName" class="text45" id="hallName">
									<option value="<c:out value='${event.hallName}'/>" selected='selected'>${event.hallName}</option>
									<option value="Maverick">Maverick</option>
									<option value="KC">KC</option>
									<option value="Arlington">Arlington</option>
									<option value="Shard">Shard</option>
									<option value="Liberty">Liberty</option>


							</select></td>
							<td><c:out value='${errorMsgs.hallNameError}' /></td>
						</tr>

						<tr>
							<td>Estimated Attendees (*):</td>
							<td><input name="estAttendees" value="<c:out value='${event.estAttendees}'/>" class="text45"></td>
							<td><c:out value='${errorMsgs.estAttendeesError}' /></td>
						</tr>
						<tr>
							<td>Name (*):</td>
							<td><input name="name" value="<c:out value='${event.name}'/>" class="text45"></td>
							<td><c:out value='${errorMsgs.nameError}' /></td>
						</tr>


						<tr>
							<td>Food Type:</td>
							<td><select name="foodType" class="text45" id="foodType">
									<option value="<c:out value='${event.foodType}'/>" selected='selected'>${event.foodType}</option>
									<option value="American">American</option>
									<option value="Chinese">Chinese</option>
									<option value="French">French</option>
									<option value="Greek">Greek</option>
									<option value="Indian">Indian</option>
									<option value="Italian">Italian</option>
									<option value="Japanese">Japanese</option>
							</select></td>
							<td><c:out value='${errorMsgs.foodTypeError}' /></td>
						</tr>

						<tr>
							<td>Meal (*):</td>
							<td><select name="meal" class="text45" id="meal">
									<option value="<c:out value='${event.meal}'/>" selected='selected'>${event.meal}</option>
									<option value="breakfast">breakfast</option>
									<option value="lunch">lunch</option>
									<option value="supper">supper</option>
							</select></td>
							<td><c:out value='${errorMsgs.mealError}' /></td>
						</tr>
						<tr>
							<td>Meal Formality (*):</td>
							<td><select name="mealFormality" class="text45" id="mealFormality">
									<option value="<c:out value='${event.mealFormality}'/>" selected='selected'>${event.mealFormality}</option>
									<option value="formal">formal</option>
									<option value="informal">informal</option>
							</select></td>
							<td><c:out value='${errorMsgs.mealFormalityError}' /></td>
						</tr>
						<tr>
							<td>Drink Type (*):</td>
							<td><select name="drinkType" class="text45" id="drinkType">
									<option value="<c:out value='${event.drinkType}'/>" selected='selected'>${event.drinkType}</option>
									<option value="alcohol">alcohol</option>
									<option value="non-aclohol">non-aclohol</option>
							</select></td>
							<td><c:out value='${errorMsgs.drinkTypeError}' /></td>
						</tr>
						<tr>
							<td>Entertainment Items (*):</td>
							<td><select name="entertainmentItems" class="text45" id="entertainmentItems">
									<option value="<c:out value='${event.entertainmentItems}'/>" selected='selected'>${event.entertainmentItems}</option>
									<option value="music">music</option>
									<option value="non-music">non-music</option>
							</select></td>
							<td><c:out value='${errorMsgs.entertainmentItemsError}' /></td>
						</tr>
						<tr>
							<td>Event Status (*):</td>
							<td><select name="eventStatus" class="text45" id="eventStatus">
									<option value="<c:out value='${event.eventStatus}'/>" selected='selected'>${event.eventStatus}</option>
									<option value="reserved">reserved</option>
									<option value="saved">saved</option>
							</select></td>

						</tr>
						<tr>
							<td>Estimated Cost (*):</td>
							<td><input readonly="readonly" name="estCost" value="<c:out value='${event.estCost}'/>" class="text45"></td>
							<td></td>
						</tr>

						<tr>
							<td colspan="2">(*) Mandatory field</td>
						</tr>
					</table>
					<input name="action" value="updateEventUser" type="hidden" /> <input name="id" value="<c:out value="${event.id}" />" type="hidden"> <input
						type="submit" onclick="return checkIt()" value="Update event" />
				</form>
			</td>
		</tr>
	</table>

	<script type="text/javascript">
		function checkIt() {
			var test = confirm("are you sure you want to update event");
			return test;
		}
	</script>
</html>