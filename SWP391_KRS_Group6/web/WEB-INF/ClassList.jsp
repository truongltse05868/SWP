<%-- 
    Document   : ClassList
    Created on : Jun 3, 2024, 10:39:49 AM
    Author     : Ngs-MT305
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
        <style>
            .toast-container {
                position: fixed;
                top: 80px; /* Điều chỉnh giá trị top để đặt vị trí thấp hơn */
                right: 20px;
                z-index: 1050;
            }
            .toast {
                opacity: 0.9;
                font-size: 1.5em; /* Tăng kích thước của toast */
                /*background-color: #28a745;  Nền màu xanh lá cây */
                /*color: white;  Màu chữ trắng */
                border: 2px solid #218838; /* Viền màu xanh đậm */
                border-radius: 10px; /* Đường viền cong */
            }
            .toast-header {
                font-size: 1.75em; /* Tăng kích thước tiêu đề của toast */
                background-color: #218838; /* Nền màu xanh đậm cho tiêu đề */
                color: white; /* Màu chữ trắng */
                border-bottom: 1px solid rgba(255, 255, 255, 0.2); /* Viền dưới tiêu đề */
                border-top-left-radius: 10px; /* Đường viền cong góc trên bên trái */
                border-top-right-radius: 10px; /* Đường viền cong góc trên bên phải */
            }
            .toast-body {
                font-size: 1.5em; /* Tăng kích thước nội dung của toast */
            }
        </style>
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
                                        <!--                                        <div class="form-group">
                                                                                    <div class="input-group">
                                                                                        <label>Search Courses</label>
                                                                                        <input name="dzName" type="text" required class="form-control">
                                                                                    </div>
                                                                                </div>-->
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
                                    <c:if test="${not empty classes}">
                                        <div class="row">
                                            <c:forEach var="classList" items="${classes}">
                                                <div class="col-md-6 col-lg-4 col-sm-6 mb-4">
                                                    <div class="card">
                                                        <div class="card-img-top">
                                                            <img src="assets/images/courses/pic1.jpg" class="img-fluid" alt="Class Image">
                                                        </div>
                                                        <div class="card-body text-center">
                                                            <form method="post" action="ClassController">
                                                                <input type="hidden" name="action" value="classDetail">
                                                                <input type="hidden" name="classId" value="${classList.class_id}">
                                                                <h5><button type="submit" class="btn btn-link p-0">Class: ${classList.class_name}</button></h5>
                                                            </form>
                                                            <span>
                                                                <c:choose>
                                                                    <c:when test="${not empty subjectList}">
                                                                        <c:forEach var="subjects" items="${subjectList}">
                                                                            <c:if test="${subjects.subject_id == classList.subject_id}">
                                                                                ${subjects.subject_name}
                                                                            </c:if>
                                                                        </c:forEach>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        No subject found
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </span>
                                                        </div>
                                                        <div class="card-footer">
                                                            <form method="post" action="ClassController">
                                                                <input type="hidden" name="action" value="enrollClass">
                                                                <input type="hidden" name="classId" value="${classList.class_id}">
                                                                <button type="submit" class="btn btn-primary btn-block">Enroll Me</button>
                                                            </form>
                                                            <div class="price mt-2">
                                                                <span>Student: <c:out value="${userCountMap[classList.class_id] != null ? userCountMap[classList.class_id] : 0}" /></span>
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
                                        <!--<div> ${successMessage} </div>-->
                                    <!--hiển thị thông báo-->
                                    <c:if test="${not empty successMessage}">
                                        <div class="toast-container">
                                            <div id="successToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true" data-autohide="true">
                                                <div class="toast-header">
                                                    <strong class="mr-auto">Thông báo</strong>
                                                    <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="toast-body">
                                                    ${successMessage} 
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="row mt-3">
                                        <nav aria-label="Page navigation" class="col-12">
                                            <ul class="pagination justify-content-center">
                                                <c:forEach begin="1" end="${totalPages}" var="i">
                                                    <li class="page-item <c:if test='${i == currentPage}'>active</c:if>">
                                                        <a class="page-link" href="ClassController?action=ListAllClass&userId=${userId}&page=${i}&size=${pageSize}">${i}</a>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </nav>
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
        <!-- jQuery and Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <script>
            $(document).ready(function () {
                var successMessage = "${successMessage}";
                if (successMessage) {
                    $('#successToast').toast({
                        delay: 5000
                    });
                    $('#successToast').toast('show');
                }
            });
        </script>
    </body>

</html>

