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

        function sendPostRequestForBlog(action) {
            // Tạo form ẩn
            var form = document.createElement("form");
            form.setAttribute("method", "post");
            form.setAttribute("action", "PostController");

            // Tạo input ẩn cho action
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", "service");
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
            <div class="container-fluid">
                <!-- Navigation Menu ==== -->
                <div class="menu-links navbar-collapse collapse justify-content-start" id="menuDropdown">
                    <div class="menu-logo">
                        <a href="Home"><img src="assets/images/logo.png" alt=""></a>
                    </div>
                    <ul class="nav navbar-nav">	
                        <li class="active"><a href="Home">Home</a>
                        </li>
                        <li><a href="javascript:;">Pages <i class="fa fa-chevron-down"></i></a>
                            <ul class="sub-menu">
                                <li><a href="ContactController">Contact Us</a>
                                </li>
                                <li><a href="portfolio.html">Portfolio</a></li>
                                    <c:if test="${sessionScope['account'] != null}">
                                    <li><a href="profile" onclick="sendPostRequestProfile('profileUserPage', ${sessionScope['account'].getUser_id()});
                                            return false;">Profile</a></li>
                                    </c:if>
                            </ul>
                        </li>
                        <li class="add-mega-menu"><a href="ClassController">Our Classes </a>

                        </li>
                        <li class="add-mega-menu"><a href="SubjectController">Our Courses </a>
                        </li>
                        <li><a href="PostController" onclick="sendPostRequestForBlog('BlogList'); return false;">Blog </a>
                        </li>
                        <c:if test="${sessionScope['account'].getRole_id() == 1}">
                            <li class="nav-dashboard"><a href="Dashboard">Dashboard <i class="fa fa-chevron-down"></i></a>
                                <ul class="sub-menu">
                                    <li><a href="Dashboard">Dashboard</a></li>
                                    <li><a href="SettingController">Setting List</a></li>
                                    <li><a href="PostController">Post List</a></li>
                                    <li><a href="userList" onclick="sendPostRequest('userList'); return false;">User List</a></li>
                                </ul>
                            </li>
                        </c:if>

                    </ul>
                    <div class="nav-social-link">
                        <a href="javascript:;"><i class="fa fa-facebook"></i></a>
                        <a href="javascript:;"><i class="fa fa-google-plus"></i></a>
                        <a href="javascript:;"><i class="fa fa-linkedin"></i></a>
                    </div>
                    <ul>
                        <c:if test="${sessionScope['account'].getRole_id() == null}">
                            <li><a id="loginLink" href="Login" >Login</a></li>
                            <li><a id="registerLink" href="RegisterController">Register</a></li>
                            <!--                            <li><a id="forgotpass" href="forgotpass" onclick="sendPostRequestForPass('forgotPassPage'); 
                                                                return false;">ForgotPassword</a></li>-->
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
                <!-- Navigation Menu END ==== -->

            </div>
        </div>
        <div class="sticky-header navbar-expand-lg">
            <div class="menu-bar clearfix">
                <div class="container-fluid clearfix">
                    <!-- Header Logo ==== -->
                    <div class="mx-5 menu-logo">
                        <a href="Home"><img src="assets/images/logo-white.png" alt=""></a>
                    </div>
                    <!-- Mobile Nav Button ==== -->
                    <button class="navbar-toggler collapsed menuicon justify-content-end" type="button" data-toggle="collapse" data-target="#menuDropdown" aria-controls="menuDropdown" aria-expanded="false" aria-label="Toggle navigation">
                        <span></span>
                        <span></span>
                        <span></span>
                    </button>


                    <!-- Search Box ==== -->
                    <div class="nav-search-bar">
                        <form action="#">
                            <input name="search" value="" type="text" class="form-control" placeholder="Type to search">
                            <span><i class="ti-search"></i></span>
                        </form>
                        <span id="search-remove"><i class="ti-close"></i></span>
                    </div>
                    <!-- Navigation Menu ==== -->
                    <div class="mx-auto menu-links navbar-collapse collapse justify-content-center d-flex" id="menuDropdown">
                        <div class="mx-5 menu-logo">
                            <a href="Home"><img src="assets/images/logo.png" alt=""></a>
                        </div>
                        <ul class="nav navbar-nav">	
                            <li class="active"><a href="Home">Home</a>
                            </li>
                            <li><a href="javascript:;">Pages <i class="fa fa-chevron-down"></i></a>
                                <ul class="sub-menu">
                                    
                                    <li><a href="ContactController">Contact Us</a>
                                    <li><a href="portfolio.html">Portfolio</a></li>
                                        <c:if test="${sessionScope['account'] != null}">
                                        <li><a href="profile" onclick="sendPostRequestProfile('profileUserPage', ${sessionScope['account'].getUser_id()});
                                                return false;">Profile</a></li>
                                        </c:if>

                                 
                                </ul>
                            </li>
                            <li class="add-mega-menu"><a href="ClassController">Our Classes </a>
                                
                            </li>
                            <li class="add-mega-menu"><a href="SubjectController">Our Courses </a>
                               
                            </li>
                           <li><a href="PostController" onclick="sendPostRequestForBlog('BlogList'); return false;">Blog </a>
                                
                            </li>
                            <c:if test="${sessionScope['account'].getRole_id() == 1}">
                                <li class="nav-dashboard"><a href="Dashboard">Dashboard <i class="fa fa-chevron-down"></i></a>
                                    <ul class="sub-menu">
                                        <li><a href="Dashboard">Dashboard</a></li>
                                        <li><a href="SettingController">Setting List</a></li>
                                        <li><a href="PostController">Post List</a></li>
                                        <li><a href="userList" onclick="sendPostRequest('userList'); return false;">User List</a></li>
                                                                            </ul>
                                </li>
                            </c:if>
                        </ul>
                        <div class=" secondary-menu">
                            <div class="secondary-inner">
                                <div class="row d-flex justify-content-between">

                                    <div class="topbar-right">
                                        <ul>
                                            <c:if test="${sessionScope['account'].getRole_id() == null}">
                                                <li><a id="loginLink" href="Login" >Login</a></li>
                                                <li><a id="registerLink" href="RegisterController">Register</a></li>
                                                <!--                                                <li><a id="forgotpass" href="forgotpass" onclick="sendPostRequestForPass('forgotPassPage');
                                                                                                        return false;">ForgotPassword</a></li>-->
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
                    </div>
                    <!-- Navigation Menu END ==== -->
                </div>
            </div>
        </div>
    </header>
</html>
