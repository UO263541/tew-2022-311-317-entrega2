/**
 * 
 */
$(function() {
	 //Creamos el modelo con los datos y la conexión al servicio web.
	 var model = new Model();
	 model.load();
}) 


//Clase que contiene el Modelo de la aplicación.  
function Model(){ 
	//Lista de pisos.
	this.tbPisos = null;
	this.tbPisosPublic = null;
	this.IdArray = [];
	
	//Carga los datos del servicio sobreescribiendo el dato this.tbpisos.
	this.load = function() {
		this.tbPisos = PisosServicesRs.getPisos();
		this.tbPisosPublic = PisosServicesRs.getPisos();
		this.IdArray = [];
	}
	
	
	//ALTA DE PISO
	this.add = function(piso) {
		PisosServicesRs.savePiso({//Servicio de alta de piso
			$entity : piso,
			$contentType : "application/json"
		}); 
		this.load();//Actualizamos la lista de pisos
		location="indexAgente.html"
	}
	
	
	//BORRAR UN PISO
	this.remove = function(id_piso) {
		PisosServicesRs.deletePiso({// Servicio de borrado de piso
			id : id_piso
		});
	}
	
	//ACTUALIZAR INFORMACIÓN DE UN PISO
	this.edit = function(piso) {
		PisosServicesRs.updatePiso({// Servicio de update piso
			$entity : piso,
			$contentType : "application/json"
		});
		this.load();//Actualizamos la lista de pisos
	} 
	
	
	//BUSCAR PISO POR ID
	this.find = function(id_piso) {
		function checkPiso(obj) {
			return obj.id == id_piso;
		}
		// Buscamos un piso cuyo id coincida con el que se pasa por parámetro
		var piso = this.tbPisos.find(checkPiso);
		return piso;//Devolvemos el piso encontrado
	}
}

  
  
