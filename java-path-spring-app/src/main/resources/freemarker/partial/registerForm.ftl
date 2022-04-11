<#import "/spring.ftl" as spring />
<#-- The value="${variable!}" basically means "if exists", and is put there in order to re-add the
customer in case he comes from an error redirection etc. -->
<form method="post" action="/mvc/registerCustomer" name="customer" id="customer">
	<div class="form-row">
		<div class="form-group col-12">
			<label for="email">Email</label>
			<input type="email" class="form-control" name="email" id="email" placeholder="user@example.com">
		</div>
	</div>
	<div class="form-row">
		<div class="form-group col-md-5">
			<label for="firstname">First Name</label>
			<input type="text" class="form-control" name="firstname" id="firstname" placeholder="John">
		</div>
		<div class="form-group col-md-5">
			<label for="lastname">Last Name</label>
			<input type="text" class="form-control" name="lastname" id="lastname" placeholder="Doe">
		</div>
		<div class="form-group col-md-2">
			<label for="age">Age</label>
			<input class="form-control" name="age" id="age" placeholder="24">
		</div>
	</div>
	<div class="form-row">
		<div class="form-group col-md-10">
			<label for="address">Address</label>
			<input type="text" class="form-control" name="address" id="address" placeholder="1234 Main St.">
		</div>
		<div class="form-group col-md-2">
			<label for="customerCategory">Category</label>
			<select id="customerCategory" class="form-control" name="customerCategory"
					id="customerCategory">
				<#list customerCategories as category>
				<#-- If customer category exists, then while iterating, if the category matches the one
				listed in the CustomerCategory enumeration, then add the selected tag. If customerCategory
				does not exist, set as default value for the customerCategory to be INDIVIDUAL, and mark as
				selected the invidivual category-->
					<option>${category}</option>
				</#list>
			</select>
		</div>
	</div>
	<div>
		<#if Session.registeredCustomerID??>
			<small class="text-success">Success! You have registered with ID
				'${Session.registeredCustomerID}'
				. This message will remain here until you close the browser.</small>
		</#if>
	</div>
	<br>
	<button type="submit" class="btn btn-primary">Sign Up</button>
</form>
