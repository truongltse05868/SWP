<%-- 
    Document   : SideBar
    Created on : Jul 18, 2024, 7:26:55 PM
    Author     : HuyPC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
    <title>EduChamp : Education HTML Template </title>

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

</head>
<html>

    <div class="widget recent-posts-entry">
        <h6 class="widget-title">Recent Posts</h6>
        <c:if test="${not empty Recentposts}">
            <c:forEach var="Recentposts" items="${Recentposts}">
                <div class="widget-post-bx">
                    <div class="widget-post clearfix">
                        <div class="ttr-post-media"> <img src=${Recentposts.thumbnailUrl} width="200" height="143" alt=""> </div>
                        <div class="ttr-post-info">
                            <div class="ttr-post-header">
                                <form method="post" action="PostController">
                                    <input type="hidden" name="service" value="BlogDetail">
                                    <input type="hidden" name="pid" value="${Recentposts.postId}">
                                    <h6 class="post-title"><button type="submit" class="btn-link">${Recentposts.title}</button></h6>
                                </form>
                            </div>
                            <ul class="media-post">
                                <li><form method="post" action="PostController">
                                        <input type="hidden" name="service" value="BlogDetail">
                                        <input type="hidden" name="pid" value="${Recentposts.postId}">
                                        <button type="submit" class="btn-link">READ MORE</button>
                                    </form></li>
                                <!--                                <li><a href="#"><i class="fa fa-comments-o"></i>15 Comment</a></li>-->
                            </ul>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </div>


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
</html>
