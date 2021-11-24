<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <!-- Theme Made By www.w3schools.com -->
  <title>Personal Naver API Project</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css">
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60">

<jsp:include page="/WEB-INF/views/side/header.jsp"></jsp:include>
<script type="text/javascript" src="<c:url value="/resources/js/login.js"/>"></script>

<!-- Container (About Section) -->
<div id="searchNews" class="container-fluid">
  <div class="container" style="width:80%" align="center">
  	<c:choose>
  		<c:when test="${display == null}">
  			<h2>뉴스를 검색하세요[현재 검색된 뉴스 : 0 개]</h2>
  		</c:when>
  		<c:otherwise>
  			<h2>뉴스를 검색하세요[현재 검색된 뉴스 : ${display} 개]</h2>
  		</c:otherwise>
  	</c:choose>
  	<p>클릭시 새창에서 해당 기사의 링크로 이동합니다.</p>
  	<!-- 뉴스 테이블 -->
  	<div style="height:500px; overflow:auto;">
	  	<table class="table table-hover" style="width:100%; border:0px; border-collapse:collapse;">
	  		<thead style="position:sticky; top:0; color:black; background-color:LightGray;">
	  			<tr>
	  				<th style="text-align:center;">index</th>
		  			<th style="text-align:center;">제목</th>
		  			<th style="text-align:center;">요약 내용</th>
		  			<th style="text-align:center;">네이버에 제공된 시간</th>
	  			</tr>
	  		</thead>
	  		<br>
	  		<tbody style="top:5px;">
	  			<c:forEach items="${newsList}" var="news" varStatus="status">
		  			<tr style="cursor:pointer;" onclick="window.open('${news.link}')" onMouseOver="window.status='${news.link}'" onMouseOut="window.status='';">
		  				<td>${status.count}</td>
		  				<td>${news.title}</td>
		  				<td>${news.description}</td>
		  				<td>${news.pubDate}</td>
		  			</tr>
	  			</c:forEach>
	  		</tbody>
	  	</table>
  	</div>
  </div>
</div>

<div class="container-fluid bg-grey">
  <div class="row">
  	  <c:choose>
  	  	<c:when test="${keyword != null}">
      		<h2 style="text-align:center">"${keyword}"에 관련된 YouTube 영상</h2><br>
      	</c:when>
      	<c:otherwise>
      		<h2 style="text-align:center">키워드를 검색하여 관련된 YouTube 영상을 시청하세요</h2>
      	</c:otherwise>
      </c:choose>
      
    <div id="myCarousel" class="carousel slide text-center" data-ride="carousel">
	    <!-- Indicators -->
	    <ol class="carousel-indicators">
	      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
	      <li data-target="#myCarousel" data-slide-to="1"></li>
	      <li data-target="#myCarousel" data-slide-to="2"></li>
	    </ol>
	
	    <!-- Wrapper for slides -->
	    <div class="carousel-inner" role="listbox">
	      <div class="item active">
	        <h4>"This company is the best. I am so happy with the result!"<br><span>Michael Roe, Vice President, Comment Box</span></h4>
	      </div>
	      <div class="item">
	        <h4>"One word... WOW!!"<br><span>John Doe, Salesman, Rep Inc</span></h4>
	      </div>
	      <div class="item">
	        <h4>"Could I... BE any more happy with this company?"<br><span>Chandler Bing, Actor, FriendsAlot</span></h4>
	      </div>
	    </div>
	
	    <!-- Left and right controls -->
	    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
	      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
	      <span class="sr-only">Previous</span>
	    </a>
	    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
	      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
	      <span class="sr-only">Next</span>
	    </a>
  </div>
  </div>
</div>

<!-- Container (Services Section) -->
<div id="services" class="container-fluid text-center">
  <h2>SERVICES</h2>
  <h4>What we offer</h4>
  <br>
  <div class="row slideanim">
    <div class="col-sm-4">
      <span class="glyphicon glyphicon-off logo-small"></span>
      <h4>POWER</h4>
      <p>Lorem ipsum dolor sit amet..</p>
    </div>
    <div class="col-sm-4">
      <span class="glyphicon glyphicon-heart logo-small"></span>
      <h4>LOVE</h4>
      <p>Lorem ipsum dolor sit amet..</p>
    </div>
    <div class="col-sm-4">
      <span class="glyphicon glyphicon-lock logo-small"></span>
      <h4>JOB DONE</h4>
      <p>Lorem ipsum dolor sit amet..</p>
    </div>
  </div>
  <br><br>
  <div class="row slideanim">
    <div class="col-sm-4">
      <span class="glyphicon glyphicon-leaf logo-small"></span>
      <h4>GREEN</h4>
      <p>Lorem ipsum dolor sit amet..</p>
    </div>
    <div class="col-sm-4">
      <span class="glyphicon glyphicon-certificate logo-small"></span>
      <h4>CERTIFIED</h4>
      <p>Lorem ipsum dolor sit amet..</p>
    </div>
    <div class="col-sm-4">
      <span class="glyphicon glyphicon-wrench logo-small"></span>
      <h4 style="color:#303030;">HARD WORK</h4>
      <p>Lorem ipsum dolor sit amet..</p>
    </div>
  </div>
</div>

<!-- Container (Portfolio Section) -->
<div id="portfolio" class="container-fluid text-center bg-grey">
  <h2>What our customers say</h2>
  <div id="myCarousel" class="carousel slide text-center" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
      <div class="item active">
        <h4>"This company is the best. I am so happy with the result!"<br><span>Michael Roe, Vice President, Comment Box</span></h4>
      </div>
      <div class="item">
        <h4>"One word... WOW!!"<br><span>John Doe, Salesman, Rep Inc</span></h4>
      </div>
      <div class="item">
        <h4>"Could I... BE any more happy with this company?"<br><span>Chandler Bing, Actor, FriendsAlot</span></h4>
      </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</div><br>

<!-- Container (Contact Section) -->
<div id="contact" class="container-fluid bg-grey">
  <h2 class="text-center">CONTACT</h2>
  <div class="row">
    <div class="col-sm-7" align="center">
      <h3><span class="glyphicon glyphicon-map-marker"></span> HyeongSeok LEE</h3>
      <h3><span class="glyphicon glyphicon-phone"></span> +82 1036231762</h3>
      <h3><span class="glyphicon glyphicon-envelope"></span> kea7109@naver.com</h3>
    </div>
    <div class="col-sm-5">
    	<form>
	      <div class="row">
	        <div class="col-sm-5 form-group">
	          <input class="form-control" id="name" name="name" placeholder="Name" type="text" required>
	        </div>
	        <div class="col-sm-5 form-group">
	          <input class="form-control" id="email" name="email" placeholder="Email" type="email" required>
	        </div>
	      </div>
	      	
	      <textarea class="form-control" id="comments" name="comments" placeholder="Comment" rows="5"></textarea><br>
	      	
	      <div class="row">
	        <div class="col-sm-1 form-group">
	          <button class="btn btn-default" type="submit">Send</button>
	        </div>
	      </div>
      	</form>
    </div>
  </div>
</div>

<jsp:include page="/WEB-INF/views/side/footer.jsp"></jsp:include>

<script type="text/javascript" src="<c:url value="/resources/js/main.js"/>"></script>

</body>
</html>
