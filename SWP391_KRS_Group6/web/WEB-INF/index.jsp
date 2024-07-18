<%-- 
    Document   : index
    Created on : May 25, 2024, 12:33:34 AM
    Author     : HuyPC
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="entity.User" %>

<!DOCTYPE html>

<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>

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

    <!-- REVOLUTION SLIDER CSS ============================================= -->
    <link rel="stylesheet" type="text/css" href="assets/vendors/revolution/css/layers.css">
    <link rel="stylesheet" type="text/css" href="assets/vendors/revolution/css/settings.css">
    <link rel="stylesheet" type="text/css" href="assets/vendors/revolution/css/navigation.css">
    <!-- REVOLUTION SLIDER END -->
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
            form.setAttribute("service", "BlogList");

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

</head>
<body id="bg">
    <div class="page-wraper">
        <div id="loading-icon-bx"></div>
        <!-- Header Top ==== -->
        <jsp:include page="Header.jsp"/>
        <!-- Header Top END ==== -->
        <!-- Content -->
        <div class="page-content bg-white">
            <!-- Main Slider -->
            <div class="rev-slider">
                <div id="rev_slider_486_1_wrapper" class="rev_slider_wrapper fullwidthbanner-container" data-alias="news-gallery36" data-source="gallery" style="margin:0px auto;background-color:#ffffff;padding:0px;margin-top:0px;margin-bottom:0px;">
                    <!-- START REVOLUTION SLIDER 5.3.0.2 fullwidth mode -->
                    <div id="rev_slider_486_1" class="rev_slider fullwidthabanner" style="display:none;" data-version="5.3.0.2">
                        <ul>	<!-- SLIDE  -->
                            <li data-index="rs-100" 
                                data-transition="parallaxvertical" 
                                data-slotamount="default" 
                                data-hideafterloop="0" 
                                data-hideslideonmobile="off" 
                                data-easein="default" 
                                data-easeout="default" 
                                data-masterspeed="default" 
                                data-thumb="error-404.html" 
                                data-rotate="0" 
                                data-fstransition="fade" 
                                data-fsmasterspeed="1500" 
                                data-fsslotamount="7" 
                                data-saveperformance="off" 
                                data-title="A STUDY ON HAPPINESS" 
                                data-param1="" data-param2="" 
                                data-param3="" data-param4="" 
                                data-param5="" data-param6="" 
                                data-param7="" data-param8="" 
                                data-param9="" data-param10="" 
                                data-description="Science says that Women are generally happier">
                                <!-- MAIN IMAGE -->
                                <img src="assets/images/slider/slide1.jpg" alt="" 
                                     data-bgposition="center center" 
                                     data-bgfit="cover" 
                                     data-bgrepeat="no-repeat" 
                                     data-bgparallax="10" 
                                     class="rev-slidebg" 
                                     data-no-retina />

                                <!-- LAYER NR. 1 -->
                                <div class="tp-caption tp-shape tp-shapewrapper " 
                                     id="slide-100-layer-1" 
                                     data-x="['center','center','center','center']" data-hoffset="['0','0','0','0']" 
                                     data-y="['middle','middle','middle','middle']" data-voffset="['0','0','0','0']" 
                                     data-width="full"
                                     data-height="full"
                                     data-whitespace="nowrap"
                                     data-type="shape" 
                                     data-basealign="slide" 
                                     data-responsive_offset="off" 
                                     data-responsive="off"
                                     data-frames='[{"from":"opacity:0;","speed":1,"to":"o:1;","delay":0,"ease":"Power4.easeOut"},{"delay":"wait","speed":1,"to":"opacity:0;","ease":"Power4.easeOut"}]'
                                     data-textAlign="['left','left','left','left']"
                                     data-paddingtop="[0,0,0,0]"
                                     data-paddingright="[0,0,0,0]"
                                     data-paddingbottom="[0,0,0,0]"
                                     data-paddingleft="[0,0,0,0]"
                                     style="z-index: 5;background-color:rgba(2, 0, 11, 0.80);border-color:rgba(0, 0, 0, 0);border-width:0px;"> </div>	
                                <!-- LAYER NR. 2 -->
                                <div class="tp-caption Newspaper-Title   tp-resizeme" 
                                     id="slide-100-layer-2" 
                                     data-x="['center','center','center','center']" 
                                     data-hoffset="['0','0','0','0']" 
                                     data-y="['top','top','top','top']" 
                                     data-voffset="['250','250','250','240']" 
                                     data-fontsize="['50','50','50','30']"
                                     data-lineheight="['55','55','55','35']"
                                     data-width="full"
                                     data-height="none"
                                     data-whitespace="normal"
                                     data-type="text" 
                                     data-responsive_offset="on" 
                                     data-frames='[{"from":"y:[-100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;","mask":"x:0px;y:0px;s:inherit;e:inherit;","speed":1500,"to":"o:1;","delay":1000,"ease":"Power3.easeInOut"},{"delay":"wait","speed":1000,"to":"auto:auto;","mask":"x:0;y:0;s:inherit;e:inherit;","ease":"Power3.easeInOut"}]'
                                     data-textAlign="['center','center','center','center']"
                                     data-paddingtop="[0,0,0,0]"
                                     data-paddingright="[0,0,0,0]"
                                     data-paddingbottom="[10,10,10,10]"
                                     data-paddingleft="[0,0,0,0]"
                                     style="z-index: 6; font-family:rubik; font-weight:700; text-align:center; white-space: normal;">
                                    Welcome To University
                                </div>

                                <!-- LAYER NR. 3 -->
                                <div class="tp-caption Newspaper-Subtitle   tp-resizeme" 
                                     id="slide-100-layer-3" 
                                     data-x="['center','center','center','center']" 
                                     data-hoffset="['0','0','0','0']" 
                                     data-y="['top','top','top','top']" 
                                     data-voffset="['210','210','210','210']" 
                                     data-width="none"
                                     data-height="none"
                                     data-whitespace="nowrap"
                                     data-type="text" 
                                     data-responsive_offset="on"
                                     data-frames='[{"from":"y:[-100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;","mask":"x:0px;y:0px;s:inherit;e:inherit;","speed":1500,"to":"o:1;","delay":1000,"ease":"Power3.easeInOut"},{"delay":"wait","speed":1000,"to":"auto:auto;","mask":"x:0;y:0;s:inherit;e:inherit;","ease":"Power3.easeInOut"}]'
                                     data-textAlign="['left','left','left','left']"
                                     data-paddingtop="[0,0,0,0]"
                                     data-paddingright="[0,0,0,0]"
                                     data-paddingbottom="[0,0,0,0]"
                                     data-paddingleft="[0,0,0,0]"
                                     style="z-index: 7; white-space: nowrap; color:#fff; font-family:rubik; font-size:18px; font-weight:400;">
                                    Batter Education For A Better 
                                </div>

                                <!-- LAYER NR. 3 -->
                                <div class="tp-caption Newspaper-Subtitle   tp-resizeme" 
                                     id="slide-100-layer-4" 
                                     data-x="['center','center','center','center']" 
                                     data-hoffset="['0','0','0','0']" 
                                     data-y="['top','top','top','top']" 
                                     data-voffset="['320','320','320','290']" 
                                     data-width="['800','800','700','420']"
                                     data-height="['100','100','100','120']"
                                     data-whitespace="unset"
                                     data-type="text" 
                                     data-responsive_offset="on"
                                     data-frames='[{"from":"y:[-100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;","mask":"x:0px;y:0px;s:inherit;e:inherit;","speed":1500,"to":"o:1;","delay":1000,"ease":"Power3.easeInOut"},{"delay":"wait","speed":1000,"to":"auto:auto;","mask":"x:0;y:0;s:inherit;e:inherit;","ease":"Power3.easeInOut"}]'
                                     data-textAlign="['center','center','center','center']"
                                     data-paddingtop="[0,0,0,0]"
                                     data-paddingright="[0,0,0,0]"
                                     data-paddingbottom="[0,0,0,0]"
                                     data-paddingleft="[0,0,0,0]"
                                     style="z-index: 7; text-transform:capitalize; white-space: unset; color:#fff; font-family:rubik; font-size:18px; line-height:28px; font-weight:400;">
                                    an online education platform offering interactive courses, tutorials, and tools for students and educators, enhancing learning and teaching experiences for academic success
                                </div>
                                <!-- LAYER NR. 4 -->
                                <form method="post" action="PostController">
                                    <input type="hidden" name="service" value="BlogDetail">
                                    <input type="hidden" name="pid" value="1">
                                    <div class="tp-caption Newspaper-Button rev-btn " 
                                       id="slide-200-layer-5" 
                                       data-x="['center','center','center','center']" 
                                       data-hoffset="['90','80','75','90']" 
                                       data-y="['top','top','top','top']" 
                                       data-voffset="['400','400','400','420']" 
                                       data-width="none"
                                       data-height="none"
                                       data-whitespace="nowrap"
                                       data-type="button" 
                                       data-responsive_offset="on" 
                                       data-responsive="off"
                                       data-frames='[{"from":"y:[-100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;","mask":"x:0px;y:0px;","speed":1500,"to":"o:1;","delay":1000,"ease":"Power3.easeInOut"},{"delay":"wait","speed":1000,"to":"auto:auto;","mask":"x:0;y:0;","ease":"Power3.easeInOut"},{"frame":"hover","speed":"300","ease":"Power1.easeInOut","to":"o:1;rX:0;rY:0;rZ:0;z:0;","style":"c:rgba(0, 0, 0, 1.00);bg:rgba(255, 255, 255, 1.00);bc:rgba(255, 255, 255, 1.00);bw:1px 1px 1px 1px;"}]'
                                       data-textAlign="['center','center','center','center']"
                                       data-paddingtop="[12,12,12,12]"
                                       data-paddingright="[30,35,35,15]"
                                       data-paddingbottom="[12,12,12,12]"
                                       data-paddingleft="[30,35,35,15]"
                                       style="z-index: 8; white-space: nowrap; outline:none;box-shadow:none;box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;cursor:pointer; background-color:var(--primary) !important; border:0; border-radius:30px; margin-right:5px;">
                                        <button type="submit" class="btn-link" style="background: none; border: none; color: inherit; font: inherit; cursor: pointer;">READ MORE</button>
                                    </div>
                                </form>
                                <a class="tp-caption Newspaper-Button rev-btn" 
                                   id="slide-200-layer-6" 
                                   data-x="['center','center','center','center']" 
                                   data-hoffset="['-90','-80','-75','-90']" 
                                   data-y="['top','top','top','top']" 
                                   data-voffset="['400','400','400','420']" 
                                   data-width="none"
                                   data-height="none"
                                   data-whitespace="nowrap"
                                   data-type="button" 
                                   data-responsive_offset="on" 
                                   data-responsive="off"
                                   data-frames='[{"from":"y:[-100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;","mask":"x:0px;y:0px;","speed":1500,"to":"o:1;","delay":1000,"ease":"Power3.easeInOut"},{"delay":"wait","speed":1000,"to":"auto:auto;","mask":"x:0;y:0;","ease":"Power3.easeInOut"},{"frame":"hover","speed":"300","ease":"Power1.easeInOut","to":"o:1;rX:0;rY:0;rZ:0;z:0;","style":"c:rgba(0, 0, 0, 1.00);bg:rgba(255, 255, 255, 1.00);bc:rgba(255, 255, 255, 1.00);bw:1px 1px 1px 1px;"}]'
                                   data-textAlign="['center','center','center','center']"
                                   data-paddingtop="[12,12,12,12]"
                                   data-paddingright="[30,35,35,15]"
                                   data-paddingbottom="[12,12,12,12]"
                                   data-paddingleft="[30,35,35,15]"
                                   href="ContactController"
                                   style="z-index: 8; white-space: nowrap; outline:none; box-shadow:none; box-sizing:border-box; -moz-box-sizing:border-box; -webkit-box-sizing:border-box; cursor:pointer; border-radius:30px;">
                                    CONTACT US
                                </a>
                            </li>
                            <li data-index="rs-200" 
                                data-transition="parallaxvertical" 
                                data-slotamount="default" 
                                data-hideafterloop="0" 
                                data-hideslideonmobile="off" 
                                data-easein="default" 
                                data-easeout="default" 
                                data-masterspeed="default" 
                                data-thumb="assets/images/slider/slide1.jpg" 
                                data-rotate="0" 
                                data-fstransition="fade" 
                                data-fsmasterspeed="1500" 
                                data-fsslotamount="7" 
                                data-saveperformance="off" 
                                data-title="A STUDY ON HAPPINESS" 
                                data-param1="" data-param2="" 
                                data-param3="" data-param4="" 
                                data-param5="" data-param6="" 
                                data-param7="" data-param8="" 
                                data-param9="" data-param10="" 
                                data-description="Science says that Women are generally happier">
                                <!-- MAIN IMAGE -->
                                <img src="assets/images/slider/slide2.jpg" alt="" 
                                     data-bgposition="center center" 
                                     data-bgfit="cover" 
                                     data-bgrepeat="no-repeat" 
                                     data-bgparallax="10" 
                                     class="rev-slidebg" 
                                     data-no-retina />

                                <!-- LAYER NR. 1 -->
                                <div class="tp-caption tp-shape tp-shapewrapper " 
                                     id="slide-200-layer-1" 
                                     data-x="['center','center','center','center']" data-hoffset="['0','0','0','0']" 
                                     data-y="['middle','middle','middle','middle']" data-voffset="['0','0','0','0']" 
                                     data-width="full"
                                     data-height="full"
                                     data-whitespace="nowrap"
                                     data-type="shape" 
                                     data-basealign="slide" 
                                     data-responsive_offset="off" 
                                     data-responsive="off"
                                     data-frames='[{"from":"opacity:0;","speed":1,"to":"o:1;","delay":0,"ease":"Power4.easeOut"},{"delay":"wait","speed":1000,"to":"opacity:1;","ease":"Power4.easeOut"}]'
                                     data-textAlign="['left','left','left','left']"
                                     data-paddingtop="[0,0,0,0]"
                                     data-paddingright="[0,0,0,0]"
                                     data-paddingbottom="[0,0,0,0]"
                                     data-paddingleft="[0,0,0,0]"
                                     style="z-index: 5;background-color:rgba(2, 0, 11, 0.80);border-color:rgba(0, 0, 0, 0);border-width:0px;"> 
                                </div>

                                <!-- LAYER NR. 2 -->
                                <div class="tp-caption Newspaper-Title   tp-resizeme" 
                                     id="slide-200-layer-2" 
                                     data-x="['center','center','center','center']" 
                                     data-hoffset="['0','0','0','0']" 
                                     data-y="['top','top','top','top']" 
                                     data-voffset="['250','250','250','240']" 
                                     data-fontsize="['50','50','50','30']"
                                     data-lineheight="['55','55','55','35']"
                                     data-width="full"
                                     data-height="none"
                                     data-whitespace="normal"
                                     data-type="text" 
                                     data-responsive_offset="on" 
                                     data-frames='[{"from":"y:[-100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;","mask":"x:0px;y:0px;s:inherit;e:inherit;","speed":1500,"to":"o:1;","delay":1000,"ease":"Power3.easeInOut"},{"delay":"wait","speed":1000,"to":"auto:auto;","mask":"x:0;y:0;s:inherit;e:inherit;","ease":"Power3.easeInOut"}]'
                                     data-textAlign="['center','center','center','center']"
                                     data-paddingtop="[0,0,0,0]"
                                     data-paddingright="[0,0,0,0]"
                                     data-paddingbottom="[10,10,10,10]"
                                     data-paddingleft="[0,0,0,0]"
                                     style="z-index: 6; font-family:rubik; font-weight:700; text-align:center; white-space: normal;text-transform:uppercase;">
                                    Welcome To University
                                </div>

                                <!-- LAYER NR. 3 -->
                                <div class="tp-caption Newspaper-Subtitle   tp-resizeme" 
                                     id="slide-200-layer-3" 
                                     data-x="['center','center','center','center']" 
                                     data-hoffset="['0','0','0','0']" 
                                     data-y="['top','top','top','top']" 
                                     data-voffset="['210','210','210','210']" 
                                     data-width="none"
                                     data-height="none"
                                     data-whitespace="nowrap"
                                     data-type="text" 
                                     data-responsive_offset="on"
                                     data-frames='[{"from":"y:[-100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;","mask":"x:0px;y:0px;s:inherit;e:inherit;","speed":1500,"to":"o:1;","delay":1000,"ease":"Power3.easeInOut"},{"delay":"wait","speed":1000,"to":"auto:auto;","mask":"x:0;y:0;s:inherit;e:inherit;","ease":"Power3.easeInOut"}]'
                                     data-textAlign="['left','left','left','left']"
                                     data-paddingtop="[0,0,0,0]"
                                     data-paddingright="[0,0,0,0]"
                                     data-paddingbottom="[0,0,0,0]"
                                     data-paddingleft="[0,0,0,0]"
                                     style="z-index: 7; white-space: nowrap;text-transform:uppercase; color:#fff; font-family:rubik; font-size:18px; font-weight:400;">
                                    Batter Education For A Better 
                                </div>

                                <!-- LAYER NR. 3 -->
                                <div class="tp-caption Newspaper-Subtitle   tp-resizeme" 
                                     id="slide-200-layer-4" 
                                     data-x="['center','center','center','center']" 
                                     data-hoffset="['0','0','0','0']" 
                                     data-y="['top','top','top','top']" 
                                     data-voffset="['320','320','320','290']" 
                                     data-width="['800','800','700','420']"
                                     data-height="['100','100','100','120']"
                                     data-whitespace="unset"
                                     data-type="text" 
                                     data-responsive_offset="on"
                                     data-frames='[{"from":"y:[-100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;","mask":"x:0px;y:0px;s:inherit;e:inherit;","speed":1500,"to":"o:1;","delay":1000,"ease":"Power3.easeInOut"},{"delay":"wait","speed":1000,"to":"auto:auto;","mask":"x:0;y:0;s:inherit;e:inherit;","ease":"Power3.easeInOut"}]'
                                     data-textAlign="['center','center','center','center']"
                                     data-paddingtop="[0,0,0,0]"
                                     data-paddingright="[0,0,0,0]"
                                     data-paddingbottom="[0,0,0,0]"
                                     data-paddingleft="[0,0,0,0]"
                                     style="z-index: 7; text-transform:capitalize; white-space: unset; color:#fff; font-family:rubik; font-size:18px; line-height:28px; font-weight:400;">
                                    an online education platform offering interactive courses, tutorials, and tools for students and educators, enhancing learning and teaching experiences for academic success
                                </div>
                                <!-- LAYER NR. 4 -->
                                <form method="post" action="PostController">
                                    <input type="hidden" name="service" value="BlogDetail">
                                    <input type="hidden" name="pid" value="1">
                                    <div class="tp-caption Newspaper-Button rev-btn " 
                                       id="slide-200-layer-5" 
                                       data-x="['center','center','center','center']" 
                                       data-hoffset="['90','80','75','90']" 
                                       data-y="['top','top','top','top']" 
                                       data-voffset="['400','400','400','420']" 
                                       data-width="none"
                                       data-height="none"
                                       data-whitespace="nowrap"
                                       data-type="button" 
                                       data-responsive_offset="on" 
                                       data-responsive="off"
                                       data-frames='[{"from":"y:[-100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;","mask":"x:0px;y:0px;","speed":1500,"to":"o:1;","delay":1000,"ease":"Power3.easeInOut"},{"delay":"wait","speed":1000,"to":"auto:auto;","mask":"x:0;y:0;","ease":"Power3.easeInOut"},{"frame":"hover","speed":"300","ease":"Power1.easeInOut","to":"o:1;rX:0;rY:0;rZ:0;z:0;","style":"c:rgba(0, 0, 0, 1.00);bg:rgba(255, 255, 255, 1.00);bc:rgba(255, 255, 255, 1.00);bw:1px 1px 1px 1px;"}]'
                                       data-textAlign="['center','center','center','center']"
                                       data-paddingtop="[12,12,12,12]"
                                       data-paddingright="[30,35,35,15]"
                                       data-paddingbottom="[12,12,12,12]"
                                       data-paddingleft="[30,35,35,15]"
                                       style="z-index: 8; white-space: nowrap; outline:none;box-shadow:none;box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;cursor:pointer; background-color:var(--primary) !important; border:0; border-radius:30px; margin-right:5px;">
                                        <button type="submit" class="btn-link" style="background: none; border: none; color: inherit; font: inherit; cursor: pointer;">READ MORE</button>
                                    </div>
                                </form>

                                <a class="tp-caption Newspaper-Button rev-btn" 
                                   id="slide-200-layer-6" 
                                   data-x="['center','center','center','center']" 
                                   data-hoffset="['-90','-80','-75','-90']" 
                                   data-y="['top','top','top','top']" 
                                   data-voffset="['400','400','400','420']" 
                                   data-width="none"
                                   data-height="none"
                                   data-whitespace="nowrap"
                                   data-type="button" 
                                   data-responsive_offset="on" 
                                   data-responsive="off"
                                   data-frames='[{"from":"y:[-100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;","mask":"x:0px;y:0px;","speed":1500,"to":"o:1;","delay":1000,"ease":"Power3.easeInOut"},{"delay":"wait","speed":1000,"to":"auto:auto;","mask":"x:0;y:0;","ease":"Power3.easeInOut"},{"frame":"hover","speed":"300","ease":"Power1.easeInOut","to":"o:1;rX:0;rY:0;rZ:0;z:0;","style":"c:rgba(0, 0, 0, 1.00);bg:rgba(255, 255, 255, 1.00);bc:rgba(255, 255, 255, 1.00);bw:1px 1px 1px 1px;"}]'
                                   data-textAlign="['center','center','center','center']"
                                   data-paddingtop="[12,12,12,12]"
                                   data-paddingright="[30,35,35,15]"
                                   data-paddingbottom="[12,12,12,12]"
                                   data-paddingleft="[30,35,35,15]"
                                   href="ContactController"
                                   style="z-index: 8; white-space: nowrap; outline:none; box-shadow:none; box-sizing:border-box; -moz-box-sizing:border-box; -webkit-box-sizing:border-box; cursor:pointer; border-radius:30px;">
                                    CONTACT US
                                </a>
                            </li>
                            <!-- SLIDE  -->
                        </ul>
                    </div><!-- END REVOLUTION SLIDER -->  
                </div>  
            </div>  
            <!-- Main Slider -->
            <div class="content-block">

                <!-- Our Services -->
                <div class="section-area content-inner service-info-bx">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-4 col-md-4 col-sm-6">
                                <div class="service-bx">
                                    <div class="action-box">
                                        <img src="assets/images/our-services/pic1.jpg" alt="">
                                    </div>
                                    <div class="info-bx text-center">
                                        <div class="feature-box-sm radius bg-white">
                                            <i class="fa fa-bank text-primary"></i>
                                        </div>
                                        <h4><a href="#">Best Industry Leaders</a></h4>
                                        <a href="#" class="btn radius-xl">View More</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-6">
                                <div class="service-bx">
                                    <div class="action-box">
                                        <img src="assets/images/our-services/pic2.jpg" alt="">
                                    </div>
                                    <div class="info-bx text-center">
                                        <div class="feature-box-sm radius bg-white">
                                            <i class="fa fa-book text-primary"></i>
                                        </div>
                                        <h4><a href="#">Learn Courses Online</a></h4>
                                        <a href="#" class="btn radius-xl">View More</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-12">
                                <div class="service-bx m-b0">
                                    <div class="action-box">
                                        <img src="assets/images/our-services/pic3.jpg" alt="">
                                    </div>
                                    <div class="info-bx text-center">
                                        <div class="feature-box-sm radius bg-white">
                                            <i class="fa fa-file-text-o text-primary"></i>
                                        </div>
                                        <h4><a href="#">Book Library & Store</a></h4>
                                        <a href="#" class="btn radius-xl">View More</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Our Services END -->

                <!-- Popular Courses -->
                <!--                <div class="section-area section-sp2 popular-courses-bx">
                                    <div class="container">
                                        <div class="row">
                                            <div class="col-md-12 heading-bx left">
                                                <h2 class="title-head">Popular <span>Courses</span></h2>
                                                <p>It is a long established fact that a reader will be distracted by the readable content of a page</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="courses-carousel owl-carousel owl-btn-1 col-12 p-lr0">
                                                <div class="item">
                                                    <div class="cours-bx">
                                                        <div class="action-box">
                                                            <img src="assets/images/courses/pic1.jpg" alt="">
                                                            <a href="#" class="btn">Read More</a>
                                                        </div>
                                                        <div class="info-bx text-center">
                                                            <h5><a href="#">Introduction EduChamp – LMS plugin</a></h5>
                                                            <span>Programming</span>
                                                        </div>
                                                        <div class="cours-more-info">
                                                            <div class="review">
                                                                <span>3 Review</span>
                                                                <ul class="cours-star">
                                                                    <li class="active"><i class="fa fa-star"></i></li>
                                                                    <li class="active"><i class="fa fa-star"></i></li>
                                                                    <li class="active"><i class="fa fa-star"></i></li>
                                                                    <li><i class="fa fa-star"></i></li>
                                                                    <li><i class="fa fa-star"></i></li>
                                                                </ul>
                                                            </div>
                                                            <div class="price">
                                                                <del>$190</del>
                                                                <h5>$120</h5>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>-->
                <!-- Popular Courses END -->

                <!-- Form -->
                <div class="section-area section-sp1 ovpr-dark bg-fix online-cours" style="background-image:url(assets/images/background/bg1.jpg);">
                    <div class="container">
                        <!--                        <div class="row">
                                                    <div class="col-md-12 text-center text-white">
                                                        <h2>Online Courses To Learn</h2>
                                                        <h5>Own Your Feature Learning New Skills Online</h5>
                                                        <form class="cours-search">
                                                            <div class="input-group">
                                                                <input type="text" class="form-control" placeholder="What do you want to learn today?	">
                                                                <div class="input-group-append">
                                                                    <button class="btn" type="submit">Search</button> 
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>-->
                        <!--                        <div class="mw800 m-auto">
                                                    <div class="row">
                                                        <div class="col-md-4 col-sm-6">
                                                            <div class="cours-search-bx m-b30">
                                                                <div class="icon-box">
                                                                    <h3><i class="ti-user"></i><span class="counter">5</span>M</h3>
                                                                </div>
                                                                <span class="cours-search-text">Over 5 million student</span>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4 col-sm-6">
                                                            <div class="cours-search-bx m-b30">
                                                                <div class="icon-box">
                                                                    <h3><i class="ti-book"></i><span class="counter">30</span>K</h3>
                                                                </div>
                                                                <span class="cours-search-text">30,000 Courses.</span>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4 col-sm-12">
                                                            <div class="cours-search-bx m-b30">
                                                                <div class="icon-box">
                                                                    <h3><i class="ti-layout-list-post"></i><span class="counter">20</span>K</h3>
                                                                </div>
                                                                <span class="cours-search-text">Learn Anythink Online.</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>-->
                    </div>
                </div>
                <!-- Form END -->
                <div class="section-area section-sp2">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12 text-center heading-bx">
                                <h2 class="title-head m-b0">New <span>Blogs</span></h2>
                                <p class="m-b0">Upcoming Education Events To Feed Your Brain. </p>
                            </div>
                        </div>
                        <div class="row">

                            <div class="upcoming-event-carousel owl-carousel owl-btn-center-lr owl-btn-1 col-12 p-lr0  m-b30">
                                <c:if test="${not empty posts}">
                                    <c:forEach var="posts" items="${posts}">
                                        <div class="item">
                                            <div class="event-bx">
                                                <div class="action-box">
                                                    <img src=${posts.thumbnailUrl} alt="">
                                                </div>
                                                <div class="info-bx d-flex">
                                                    <div>
                                                        <div class="event-time">
                                                            <div class="event-date">${posts.postId}</div>
                                                            <div class="event-month"><c:choose>
                                                                    <c:when test="${not empty user}">
                                                                        <c:forEach var="user" items="${user}">
                                                                            <c:if test="${user.user_id == posts.user_id}">
                                                                                ${user.full_name}
                                                                            </c:if>
                                                                        </c:forEach>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        Anonymous.
                                                                    </c:otherwise>
                                                                </c:choose></div>
                                                        </div>
                                                    </div>
                                                    <div class="event-info">
                                                        <form method="post" action="PostController">
                                                            <input type="hidden" name="service" value="BlogDetail">
                                                            <input type="hidden" name="pid" value="${posts.postId}">
                                                            <h4 class="event-title"><button type="submit" class="btn-link">${posts.title}</button></h4>
                                                        </form>
                                                        <ul class="media-post">
                                                            <li>
                                                                <form method="post" action="PostController">
                                                                    <input type="hidden" name="service" value="BlogDetail">
                                                                    <input type="hidden" name="pid" value="${posts.postId}">
                                                                    <button type="submit" class="btn-link">READ MORE</button>
                                                                </form>
                                                            </li>

                                                        </ul>
                                                        <p>${posts.summary}</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:if>
                            </div>
                            <div class="text-center">
                                <a href="PostController" onclick="sendPostRequestForBlog('BlogList'); return false;" class="btn">View All Blogs</a>
                            </div>
                        </div>
                    </div>

                    <!-- Testimonials -->

                    <!-- Recent News End -->

                </div>
                <!-- contact area END -->
            </div>
            <!-- Content END-->
            <!-- Footer ==== -->
            <jsp:include page="Footer.jsp"/>
            <!-- Footer END ==== -->
            <button class="back-to-top fa fa-chevron-up" ></button>
        </div>

        <!-- External JavaScripts -->
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
        <!-- Revolution JavaScripts Files -->
        <script src="assets/vendors/revolution/js/jquery.themepunch.tools.min.js"></script>
        <script src="assets/vendors/revolution/js/jquery.themepunch.revolution.min.js"></script>
        <!-- Slider revolution 5.0 Extensions  (Load Extensions only on Local File Systems !  The following part can be removed on Server for On Demand Loading) -->
        <script src="assets/vendors/revolution/js/extensions/revolution.extension.actions.min.js"></script>
        <script src="assets/vendors/revolution/js/extensions/revolution.extension.carousel.min.js"></script>
        <script src="assets/vendors/revolution/js/extensions/revolution.extension.kenburn.min.js"></script>
        <script src="assets/vendors/revolution/js/extensions/revolution.extension.layeranimation.min.js"></script>
        <script src="assets/vendors/revolution/js/extensions/revolution.extension.migration.min.js"></script>
        <script src="assets/vendors/revolution/js/extensions/revolution.extension.navigation.min.js"></script>
        <script src="assets/vendors/revolution/js/extensions/revolution.extension.parallax.min.js"></script>
        <script src="assets/vendors/revolution/js/extensions/revolution.extension.slideanims.min.js"></script>
        <script src="assets/vendors/revolution/js/extensions/revolution.extension.video.min.js"></script>
        <script>
                                    jQuery(document).ready(function () {
                                        var ttrevapi;
                                        var tpj = jQuery;
                                        if (tpj("#rev_slider_486_1").revolution == undefined) {
                                            revslider_showDoubleJqueryError("#rev_slider_486_1");
                                        } else {
                                            ttrevapi = tpj("#rev_slider_486_1").show().revolution({
                                                sliderType: "standard",
                                                jsFileLocation: "assets/vendors/revolution/js/",
                                                sliderLayout: "fullwidth",
                                                dottedOverlay: "none",
                                                delay: 9000,
                                                navigation: {
                                                    keyboardNavigation: "on",
                                                    keyboard_direction: "horizontal",
                                                    mouseScrollNavigation: "off",
                                                    mouseScrollReverse: "default",
                                                    onHoverStop: "on",
                                                    touch: {
                                                        touchenabled: "on",
                                                        swipe_threshold: 75,
                                                        swipe_min_touches: 1,
                                                        swipe_direction: "horizontal",
                                                        drag_block_vertical: false
                                                    }
                                                    ,
                                                    arrows: {
                                                        style: "uranus",
                                                        enable: true,
                                                        hide_onmobile: false,
                                                        hide_onleave: false,
                                                        tmp: '',
                                                        left: {
                                                            h_align: "left",
                                                            v_align: "center",
                                                            h_offset: 10,
                                                            v_offset: 0
                                                        },
                                                        right: {
                                                            h_align: "right",
                                                            v_align: "center",
                                                            h_offset: 10,
                                                            v_offset: 0
                                                        }
                                                    },

                                                },
                                                viewPort: {
                                                    enable: true,
                                                    outof: "pause",
                                                    visible_area: "80%",
                                                    presize: false
                                                },
                                                responsiveLevels: [1240, 1024, 778, 480],
                                                visibilityLevels: [1240, 1024, 778, 480],
                                                gridwidth: [1240, 1024, 778, 480],
                                                gridheight: [768, 600, 600, 600],
                                                lazyType: "none",
                                                parallax: {
                                                    type: "scroll",
                                                    origo: "enterpoint",
                                                    speed: 400,
                                                    levels: [5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 46, 47, 48, 49, 50, 55],
                                                    type: "scroll",
                                                },
                                                shadow: 0,
                                                spinner: "off",
                                                stopLoop: "off",
                                                stopAfterLoops: -1,
                                                stopAtSlide: -1,
                                                shuffle: "off",
                                                autoHeight: "off",
                                                hideThumbsOnMobile: "off",
                                                hideSliderAtLimit: 0,
                                                hideCaptionAtLimit: 0,
                                                hideAllCaptionAtLilmit: 0,
                                                debugMode: false,
                                                fallbacks: {
                                                    simplifyAll: "off",
                                                    nextSlideOnWindowFocus: "off",
                                                    disableFocusListener: false,
                                                }
                                            });
                                        }
                                    });
        </script>
</body>
</html>
