<%-- 
    Document   : AddProduct
    Created on : Jan 29, 2024, 10:50:48 PM
    Author     : Xuanphuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>JSP Page</title>
        <!-- Include Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.tiny.cloud/1/tvyzan2m5d7swqx0o070hgujuzbcvpkmqalb4rrhampk4hgr/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>
        <link rel="stylesheet" href="./assets/css/AddProduct.css">
    </head>
    <body>
        <div class="container custom-container">
            <div class="intermediary-order">
                Intermediary_order
            </div>

            <form id="contact" action="addproduct" method="post">
                <!-- Title -->
                <div class="form-row">
                    <div class="col-md-2">
                        <label for="title">Title</label>
                    </div>
                    <div class="col-md-10">
                        <textarea type="text" class="form-control keep-plain-textarea" id="title" name="title" required autofocus></textarea>
                    </div>
                </div>

                <!-- Price -->
                <div class="form-row">
                    <div class="col-md-2">
                        <label for="price">Price</label>
                    </div>
                    <div class="col-md-10">
                        <input type="number" class="form-control" id="price" name="price" required>
                    </div>
                </div>

                <!-- Fee Type -->
                <div class="form-row">
                    <div class="col-md-2">
                        <label>Fee Type</label>
                    </div>
                    <div class="col-md-10">
                        <div class="fee-type-container">
                            <div class="fee-type-option">
                                <input type="radio" id="buy" name="feetype" value="1" required>
                                <label for="buy">Buyer</label>
                            </div>
                            <div class="fee-type-option">
                                <input type="radio" id="sale" name="feetype" value="0" required>
                                <label for="sale">Seller</label>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Description -->
                <div class="form-row">
                    <div class="col-md-2">
                        <label for="description">Description(The more detailed the product, the better because this will
                            be the legal basis for resolving complaints if any arise later)</label>
                    </div>
                    <div class="col-md-10">
                        <textarea type="text" class="form-control "  id="description" name="description" rows="8" ></textarea>
                    </div>
                </div>

                <!-- Contact -->
                <div class="form-row">
                    <div class="col-md-2">
                        <label for="contact">Contact</label>
                    </div>
                    <div class="col-md-10">
                        <textarea type="text" class="form-control keep-plain-textarea" id="contact" name="contact" rows="2"
                                  placeholder="Số điện thoại / Zalo / Link Facebook / Telegram / discord ..." required></textarea>
                    </div>
                </div>

                <!-- Hide Description -->
                <div class="form-row">
                    <div class="col-md-2">
                        <label for="hide">Hide Description</label>
                    </div>
                    <div class="col-md-10">
                        <textarea type="text" class="form-control " id="hide" name="hide" rows="5" ></textarea>
                    </div>
                </div>

                <!-- Public Status -->
                <div class="form-row">
                    <div class="col-md-2">
                        <label>Public Status</label>
                    </div>
                    <div class="col-md-10 ">
                        <div class="public-status-container">
                            <div class="public-status-option">
                                <input type="radio" id="public" name="publicstatus" value="1" required>
                                <label for="public">Public for everyone</label>
                            </div>
                            <div class="public-status-option">
                                <input type="radio" id="unpublic" name="publicstatus" value="0" required>
                                <label for="unpublic">Hide for those with the link</label>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group text-center">
                    <input type="submit" class="btn btn-primary mx-auto" value="Create">
                    <a href="mysale">   <button type="button" class="btn btn-secondary close-button">Close</button></a>
                </div>
                <script>
                    tinymce.init({
                        selector: 'textarea:not(.keep-plain-textarea)',
                        plugins: 'anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks wordcount checklist mediaembed casechange export formatpainter pageembed linkchecker a11ychecker tinymcespellchecker permanentpen powerpaste advtable advcode editimage advtemplate ai mentions tinycomments tableofcontents footnotes mergetags autocorrect typography inlinecss',
                        toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table mergetags | addcomment showcomments | spellcheckdialog a11ycheck typography | align lineheight | checklist numlist bullist indent outdent | emoticons charmap | removeformat',
                        tinycomments_mode: 'embedded',
                        tinycomments_author: 'Author name',
                        mergetags_list: [
                            {value: 'First.Name', title: 'First Name'},
                            {value: 'Email', title: 'Email'},
                        ],
                        ai_request: (request, respondWith) => respondWith.string(() => Promise.reject("See docs to implement AI Assistant")),
                    });
                </script>
            </form>
        </div>

        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    </body>
</html>
