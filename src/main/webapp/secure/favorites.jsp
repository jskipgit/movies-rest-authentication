<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Favorites</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        /* Remove the navbar's default margin-bottom and rounded borders */
        .navbar {
            margin-bottom: 0;
            border-radius: 0;
        }

        /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
        .row.content {height: 450px}

        /* Set gray background color and 100% height */
        .sidenav {
            padding-top: 20px;
            background-color: #f1f1f1;
            height: 100%;
        }

        /* Set black background color, white text and some padding */
        footer {
            background-color: #555;
            color: white;
            padding: 15px;
        }

        /* On small screens, set height to 'auto' for sidenav and grid */
        @media screen and (max-width: 767px) {
            .sidenav {
                height: auto;
                padding: 15px;
            }
            .row.content {height:auto;}
        }
    </style>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Logo</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Favorites</a></li>
                <li><a href="/secure/movies">Home</a></li>
                <c:if test="${user_perms.contains('PERM_CREATE_MOVIE')}">
                    <li><a href="/secure/create.jsp">Create</a></li>
                </c:if>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/secure/logout"><span class="glyphicon glyphicon-log-out"></span>Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <c:if test="${delete_success != null}">
        <div class="alert alert-success">
            <c:out value="${delete_success}"/>
        </div>
    </c:if>
    <div class="row">
        <c:forEach items="${favoriteMovies}" var="aMovie">
            <div class="card col-sm-12 col-md-4">
                <h4 class="card-title"><c:out value="${aMovie.name}"/></h4>
                <img class="card-img-top" width="100" src="<c:out value="${aMovie.posterUrl}"/>" alt="Card image cap">
                <div class="card-block">
                    <p class="card-text"><c:out value="${aMovie.description}"/></p>
                    <p class="card-text"><small class="text-muted"><c:out value="${aMovie.mpaaRating}"/></small></p>
                    <p class="card-text"><a class="btn btn-danger" href="/secure/user/favorites/remove?id=<c:out value="${aMovie.id}"/>">Remove Favorite</a> </p>
                </div>
            </div>
        </c:forEach>


    </div>
</div>




<footer class="container-fluid text-center">
    <p>Welcome To Skippers Web App</p>
</footer>

</body>
</html>
