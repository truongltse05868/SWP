<%-- 
    Document   : index
    Created on : May 25, 2024, 12:33:34 AM
    Author     : HuyPC
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>

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
    <link rel="icon" href="assets/images/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" type="image/x-icon" href="assets/images/favicon.png" />

    <!-- PAGE TITLE HERE ============================================= -->
    <title>EduChamp : Education HTML Template </title>

    <!-- MOBILE SPECIFIC ============================================= -->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!--[if lt IE 9]>
    <script src="assets/js/html5shiv.min.js"></script>
    <script src="assets/js/respond.min.js"></script>
    <![endif]-->

    <!-- All PLUGINS CSS ============================================= -->
    <link rel="stylesheet" type="text/css" href="assets/css/assets.css">

    <!-- TYPOGRAPHY ============================================= -->
    <link rel="stylesheet" type="text/css" href="assets/css/typography.css">

    <!-- SHORTCODES ============================================= -->
    <link rel="stylesheet" type="text/css" href="assets/css/shortcodes/shortcodes.css">

    <!-- STYLESHEETS ============================================= -->
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">
    <link class="skin" rel="stylesheet" type="text/css" href="assets/css/color/color-1.css">

    <!-- REVOLUTION SLIDER CSS ============================================= -->
    <link rel="stylesheet" type="text/css" href="assets/vendors/revolution/css/layers.css">
    <link rel="stylesheet" type="text/css" href="assets/vendors/revolution/css/settings.css">
    <link rel="stylesheet" type="text/css" href="assets/vendors/revolution/css/navigation.css">
    <!-- REVOLUTION SLIDER END -->
     <script>
        function sendPostRequest(action) {
            // Tạo form ẩn
            var form = document.createElement("form");
            form.setAttribute("method", "post");
            form.setAttribute("action", "userController");
            
            // Tạo input ẩn cho action
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", "action");
            hiddenField.setAttribute("value", action);
            
            // Thêm input ẩn vào form
            form.appendChild(hiddenField);
            
            // Thêm form vào body và submit
            document.body.appendChild(form);
            form.submit();
        }
        function sendPostRequestForPass(action) {
            // Tạo form ẩn
            var form = document.createElement("form");
            form.setAttribute("method", "post");
            form.setAttribute("action", "ForgotPasswordController");
            
            // Tạo input ẩn cho action
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", "action");
            hiddenField.setAttribute("value", action);
            
            // Thêm input ẩn vào form
            form.appendChild(hiddenField);
            
            // Thêm form vào body và submit
            document.body.appendChild(form);
            form.submit();
        }
    </script>

