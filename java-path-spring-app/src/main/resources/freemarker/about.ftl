<!DOCTYPE html>
<html lang="en">
<#import 'partial/siteTemplate.ftl' as siteTemplate>
<#import "/spring.ftl" as spring />
<head>
	<meta charset="UTF-8">
	<title>Title</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<@siteTemplate.navbar currentPage="about"/>
<div class="container">
	<div class="row" align="center">
		<div class="col">
			<h1><@spring.message "about.header"/></h1>
			<h4><@spring.message "about.names"/></h4>
			<p><@spring.message "about.description"/></p>
		</div>
	</div>
	<hr>
	<div class="row">
		<div class="col">
			<p id="timesVisitedParagraph"></p>
		</div>
	</div>
</div>
<@siteTemplate.footer/>

<#-- Simple script which reads a "counter" cookie and replaces a paragraph with a text notifying the user about
the cookie (more specifically: the times he has visited the page) -->
<script>
	const cookieValue = getCookie("counter");
	document.getElementById("timesVisitedParagraph").textContent = "You have visited this page " + cookieValue + " " +
			"times!";

	// Function copied from the internet, basically access the wanted cookie
	function getCookie(name) {
		let cookie = {};
		document.cookie.split(';').forEach(function (el) {
			let [k, v] = el.split('=');
			cookie[k.trim()] = v;
		})
		return cookie[name];
	}
</script>
</body>
</html>
