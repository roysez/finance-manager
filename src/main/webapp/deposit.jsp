<!DOCTYPE HTML>
<!--
	Monochromed by TEMPLATED
    templated.co @templatedco
    Released for free under the Creative Commons Attribution 3.0 license (templated.co/license)
-->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
	<title>Finance Manager</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<link href='http://fonts.googleapis.com/css?family=Oxygen:400,300,700' rel='stylesheet' type='text/css'/>

	<link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>" >

	<!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="<c:url value='/js/skel.min.js'/>"></script>
	<script src="<c:url value='/js/skel-panels.min.js'/>"></script>
	<script src="<c:url value='/js/init.js'/>"></script>
	<script src="<c:url value='/js/deposit-operations.js'/>"></script>


	<link 	rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" >

	<noscript>
		<link rel="stylesheet" href="/css/skel-noscript.cs'/>" />
		<link rel="stylesheet" href="/css/style.css" />
	</noscript>

	<!--[if lte IE 8]><link rel="stylesheet" href="<c:url value='/css/ie/v8.css'/>" /><![endif]-->
	<!--[if lte IE 9]><link rel="stylesheet" href="<c:url value='/css/ie/v9.css'/>" /><![endif]-->

	<!-- For popup ! -->
	<link rel='stylesheet prefetch' href='http://cdn.jsdelivr.net/jquery.magnific-popup/1.0.0/magnific-popup.css'>

	<style>

		.white-popup {
			position: relative;
			background: #FFF;
			padding: 40px;
			width: auto;
			max-width: 500px;
			margin: 20px auto;
			transition: 1s all;
		}

		.mfp-bg {}

		.mfp-fade.mfp-bg {
			opacity: 0;
			-webkit-transition: all 0.15s ease-out;
			-moz-transition: all 0.15s ease-out;
			transition: all 0.15s ease-out;
		}


		/* overlay animate in */

		.mfp-fade.mfp-bg.mfp-ready {
			opacity: 0.8;
		}


		/* overlay animate out */

		.mfp-fade.mfp-bg.mfp-removing {
			opacity: 0;
		}

		.mfp-fade.mfp-wrap .mfp-content {
			opacity: 0;
			transition: all 0.4s ease-out;
		}

		.mfp-fade.mfp-wrap.mfp-ready .mfp-content {
			opacity: 1;
		}

		.mfp-fade.mfp-wrap.mfp-removing .mfp-content {
			opacity: 0;
		}
	</style>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
	<!-- close for popup -->
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
						<li >
							<a href="<c:url value='/'/>">
								<div class="icon-block"><img src="<c:url value='/images/icons/if_cash_register_17219.png'/>" class="icon" alt=""/></div>
								<div class="text-block">Transactions</div>
							</a>
						</li>
						<li class="active">
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
		<c:if test="${error!=null}">
			<c:if test="${!error.isEmpty()}">
				<div class="alert alert-danger">
					<p>${error}</p>
				</div>
			</c:if>
		</c:if>
		<div class="container-fluid">
			<div class="row">

				<!-- Sidebar -->
				<div id="sidebar" class="4u">
					<section>
						<header>
							<h2>Deposits <img src="images/icons/if_safe_17226.png" class="icon"
											  style="margin-bottom: -10px" alt=""/></h2>
							<span class="byline">Here you can find all your deposits</span>
						</header>
						<p>When money is deposited into a banking account, it earns interest. This means that, at fixed intervals, a small percentage of the account's total is added to the amount of money already in the account.
						</p>
						<ul class="default">
							<li>
								<a href="#add-deposit-popup" type="button"
									class="btn open-popup-link btn-success btn-lg btn3d">
									Add deposit
								</a>
							</li>
							<%--<li> <button type="button" class="btn btn-warning btn-xs btn3d">Change status of deposit</button></li>--%>

						</ul>
					</section>
				</div>
				<!-- Sidebar -->

				<!-- Add deposit popup-->
				<div id="add-deposit-popup" class="white-popup mfp-hide">
					<div >
						<h4>Add deposit:</h4>
						<div class="panel panel-default">
							<div class="panel-body form-horizontal payment-form">
								<form:form action="deposits/"  modelAttribute="deposit" method="POST" class="deposit-form" >
									<div class="form-group">

										<label for="sum" class="col-sm-12 control-label" >Sum for deposit</label>
										<div class="col-sm-12">
											<form:input required="required" placeholder="Example: 1.000$"
														path="sum" id="sum" type="text"
														class="form-control" name="sum" />


										</div>

									</div>
									<div class="form-group">

										<label for="percentages" class="col-sm-3 control-label" >Percentages:</label>
										<div class="col-sm-12">
											<form:input required="required" placeholder="Example: 3%"
														path="percentages" id="percentages" type="text"
														class="form-control" name="percentages" />


										</div>

									</div>

									<div class="form-group">

										<label for="description" class="col-sm-3 control-label" >Description:</label>
										<div class="col-sm-12">
											<form:input placeholder="say something :)"
														path="description" id="description" type="text"
														class="form-control" name="description" />


										</div>

									</div>

									<div class="form-group">

										<label for="term" class="col-sm-3 control-label" >Term:</label>
										<div class="col-sm-12">
											<form:input placeholder="How many months:"
														path="term" id="term" type="text"
														class="form-control" name="term" />


										</div>

									</div>
									<div class="form-group">
										<div class="col-sm-12 text-right">
											<button type="submit" class="btn btn-default preview-add-button">
												<i class="fa fa-plus"></i> Add deposit
											</button>
										</div>
									</div>
								</form:form>

							</div>
						</div>
					</div> <!-- / panel preview -->
				</div>
				<!-- Add deposir popup close-->

				<!-- Content -->
				<div id="content" class="8u skel-cell-important">
						<table class="table table-striped table-hover">
							<thead>
							<tr>

								<th>Id</th>
								<th>Sum</th>
								<th>Income</th>
								<th>Income in %</th>
								<th>Paid/Term (m)</th>
								<th>Status</th>
								<th>Description</th>
								<th>Actions</th>
							</tr>
							</thead>
							<tbody>
							<c:forEach items="${deposits}" var="item">
								<tr class="success" id="row-${item.getId()}">

									<td id="col-id-${item.getId()}">${item.getId()}</td>
									<td id="col-sum-${item.getId()}">${item.getSum()} $</td>
									<td id="col-income-${item.getId()}">${item.getIncome()} $</td>
									<td id="col-perc-${item.getId()}">${item.getPercentages()}%</td>
									<td id="col-term-${item.getId()}">${item.getMonthPaid()}/${item.getTerm()}</td>
									<td class="status" id="col-status-${item.getId()}">${item.getDepositStatus().toString()}</td>
									<td id="col-desc-${item.getId()}">${item.getDescription()}</td>
									<td>
										<div class="ui-group-buttons">
											<c:if test="${item.checkIfCompleted()}">
												<button onclick="doCharge(${item.getId()})"  class="btn btn-success disabled" role="button"><i class="fa fa-refresh  "></i></button>
												<div class="or"></div>
												<button onclick="deleteDeposit(${item.getId()})" class="btn btn-danger" role="button"><i class="fa fa-trash-o"></i></button>
											</c:if>
											<c:if test="${!item.checkIfCompleted()}">
												<button id="btn-chrg-${item.getId()}" onclick="doCharge(${item.getId()})" class="btn btn-success " role="button"><i class="fa fa-refresh  "></i></button>
												<div class="or"></div>
												<button id="btn-delete-${item.getId()}" onclick="deleteDeposit(${item.getId()})" class="btn btn-danger disabled" role="button"><i class="fa fa-trash-o"></i></button>
											</c:if>
										</div>
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<!-- /Content -->

			</div>
		<!-- For popup -->
		<script src='http://code.jquery.com/jquery-2.2.4.min.js'></script>
		<script src='http://cdn.jsdelivr.net/jquery.magnific-popup/1.0.0/jquery.magnific-popup.min.js'></script>
		<script src="<c:url value='/js/popup-show.js'/>"></script>
		<!--  For popup close -->
		</div>

	<!-- Main -->
	<!-- Copyright -->
		<div id="copyright">
			<div class="container">
				Design: <a href="">Roysez</a>
			</div>
		</div>

	</body>
</html>