</head>
<body id="bg">
    <div class="page-wraper">
        <div id="loading-icon-bx"></div>
        <!-- Header Top ==== -->
        <header class="header rs-nav header-transparent">
            <div class="top-bar">
                <div class="container">
                    <div class="row d-flex justify-content-between">
                        <div class="topbar-left">
                            <ul>
                                <li><a href="faq-1.html"><i class="fa fa-question-circle"></i>Ask a Question</a></li>
                                <li><a href="javascript:;"><i class="fa fa-envelope-o"></i>Support@website.com</a></li>
                            </ul>
                        </div>

                        <div class="topbar-right">
                            <ul>
                                <li>
                                    <select class="header-lang-bx">
                                        <option data-icon="flag flag-uk">English UK</option>
                                        <option data-icon="flag flag-us">English US</option>
                                    </select>
                                </li>
                                <c:if test="${sessionScope['account'].getRole_id() == null}">
                                    <li><a id="loginLink" href="Login" >Login</a></li>
                                    <li><a id="registerLink" href="register.html">Register</a></li>
                                    <li><a id="forgotpass" href="forgotpass" onclick="sendPostRequestForPass('forgotPassPage'); return false;">ForgotPassword</a></li>
                                    </c:if>
                                    <c:if test="${sessionScope['account'].getRole_id() != null}">
                                    <li> <a href="contact.html">Hello, ${sessionScope.account.full_name} </a></li>
                                    <li><a id="logoutLink" href="Logout" >Logout</a></li>
                                    </c:if>
                                    




                            </ul>
                        </div>

                        <form id="hiddenForm" method="post" style="display:none;">
                            <input type="hidden" name="action" value="Login">
                        </form>
                        <form id="logout" method="post" style="display:none;">
                            <input type="hidden" name="action" value="Logout">
                        </form>
                    </div>
                    <!-- Navigation Menu ==== -->
                    <div class="menu-links navbar-collapse collapse justify-content-start" id="menuDropdown">
                        <div class="menu-logo">
                            <a href="Home"><img src="assets/images/logo.png" alt=""></a>
                        </div>
                        <ul class="nav navbar-nav">	
                            <li class="active"><a href="javascript:;">Home <i class="fa fa-chevron-down"></i></a>
                                <ul class="sub-menu">
                                    <li><a href="Home">Home 1</a></li>
                                    <li><a href="index-2.html">Home 2</a></li>
                                </ul>
                            </li>
                            <li><a href="javascript:;">Pages <i class="fa fa-chevron-down"></i></a>
                                <ul class="sub-menu">
                                    <li><a href="javascript:;">About<i class="fa fa-angle-right"></i></a>
                                        <ul class="sub-menu">
                                            <li><a href="about-1.html">About 1</a></li>
                                            <li><a href="about-2.html">About 2</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="javascript:;">Event<i class="fa fa-angle-right"></i></a>
                                        <ul class="sub-menu">
                                            <li><a href="event.html">Event</a></li>
                                            <li><a href="events-details.html">Events Details</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="javascript:;">FAQ's<i class="fa fa-angle-right"></i></a>
                                        <ul class="sub-menu">
                                            <li><a href="faq-1.html">FAQ's 1</a></li>
                                            <li><a href="faq-2.html">FAQ's 2</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="javascript:;">Contact Us<i class="fa fa-angle-right"></i></a>
                                        <ul class="sub-menu">
                                            <li><a href="contact-1.html">Contact Us 1</a></li>
                                            <li><a href="contact-2.html">Contact Us 2</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="portfolio.html">Portfolio</a></li>
                                    <li><a href="profile.html">Profile</a></li>
                                    <li><a href="membership.html">Membership</a></li>
                                    <li><a href="error-404.html">404 Page</a></li>
                                </ul>
                            </li>
                            <li class="add-mega-menu"><a href="javascript:;">Our Courses <i class="fa fa-chevron-down"></i></a>
                                <ul class="sub-menu add-menu">
                                    <li class="add-menu-left">
                                        <h5 class="menu-adv-title">Our Courses</h5>
                                        <ul>
                                            <li><a href="courses.html">Courses </a></li>
                                            <li><a href="courses-details.html">Courses Details</a></li>
                                            <li><a href="profile.html">Instructor Profile</a></li>
                                            <li><a href="event.html">Upcoming Event</a></li>
                                            <li><a href="membership.html">Membership</a></li>
                                        </ul>
                                    </li>
                                    <li class="add-menu-right">
                                        <img src="assets/images/adv/adv.jpg" alt=""/>
                                    </li>
                                </ul>
                            </li>
                            <li><a href="javascript:;">Blog <i class="fa fa-chevron-down"></i></a>
                                <ul class="sub-menu">
                                    <li><a href="blog-classic-grid.html">Blog Classic</a></li>
                                    <li><a href="blog-classic-sidebar.html">Blog Classic Sidebar</a></li>
                                    <li><a href="blog-list-sidebar.html">Blog List Sidebar</a></li>
                                    <li><a href="blog-standard-sidebar.html">Blog Standard Sidebar</a></li>
                                    <li><a href="blog-details.html">Blog Details</a></li>
                                </ul>
                            </li>
                            <li class="nav-dashboard"><a href="javascript:;">Dashboard <i class="fa fa-chevron-down"></i></a>
                                <ul class="sub-menu">
                                    <li><a href="admin/dashboard.html">Dashboard</a></li>
                                    <li><a href="admin/add-listing.html">Add Listing</a></li>
                                    <li><a href="admin/bookmark.html">Bookmark</a></li>
                                    <li><a href="SettingController">Setting List</a></li>
                                    <li><a href="userController">User List</a></li>
                                    <!--<li><a href="#" onclick="submitForm('userListForm')">User List</a></li>-->
                                    <!--                                    <form id="userListForm" action="userController" method="" style="display: none;">
                                                                            <input type="hidden" name="action" value="userList">
                                                                        </form>
                                                                        <script>
                                                                            function submitForm(formId) {
                                                                                document.getElementById(formId).submit();
                                                                            }
                                                                        </script>-->
                                    <li><a href="javascript:;">Calendar<i class="fa fa-angle-right"></i></a>
                                        <ul class="sub-menu">
                                            <li><a href="admin/basic-calendar.html">Basic Calendar</a></li>
                                            <li><a href="admin/list-view-calendar.html">List View Calendar</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="javascript:;">Mailbox<i class="fa fa-angle-right"></i></a>
                                        <ul class="sub-menu">
                                            <li><a href="admin/mailbox.html">Mailbox</a></li>
                                            <li><a href="admin/mailbox-compose.html">Compose</a></li>
                                            <li><a href="admin/mailbox-read.html">Mail Read</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                        <div class="nav-social-link">
                            <a href="javascript:;"><i class="fa fa-facebook"></i></a>
                            <a href="javascript:;"><i class="fa fa-google-plus"></i></a>
                            <a href="javascript:;"><i class="fa fa-linkedin"></i></a>
                        </div>
                    </div>
                    <!-- Navigation Menu END ==== -->

                </div>
            </div>
            <div class="sticky-header navbar-expand-lg">
                <div class="menu-bar clearfix">
                    <div class="container clearfix">
                        <!-- Header Logo ==== -->
                        <div class="menu-logo">
                            <a href="Home"><img src="assets/images/logo-white.png" alt=""></a>
                        </div>
                        <!-- Mobile Nav Button ==== -->
                        <button class="navbar-toggler collapsed menuicon justify-content-end" type="button" data-toggle="collapse" data-target="#menuDropdown" aria-controls="menuDropdown" aria-expanded="false" aria-label="Toggle navigation">
                            <span></span>
                            <span></span>
                            <span></span>
                        </button>
                        <!-- Author Nav ==== -->
                        <div class="secondary-menu">
                            <div class="secondary-inner">
                                <ul>
                                    <li><a href="javascript:;" class="btn-link"><i class="fa fa-facebook"></i></a></li>
                                    <li><a href="javascript:;" class="btn-link"><i class="fa fa-google-plus"></i></a></li>
                                    <li><a href="javascript:;" class="btn-link"><i class="fa fa-linkedin"></i></a></li>
                                    <!-- Search Button ==== -->
                                    <li class="search-btn"><button id="quik-search-btn" type="button" class="btn-link"><i class="fa fa-search"></i></button></li>
                                </ul>
                            </div>
                        </div>
                        <!-- Search Box ==== -->
                        <div class="nav-search-bar">
                            <form action="#">
                                <input name="search" value="" type="text" class="form-control" placeholder="Type to search">
                                <span><i class="ti-search"></i></span>
                            </form>
                            <span id="search-remove"><i class="ti-close"></i></span>
                        </div>
                        <!-- Navigation Menu ==== -->
                        <div class="menu-links navbar-collapse collapse justify-content-start" id="menuDropdown">
                            <div class="menu-logo">
                                <a href="Home"><img src="assets/images/logo.png" alt=""></a>
                            </div>
                            <ul class="nav navbar-nav">	
                                <li class="active"><a href="javascript:;">Home <i class="fa fa-chevron-down"></i></a>
                                    <ul class="sub-menu">
                                        <li><a href="Home">Home 1</a></li>
                                        <li><a href="index-2.html">Home 2</a></li>
                                    </ul>
                                </li>
                                <li><a href="javascript:;">Pages <i class="fa fa-chevron-down"></i></a>
                                    <ul class="sub-menu">
                                        <li><a href="javascript:;">About<i class="fa fa-angle-right"></i></a>
                                            <ul class="sub-menu">
                                                <li><a href="about-1.html">About 1</a></li>
                                                <li><a href="about-2.html">About 2</a></li>
                                            </ul>
                                        </li>
                                        <li><a href="javascript:;">Event<i class="fa fa-angle-right"></i></a>
                                            <ul class="sub-menu">
                                                <li><a href="event.html">Event</a></li>
                                                <li><a href="events-details.html">Events Details</a></li>
                                            </ul>
                                        </li>
                                        <li><a href="javascript:;">FAQ's<i class="fa fa-angle-right"></i></a>
                                            <ul class="sub-menu">
                                                <li><a href="faq-1.html">FAQ's 1</a></li>
                                                <li><a href="faq-2.html">FAQ's 2</a></li>
                                            </ul>
                                        </li>
                                        <li><a href="javascript:;">Contact Us<i class="fa fa-angle-right"></i></a>
                                            <ul class="sub-menu">
                                                <li><a href="contact-1.html">Contact Us 1</a></li>
                                                <li><a href="contact-2.html">Contact Us 2</a></li>
                                            </ul>
                                        </li>
                                        <li><a href="portfolio.html">Portfolio</a></li>
                                        <li><a href="profile.html">Profile</a></li>
                                        <li><a href="membership.html">Membership</a></li>
                                        <li><a href="error-404.html">404 Page</a></li>
                                    </ul>
                                </li>
                                <li class="add-mega-menu"><a href="javascript:;">Our Courses <i class="fa fa-chevron-down"></i></a>
                                    <ul class="sub-menu add-menu">
                                        <li class="add-menu-left">
                                            <h5 class="menu-adv-title">Our Courses</h5>
                                            <ul>
                                                <li><a href="courses.html">Courses </a></li>
                                                <li><a href="courses-details.html">Courses Details</a></li>
                                                <li><a href="profile.html">Instructor Profile</a></li>
                                                <li><a href="event.html">Upcoming Event</a></li>
                                                <li><a href="membership.html">Membership</a></li>
                                            </ul>
                                        </li>
                                        <li class="add-menu-right">
                                            <img src="assets/images/adv/adv.jpg" alt=""/>
                                        </li>
                                    </ul>
                                </li>
                                <li><a href="javascript:;">Blog <i class="fa fa-chevron-down"></i></a>
                                    <ul class="sub-menu">
                                        <li><a href="blog-classic-grid.html">Blog Classic</a></li>
                                        <li><a href="blog-classic-sidebar.html">Blog Classic Sidebar</a></li>
                                        <li><a href="blog-list-sidebar.html">Blog List Sidebar</a></li>
                                        <li><a href="blog-standard-sidebar.html">Blog Standard Sidebar</a></li>
                                        <li><a href="blog-details.html">Blog Details</a></li>
                                    </ul>
                                </li>
                                <li class="nav-dashboard"><a href="javascript:;">Dashboard <i class="fa fa-chevron-down"></i></a>
                                    <ul class="sub-menu">
                                        <li><a href="admin/dashboard.html">Dashboard</a></li>
                                        <li><a href="admin/add-listing.html">Add Listing</a></li>
                                        <li><a href="admin/bookmark.html">Bookmark</a></li>
                                        <li><a href="SettingController">Setting List</a></li>
                                        <!--<li><a href="userController?action=userList">User List</a></li>-->
