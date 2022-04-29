<!-- @author IT19075754 Jayasinghe D.T. -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, model.*"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">

<title>DHSC Games Store</title>

<!-- Bootstrap core CSS -->
<link href="it19075754_resources/css/bootstrap.min.css" rel="stylesheet">
<link href="it19075754_resources/css/storestyles.css" rel="stylesheet">
<link rel="icon" href="it19075754_resources/images/sidebar/home.ico"
	type="image/x-icon">

<style>
.goBack {
	background-color: rgb(241, 198, 56);
}

.goBack:hover {
	background-color: yellow;
}

h1 {
	align-items: center;
}
</style>
<!-- Custom styles for this template -->
<link href="carousel.css" rel="stylesheet">
</head>

<%
	if (session.getAttribute("userName") != null) {
%>

<!-- getting the list of games -->
<%
	List<Game> allGames = (List<Game>) request.getAttribute("ALLGAMES");
%>

<body>
	<!-- wrapper-->
	<div class="container-fluid custom-height">
		<div class="row bg-d">

			<!-- sidebar contents -->
			<nav
				class="col-sm-1 col-md-1 col-lg-1 d-none d-md-block sidebar bg-d ">
				<div class="sidebar-sticky bg-d ">
					<ul class="nav flex-column">
						<li class="nav-item  text-center"><a class="nav-link "
							href="gamerHome.jsp"> <img class="sidebar-ico"
								src="it19075754_resources\images\sidebar\home.ico"
								draggable="false">
								<p class="text-white">Home</p>
						</a></li>
						<li class="nav-item text-center"><a class="nav-link active"
							href="#"><img class="sidebar-ico"
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
			<!-- sidebar contents end here-->

			<!-- main page contents-->
			<div class="container-fluid" style="margin: 20px 0 20px 184px;">
				<main role="main"
					class="col-md-12 col-sm-12 ml-sm-auto col-lg-12 pt-3 main-page">

					<div class="col">
						<form class="form-inline mt-md-0 justify-content-end"
							action="gamerHome.jsp" method="post">
							<button class="btn goBack mb-2" type="submit">Go To Home</button>
						</form>
					</div>

					<section class="jumbotron my-2 pt-7 text-black"
						style = "background: transparent url('it19075754_resources/images/bg/bg2.jpg') no-repeat center center/cover; height:580px;">

						<div class="container mx-4 my-5 py-2 px-4">
							
							<div class="col">
								<form class="form-inline mt-3 mt-md-0   justify-content-center">
									<input class="form-control mr-sm-2" type="text"
										placeholder="Search Games" aria-label="Search">
									<button class="btn btn-warning my-2" type="submit">Search</button>
								</form>
							</div>
						</div>
					</section>

					<div class="album py-5 bg-light mx-3 mb-4 ">
						<div class="container">
							<div class="row">

								<!-- printing existing games in the db-->
								<%
									if (allGames != null) {
											if (allGames.isEmpty() == false) {
												for (Game grabGame : allGames) {
								%>

								<div class="col-md-4">
									<div class="card mb-4 shadow-sm">
										<div
											class="row justify-content-center align-content-center m-1"
											style="background: transparent url('<%=grabGame.getBanner()%>') no-repeat center center/cover; height: 224px">
										</div>
										<div class="card-body">
											<h5 class="card-text"><%=grabGame.getName()%></h5>
											<p class="card-text"><%=grabGame.getDescription()%></p>
											<b><p class="card-text">
													Price:
													<%=grabGame.getPrice()%></p></b>

											<div class="d-flex justify-content-end align-items-center">
												<div class="btn-group">
													<form action="StoreServicesServlet" method="post">
														<input type="hidden" name="action" value="addToCart" /> <input
															type="hidden" name="gameid"
															value="<%=Integer.toString(grabGame.getgID())%>" /> <a></a>
														<button type="submit" class="btn btn-sm btn-warning">
															<b>Add To Cart</b>
														</button>
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>

								<%
									}
											}
										} else {
								%>

								<div
									class="col d-flex justify-content-between align-items-center">
									<div
										class="col btn-group align-content-center justify-content-center">
										<img width="50px"
											src="it19075754_resources/images/ico/emptycart.ico"
											draggable="false" />
										<h5 class="card-text text-white align-self-center ml-3">Store
											is Empty</h5>
									</div>
								</div>

								<%
									}
								%>

								<!-- printing existing games in the db ends here-->

							</div>
							<div class="row">




								<!-- space for additional stuff -->




							</div>
						</div>
					</div>
					<!-- wrapper ends here -->
					
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
			</div>
			<!-- main page contents end here-->
		</div>
	</div>

	<!-- bootstrap Core JSS -->
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
