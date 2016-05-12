<%-- 
    Document   : index
    Created on : 10 14, 15, 8:06:04 PM
    Author     : jdomugho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Anime Downloader</title>
        <%@include file="../resources/includes/header.jsp" %>

        <style>
            td > input, th > input{
                text-align: center;
            }
        </style>
    </head>
    <body>
        <%@include file="../resources/includes/sidenavigation.jsp" %>
        <div id="page-content">
            <%@include file="../resources/includes/topnavigation.jsp" %>
            <br>
            <div class="col-md-12 col-sm-12">
                <div class="panel panel-default">
                    <div class="panel-heading">Download URL</div>
                    <div class="panel-body">
                        <div class="form-group col-md-12">
                            <label for="url">URL</label>
                            <input type="text" class="form-control" id="url" placeholder="Video Link">
                        </div>    
                        <div class="form-group col-md-4">
                            <label for="filename">File Name</label>
                            <input type="text" class="form-control" id="filename" placeholder="Episode #">
                        </div>    
                        <div class="form-group col-md-4">
                            <label for="fileextension">File Extension</label>
                            <input type="text" class="form-control" id="fileextension" value=".mp4" placeholder=".mp4 / .swf">
                        </div>    
                        <div class="form-group col-md-4">
                            <label for="proxy">Proxy</label>
                            <input type="text" class="form-control" id="proxy" value="10.10.11.201:3128" placeholder="10.10.11.201:3128">
                        </div>    
                        <div class="form-group col-md-12">
                            <button id="btnSubmit" class="btn btn-primary">SUBMIT</button>
                        </div>    
                    </div>
                </div>
            </div>
            <div class="col-md-12 col-sm-12">
                <div class="panel panel-default">
                    <div class="panel-heading">Downloads</div>
                    <div id="tickets" class="panel-body">
                        <!--                        ${ticketsJson}
                        
                                                <div class="downloadTicket col-md-12 col-sm-12">
                                                    <div class="tckHeader">
                                                        <h5 class="tckStatus">
                                                            Downloading 100 vehicles to USS/RNagoya/20151026
                                                            <small class="pull-right">filename: 21.mp4</small>
                                                        </h5>
                                                    </div>
                                                    <div class="tckHeader">
                                                        <h5>
                                                            <b>42%</b> complete <small>(42/100)</small>                                   
                                                        </h5>
                                                    </div>
                                                    <div class="progress ">
                                                        <div class="progress-bar progress-bar-striped active" 
                                                             role="progressbar" aria-valuenow="42"
                                                             aria-valuemin="0" aria-valuemax="100" style="width: 42%">  
                                                        </div>
                                                    </div>
                                                    <hr>
                                                </div>  -->
                    </div>
                </div>
            </div>
        </div>
        <div id="ticketsJson" style="visibility: hidden">${ticketsJson}</div>
    </body>
    <script>
        $(document).ready(function () {
            var jsonArray = $.parseJSON($("#ticketsJson").html());
            var ticketContent = "";

            $.each(jsonArray, function (i, field) {
                ticketContent += buildView(field);
            });
            $("#tickets").html(ticketContent);
        });

        $('#btnSubmit').click(function () {
            var url = $("#url").val();
            var filename = $("#filename").val();
            var fileExtension = $("#fileextension").val();

            $.post("/AnimeDownloader/runDownloader", {url: url, filename: filename, fileExtension: fileExtension})
                    .done(function (data) {
                        location.reload();
                    });
        });


        function buildView(ticket) {
            var view = "";

            view += "<div class=\"downloadTicket col-md-12 col-sm-12\">";
            view += "<div class=\"tckHeader\">";
            view += "<h5 class=\"tckStatus\">";
            view += ticket.status;
            view += "<small class=\"pull-right\">filename: " + ticket.filename + ticket.fileextension + "</small>";
            view += "</h5>";
            view += "</div>";
            view += "<div class=\"tckHeader\">";
            view += "<h5>";
            view += "<b>" + ticket.percentage + "%</b> complete <small>(" + ticket.downloaded + "/" + ticket.totalfilesize + ")</small>";
            view += "</h5>";
            view += "</div>";
            view += "<div class=\"progress \">";
            view += "<div class=\"progress-bar progress-bar-striped active\"";
            view += "role=\"progressbar\" aria-valuenow=\"" + ticket.percentage + "\"";
            view += "aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: " + ticket.percentage + "%\">";
            view += "</div>";
            view += "</div>";
            view += "<hr>";
            view += "</div>";

            return view;
        }
    </script>
</html>