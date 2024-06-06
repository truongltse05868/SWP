<%-- 
    Document   : DashboardNav
    Created on : Jun 4, 2024, 8:35:54 AM
    Author     : HuyPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <!-- header start -->
    <header class="ttr-header">
        <div class="ttr-header-wrapper">
            <!-- sidebar menu toggler start -->
            <div class="ttr-toggle-sidebar ttr-material-button">
                <i class="ti-close ttr-open-icon"></i>
                <i class="ti-menu ttr-close-icon"></i>
            </div>
            <!-- sidebar menu toggler end -->
            <!-- logo start -->
            <div class="ttr-logo-box">
                <div>
                    <a href="Home" class="ttr-logo">
                        <img alt="" class="ttr-logo-mobile" src="admin/assets/images/logo-mobile.png" width="30" height="30">
                        <img alt="" class="ttr-logo-desktop" src="admin/assets/images/logo-white.png" width="160" height="27">
                    </a>
                </div>
            </div>
            <!-- logo end -->
            <div class="ttr-header-menu">
                <!-- header left menu start -->
                <ul class="ttr-header-navigation">
                    <li>
                        <a href="Home" class="ttr-material-button ttr-submenu-toggle">HOME</a>
                    </li>
                    <li>
                        <a href="#" class="ttr-material-button ttr-submenu-toggle">QUICK MENU <i class="fa fa-angle-down"></i></a>
                        <div class="ttr-header-submenu">
                            <ul>
                                <li><a href="../courses.html">Our Courses</a></li>
                                <li><a href="../event.html">New Event</a></li>
                                <li><a href="../membership.html">Membership</a></li>
                            </ul>
                        </div>
                    </li>
                </ul>
                <!-- header left menu end -->
            </div>
            <div class="ttr-header-right ttr-with-seperator">
                <!-- header right menu start -->
                <ul class="ttr-header-navigation">
                    <li>
                        <a href="#" class="ttr-material-button ttr-search-toggle"><i class="fa fa-search"></i></a>
                    </li>
                    <li>
                        <a href="#" class="ttr-material-button ttr-submenu-toggle"><i class="fa fa-bell"></i></a>
                        <div class="ttr-header-submenu noti-menu">
                            <div class="ttr-notify-header">
                                <span class="ttr-notify-text-top">9 New</span>
                                <span class="ttr-notify-text">User Notifications</span>
                            </div>
                            <div class="noti-box-list">
                                <ul>
                                    <li>
                                        <span class="notification-icon dashbg-gray">
                                            <i class="fa fa-check"></i>
                                        </span>
                                        <span class="notification-text">
                                            <span>Sneha Jogi</span> sent you a message.
                                        </span>
                                        <span class="notification-time">
                                            <a href="#" class="fa fa-close"></a>
                                            <span> 02:14</span>
                                        </span>
                                    </li>
                                    <li>
                                        <span class="notification-icon dashbg-yellow">
                                            <i class="fa fa-shopping-cart"></i>
                                        </span>
                                        <span class="notification-text">
                                            <a href="#">Your order is placed</a> sent you a message.
                                        </span>
                                        <span class="notification-time">
                                            <a href="#" class="fa fa-close"></a>
                                            <span> 7 Min</span>
                                        </span>
                                    </li>
                                    <li>
                                        <span class="notification-icon dashbg-red">
                                            <i class="fa fa-bullhorn"></i>
                                        </span>
                                        <span class="notification-text">
                                            <span>Your item is shipped</span> sent you a message.
                                        </span>
                                        <span class="notification-time">
                                            <a href="#" class="fa fa-close"></a>
                                            <span> 2 May</span>
                                        </span>
                                    </li>
                                    <li>
                                        <span class="notification-icon dashbg-green">
                                            <i class="fa fa-comments-o"></i>
                                        </span>
                                        <span class="notification-text">
                                            <a href="#">Sneha Jogi</a> sent you a message.
                                        </span>
                                        <span class="notification-time">
                                            <a href="#" class="fa fa-close"></a>
                                            <span> 14 July</span>
                                        </span>
                                    </li>
                                    <li>
                                        <span class="notification-icon dashbg-primary">
                                            <i class="fa fa-file-word-o"></i>
                                        </span>
                                        <span class="notification-text">
                                            <span>Sneha Jogi</span> sent you a message.
                                        </span>
                                        <span class="notification-time">
                                            <a href="#" class="fa fa-close"></a>
                                            <span> 15 Min</span>
                                        </span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </li>
                    <li>
                        <a href="#" class="ttr-material-button ttr-submenu-toggle"><span class="ttr-user-avatar"><img alt="" src="admin/assets/images/testimonials/pic3.jpg" width="32" height="32"></span></a>
                        <div class="ttr-header-submenu">
                            <ul>
                                <!--<li><a href="user-profile.html">My profile</a></li>-->
                                <c:if test="${sessionScope['account'] != null}">
                                    <li><a href="profile" onclick="sendPostRequestProfile('profileUserPage', ${sessionScope['account'].getUser_id()});
                                            return false;">My profile</a></li>
                                </c:if>
                                <li><a href="list-view-calendar.html">Activity</a></li>
                                <li><a href="mailbox.html">Messages</a></li>
                                <li><a href="Logout">Logout</a></li>
                            </ul>
                        </div>
                    </li>
                    <li class="ttr-hide-on-mobile">
                        <a href="#" class="ttr-material-button"><i class="ti-layout-grid3-alt"></i></a>
                        <div class="ttr-header-submenu ttr-extra-menu">
                            <a href="#">
                                <i class="fa fa-music"></i>
                                <span>Musics</span>
                            </a>
                            <a href="#">
                                <i class="fa fa-youtube-play"></i>
                                <span>Videos</span>
                            </a>
                            <a href="#">
                                <i class="fa fa-envelope"></i>
                                <span>Emails</span>
                            </a>
                            <a href="#">
                                <i class="fa fa-book"></i>
                                <span>Reports</span>
                            </a>
                            <a href="#">
                                <i class="fa fa-smile-o"></i>
                                <span>Users</span>
                            </a>
                            <a href="#">
                                <i class="fa fa-picture-o"></i>
                                <span>Pictures</span>
                            </a>
                        </div>
                    </li>
                </ul>
                <!-- header right menu end -->
            </div>
            <!--header search panel start -->
            <div class="ttr-search-bar">
                <form class="ttr-search-form">
                    <div class="ttr-search-input-wrapper">
                        <input type="text" name="qq" placeholder="search something..." class="ttr-search-input">
                        <button type="submit" name="search" class="ttr-search-submit"><i class="ti-arrow-right"></i></button>
                    </div>
                    <span class="ttr-search-close ttr-search-toggle"><i class="ti-close"></i></span>
                </form>
            </div>
            <!--header search panel end -->
        </div>
    </header>
