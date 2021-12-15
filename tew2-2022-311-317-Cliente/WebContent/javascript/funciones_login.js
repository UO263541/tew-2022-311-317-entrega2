/**
 * 
 */


function Model() {
	//Lista de agentes.
	 this.LAgentes= null;
	 
	
	//Carga los datos del servicio sobreescribiendo el dato this.LAgentes.
	this.load = function() {
		this.LAgentes = AgentesServiceRs.getAgentes();
	}

	this.comprueba = function (user, pass) {
 	   for(var a in this.LAgentes){
 		   var agente = this.LAgentes[a];
 		   console.log("AQUI ESTOY")
 		   console.log(agente.login)
 		   console.log(agente.passwd)
            if((agente.login == user) && (agente.passwd == pass)){
            	localStorage.setItem('agente', agente.id);
	    		console.log("Retorno")
	    		return true;
           
            }
            
            }
        }
	        
	  

};


function Controller(varmodel) {
	var that = this;
	this.model = varmodel;
	this.init = function() {
		// Cargamos la lista de agentes del servicio
		
		this.model.load();
		$("#loginForm").submit(function(event){
			
			//Evitamos que el formulario ejecute la acción predeterminada 
			event.preventDefault();
			
			//Cogemos los valores del formulario y comprobamos que existan en la base de datos
			var user=$("#username").val();
			var pass=$("#passwd").val();
		    var existe = that.model.comprueba(user,pass);
		    
		    
		    //Comprobamos si existe el agente y sus credenciales son correctas
		    if (existe){
		    	location="./indexAgente.html";//Cambiamos la vista si existe
		    	
		    }
		    //Mostramos alerta en caso de error
		    else alert("No se reconoce el agente");  
		});
		}
			
};	

$(function() {
	// Creamos el modelo con los datos y la conexión al servicio web.
	var model = new Model();
	// Creamos el controlador
	var control = new Controller(model);
	// Iniciamos la aplicación
	control.init();
});


