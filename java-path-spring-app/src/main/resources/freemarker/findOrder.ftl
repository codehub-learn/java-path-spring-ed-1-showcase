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
<@siteTemplate.navbar currentPage="findOrder"/>
<div class="container">
	<div class="row" align="center">
		<div class="col">
			<form action="/mvc/findOrder" method="get">
				<label for="orderId">Order ID</label>
				<input type="number" name="orderId" id="orderId" placeholder="Enter ID Here">
                <#-- If value is not null-->
                <#if error??>
					<div>
						<small class="text-danger">
							The order you searched for does not exist.
						</small>
					</div>
                </#if>
				<button type="submit" class="btn btn-primary">Search</button>
			</form>
		</div>
	</div>
	<hr>
	<#if order??>
        <#include "partial/order.ftl">
	</#if>

</div>
<@siteTemplate.footer/>
</body>
</html>
