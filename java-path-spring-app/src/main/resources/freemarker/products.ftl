<!DOCTYPE html>
<html lang="en">
<#import 'partial/siteTemplate.ftl' as siteTemplate>
<head>
	<meta charset="UTF-8">
	<title>Title</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<@siteTemplate.navbar currentPage="products"/>
<div class="container">
	<div class="row" align="center">
		<div class="col">
			<table class="table table-hover">
				<thead>
				<tr>
					<th>#</th>
					<th>Serial</th>
					<th>Name</th>
					<th>Price</th>
					<th>Category</th>
				</tr>
				</thead>
				<tbody>
                <#list allProducts as product>
					<tr>
						<td>${product?counter}</td>
						<td>${product.serial}</td>
						<td>${product.name}</td>
						<td>${product.price}&euro;</td>
						<td>${product.category.description}</td>
					</tr>
                </#list>
				</tbody>
			</table>
		</div>
	</div>
</div>
<@siteTemplate.footer/>
</body>
</html>
