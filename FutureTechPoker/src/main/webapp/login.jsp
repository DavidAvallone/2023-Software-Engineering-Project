<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  String code = request.getParameter("msg");
  String message = "";

  if(code != null) {
    switch (code) {
      case "1":
        message = "Login or Password Incorrect.";
        break;
      case "2":
        message = "Unexpected error.";
        break;
    }
  }

%>
<html>
<head>
  <title>FutureTech Poker Login</title>
  <link rel="stylesheet" type="text/css" href="home.css">
</head>
<body>
  <div class="banner">
    <h1><%= "FutureTech Poker" %></h1>
  </div>
  <br/>
  <div class="animated-background"></div>


  <div class="centered">
    <h2> Login </h2>
    <form method="post" action="loginServlet">
      <div class="row mb-2">
        <label class="col-form-label col-md-3 col-lg-1" for="txt_login" style="font-family: Copperplate Gothic Light, fantasy;"> Login: </label>
        <div class="col-12 col-md-6">
          <input class="form-control" type="email" name="txt_login" id="txt_login" required/>
        </div>
      </div>
      <br>
      <div class="row mb-2">
        <label class="col-form-label col-md-3 col-lg-1" for="txt_pass" style="font-family: Copperplate Gothic Light, fantasy;"> Password: </label>
        <div class="col-12 col-md-6">
          <input class="form-control" type="password" name="txt_pass" id="txt_pass" autocomplete="off"
                 minlength="6" required/>
        </div>
      </div>
      <br>
      <div class="centered">
  <%--      <div class="offset-md-1 col-12 col-md-2 d-grid">--%>
  <%--        <button type="submit" class="btn btn-success me-2"> <span class="bi bi-box-arrow-in-right"></span> Login </button>--%>
  <%--      </div>--%>
        <div class="table-buttons">
          <button type="submit" class="btn btn-success me-2"> <span class="bi bi-box-arrow-in-right"></span> Login </button>
        </div>
        <br>
        <div class="table-buttons">
          <button type="reset" class="btn btn-warning me-2" id="clear_button"> Clear</button>
        </div>
  <%--      <div class="col-12 col-md-2 d-grid">--%>
  <%--        <button type="reset" class="btn btn-warning me-2" id="clear_button"> Clear</button>--%>
  <%--      </div>--%>
  <%--      <div class="col-12 col-md-2 d-grid">--%>
  <%--        <a type="button" class="btn btn-danger me-2" href="index.jsp"> Cancel</a>--%>
  <%--      </div>--%>
        <br>
        <div class="table-buttons">
          <button onclick="window.location.href='index.jsp'">Cancel</button>
        </div>

      </div>
    </form>
  </div>

  <% if(message.length()>0){ %>
  <div class="container">
    <div class="row">
      <div class="col-md-6">
        <div class="alert alert-warning alert-dismissible">
  <%--        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>--%>
          <%= message %>
        </div>
      </div>
    </div>
  </div>
  <% } /*Closing IF */ %>

  <br/><br/>
  <br/><br/>
  <br/><br/>

  <script type="text/javascript"></script>
</body>
</html>