//Clase que contiene la gestión de la capa Vista 
  function View(){ 
  	this.list = function(lista) { 
  	    $("#ListadoPisos").html(""); 
  	    $("#ListadoPisos").html( "<thead>" + "<tr>" + "<th></th>"  
  	      + "<th>ID</th>" + "<th>Agente</th>" + "<th>Precio</th>" 
  	      + "<th>Dirección</th>" + "<th>Ciudad</th>" + "<th>Año</th>"
  	      + "<th>Estado</th>"+ "<th>Dirección</th>" 
  	      + "<th>Imagen</th>" + "</tr>" 
  	      + "</thead>" + "<tbody>" + "</tbody>"); 
  	    for ( var i in lista) {
  	    	
  	      //Obtenemos el id del agente actual
  	      var idAgente = localStorage.getItem('agente');
  	      //Comprobamos que el agente en sesión concuerde con el del piso
  	      var piso = lista[i]; 
  	      if(piso.idAgente == idAgente){
  	    	  //Si el piso tiene como idAgente el mismo id que el agente en sesión, lo añadimos a la tabla
  	    	$("#ListadoPisos tbody").append("<tr> <td>"   
  	  	  	    + "<td>" + piso.id + "</td>" 
  	  	        + "<td>" + piso.idAgente + "</td>" 
  	  	        + "<td>" + piso.precio + "</td>"
  	  	        + "<td>" + piso.direccion 
  	  	        + "</td>" + "<td>" + piso.ciudad + "</td>"
  	  	        + "<td>" + piso.anio 
  	  	        + "<td>" + piso.estado 
  	  	        + "<td>" + piso.foto 
	  	  	    + "<img src='icons/editar.png' class='btnEdit' style='height: 20px; width: auto; margin: 5px; cursor: pointer;'/>" 
	  	      	+ "<img src='icons/eliminar.png' class='btnDelete' style='height: 20px; width: auto; margin: 5px; cursor: pointer;'/> </td>"
  	  	        +"</td></tr>");; 
  	      }
  	    } 
  	  }
  	
  	this.listCliente = function(lista) { 
  	    $("#ListadoPisosCliente").html(""); 
  	    $("#ListadoPisosCliente").html( "<thead>" + "<tr>" + "<th></th>"  
  	      + "<th>ID</th>" + "<th>Agente</th>" + "<th>Precio</th>" 
  	      + "<th>Dirección</th>" + "<th>Ciudad</th>" + "<th>Año</th>"
  	      + "<th>Estado</th>"+ "<th>Dirección</th>" 
  	      + "<th>Imagen</th>" + "</tr>" 
  	      + "</thead>" + "<tbody>" + "</tbody>"); 
  	    for ( var i in lista) { 
  	      var piso = lista[i]; 
  	      $("#ListadoPisosCliente tbody").append("<tr> <td>"   
	  	    + "<td>" + piso.id + "</td>" 
	        + "<td>" + piso.idAgente + "</td>" 
	        + "<td>" + piso.precio + "</td>"
	        + "<td>" + piso.direccion 
	        + "</td>" + "<td>" + piso.ciudad + "</td>"
	        + "<td>" + piso.anio 
	        + "<td>" + piso.estado 
	        + "<td>" + piso.foto 
	        +"</td></tr>");; 
  	    } 
  	  }
  	
  	// CARGAR PISO DEL FORMULARIO
  	this.loadPisoFromForm = function () {
		var piso = {
				id : $("#id").val(),
				idAgente: localStorage.getItem('agente'),
				precio : $("#precio").val(),
				direccion : $("#direccion").val(),
				ciudad : $("#ciudad").val(),
				anio : $("#anio").val(),
				estado : $("#estado").val(),
				foto: $("#foto").val(),
		};
		return piso;
	}
  	
  	
  	// CARGAR DATOS DE UN PISO Y PONERLOS EN EL FORMULARIO
  	this.loadPisoInForm = function (piso) {
		$("#id").val(piso.id);
		$("#idAgente").val(localStorage.getItem('agente'));
		$("#precio").val(piso.precio);
		$("#direccion").val(piso.direccion);
		$("#ciudad").val(piso.ciudad);
		$("#anio").val(piso.anio);
		$("#estado").val(piso.estado);
		$("#foto").val(piso.foto);
	}
	
  	
  	// OBTENER EL ID DEL PISO
	this.getIdPiso = function(celda) {
		// Accedemos a la fila que está por encima de esta celda
		// (closest('tr'))y despues obtenemos todas las celdas de esa fila
		// (find('tr')) y
		// nos quedamos con la segunda (get(1)) que es la contiene el "id" del
		// piso.
		var id_piso = parseInt(celda.closest('tr').find('td').get(1).innerHTML);
		return id_piso;
	}
  	
  	
  	// FILTRAR LA TABLA DE PISOS EN FUNCIÖN DE LA CIUDAD
  	this.filtraPorCiudad = function(lista, ciudad){
		//Creamos la cabecera de la tabla
  		$("#tbPiso").html(""); 
		$("#tbPiso").html( "<thead>" + "<tr>" + "<th></th>"
				+ "<th>id</th>" + "<th>idAgente</th>" + "<th>precio</th>" + "<th>direccion</th>"
				+ "<th>ciudad</th>" + "<th>Anyo</th>" + "<th>estado</th>"+ "<th>foto</th>" +"</tr>"
				+ "</thead>" + "<tbody>" + "</tbody>");
		
		//Recorremos el array de pisos en busca de una coincidencia de ciudad
		for ( var i in lista) {
			var piso = lista[i];
			if(piso.ciudad.includes(ciudad)){
				//En caso de que la ciudad coincida con la suministrada, lo añadimos a la tabla
				$("#ListadoPisos tbody").append("<tr> <td>"   
				  	    + "<td>" + piso.id + "</td>" 
				        + "<td>" + piso.idAgente + "</td>" 
				        + "<td>" + piso.precio + "</td>"
				        + "<td>" + piso.direccion 
				        + "</td>" + "<td>" + piso.ciudad + "</td>"
				        + "<td>" + piso.anio 
				        + "<td>" + piso.estado 
				        + "<td>" + piso.foto 
				        + "<img src='icons/editar.png' class='btnEdit'/>" 
			  	      	+ "<img src='icons/eliminar.png' class='btnDelete'/> </td>"
				        +"</td></tr>");; 
			}
		
		}
  	}
  };
  
  
  function Controller(varmodel, varview) { 
	  // Definimos una copia de this para evitar el conflicto con el this (del 
	  // objeto que recibe el evento) 
	  // en los manejadores de eventos 
	  var that = this; 
	  // referencia al modelo 
	  this.model = varmodel; 
	  // refefencia a la vista 
	  this.view = varview; 
	 
	  // Funcion de inicialización para cargar modelo y vista, definición de manejadores 
	  this.init = function() { 
	 
	    // Cargamos la lista de pisos del servicio 
	    this.model.load(); 
	    
	    // Repintamos la lista de pisos. 
	    this.view.list(this.model.tbPisos); 
	    this.view.listCliente(this.model.tbPisosPublic); 
	    console.log("Pinto Lista")
	 
	    
	    
	    
	    // MANEJADORES DE EVENTOS 
	 // Manejador del botón submit del formulario de Alta y Edición 
	    $("#formPiso").submit(
	    // Método que gestiona el evento de clickar el botón submit del 
	    // formulario 
	    function(event) { 
	    	console.log('Formulario sdkjfha')
	      // Si el piso cargado en el formulario tiene ID se invoca al 
	      // método de edición 
    	  // sino se invoca al método de alta. 
    	  piso = that.view.loadPisoFromForm(); 
    	  if ($("#id").val() == "") { 
    	    that.model.add(piso); 
    	  } else { 
    	    that.model.edit(piso); 
    	  } 
    	  // Volvemos a listar los pisos restantes para que aparezca el 
    	  // nuevo 
    	  that.view.list(that.model.tbPisos); 
    	  location="indexAgente.html";
    	});
	    
	    
	    
	 // Manejador del enlace de edición de un piso en la tabla 
	    $("#ListadoPisos .btnEdit").click( 
	    		
	    // Manejador del evento click en .btnEdit
	    function(event) { 
	    	
	    	//Mostramos tabla de editar
	    	document.getElementById('TablaEdit').removeAttribute("style");
	    	console.log(document.getElementById('TablaEdit'))
	    	
	    	//Subimos hasta arriba para visualizar la tabla
	    	window.scrollTo(0,0);
	    	
	      // Obtenemos el id del piso seleccionado
	    	console.log($(this))
	    	var fila = $(this).parent()
	    	var idPiso = that.view.getIdPiso($(this)); 
	      
	      console.log('Que piso', idPiso)
	      
	      // Obtenemos el piso con el id 
	      var piso = that.model.find(idPiso); 
	      // Cargamos el formulario con los datos del piso seleccionado para editar 
	      that.view.loadPisoInForm(piso); 
	    });
	    
	   
	    
	    
	    
	    // CERRAR SESIÓN DE USUARIO
		$("#logout").click(
			function(event){
				event.preventDefault();
				event.stopPropagation();
				localStorage.removeItem('agente');
				location="login.html";
			})
	    
	  } 
	};

	
	
	
  
  
	$(function() { 
	  // Creamos el modelo con los datos y la conexión al servicio web. 
	  var model = new Model(); 
	  // Creamos la vista que incluye acceso al modelo.
	  var view = new View(); 
	  // Creamos el controlador 
	  var control = new Controller(model, view); 
	 
	  // Iniciamos la aplicación 
	  control.init(); 
	});
  
  