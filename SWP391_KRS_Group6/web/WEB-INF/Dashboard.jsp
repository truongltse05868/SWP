<%-- 
    Document   : Dashboard
    Created on : Jun 4, 2024, 7:48:27 AM
    Author     : HuyPC
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:08:15 GMT -->
    <head>

        <!-- META ============================================= -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="keywords" content="" />
        <meta name="author" content="" />
        <meta name="robots" content="" />

        <!-- DESCRIPTION -->
        <meta name="description" content="EduChamp : Education HTML Template" />

        <!-- OG -->
        <meta property="og:title" content="EduChamp : Education HTML Template" />
        <meta property="og:description" content="EduChamp : Education HTML Template" />
        <meta property="og:image" content="" />
        <meta name="format-detection" content="telephone=no">

        <!-- FAVICONS ICON ============================================= -->
        <link rel="icon" href="../error-404.html" type="image/x-icon" />
        <link rel="shortcut icon" type="image/x-icon" href="admin/assets/images/favicon.png" />

        <!-- PAGE TITLE HERE ============================================= -->
        <title>EduChamp : Education HTML Template </title>

        <!-- MOBILE SPECIFIC ============================================= -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!--[if lt IE 9]>
        <script src="assets/js/html5shiv.min.js"></script>
        <script src="assets/js/respond.min.js"></script>
        <![endif]-->

        <!-- All PLUGINS CSS ============================================= -->
        <link rel="stylesheet" type="text/css" href="admin/assets/css/assets.css">
        <link rel="stylesheet" type="text/css" href="admin/assets/vendors/calendar/fullcalendar.css">

        <!-- TYPOGRAPHY ============================================= -->
        <link rel="stylesheet" type="text/css" href="admin/assets/css/typography.css">

        <!-- SHORTCODES ============================================= -->
        <link rel="stylesheet" type="text/css" href="admin/assets/css/shortcodes/shortcodes.css">

        <!-- STYLESHEETS ============================================= -->
        <link rel="stylesheet" type="text/css" href="admin/assets/css/style.css">
        <link rel="stylesheet" type="text/css" href="admin/assets/css/dashboard.css">
        <link class="skin" rel="stylesheet" type="text/css" href="admin/assets/css/color/color-1.css">

    </head>
    <body class="ttr-opened-sidebar ttr-pinned-sidebar">

        <!-- header start -->

        <jsp:include page="DashboardNav.jsp"/>

        <!-- Left sidebar menu end -->

        <!--Main container start -->
        <main class="ttr-wrapper">
            <div class="container-fluid">
                <div class="db-breadcrumb">
                    <h4 class="breadcrumb-title">Dashboard</h4>
                    <ul class="db-breadcrumb-list">
                        <li><a href="#"><i class="fa fa-home"></i>Home</a></li>
                        <li>Dashboard</li>
                    </ul>
                </div>	
                <!-- Card -->
                <div class="row">
                    <div class="col-md-6 col-lg-3 col-xl-3 col-sm-6 col-12">
                        <div class="widget-card widget-bg1">					 
                            <div class="wc-item">
                                <h4 class="wc-title">
                                    Total Subject
                                </h4>
                                <span class="wc-des">
                                    All Customs Value
                                </span>
                                <span class="wc-stats counter">
                                    ${subject.size()}
                                </span>	
                                <div class="progress wc-progress">
                                    <div class="progress-bar" role="progressbar" style="width: 78%;" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                                <span class="wc-progress-bx">
                                    <span class="wc-change">
                                        Change
                                    </span>
                                    <span class="wc-number ml-auto">
                                        78%
                                    </span>
                                </span>
                            </div>				      
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-3 col-xl-3 col-sm-6 col-12">
                        <div class="widget-card widget-bg2">					 
                            <div class="wc-item">
                                <h4 class="wc-title">
                                     Total Blogs
                                </h4>
                                <span class="wc-des">
                                    Customer Review
                                </span>
                                <span class="wc-stats counter">
                                    ${posts.size()}
                                </span>		
                                <div class="progress wc-progress">
                                    <div class="progress-bar" role="progressbar" style="width: 88%;" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                                <span class="wc-progress-bx">
                                    <span class="wc-change">
                                        Change
                                    </span>
                                    <span class="wc-number ml-auto">
                                        88%
                                    </span>
                                </span>
                            </div>				      
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-3 col-xl-3 col-sm-6 col-12">
                        <div class="widget-card widget-bg3">					 
                            <div class="wc-item">
                                <h4 class="wc-title">
                                   Total Orders 
                                </h4>
                                <span class="wc-des">
                                    Fresh Order Amount 
                                </span>
                                <span class="wc-stats counter">
                                    ${classes.size()}
                                </span>		
                                <div class="progress wc-progress">
                                    <div class="progress-bar" role="progressbar" style="width: 65%;" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                                <span class="wc-progress-bx">
                                    <span class="wc-change">
                                        Change
                                    </span>
                                    <span class="wc-number ml-auto">
                                        65%
                                    </span>
                                </span>
                            </div>				      
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-3 col-xl-3 col-sm-6 col-12">
                        <div class="widget-card widget-bg4">					 
                            <div class="wc-item">
                                <h4 class="wc-title">
                                    Total Users 
                                </h4>
                                <span class="wc-des">
                                    Joined New User
                                </span>
                                <span class="wc-stats counter">
                                    ${users.size()} 
                                </span>		
                                <div class="progress wc-progress">
                                    <div class="progress-bar" role="progressbar" style="width: 90%;" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                                <span class="wc-progress-bx">
                                    <span class="wc-change">
                                        Change
                                    </span>
                                    <span class="wc-number ml-auto">
                                        90%
                                    </span>
                                </span>
                            </div>				      
                        </div>
                    </div>
                </div>
                <!-- Card END -->
                <div class="row">
                    <!-- Your Profile Views Chart -->
                    <div class="col-lg-8 m-b30">
                        <div class="widget-box">
                            <div class="wc-title">
                                <h4>Your Profile Views</h4>
                            </div>
                            <div class="widget-inner">
                                <canvas id="chart" width="100" height="45"></canvas>
                            </div>
                        </div>
                    </div>
                    <!-- Your Profile Views Chart END-->
                    <div class="col-lg-4 m-b30">
                        <div class="widget-box">
                            <div class="wc-title">
                                <h4>Notifications</h4>
                            </div>
                            <div class="widget-inner">
                                <div class="noti-box-list">
                                    <ul>
                                        <c:if test="${not empty posts}">
                                            <c:forEach var="post" items="${posts}">
                                                <li>
                                                    <span class="notification-icon dashbg-gray">
                                                        <i class="fa fa-check"></i>
                                                    </span>
                                                    <span class="notification-text">
                                                        <span>New Post </span>${post.title}
                                                    </span>
                                                    <span class="notification-time">
                                                        <a href="#" class="fa fa-close"></a>
                                                        <span>By  <c:choose>
                                                            <c:when test="${not empty users}">
                                                                <c:forEach var="user" items="${users}">
                                                                    <c:if test="${user.user_id == post.user_id}">
                                                                        ${user.full_name}
                                                                    </c:if>
                                                                </c:forEach>
                                                            </c:when>
                                                            <c:otherwise>
                                                                Author information is unavailable.
                                                            </c:otherwise>
                                                        </c:choose></span>
                                                    </span>
                                                </li>
                                            </c:forEach>
                                        </c:if>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 m-b30">
                        <div class="widget-box">
                            <div class="wc-title">
                                <h4>New Users</h4>
                            </div>
                            <div class="widget-inner">
                                <div class="new-user-list">
                                    <ul>
                                        <li>
                                            <span class="new-users-pic">
                                                <img src="admin/assets/images/testimonials/pic1.jpg" alt=""/>
                                            </span>
                                            <span class="new-users-text">
                                                <a href="#" class="new-users-name">Anna Strong </a>
                                                <span class="new-users-info">Visual Designer,Google Inc </span>
                                            </span>
                                            <span class="new-users-btn">
                                                <a href="#" class="btn button-sm outline">Follow</a>
                                            </span>
                                        </li>
                                        <li>
                                            <span class="new-users-pic">
                                                <img src="admin/assets/images/testimonials/pic2.jpg" alt=""/>
                                            </span>
                                            <span class="new-users-text">
                                                <a href="#" class="new-users-name"> Milano Esco </a>
                                                <span class="new-users-info">Product Designer, Apple Inc </span>
                                            </span>
                                            <span class="new-users-btn">
                                                <a href="#" class="btn button-sm outline">Follow</a>
                                            </span>
                                        </li>
                                        <li>
                                            <span class="new-users-pic">
                                                <img src="admin/assets/images/testimonials/pic1.jpg" alt=""/>
                                            </span>
                                            <span class="new-users-text">
                                                <a href="#" class="new-users-name">Nick Bold  </a>
                                                <span class="new-users-info">Web Developer, Facebook Inc </span>
                                            </span>
                                            <span class="new-users-btn">
                                                <a href="#" class="btn button-sm outline">Follow</a>
                                            </span>
                                        </li>
                                        <li>
                                            <span class="new-users-pic">
                                                <img src="admin/assets/images/testimonials/pic2.jpg" alt=""/>
                                            </span>
                                            <span class="new-users-text">
                                                <a href="#" class="new-users-name">Wiltor Delton </a>
                                                <span class="new-users-info">Project Manager, Amazon Inc </span>
                                            </span>
                                            <span class="new-users-btn">
                                                <a href="#" class="btn button-sm outline">Follow</a>
                                            </span>
                                        </li>
                                        <li>
                                            <span class="new-users-pic">
                                                <img src="admin/assets/images/testimonials/pic3.jpg" alt=""/>
                                            </span>
                                            <span class="new-users-text">
                                                <a href="#" class="new-users-name">Nick Stone </a>
                                                <span class="new-users-info">Project Manager, Amazon Inc  </span>
                                            </span>
                                            <span class="new-users-btn">
                                                <a href="#" class="btn button-sm outline">Follow</a>
                                            </span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 m-b30">
                        <div class="widget-box">
                            <div class="wc-title">
                                <h4>Orders</h4>
                            </div>
                            <div class="widget-inner">
                                <div class="orders-list">
                                    <ul>
                                        <li>
                                            <span class="orders-title">
                                                <a href="#" class="orders-title-name">Anna Strong </a>
                                                <span class="orders-info">Order #02357 | Date 12/08/2019</span>
                                            </span>
                                            <span class="orders-btn">
                                                <a href="#" class="btn button-sm red">Unpaid</a>
                                            </span>
                                        </li>
                                        <li>
                                            <span class="orders-title">
                                                <a href="#" class="orders-title-name">Revenue</a>
                                                <span class="orders-info">Order #02357 | Date 12/08/2019</span>
                                            </span>
                                            <span class="orders-btn">
                                                <a href="#" class="btn button-sm red">Unpaid</a>
                                            </span>
                                        </li>
                                        <li>
                                            <span class="orders-title">
                                                <a href="#" class="orders-title-name">Anna Strong </a>
                                                <span class="orders-info">Order #02357 | Date 12/08/2019</span>
                                            </span>
                                            <span class="orders-btn">
                                                <a href="#" class="btn button-sm green">Paid</a>
                                            </span>
                                        </li>
                                        <li>
                                            <span class="orders-title">
                                                <a href="#" class="orders-title-name">Revenue</a>
                                                <span class="orders-info">Order #02357 | Date 12/08/2019</span>
                                            </span>
                                            <span class="orders-btn">
                                                <a href="#" class="btn button-sm green">Paid</a>
                                            </span>
                                        </li>
                                        <li>
                                            <span class="orders-title">
                                                <a href="#" class="orders-title-name">Anna Strong </a>
                                                <span class="orders-info">Order #02357 | Date 12/08/2019</span>
                                            </span>
                                            <span class="orders-btn">
                                                <a href="#" class="btn button-sm green">Paid</a>
                                            </span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12 m-b30">
                        <div class="widget-box">
                            <div class="wc-title">
                                <h4>Basic Calendar</h4>
                            </div>
                            <div class="widget-inner">
                                <div id="calendar"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <div class="ttr-overlay"></div>

        <!-- External JavaScripts -->
        <script src="admin/assets/js/jquery.min.js"></script>
        <script src="admin/assets/vendors/bootstrap/js/popper.min.js"></script>
        <script src="admin/assets/vendors/bootstrap/js/bootstrap.min.js"></script>
        <script src="admin/assets/vendors/bootstrap-select/bootstrap-select.min.js"></script>
        <script src="admin/assets/vendors/bootstrap-touchspin/jquery.bootstrap-touchspin.js"></script>
        <script src="admin/assets/vendors/magnific-popup/magnific-popup.js"></script>
        <script src="admin/assets/vendors/counter/waypoints-min.js"></script>
        <script src="admin/assets/vendors/counter/counterup.min.js"></script>
        <script src="admin/assets/vendors/imagesloaded/imagesloaded.js"></script>
        <script src="admin/assets/vendors/masonry/masonry.js"></script>
        <script src="admin/assets/vendors/masonry/filter.js"></script>
        <script src="admin/assets/vendors/owl-carousel/owl.carousel.js"></script>
        <script src='admin/assets/vendors/scroll/scrollbar.min.js'></script>
        <script src="admin/assets/js/functions.js"></script>
        <script src="admin/assets/vendors/chart/chart.min.js"></script>
        <script src="admin/assets/js/admin.js"></script>
        <script src='admin/assets/vendors/calendar/moment.min.js'></script>
        <script src='admin/assets/vendors/calendar/fullcalendar.js'></script>
        <script src='admin/assets/vendors/switcher/switcher.js'></script>
        <script>
            $(document).ready(function () {

                $('#calendar').fullCalendar({
                    header: {
                        left: 'prev,next today',
                        center: 'title',
                        right: 'month,agendaWeek,agendaDay,listWeek'
                    },
                    defaultDate: '2019-03-12',
                    navLinks: true, // can click day/week names to navigate views

                    weekNumbers: true,
                    weekNumbersWithinDays: true,
                    weekNumberCalculation: 'ISO',

                    editable: true,
                    eventLimit: true, // allow "more" link when too many events
                    events: [
                        {
                            title: 'All Day Event',
                            start: '2019-03-01'
                        },
                        {
                            title: 'Long Event',
                            start: '2019-03-07',
                            end: '2019-03-10'
                        },
                        {
                            id: 999,
                            title: 'Repeating Event',
                            start: '2019-03-09T16:00:00'
                        },
                        {
                            id: 999,
                            title: 'Repeating Event',
                            start: '2019-03-16T16:00:00'
                        },
                        {
                            title: 'Conference',
                            start: '2019-03-11',
                            end: '2019-03-13'
                        },
                        {
                            title: 'Meeting',
                            start: '2019-03-12T10:30:00',
                            end: '2019-03-12T12:30:00'
                        },
                        {
                            title: 'Lunch',
                            start: '2019-03-12T12:00:00'
                        },
                        {
                            title: 'Meeting',
                            start: '2019-03-12T14:30:00'
                        },
                        {
                            title: 'Happy Hour',
                            start: '2019-03-12T17:30:00'
                        },
                        {
                            title: 'Dinner',
                            start: '2019-03-12T20:00:00'
                        },
                        {
                            title: 'Birthday Party',
                            start: '2019-03-13T07:00:00'
                        },
                        {
                            title: 'Click for Google',
                            url: 'http://google.com/',
                            start: '2019-03-28'
                        }
                    ]
                });

            });

        </script>
    </body>

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:09:05 GMT -->
</html>