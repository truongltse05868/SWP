<%-- 
    Document   : ClassDetail
    Created on : Jun 22, 2024, 10:38:57 AM
    Author     : Sunshine
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                opacity: 0.95;
                font-size: 1.2em; /* Kích thước hợp lý cho toast */
                background-color: #4CAF50; /* Nền màu xanh lá cây tươi */
                color: white; /* Màu chữ trắng */
                border: none; /* Loại bỏ viền */
                border-radius: 10px; /* Đường viền cong */
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Hiệu ứng đổ bóng */
                padding: 0;
            }
            .toast-header {
                font-size: 1.4em; /* Tăng kích thước tiêu đề của toast */
                background-color: #388E3C; /* Nền màu xanh đậm cho tiêu đề */
                color: white; /* Màu chữ trắng */
                border-bottom: 1px solid rgba(255, 255, 255, 0.2); /* Viền dưới tiêu đề */
                border-top-left-radius: 10px; /* Đường viền cong góc trên bên trái */
                border-top-right-radius: 10px; /* Đường viền cong góc trên bên phải */
                padding: 10px 15px; /* Thêm khoảng cách bên trong */
                display: flex;
                justify-content: space-between;
                align-items: center;
            }
            .toast-body {
                font-size: 1.2em; /* Tăng kích thước nội dung của toast */
                padding: 15px; /* Thêm khoảng cách bên trong */
                border-bottom-left-radius: 10px; /* Đường viền cong góc dưới bên trái */
                border-bottom-right-radius: 10px; /* Đường viền cong góc dưới bên phải */
            }
            .toast .close {
                color: white; /* Màu chữ trắng */
                background: none; /* Loại bỏ nền */
                border: none; /* Loại bỏ viền */
                font-size: 1.5em; /* Kích thước nút đóng */
                padding: 0;
                margin: 0;
            }
        </style>

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
                                    <div class="widget-inner">
                                        <div class="row mb-3">
                                            <form id="searchForm" class="col-md-12" method="post" action="ClassController">
                                                <div class="form-row align-items-center">
                                                    <div class="col-md-4 mb-2">
                                                        <input class="form-control" type="text" name="searchUsername" placeholder="Search by username" value="${searchUsername}">
                                                    </div>
                                                    <div class="col-md-4 mb-2">
                                                        <select class="form-control" name="roleId">
                                                            <c:choose>
                                                                <c:when test="${not empty roles}">
                                                                    <option value="" <c:if test="${searchRoleId == null}">selected</c:if>>All Roles</option>
                                                                    <c:forEach var="role" items="${roles}">
                                                                        <option value="${role.settingId}" <c:if test="${role.settingId == searchRoleId}">selected</c:if>>${role.settingName}</option>
                                                                    </c:forEach>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option value="" selected>All roles</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-4 mb-2">
                                                        <input type="hidden" name="action" value="searchUserInClassUser">
                                                        <input type="hidden" name="classId" value="${classs.class_id}">
                                                        <input type="hidden" name="page" value="${currentPage}">
                                                        <input type="hidden" name="size" value="3">
                                                        <button class="btn btn-primary btn-sm btn-block" type="submit">Search</button>
                                                    </div>
                                                </div>
                                            </form>
                                            <form class="col-md-2 text-right" action="ClassController" method="post">
                                                <input type="hidden" name="action" value="ListAllClass">
                                                <input type="hidden" name="page" value="${currentPage}">
                                                <button type="submit" class="btn btn-secondary btn-sm">Back</button>
                                            </form>
                                        </div>
                                        <div class="row mb-3">
                                            <div class="col-md-12">
                                                <label><strong>Class:</strong> ${classs.class_name}</label>
                                                <!--<label>Teacher: ${classs.class_name}</label>-->
                                            </div>
                                        </div>
                                        <c:if test="${not empty users}">
                                            <table class="table table-striped table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th>
                                                            <a href="ClassController?action=searchUserInClass&page=${currentPage}&classId=${classs.class_id}&size=3&searchClassname=${fn:escapeXml(searchUsername)}&roleId=${searchRoleId}&sortField=user_name&sortOrder=${sortOrder == 'asc' ? 'desc' : 'asc'}">Username</a>
                                                        </th>
                                                        <th>
                                                            <a href="ClassController?action=searchUserInClass&page=${currentPage}&classId=${classs.class_id}&size=3&searchClassname=${fn:escapeXml(searchUsername)}&roleId=${searchRoleId}&sortField=full_name&sortOrder=${sortOrder == 'asc' ? 'desc' : 'asc'}">Full Name</a>
                                                        </th>
                                                        <th>Gender</th>
                                                        <th>Role</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="user" items="${users}">
                                                        <tr>
                                                            <td>${user.user_name}</td>
                                                            <td>${user.full_name}</td>
                                                            <td>${user.gender}</td>
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
                                                </tbody>
                                            </table>
                                            <!-- Pagination -->
                                            <div class="row mt-3">
                                                <nav aria-label="Page navigation" class="col-md-12">
                                                    <ul class="pagination justify-content-center">
                                                        <c:forEach begin="1" end="${totalPages}" var="i">
                                                            <li class="page-item <c:if test='${i == currentPage}'>active</c:if>">
                                                                <a class="page-link" href="ClassController?action=searchUserInClass&page=${i}&classId=${classs.class_id}&size=3&searchClassname=${fn:escapeXml(searchUsername)}&roleId=${searchRoleId}&sortField=${sortField}&sortOrder=${sortOrder}">${i}</a>
                                                            </li>
                                                        </c:forEach>
                                                    </ul>
                                                </nav>
                                            </div>
                                        </c:if>
                                        <c:if test="${empty users}">
                                            <p>No users found.</p>
                                        </c:if>
                                        <!--hiển thị thông báo-->
                                        <c:if test="${not empty successMessage}">
                                            <div class="toast-container">
                                                <div id="successToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true" data-autohide="true">
                                                    <div class="toast-header">
                                                        <strong class="mr-auto">Thông báo</strong>
                                                        <button type="button" class="close" data-dismiss="toast" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="toast-body">
                                                        ${successMessage}
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>

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

