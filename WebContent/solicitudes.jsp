<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">    
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="sayipolia" content="">
    <link rel="icon" href="favicon.ico">
    <title>Lista de todas las Solicitudes</title>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/justified-nav.css" rel="stylesheet">

  </head>

  <body>

    <div class="container">

      <!-- The justified navigation menu is meant for single line per list item.
           Multiple lines will require custom code not provided by Bootstrap. -->
      <div class="masthead">
        <h3 class="text-muted">Administración</h3>
        <nav>
          <ul class="nav nav-justified">
            <li><a href="admincontroller?action=crear">Alta de Servidor</a></li>
            <li><a href="servidorcontroller?action=lista">Servidores</a></li>             
            <li><a href="solicitudcontroller?action=solicitudes">Solicitudes</a></li>                        
            <li><a href="admincontroller?action=logout">Salir</a></li>            
          </ul>
        </nav>
      </div>
      <br>

      <div class="panel panel-default">
        <div class="panel-heading">
          <h3 class="panel-title"><b>Lista de solicitudes recibidas</b></h3>
        </div>
        <div class="panel-body">
          <table class="table table-striped">
            <thead>
              <tr>
                <th class="left">Fecha</th>
                <th>Nombre</th>
                <th>Email</th>                
                <th>Teléfono</th>
                <th>Ubicación</th>
                <th>Servidor solicitado</th>
                
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${solicitudes}" var="solicitud" varStatus="status">
                <tr>
                  <td class="left">${solicitud.getFecha()}</td>
                  <td>${solicitud.getNombre()}</td>
                  <td>${solicitud.getEmail() }</td>
                  <td>${solicitud.getTel()}</td>
                  <td>${solicitud.getDireccion()}</td>
                  <td>${solicitud.getServidor().getNombre()}</td>
                                                    
                </tr>
              </c:forEach>                      
            </tbody>           
          </table>
        </div>
      </div>

      <!-- Site footer -->
     <footer class="footer">
      <div class="container text-center">
         <p>&copy; SAYI POLIA 2018.</p>
         </div>
      </footer>

    </div> <!-- /container -->

  </body>
</html>
