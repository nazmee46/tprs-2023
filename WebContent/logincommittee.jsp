<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
	<meta name="generator" content="Hugo 0.84.0">
	<title>Committee | Committee Login</title>
	<link rel="icon" href="assets/images/UiTM_logo.png">
	<link href="assets/css/bootstrap.css" rel="stylesheet">
	<link href="assets/css/style.css" rel="stylesheet">
</head>

<body>
	<main class="form-signin text-center mt-8 bg-accent">
		<input type="button" class="w-50 p-3 fw-bold bg-accent" value="Committee"> 
		<form action="Login_ServletCommittee" method="post" class="m-4">
			<h1 class="h3 my-5 fw-normal fw-bold">Sign in</h1>
			<c:if test="${session_status != null}">
				<div class="alert alert-danger" role="alert">
					<c:out value="${session_status}"></c:out>
					<c:set var="session_status" value="${null}"></c:set>
				</div>
			</c:if>
			<div class="form-floating">
				<input type="text" name="commid" class="form-control" placeholder="ID number" required>
				<label>Enter your ID</label>
			</div>
			<div class="form-floating">
				<input type="password" name="commpass" class="form-control" placeholder="Password" required>
				<label>Password</label>
			</div>
			<button class="w-100 btn btn-lg btn-primary my-5" type="submit">Sign in</button>
			<p class=" pb-4 text-muted">&copy; 2017 - 2021</p>
		</form>
	</main>
</body>

</html>