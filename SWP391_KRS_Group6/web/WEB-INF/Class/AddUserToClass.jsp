<%-- 
    Document   : userList
    Created on : May 20, 2024, 2:41:19 AM
    Author     : simon
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <script>
            function sendPostRequestSort(sortField) {
                var form = document.createElement('form');
                form.method = 'post';
                form.action = 'ClassController';

                var inputs = [
                    {name: 'classId', value: '${classs.class_id}'},
                    {name: 'searchQuery', value: '${searchQuery}'},
                    {name: 'page', value: '${currentPage}'},
                    {name: 'pageSize', value: '${pageSize}'},
                    {name: 'sortField', value: sortField},
                    {name: 'sortOrder', value: '${sortOrder == "asc" ? "desc" : "asc"}'}
                ];

                inputs.forEach(function (inputData) {
                    var input = document.createElement('input');
                    input.type = 'hidden';
                    input.name = inputData.name;
                    input.value = inputData.value;
                    form.appendChild(input);
                });

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
    <body class="ttr-opened-sidebar ttr-pinned-sidebar">


        <jsp:include page="../DashboardNav.jsp"/>


        <!--Main container start -->
        <main class="ttr-wrapper">
            <div class="container-fluid">
                <div class="db-breadcrumb">
                    <h4 class="breadcrumb-title">Add User To Class</h4>
                    <ul class="db-breadcrumb-list">
                        <li><a href="#"><i class="fa fa-home"></i>Home</a></li>
                        <li>Add User To Class</li>
                    </ul>
                </div>
                <div class="row">
                    <!-- Your Profile Views Chart -->
                    <div class="col-lg-12 m-b30">
                        <div class="widget-box">
                            <div class="widget-inner">
                                <div class="row">
                                    <form class="col-sm-3" method="post" action="ClassController">
                                        <div class="row">
                                            <div class="col">
                                                <input class="form-control" type="text" name="searchQuery" placeholder="Search" value="${searchQuery}">
                                            </div>
                                            <div class="col-auto">
                                                <input type="hidden" name="action" value="searchUserNotInClass">
                                                <input type="hidden" name="classId" value="${classs.class_id}">
                                                <input type="hidden" name="page" value="${currentPage}">
                                                <input type="hidden" name="pageSize" value="${pageSize}">
                                                <button class="btn btn-primary btn-sm" type="submit">Search</button>
                                            </div>
                                        </div>
                                    </form>
                                    <form class="col-sm-7 text-right" action="ClassController" method="post">
                                        <input type="hidden" name="action" value="classDetail">
                                        <input type="hidden" name="classId" value="${classs.class_id}">
                                        <button class="btn btn-primary btn-sm" type="submit"><i class=""></i>Back To Class</button>
                                    </form>
                                </div>
                            </div>
                            <div class="widget-inner">
                                <div class="row">
                                    <label>Class: ${classs.class_name}</label>
                                </div>
                            </div>
                            <div class="widget-inner">
                                <c:if test="${not empty users}">
                                    <table class="table table-bordered">
                                        <thead>
                                            <tr>
                                                <th><a href="#" onclick="sendPostRequestSort('user_name');
                                                        return false;">Username</a></th>
                                                <th><a href="#" onclick="sendPostRequestSort('email'); return false;">Email</a></th>
                                                <th><a href="#" onclick="sendPostRequestSort('full_name');
                                                        return false;">Full Name</a></th>
                                                <th><a href="#" onclick="sendPostRequestSort('phone');
                                                        return false;">Phone</a></th>
                                                <th>Gender</th>
                                                <th>Role</th>
                                                <th>Update</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="user" items="${users}">
                                                <tr>
                                                    <td>${user.user_name}</td>
                                                    <td>${user.email}</td>
                                                    <td>${user.full_name}</td>
                                                    <td>${user.phone}</td>
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
                                                    <td>
                                                        <form action="${pageContext.request.contextPath}/ClassController" method="post">
                                                            <input type="hidden" name="UserId" value="${user.user_id}" />
                                                            <input type="hidden" name="classId" value="${classs.class_id}">
                                                            <input type="hidden" name="page" value="${currentPage}">
                                                            <input type="hidden" name="pageSize" value="${pageSize}">
                                                            <input type="hidden" name="action" value="addUserToClass">
                                                            <button class="btn btn-primary btn-sm" type="submit">Add</button>
                                                        </form>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <!-- Pagination controls -->
                                    <nav aria-label="Page navigation">
                                        <ul class="pagination">
                                            <c:forEach begin="1" end="${totalPages}" var="i">
                                                <li class="page-item <c:if test='${i == currentPage}'>active</c:if>">
                                                    <a class="page-link" href="?action=searchUserNotInClass&classId=${classs.class_id}&page=${i}&pageSize=${pageSize}&searchQuery=${fn:escapeXml(searchQuery)}&sortField=${sortField}&sortOrder=${sortOrder}">${i}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </nav>
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
                        <!--<p class="footer-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis varius nisl eget enim varius, a luctus velit facilisis.</p>-->
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
