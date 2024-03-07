
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Edit Account</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <style>
            img{
                width: 200px;
                height: 120px;
            }
        </style>
    <body>
        <div class="container">
            
            <div id="editEmployeeModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="editAc" method="post">
                            <div class="modal-header">						
                                <h4 class="modal-title">Edit Account</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><a href="managerAc">&times;</a></button>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>ID</label>
                                    <input value="${accEdit.getId()}" name="id" type="text" class="form-control" readonly required>
                                </div>
                                <div class="form-group">
                                    <label>Name</label>
                                    <input value="${accEdit.getName()}" name="name" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Account Name</label>
                                    <input value="${accEdit.getUsername()}" name="user" type="text" class="form-control" required readonly >
                                </div>
                                <div class="form-group">
                                    <label>Password</label>
                                    <input value="${accEdit.getPassword()}" name="pass" type="password" class="form-control" readonly="" required>
                                </div>
                                <div class="form-group">
                                    <label>Role</label>
                                    <input value="${accEdit.getRole_id()}" name="roleId" type="text" class="form-control" required readonly="">
                                </div>
                                    <div><p style="color:red">${mess}</p></div>
                            </div>
                            <div class="modal-footer">
                                <input type="submit" class="btn btn-success" value="Edit">
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>
        <script src="js/manager.js" type="text/javascript"></script>
    </body>
</html>