<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="/resources/css/login.css">
  <title>login user</title>
</head>

<body>
	<div class="jumbotron text-center fontColor" style="text-align:center; background-color:#f4511e" align="center">
	  <h1>Login</h1> 
	  <p>with Naver API</p> 
	</div><br>
	
	<div class="container">
		<form action="/login/loginProcess" method="POST" target="parentWindow">
			<div class="form-group">
				<label for="email">Email:</label>
				<input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
			</div>
			
			<div class="form-group">
				<label for="pwd">Password:</label>
				<input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
			</div>
			<button type="submit" class="btn btn-default" onclick="window.close()">Submit</button>
			<button type="reset" class="btn btn-default" onclick="self.close()">Cancle</button>
		</form>
	</div>
	<script type="text/javascript" src="<c:url value="/resources/js/login.js"/>"></script>
</body>
</html>