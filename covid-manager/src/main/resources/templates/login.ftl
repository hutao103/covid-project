<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>SYSTEM LOGIN</title>
    <link type="text/css" rel="stylesheet" href="${request.contextPath}/css/style.css" />
    <script type="text/javascript">
	/* if(top.location!=self.location){
	      top.location=self.location;
	 } */
    </script>
</head>
<body class="login_bg">
    <section class="loginBox">
        <header class="loginHeader">
            <h1>COVID-19</h1>
        </header>
        <section class="loginCont">
	        <form class="loginForm" action="${request.contextPath}/user/login"  name="actionForm" id="actionForm"  method="post" >
				<div class="info">${error}</div>
				<div class="inputbox">
                    <label for="user">UN:</label>
					<input type="text" class="input-text" id="userName" name="userName" placeholder="Username" value="${userName}"/>
				</div>	
				<div class="inputbox">
                    <label for="mima">PWD:</label>
                    <input type="password" id="password" name="password" placeholder="Password" value="${password}"/>
                </div>	
				<div class="subBtn">
					
                    <input type="submit" value="LOGIN"/>
                    <input type="reset" value="RESET"/>
                </div>	
			</form>
        </section>
    </section>
</body>
</html>
