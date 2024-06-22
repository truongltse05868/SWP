<%-- 
    Document   : ClassDetail
    Created on : Jun 22, 2024, 10:38:57 AM
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
        <title>KRS: Class List</title>

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
        <script>
            function sendPostRequestLesson(action, subjectId) {
                // Tạo form ẩn
                var form = document.createElement("form");
                form.setAttribute("method", "post");
                form.setAttribute("action", "LessonController");

                // Tạo input ẩn cho action
                var hiddenField = document.createElement("input");
                hiddenField.setAttribute("type", "hidden");
                hiddenField.setAttribute("name", "action");
                hiddenField.setAttribute("value", action);

                // Thêm input ẩn vào form
                form.appendChild(hiddenField);
                // Create hidden input for searchUsername if it exists

                var hiddenField2 = document.createElement("input");
                searchUsernameInput.type = "hidden";
                searchUsernameInput.name = "subjectId";
                searchUsernameInput.value = subjectId;
                form.appendChild(hiddenField2);

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
            <jsp:include page="../Header.jsp"/>
            <!-- header END ==== -->
            <!-- Content -->
            <div class="page-content bg-white">
                <!-- inner page banner -->
                <div class="page-banner ovbl-dark" style="background-image:url(assets/images/banner/banner3.jpg);">
                    <div class="container">
                        <div class="page-banner-entry">
                            <h1 class="text-white">Our Class</h1>
                        </div>
                    </div>
                </div>
                <!-- Breadcrumb row -->
                <div class="breadcrumb-row">
                    <div class="container">
                        <ul class="list-inline">
                            <li><a href="#">Home</a></li>
                            <li>Our Class</li>
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
                                    <div class="widget recent-posts-entry widget-courses">
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
                                    </div>
                                </div>
                                <div class="col-lg-9 col-md-8 col-sm-12">
                                    <c:if test="${not empty users}">
                                        <table >
                                            <tr>
                                                <th><a href="SortByUserName" onclick="sendPostRequestSort('user_name', '${searchUsername}');return false;">Username</a></th>
                                                
                                                <th><a href="SortByFullName" onclick="sendPostRequestSort('full_name', '${searchUsername}');return false;">Full Name</a></th>


                                                <th>Role</th>
                                                


                                            </tr>
                                            <c:forEach var="user" items="${users}">
                                                <tr>
                                                    <td>${user.user_name}</td>

                                                    <td>${user.full_name}</td>


                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${not empty roles}">
                                                                <c:forEach var="role" items="${roles}">
                                                                    <c:if test="${role.settingId == user.role_id}">
                                                                        ${role.settingName}
                                                                    </c:if>
                                                                </c:forEach>
                                                            </c:when>
                                                            <c:otherwise>
                                                                Role information is unavailable.
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    
                                                </tr>
                                            </c:forEach>
                                        </table>
                                        <div class="pagination-bx rounded-sm gray clearfix">
                                            <ul class="pagination">
                                                <c:if test="${currentPage > 1}">
                                                    <li class="previous"><a href="ClassController?action=ListAllClassAdmin&page=${currentPage - 1}&sortColumn=${sortColumn}&sortOrder=${sortOrder}&searchClass=${param.searchSetting}"><i class="ti-arrow-left"></i> Prev</a></li>
                                                        <c:if test="${currentPage > 2}">
                                                        <li><a href="ClassController?action=ListAllClassAdmin&page=${currentPage - 2}&sortColumn=${sortColumn}&sortOrder=${sortOrder}&searchClass=${param.searchSetting}" >${currentPage - 2}</a></li>
                                                        </c:if>
                                                    <li><a href="ClassController?action=ListAllClassAdmin&page=${currentPage - 1}&sortColumn=${sortColumn}&sortOrder=${sortOrder}&searchClass=${param.searchSetting}" >${currentPage - 1}</a></li>

                                                </c:if>
                                                <li class="active"><a href="ClassController?action=ListAllClassAdmin&page=${currentPage}&sortColumn=${sortColumn}&sortOrder=${sortOrder}&searchClass=${param.searchSetting}" >${currentPage}</a></li>
                                                <li><a href="ClassController?action=ListAllClassAdmin&page=${currentPage + 1}&sortColumn=${sortColumn}&sortOrder=${sortOrder}&searchClass=${param.searchSetting}" >${currentPage + 1}</a></li>
                                                <li><a href="ClassController?action=ListAllClassAdmin&page=${currentPage+2}&sortColumn=${sortColumn}&sortOrder=${sortOrder}&searchClass=${param.searchSetting}" >${currentPage+2}</a></li>
                                                <li class="next"><a href="ClassController?action=ListAllClassAdmin&page=${currentPage + 1}&sortColumn=${sortColumn}&sortOrder=${sortOrder}&searchClass=${param.searchSetting}" >Next<i class="ti-arrow-right"></i></a></li>
                                            </ul>
                                        </div>
                                    </c:if>

                                    <c:if test="${empty users}">
                                        <p>No users found.</p>
                                    </c:if>
                                    <div>${successMessage}</div>
                                    <!--                                    <div class="col-lg-12 m-b20">
                                                                            <div class="pagination-bx rounded-sm gray clearfix">
                                                                                <ul class="pagination">
                                                                                    <li class="previous"><a href="#"><i class="ti-arrow-left"></i> Prev</a></li>
                                                                                    <li class="active"><a href="#">1</a></li>
                                                                                    <li><a href="#">2</a></li>
                                                                                    <li><a href="#">3</a></li>
                                                                                    <li class="next"><a href="#">Next <i class="ti-arrow-right"></i></a></li>
                                                                                </ul>
                                                                            </div>
                                                                        </div>-->
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

