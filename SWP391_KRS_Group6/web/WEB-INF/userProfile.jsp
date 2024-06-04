<%-- 
    Document   : userProfile
    Created on : May 20, 2024, 2:41:19 AM
    Author     : simon
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <!-- Mirrored from educhamp.themetrades.com/demo/admin/user-profile.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:11:35 GMT -->
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
            function sendPostRequest(action) {
                // Tạo form ẩn
                var form = document.createElement("form");
                form.setAttribute("method", "post");
                form.setAttribute("action", "userController");

                // Tạo input ẩn cho action
                var hiddenField = document.createElement("input");
                hiddenField.setAttribute("type", "hidden");
                hiddenField.setAttribute("name", "action");
                hiddenField.setAttribute("value", action);

                // Thêm input ẩn vào form
                form.appendChild(hiddenField);

                // Thêm form vào body và submit
                document.body.appendChild(form);
                form.submit();
            }
            function showAlert(message) {
                alert(message);
            }
        </script>

    </head>
    <body class="ttr-opened-sidebar ttr-pinned-sidebar">
        <c:if test="${not empty successMessage}">
            <script>
                showAlert('${successMessage}');
            </script>
        </c:if>


        <!-- header start -->
        
                <jsp:include page="DashboardNav.jsp"/>
               
        <!-- Left sidebar menu end -->

        <!--Main container start -->
        <main class="ttr-wrapper">
            <div class="container-fluid">
                <div class="db-breadcrumb">
                    <h4 class="breadcrumb-title">User Profile</h4>
                    <ul class="db-breadcrumb-list">
                        <li><a href="#"><i class="fa fa-home"></i>Home</a></li>
                        <li>User Profile</li>
                    </ul>
                </div>	
                <div class="row">
                    <!-- Your Profile Views Chart -->
                    <div class="col-lg-12 m-b30">
                        <div class="widget-box">
                            <div class="wc-title">
                                <h4>User Profile</h4>
                            </div>
                            <div class="widget-inner">
                                <form class="edit-profile m-b30" action="${pageContext.request.contextPath}/userController" method="post">
                                    <input type="hidden" name="action" value="update"> 
                                    <input type="hidden" name="id" value="${user.user_id}">
                                    <div class="">
                                        <div class="form-group row">
                                            <div class="col-sm-10  ml-auto">
                                                <h3>1. Personal Details</h3>
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label class="col-sm-2 col-form-label">User Name</label>
                                            <div class="col-sm-7">
                                                <input class="form-control" name="userName" type="text" value="${user.user_name}">
                                            </div>
                                        </div>
                                        <!--                                        <div class="form-group row">
                                                                                    <label class="col-sm-2 col-form-label">Password</label>
                                                                                    <div class="col-sm-7">
                                                                                        <input class="form-control" name="password" type="password" value="${user.password}">
                                                                                    </div>
                                                                                </div>-->
                                        <div class="form-group row">
                                            <label class="col-sm-2 col-form-label">Email</label>
                                            <div class="col-sm-7">
                                                <input class="form-control" name="email" type="email" value="${user.email}">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-2 col-form-label">Full Name</label>
                                            <div class="col-sm-7">
                                                <input class="form-control" name="fullName" type="text" value="${user.full_name}">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-2 col-form-label">Phone</label>
                                            <div class="col-sm-7">
                                                <input class="form-control" name="phone" type="tel" value="${user.phone}">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-2 col-form-label">Gender</label>
                                            <div class="col-sm-4">
                                                <select class="form-control" name="gender">
                                                    <option value="Male" <c:if test="${user.gender == 'Male'}">selected</c:if>>Male</option>
                                                    <option value="Female" <c:if test="${user.gender == 'Female'}">selected</c:if>>Female</option>
                                                    <option value="Other" <c:if test="${user.gender == 'Other'}">selected</c:if>>Other</option>

                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-sm-2 col-form-label">Age</label>
                                                <div class="col-sm-7">
                                                    <input class="form-control" name="age" type="number" value="${user.age}">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-2 col-form-label">Status</label>
                                            <div class="col-sm-7">
                                                <input class="form-control"  name="status" type="checkbox" ${user.status ? 'checked' : ''}>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-2 col-form-label">Role</label>
                                            <div class="col-sm-2">
                                                <select class="form-control" name="roleId" id="roleSelect">
                                                    <c:forEach var="role" items="${roles}">
                                                        <option value="${role.settingId}" <c:if test="${role.settingId == user.role_id}">selected</c:if>>${role.settingName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="seperator"></div>

                                        <div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space-2x"></div>

                                    </div>
                                    <div class="">
                                        <div class="">
                                            <div class="row">
                                                <div class="col-sm-2">
                                                </div>
                                                <div class="col-sm-7">
                                                    <button type="submit" class="btn">Save changes</button>
                                                    <button type="button" onclick="sendPostRequest('userList'); return false;" class="btn-secondry">Back</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <form class="edit-profile" action="${pageContext.request.contextPath}/userController" method="post">
                                    <input type="hidden" name="action" value="changePassAdmin"> 
                                    <input type="hidden" name="id" value="${user.user_id}">
                                    <div class="">
                                        <div class="form-group row">
                                            <div class="col-sm-10 ml-auto">
                                                <h3>2. Password</h3>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-2 col-form-label">New Password</label>
                                            <div class="col-sm-7">
                                                <div class="input-group">
                                                    <input class="form-control" type="password" name="newPassword" required>
                                                    <span class="error" style="font-size: smaller;">${errors['passError']}</span>
                                                    <div class="input-group-append">
                                                        <button type="button" class="btn btn-outline-secondary toggle-password"><i class="fa fa-eye"></i></button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-2 col-form-label">Re-type New Password</label>
                                            <div class="col-sm-7">
                                                <div class="input-group">
                                                    <input class="form-control" type="password" name="reNewPassword" required>
                                                    <span class="error" style="font-size: smaller;">${errors['pass2Error']}</span>
                                                    <div class="input-group-append">
                                                        <button type="button" class="btn btn-outline-secondary toggle-password"><i class="fa fa-eye"></i></button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-2"></div>
                                        <div class="col-sm-7">
                                            <button type="submit" class="btn">Save changes</button>
                                            <button type="button" onclick="sendPostRequest('userList'); return false;" class="btn btn-secondary">Back</button>
                                        </div>
                                    </div>
                                </form>

<!--                                <script>
                                    document.querySelectorAll('.toggle-password').forEach(button => {
                                        button.addEventListener('click', () => {
                                            const input = button.closest('.input-group').querySelector('input');
                                            const icon = button.querySelector('i');
                                            if (input.type === 'password') {
                                                input.type = 'text';
                                                icon.classList.remove('fa-eye');
                                                icon.classList.add('fa-eye-slash');
                                            } else {
                                                input.type = 'password';
                                                icon.classList.remove('fa-eye-slash');
                                                icon.classList.add('fa-eye');
                                            }
                                        });
                                    });

                                    document.querySelector('form').addEventListener('submit', function (e) {
                                        const newPassword = document.querySelector('input[name="newPassword"]').value;
                                        const reNewPassword = document.querySelector('input[name="reNewPassword"]').value;

                                        if (newPassword !== reNewPassword) {
                                            e.preventDefault();
                                            alert('New passwords do not match.');
                                        }
                                    });
                                </script>-->

                            </div>
                        </div>
                    </div>
                    <!-- Your Profile Views Chart END-->
                </div>
            </div>
        </main>
        <div class="ttr-overlay"></div>

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
        <script src='admin/assets/vendors/scroll/scrollbar.min.js'></script>
        <script src="admin/assets/js/functions.js"></script>
        <script src="admin/assets/vendors/chart/chart.min.js"></script>
        <script src="admin/assets/js/admin.js"></script>
        <script src='admin/assets/vendors/switcher/switcher.js'></script>

    </body>

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/user-profile.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:11:35 GMT -->

</html>
