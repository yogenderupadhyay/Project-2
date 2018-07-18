<html>
<head>
<title>index</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
<div class="container">
<img src="resources/images/login.PNG" >
  <h2>Register Here</h2>
  <form action="register">
  <div class="form-group">
      <label for="fName">First Name:</label>
      <input type="text" class="form-control" id="fName" placeholder="Enter First Name" name="fName">
    </div>
    <div class="form-group">
      <label for="LName">Last Name:</label>
      <input type="text" class="form-control" id="LName" placeholder="Enter Last Name" name="LName">
    </div>
    <div class="form-group">
      <label for="uname">Username:</label>
      <input type="text" class="form-control" id="uname" placeholder="Enter Username must be less than 43 characters" name="uname"><B>@sb.com</B>
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="psw">
    </div>
    <div class = "form-group">
    <label for="gender" >Gender :</label>
    <input type="radio" id="gender" name="gender" value="male" checked> Male<br>
  <input type="radio" id="gender" name="gender" value="female"> Female<br>
    </div>
    <div class="form-group">
      <label for="mobile">Mobile:</label>
      <input type="text" class="form-control" id="mobile" placeholder="Enter your mobile number" name="mobile">
    </div>
    <div class="form-group">
      <label for="dob">Date of Birth:</label>
      <input type="date" class="form-control" id="dob" placeholder="mm/dd/yy" name="dob">
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
  </form>
</div>
</body>
</html>