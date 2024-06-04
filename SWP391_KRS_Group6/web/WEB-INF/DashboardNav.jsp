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
        </script>
   <nav class="ttr-sidebar-navi">
                    <ul>
                        <li>
                            <a href="Home" class="ttr-material-button">
                                <span class="ttr-icon"><i class="ti-home"></i></span>
                                <span class="ttr-label">Dashborad</span>
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
                                <span class="ttr-icon"><i class="ti-comments"></i></span>
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

                                <li>
                                    <a href="user-profile.html" class="ttr-material-button"><span class="ttr-label">User Profile</span></a>
                                </li>
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
</html>
