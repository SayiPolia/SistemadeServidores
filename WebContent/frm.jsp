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
    <title>Reserva de Servidor</title>
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
        <h3 class="text-muted">Servidores disponibles</h3>
        <nav>
          <ul class="nav nav-justified">
            <li><a href="homepage">Inicio</a></li>            
            <li><a href="admincontroller?action=login">Administración</a></li>                        
          </ul>
        </nav>
      </div>
      <br>
     

      <h4><font color="red">${message}</font></h4>
      
      <div class="panel panel-default">
        <div class="panel-heading">
          <h3 class="panel-title"><b>Solicitar reserva de Servidor: ${servidor.getNombre()}</b></h3>
        </div>
       <div class="panel-body">

             <form action="solicitudcontroller" method="post">
             <div class="form-group">
              <label for="nombre">Nombre</label>
              <input type="text" class="form-control" name="nombre" value="" required id="nombre" >
            </div>                   
            <div class="form-group">
              <label for="email">Email</label>
              <input type="email" class="form-control" name="email" value="" required id="email" >
            </div>                   
            <div class="form-group">
              <label for="telefono">Teléfono</label>
              <input type="text" class="form-control" name="telefono" value="" required id="telefono">
            </div>                   
            <div class="form-group">
              <label for="direccion">Ubicación</label>
              <input type="text" class="form-control" name="direccion" value="" required id="direccion">
            </div>                                          
                       
              <input type="hidden" class="form-control" name="idservidor" value="${servidor.getId()}" required id="idservidor" >
           
            <button type="submit" class="btn btn-default" >Solicitar</button>
          </form>

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
