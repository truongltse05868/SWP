
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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
        <script>
            function sendPostRequestSort2(sortField, searchLesson, searchSubjectId, sortOrder) {
                const form = document.createElement('form');
                form.method = 'post';
                form.action = 'LessonController';

                const actionField = document.createElement('input');
                actionField.type = 'hidden';
                actionField.name = 'action';
                actionField.value = 'lessonList';
                form.appendChild(actionField);

                const sortFieldInput = document.createElement('input');
                sortFieldInput.type = 'hidden';
                sortFieldInput.name = 'sortField';
                sortFieldInput.value = sortField;
                form.appendChild(sortFieldInput);

                const sortOrderInput = document.createElement('input');
                sortOrderInput.type = 'hidden';
                sortOrderInput.name = 'sortOrder';
                sortOrderInput.value = sortOrder;
                form.appendChild(sortOrderInput);

                const searchClassnameInput = document.createElement('input');
                searchClassnameInput.type = 'hidden';
                searchClassnameInput.name = 'searchLesson';
                searchClassnameInput.value = searchLesson;
                form.appendChild(searchClassnameInput);

                const searchSubjectIdInput = document.createElement('input');
                searchSubjectIdInput.type = 'hidden';
                searchSubjectIdInput.name = 'subjectId';
                searchSubjectIdInput.value = searchSubjectId;
                form.appendChild(searchSubjectIdInput);

                const pageInput = document.createElement('input');
                pageInput.type = 'hidden';
                pageInput.name = 'page';
                pageInput.value = '1';
                form.appendChild(pageInput);

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
                    <h4 class="breadcrumb-title">Lesson List</h4>
                    <ul class="db-breadcrumb-list">
                        <li><a href="#"><i class="fa fa-home"></i> Home</a></li>
                        <li>Lesson List</li>
                    </ul>
                </div>
                <div class="row">
                    <!-- Your Profile Views Chart -->
                    <div class="col-lg-12 m-b30">
                        <div class="widget-box">
                            <div class="widget-inner">
                                <div class="row d-flex justify-content-between align-items-center">
                                    <form class="col-sm-7 d-flex" method="post" action="LessonController">
                                        <div class="col">
                                            <input class="form-control" type="text" name="searchLesson" placeholder="Search by Lesson name" value="${searchLesson}">
                                        </div>
                                        <div class="col">
                                            <select class="form-control" name="subjectId">
                                                <c:choose>
                                                    <c:when test="${not empty subjectList}">
                                                        <option value="" <c:if test="${searchSubjectId == null}">selected</c:if>>All Subjects</option>
                                                        <c:forEach var="subject" items="${subjectList}">
                                                            <option value="${subject.subject_id}" <c:if test="${subject.subject_id == searchSubjectId}">selected</c:if>>${subject.subject_name}</option>
                                                        </c:forEach>

                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="" selected>All Subjects</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </select>

                                        </div>
                                        <div class="col-auto">
                                            <input type="hidden" name="action" value="lessonList">
                                            <input type="hidden" name="page" value="${currentPage}">
                                            <input type="hidden" name="size" value="3">
                                            <button class="btn btn-primary btn-sm" type="submit">Search</button>
                                        </div>
                                    </form>
                                    <form class="col-sm-3 text-right" action="LessonController" method="post">
                                        <input type="hidden" name="action" value="addLessonPage">
                                        <button class="btn btn-primary btn-sm" type="submit"><i class="fa fa-fw fa-plus-circle"></i> Add Lesson</button>
                                    </form>
                                </div>
                            </div>
                            <div class="widget-inner">
                                <c:if test="${not empty lessons}">
                                    <table class="table table-bordered">

                                        <tr>
                                            <th><a href="#" onclick="sendPostRequestSort2('title', '${searchLesson}', '${searchSubjectId}', '${sortOrder == 'asc' ? 'desc' : 'asc'}'); return false;">Title</a>
                                                <c:choose>
                                                    <c:when test="${sortField == 'title'}">
                                                        <i class="bi bi-arrow-${sortOrder == 'asc' ? 'up' : 'down'}"></i>
                                                    </c:when>
                                                </c:choose>
                                            </th>
                                            <th><a href="#" onclick="sendPostRequestSort2('subject_id', '${searchLesson}', '${searchSubjectId}', '${sortOrder == 'asc' ? 'desc' : 'asc'}'); return false;">Subject</a>
                                                <c:choose>
                                                    <c:when test="${sortField == 'subject_id'}">
                                                        <i class="bi bi-arrow-${sortOrder == 'asc' ? 'up' : 'down'}"></i>
                                                    </c:when>
                                                </c:choose>
                                            </th>
                                            <th><a href="#" onclick="sendPostRequestSort2('status', '${searchLesson}', '${searchSubjectId}', '${sortOrder == 'asc' ? 'desc' : 'asc'}'); return false;">Status</a>
                                                <c:choose>
                                                    <c:when test="${sortField == 'status'}">
                                                        <i class="bi bi-arrow-${sortOrder == 'asc' ? 'up' : 'down'}"></i>
                                                    </c:when>
                                                </c:choose>
                                            </th>
                                            <th>Update</th>
                                            <!--<th>Detail</th>-->
                                        </tr>
                                        <c:forEach var="lessonList" items="${lessons}">
                                            <tr>
                                                <td>${lessonList.title}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${not empty subjectList}">
                                                            <c:forEach var="subjects" items="${subjectList}">
                                                                <c:if test="${subjects.subject_id == lessonList.subject_id}">
                                                                    ${subjects.subject_name}
                                                                </c:if>
                                                            </c:forEach>
                                                        </c:when>
                                                        <c:otherwise>
                                                            Subject information is unavailable.
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${lessonList.status}">
                                                            Active
                                                        </c:when>
                                                        <c:otherwise>
                                                            Inactive
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>
                                                    <form action="${pageContext.request.contextPath}/LessonController" method="post">
                                                        <input type="hidden" name="lessonId" value="${lessonList.lesson_id}" />
                                                        <input type="hidden" name="action" value="updateLessonPage">
                                                        <button class="btn btn-primary btn-sm" type="submit">Edit</button>
                                                    </form>
                                                </td>
                                                <!--                                                <td>
                                                                                                    <form action="${pageContext.request.contextPath}/LessonController" method="post">
                                                                                                        <input type="hidden" name="classId" value="${lessonList.lesson_id}" />
                                                                                                        <input type="hidden" name="action" value="LessonDetail">
                                                                                                        <input type="hidden" name="page" value="${currentPage}">
                                                                                                        <button class="btn btn-primary btn-sm" type="submit">Detail</button>
                                                                                                    </form>
                                                                                                </td>-->
                                            </tr>
                                        </c:forEach>
                                    </table>
                                    <!-- Pagination -->
                                    <div class="row mt-3">
                                        <nav aria-label="Page navigation">
                                            <ul class="pagination">
                                                <c:forEach begin="1" end="${totalPages}" var="i">
                                                    <li class="page-item <c:if test='${i == currentPage}'>active</c:if>">
                                                        <a class="page-link" href="LessonController?action=lessonList&page=${i}&size=3&searchLesson=${fn:escapeXml(searchLesson)}&subjectId=${searchSubjectId}&sortField=${sortField}&sortOrder=${sortOrder}">${i}</a>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </nav>
                                    </div>
                                </c:if>
                                <c:if test="${empty lessons}">
                                    <p>No classes found.</p>
                                </c:if>


                            </div>
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
