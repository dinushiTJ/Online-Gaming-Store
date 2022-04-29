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

<title>DHSC Games Orders</title>

<!-- Bootstrap core CSS -->
<link href="it19075754_resources/css/bootstrap.min.css" rel="stylesheet">
<link href="it19075754_resources/css/storestyles.css" rel="stylesheet">
<link rel="icon" href="it19075754_resources/images/sidebar/home.ico"
	type="image/x-icon">

<style>
.btn-success{
	background-color: rgb(13, 167, 159);
	color: rgb(0, 0, 0);
	border-color: rgb(13, 167, 159);
}


.btn-success:hover{
	background-color: rgb(0, 0, 0);
	color: rgb(255, 255, 255);
	border-color: rgb(0, 0, 0);
}

</style>
<!-- Custom styles for this template -->
<link href="carousel.css" rel="stylesheet">
</head>

<%
	if(session.getAttribute("userName") !=null)
	{
%>

<%
	List<Order> orders = (List<Order>) request.getAttribute("ALLORDERS");
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
							href="profile.jsp"><img class="sidebar-ico"
								src="it19075754_resources\images\sidebar\profile.ico"
								draggable="false">
								<p class="text-white">Profile</p> </a></li>
						<li class="nav-item text-center"><a class="nav-link active"
							href="PaymentServicesServlet"><img class="sidebar-ico"
								src="it19075754_resources\images\sidebar\Payment.ico"
								draggable="false">
								<p class="text-white">Orders</p> </a></li>
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
						</form>
					</div>
					
					
					<section class="jumbotron text-center mx-3 text-black"
						style="background: transparent url('it19075754_resources/images/bg/bg4.jpg') no-repeat center center/cover; ">
						<div class="container mx-2 my-6 py-6 px-7">
							
						<div class="col">
								<form class="form-inline mt-2 mt-md-0   justify-content-center">
									<input class="form-control mr-sm-2" type="text"
										placeholder="Search Orders" aria-label="Search">
									<button class="btn btn-success my-2" type="submit">Search</button>
								</form>
							</div>
						</div>
					</section>
					

					<div class="album py-5 bg-light mx-3 mb-4">
						<div class="container">
							<div class="row">
								<div class="col text-light">
								
								<% if (orders != null) { %>
								<table class="container-fluid">

								<tr>
								<th>Order ID</th>
								<th style = "text-align: center;">Date</th>
								<th>Cost</th>
								<th>Discount</th>
								<th style = "text-align: center;">Total</th>
								<th style = "text-align: center;">Payment Status</th>		
								<th style = "text-align: center;">Action</th>				
								</tr>
								<%for (Order order : orders) { %>
								<tr>
								<td style = "text-align: center;"> <%=order.getoID() %></td>
								<td><%=order.getOrderDate() %></td>
								<td style = "text-align: right;"><%=order.getOrderPayment() + order.getOrderDiscount() %></td>
								<td style = "text-align: right;"><%=order.getOrderDiscount() %></td>
								<td style = "text-align: right;"><%=order.getOrderPayment()%></td>
								<td style = "text-align: center;"><%=order.getPaymentStatus() %></td>		
								<td>
								<div class="container d-none d-flex d-flex-wrap justify-content-start my-2">
												
												<%if (order.getPaymentStatus().equals("pending")) {%>
												<form action="PaymentServicesServlet" method="post">
													<input type="hidden" name="action" value="newPayment" /> <input
														type="hidden" name="orderid"
														value="<%=Integer.toString(order.getoID())%>" /> <a></a>
													<button type="submit" class="btn btn-sm  btn-success mr-4">Pay Now</button>
												</form>
												
												<form action="PaymentServicesServlet" method="post">
													<input type="hidden" name="action" value="deleteOrder" /> <input
														type="hidden" name="orderid"
														value="<%=Integer.toString(order.getoID())%>" /> <a></a>
													<button type="submit" class="btn btn-sm btn-danger">Delete</button>
												</form>
												<% } else { %>
												<form action="PaymentServicesServlet" method="post">
													<input type="hidden" name="action" value="getShippingInfo" /> <input
														type="hidden" name="orderid"
														value="<%=Integer.toString(order.getoID())%>" /> <a></a>
													<button type="submit" class="btn btn-sm btn-warning  mr-4">Update Shipping info</button>
												</form>
												<% } %>
												
											</div>
								
								</td>				
								</tr>
								<% } %>
								</table>
								<% }%>
								
								</div>
								
								
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
