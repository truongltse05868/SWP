<%-- 
    Document   : userList
    Created on : May 20, 2024, 2:41:19 AM
    Author     : simon
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">


    </head>
    <body class="ttr-opened-sidebar ttr-pinned-sidebar">
        <!-- header start -->
        <jsp:include page="../DashboardNav.jsp"/>
        <!-- sidebar menu end -->
        <main class="ttr-wrapper">
            <div class="container-fluid">
                <div class="db-breadcrumb">
                    <h4 class="breadcrumb-title">Post List</h4>
                    <ul class="db-breadcrumb-list">
                        <li><a href="Home"><i class="fa fa-home"></i>Home</a></li>
                        <li>Post List</li>
                    </ul>
                </div>
                <div class="row">
                    <div class="col-lg-12 m-b30">
                        <div class="widget-box">
                            <div>
                                <c:if test="${not empty successMessage}">
                                    <p style="color:green;">${successMessage}</p>
                                </c:if>
                            </div>
                            <div class="widget-inner">
                                <!-- Add Post Form -->
                                <form method="post" action="PostController" style="display:inline;">
                                    <input type="hidden" name="service" value="insertPost">
                                    <button type="submit" class="btn btn-primary btn-sm">Add</button>
                                </form>
                                <!-- Search and Sort Form -->
                                <form method="get" action="PostController" class="form-inline my-3">
                                    <input type="hidden" name="service" value="ListAllPost">
                                    <div class="form-group mx-sm-3 mb-2">
                                        <label for="keyword" class="sr-only">Keyword</label>
                                        <input type="text" class="form-control" id="keyword" name="keyword" placeholder="Search..." value="${param.keyword}">
                                    </div>
                                    <button type="submit" class="btn btn-primary mb-2">Search</button>
                                </form>
                                <!-- Post List Table -->
                                <c:if test="${not empty posts}">
                                    <table class="table table-striped my-3">
                                        <thead>
                                            <tr>
                                                <th>
                                                    <a href="PostController?service=ListAllPost&page=${currentPage}&sortBy=post_id&sortOrder=${sortOrder == 'asc' ? 'desc' : 'asc'}">
                                                        ID
                                                        <c:choose>
                                                            <c:when test="${sortBy == 'post_id'}">
                                                                <i class="bi bi-arrow-${sortOrder == 'asc' ? 'up' : 'down'}"></i>
                                                            </c:when>
                                                        </c:choose>
                                                    </a>
                                                </th>
                                                <th>
                                                    <a href="PostController?service=ListAllPost&page=${currentPage}&sortBy=title&sortOrder=${sortOrder == 'asc' ? 'desc' : 'asc'}">
                                                        Title
                                                        <c:choose>
                                                            <c:when test="${sortBy == 'title'}">
                                                                <i class="bi bi-arrow-${sortOrder == 'asc' ? 'up' : 'down'}"></i>
                                                            </c:when>
                                                        </c:choose>
                                                    </a>
                                                </th>
                                                <th>Thumbnail Url</th>
                                                <th>Publish</th>
                                                <th>Author</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="post" items="${posts}">
                                                <tr>
                                                    <td><c:out value="${post.postId}"/></td>
                                                    <td><c:out value="${post.title}"/></td>
                                                    <td>  
                                                        <c:if test="${not empty post.thumbnailUrl}">
                                                            <img src="<c:out value='${post.thumbnailUrl}'/>" alt="Thumbnail" style="max-width: 100px; max-height: 100px;">
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <form method="post" action="PostController" style="display:inline;">
                                                            <input type="hidden" name="service" value="toggleStatus">
                                                            <input type="hidden" name="postId" value="${post.postId}">
                                                            <button type="submit" class="btn-link p-0">
                                                                <c:choose>
                                                                    <c:when test="${post.status}">
                                                                        <span class="badge badge-success">Published</span>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <span class="badge badge-danger">Unpublished</span>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </button>
                                                        </form>
                                                    </td>
                                                    <td>
                                                        <c:choose>
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
                                                        </c:choose>
                                                    </td>
                                                    <td>
                                                        <form method="post" action="PostController" style="display:inline;">
                                                            <input type="hidden" name="service" value="updatePost">
                                                            <input type="hidden" name="pid" value="${post.postId}">
                                                            <button type="submit" class="btn btn-primary btn-sm">Update</button>
                                                        </form>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <div class="pagination-bx rounded-sm gray clearfix">
                                        <ul class="pagination">
                                            <c:if test="${currentPage > 1}">
                                                <li class="page-item">
                                                    <a class="page-link" href="PostController?service=ListAllPost&page=${currentPage - 1}&sortColumn=${sortColumn}&sortOrder=${sortOrder}&keyword=${param.keyword}">
                                                        <i class="ti-arrow-left"></i>
                                                    </a>
                                                </li>
                                            </c:if>
                                            <c:if test="${currentPage > 3}">
                                                <li class="page-item">
                                                    <a class="page-link" href="PostController?service=ListAllPost&page=1&sortColumn=${sortColumn}&sortOrder=${sortOrder}&keyword=${param.keyword}">1</a>
                                                </li>
                                                <li class="page-item disabled"><span class="page-link">...</span></li>
                                                </c:if>

                                            <c:forEach var="i" begin="${currentPage > 2 ? currentPage - 2 : 1}" end="${currentPage + 2 > maxPage ? maxPage : currentPage + 2}">
                                                <li class="page-item ${currentPage == i ? 'active' : ''}">
                                                    <a class="page-link" href="PostController?service=ListAllPost&page=${i}&sortColumn=${sortColumn}&sortOrder=${sortOrder}&keyword=${param.keyword}">${i}</a>
                                                </li>
                                            </c:forEach>

                                            <c:if test="${currentPage < maxPage - 2}">
                                                <li class="page-item disabled"><span class="page-link">...</span></li>
                                                <li class="page-item">
                                                    <a class="page-link" href="PostController?service=ListAllPost&page=${maxPage}&sortColumn=${sortColumn}&sortOrder=${sortOrder}&keyword=${param.keyword}">${maxPage}</a>
                                                </li>
                                            </c:if>

                                            <c:if test="${currentPage < maxPage}">
                                                <li class="page-item">
                                                    <a class="page-link" href="PostController?service=ListAllPost&page=${currentPage + 1}&sortColumn=${sortColumn}&sortOrder=${sortOrder}&keyword=${param.keyword}">
                                                        <i class="ti-arrow-right"></i>
                                                    </a>
                                                </li>
                                            </c:if>
                                        </ul>
                                    </div>
                                </c:if>
                                <c:if test="${empty posts}">
                                    <p>No posts found.</p>

                                </c:if>
                                <!-- Pagination Controls -->

                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </main>

        <!--Main container end -->

        <!-- Footer ==== -->
        <footer>
            <div class="footer">
                <div class="footer-inner">
                    <div class="footer-item">
                        <div class="footer-title">About Us</div>
                        <p class="footer-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis varius nisl eget enim varius, a luctus velit facilisis.</p>
                    </div>
                    <div class="footer-item">
                        <div class="footer-title">Quick Links</div>
                        <ul class="footer-links">
                            <li><a href="#">Home</a></li>
                            <li><a href="#">Courses</a></li>
                            <li><a href="#">Events</a></li>
                            <li><a href="#">Blog</a></li>
                        </ul>
                    </div>
                    <div class="footer-item">
                        <div class="footer-title">Contact Us</div>
                        <ul class="footer-contact">
                            <li><span>Address:</span> 123 Street, City, Country</li>
                            <li><span>Phone:</span> (123) 456-7890</li>
                            <li><span>Email:</span> info@example.com</li>
                        </ul>
                    </div>
                </div>
            </div>
        </footer>
        <!-- Footer END ==== -->

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
        <script src="admin/assets/vendors/scroll/scrollbar.min.js"></script>
        <script src="admin/assets/vendors/scroll/scrollbar.js"></script>
        <script src="admin/assets/js/functions.js"></script>
        <script src="admin/assets/vendors/chart/chart.min.js"></script>
        <script src="admin/assets/js/admin.js"></script>
        <script src="admin/assets/vendors/calendar/moment.min.js"></script>
        <script src="admin/assets/vendors/calendar/fullcalendar.js"></script>
        <script src="admin/assets/vendors/switcher/switcher.js"></script>
    </body>

</html>
