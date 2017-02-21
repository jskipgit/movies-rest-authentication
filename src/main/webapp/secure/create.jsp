<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
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
                <li><a href="/secure/movies">Home</a></li>
                <li class="active"><a href="#">Create</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/secure/logout"><span class="glyphicon glyphicon-log-out"></span>Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <c:if test="${succes_movie_create_msg != null}">
        <div class="alert alert-success">
            <c:out value="${succes_movie_create_msg}"/>
        </div>
    </c:if>
    <div class="row">
        <form action="/secure/movie/create" enctype="multipart/form-data"  method="post">
            <div class="form-group">
                <label for="xzy">Movie Name:</label>
                <input type="text" class="form-control" id="xzy" name="name" value="<c:out value="${selectedMovie.name}"/>">
            </div>
            <div class="form-group">
                <label for="Description">Description:</label>
                <input type="text" class="form-control" id="Description" name="description" value="<c:out value="${selectedMovie.description}"/>">
            </div>
            <div class="form-group">
                <label for="Category">Category:</label>
                <input type="text" class="form-control" id="Category" name="category" value="<c:out value="${selectedMovie.category}"/>">
            </div>
            <div class="form-group">
                <label for="mpaaRating">MPAA Rating:</label>
                <input type="text" class="form-control" id="mpaaRating" name="mpaaRating"  value="<c:out value="${selectedMovie.mpaaRating}"/>">
            </div>
            <div class="form-group">
                <label for="rating">Rating:</label>
                <input type="number" step="0.1" class="form-control" id="rating" name="rating" value="<c:out value="${selectedMovie.rating}"/>">
            </div>
            <div class="form-group">
                <label for="url">Poster URL:</label>
                <input type="text" class="form-control" id="url" name="posterUrl" value="<c:out value="${selectedMovie.posterUrl}"/>">
            </div>

            <div class="form-group">
                <label for="posterFile">Poster Image:</label>
                <input type="file" class="form-control" id="posterFile" name="movieFile">
            </div>
            <input type="hidden" name="id"  value="<c:out default="0" value="${selectedMovie.id}"/>" />
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
    </div>
</div>




<footer class="container-fluid text-center">
    <p>Welcome To Skippers Web App</p>
</footer>

</body>
</html>
