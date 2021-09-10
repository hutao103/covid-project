<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>COVID-19 SYSTEM</title>
    <link type="text/css" rel="stylesheet" href="${request.contextPath}/css/style.css" />
    <link type="text/css" rel="stylesheet" href="${request.contextPath}/css/public.css" />
</head>
<body>
<!--HEAD-->
    <header class="publicHeader">
        <h1>COVID-19 SYSTEM</h1>
        <div class="publicHeaderR">
            <p><span>HELLO!</span><span style="color: #fff21b"> ${userSession.userName}</span></p>
            <a href="${request.contextPath}/logout">LOGOUT</a>
        </div>
    </header>
<!--TIME-->
    <section class="publicTime">
        <span id="time"></span>
        <a href="javascript:;">Warm prompt: in order to browse, please use the high version of the browser！（IE10+）</a>
    </section>
 <!--BODY CONTENT-->
 <section class="publicMian ">
     <div class="left">
         <h2 class="leftH2"><span class="span1"></span>FUNCTION<span></span></h2>
         <nav>
             <ul class="list">
             	<li><a href="${request.contextPath}/covid/user/query">COVID USER</a></li>
             	<li><a href="${request.contextPath}/address/query">ADDRESS</a></li>
             	<li><a href="${request.contextPath}/monitor/query">MONITOR</a></li>
             	<li><a href="${request.contextPath}/vaccine/query">VACCINE</a></li>
             	<li><a href="${request.contextPath}/meetingInfected/query">MEETING</a></li>
             	<li><a href="${request.contextPath}/userPath/query">USERPATH</a></li>
             	<li><a href="${request.contextPath}/user/query">SYSTEM USER</a></li>
             	<li><a href="${request.contextPath}/logout">LOGOUT</a></li>
             </ul>
         </nav>
     </div>
<input type="hidden" id="path" name="path" value="${request.contextPath}"/>
<input type="hidden" id="referer" name="referer" value=""/>