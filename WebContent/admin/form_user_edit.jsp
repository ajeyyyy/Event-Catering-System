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
	<span class="error"><c:out value='${errorMsgs.errorMsg}' /></span>
	<table>
		<tr>
			<td>
				<form name="companyForm"
					action="<c:url value='/UserController?updateUser' />" method="post">
					<table style="width: 100%">
						<tr>
							<td>Uta ID (*):</td>
							<td><input name="utaId"
								value="<c:out value='${user.utaId}'/>" class="text16"></td>
							<td><c:out value='${errorMsgs.utaIdError}' /></td>
						</tr>

						<tr>
							<td>First Name (*):</td>
							<td><input name="firstName"
								value="<c:out value='${user.firstName}'/>" class="text45">
							</td>
							<td><c:out value='${errorMsgs.firstNameError}' /></td>
						</tr>

						<tr>
							<td>Last Name (*):</td>
							<td><input name="lastName"
								value="<c:out value='${user.lastName}'/>" class="text45">
							</td>
							<td><c:out value='${errorMsgs.lastNameError}' /></td>
						</tr>

						<tr>
							<td>User Name (*):</td>
							<td><input name="username"
								value="<c:out value='${user.username}'/>" class="text45">
							</td>
							<td><c:out value='${errorMsgs.usernameError}' /></td>
						</tr>
						<tr>
							<td>Password (*):</td>
							<td><input name="password"
								value="<c:out value='${user.password}'/>" class="text45">
							</td>
							<td><c:out value='${errorMsgs.passwordError}' /></td>
						</tr>

						<tr>
							<td>Phone:</td>
							<td><input name="phone"
								value="<c:out value='${user.phone}'/>" class="text16"></td>
							<td><c:out value='${errorMsgs.phoneError}' /></td>
						</tr>

						<tr>
							<td>Email (*):</td>
							<td><input name="email"
								value="<c:out value='${user.email}'/>" class="text45"></td>
							<td><c:out value='${errorMsgs.emailError}' /></td>
						</tr>
						<tr>
							<td>Street Number (*):</td>
							<td><input name="streetNumber"
								value="<c:out value='${user.streetNumber}'/>" class="text45">
							</td>
							<td><c:out value='${errorMsgs.streetNumberError}' /></td>
						</tr>
						<tr>
							<td>Street Name (*):</td>
							<td><input name="streetName"
								value="<c:out value='${user.streetName}'/>" class="text45">
							</td>
							<td><c:out value='${errorMsgs.streetNameError}' /></td>
						</tr>
						<tr>
							<td>city (*):</td>
							<td><input name="city" value="<c:out value='${user.city}'/>"
								class="text45"></td>
							<td><c:out value='${errorMsgs.cityError}' /></td>
						</tr>
						<tr>
							<td>state (*):</td>
							<td><input name="state"
								value="<c:out value='${user.state}'/>" class="text45"></td>
							<td><c:out value='${errorMsgs.stateError}' /></td>
						<tr>
						</tr>
						<tr>
							<td>Zip Code (*):</td>
							<td><input name="zipCode"
								value="<c:out value='${user.zipCode}'/>" class="text45">
							</td>
							<td><c:out value='${errorMsgs.zipCodeError}' /></td>
						<tr>
						</tr>
						<tr>
							<td>Role (*):</td>
							<td><select name="role" class="text45" id="userRole">
									<option value="user">User</option>
									<option value="staff">Carterer Staff</option>
									<option value="manager">Caterer Manager</option>
									<option value="admin">Admin</option>
							</select></td>
							<td><c:out value='${errorMsgs.roleError}' /></td>
						<tr>
							<td colspan="2">(*) Mandatory field</td>
						</tr>
					</table>
					<input name="action" value="updateUser" type="hidden">
					<input type="hidden" name="queryStr" value='${param["queryStr"]}'> 
					 <input
						type="submit" value="Update User">
				</form>
			</td>
		</tr>
	</table>
</body>
<script type="text/javascript">
	document.getElementById('userRole').value = "<c:out value='${user.role}' />";
</script>
</html>