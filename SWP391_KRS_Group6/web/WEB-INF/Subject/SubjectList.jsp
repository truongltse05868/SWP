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
            <jsp:include page="../Header.jsp"/>
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
                                            <form method="get" action="SubjectController">
                                                <div class="input-group">
                                                    <label for="keyword">Search Courses</label>
                                                    <input type="hidden" name="service" value="ListAllSubject">
                                                    <input type="text" id="keyword" name="keyword" class="form-control" style="width: 300px;" value="${param.keyword}">
                                                    <button type="submit" class="btn">Search</button>
                                                </div>
                                            </form>
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

                                </div>
                                <div class="col-lg-9 col-md-8 col-sm-12">
                                    <c:if test="${sessionScope['account'].getRole_id() == 1}">
                                        <form method="post" action="SubjectController" class="my-3">
                                            <input type="hidden" name="service" value="insertSubject">
                                            <button type="submit" class=" btn btn-block btn-primary">Add</button>
                                        </form>
                                    </c:if>
                                    <c:if test="${not empty subjectList}">
                                        <div class="row">
                                            <c:forEach var="subjectList" items="${subjectList}">
                                                <div class="col-md-6 col-lg-4 col-sm-6 mb-4">
                                                    <div class="card h-100"> <!-- Ensure each card has the h-100 class for equal height -->
                                                        <div class="card-header p-0">
                                                            <img src="assets/images/courses/pic${subjectList.subject_id}.jpg" class="card-img-top" alt="">
                                                            <c:if test="${sessionScope['account'].getRole_id() == 1}">
                                                                <form method="post" action="SubjectController">
                                                                    <input type="hidden" name="service" value="updateSubject">
                                                                    <input type="hidden" name="pid" value="${subjectList.subject_id}">
                                                                    <button type="submit" class="btn btn-block btn-primary">Update</button>
                                                                </form>
                                                            </c:if>
                                                        </div>
                                                        <div class="card-body d-flex flex-column"> <!-- Use flex-column for equal heights -->
                                                            <div class="info-bx mt-3 text-center">
                                                                <h5>${subjectList.subject_name}</h5>
                                                            </div>
                                                            <div class="info-bx mt-2">
                                                                <div class="text-center">
                                                                    <h6>${subjectList.subject_code}</h6>
                                                                </div>
                                                            </div>
                                                            <div class=" mt-auto"> <!-- mt-auto pushes footer to bottom -->
                                                                <div class="d-flex justify-content-between">
                                                                    <c:if test="${sessionScope['account'].getRole_id() == 1}">
                                                                        <div>
                                                                            <form method="post" action="SubjectController" >
                                                                                <input type="hidden" name="service" value="toggleStatus">
                                                                                <input type="hidden" name="postId" value="${subjectList.subject_id}">
                                                                                <button type="submit" class="btn-link p-0">
                                                                                    <c:choose>
                                                                                        <c:when test="${subjectList.status}">
                                                                                            <span class="badge badge-success">Active</span>
                                                                                        </c:when>
                                                                                        <c:otherwise>
                                                                                            <span class="badge badge-secondary">Inactive</span>
                                                                                        </c:otherwise>
                                                                                    </c:choose>
                                                                                </button>
                                                                            </form>
                                                                        </div>
                                                                    </c:if>
                                                                    <c:if test="${sessionScope['account'].getRole_id() != 1}">
                                                                        <div>
                                                                            <c:choose>
                                                                                <c:when test="${subjectList.status}">
                                                                                    <span class="badge badge-success">Active</span>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <span class="badge badge-secondary">Inactive</span>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </div>
                                                                    </c:if>
                                                                    <div class="price">
                                                                        <form method="post" action="SubjectController">
                                                                            <input type="hidden" name="service" value="CourseDetail">
                                                                            <input type="hidden" name="pid" value="${subjectList.subject_id}">
                                                                            <button type="submit" class="btn">Read More</button>
                                                                        </form>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                        <div class="pagination-bx rounded-sm gray clearfix">
                                            <ul class="pagination">
                                                <c:if test="${currentPage > 1}">
                                                    <li class="page-item">
                                                        <a class="page-link" href="SubjectController?service=ListAllSubject&page=${currentPage - 1}&sortColumn=${sortColumn}&sortOrder=${sortOrder}&keyword=${param.keyword}">
                                                            <i class="ti-arrow-left"></i>
                                                        </a>
                                                    </li>
                                                </c:if>
                                                <c:if test="${currentPage > 3}">
                                                    <li class="page-item">
                                                        <a class="page-link" href="SubjectController?service=ListAllSubject&page=1&sortColumn=${sortColumn}&sortOrder=${sortOrder}&keyword=${param.keyword}">1</a>
                                                    </li>
                                                    <li class="page-item disabled"><span class="page-link">...</span></li>
                                                    </c:if>

                                                <c:forEach var="i" begin="${currentPage > 2 ? currentPage - 2 : 1}" end="${currentPage + 2 > maxPage ? maxPage : currentPage + 2}">
                                                    <li class="page-item ${currentPage == i ? 'active' : ''}">
                                                        <a class="page-link" href="SubjectController?service=ListAllSubject&page=${i}&sortColumn=${sortColumn}&sortOrder=${sortOrder}&keyword=${param.keyword}">${i}</a>
                                                    </li>
                                                </c:forEach>

                                                <c:if test="${currentPage < maxPage - 2}">
                                                    <li class="page-item disabled"><span class="page-link">...</span></li>
                                                    <li class="page-item">
                                                        <a class="page-link" href="SubjectController?service=ListAllSubject&page=${maxPage}&sortColumn=${sortColumn}&sortOrder=${sortOrder}&keyword=${param.keyword}">${maxPage}</a>
                                                    </li>
                                                </c:if>

                                                <c:if test="${currentPage < maxPage}">
                                                    <li class="page-item">
                                                        <a class="page-link" href="SubjectController?service=ListAllSubject&page=${currentPage + 1}&sortColumn=${sortColumn}&sortOrder=${sortOrder}&keyword=${param.keyword}">
                                                            <i class="ti-arrow-right"></i>
                                                        </a>
                                                    </li>
                                                </c:if>
                                            </ul>
                                        </div>
                                    </c:if>
                                    <c:if test="${empty subjectList}">
                                        <p>No subject found.</p>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- contact area END -->

            </div>
            <!-- Content END-->
            <!-- Footer ==== -->
            <jsp:include page="../Footer.jsp"/>
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

