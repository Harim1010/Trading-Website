<%-- 
    Document   : MySaleo.jsp
    Created on : Jan 30, 2024, 4:20:28 PM
    Author     : Xuanphuc
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>  
<%@ page import="model.*" %>
<%@ page import="util.*" %>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Quản ký tài khoản</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="./assets/css/MySale.css">
        <style>
            .add-button-container {
                text-align: right;
            }

            .add-button-container a {
                text-decoration: none;
                color: #fff;
            }

            .add-button-container a:hover {
                color: #fff;
                text-decoration: none;
            }

            .add-button {
                background-color: #007bff;
                border: none;
                color: #fff;
                padding: 8px 16px;
                border-radius: 5px;
                font-size: 12px;
                margin: 10px;
                cursor: pointer;
            }

            .add-button:hover {
                background-color: #0056b3;
            }
            .header-list{
                padding-top: 20px;
                font-size: 20px;
            }
            .table{
                font-size: 15px;
            }
            .filter{
                width: 10%;
            }
            input[type=number]::-webkit-inner-spin-button,
            input[type=number]::-webkit-outer-spin-button {
                -webkit-appearance: none;
                margin: 0;
            }
            input[type=number] {
                -moz-appearance: textfield;
            }
            .filter .search{
                background-color: #007bff;
                color: white;
                border-radius: 20%;
            }
        </style>
    </head>
    <body>
        <%@include file="Component/header.jsp" %>

        <div class="container-fluid" style="margin-top: 150px;">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row header-list" style="background-color:#566787;">
                        <h2 style="color: #ffffff; font-size: 30px"><b>PRODUCT INFORMATIONS</b></h2>
                    </div>    
                    <div class=" add-button-container">
                        <a href="AddProduct.jsp"><button class="add-button">Add Product</button></a>
                    </div>
                </div>
                <form action="mysale" method="post">
                    <table class="table table-striped table-hover" >
                        <thead>
                            <tr>
                                <th>Code product</th>
                                <th>Title product</th>
                                <th>Status product</th>
                                <th>Buyer</th>
                                <th>Contact</th>
                                <th>Public/Hidden</th>
                                <th>Price</th>
                                <th>Transaction fees</th>
                                <th>Pay transaction fees</th>
                                <th>Total fee buyer pay</th>
                                <th>Money received actually</th>
                                <th>Created time</th>
                                <th>Updated time</th>
                                <th>Detail</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class="filter"><input type="text" name="code" style="width: 70%" /></td>
                                <td class="filter"><input type="text" name="title" style="width: 70%" /></td>
                                <td class="filter">    
                                    <select name="orderstatus"  style="width: 90%">
                                        <option value="">All</option>
                                        <option value="ready to trade">Ready to trade</option>
                                        <option value="transacted">Transacted</option>
                                        <option value="complain">Complain</option>                                </select>
                                </td>

                                <td class="filter"><input type="text" name="productbuyer" style="width: 70%" /></td>
                                <td class="filter"><input type="text" name="contact" style="width: 70%" /></td>
                                <td class="filter">    
                                    <select name="publicstatus"  style="width: 90%">
                                        <option value="">All</option>
                                        <option value="1">public</option>
                                        <option value="0">hide</option>
                                    </select>
                                </td>
                                <td class="filter">
                                    <input type="number" name="pricefrom" placeholder="From" style="width: 50%" />
                                    <input type="number" name="priceto" placeholder="To" style="width: 40%" />
                                </td>
                                <td class="filter"><input type="text" style="width: 70%" hidden/></td>
                                <td class="filter">
                                    <select name="paytransaction"  style="width: 90%">
                                        <option value="">All</option>
                                        <option value="0">seller</option>
                                        <option value="1">buyer</option>
                                    </select>
                                </td>
                                <td class="filter"><input type="text" style="width: 70%" hidden/></td>
                                <td class="filter"><input type="text" style="width: 70%" hidden/></td>
                                <td class="filter">
                                    <input type="date" name="createdfrom" placeholder="From" style="width: 50%" />
                                    <input type="date" name="createdto" placeholder="To" style="width: 40%" />
                                </td>
                                <td class="filter">
                                    <input type="date" name="updatedfrom" placeholder="From" style="width: 50%" />
                                    <input type="date" name="updatedto" placeholder="To" style="width: 40%" />
                                </td>
                                <td class="filter"><input class="search" type="submit" value="search" style="width: 90%"/></td>
                            </tr>
                            <c:forEach items="${listsalepro}" var="p">

                                <tr>
                                    <td>${p.getId()}</td>
                                    <td>${p.getTitle()}</td>
                                    <td>${p.getOrder_status()}</td>
                                    <td></td>
                                    <td>${p.getContact()}</td>
                                    <c:choose>
                                        <c:when test="${p.isPublic_status()}">
                                            <td>public for everyone</td>
                                        </c:when> 
                                        <c:otherwise>
                                            <td>Hide for those with the link</td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td><fmt:formatNumber type="number" value="${p.getPrice()}" pattern="#,###"/></td>
                                    <td><fmt:formatNumber type="number" value="${(p.getPrice()*0.1)}" pattern="#,###"/></td>

                                    <c:choose>
                                        <c:when test="${p.isFee_type()}">
                                            <td>Seller</td>
                                            <td><fmt:formatNumber type="number" value="${p.getPrice()}" pattern="#,###"/></td>
                                            <td><fmt:formatNumber type="number" value="${p.getPrice()}" pattern="#,###"/></td>

                                        </c:when> 
                                        <c:otherwise>
                                            <td>Buyer</td>
                                            <td><fmt:formatNumber type="number" value="${(p.getPrice()+(p.getPrice()*0.1))}" pattern="#,###"/></td>
                                            <td><fmt:formatNumber type="number" value="${p.getPrice()}" pattern="#,###"/></td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td>${p.getCreated_at()}</td>
                                    <td>${p.getUpdated_at()}</td>
                                    <td><a href="detailproduct?id=${p.getId()}">Detail</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


    </body>

</html>