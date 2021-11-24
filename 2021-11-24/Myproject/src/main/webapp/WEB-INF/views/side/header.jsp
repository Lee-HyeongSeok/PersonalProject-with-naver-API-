<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#myPage">Personal Project</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#searchNews">ISSUE</a></li>
        <li><a href="#services">SERVICES</a></li>
        <li><a href="#contact">CONTACT</a></li>
        <c:choose>
        	<c:when test="${User != null}"> <!-- session에 User VO가 있을 때(로그인 상태) -->
        		<li><a href="/login/logout">LOGOUT</a></li>
        		<li><a href="#">MyPage</a></li>
        	</c:when>
        	<c:otherwise> <!-- session에 User VO가 없을 때(비로그인 상태) -->
        		<li><a onclick="loginPopWindow()">LOGIN</a></li>
        	</c:otherwise>
        </c:choose>
      </ul>
    </div>
  </div>
</nav>

<div class="jumbotron text-center" style="text-align:center" align="center">
  <h1>News Community Web Site</h1> 
  <p>with Naver API</p> 
  
  <form action="/home" method="post">
    <div class="input-group" style="width:60%; margin:auto" align="center">
      <input type="text" class="form-control" size="50" name="keyword" placeholder="Search issue" required>
      <div class="input-group-btn">
        <button type="submit" class="btn btn-danger">Search</button>
      </div>
    </div>
  </form>
</div>
