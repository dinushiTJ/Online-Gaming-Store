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

<title>DHSC Games Cart</title>

<!-- Bootstrap core CSS -->
<link href="it19075754_resources/css/bootstrap.min.css" rel="stylesheet">
<link href="it19075754_resources/css/storestyles.css" rel="stylesheet">
<link rel="icon" href="it19075754_resources/images/sidebar/home.ico"
	type="image/x-icon">
<link href="carousel.css" rel="stylesheet">

<style>
.goStore {
	background-color: rgb(185, 127, 110);
	color: white;
}

.goStore:hover {
	background-color: rgb(9, 40, 99);
	color: white;
}

.btn-success {
	background-color: rgb(146, 112, 112);
	border-color: rgb(146, 112, 112);
}

.btn-success:hover {
	background-color: rgb(75, 69, 69);
	border-color: rgb(87, 84, 84);
}
</style>


</head>

<!-- getting list of cart items -->
<%
	if (session.getAttribute("userName") != null) {
%>

<%
	List<Game> myCart = (List<Game>) request.getAttribute("MYCART");
%>

<body>
	<!-- main wrapper -->
	<div class="container-fluid">
		<div class="row bg-d">

			<!-- sidebar contents -->
			<nav
				class="col-sm-1 col-md-1 col-lg-1 d-none d-md-block sidebar bg-d">
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
						<li class="nav-item text-center"><a class="nav-link active"
							href="#"><img class="sidebar-ico"
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
				<main role="main"
					class="col-md-12 col-sm-12 ml-sm-auto col-lg-12 pt-3 main-page">

					<div class="col">
						<form class="form-inline mt-md-0   justify-content-end"
							action="store.jsp" method="post">
							<button class="btn goStore mb-2" type="submit">Go To
								Store</button>
						</form>
					</div>

					<section class="jumbotron text-center mx-3 text-black"
						style="background: transparent url('it19075754_resources/images/bg/bg3.jpg') no-repeat center center/cover; height: 600px;">
						<div class="container mx-4 my-5 py-3 px-4">

							<div class="col">
								<form class="form-inline mt-2 mt-md-0   justify-content-center">
									<input class="form-control mr-sm-2" type="text"
										placeholder="Search Cart" aria-label="Search">
									<button class="btn btn-success my-2" type="submit">Search</button>
								</form>
							</div>
						</div>
					</section>

					<!-- main contents of the page -->
					<div class="album py-5 bg-light mx-3 mb-4">
						<div class="container">
							<!-- displaying existing cart items from the db -->
							<div class="row">
								<%
									if (myCart != null) {
											if (myCart.isEmpty() == false) {
								%>

								<div
									class="col-12 col-lg-12 mb-5 d-flex justify-content-between align-items-center">
									<div
										class="col-3 ml-0 pl-0 btn-group align-content-center justify-content-center">
										<form action="CartServicesServlet" method="post">
											<input type="hidden" name="action" value="clearCart" /> <input
												type="hidden" name="userid" value=2 />
											<button type="submit" class="btn btn-md btn-secondary mr-4">Clear
												Cart</button>
										</form>
										<div style="clear: both;"></div>
										<form action="CartServicesServlet" method="post">
											<input type="hidden" name="action" value="placeOrder" /> <input
												type="hidden" name="userid" value=2 />
											<button type="submit" class="btn btn-md btn-success">Place
												Order</button>
										</form>
									</div>
								</div>

								<%
									for (Game grabGame : myCart) {
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
											<div
												class="d-flex justify-content-between align-items-center">
												<div class="btn-group">
													<form action="CartServicesServlet" method="post">
														<input type="hidden" name="action" value="removeFromCart" />
														<input type="hidden" name="gameid"
															value="<%=grabGame.getgID()%>" />
														<button type="submit" class="btn btn-sm btn-danger">Remove
															from cart</button>
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
										<h5 class="card-text text-white align-self-center ml-3">Cart
											is Empty</h5>
									</div>
								</div>

								<%
									}
								%>
								<!-- cart items end here -->
							</div>
							<div class="row">


								<!-- space for additional stuff -->


							</div>
						</div>
					</div>


					<!-- footer -->
					<footer class="container">
						<p class="float-right">
							<a href="#">Back to top</a>
						</p>
						<p>
							&copy; 2019-2020 DHSC Games. <br> &middot; <a href="#">About</a>
							&middot; <a href="#">Contact</a> &middot;
						</p>
					</footer>
					<!-- Footer ends here -->
				</main>
			</div>
		</div>
	</div>
	<!-- main wrapper ends here -->

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
