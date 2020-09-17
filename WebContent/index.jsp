<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Company Management</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<div class="header_resize" style="display: flex; flex-direction: row; align-items: baseline;">
		<div class="logo">
			<h1>
				<a href="<c:url value='/' />">Event Catering System</a>
			</h1>
		</div>
		
	</div>
	<div class="main">


		<div class="content">

			<div class="menu_nav">
				<ul>
					<li><a href="<c:url value='/user/login.jsp' />"><span>Login</span></a></li>
					<li><a href="<c:url value='/user/form_user.jsp' />"><span>Register</span></a></li>
				</ul>
			</div>
		</div>

	</div>
</body>
</html>