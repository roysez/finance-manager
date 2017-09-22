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
	<!-- Header -->

	<!-- Main -->
	<div id="main">
		<div class="container">
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
											<button type="button" class="btn btn-primary btn-lg btn3d">Add credit to history</button>
										</section>
								</div>

							<!-- Table -->
							<table id="mytable" class="table table-bordered">
								<thead >
								<tr>

									<th>Description</th>
									<th>Amount to pay</th>
									<th>Date</th>
									<th>Due date</th>
									<th style="width:25%">Progress</th>
									<th>Status</th>
								</tr>
								</thead>
								<tbody>
								<tr>

									<td>Mohsin</td>
									<td>+92-333-5586757</td>
									<td>isometric.mohsin@gmail.com</td>
									<td>isometric.mohsin@gmail.com</td>
									<td>
										<div class="progress">
											<div style="width: 60%;" aria-valuemax="100" aria-valuemin="0" aria-valuenow="60" role="progressbar" class="red progress-bar">
												<span>60%</span>
											</div>
										</div>
									</td>
									<td><span class="label label-info">Pending</span></td>
								</tr>
								<tr>

									<td>Haseeb</td>
									<td>+92-333-5586757</td>
									<td>haseeb@gmail.com</td>
									<td>isometric.mohsin@gmail.com</td>
									<td>
										<div class="progress">
											<div style="width: 80%;" aria-valuemax="100" aria-valuemin="0" aria-valuenow="80" role="progressbar" class="green progress-bar">
												<span>80%</span>
											</div>
										</div>
									</td>
									<td><span class="label label-primary">Due</span></td>
								</tr>
								<tr>

									<td>Hussain</td>
									<td>+92-333-5586757</td>
									<td>hussain@gmail.com</td>
									<td>isometric.mohsin@gmail.com</td>
									<td>
										<div class="progress">
											<div style="width: 40%;" aria-valuemax="100" aria-valuemin="0" aria-valuenow="40" role="progressbar" class="purple progress-bar">
												<span>40%</span>
											</div>
										</div>
									</td>
									<td><span class="label label-warning">Suspended</span></td>
								</tr>
								<tr>

									<td>Noman</td>
									<td>+92-333-5586757</td>
									<td>noman@gmail.com</td>
									<td>isometric.mohsin@gmail.com</td>
									<td>
										<div class="progress">
											<div style="width: 90%;" aria-valuemax="100" aria-valuemin="0" aria-valuenow="90" role="progressbar" class="purple progress-bar">
												<span>90%</span>
											</div>
										</div>
									</td>
									<td><span class="label label-success">Due</span></td>
								</tr>
								<tr>

									<td>Ubaid</td>
									<td>+92-333-5586757</td>
									<td>ubaid@gmail.com</td>
									<td>isometric.mohsin@gmail.com</td>
									<td>
										<div class="progress">
											<div style="width: 60%;" aria-valuemax="100" aria-valuemin="0" aria-valuenow="60" role="progressbar" class="red progress-bar">
												<span>60%</span>
											</div>
										</div>
									</td>
									<td><span class="label label-warning">Suspended</span></td>
								</tr>
								<tr>

									<td>Adnan</td>
									<td>+92-333-5586757</td>
									<td>adnan@gmail.com</td>
									<td>isometric.mohsin@gmail.com</td>
									<td>
										<div class="progress">
											<div style="width: 45%;" aria-valuemax="100" aria-valuemin="0" aria-valuenow="45" role="progressbar" class="red progress-bar">
												<span>45%</span>
											</div>
										</div>
									</td>
									<td><span class="label label-warning">Suspended</span></td>
								</tr>
								<tr>

									<td>Saboor</td>
									<td>+92-333-5586757</td>
									<td>saboor@gmail.com</td>
									<td>isometric.mohsin@gmail.com</td>
									<td>
										<div class="progress">
											<div style="width: 89%;" aria-valuemax="100" aria-valuemin="0" aria-valuenow="89" role="progressbar" class="green progress-bar">
												<span>89%</span>
											</div>
										</div>
									</td>
									<td><span class="label label-warning">Suspended</span></td>
								</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				</div>
				<!-- /Content -->

			</div>

		</div>

	<!-- Main -->
	<!-- Copyright -->
		<div id="copyright">
			<div class="container">
				Design: <a href="http://templated.co">TEMPLATED</a> Images: <a href="http://unsplash.com">Unsplash</a> (<a href="http://unsplash.com/cc0">CC0</a>)
			</div>
		</div>
	<script type="text/javascript">


	</script>
	</body>
</html>