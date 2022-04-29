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

<title>DHSC Games Home</title>

<!-- Bootstrap core CSS -->
<link href="it19075754_resources/css/bootstrap.min.css" rel="stylesheet">
<link href="it19075754_resources/css/storestyles.css" rel="stylesheet">
<link rel="icon" href="it19075754_resources/images/sidebar/home.ico"
	type="image/x-icon">

<style>
.signOut {
	background-color: rgb(211, 61, 61);
	color: white;
}

.signOut:hover {
	background-color: rgb(255, 0, 0);
}

.card {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
	max-width: 300px;
	margin: auto;
	text-align: center;
	font-family: arial;
}

.price {
	color: grey;
	font-size: 22px;
}

.card button {
	border: none;
	outline: 0;
	padding: 12px;
	color: white;
	background-color: #000;
	text-align: center;
	cursor: pointer;
	width: 100%;
	font-size: 18px;
}

.card button:hover {
	background-color: rgb(117, 111, 109);
}
</style>
<!-- Custom styles for this template -->
<link href="carousel.css" rel="stylesheet">
</head>

<%
	if (session.getAttribute("userName") != null) {
%>

<body>

	<div class="container-fluid">
		<div class="row bg-d">
			<!-- sidebar content -->
			<nav
				class="col-sm-1 col-md-1 col-lg-1 d-none d-md-block sidebar bg-d">
				<div class="sidebar-sticky bg-d">
					<ul class="nav flex-column">
						<li class="nav-item  text-center"><a class="nav-link active"
							href="#"> <img class="sidebar-ico"
								src="it19075754_resources\images\sidebar\home.ico"
								draggable="false">
								<p class="text-white">Home</p>
						</a></li>
						<li class="nav-item text-center"><a class="nav-link"
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
						<li class="nav-item text-center"><a class="nav-link "
							href="PaymentServicesServlet"><img class="sidebar-ico"
								src="it19075754_resources\images\sidebar\Payment.ico"
								draggable="false">
								<p class="text-white">Orders</p> </a></li>
					</ul>
				</div>
			</nav>
			<!-- sidebar contents end here -->

			<!-- main contents -->
			<div class="container-fluid" style="margin: 20px 0 20px 184px;">
				<main role="main" class="col-md-12 col-sm-12 ml-sm-auto col-lg-12 pt-3 main-page">

					<div class="col">
						<form class="form-inline mt-md-0 justify-content-end"
							action="logout.jsp" method="post">
							<button class="btn signOut mb-3" type="submit">Sign Out</button>
						</form>
					</div>


					<section class="jumbotron text-center pr-2 mx-3 text-black"
						style="background: transparent url('it19075754_resources/images/bg/bg1.jpg') no-repeat center center/cover; height: 600px;">

					</section>

					<div class="album py-5 bg-light mx-3 mb-4 ">
						<div class="container">
							<div class="row">


								<!-- place additional stuff here -->
								<!-- remove following code -->



								<!-- game 1 -->
								<div class="col-md-4">
									<div class="card mb-4 shadow-sm">
										<img src="it19075754_resources/images/store/smb.jpg"
											alt="game" style="width: 100%; height: 25%">
										<h1>Super-Mario Galaxy</h1>
										<p>Game developed by nintendo</p>
										<p class="price">FREE</p>
										<button type="submit" class="btn btn-sm btn-warning">
											<b>Coming Soon</b>
										</button>
									</div>
								</div>

								<!-- game 2 -->
								<div class="col-md-4">
									<div class="card mb-4 shadow-sm">
										<img src="it19075754_resources/images/store/WD.jpg"
											alt="game" style="width: 100%; height: 25%">
										<h1>Gun Game</h1>
										<p>Game published and developed for arcades by Activision.</p>
										<p class="price">FREE</p>
										<button type="submit" class="btn btn-sm btn-warning">
											<b>Coming Soon</b>
										</button>
									</div>
								</div>
								
								<!-- game 3 -->
								<div class="col-md-4">
									<div class="card mb-4 shadow-sm">
										<img src="it19075754_resources/images/store/pm.jpg"
											alt="game" style="width: 100%; height: 25%">
										<h1>pokemon</h1>
										<p>Pokémon is a series of video games. Anyone can play</p>
										<p class="price">FREE</p>
										<button type="submit" class="btn btn-sm btn-warning">
											<b>Coming Soon</b>
										</button>
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
	</div>

	<!-- Bootstrap Core JSS -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>

	<%
		} else
			response.sendRedirect("index.jsp");
	%>

</body>

</html>