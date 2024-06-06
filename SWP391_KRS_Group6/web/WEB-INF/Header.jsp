<%-- 
    Document   : Header
    Created on : Jun 4, 2024, 7:54:54 AM
    Author     : HuyPC
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="entity.User" %>

<!DOCTYPE html>
<html>
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
        function sendPostRequestForPass(action) {
            // Tạo form ẩn
            var form = document.createElement("form");
            form.setAttribute("method", "post");
            form.setAttribute("action", "ForgotPasswordController");

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

        function sendPostRequestProfile(action, userId) {
            // Tạo form ẩn
            var form = document.createElement("form");
            form.setAttribute("method", "post");
            form.setAttribute("action", "userController");

            // Tạo input ẩn cho action
            var hiddenActionField = document.createElement("input");
            hiddenActionField.setAttribute("type", "hidden");
            hiddenActionField.setAttribute("name", "action");
            hiddenActionField.setAttribute("value", action);

            // Tạo input ẩn cho userId
            var hiddenUserIdField = document.createElement("input");
            hiddenUserIdField.setAttribute("type", "hidden");
            hiddenUserIdField.setAttribute("name", "userId");
            hiddenUserIdField.setAttribute("value", userId);

            // Thêm các input ẩn vào form
            form.appendChild(hiddenActionField);
            form.appendChild(hiddenUserIdField);

            // Thêm form vào body và submit
            document.body.appendChild(form);
            form.submit();
        }
    </script>
    <header class="header rs-nav header-transparent">
        <div class="top-bar">
            <div class="container">
                <!--                <div class="row d-flex justify-content-between">
                                    <div class="topbar-left">
                                        <ul>
                                            <li><a href="faq-1.html"><i class="fa fa-question-circle"></i>Ask a Question</a></li>
                                            <li><a href="javascript:;"><i class="fa fa-envelope-o"></i>Support@website.com</a></li>
                                        </ul>
                                    </div>
                
                                    <div class="topbar-right">
                                        <ul>
                                            <li>
                                                <select class="header-lang-bx">
                                                    <option data-icon="flag flag-uk">English UK</option>
                                                    <option data-icon="flag flag-us">English US</option>
                                                </select>
                                            </li>
                <c:if test="${sessionScope['account'].getRole_id() == null}">
                    <li><a id="loginLink" href="Login" >Login</a></li>
                    <li><a id="registerLink" href="RegisterController">Register</a></li>
                    <li><a id="forgotpass" href="forgotpass" onclick="sendPostRequestForPass('forgotPassPage');
                            return false;">ForgotPassword</a></li>
                </c:if>
                <c:if test="${sessionScope['account'].getRole_id() != null}">
                <li> 
                    <label><a href="profile" onclick="sendPostRequestProfile('profileUserPage', ${sessionScope['account'].getUser_id()});
                            return false;">Hello, ${sessionScope.account.full_name} </a></label>
                </li>
                <li><a id="logoutLink" href="Logout" >Logout</a></li>
                </c:if>
        </ul>
    </div>

    <form id="hiddenForm" method="post" style="display:none;">
        <input type="hidden" name="action" value="Login">
    </form>
    <form id="logout" method="post" style="display:none;">
        <input type="hidden" name="action" value="Logout">
    </form>
</div>-->
                <!-- Navigation Menu ==== -->
                <div class="menu-links navbar-collapse collapse justify-content-start" id="menuDropdown">
                    <div class="menu-logo">
                        <a href="Home"><img src="assets/images/logo.png" alt=""></a>
                    </div>
                    <ul class="nav navbar-nav">	
                        <li class="active"><a href="javascript:;">Home <i class="fa fa-chevron-down"></i></a>
                            <ul class="sub-menu">
                                <li><a href="Home">Home</a></li>
                                <!--<li><a href="index-2.html">Home 2</a></li>-->
                            </ul>
                        </li>
                        <li><a href="javascript:;">Pages <i class="fa fa-chevron-down"></i></a>
                            <ul class="sub-menu">
                                <li><a href="javascript:;">About<i class="fa fa-angle-right"></i></a>
                                    <ul class="sub-menu">
                                        <li><a href="about-1.html">About 1</a></li>
                                        <li><a href="about-2.html">About 2</a></li>
                                    </ul>
                                </li>
                                <li><a href="javascript:;">Event<i class="fa fa-angle-right"></i></a>
                                    <ul class="sub-menu">
                                        <li><a href="event.html">Event</a></li>
                                        <li><a href="events-details.html">Events Details</a></li>
                                    </ul>
                                </li>
                                <li><a href="javascript:;">FAQ's<i class="fa fa-angle-right"></i></a>
                                    <ul class="sub-menu">
                                        <li><a href="faq-1.html">FAQ's 1</a></li>
                                        <li><a href="faq-2.html">FAQ's 2</a></li>
                                    </ul>
                                </li>
                                <li><a href="javascript:;">Contact Us<i class="fa fa-angle-right"></i></a>
                                    <ul class="sub-menu">
                                        <li><a href="contact-1.html">Contact Us 1</a></li>
                                        <li><a href="contact-2.html">Contact Us 2</a></li>
                                    </ul>
                                </li>
                                <li><a href="portfolio.html">Portfolio</a></li>
                                    <c:if test="${sessionScope['account'] != null}">
                                    <li><a href="profile" onclick="sendPostRequestProfile('profileUserPage', ${sessionScope['account'].getUser_id()});
                                            return false;">Profile</a></li>
                                    </c:if>

                                <li><a href="membership.html">Membership</a></li>
                                <li><a href="error-404.html">404 Page</a></li>
                            </ul>
                        </li>
                        <li class="add-mega-menu"><a href="javascript:;">Our Classes <i class="fa fa-chevron-down"></i></a>
                            <ul class="sub-menu add-menu">
                                <li class="add-menu-left">
                                    <h5 class="menu-adv-title">Our Classes</h5>
                                    <ul>
                                        <li><a href="ClassController">Classes </a></li>
                                        <li><a href="courses-details.html">Classes Details</a></li>
                                        <li><a href="profile.html">Instructor Profile</a></li>
                                        <li><a href="event.html">Upcoming Event</a></li>
                                        <li><a href="membership.html">Membership</a></li>
                                    </ul>
                                </li>
                                <li class="add-menu-right">
                                    <img src="assets/images/adv/adv.jpg" alt=""/>
                                </li>
                            </ul>
                        </li>
                        <li class="add-mega-menu"><a href="javascript:;">Our Courses <i class="fa fa-chevron-down"></i></a>
                            <ul class="sub-menu add-menu">
                                <li class="add-menu-left">
                                    <h5 class="menu-adv-title">Our Courses</h5>
                                    <ul>
                                        <li><a href="SubjectController">Courses </a></li>
                                        <li><a href="courses-details.html">Courses Details</a></li>
                                        <li><a href="profile.html">Instructor Profile</a></li>
                                        <li><a href="event.html">Upcoming Event</a></li>
                                        <li><a href="membership.html">Membership</a></li>
                                    </ul>
                                </li>
                                <li class="add-menu-right">
                                    <img src="assets/images/adv/adv.jpg" alt=""/>
                                </li>
                            </ul>
                        </li>
                        <li><a href="javascript:;">Blog <i class="fa fa-chevron-down"></i></a>
                            <ul class="sub-menu">
                                <li><a href="blog-classic-grid.html">Blog Classic</a></li>
                                <li><a href="blog-classic-sidebar.html">Blog Classic Sidebar</a></li>
                                <li><a href="blog-list-sidebar.html">Blog List Sidebar</a></li>
                                <li><a href="blog-standard-sidebar.html">Blog Standard Sidebar</a></li>
                                <li><a href="blog-details.html">Blog Details</a></li>
                            </ul>
                        </li>
                        <c:if test="${sessionScope['account'].getRole_id() == 1}">
                            <li class="nav-dashboard"><a href="javascript:;">Dashboard <i class="fa fa-chevron-down"></i></a>
                                <ul class="sub-menu">
                                    <li><a href="Dashboard">Dashboard</a></li>
                                    <li><a href="admin/add-listing.html">Add Listing</a></li>
                                    <li><a href="admin/bookmark.html">Bookmark</a></li>
                                    <li><a href="SettingController">Setting List</a></li>
                                    <li><a href="userList" onclick="sendPostRequest('userList'); return false;">User List</a></li>
                                    <li><a href="javascript:;">Calendar<i class="fa fa-angle-right"></i></a>
                                        <ul class="sub-menu">
                                            <li><a href="admin/basic-calendar.html">Basic Calendar</a></li>
                                            <li><a href="admin/list-view-calendar.html">List View Calendar</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="javascript:;">Mailbox<i class="fa fa-angle-right"></i></a>
                                        <ul class="sub-menu">
                                            <li><a href="admin/mailbox.html">Mailbox</a></li>
                                            <li><a href="admin/mailbox-compose.html">Compose</a></li>
                                            <li><a href="admin/mailbox-read.html">Mail Read</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                        </c:if>



                    </ul>

                    <div class="nav-social-link">
                        <a href="javascript:;"><i class="fa fa-facebook"></i></a>
                        <a href="javascript:;"><i class="fa fa-google-plus"></i></a>
                        <a href="javascript:;"><i class="fa fa-linkedin"></i></a>
                    </div>
                </div>
                <!-- Navigation Menu END ==== -->

            </div>
        </div>
        <div class="sticky-header navbar-expand-lg">
            <div class="menu-bar clearfix">
                <div class="container clearfix">
                    <!-- Header Logo ==== -->
                    <div class="menu-logo">
                        <a href="Home"><img src="assets/images/logo-white.png" alt=""></a>
                    </div>
                    <!-- Mobile Nav Button ==== -->
                    <button class="navbar-toggler collapsed menuicon justify-content-end" type="button" data-toggle="collapse" data-target="#menuDropdown" aria-controls="menuDropdown" aria-expanded="false" aria-label="Toggle navigation">
                        <span></span>
                        <span></span>
                        <span></span>
                    </button>
                    <!-- Author Nav ==== -->
                    <div class="secondary-menu">
                        <div class="secondary-inner">
                            <div class="row d-flex justify-content-between">

                                <div class="topbar-right">
                                    <ul>
                                        <c:if test="${sessionScope['account'].getRole_id() == null}">
                                            <li><a id="loginLink" href="Login" >Login</a></li>
                                            <li><a id="registerLink" href="RegisterController">Register</a></li>
                                            <li><a id="forgotpass" href="forgotpass" onclick="sendPostRequestForPass('forgotPassPage');
                                                    return false;">ForgotPassword</a></li>
                                            </c:if>
                                            <c:if test="${sessionScope['account'].getRole_id() != null}">
                                            <li> 
                                                <label><a href="profile" onclick="sendPostRequestProfile('profileUserPage', ${sessionScope['account'].getUser_id()});
                                                        return false;">Hello, ${sessionScope.account.full_name} </a></label>
                                            </li>
                                            <li><a id="logoutLink" href="Logout" >Logout</a></li>
                                            </c:if>
                                    </ul>
                                </div>

                                <form id="hiddenForm" method="post" style="display:none;">
                                    <input type="hidden" name="action" value="Login">
                                </form>
                                <form id="logout" method="post" style="display:none;">
                                    <input type="hidden" name="action" value="Logout">
                                </form>
                                <ul>
                                    <li><a href="javascript:;" class="btn-link"><i class="fa fa-facebook"></i></a></li>
                                    <li><a href="javascript:;" class="btn-link"><i class="fa fa-google-plus"></i></a></li>
                                    <li><a href="javascript:;" class="btn-link"><i class="fa fa-linkedin"></i></a></li>
                                    <!-- Search Button ==== -->
                                    <li class="search-btn"><button id="quik-search-btn" type="button" class="btn-link"><i class="fa fa-search"></i></button></li>
                                </ul>
                            </div>

                        </div>
                    </div>
                    <!-- Search Box ==== -->
                    <div class="nav-search-bar">
                        <form action="#">
                            <input name="search" value="" type="text" class="form-control" placeholder="Type to search">
                            <span><i class="ti-search"></i></span>
                        </form>
                        <span id="search-remove"><i class="ti-close"></i></span>
                    </div>
                    <!-- Navigation Menu ==== -->
                    <div class="menu-links navbar-collapse collapse justify-content-start" id="menuDropdown">
                        <div class="menu-logo">
                            <a href="Home"><img src="assets/images/logo.png" alt=""></a>
                        </div>
                        <ul class="nav navbar-nav">	
                            <li class="active"><a href="javascript:;">Home <i class="fa fa-chevron-down"></i></a>
                                <ul class="sub-menu">
                                    <li><a href="Home">Home</a></li>
                                    <!--<li><a href="index-2.html">Home 2</a></li>-->
                                </ul>
                            </li>
                            <li><a href="javascript:;">Pages <i class="fa fa-chevron-down"></i></a>
                                <ul class="sub-menu">
                                    <li><a href="javascript:;">About<i class="fa fa-angle-right"></i></a>
                                        <ul class="sub-menu">
                                            <li><a href="about-1.html">About 1</a></li>
                                            <li><a href="about-2.html">About 2</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="javascript:;">Event<i class="fa fa-angle-right"></i></a>
                                        <ul class="sub-menu">
                                            <li><a href="event.html">Event</a></li>
                                            <li><a href="events-details.html">Events Details</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="javascript:;">FAQ's<i class="fa fa-angle-right"></i></a>
                                        <ul class="sub-menu">
                                            <li><a href="faq-1.html">FAQ's 1</a></li>
                                            <li><a href="faq-2.html">FAQ's 2</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="javascript:;">Contact Us<i class="fa fa-angle-right"></i></a>
                                        <ul class="sub-menu">
                                            <li><a href="contact-1.html">Contact Us 1</a></li>
                                            <li><a href="contact-2.html">Contact Us 2</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="portfolio.html">Portfolio</a></li>
                                        <c:if test="${sessionScope['account'] != null}">
                                        <li><a href="profile" onclick="sendPostRequestProfile('profileUserPage', ${sessionScope['account'].getUser_id()});
                                                return false;">Profile</a></li>
                                        </c:if>

                                    <li><a href="membership.html">Membership</a></li>
                                    <li><a href="error-404.html">404 Page</a></li>
                                </ul>
                            </li>
                            <li class="add-mega-menu"><a href="javascript:;">Our Classes <i class="fa fa-chevron-down"></i></a>
                                <ul class="sub-menu add-menu">
                                    <li class="add-menu-left">
                                        <h5 class="menu-adv-title">Our Classes</h5>
                                        <ul>
                                            <li><a href="ClassController">Classes </a></li>
                                            <li><a href="courses-details.html">Classes Details</a></li>
                                            <li><a href="profile.html">Instructor Profile</a></li>
                                            <li><a href="event.html">Upcoming Event</a></li>
                                            <li><a href="membership.html">Membership</a></li>
                                        </ul>
                                    </li>
                                    <li class="add-menu-right">
                                        <img src="assets/images/adv/adv.jpg" alt=""/>
                                    </li>
                                </ul>
                            </li>
                            <li class="add-mega-menu"><a href="javascript:;">Our Courses <i class="fa fa-chevron-down"></i></a>
                                <ul class="sub-menu add-menu">
                                    <li class="add-menu-left">
                                        <h5 class="menu-adv-title">Our Courses</h5>
                                        <ul>
                                            <li><a href="SubjectController">Courses </a></li>
                                            <li><a href="courses-details.html">Courses Details</a></li>
                                            <li><a href="profile.html">Instructor Profile</a></li>
                                            <li><a href="event.html">Upcoming Event</a></li>
                                            <li><a href="membership.html">Membership</a></li>
                                        </ul>
                                    </li>
                                    <li class="add-menu-right">
                                        <img src="assets/images/adv/adv.jpg" alt=""/>
                                    </li>
                                </ul>
                            </li>
                            <li><a href="javascript:;">Blog <i class="fa fa-chevron-down"></i></a>
                                <ul class="sub-menu">
                                    <li><a href="blog-classic-grid.html">Blog Classic</a></li>
                                    <li><a href="blog-classic-sidebar.html">Blog Classic Sidebar</a></li>
                                    <li><a href="blog-list-sidebar.html">Blog List Sidebar</a></li>
                                    <li><a href="blog-standard-sidebar.html">Blog Standard Sidebar</a></li>
                                    <li><a href="blog-details.html">Blog Details</a></li>
                                </ul>
                            </li>
                            <c:if test="${sessionScope['account'].getRole_id() == 1}">
                                <li class="nav-dashboard"><a href="javascript:;">Dashboard <i class="fa fa-chevron-down"></i></a>
                                    <ul class="sub-menu">
                                        <li><a href="Dashboard">Dashboard</a></li>
                                        <li><a href="admin/add-listing.html">Add Listing</a></li>
                                        <li><a href="admin/bookmark.html">Bookmark</a></li>
                                        <li><a href="SettingController">Setting List</a></li>
                                        <li><a href="userList" onclick="sendPostRequest('userList'); return false;">User List</a></li>
                                        <li><a href="javascript:;">Calendar<i class="fa fa-angle-right"></i></a>
                                            <ul class="sub-menu">
                                                <li><a href="admin/basic-calendar.html">Basic Calendar</a></li>
                                                <li><a href="admin/list-view-calendar.html">List View Calendar</a></li>
                                            </ul>
                                        </li>
                                        <li><a href="javascript:;">Mailbox<i class="fa fa-angle-right"></i></a>
                                            <ul class="sub-menu">
                                                <li><a href="admin/mailbox.html">Mailbox</a></li>
                                                <li><a href="admin/mailbox-compose.html">Compose</a></li>
                                                <li><a href="admin/mailbox-read.html">Mail Read</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </li>
                            </c:if>
                        </ul>
                        <div class="nav-social-link">
                            <a href="javascript:;"><i class="fa fa-facebook"></i></a>
                            <a href="javascript:;"><i class="fa fa-google-plus"></i></a>
                            <a href="javascript:;"><i class="fa fa-linkedin"></i></a>
                        </div>
                    </div>
                    <!-- Navigation Menu END ==== -->
                </div>
            </div>
        </div>
    </header>
</html>
