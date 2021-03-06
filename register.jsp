<!DOCTYPE html>
<html lang="en">
<% String ctxPath = request.getContextPath(); %>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>Register now !</title>

  <link href="<%= ctxPath %>/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%= ctxPath %>/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
  <link href="<%= ctxPath %>/css/register.css" rel="stylesheet">
</head>

<body>

<div class="container">

      <!-- TODO : fix form method -->
      <form class="form-signin" action="register" method="POST">
        
        <!-- TODO : check for error message and display this div -->
        <div class="alert alert-danger" role="alert">show error message here if any !</div>
        
        <h2 class="form-signin-heading">
        	<span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>
        	Register Now !
        </h2>
        
        <label for="firstname" class="sr-only">Firstname</label>
        <input id="firstname" class="form-control" placeholder="firstname" required="" autofocus="" type="text">
        
        <label for="lastname" class="sr-only">Lastname</label>
        <input id="lastname" class="form-control" placeholder="lastname" required="" type="text">
        
        <label for="email" class="sr-only">Email address</label>
        <input id="email" class="form-control" placeholder="Email address" required="" type="email">
        
        <label for="password" class="sr-only">Password</label>
        <input id="password" class="form-control" placeholder="Password" required="" type="password">
        
        <label for="confirmPassword" class="sr-only">Confirm Password</label>
        <input id="confirmPassword" class="form-control" placeholder="confirm password" required="" type="password">
        
        <button class="btn btn-lg btn-primary btn-block" type="submit">Register !</button>
      </form>

</div>


  <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
  <script src="<%= ctxPath %>/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>