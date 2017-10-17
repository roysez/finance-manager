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
		<script src="<c:url value='/js/transaction-operations.js'/>"></script>


		<%--<link href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">--%>

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
			<c:if test="${error!=null}">
				<c:if test="${!error.isEmpty()}">
				<div class="alert alert-danger">
					<p>${error}</p>
				</div>
				</c:if>
			</c:if>
			<!-- main window -->
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
								<tr class='clickable-row' id="row-${item.getId()}"/>
									<td>${item.getId()}</td>
									<td>${item.getTrType().toString()}</td>
									<td>${item.getDescription()}</td>
									<td>${item.getSum()}</td>
									<td>${item.getCategory().getCategoryName()}</td>
									<td>${item.getDate().toString()}</td>
									<td class="text-center">
										<div class="ui-group-buttons">

										<button  onclick="deleteTransaction(${item.getId()})" class="btn btn-danger" role="button">
										<i class="fa fa-trash-o"></i> Delete</button>
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
							<!-- Header at side bar -->
							<header>
								<h2> Overall balance
									<img src="<c:url value='/images/icons/if_history_58875.png'/>" class="icon"
										 style="margin-bottom: -10px" alt=""/></h2>
								<span class="byline"> Welcome, <i><strong>${user.getFirstName()} ${user.getLastName()}</strong></i>
									<br>
									Here you can review your balance</span>

							</header>
							<!-- Balance -->
							<h2 class="balance">
								<img src="<c:url value='/images/icons/if_credit_card_17221.png'/>" class="icon" style="margin-bottom: -10px" alt=""/>
								$ ${user.getBalance()}
							</h2>
							<br/>
							<!-- Buttons -->
							<a href="#income-popup" type="button"
							   class="btn open-popup-link btn-success btn-lg btn3d">
								 Record income
							</a>
							<a href="#expense-popup" type="button"
							   class="btn open-popup-link btn-danger btn-lg btn3d">
								Record the expense
							</a>
							<a href="#category-popup" type="button"
							   class="btn open-popup-link btn-success btn-lg btn3d">
								Add new category
							</a>


						</section>
					</div>
					<!-- Sidebar -->

				</div>

			</div>
			<!-- main window close -->

			<!-- Add income popup-->
			<div id="income-popup" class="white-popup mfp-hide">
				<div >
					<h4>Record income:</h4>
					<div class="panel panel-default">
						<div class="panel-body form-horizontal payment-form">
							<form:form action="transactions/income"  modelAttribute="transaction" method="POST" class="transaction-form" >
								<div class="form-group">

									<label for="sum" class="col-sm-3 control-label" >Income:</label>
									<div class="col-sm-12">

										<form:input required="required" placeholder="Sum:"
													path="sum"  type="text"
													class="form-control" name="sum" />
									</div>
								</div>
								<div class="form-group">
									<label for="description" class="col-sm-3 control-label">Description:</label>
									<div class="col-sm-12">
										<form:input required="required" placeholder="Description:"
													path="description" id="description" type="text"
													class="form-control" name="description"  />
									</div>
								</div>
								<div class="form-group">
									<label for="tax" class="col-sm-3 control-label">Category:</label>
									<div class="col-sm-12">

										<select name="selectedCategory">
											<c:forEach items="${categoriesList}" var="object">
												<option >${object.getCategoryName()}</option>
											</c:forEach>

										</select>
										<a href="#category-popup" type="button"
										   class="btn open-popup-link btn-default btn-sm ">
											Add new category
										</a>

									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-12 text-right">
										<button type="submit" class="btn btn-default preview-add-button">
											<i class="fa fa-plus"></i> record income
										</button>
									</div>
								</div>
							</form:form>

						</div>
					</div>
				</div> <!-- / panel preview -->
			</div>
			<!-- Add income popup close-->


			<!-- Add expense popup-->
			<div id="expense-popup" class="white-popup mfp-hide">
				<div >
					<h4>Add expense:</h4>
					<div class="panel panel-default">
						<div class="panel-body form-horizontal payment-form">
							<form:form action="transactions/expenses"  modelAttribute="transaction" method="POST" class="transaction-form" >
								<div class="form-group">

									<label for="categoryName" class="col-sm-3 control-label" >Sum:</label>
									<div class="col-sm-12">

										<form:input required="required" placeholder="Sum:"
													path="sum" id="sum" type="text"
													class="form-control" name="sum" />
									</div>
								</div>
								<div class="form-group">
									<label for="tax" class="col-sm-3 control-label">Description:</label>
									<div class="col-sm-12">
										<form:input  required="required" placeholder="Description:"
													path="description" id="description" type="text"
													class="form-control" name="description"  />
									</div>
								</div>
								<div class="form-group">
									<label for="tax" class="col-sm-3 control-label">Category:</label>
									<div class="col-sm-12">

										<select name="selectedCategory">
											<c:forEach items="${categoriesList}" var="object">
												<option >${object.getCategoryName()} [${object.getTax()}%]</option>
											</c:forEach>

										</select>
										<a href="#category-popup" type="button"
										   class="btn open-popup-link btn-default btn-sm ">
											Add new category
										</a>

									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-12 text-right">
										<button type="submit" class="btn btn-default preview-add-button">
											<i class="fa fa-plus"></i> Add new expense
										</button>
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</div> <!-- / panel preview -->
			</div>
			<!-- Add expense popup close-->

			<!-- Add category popup-->
			<div id="category-popup" class="white-popup mfp-hide">
				<div >
					<h4>Add category:</h4>
					<div class="panel panel-default">
						<div class="panel-body form-horizontal payment-form">
							<form:form action="categories/"  modelAttribute="category" method="POST" class="category-form" >
								<div class="form-group">

									<label for="categoryName" class="col-sm-12 control-label" >Category name</label>
									<div class="col-sm-12">

										<form:input required="required" placeholder="Name of a new category"
													path="categoryName" id="categoryName" type="text"
													class="form-control" name="categoryName" />
									</div>
								</div>
							<div class="form-group">
								<label for="tax" class="col-sm-3 control-label">Tax</label>
								<div class="col-sm-12">
									<form:input required="required" placeholder="Tax"
												path="tax" id="tax" type="text"
												class="form-control" name="tax"  />
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-12 text-right">
									<button type="submit" class="btn btn-default preview-add-button">
										<i class="fa fa-plus"></i> Add new category
									</button>
								</div>
							</div>
							</form:form>
						</div>
					</div>
				</div> <!-- / panel preview -->
			</div>
			<!-- Add category popup close-->


			<!-- For popup -->
			<script src='http://code.jquery.com/jquery-2.2.4.min.js'></script>
			<script src='http://cdn.jsdelivr.net/jquery.magnific-popup/1.0.0/jquery.magnific-popup.min.js'></script>
			<script src="<c:url value='/js/popup-show.js'/>"></script>
			<!--  For popup close -->
</div>
	<!-- Copyright -->
		<div id="copyright">
			<div class="container">
				Design: <a href="#">roysez</a>
			</div>
		</div>

	</body>
</html>