</div>
<!-- side menu logo end -->
<!-- sidebar menu start -->
<!-- header end -->

<!-- Left sidebar menu start -->
<div class="ttr-sidebar">
    <div class="ttr-sidebar-wrapper content-scroll">
        <!-- side menu logo start -->
        <div class="ttr-sidebar-logo">
            <a href="#"><img alt="" src="admin/assets/images/logo.png" width="122" height="27"></a>
            <!-- <div class="ttr-sidebar-pin-button" title="Pin/Unpin Menu">
                <i class="material-icons ttr-fixed-icon">gps_fixed</i>
                <i class="material-icons ttr-not-fixed-icon">gps_not_fixed</i>
            </div> -->
            <div class="ttr-sidebar-toggle-button">
                <i class="ti-arrow-left"></i>
            </div>
        </div>
        <!-- side menu logo end -->
        <!-- sidebar menu start -->
        <nav class="ttr-sidebar-navi">
            <ul>
                <li>
                    <a href="Dashboard" class="ttr-material-button">
                        <span class="ttr-icon"><i class="ti-home"></i></span>
                        <span class="ttr-label">Dashboard</span>
                    </a>
                </li>
                <li>
                    <a href="courses.html" class="ttr-material-button">
                        <span class="ttr-icon"><i class="ti-book"></i></span>
                        <span class="ttr-label">Courses</span>
                    </a>
                </li>
                <li>
                    <a href="event.html" class="ttr-material-button">
                        <span class="ttr-icon"><i class="ti-calendar"></i></span>
                        <span class="ttr-label">Event</span>
                    </a>
                </li>
                <li>
                    <a href="list-view-calendar.html" class="ttr-material-button">
                        <span class="ttr-icon"><i class="ti-layout-accordion-list"></i></span>
                        <span class="ttr-label">Calendar</span>
                    </a>
                </li>
                <li>
                    <a href="app-profile.html" class="ttr-material-button">
                        <span class="ttr-icon"><i class="ti-user"></i></span>
                        <span class="ttr-label">Profile</span>
                    </a>
                </li>
                <li>
                    <a href="mailbox.html" class="ttr-material-button">
                        <span class="ttr-icon"><i class="ti-email"></i></span>
                        <span class="ttr-label">Inbox</span>
                    </a>
                </li>
                <li>
                    <a href="SettingController" class="ttr-material-button">
                        <span class="ttr-icon"><i class="ti-settings"></i></span>
                        <span class="ttr-label">Setting</span>
                    </a>
                </li>
                <li>
                    <a href="user.html" class="ttr-material-button">
                        <span class="ttr-icon"><i class="ti-user"></i></span>
                        <span class="ttr-label">User</span>
                        <span class="ttr-arrow-icon"><i class="fa fa-angle-right"></i></span>
                    </a>
                    <ul>
                        <!--                                <li>
                                                            <a href="addUserController" class="ttr-material-button">
                                                                <span class="ttr-label">Add User</span>
                                                            </a>
                                                        </li>-->
                        <li>
                            <a href="UserList" class="ttr-material-button" onclick="sendPostRequest('userList'); return false;">
                                <span class="ttr-label">User List
                                </span>
                            </a>
                        </li>
                        <li>
                            <a href="AddUser" class="ttr-material-button" onclick="sendPostRequest('addUserPage'); return false;">
                                <span class="ttr-label">Add User
                                </span>
                            </a>
                        </li>

<!--                        <li>
                            <a href="user-profile.html" class="ttr-material-button"><span class="ttr-label">User Profile</span></a>
                        </li>-->
                    </ul>
                </li>
                <li>
                    <a href="../login.html" class="ttr-material-button">
                        <span class="ttr-icon"><i class="ti-lock"></i></span>
                        <span class="ttr-label">Logout</span>
                    </a>
                </li>
            </ul>
            <!-- sidebar menu end -->
        </nav>
        <!-- sidebar menu end -->
    </div>
</div>
</html>
