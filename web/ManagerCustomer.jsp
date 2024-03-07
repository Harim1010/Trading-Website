
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Quản ký tài khoản</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/ManagerProduct.css" rel="stylesheet" type="text/css"/>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
            rel="stylesheet"
            />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <style>
            img{
                width: 200px;
                height: 120px;
            }
        </style>
        <script>
            function back() {
                window.location.href = "home.jsp";
            }
            function doDelete(id)
            {
                var c = confirm("Are you sure?");
                if (c)
                {
                    window.location.href = "deleteAc?pid=" + id;
                }
            }
        </script>
    </head>
    <body>
        <%@include file="Component/header.jsp" %>
        <div class="container" style="margin-top: 115px;">
           
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row" style="background-color:#3498db; padding-bottom: 5px; padding-top: 8px; " >
                        <h2 style="color: #ffffff;">User Management</h2>   
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>
                                <span class="custom-checkbox">
                                    <input type="checkbox" id="selectAll">
                                    <label for="selectAll"></label>
                                </span>
                            </th>
                            <th>ID</th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Name</th>
                            <th>Role</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listA}" var="p">
                            <tr>
                                <td>
                                    <span class="custom-checkbox">
                                        <input type="checkbox" id="checkbox1" name="options[]" value="1">
                                        <label for="checkbox1"></label>
                                    </span>
                                </td>
                                <td>${p.id}</td>
                                <td>${p.name}</td>
                                <td>${p.email}</td>
                                <td>${p.username}</td>
                                <td>${p.role_id == 1 ? 'admin' : p.role_id == 0 ? 'user' : 'unknown'}</td>
                                <td>
                                    <a href="editAc?pid=${p.id}"  class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                </td>
                                <td><a href="deleteAc?pid=${p.id}"  class="edit" data-toggle="modal"  onclick="doDelete(${p.id}) > <i class="material-icons" data-toggle="tooltip" title="Delete" >X</i></a></td>
                            </tr>
                        </c:forEach>
                    <h4 style="color:red">${mess}</h4>
                    </tbody>
                </table>

            </div>
            <a href="#">
                <button type="button" class="btn btn-primary" onclick="back()" style="background-color: #3498db">Trang chủ</button>
                <script src="js/ManagerProduct.js" type="text/javascript"></script>
            </a>
        </div>
        <%@include file="Component/footer.jsp" %>
    </body>  
</html>