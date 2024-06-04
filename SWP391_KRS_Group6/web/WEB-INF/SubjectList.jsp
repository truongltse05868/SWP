<%-- 
    Document   : SubjectList
    Created on : Jun 4, 2024, 5:17:00 PM
    Author     : HuyPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <title>KRS: Subject List</title>

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
            <jsp:include page="Header.jsp"/>
            <!-- header END ==== -->
            <!-- Content -->
            <div class="page-content bg-white">
                <!-- inner page banner -->
                <div class="page-banner ovbl-dark" style="background-image:url(assets/images/banner/banner3.jpg);">
                    <div class="container">
                        <div class="page-banner-entry">
                            <h1 class="text-white">Our Subject</h1>
                        </div>
                    </div>
                </div>
                <!-- Breadcrumb row -->
                <div class="breadcrumb-row">
                    <div class="container">
                        <ul class="list-inline">
                            <li><a href="Home">Home</a></li>
                            <li>Our Subject</li>
                        </ul>
                    </div>
                </div>
                <!-- Breadcrumb row END -->
                <!-- inner page banner END -->
                <div class="content-block">
                    <!-- About Us -->
                    <div class="section-area section-sp1">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-3 col-md-4 col-sm-12 m-b30">
                                    <div class="widget courses-search-bx placeani">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label>Search Courses</label>
                                                <input name="dzName" type="text" required class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="widget widget_archive">
                                        <h5 class="widget-title style-1">All Courses</h5>
                                        <ul>
                                            <li class="active"><a href="#">General</a></li>
                                            <li><a href="#">IT & Software</a></li>
                                            <li><a href="#">Photography</a></li>
                                            <li><a href="#">Programming Language</a></li>
                                            <li><a href="#">Technology</a></li>
                                        </ul>
                                    </div>
                                    <div class="widget">
                                        <a href="#"><img src="assets/images/adv/adv.jpg" alt=""/></a>
                                    </div>
                                    <!--                                    <div class="widget recent-posts-entry widget-courses">
                                                                            <h5 class="widget-title style-1">Recent Courses</h5>
                                                                            <div class="widget-post-bx">
                                                                                <div class="widget-post clearfix">
                                                                                    <div class="ttr-post-media"> <img src="assets/images/blog/recent-blog/pic1.jpg" width="200" height="143" alt=""> </div>
                                                                                    <div class="ttr-post-info">
                                                                                        <div class="ttr-post-header">
                                                                                            <h6 class="post-title"><a href="#">Introduction EduChamp</a></h6>
                                                                                        </div>
                                                                                        <div class="ttr-post-meta">
                                                                                            <ul>
                                                                                                <li class="price">
                                                                                                    <del>$190</del>
                                                                                                    <h5>$120</h5>
                                                                                                </li>
                                                                                                <li class="review">03 Review</li>
                                                                                            </ul>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="widget-post clearfix">
                                                                                    <div class="ttr-post-media"> <img src="assets/images/blog/recent-blog/pic3.jpg" width="200" height="160" alt=""> </div>
                                                                                    <div class="ttr-post-info">
                                                                                        <div class="ttr-post-header">
                                                                                            <h6 class="post-title"><a href="#">English For Tommorow</a></h6>
                                                                                        </div>
                                                                                        <div class="ttr-post-meta">
                                                                                            <ul>
                                                                                                <li class="price">
                                                                                                    <h5 class="free">Free</h5>
                                                                                                </li>
                                                                                                <li class="review">07 Review</li>
                                                                                            </ul>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>-->
                                </div>
                                <div class="col-lg-9 col-md-8 col-sm-12">
                                    <c:if test="${not empty subjectList}">
                                        <div class="row">

                                            <c:forEach var="subjectList" items="${subjectList}">
                                                <div class="col-md-6 col-lg-4 col-sm-6 m-b30">
                                                    <div class="cours-bx">
                                                        <div class="action-box">
                                                            <img src="assets/images/courses/pic1.jpg" alt="">
                                                            <a href="#" class="btn">Read More</a>
                                                        </div>
                                                        <div class="info-bx text-center">
                                                            <h3><a href="#"> ${subjectList.subject_name}</a></h3>
                                                        </div>
                                                        <div class="info-bx text-center">
                                                            <div >
                                                                <h6><a> ${subjectList.subject_code}</a></h6>
                                                            </div>
                                                            <div >
                                                                <p><a>Description: ${subjectList.description}</a></p>
                                                            </div>
                                                            <div >
                                                                <c:choose>
                                                                    <c:when test="${subject.status}">
                                                                        <span class="badge badge-success">Active</span>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <span class="badge badge-secondary">Inactive</span>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </div>
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
                                            </c:forEach>



                                        </div>
                                    </c:if>
                                    <c:if test="${empty classes}">
                                        <p>No Classes found.</p>
                                    </c:if>
                                    <div class="col-lg-12 m-b20">
                                        <div class="pagination-bx rounded-sm gray clearfix">
                                            <ul class="pagination">
                                                <li class="previous"><a href="#"><i class="ti-arrow-left"></i> Prev</a></li>
                                                <li class="active"><a href="#">1</a></li>
                                                <li><a href="#">2</a></li>
                                                <li><a href="#">3</a></li>
                                                <li class="next"><a href="#">Next <i class="ti-arrow-right"></i></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- contact area END -->

            </div>
            <!-- Content END-->
            <!-- Footer ==== -->
            <jsp:include page="Footer.jsp"/>
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
    </body>

</html>

