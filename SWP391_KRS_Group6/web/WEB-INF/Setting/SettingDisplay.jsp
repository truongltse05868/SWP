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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

    </head>
    <body class="ttr-opened-sidebar ttr-pinned-sidebar">

        <!-- header start -->

        <jsp:include page="../DashboardNav.jsp"/>
        <!-- sidebar menu end -->

        <!-- Left sidebar menu end -->

        <!--Main container start -->
        <main class="ttr-wrapper">
            <div class="container-fluid">
                <div class="db-breadcrumb">
                    <h4 class="breadcrumb-title">Setting List</h4>
                    <ul class="db-breadcrumb-list">
                        <li><a href="#"><i class="fa fa-home"></i>Home</a></li>
                        <li>Setting List</li>
                    </ul>
                </div>
                <div class="row">
                    <div class="col-lg-12 m-b30">
                        <div class="widget-box">
                            <div class="widget-inner">
                                <form method="post" action="SettingController" style="display:inline;">
                                    <input type="hidden" name="service" value="insertSetting">
                                    <button type="submit" class="btn btn-primary btn-sm">Add</button>
                                </form>
                                <form method="get" action="SettingController" class="form-inline my-3">
                                    <input type="hidden" name="service" value="listAllSetting">
                                    <input type="text" name="searchSetting" class="form-control mr-2" placeholder="Search..." value="${param.searchSetting}">
                                    <button type="submit" class="btn btn-primary">Search</button>
                                </form>
                                <c:if test="${not empty settings}">
                                    <table class="table table-striped my-3">
                                        <thead>
                                            <tr>
                                                <th>
                                                    <a href="SettingController?service=listAllSetting&sortColumn=setting_id&page=${currentPage}&sortOrder=${sortOrder == 'asc' ? 'desc' : 'asc'}">
                                                        ID
                                                        <i class="fas fa-arrow-${sortColumn == 'setting_id' ? (sortOrder == 'asc' ? 'up' : 'down') : ''}"></i>
                                                    </a>
                                                </th>
                                                <th>
                                                    <a href="SettingController?service=listAllSetting&sortColumn=setting_name&page=${currentPage}&sortOrder=${sortOrder == 'asc' ? 'desc' : 'asc'}">
                                                        Name
                                                        <i class="fas fa-arrow-${sortColumn == 'setting_name' ? (sortOrder == 'asc' ? 'up' : 'down') : ''}"></i>
                                                    </a>
                                                </th>
                                                <th>
                                                    <a href="SettingController?service=listAllSetting&sortColumn=type&page=${currentPage}&sortOrder=${sortOrder == 'asc' ? 'desc' : 'asc'}">
                                                        Type
                                                        <i class="fas fa-arrow-${sortColumn == 'type' ? (sortOrder == 'asc' ? 'up' : 'down') : ''}"></i>
                                                    </a>
                                                </th>
                                                <th>Status</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="setting" items="${settings}">
                                                <tr>
                                                    <td><c:out value="${setting.settingId}"/></td>
                                                    <td><c:out value="${setting.settingName}"/></td>
                                                    <td><c:out value="${setting.type}"/></td>
                                                    <td>
                                                        <form method="post" action="SettingController" style="display:inline;">
                                                            <input type="hidden" name="service" value="toggleStatus">
                                                            <input type="hidden" name="settingId" value="${setting.settingId}">
                                                            <button type="submit" class=" btn-link p-0">
                                                                <c:choose>
                                                                    <c:when test="${setting.status}">
                                                                        <span class="badge badge-success">Active</span>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <span class="badge badge-danger">Inactive</span>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </button>
                                                        </form>
                                                    </td>
                                                    <td>
                                                        <form method="post" action="SettingController" style="display:inline;">
                                                            <input type="hidden" name="service" value="updateSetting">
                                                            <input type="hidden" name="pid" value="${setting.settingId}">
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
                                                    <a class="page-link" href="SettingController?service=listAllSetting&page=${currentPage - 1}&sortColumn=${sortColumn}&sortOrder=${sortOrder}&searchSetting=${param.searchSetting}">
                                                        <i class="ti-arrow-left"></i>
                                                    </a>
                                                </li>
                                            </c:if>
                                            <c:if test="${currentPage > 3}">
                                                <li class="page-item">
                                                    <a class="page-link" href="SettingController?service=listAllSetting&page=1&sortColumn=${sortColumn}&sortOrder=${sortOrder}&searchSetting=${param.searchSetting}">1</a>
                                                </li>
                                                <li class="page-item disabled"><span class="page-link">...</span></li>
                                                </c:if>

                                            <c:forEach var="i" begin="${currentPage > 2 ? currentPage - 2 : 1}" end="${currentPage + 2 > maxPage ? maxPage : currentPage + 2}">
                                                <li class="page-item ${currentPage == i ? 'active' : ''}">
                                                    <a class="page-link" href="SettingController?service=listAllSetting&page=${i}&sortColumn=${sortColumn}&sortOrder=${sortOrder}&searchSetting=${param.searchSetting}">${i}</a>
                                                </li>
                                            </c:forEach>

                                            <c:if test="${currentPage < maxPage - 2}">
                                                <li class="page-item disabled"><span class="page-link">...</span></li>
                                                <li class="page-item">
                                                    <a class="page-link" href="SettingController?service=listAllSetting&page=${maxPage}&sortColumn=${sortColumn}&sortOrder=${sortOrder}&searchSetting=${param.searchSetting}">${maxPage}</a>
                                                </li>
                                            </c:if>

                                            <c:if test="${currentPage < maxPage}">
                                                <li class="page-item">
                                                    <a class="page-link" href="SettingController?service=listAllSetting&page=${currentPage + 1}&sortColumn=${sortColumn}&sortOrder=${sortOrder}&searchSetting=${param.searchSetting}">
                                                        <i class="ti-arrow-right"></i>
                                                    </a>
                                                </li>
                                            </c:if>
                                        </ul>
                                    </div>
                                </c:if>
                                <c:if test="${empty settings}">
                                    <p>No settings found.</p>
                                </c:if>
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
                        <p class="footer-text">G6.</p>
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
