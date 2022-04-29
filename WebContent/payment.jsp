<!-- @author IT19075754 Jayasinghe D.T. -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">

<title>DHSC Games Payment</title>

<!-- Bootstrap core CSS -->
<link href="it19075754_resources/css/bootstrap.min.css" rel="stylesheet">
<link href="it19075754_resources/css/storestyles.css" rel="stylesheet">
<link rel="icon" href="it19075754_resources/images/sidebar/home.ico"
	type="image/x-icon">

<style>
</style>
<!-- Custom styles for this template -->
<link href="carousel.css" rel="stylesheet">
</head>

<%
	if(session.getAttribute("userName") !=null)
	{
%>

<body>
	
	<!-- main wrapper -->
	<div class="container-fluid">
		<div class="row bg-d">
		<!-- sidebar contents -->
			<nav class="col-sm-1 col-md-1 col-lg-1 d-none d-md-block sidebar bg-d">
				<div class="sidebar-sticky bg-d">
					<ul class="nav flex-column">
						<li class="nav-item  text-center"><a class="nav-link "
							href="gamerHome.jsp"> <img class="sidebar-ico"
								src="it19075754_resources\images\sidebar\home.ico"
								draggable="false">
								<p class="text-white">Home</p>
						</a></li>
						<li class="nav-item text-center"><a class="nav-link "
							href="StoreServicesServlet"><img class="sidebar-ico"
								src="it19075754_resources\images\sidebar\store.ico"
								draggable="false">
								<p class="text-white">Store</p> </a></li>
						<li class="nav-item text-center"><a class="nav-link "
							href="CartServicesServlet"><img class="sidebar-ico"
								src="it19075754_resources\images\sidebar\cart.ico"
								draggable="false">
								<p class="text-white">Cart</p> </a></li>
						<li class="nav-item text-center"><a class="nav-link "
							href="ProfileServicesServlet"><img class="sidebar-ico"
								src="it19075754_resources\images\sidebar\profile.ico"
								draggable="false">
								<p class="text-white">Profile</p> </a></li>
						<li class="nav-item text-center"><a class="nav-link active"
							href="#"><img class="sidebar-ico"
								src="it19075754_resources\images\sidebar\Payment.ico"
								draggable="false">
								<p class="text-white">Payment</p> </a></li>
					</ul>
				</div>
			</nav>
			<!-- sidebar contents end here -->

			<!-- main contents -->
			<div class="container-fluid" style="margin: 20px 0 0 184px;">
				<main role="main"
					class="col-md-12 col-sm-12 ml-sm-auto col-lg-12 px-4 main-page">
					
					
					<div class="col">
						<form class="form-inline mt-2 mt-md-0   justify-content-begin" action="logout.jsp" method="post">
							<button class="btn btn-warning my-2" type="submit">Sign
								out</button>
						</form>
					</div>
					
					
					<section class="jumbotron text-center mx-3 text-black"
						style="background: transparent url('it19075754_resources/images/bg/bg2.jpg') no-repeat center center/cover; height:400px;">

						<div class="container mx-4 my-5 py-3 px-4">
							<h1>DHSC Games Store</h1>
							<p class="lead text-black">Hey there&#8212;welcome to the
								most awesome games store in the whole universe.</p>
							<div class="col">
								<form class="form-inline mt-2 mt-md-0   justify-content-center">
									<input class="form-control mr-sm-2" type="text"
										placeholder="Search Games" aria-label="Search">
									<button class="btn btn-success my-2" type="submit">Search</button>
								</form>
							</div>
						</div>
					</section>

					<div class="album py-5 bg-light mx-3 mb-4">
						<div class="container">
							<div class="row">


								<!-- place additional content -->


							</div>
						</div>
					</div>
					
			<!-- footer -->
			<footer class="container">
				<p class="float-right"><a href="#">Back to top</a></p>
				<p>
					&copy; 2019-2020 DHSC Games. <br> &middot; <a href="#">About</a>
					&middot; <a href="#">Contact</a> &middot;
				</p>
			</footer>
			<!-- Footer ends here -->
			
				</main>
				<!-- main contents end here -->
			</div>
		</div>
	</div>
	<!-- main wrapper ends here -->

	<!-- Bootstrap Core JSS -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
		
		<%
	}
	else
		response.sendRedirect("index.jsp");
%>
		
</body>
		
</html>
