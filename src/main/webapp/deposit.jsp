<!DOCTYPE HTML>
<!--
	Monochromed by TEMPLATED
    templated.co @templatedco
    Released for free under the Creative Commons Attribution 3.0 license (templated.co/license)
-->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
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
		<div class="container">
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
							<li> <button type="button" class="btn btn-primary btn-xs btn3d">Add deposit</button></li>
							<li> <button type="button" class="btn btn-warning btn-xs btn3d">Change status of deposit</button></li>

						</ul>
					</section>
				</div>
				<!-- Sidebar -->

				<!-- Content -->
				<div id="content" class="8u skel-cell-important">
						<table class="table table-striped table-hover">
							<thead>
							<tr>

								<th>Id</th>
								<th>Sum</th>
								<th>Income</th>
								<th>Income in %</th>
								<th>Status</th>
								<th>Description</th>
							</tr>
							</thead>
							<tbody>
							<c:forEach items="${deposits}" var="item">
								<tr class="success">

									<td>${item.getId()}</td>
									<td>${item.getSum()}</td>
									<td>${item.getIncome()}</td>
									<td>${item.getPercentages()}</td>
									<td class="status">${item.getDepositStatus()}</td>
									<td>${item.getDescription()}</td>

								</tr>
							</c:forEach>
							</tbody>
						</table>
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
        $(function() {
            $('.col-check').hide();
            $('#btn-admin').on('click', function(){
                if($("#toolbar-admin").is(":visible"))
                {
                    $("#toolbar-admin").hide();
                    $(".col-check").hide();
                }
                else
                {
                    $("#toolbar-admin").show();
                    $(".col-check").show();
                }
            });

            $('#btn-online').on('click', function(){
                $('table tr').filter(':has(:checkbox:checked)').find('td').parent().removeClass().addClass('success');
                $('table tr').filter(':has(:checkbox:checked)').find('td.status').text('Online');
            });
            $('#btn-offline').on('click', function(){
                $('table tr').filter(':has(:checkbox:checked)').find('td').parent().removeClass().addClass('warning');
                $('table tr').filter(':has(:checkbox:checked)').find('td.status').text('Offline');
            });
            $('#btn-out-of-order').on('click', function(){
                $('table tr').filter(':has(:checkbox:checked)').find('td').parent().removeClass().addClass('danger');
                $('table tr').filter(':has(:checkbox:checked)').find('td.status').text('Out Of Order');
            });

        });
	</script>
	</body>
</html>