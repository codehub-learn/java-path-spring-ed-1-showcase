<#macro navbar currentPage>
	<!-- Navbar -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="/mvc">E-Shop.gr</a>

		<div class="collapse navbar-collapse" id="navbarWithLogOut">
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<li class="nav-item">
					<a class="nav-link ${(currentPage=='home')?then('active','')}" href="/mvc">Home <span
								class="sr-only">
							(current)
						</span></a>
				</li>
				<li class="nav-item">
					<a class="nav-link ${(currentPage=='products')?then('active','')}" href="/mvc/products">Our
						Products</a>
				</li>
				<li class="nav-item">
					<a class="nav-link ${(currentPage=='findOrder')?then('active','')}" href="/mvc/findOrder">Find Your
						Order</a>
				</li>
				<li class="nav-item">
					<a class="nav-link ${(currentPage=='about')?then('active','')}" href="/mvc/about">About Us</a>
				</li>
			</ul>
			<a href="/mvc/registerCustomer"
			   class="btn btn${(currentPage=='registerCustomer')?then('','-outline')}-success  my-2 my-sm-0"
			   role="button">Register</a>
		</div>
	</nav>
	<!-- Navbar -->
</#macro>

<#macro footer>
	<!-- Footer -->
	<footer class="bg-dark text-center text-white fixed-bottom">
		<!-- Copyright -->
		<div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
			© 2021 Copyright:
			<a class="text-white" href="/mvc">E-Shop.gr</a>
		</div>
		<!-- Copyright -->
	</footer>
	<!-- Footer -->
</#macro>
