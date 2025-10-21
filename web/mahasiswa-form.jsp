<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Universitas Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="https://www.javaguides.net" class="navbar-brand"> Universitas App </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Mahasiswa</a></li>
                    </ul>
                </nav>
            </header>
            <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${mahasiswa != null}">
                            <form action="<%=request.getContextPath()%>/update" method="post">
                        </c:if>
                        <c:if test="${mahasiswa == null}">
                            <form action="<%=request.getContextPath()%>/insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${mahasiswa != null}">
                                    Edit Mahasiswa
                                </c:if>
                                <c:if test="${mahasiswa == null}">
                                    Add New Mahasiswa
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${mahasiswa != null}">
                            <input type="hidden" name="id" value="<c:out value='${mahasiswa.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Mahasiwa NIM</label> <input type="text" value="<c:out value='${mahasiswa.nim}' />" class="form-control" name="nim" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Mahasiswa Nama</label> <input type="text" value="<c:out value='${mahasiswa.nama}' />" class="form-control" name="nama">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Mahasiswa Semester</label> <input type="text" value="<c:out value='${mahasiswa.semester}' />" class="form-control" name="semester">
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>Mahasiswa IPK</label> <input type="text" value="<c:out value='${mahasiswa.ipk}' />" class="form-control" name="ipk">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>