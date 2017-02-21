<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
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
                <li><a href="/secure/user/favorites/list">Favorites</a></li>
                <li class="active"><a href="#">Home</a></li>
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
    <c:if test="${success_msg != null}">
        <div class="alert alert-success">
            <c:out value="${success_msg}"/>
        </div>
    </c:if>
    <div class="row">
        <c:forEach items="${mList}" var="aMovie">
            <div class="card col-sm-12 col-md-4">
                <h4 class="card-title"><c:out value="${aMovie.name}"/></h4>
                <c:if test="${aMovie.posterUrl != null && aMovie.posterUrl.length() > 0}">
                    <img class="card-img-top" width="100" src="<c:out value="${aMovie.posterUrl}"/>" alt="Card image cap">
                </c:if>
                <c:if test="${aMovie.posterFileName != null && aMovie.posterFileName.length() > 0}">
                    <img class="card-img-top" width="100" src="/ourCoolUploadedFiles/<c:out value="${aMovie.posterFileName}"/>" alt="Card image cap">
                </c:if>
                <div class="card-block">
                    <p class="card-text"><c:out value="${aMovie.description}"/></p>
                    <p class="card-text"><small class="text-muted"><c:out value="${aMovie.mpaaRating}"/></small></p>
                    <p class="card-text"><a class="btn btn-info" href="/secure/movie/select?id=<c:out value="${aMovie.id}"/>">Edit</a> </p>
                    <p class="card-text"><a class="btn btn-danger" href="/secure/movie/delete?id=<c:out value="${aMovie.id}"/>">Delete</a> </p>
                    <p class="card-text"><a class="btn btn-warning" href="/secure/user/favorites/add?id=<c:out value="${aMovie.id}"/>">Add To Favorites</a> </p>
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
