<%-- 
    Document   : ProfileUser
    Created on : Jun 2, 2024, 4:37:23 PM
    Author     : Sunshine
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
            <jsp:include page="Header.jsp"/>
            <!-- header END ==== -->
            <!-- Content -->
            <div class="page-content bg-white">
                <!-- inner page banner -->
                <div class="page-banner ovbl-dark" style="background-image:url(assets/images/banner/banner1.jpg);">
                    <div class="container">
                        <div class="page-banner-entry">
                            <h1 class="text-white">Profile</h1>
                        </div>
                    </div>
                </div>
                <!-- Breadcrumb row -->
                <div class="breadcrumb-row">
                    <div class="container">
                        <ul class="list-inline">
                            <li><a href="#">Home</a></li>
                            <li>Profile</li>
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
                                    <div class="profile-bx text-center">
                                        <div class="user-profile-thumb">
                                            <img src="assets/images/profile/pic1.jpg" alt=""/>
                                        </div>
                                        <div class="profile-info">
                                            <h4>${user.user_name}</h4>
                                            <span>${user.email}</span>
                                        </div>
                                        <div class="profile-social">
                                            <ul class="list-inline m-a0">
                                                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                                <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                                                <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                            </ul>
                                        </div>
                                        <div class="profile-tabnav">
                                            <ul class="nav nav-tabs">
                                                <li class="nav-item">
                                                    <a class="nav-link active" data-toggle="tab" href="#courses"><i class="ti-book"></i>Courses</a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link " data-toggle="tab" href="#post-list"><i class="ti-book"></i>Post</a>
                                                </li>
                                                <li class="nav-item" active>
                                                    <a class="nav-link" data-toggle="tab" href="#quiz-results"><i class="ti-bookmark-alt"></i>Quiz Results </a>
                                                </li>
                                                <li class="nav-item ">
                                                    <a class="nav-link" data-toggle="tab" href="#edit-profile"><i class="ti-pencil-alt"></i>Edit Profile</a>
                                                </li>
                                                <li class="nav-item ">
                                                    <a class="nav-link" data-toggle="tab" href="#change-password"><i class="ti-lock"></i>Change Password</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-9 col-md-8 col-sm-12 m-b30">
                                    <div class="profile-content-bx">
                                        <div class="tab-content">
                                            <div class="tab-pane active" id="courses">
                                                <div class="profile-head">
                                                    <h3>My Courses</h3>
                                                    <div class="feature-filters style1 ml-auto">
                                                        <ul class="filters" data-toggle="buttons">
                                                            <li data-filter="" class="btn active">
                                                                <input type="radio">
                                                                <a href="#"><span>All</span></a> 
                                                            </li>
                                                            <li data-filter="publish" class="btn">
                                                                <input type="radio">
                                                                <a href="#"><span>Publish</span></a> 
                                                            </li>
                                                            <li data-filter="pending" class="btn">
                                                                <input type="radio">
                                                                <a href="#"><span>Pending</span></a> 
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="courses-filter">
                                                    <div class="clearfix">
                                                        <ul id="masonry" class="ttr-gallery-listing magnific-image row">
                                                            <li class="action-card col-xl-4 col-lg-6 col-md-12 col-sm-6 publish">
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
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane" id="post-list">
                                                <div class="profile-head">
                                                    <h3>My Post</h3>
                                                    <div class="feature-filters style1 ml-auto">
                                                        <ul class="filters" data-toggle="buttons">
                                                            <li data-filter="all" class="btn active">
                                                                <input type="radio" name="filter" checked>
                                                                <a href="#"><span>All</span></a> 
                                                            </li>
                                                            <li data-filter="publish" class="btn">
                                                                <input type="radio" name="filter">
                                                                <a href="#"><span>Publish</span></a> 
                                                            </li>
                                                            <li data-filter="pending" class="btn">
                                                                <input type="radio" name="filter">
                                                                <a href="#"><span>Pending</span></a> 
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="courses-filter ">
                                                    <div class="clearfix post-list">
                                                        <ul id="masonry" class="ttr-gallery-listing magnific-image row">
                                                            <c:if test="${not empty posts}">
                                                                <c:forEach var="post" items="${posts}">
                                                                    <li class="action-card col-xl-4 col-lg-6 col-md-12 col-sm-6 ${post.status ? 'publish' : 'pending'}">
                                                                        <div class="cours-bx">
                                                                            <div class="action-box">
                                                                                <img src="${post.thumbnailUrl}" alt="">
                                                                                <form method="post" action="PostController" style="display:inline;">
                                                                                    <input type="hidden" name="service" value="updatePost">
                                                                                    <input type="hidden" name="pid" value="${post.postId}">
                                                                                    <button type="submit" class="btn ">Update</button>
                                                                                </form>
                                                                            </div>
                                                                            <div class="info-bx text-center">
                                                                                <h5><a href="#">${post.title}</a></h5>
                                                                                <span>
                                                                                    <c:choose>
                                                                                        <c:when test="${post.status}">
                                                                                            <span class="badge badge-success">Publish</span>
                                                                                        </c:when>
                                                                                        <c:otherwise>
                                                                                            <span class="badge badge-danger">Pending</span>
                                                                                        </c:otherwise>
                                                                                    </c:choose>
                                                                                </span>
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
                                                                    </li>
                                                                </c:forEach>
                                                            </c:if>
                                                            <c:if test="${empty posts}">
                                                                <p>No post found.</p>
                                                            </c:if>
                                                        </ul>
                                                        <c:if test="${totalPost.size() > pageSize}">
                                                            <div class="pagination-bx rounded-sm gray clearfix">
                                                                <ul class="pagination">
                                                                    <c:if test="${currentPage > 1}">
                                                                        <li class="previous"><a href="userController?action=profileUserPage&Tab=post-list&userId=${user.user_id}&page=${currentPage - 1}&sortColumn=${sortBy}&sortOrder=${sortOrder}&searchSetting=${param.searchSetting}"><i class="ti-arrow-left"></i> Prev</a></li>
                                                                            <c:if test="${currentPage > 2}">
                                                                            <li><a href="userController?action=profileUserPage&Tab=post-list&userId=${user.user_id}&page=${currentPage - 2}&sortColumn=${sortBy}&sortOrder=${sortOrder}&searchSetting=${param.searchSetting}" >${currentPage - 2}</a></li>
                                                                            </c:if>
                                                                        <li><a href="userController?action=profileUserPage&Tab=post-list&userId=${user.user_id}&page=${currentPage - 1}&sortColumn=${sortBy}&sortOrder=${sortOrder}&searchSetting=${param.searchSetting}" >${currentPage - 1}</a></li>
                                                                        </c:if>
                                                                    <li class="active"><a href="userController?action=profileUserPage&Tab=post-list&userId=${user.user_id}&page=${currentPage}&sortColumn=${sortBy}&sortOrder=${sortOrder}&searchSetting=${param.searchSetting}" >${currentPage}</a></li>
                                                                    <li><a href="userController?action=profileUserPage&Tab=post-list&userId=${user.user_id}&page=${currentPage + 1}&sortColumn=${sortBy}&sortOrder=${sortOrder}&searchSetting=${param.searchSetting}" >${currentPage + 1}</a></li>
                                                                    <li><a href="userController?action=profileUserPage&Tab=post-list&userId=${user.user_id}&page=${currentPage+2}&sortColumn=${sortBy}&sortOrder=${sortOrder}&searchSetting=${param.searchSetting}" >${currentPage+2}</a></li>
                                                                    <li class="next"><a href="userController?Tab=post-list&action=profileUserPage&userId=${user.user_id}&page=${currentPage + 1}&sortColumn=${sortBy}&sortOrder=${sortOrder}&searchSetting=${param.searchSetting}" >Next<i class="ti-arrow-right"></i></a></li>
                                                                </ul>
                                                            </div>
                                                        </c:if>

                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane" id="quiz-results">
                                                <div class="profile-head">
                                                    <h3>Quiz Results</h3>
                                                </div>
                                                <div class="courses-filter">
                                                    <div class="row">
                                                        <div class="col-md-6 col-lg-6">
                                                            <ul class="course-features">
                                                                <li><i class="ti-book"></i> <span class="label">Lectures</span> <span class="value">8</span></li>
                                                                <li><i class="ti-help-alt"></i> <span class="label">Quizzes</span> <span class="value">1</span></li>
                                                                <li><i class="ti-time"></i> <span class="label">Duration</span> <span class="value">60 hours</span></li>
                                                                <li><i class="ti-stats-up"></i> <span class="label">Skill level</span> <span class="value">Beginner</span></li>
                                                                <li><i class="ti-smallcap"></i> <span class="label">Language</span> <span class="value">English</span></li>
                                                                <li><i class="ti-user"></i> <span class="label">Students</span> <span class="value">32</span></li>
                                                                <li><i class="ti-check-box"></i> <span class="label">Assessments</span> <span class="value">Yes</span></li>
                                                            </ul>
                                                        </div>
                                                        <div class="col-md-6 col-lg-6">
                                                            <ul class="course-features">
                                                                <li><i class="ti-book"></i> <span class="label">Lectures</span> <span class="value">8</span></li>
                                                                <li><i class="ti-help-alt"></i> <span class="label">Quizzes</span> <span class="value">1</span></li>
                                                                <li><i class="ti-time"></i> <span class="label">Duration</span> <span class="value">60 hours</span></li>
                                                                <li><i class="ti-stats-up"></i> <span class="label">Skill level</span> <span class="value">Beginner</span></li>
                                                                <li><i class="ti-smallcap"></i> <span class="label">Language</span> <span class="value">English</span></li>
                                                                <li><i class="ti-user"></i> <span class="label">Students</span> <span class="value">32</span></li>
                                                                <li><i class="ti-check-box"></i> <span class="label">Assessments</span> <span class="value">Yes</span></li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane" id="edit-profile">
                                                <div class="profile-head">
                                                    <h3>Edit Profile</h3>
                                                </div>
                                                <form class="edit-profile" action="${pageContext.request.contextPath}/userController" method="post">
                                                    <input type="hidden" name="userId" value="${user.user_id}">
                                                    <input type="hidden" name="action" value="userChangeInfo">
                                                    <div class="">
                                                        <div class="form-group row">
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-10 ml-auto">
                                                                <h3>1. Personal Details</h3>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">User Name</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <input class="form-control" type="text" name="username" value="${user.user_name}">
                                                                <span class="error" style="font-size: smaller;">${errors['userNameError']}</span>
                                                                <span class="error" style="font-size: smaller;">${errors['userNameDup']}</span>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Phone No.</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <input class="form-control" type="text" name="phone" value="${user.phone}">
                                                                <span class="error" style="font-size: smaller;">${errors['phoneError']}</span>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Full Name</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <input class="form-control" type="text" name="fullname" value="${user.full_name}">
                                                                <span class="error" style="font-size: smaller;">${errors['fullnameError']}</span>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Email</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <input class="form-control" type="text" name="email" value="${user.email}">
                                                                <span class="error" style="font-size: smaller;">${errors['emailError']}</span>
                                                                <span class="error" style="font-size: smaller;">${errors['emailDup']}</span>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Gender</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <div class="form-check form-check-inline">
                                                                    <input type="radio" class="form-check-input" name="gender" id="male" value="Male" <c:if test="${user.gender == 'Male'}">checked</c:if>>
                                                                        <label class="form-check-label" for="male">Male</label>
                                                                    </div>
                                                                    <div class="form-check form-check-inline me-3">
                                                                        <input type="radio" class="form-check-input" name="gender" id="female" value="Female" <c:if test="${user.gender == 'Female'}">checked</c:if>>
                                                                        <label class="form-check-label" for="female">Female</label>
                                                                    </div>
                                                                    <div class="form-check form-check-inline">
                                                                        <input type="radio" class="form-check-input" name="gender" id="other" value="Other" <c:if test="${user.gender == 'Other'}">checked</c:if>>
                                                                        <label class="form-check-label" for="other">Other</label>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Role</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <c:choose>
                                                                <c:when test="${not empty roles}">
                                                                    <c:forEach var="role" items="${roles}">
                                                                        <c:if test="${role.settingId == user.role_id}">
                                                                            <span>${role.settingName}</span>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span>No role found</span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </div>
                                                    </div>
                                                    <div class="seperator"></div>   
                                                    <div class="">
                                                        <div class="">
                                                            <div class="row">
                                                                <div class="col-12 col-sm-3 col-md-3 col-lg-2">
                                                                </div>
                                                                <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                    <button type="submit" class="btn">Save changes</button>
                                                                    <!--<button type="reset" class="btn-secondry">Cancel</button>-->
                                                                </div>
                                                                <div><span class="error" style="font-size: smaller;">${errors['Success']}</span></div>
                                                                <div><span class="error" style="font-size: smaller;">${errors['Errors']}</span></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="tab-pane" id="change-password">
                                                <div class="profile-head">
                                                    <h3>Change Password</h3>
                                                </div>
                                                <form class="edit-profile" action="${pageContext.request.contextPath}/userController" method="post">
                                                    <input type="hidden" name="userid" value="${user.user_id}">
                                                    <input type="hidden" name="action" value="userChangePass">
                                                    <div class="">
                                                        <div class="form-group row">
                                                            <div class="col-12 col-sm-8 col-md-8 col-lg-9 ml-auto">
                                                                <h3>Password</h3>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-4 col-md-4 col-lg-3 col-form-label">Current Password</label>
                                                            <div class="col-12 col-sm-8 col-md-8 col-lg-7">
                                                                <input class="form-control" type="password" name="currentPassword" value="">
                                                                <span class="error" style="font-size: smaller;">${errors['errosOldPass']}</span>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-4 col-md-4 col-lg-3 col-form-label">New Password</label>
                                                            <div class="col-12 col-sm-8 col-md-8 col-lg-7">
                                                                <input class="form-control" type="password" name="newPassword" value="">
                                                                <span class="error" style="font-size: smaller;">${errors['errorsSamePass']}</span>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-4 col-md-4 col-lg-3 col-form-label">Re Type New Password</label>
                                                            <div class="col-12 col-sm-8 col-md-8 col-lg-7">
                                                                <input class="form-control" type="password" name="renewPassword" value="">
                                                                <span class="error" style="font-size: smaller;">${errors['errorsSamePass']}</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-12 col-sm-4 col-md-4 col-lg-3">
                                                        </div>
                                                        <div class="col-12 col-sm-8 col-md-8 col-lg-7">
                                                            <button type="submit" class="btn">Changes Password</button>
                                                            <button type="reset" class="btn-secondry">Cancel</button>
                                                        </div>
                                                        <div>
                                                            <span class="error" style="font-size: smaller;">${errors['errorsUpdate']}</span>
                                                        </div>
                                                    </div>

                                                </form>
                                            </div>
                                        </div>
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
    <script>
        // Function to get URL parameter by name
        function getParameterByName(name) {
            const urlParams = new URLSearchParams(window.location.search);
            return urlParams.get(name);
        }

        document.addEventListener("DOMContentLoaded", function () {
            const tab = "${tab}"; // Lấy giá trị của 'tab' từ request attribute
            if (tab) {
                // Remove 'active' and 'show' classes from all tab links and tab panes
                document.querySelectorAll('.nav-link').forEach(link => link.classList.remove('active'));
                document.querySelectorAll('.tab-pane').forEach(pane => pane.classList.remove('active', 'show'));
                // Find the tab link corresponding to the parameter and click it
                const targetTabLink = document.querySelector(`.nav-link[href='#${tab}']`);
                if (targetTabLink) {
                    targetTabLink.classList.add('active');
                    document.querySelector(`.tab-pane#${tab}`).classList.add('active', 'show');
                }
            }
            //                else {
            //                    // If no tab parameter, activate the first tab
            //                    document.querySelector('.nav-link').classList.add('active');
            //                    document.querySelector('.tab-pane').classList.add('show', 'active');
            //                }
        });

        document.addEventListener("DOMContentLoaded", function () {
            const filterButtons = document.querySelectorAll('.filters li');
            const posts = document.querySelectorAll('#masonry .action-card');

            filterButtons.forEach(button => {
                button.addEventListener('click', function () {
                    filterButtons.forEach(btn => btn.classList.remove('active'));
                    this.classList.add('active');

                    const filter = this.getAttribute('data-filter');

                    posts.forEach(post => {
                        if (filter === 'all') {
                            post.style.display = 'block';
                        } else {
                            if (post.classList.contains(filter)) {
                                post.style.display = 'block';
                            } else {
                                post.style.display = 'none';
                            }
                        }
                    });
                });
            });
        });

    </script>
</body>

</html>
