<%-- 
    Document   : listadoEstudiantes
    Created on : 2 dic. 2023, 14:52:35
    Author     : Windows 10
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_MX" />

<section id="estudiantes">
    <div class="container">
        <div class="row">

            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Estudiantes</h4>
                    </div>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>Nombre</th>
                                <th>Edad</th>
                                <th></th>
                            </tr>

                        </thead>
                        <tbody>

                            <c:forEach var="estudiante" items="${estudiantes}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${estudiante.nombre}  ${estudiante.apellido}</td>

                                    <td><fmt:formatNumber value="${estudiante.edad}"  /> </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=editar&idEstudiante=${estudiante.idEstudiante}"
                                           class ="btn btn-secondary">
                                            <i class="fas fa-angle-double-right"></i> Editar </a>  
                                    </td>    
                                </tr>               
                            </c:forEach>

                        </tbody>
                    </table>
                </div>

            </div>
            <!-- Totales -->
            <div class="col-md-3">


                <div class="card text-center bg-success text-white mb-3">
                    <div class="card-body">
                        <h3>Total Estudiantes</h3>
                        <h4 class="display-4">
                            <i class="fas fa-graduation-cap  "> </i>${totalEstudiantes}

                        </h4>
                    </div>
                </div>
            </div>

        </div>



    </div>    
</div>


</section>
<jsp:include page="agregarEstudiante.jsp" />