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
	<div class="header_resize" style="display: flex;flex-direction:row;align-items: baseline;">
		<div class="logo">
			<h1>
				<a href="<c:url value='/' />">Event Catering System</a>
			</h1>
		</div>
	</div>
	<span class="error"><c:out value='${errorMsgs.errorMsg}' /></span>
	<table>
		<tr>
			<td>
				<form name="companyForm"
					action="<c:url value='/UserController?login' />" method="post">
					<table style="width: 100%">
						

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
						
						

						
					</table>
					<input name="action" value="login" type="hidden"> <input
						type="submit" value="Login">
				</form>
			</td>
		</tr>
	</table>
</body>
</html>