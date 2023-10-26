<%--
  Created by IntelliJ IDEA.
  User: neilkeohane
  Date: 10/23/23
  Time: 6:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  String error = request.getParameter("error");
    String errorMessage = "";

    if(error != null) {
        switch (error) {
            case "1":
                errorMessage = "Email is already registered..";
                break;
            case "2":
                errorMessage = "Unexpected error.";
                break;
        }
    }

%>
<html>
<head>
    <title>FutureTech Poker</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h1><%= "FutureTech Poker" %></h1>
<br/>
<%-- Here you put the content for this page --%>

<div class="container mt-2">
    <h3 class="center text-primary"> Register New User </h3>
    <form method="post" action="registerUserServlet">
        <div class="row mb-2">
            <label class="col-form-label col-md-3 col-lg-1" for="txt_name"> Name: </label>
            <div class="col-12 col-md-6">
                <input class="form-control" type="text" name="txt_name" id="txt_name" />
            </div>
        </div>
        <div class="row mb-2">
            <label class="col-form-label col-md-3 col-lg-1" for="txt_login"> Login: </label>
            <div class="col-12 col-md-6">
                <input class="form-control" type="email" name="txt_login" id="txt_login" required/>
            </div>
        </div>
        <div class="row mb-2">
            <label class="col-form-label col-md-3 col-lg-1" for="txt_pass"> Password: </label>
            <div class="col-12 col-md-6">
                <input class="form-control" type="password" name="txt_pass" id="txt_pass" autocomplete="off"
                       minlength="6" required/>
            </div>
        </div>
        <div class="row mb-2">
            <label class="col-form-label col-md-3 col-lg-1" for="txt_conf"> Confirm Password: </label>
            <div class="col-12 col-md-6">
                <input class="form-control" type="password" name="txt_conf" id="txt_conf" autocomplete="off"
                       minlength="6" required/>
            </div>
        </div>
        <div class="row mb-2">
            <div class="offset-md-1 col-12 col-md-2 d-grid">
                <button type="submit" onclick="return checkRegisterForm()" class="btn btn-success me-2"> Register</button>
            </div>
            <div class="col-12 col-md-2 d-grid">
                <button type="reset" class="btn btn-warning me-2" id="clear_button"> Clear</button>
            </div>
            <div class="col-12 col-md-2 d-grid">
                <a type="button" class="btn btn-danger me-2" href="index.jsp"> Cancel</a>
            </div>
        </div>
    </form>
</div>

<% if(errorMessage.length()>0){ %>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <div class="alert alert-danger alert-dismissible">
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                <strong>Error!</strong> <%= errorMessage %>
            </div>
        </div>
    </div>
</div>
<% } /*Closing IF */ %>

<br/><br/>

<%-- =================================== --%>
<script type="text/javascript">
function checkRegisterForm(){
    let pass = document.getElementById("txt_pass").value;
    let conf = document.getElementById("txt_conf").value;
    if(pass === conf){
        //Typed text in Password and Confirm Password are the same
        return true;
    } else {
        //Typed text in Password and Confirm Password do not match.
        window.alert("Passwords do not match.");
        return false;
    }
}
</script>
</body>
</html>