<!--                                        <li>
                                            <form action="userController" method="post" style="display:inline;">
                                                <input type="hidden" name="action" value="userList">
                                                <button type="submit" style="background:none; border:none; color:blue; text-decoration:underline; cursor:pointer;">User List</button>
                                            </form>
                                        </li>-->
                                        <li><a href="userList" onclick="sendPostRequest('userList'); return false;">User List</a></li>
                                        <li><a href="javascript:;">Calendar<i class="fa fa-angle-right"></i></a>
                                            <ul class="sub-menu">
                                                <li><a href="admin/basic-calendar.html">Basic Calendar</a></li>
                                                <li><a href="admin/list-view-calendar.html">List View Calendar</a></li>
                                            </ul>
                                        </li>
                                        <li><a href="javascript:;">Mailbox<i class="fa fa-angle-right"></i></a>
                                            <ul class="sub-menu">
                                                <li><a href="admin/mailbox.html">Mailbox</a></li>
                                                <li><a href="admin/mailbox-compose.html">Compose</a></li>
                                                <li><a href="admin/mailbox-read.html">Mail Read</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                            <div class="nav-social-link">
                                <a href="javascript:;"><i class="fa fa-facebook"></i></a>
                                <a href="javascript:;"><i class="fa fa-google-plus"></i></a>
                                <a href="javascript:;"><i class="fa fa-linkedin"></i></a>
                            </div>
                        </div>
                        <!-- Navigation Menu END ==== -->
                    </div>
                </div>
            </div>
        </header>
        <!-- Header Top END ==== -->
        <!-- Content -->
        <div class="page-content bg-white">
            <!-- Main Slider -->
            <div class="rev-slider">
                <div id="rev_slider_486_1_wrapper" class="rev_slider_wrapper fullwidthbanner-container" data-alias="news-gallery36" data-source="gallery" style="margin:0px auto;background-color:#ffffff;padding:0px;margin-top:0px;margin-bottom:0px;">
                    <!-- START REVOLUTION SLIDER 5.3.0.2 fullwidth mode -->
                    <div id="rev_slider_486_1" class="rev_slider fullwidthabanner" style="display:none;" data-version="5.3.0.2">
                        <ul>	<!-- SLIDE  -->
                            <li data-index="rs-100" 
                                data-transition="parallaxvertical" 
                                data-slotamount="default" 
                                data-hideafterloop="0" 
                                data-hideslideonmobile="off" 
                                data-easein="default" 
                                data-easeout="default" 
                                data-masterspeed="default" 
                                data-thumb="error-404.html" 
                                data-rotate="0" 
                                data-fstransition="fade" 
                                data-fsmasterspeed="1500" 
                                data-fsslotamount="7" 
                                data-saveperformance="off" 
                                data-title="A STUDY ON HAPPINESS" 
                                data-param1="" data-param2="" 
                                data-param3="" data-param4="" 
                                data-param5="" data-param6="" 
                                data-param7="" data-param8="" 
                                data-param9="" data-param10="" 
                                data-description="Science says that Women are generally happier">
                                <!-- MAIN IMAGE -->
                                <img src="assets/images/slider/slide1.jpg" alt="" 
                                     data-bgposition="center center" 
                                     data-bgfit="cover" 
                                     data-bgrepeat="no-repeat" 
                                     data-bgparallax="10" 
                                     class="rev-slidebg" 
                                     data-no-retina />

                                <!-- LAYER NR. 1 -->
                                <div class="tp-caption tp-shape tp-shapewrapper " 
                                     id="slide-100-layer-1" 
                                     data-x="['center','center','center','center']" data-hoffset="['0','0','0','0']" 
                                     data-y="['middle','middle','middle','middle']" data-voffset="['0','0','0','0']" 
                                     data-width="full"
                                     data-height="full"
                                     data-whitespace="nowrap"
                                     data-type="shape" 
                                     data-basealign="slide" 
                                     data-responsive_offset="off" 
                                     data-responsive="off"
                                     data-frames='[{"from":"opacity:0;","speed":1,"to":"o:1;","delay":0,"ease":"Power4.easeOut"},{"delay":"wait","speed":1,"to":"opacity:0;","ease":"Power4.easeOut"}]'
                                     data-textAlign="['left','left','left','left']"
                                     data-paddingtop="[0,0,0,0]"
                                     data-paddingright="[0,0,0,0]"
                                     data-paddingbottom="[0,0,0,0]"
                                     data-paddingleft="[0,0,0,0]"
                                     style="z-index: 5;background-color:rgba(2, 0, 11, 0.80);border-color:rgba(0, 0, 0, 0);border-width:0px;"> </div>	
                                <!-- LAYER NR. 2 -->
                                <div class="tp-caption Newspaper-Title   tp-resizeme" 
                                     id="slide-100-layer-2" 
                                     data-x="['center','center','center','center']" 
                                     data-hoffset="['0','0','0','0']" 
                                     data-y="['top','top','top','top']" 
                                     data-voffset="['250','250','250','240']" 
                                     data-fontsize="['50','50','50','30']"
                                     data-lineheight="['55','55','55','35']"
                                     data-width="full"
                                     data-height="none"
                                     data-whitespace="normal"
                                     data-type="text" 
                                     data-responsive_offset="on" 
                                     data-frames='[{"from":"y:[-100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;","mask":"x:0px;y:0px;s:inherit;e:inherit;","speed":1500,"to":"o:1;","delay":1000,"ease":"Power3.easeInOut"},{"delay":"wait","speed":1000,"to":"auto:auto;","mask":"x:0;y:0;s:inherit;e:inherit;","ease":"Power3.easeInOut"}]'
                                     data-textAlign="['center','center','center','center']"
                                     data-paddingtop="[0,0,0,0]"
                                     data-paddingright="[0,0,0,0]"
                                     data-paddingbottom="[10,10,10,10]"
                                     data-paddingleft="[0,0,0,0]"
                                     style="z-index: 6; font-family:rubik; font-weight:700; text-align:center; white-space: normal;">
                                    Welcome To University
                                </div>

                                <!-- LAYER NR. 3 -->
                                <div class="tp-caption Newspaper-Subtitle   tp-resizeme" 
                                     id="slide-100-layer-3" 
                                     data-x="['center','center','center','center']" 
                                     data-hoffset="['0','0','0','0']" 
                                     data-y="['top','top','top','top']" 
                                     data-voffset="['210','210','210','210']" 
                                     data-width="none"
                                     data-height="none"
                                     data-whitespace="nowrap"
                                     data-type="text" 
                                     data-responsive_offset="on"
                                     data-frames='[{"from":"y:[-100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;","mask":"x:0px;y:0px;s:inherit;e:inherit;","speed":1500,"to":"o:1;","delay":1000,"ease":"Power3.easeInOut"},{"delay":"wait","speed":1000,"to":"auto:auto;","mask":"x:0;y:0;s:inherit;e:inherit;","ease":"Power3.easeInOut"}]'
                                     data-textAlign="['left','left','left','left']"
                                     data-paddingtop="[0,0,0,0]"
                                     data-paddingright="[0,0,0,0]"
                                     data-paddingbottom="[0,0,0,0]"
                                     data-paddingleft="[0,0,0,0]"
                                     style="z-index: 7; white-space: nowrap; color:#fff; font-family:rubik; font-size:18px; font-weight:400;">
                                    Batter Education For A Better 
                                </div>

                                <!-- LAYER NR. 3 -->
                                <div class="tp-caption Newspaper-Subtitle   tp-resizeme" 
                                     id="slide-100-layer-4" 
                                     data-x="['center','center','center','center']" 
                                     data-hoffset="['0','0','0','0']" 
                                     data-y="['top','top','top','top']" 
                                     data-voffset="['320','320','320','290']" 
                                     data-width="['800','800','700','420']"
                                     data-height="['100','100','100','120']"
                                     data-whitespace="unset"
                                     data-type="text" 
                                     data-responsive_offset="on"
                                     data-frames='[{"from":"y:[-100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;","mask":"x:0px;y:0px;s:inherit;e:inherit;","speed":1500,"to":"o:1;","delay":1000,"ease":"Power3.easeInOut"},{"delay":"wait","speed":1000,"to":"auto:auto;","mask":"x:0;y:0;s:inherit;e:inherit;","ease":"Power3.easeInOut"}]'
                                     data-textAlign="['center','center','center','center']"
                                     data-paddingtop="[0,0,0,0]"
                                     data-paddingright="[0,0,0,0]"
                                     data-paddingbottom="[0,0,0,0]"
                                     data-paddingleft="[0,0,0,0]"
                                     style="z-index: 7; text-transform:capitalize; white-space: unset; color:#fff; font-family:rubik; font-size:18px; line-height:28px; font-weight:400;">
                                    Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the
                                </div>
                                <!-- LAYER NR. 4 -->
                                <div class="tp-caption Newspaper-Button rev-btn " 
                                     id="slide-100-layer-5" 
                                     data-x="['center','center','center','center']" 
                                     data-hoffset="['90','80','75','90']" 
                                     data-y="['top','top','top','top']" 
                                     data-voffset="['400','400','400','420']" 
                                     data-width="none"
                                     data-height="none"
                                     data-whitespace="nowrap"
                                     data-type="button" 
                                     data-responsive_offset="on" 
                                     data-responsive="off"
                                     data-frames='[{"from":"y:[-100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;","mask":"x:0px;y:0px;","speed":1500,"to":"o:1;","delay":1000,"ease":"Power3.easeInOut"},{"delay":"wait","speed":1000,"to":"auto:auto;","mask":"x:0;y:0;","ease":"Power3.easeInOut"},{"frame":"hover","speed":"300","ease":"Power1.easeInOut","to":"o:1;rX:0;rY:0;rZ:0;z:0;","style":"c:rgba(0, 0, 0, 1.00);bg:rgba(255, 255, 255, 1.00);bc:rgba(255, 255, 255, 1.00);bw:1px 1px 1px 1px;"}]'
                                     data-textAlign="['center','center','center','center']"
                                     data-paddingtop="[12,12,12,12]"
                                     data-paddingright="[30,35,35,15]"
                                     data-paddingbottom="[12,12,12,12]"
                                     data-paddingleft="[30,35,35,15]"
                                     style="z-index: 8; white-space: nowrap; outline:none;box-shadow:none;box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;cursor:pointer; background-color:var(--primary) !important; border:0; border-radius:30px; margin-right:5px;">READ MORE </div>
                                <div class="tp-caption Newspaper-Button rev-btn" 
                                     id="slide-100-layer-6" 
                                     data-x="['center','center','center','center']" 
                                     data-hoffset="['-90','-80','-75','-90']" 
                                     data-y="['top','top','top','top']" 
                                     data-voffset="['400','400','400','420']" 
                                     data-width="none"
                                     data-height="none"
                                     data-whitespace="nowrap"
                                     data-type="button" 
                                     data-responsive_offset="on" 
                                     data-responsive="off"
                                     data-frames='[{"from":"y:[-100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;","mask":"x:0px;y:0px;","speed":1500,"to":"o:1;","delay":1000,"ease":"Power3.easeInOut"},{"delay":"wait","speed":1000,"to":"auto:auto;","mask":"x:0;y:0;","ease":"Power3.easeInOut"},{"frame":"hover","speed":"300","ease":"Power1.easeInOut","to":"o:1;rX:0;rY:0;rZ:0;z:0;","style":"c:rgba(0, 0, 0, 1.00);bg:rgba(255, 255, 255, 1.00);bc:rgba(255, 255, 255, 1.00);bw:1px 1px 1px 1px;"}]'
                                     data-textAlign="['center','center','center','center']"
                                     data-paddingtop="[12,12,12,12]"
                                     data-paddingright="[30,35,35,15]"
                                     data-paddingbottom="[12,12,12,12]"
                                     data-paddingleft="[30,35,35,15]"
                                     style="z-index: 8; white-space: nowrap; outline:none;box-shadow:none;box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;cursor:pointer; border-radius:30px;">CONTACT US</div>
                            </li>
                            <li data-index="rs-200" 
                                data-transition="parallaxvertical" 
                                data-slotamount="default" 
                                data-hideafterloop="0" 
                                data-hideslideonmobile="off" 
                                data-easein="default" 
                                data-easeout="default" 
                                data-masterspeed="default" 
                                data-thumb="assets/images/slider/slide1.jpg" 
                                data-rotate="0" 
                                data-fstransition="fade" 
                                data-fsmasterspeed="1500" 
                                data-fsslotamount="7" 
                                data-saveperformance="off" 
                                data-title="A STUDY ON HAPPINESS" 
                                data-param1="" data-param2="" 
                                data-param3="" data-param4="" 
                                data-param5="" data-param6="" 
                                data-param7="" data-param8="" 
                                data-param9="" data-param10="" 
                                data-description="Science says that Women are generally happier">
                                <!-- MAIN IMAGE -->
                                <img src="assets/images/slider/slide2.jpg" alt="" 
                                     data-bgposition="center center" 
                                     data-bgfit="cover" 
                                     data-bgrepeat="no-repeat" 
                                     data-bgparallax="10" 
                                     class="rev-slidebg" 
                                     data-no-retina />

                                <!-- LAYER NR. 1 -->
                                <div class="tp-caption tp-shape tp-shapewrapper " 
                                     id="slide-200-layer-1" 
                                     data-x="['center','center','center','center']" data-hoffset="['0','0','0','0']" 
                                     data-y="['middle','middle','middle','middle']" data-voffset="['0','0','0','0']" 
                                     data-width="full"
                                     data-height="full"
                                     data-whitespace="nowrap"
                                     data-type="shape" 
                                     data-basealign="slide" 
                                     data-responsive_offset="off" 
                                     data-responsive="off"
                                     data-frames='[{"from":"opacity:0;","speed":1,"to":"o:1;","delay":0,"ease":"Power4.easeOut"},{"delay":"wait","speed":1000,"to":"opacity:1;","ease":"Power4.easeOut"}]'
                                     data-textAlign="['left','left','left','left']"
                                     data-paddingtop="[0,0,0,0]"
                                     data-paddingright="[0,0,0,0]"
                                     data-paddingbottom="[0,0,0,0]"
                                     data-paddingleft="[0,0,0,0]"
                                     style="z-index: 5;background-color:rgba(2, 0, 11, 0.80);border-color:rgba(0, 0, 0, 0);border-width:0px;"> 
                                </div>

                                <!-- LAYER NR. 2 -->
                                <div class="tp-caption Newspaper-Title   tp-resizeme" 
                                     id="slide-200-layer-2" 
                                     data-x="['center','center','center','center']" 
                                     data-hoffset="['0','0','0','0']" 
                                     data-y="['top','top','top','top']" 
                                     data-voffset="['250','250','250','240']" 
                                     data-fontsize="['50','50','50','30']"
                                     data-lineheight="['55','55','55','35']"
                                     data-width="full"
                                     data-height="none"
                                     data-whitespace="normal"
                                     data-type="text" 
                                     data-responsive_offset="on" 
                                     data-frames='[{"from":"y:[-100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;","mask":"x:0px;y:0px;s:inherit;e:inherit;","speed":1500,"to":"o:1;","delay":1000,"ease":"Power3.easeInOut"},{"delay":"wait","speed":1000,"to":"auto:auto;","mask":"x:0;y:0;s:inherit;e:inherit;","ease":"Power3.easeInOut"}]'
                                     data-textAlign="['center','center','center','center']"
                                     data-paddingtop="[0,0,0,0]"
                                     data-paddingright="[0,0,0,0]"
                                     data-paddingbottom="[10,10,10,10]"
                                     data-paddingleft="[0,0,0,0]"
                                     style="z-index: 6; font-family:rubik; font-weight:700; text-align:center; white-space: normal;text-transform:uppercase;">
                                    Welcome To University
                                </div>

                                <!-- LAYER NR. 3 -->
                                <div class="tp-caption Newspaper-Subtitle   tp-resizeme" 
                                     id="slide-200-layer-3" 
                                     data-x="['center','center','center','center']" 
                                     data-hoffset="['0','0','0','0']" 
                                     data-y="['top','top','top','top']" 
                                     data-voffset="['210','210','210','210']" 
                                     data-width="none"
                                     data-height="none"
                                     data-whitespace="nowrap"
                                     data-type="text" 
                                     data-responsive_offset="on"
                                     data-frames='[{"from":"y:[-100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;","mask":"x:0px;y:0px;s:inherit;e:inherit;","speed":1500,"to":"o:1;","delay":1000,"ease":"Power3.easeInOut"},{"delay":"wait","speed":1000,"to":"auto:auto;","mask":"x:0;y:0;s:inherit;e:inherit;","ease":"Power3.easeInOut"}]'
                                     data-textAlign="['left','left','left','left']"
                                     data-paddingtop="[0,0,0,0]"
                                     data-paddingright="[0,0,0,0]"
                                     data-paddingbottom="[0,0,0,0]"
                                     data-paddingleft="[0,0,0,0]"
                                     style="z-index: 7; white-space: nowrap;text-transform:uppercase; color:#fff; font-family:rubik; font-size:18px; font-weight:400;">
                                    Batter Education For A Better 
                                </div>

                                <!-- LAYER NR. 3 -->
                                <div class="tp-caption Newspaper-Subtitle   tp-resizeme" 
                                     id="slide-200-layer-4" 
                                     data-x="['center','center','center','center']" 
                                     data-hoffset="['0','0','0','0']" 
                                     data-y="['top','top','top','top']" 
                                     data-voffset="['320','320','320','290']" 
                                     data-width="['800','800','700','420']"
                                     data-height="['100','100','100','120']"
                                     data-whitespace="unset"
                                     data-type="text" 
                                     data-responsive_offset="on"
                                     data-frames='[{"from":"y:[-100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;","mask":"x:0px;y:0px;s:inherit;e:inherit;","speed":1500,"to":"o:1;","delay":1000,"ease":"Power3.easeInOut"},{"delay":"wait","speed":1000,"to":"auto:auto;","mask":"x:0;y:0;s:inherit;e:inherit;","ease":"Power3.easeInOut"}]'
                                     data-textAlign="['center','center','center','center']"
                                     data-paddingtop="[0,0,0,0]"
                                     data-paddingright="[0,0,0,0]"
                                     data-paddingbottom="[0,0,0,0]"
                                     data-paddingleft="[0,0,0,0]"
                                     style="z-index: 7; text-transform:capitalize; white-space: unset; color:#fff; font-family:rubik; font-size:18px; line-height:28px; font-weight:400;">
                                    Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the
                                </div>
                                <!-- LAYER NR. 4 -->
                                <div class="tp-caption Newspaper-Button rev-btn " 
                                     id="slide-200-layer-5" 
                                     data-x="['center','center','center','center']" 
                                     data-hoffset="['90','80','75','90']" 
                                     data-y="['top','top','top','top']" 
                                     data-voffset="['400','400','400','420']" 
                                     data-width="none"
                                     data-height="none"
                                     data-whitespace="nowrap"
                                     data-type="button" 
                                     data-responsive_offset="on" 
                                     data-responsive="off"
                                     data-frames='[{"from":"y:[-100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;","mask":"x:0px;y:0px;","speed":1500,"to":"o:1;","delay":1000,"ease":"Power3.easeInOut"},{"delay":"wait","speed":1000,"to":"auto:auto;","mask":"x:0;y:0;","ease":"Power3.easeInOut"},{"frame":"hover","speed":"300","ease":"Power1.easeInOut","to":"o:1;rX:0;rY:0;rZ:0;z:0;","style":"c:rgba(0, 0, 0, 1.00);bg:rgba(255, 255, 255, 1.00);bc:rgba(255, 255, 255, 1.00);bw:1px 1px 1px 1px;"}]'
                                     data-textAlign="['center','center','center','center']"
                                     data-paddingtop="[12,12,12,12]"
                                     data-paddingright="[30,35,35,15]"
                                     data-paddingbottom="[12,12,12,12]"
                                     data-paddingleft="[30,35,35,15]"
                                     style="z-index: 8; white-space: nowrap; outline:none;box-shadow:none;box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;cursor:pointer; background-color:var(--primary) !important; border:0; border-radius:30px; margin-right:5px;">READ MORE </div>
                                <div class="tp-caption Newspaper-Button rev-btn" 
                                     id="slide-200-layer-6" 
                                     data-x="['center','center','center','center']" 
                                     data-hoffset="['-90','-80','-75','-90']" 
                                     data-y="['top','top','top','top']" 
                                     data-voffset="['400','400','400','420']" 
                                     data-width="none"
                                     data-height="none"
                                     data-whitespace="nowrap"
                                     data-type="button" 
                                     data-responsive_offset="on" 
                                     data-responsive="off"
                                     data-frames='[{"from":"y:[-100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;","mask":"x:0px;y:0px;","speed":1500,"to":"o:1;","delay":1000,"ease":"Power3.easeInOut"},{"delay":"wait","speed":1000,"to":"auto:auto;","mask":"x:0;y:0;","ease":"Power3.easeInOut"},{"frame":"hover","speed":"300","ease":"Power1.easeInOut","to":"o:1;rX:0;rY:0;rZ:0;z:0;","style":"c:rgba(0, 0, 0, 1.00);bg:rgba(255, 255, 255, 1.00);bc:rgba(255, 255, 255, 1.00);bw:1px 1px 1px 1px;"}]'
                                     data-textAlign="['center','center','center','center']"
                                     data-paddingtop="[12,12,12,12]"
                                     data-paddingright="[30,35,35,15]"
                                     data-paddingbottom="[12,12,12,12]"
                                     data-paddingleft="[30,35,35,15]"
                                     style="z-index: 8; white-space: nowrap; outline:none;box-shadow:none;box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;cursor:pointer; border-radius:30px;">CONTACT US</div>
                            </li>
                            <!-- SLIDE  -->
                        </ul>
                    </div><!-- END REVOLUTION SLIDER -->  
                </div>  
            </div>  
            <!-- Main Slider -->
            <div class="content-block">

                <!-- Our Services -->
                <div class="section-area content-inner service-info-bx">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-4 col-md-4 col-sm-6">
                                <div class="service-bx">
                                    <div class="action-box">
                                        <img src="assets/images/our-services/pic1.jpg" alt="">
                                    </div>
                                    <div class="info-bx text-center">
                                        <div class="feature-box-sm radius bg-white">
                                            <i class="fa fa-bank text-primary"></i>
                                        </div>
                                        <h4><a href="#">Best Industry Leaders</a></h4>
                                        <a href="#" class="btn radius-xl">View More</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-6">
                                <div class="service-bx">
                                    <div class="action-box">
                                        <img src="assets/images/our-services/pic2.jpg" alt="">
                                    </div>
                                    <div class="info-bx text-center">
                                        <div class="feature-box-sm radius bg-white">
                                            <i class="fa fa-book text-primary"></i>
                                        </div>
                                        <h4><a href="#">Learn Courses Online</a></h4>
                                        <a href="#" class="btn radius-xl">View More</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-12">
                                <div class="service-bx m-b0">
                                    <div class="action-box">
                                        <img src="assets/images/our-services/pic3.jpg" alt="">
                                    </div>
                                    <div class="info-bx text-center">
                                        <div class="feature-box-sm radius bg-white">
                                            <i class="fa fa-file-text-o text-primary"></i>
                                        </div>
                                        <h4><a href="#">Book Library & Store</a></h4>
                                        <a href="#" class="btn radius-xl">View More</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Our Services END -->

                <!-- Popular Courses -->
                <div class="section-area section-sp2 popular-courses-bx">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12 heading-bx left">
                                <h2 class="title-head">Popular <span>Courses</span></h2>
                                <p>It is a long established fact that a reader will be distracted by the readable content of a page</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="courses-carousel owl-carousel owl-btn-1 col-12 p-lr0">
                                <div class="item">
                                    <div class="cours-bx">
                                        <div class="action-box">
                                            <img src="assets/images/courses/pic1.jpg" alt="">
                                            <a href="#" class="btn">Read More</a>
                                        </div>
                                        <div class="info-bx text-center">
                                            <h5><a href="#">Introduction EduChamp – LMS plugin</a></h5>
                                            <span>Programming</span>
                                        </div>
                                        <div class="cours-more-info">
                                            <div class="review">
                                                <span>3 Review</span>
                                                <ul class="cours-star">
                                                    <li class="active"><i class="fa fa-star"></i></li>
                                                    <li class="active"><i class="fa fa-star"></i></li>
                                                    <li class="active"><i class="fa fa-star"></i></li>
                                                    <li><i class="fa fa-star"></i></li>
                                                    <li><i class="fa fa-star"></i></li>
                                                </ul>
                                            </div>
                                            <div class="price">
                                                <del>$190</del>
                                                <h5>$120</h5>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="item">
                                    <div class="cours-bx">
                                        <div class="action-box">
                                            <img src="assets/images/courses/pic2.jpg" alt="">
                                            <a href="#" class="btn">Read More</a>
                                        </div>
                                        <div class="info-bx text-center">
                                            <h5><a href="#">Introduction EduChamp – LMS plugin</a></h5>
                                            <span>Programming</span>
                                        </div>
                                        <div class="cours-more-info">
                                            <div class="review">
                                                <span>3 Review</span>
                                                <ul class="cours-star">
                                                    <li class="active"><i class="fa fa-star"></i></li>
                                                    <li class="active"><i class="fa fa-star"></i></li>
                                                    <li class="active"><i class="fa fa-star"></i></li>
                                                    <li><i class="fa fa-star"></i></li>
                                                    <li><i class="fa fa-star"></i></li>
                                                </ul>
                                            </div>
                                            <div class="price">
                                                <del>$190</del>
                                                <h5>$120</h5>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="item">
                                    <div class="cours-bx">
                                        <div class="action-box">
                                            <img src="assets/images/courses/pic3.jpg" alt="">
                                            <a href="#" class="btn">Read More</a>
                                        </div>
                                        <div class="info-bx text-center">
                                            <h5><a href="#">Introduction EduChamp – LMS plugin</a></h5>
                                            <span>Programming</span>
                                        </div>
                                        <div class="cours-more-info">
                                            <div class="review">
                                                <span>3 Review</span>
                                                <ul class="cours-star">
                                                    <li class="active"><i class="fa fa-star"></i></li>
                                                    <li class="active"><i class="fa fa-star"></i></li>
                                                    <li class="active"><i class="fa fa-star"></i></li>
                                                    <li><i class="fa fa-star"></i></li>
                                                    <li><i class="fa fa-star"></i></li>
                                                </ul>
                                            </div>
                                            <div class="price">
                                                <del>$190</del>
                                                <h5>$120</h5>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="item">
                                    <div class="cours-bx">
                                        <div class="action-box">
                                            <img src="assets/images/courses/pic4.jpg" alt="">
                                            <a href="#" class="btn">Read More</a>
                                        </div>
                                        <div class="info-bx text-center">
                                            <h5><a href="#">Introduction EduChamp – LMS plugin</a></h5>
                                            <span>Programming</span>
                                        </div>
                                        <div class="cours-more-info">
                                            <div class="review">
                                                <span>3 Review</span>
                                                <ul class="cours-star">
                                                    <li class="active"><i class="fa fa-star"></i></li>
                                                    <li class="active"><i class="fa fa-star"></i></li>
                                                    <li class="active"><i class="fa fa-star"></i></li>
                                                    <li><i class="fa fa-star"></i></li>
                                                    <li><i class="fa fa-star"></i></li>
                                                </ul>
                                            </div>
                                            <div class="price">
                                                <del>$190</del>
                                                <h5>$120</h5>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Popular Courses END -->

                <!-- Form -->
                <div class="section-area section-sp1 ovpr-dark bg-fix online-cours" style="background-image:url(assets/images/background/bg1.jpg);">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12 text-center text-white">
                                <h2>Online Courses To Learn</h2>
                                <h5>Own Your Feature Learning New Skills Online</h5>
                                <form class="cours-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="What do you want to learn today?	">
                                        <div class="input-group-append">
                                            <button class="btn" type="submit">Search</button> 
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="mw800 m-auto">
                            <div class="row">
                                <div class="col-md-4 col-sm-6">
                                    <div class="cours-search-bx m-b30">
                                        <div class="icon-box">
                                            <h3><i class="ti-user"></i><span class="counter">5</span>M</h3>
                                        </div>
                                        <span class="cours-search-text">Over 5 million student</span>
                                    </div>
                                </div>
                                <div class="col-md-4 col-sm-6">
                                    <div class="cours-search-bx m-b30">
                                        <div class="icon-box">
                                            <h3><i class="ti-book"></i><span class="counter">30</span>K</h3>
                                        </div>
                                        <span class="cours-search-text">30,000 Courses.</span>
                                    </div>
                                </div>
                                <div class="col-md-4 col-sm-12">
                                    <div class="cours-search-bx m-b30">
                                        <div class="icon-box">
                                            <h3><i class="ti-layout-list-post"></i><span class="counter">20</span>K</h3>
                                        </div>
                                        <span class="cours-search-text">Learn Anythink Online.</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Form END -->
                <div class="section-area section-sp2">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12 text-center heading-bx">
                                <h2 class="title-head m-b0">Upcoming <span>Events</span></h2>
                                <p class="m-b0">Upcoming Education Events To Feed Brain. </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="upcoming-event-carousel owl-carousel owl-btn-center-lr owl-btn-1 col-12 p-lr0  m-b30">
                                <div class="item">
                                    <div class="event-bx">
                                        <div class="action-box">
                                            <img src="assets/images/event/pic4.jpg" alt="">
                                        </div>
                                        <div class="info-bx d-flex">
                                            <div>
                                                <div class="event-time">
                                                    <div class="event-date">29</div>
                                                    <div class="event-month">October</div>
                                                </div>
                                            </div>
                                            <div class="event-info">
                                                <h4 class="event-title"><a href="#">Education Autumn Tour 2019</a></h4>
                                                <ul class="media-post">
                                                    <li><a href="#"><i class="fa fa-clock-o"></i> 7:00am 8:00am</a></li>
                                                    <li><a href="#"><i class="fa fa-map-marker"></i> Berlin, Germany</a></li>
                                                </ul>
                                                <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the..</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="item">
                                    <div class="event-bx">
                                        <div class="action-box">
                                            <img src="assets/images/event/pic3.jpg" alt="">
                                        </div>
                                        <div class="info-bx d-flex">
                                            <div>
                                                <div class="event-time">
                                                    <div class="event-date">29</div>
                                                    <div class="event-month">October</div>
                                                </div>
                                            </div>
                                            <div class="event-info">
                                                <h4 class="event-title"><a href="#">Education Autumn Tour 2019</a></h4>
                                                <ul class="media-post">
                                                    <li><a href="#"><i class="fa fa-clock-o"></i> 7:00am 8:00am</a></li>
                                                    <li><a href="#"><i class="fa fa-map-marker"></i> Berlin, Germany</a></li>
                                                </ul>
                                                <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the..</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="item">
                                    <div class="event-bx">
                                        <div class="action-box">
                                            <img src="assets/images/event/pic2.jpg" alt="">
                                        </div>
                                        <div class="info-bx d-flex">
                                            <div>
                                                <div class="event-time">
                                                    <div class="event-date">29</div>
                                                    <div class="event-month">October</div>
                                                </div>
                                            </div>
                                            <div class="event-info">
                                                <h4 class="event-title"><a href="#">Education Autumn Tour 2019</a></h4>
                                                <ul class="media-post">
                                                    <li><a href="#"><i class="fa fa-clock-o"></i> 7:00am 8:00am</a></li>
                                                    <li><a href="#"><i class="fa fa-map-marker"></i> Berlin, Germany</a></li>
                                                </ul>
                                                <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the..</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="text-center">
                            <a href="#" class="btn">View All Event</a>
                        </div>
                    </div>
                </div>

                <!-- Testimonials -->
                <div class="section-area section-sp2 bg-fix ovbl-dark" style="background-image:url(assets/images/background/bg1.jpg);">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12 text-white heading-bx left">
                                <h2 class="title-head text-uppercase">what people <span>say</span></h2>
                                <p>It is a long established fact that a reader will be distracted by the readable content of a page</p>
                            </div>
                        </div>
                        <div class="testimonial-carousel owl-carousel owl-btn-1 col-12 p-lr0">
                            <div class="item">
                                <div class="testimonial-bx">
                                    <div class="testimonial-thumb">
                                        <img src="assets/images/testimonials/pic1.jpg" alt="">
                                    </div>
                                    <div class="testimonial-info">
                                        <h5 class="name">Peter Packer</h5>
                                        <p>-Art Director</p>
                                    </div>
                                    <div class="testimonial-content">
                                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type...</p>
                                    </div>
                                </div>
                            </div>
                            <div class="item">
                                <div class="testimonial-bx">
                                    <div class="testimonial-thumb">
                                        <img src="assets/images/testimonials/pic2.jpg" alt="">
                                    </div>
                                    <div class="testimonial-info">
                                        <h5 class="name">Peter Packer</h5>
                                        <p>-Art Director</p>
                                    </div>
                                    <div class="testimonial-content">
                                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type...</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Testimonials END -->

                <!-- Recent News -->
                <div class="section-area section-sp2">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12 heading-bx left">
                                <h2 class="title-head">Recent <span>News</span></h2>
                                <p>It is a long established fact that a reader will be distracted by the readable content of a page</p>
                            </div>
                        </div>
                        <div class="recent-news-carousel owl-carousel owl-btn-1 col-12 p-lr0">
                            <div class="item">
                                <div class="recent-news">
                                    <div class="action-box">
                                        <img src="assets/images/blog/latest-blog/pic1.jpg" alt="">
                                    </div>
                                    <div class="info-bx">
                                        <ul class="media-post">
                                            <li><a href="#"><i class="fa fa-calendar"></i>Jan 02 2019</a></li>
                                            <li><a href="#"><i class="fa fa-user"></i>By William</a></li>
                                        </ul>
                                        <h5 class="post-title"><a href="blog-details.html">This Story Behind Education Will Haunt You Forever.</a></h5>
                                        <p>Knowing that, you’ve optimised your pages countless amount of times, written tons.</p>
                                        <div class="post-extra">
                                            <a href="#" class="btn-link">READ MORE</a>
                                            <a href="#" class="comments-bx"><i class="fa fa-comments-o"></i>20 Comment</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="item">
                                <div class="recent-news">
                                    <div class="action-box">
                                        <img src="assets/images/blog/latest-blog/pic2.jpg" alt="">
                                    </div>
                                    <div class="info-bx">
                                        <ul class="media-post">
                                            <li><a href="#"><i class="fa fa-calendar"></i>Feb 05 2019</a></li>
                                            <li><a href="#"><i class="fa fa-user"></i>By John</a></li>
                                        </ul>
                                        <h5 class="post-title"><a href="blog-details.html">What Will Education Be Like In The Next 50 Years?</a></h5>
                                        <p>As desperate as you are right now, you have done everything you can on your.</p>
                                        <div class="post-extra">
                                            <a href="#" class="btn-link">READ MORE</a>
                                            <a href="#" class="comments-bx"><i class="fa fa-comments-o"></i>14 Comment</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="item">
                                <div class="recent-news">
                                    <div class="action-box">
                                        <img src="assets/images/blog/latest-blog/pic3.jpg" alt="">
                                    </div>
                                    <div class="info-bx">
                                        <ul class="media-post">
                                            <li><a href="#"><i class="fa fa-calendar"></i>April 14 2019</a></li>
                                            <li><a href="#"><i class="fa fa-user"></i>By George</a></li>
                                        </ul>
                                        <h5 class="post-title"><a href="blog-details.html">Master The Skills Of Education And Be.</a></h5>
                                        <p>You will see in the guide all my years of valuable experience together with.</p>
                                        <div class="post-extra">
                                            <a href="#" class="btn-link">READ MORE</a>
                                            <a href="#" class="comments-bx"><i class="fa fa-comments-o"></i>23 Comment</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Recent News End -->

            </div>
            <!-- contact area END -->
        </div>
        <!-- Content END-->
        <!-- Footer ==== -->
        <footer>
            <div class="footer-top">
                <div class="pt-exebar">
                    <div class="container">
                        <div class="d-flex align-items-stretch">
                            <div class="pt-logo mr-auto">
                                <a href="Home"><img src="assets/images/logo-white.png" alt=""/></a>
                            </div>
                            <div class="pt-social-link">
                                <ul class="list-inline m-a0">
                                    <li><a href="#" class="btn-link"><i class="fa fa-facebook"></i></a></li>
                                    <li><a href="#" class="btn-link"><i class="fa fa-twitter"></i></a></li>
                                    <li><a href="#" class="btn-link"><i class="fa fa-linkedin"></i></a></li>
                                    <li><a href="#" class="btn-link"><i class="fa fa-google-plus"></i></a></li>
                                </ul>
                            </div>
                            <div class="pt-btn-join">
                                <a href="#" class="btn ">Join Now</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="container">
                    <div class="row">
                        <div class="col-lg-4 col-md-12 col-sm-12 footer-col-4">
                            <div class="widget">
                                <h5 class="footer-title">Sign Up For A Newsletter</h5>
                                <p class="text-capitalize m-b20">Weekly Breaking news analysis and cutting edge advices on job searching.</p>
                                <div class="subscribe-form m-b20">
                                    <form class="subscription-form" action="http://educhamp.themetrades.com/demo/assets/script/mailchamp.php" method="post">
                                        <div class="ajax-message"></div>
                                        <div class="input-group">
                                            <input name="email" required="required"  class="form-control" placeholder="Your Email Address" type="email">
                                            <span class="input-group-btn">
                                                <button name="submit" value="Submit" type="submit" class="btn"><i class="fa fa-arrow-right"></i></button>
                                            </span> 
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-lg-5 col-md-7 col-sm-12">
                            <div class="row">
                                <div class="col-4 col-lg-4 col-md-4 col-sm-4">
                                    <div class="widget footer_widget">
                                        <h5 class="footer-title">Company</h5>
                                        <ul>
                                            <li><a href="Home">Home</a></li>
                                            <li><a href="about-1.html">About</a></li>
                                            <li><a href="faq-1.html">FAQs</a></li>
                                            <li><a href="contact-1.html">Contact</a></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="col-4 col-lg-4 col-md-4 col-sm-4">
                                    <div class="widget footer_widget">
                                        <h5 class="footer-title">Get In Touch</h5>
                                        <ul>
                                            <li><a href="http://educhamp.themetrades.com/admin/index.html">Dashboard</a></li>
                                            <li><a href="blog-classic-grid.html">Blog</a></li>
                                            <li><a href="portfolio.html">Portfolio</a></li>
                                            <li><a href="event.html">Event</a></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="col-4 col-lg-4 col-md-4 col-sm-4">
                                    <div class="widget footer_widget">
                                        <h5 class="footer-title">Courses</h5>
                                        <ul>
                                            <li><a href="courses.html">Courses</a></li>
                                            <li><a href="courses-details.html">Details</a></li>
                                            <li><a href="membership.html">Membership</a></li>
                                            <li><a href="profile.html">Profile</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-lg-3 col-md-5 col-sm-12 footer-col-4">
                            <div class="widget widget_gallery gallery-grid-4">
                                <h5 class="footer-title">Our Gallery</h5>
                                <ul class="magnific-image">
                                    <li><a href="assets/images/gallery/pic1.jpg" class="magnific-anchor"><img src="assets/images/gallery/pic1.jpg" alt=""></a></li>
                                    <li><a href="assets/images/gallery/pic2.jpg" class="magnific-anchor"><img src="assets/images/gallery/pic2.jpg" alt=""></a></li>
                                    <li><a href="assets/images/gallery/pic3.jpg" class="magnific-anchor"><img src="assets/images/gallery/pic3.jpg" alt=""></a></li>
                                    <li><a href="assets/images/gallery/pic4.jpg" class="magnific-anchor"><img src="assets/images/gallery/pic4.jpg" alt=""></a></li>
                                    <li><a href="assets/images/gallery/pic5.jpg" class="magnific-anchor"><img src="assets/images/gallery/pic5.jpg" alt=""></a></li>
                                    <li><a href="assets/images/gallery/pic6.jpg" class="magnific-anchor"><img src="assets/images/gallery/pic6.jpg" alt=""></a></li>
                                    <li><a href="assets/images/gallery/pic7.jpg" class="magnific-anchor"><img src="assets/images/gallery/pic7.jpg" alt=""></a></li>
                                    <li><a href="assets/images/gallery/pic8.jpg" class="magnific-anchor"><img src="assets/images/gallery/pic8.jpg" alt=""></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="footer-bottom">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 text-center"> <a target="_blank" href="https://www.templateshub.net">Templates Hub</a></div>
                    </div>
                </div>
            </div>
        </footer>
        <!-- Footer END ==== -->
        <button class="back-to-top fa fa-chevron-up" ></button>
    </div>

    <!-- External JavaScripts -->
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/vendors/bootstrap/js/popper.min.js"></script>
    <script src="assets/vendors/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/vendors/bootstrap-select/bootstrap-select.min.js"></script>
    <script src="assets/vendors/bootstrap-touchspin/jquery.bootstrap-touchspin.js"></script>
    <script src="assets/vendors/magnific-popup/magnific-popup.js"></script>
    <script src="assets/vendors/counter/waypoints-min.js"></script>
    <script src="assets/vendors/counter/counterup.min.js"></script>
    <script src="assets/vendors/imagesloaded/imagesloaded.js"></script>
    <script src="assets/vendors/masonry/masonry.js"></script>
    <script src="assets/vendors/masonry/filter.js"></script>
    <script src="assets/vendors/owl-carousel/owl.carousel.js"></script>
    <script src="assets/js/functions.js"></script>
    <script src="assets/js/contact.js"></script>
    <script src='assets/vendors/switcher/switcher.js'></script>
    <!-- Revolution JavaScripts Files -->
    <script src="assets/vendors/revolution/js/jquery.themepunch.tools.min.js"></script>
    <script src="assets/vendors/revolution/js/jquery.themepunch.revolution.min.js"></script>
    <!-- Slider revolution 5.0 Extensions  (Load Extensions only on Local File Systems !  The following part can be removed on Server for On Demand Loading) -->
    <script src="assets/vendors/revolution/js/extensions/revolution.extension.actions.min.js"></script>
    <script src="assets/vendors/revolution/js/extensions/revolution.extension.carousel.min.js"></script>
    <script src="assets/vendors/revolution/js/extensions/revolution.extension.kenburn.min.js"></script>
    <script src="assets/vendors/revolution/js/extensions/revolution.extension.layeranimation.min.js"></script>
    <script src="assets/vendors/revolution/js/extensions/revolution.extension.migration.min.js"></script>
    <script src="assets/vendors/revolution/js/extensions/revolution.extension.navigation.min.js"></script>
    <script src="assets/vendors/revolution/js/extensions/revolution.extension.parallax.min.js"></script>
    <script src="assets/vendors/revolution/js/extensions/revolution.extension.slideanims.min.js"></script>
    <script src="assets/vendors/revolution/js/extensions/revolution.extension.video.min.js"></script>
    <script>
        jQuery(document).ready(function () {
            var ttrevapi;
            var tpj = jQuery;
            if (tpj("#rev_slider_486_1").revolution == undefined) {
                revslider_showDoubleJqueryError("#rev_slider_486_1");
            } else {
                ttrevapi = tpj("#rev_slider_486_1").show().revolution({
                    sliderType: "standard",
                    jsFileLocation: "assets/vendors/revolution/js/",
                    sliderLayout: "fullwidth",
                    dottedOverlay: "none",
                    delay: 9000,
                    navigation: {
                        keyboardNavigation: "on",
                        keyboard_direction: "horizontal",
                        mouseScrollNavigation: "off",
                        mouseScrollReverse: "default",
                        onHoverStop: "on",
                        touch: {
                            touchenabled: "on",
                            swipe_threshold: 75,
                            swipe_min_touches: 1,
                            swipe_direction: "horizontal",
                            drag_block_vertical: false
                        }
                        ,
                        arrows: {
                            style: "uranus",
                            enable: true,
                            hide_onmobile: false,
                            hide_onleave: false,
                            tmp: '',
                            left: {
                                h_align: "left",
                                v_align: "center",
                                h_offset: 10,
                                v_offset: 0
                            },
                            right: {
                                h_align: "right",
                                v_align: "center",
                                h_offset: 10,
                                v_offset: 0
                            }
                        },

                    },
                    viewPort: {
                        enable: true,
                        outof: "pause",
                        visible_area: "80%",
                        presize: false
                    },
                    responsiveLevels: [1240, 1024, 778, 480],
                    visibilityLevels: [1240, 1024, 778, 480],
                    gridwidth: [1240, 1024, 778, 480],
                    gridheight: [768, 600, 600, 600],
                    lazyType: "none",
                    parallax: {
                        type: "scroll",
                        origo: "enterpoint",
                        speed: 400,
                        levels: [5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 46, 47, 48, 49, 50, 55],
                        type: "scroll",
                    },
                    shadow: 0,
                    spinner: "off",
                    stopLoop: "off",
                    stopAfterLoops: -1,
                    stopAtSlide: -1,
                    shuffle: "off",
                    autoHeight: "off",
                    hideThumbsOnMobile: "off",
                    hideSliderAtLimit: 0,
                    hideCaptionAtLimit: 0,
                    hideAllCaptionAtLilmit: 0,
                    debugMode: false,
                    fallbacks: {
                        simplifyAll: "off",
                        nextSlideOnWindowFocus: "off",
                        disableFocusListener: false,
                    }
                });
            }
        });
    </script>
</body>
</html>