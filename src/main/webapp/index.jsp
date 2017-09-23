<!DOCTYPE HTML>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html >
	<head>
		<title>Finance Manager</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<link href='http://fonts.googleapis.com/css?family=Oxygen:400,300,700' rel='stylesheet' type='text/css'/>
		<link rel="stylesheet" href="<c:url value='css/bootstrap.min.css'/>" >
		<!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="<c:url value='js/skel.min.js'/>"></script>
		<script src="<c:url value='js/skel-panels.min.js'/>"></script>
		<script src="<c:url value='js/init.js'/>"></script>
		<noscript>
			<link rel="stylesheet" href="<c:url value='css/skel-noscript.css'/>" />
			<link rel="stylesheet" href="<c:url value='css/style.css'/>" />
		</noscript>
		<!--[if lte IE 8]><link rel="stylesheet" href="<c:url value='css/ie/v8.css'/>" /><![endif]-->
		<!--[if lte IE 9]><link rel="stylesheet" href="<c:url value='css/ie/v9.css'/>" /><![endif]-->
	</head>
	<body class="homepage">

	<!-- Header -->
		<div id="header">
			<div class="container">

				<!-- Logo -->
					<div id="logo">
						<h1><a href="#">Finance manager</a></h1>
						<span>manage your spendings easily</span>
					</div>

				<!-- Nav -->
					<nav id="nav">
						<ul>
							<li class="active">
								<a href="<c:url value='/'/>">
								<div class="icon-block"><img src="<c:url value='/images/icons/if_cash_register_17219.png'/>" class="icon" alt=""/></div>
								<div class="text-block">Transactions</div>
								</a>
							</li>
							<li >
								<a href="<c:url value='/deposits'/>">
								<div class="icon-block"><img src="<c:url value='/images/icons/if_piggy_bank_58877.png'/>" class="icon" alt=""/></div>
								<div class="text-block">Deposit</div>
								</a>
							</li>
							<li>
								<a href="<c:url value='/credits'/>">
								<div class="icon-block"><img src="<c:url value='/images/icons/if_03_61467.png'/>" class="icon" alt=""/></div>
								<div class="text-block">Credit</div>
								</a>
							</li>

						</ul>
					</nav>

			</div>
		</div>
	<!-- Header -->

	<!-- Main -->
		<div id="main">

			<div class="container-fluid">

				<div class="row">

					<!-- Content -->
					<div class=" col-md-9 offset-md-2 custyle">
						<table class="table table-striped custab" >
							<thead>
							<tr>
								<th>ID </th>
								<th>Type</th>
								<th>Description</th>
								<th>Sum</th>
								<th>Category</th>
								<th>Date</th>
								<th class="text-center">Action</th>
							</tr>
							</thead>
							<c:forEach items="${listOfTransactions}" var="item">
								<tr class='clickable-row' />
									<td>${item.getId()}</td>
									<td>${item.getTrType().toString()}</td>
									<td>${item.getDescription()}</td>
									<td>${item.getSum()}</td>
									<td>${item.getCategory()}</td>
									<td>${item.getDate().toString()}</td>
									<td class="text-center">
										<div class="ui-group-buttons">
										<a href="http://www.jquery2dotnet.com" class="btn btn-success" role="button">
										<span class="glyphicon glyphicon-ok"></span> Edit</a>
										<div class="or"></div>
										<a href="http://www.jquery2dotnet.com" class="btn btn-danger" role="button">
										<span class="glyphicon glyphicon-remove"></span> Delete</a>
										</div>
									</td>
								</tr>

							</c:forEach>


						</table>
					</div>
					<!-- /Content -->

					<!-- Sidebar -->
					<div id="sidebar" class="col-md-3">
						<section>
							<header>
								<h2> Overall balance
									<img src="<c:url value='/images/icons/if_history_58875.png'/>" class="icon"
										 style="margin-bottom: -10px" alt=""/></h2>
								<span class="byline">Here you can review your balance</span>

							</header>
							<h2 class="balance">
								<img src="<c:url value='/images/icons/if_credit_card_17221.png'/>" class="icon" style="margin-bottom: -10px" alt=""/>
								$100.000
							</h2>
							<br/>
							<!--<p>Donec leo, vivamus fermentum nibh in augue praesent a lacus at urna congue rutrum. Maecenas luctus lectus at sapien. Consectetuer adipiscing elit.</p>-->
							<!--<ul class="default">-->
								<!--<li><a href="#">Pellentesque quis lectus gravida blandit.</a></li>-->
								<!--<li><a href="#">Lorem ipsum consectetuer adipiscing elit.</a></li>-->
								<!--<li><a href="#">Phasellus nec nibh pellentesque congue.</a></li>-->
								<!--<li><a href="#">Cras aliquam risus pellentesque pharetra.</a></li>-->
								<!--<li><a href="#">Duis non metus commodo euismod lobortis.</a></li>-->
								<!--<li><a href="#">Lorem ipsum dolor adipiscing elit.</a></li>-->
							<!--</ul>-->
							<button type="button" class="btn btn-success btn-lg btn3d">Record income</button>
							<button type="button" class="btn btn-danger btn-lg btn3d">Record the expense</button>

						</section>
					</div>
					<!-- Sidebar -->

				</div>

			</div>

</div>
	<!-- Copyright -->
		<div id="copyright">
			<div class="container">
				Design: <a href="http://templated.co">TEMPLATED</a> Images: <a href="http://unsplash.com">Unsplash</a> (<a href="http://unsplash.com/cc0">CC0</a>)
			</div>
		</div>

	</body>
</html>