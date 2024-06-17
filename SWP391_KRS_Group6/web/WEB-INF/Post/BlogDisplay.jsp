<%-- 
    Document   : BlogDisplay
    Created on : Jun 12, 2024, 3:33:16 PM
    Author     : HuyPC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

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

    </head>
    <body id="bg">
        <div class="page-wraper">
            <div id="loading-icon-bx"></div>

            <!-- Header Top ==== -->
            <jsp:include page="/WEB-INF/Header.jsp"/>

            <!-- header END ==== -->
            <!-- Content -->
            <div class="page-content bg-white">
                <!-- inner page banner -->
                <div class="page-banner ovbl-dark" style="background-image:url(assets/images/banner/banner1.jpg);">
                    <div class="container">
                        <div class="page-banner-entry">
                            <h1 class="text-white">Blog</h1>
                        </div>
                    </div>
                </div>
                <!-- Breadcrumb row -->
                <div class="breadcrumb-row">
                    <div class="container">
                        <ul class="list-inline">
                            <li><a href="Home">Home</a></li>
                            <li>Blog</li>
                        </ul>
                    </div>
                </div>
                <!-- Breadcrumb row END -->
                <div class="content-block">
                    <div class="section-area section-sp1">
                        <div class="container">
                            <div class="row">
                                <!-- Left part start -->
                                <div class="col-md-7 col-lg-8 col-xl-8">

                                    <c:if test="${not empty posts}">
                                        <c:forEach var="posts" items="${posts}">
                                            <c:if test="${posts.status == true}">
                                                <div class="recent-news blog-lg m-b40">
                                                    <div class="action-box blog-lg">
                                                        <img src=${posts.thumbnailUrl} alt="">
                                                    </div>
                                                    <div class="info-bx">
                                                        <ul class="media-post">
                                                            <li style="font-size: 1em;"><i class="fa fa-user"> By
                                                                    <c:choose>
                                                                        <c:when test="${not empty user}">
                                                                            <c:forEach var="user" items="${user}">
                                                                                <c:if test="${user.user_id == posts.user_id}">
                                                                                    ${user.full_name}
                                                                                </c:if>
                                                                            </c:forEach>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            Post information is unavailable.
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </i></li>
                                                        </ul>
                                                        <form method="post" action="PostController">
                                                            <input type="hidden" name="service" value="BlogDetail">
                                                            <input type="hidden" name="pid" value="${posts.postId}">
                                                            <h5 class="post-title"><button type="submit" class="btn-link">${posts.title}</button></h5>
                                                        </form>
                                                        <p>${posts.summary}</p>
                                                        <div class="post-extra">
                                                            <form method="post" action="PostController">
                                                                <input type="hidden" name="service" value="BlogDetail">
                                                                <input type="hidden" name="pid" value="${posts.postId}">
                                                                <button type="submit" class="btn-link">READ MORE</button>
                                                            </form>
                                                        </div>
                                                    </div>
                                                    <hr style="border: 1px solid #333; margin: 20px 0;">
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                        <div>
                                            <c:if test="${totalPages > 1}">
                                                <a href="PostController?service=BlogList&page=${currentPage - 1}&sortColumn=${sortColumn}&sortOrder=${sortOrder}&searchSetting=${param.searchSetting}" class="btn btn-secondary">Previous</a>
                                            </c:if>
                                            <a href="PostController?service=BlogList&page=${currentPage + 1}&sortColumn=${sortColumn}&sortOrder=${sortOrder}&searchSetting=${param.searchSetting}" class="btn btn-secondary">Next</a>
                                        </div>
                                    </c:if>
                                    <c:if test="${empty posts}">
                                        <p>No post found.</p>
                                    </c:if>
                                    <!-- Pagination start -->
                                    <!-- Pagination END -->
                                </div>
                                <!-- Left part END -->
                                <!-- Side bar start -->
                                <div class="col-md-5 col-lg-4 col-xl-4 sticky-top">
                                    <aside class="side-bar sticky-top">
                                        <c:if test="${sessionScope['account'] != null}">
                                            <form method="post" action="PostController" class="my-3">
                                                <input type="hidden" name="service" value="insertPost">
                                                <button type="submit" class=" btn btn-block btn-primary">New Blog</button>
                                            </form>
                                        </c:if>
                                        <div class="widget">
                                            <h6 class="widget-title">Search</h6>
                                            <div class="search-bx style-1">
                                                <form role="search" method="get" action="PostController">
                                                    <input type="hidden" name="service" value="BlogList">
                                                    <div class="input-group">
                                                        <label for="keyword" class="sr-only">Keyword</label>
                                                        <input class="form-control" id="keyword" name="keyword" placeholder="Search..." value="${param.keyword}" type="text">
                                                        <span class="input-group-btn">
                                                            <button type="submit" class="fa fa-search text-primary"></button>
                                                        </span> 
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </aside>
                                </div>
                                <!-- Side bar END -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Content END-->
            <!-- Footer ==== -->
            <jsp:include page="/WEB-INF/Footer.jsp"/>
            <!-- Footer END ==== -->
            <!-- scroll top button -->
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
    </body>

</html>
