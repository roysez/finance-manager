<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html >
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
	<script src="<c:url value='/js/credit-operations.js'/>"></script>


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
				<!-- Nav -->
				<nav id="nav">
					<ul>
						<li >
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
						<li class="active">
							<a href="<c:url value='/credits'/>">
								<div class="icon-block"><img src="<c:url value='/images/icons/if_03_61467.png'/>" class="icon" alt=""/></div>
								<div class="text-block">Credit</div>
							</a>
						</li>

					</ul>
				</nav>

			</div>
		</div>
	<!-- !Header -->

	<!-- Main -->
	<div id="main">
		<div class="container">
			<div id="error-alert" class="alert alert-danger user-alert">
				<p id="error-alert-text" >You don't have enough money to pay</p>
			</div>
			<div class="row">

				<!-- Content -->
				<div id="content" class="12u skel-cell-important">

					<div class="widget blank no-padding">
						<div class="panel panel-default work-progress-table">
							<!-- Default panel contents -->

								<div class="dropdown rounded row">
										<section class="col-md-8">
											<header>
												<h2>Credits History
													<img src="images/icons/if_coins_17220.png" class="icon" style="margin-bottom: -10px" alt=""/>
												</h2>
												<span class="byline">Here you can find all your credits</span>
											</header>


										</section>
										<section class="cold-md-4">
											<a href="#add-credit-popup" type="button"
											   class="btn open-popup-link btn-primary  btn-lg btn3d">
												Add credit to history
											</a>
										</section>
								</div>

							<!-- Table -->
							<table id="mytable" class="table table-bordered">
								<thead >
								<tr>

									<th>Id</th>
									<th>Sum</th>
									<th>Paid money</th>
									<th>Paid/Term (m)</th>
									<th style="width:25%">Progress</th>
									<th>Description</th>
									<th>Status</th>
									<th>Actions</th>
								</tr>
								</thead>
								<tbody>
								<c:forEach items="${credits}" var="item">
									<tr id="row-${item.getId()}">
										<td id="col-id-${item.getId()}">${item.getId()}</td>
										<td id="col-sum-${item.getId()}">${item.getAmountToPay()} $</td>
										<td id="col-pm-${item.getId()}">${item.getPaidMoney().longValue()} $</td>
										<td id="col-term-${item.getId()}">${item.getMonthPaid()}/${item.getTerm()}</td>
										<td>
											<div class="progress">
												<div style="width: ${(item.getPaidMoney()/item.getAmountToPay()*100).longValue()}%;"
													 aria-valuemax="100" aria-valuemin="0"
													 aria-valuenow=" ${(item.getPaidMoney()/item.getAmountToPay()*100).longValue()}"
													 role="progressbar" class="red progress-bar">
													<span> ${(item.getPaidMoney()/item.getAmountToPay()*100).longValue()}%</span>
												</div>
											</div>
										</td>
										<td class="" id="col-desc-${item.getId()}">${item.getDescription()}</td>
										<td class="status" id="col-status-${item.getId()}">${item.getStatus().toString()}</td>

										<td><div class="ui-group-buttons">
											<c:if test="${item.checkIfCompleted()}">
												<button onclick="doCharge(${item.getId()})"  class="btn btn-success disabled" role="button"><i class="fa fa-usd  "></i></button>
												<div class="or"></div>
												<button onclick="deleteCredit(${item.getId()})" class="btn btn-danger" role="button"><i class="fa fa-trash-o"></i></button>
											</c:if>
											<c:if test="${!item.checkIfCompleted()}">
												<button id="btn-chrg-${item.getId()}" onclick="doCharge(${item.getId()})" class="btn btn-success " role="button"><i class="fa fa-usd  "></i></button>
												<div class="or"></div>
												<button id="btn-delete-${item.getId()}" onclick="deleteCredit(${item.getId()})" class="btn btn-danger disabled" role="button"><i class="fa fa-trash-o"></i></button>
											</c:if>
										</div>
										</td>

									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				</div>
				<!-- /Content -->

			</div>

		</div>
	<!-- !Main -->

	<!-- Add credit popup-->
	<div id="add-credit-popup" class="white-popup mfp-hide">
		<div >
			<h4>Add credit:</h4>
			<div class="panel panel-default">
				<div class="panel-body form-horizontal payment-form">
					<form:form action="credits/"  modelAttribute="credit" method="POST" class="credit-form" >
						<div class="form-group">

							<label for="amountToPay" class="col-sm-12 control-label" >Sum of credit</label>
							<div class="col-sm-12">
								<form:input required="required" placeholder="Example: 1.000$"
											path="amountToPay" id="amountToPay" type="text"
											class="form-control" name="amountToPay" />


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
								<form:input placeholder="How many payments to divide"
											path="term" id="term" type="text"
											class="form-control" name="term" />


							</div>

						</div>
						<div class="form-group">
							<div class="col-sm-12 text-right">
								<button type="submit" class="btn btn-default preview-add-button">
									<i class="fa fa-plus"></i> Add credit to history
								</button>
							</div>
						</div>
					</form:form>

				</div>
			</div>
		</div> <!-- / panel preview -->
	</div>
	<!-- Add credit popup close-->

	<!-- Main -->
	<!-- Copyright -->
	<div id="copyright">
		<div class="container">
			Design: <a href="">Roysez</a>
		</div>
	</div>

	<!-- For popup -->
	<script src='http://code.jquery.com/jquery-2.2.4.min.js'></script>
	<script src='http://cdn.jsdelivr.net/jquery.magnific-popup/1.0.0/jquery.magnific-popup.min.js'></script>
	<script src="<c:url value='/js/popup-show.js'/>"></script>
	<!--  For popup close -->
	</body>
</html>