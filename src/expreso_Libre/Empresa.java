package expreso_Libre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;



public class Empresa {
	
	private String cuit;
	private String nombre;
	private double capacidadMaxDepositos;

	/*-----------Estructuras de datos---------------*/
	
    private HashMap<String,Transporte> LTransportes; 	//Lista de transportes --> Clave:matricula, Valor: tipoTransporte.
    private LinkedList<Viaje> LDestinos; 				//Destinos cargados por la empresa.
    private ArrayList<Deposito> depositos;				//Depositos de la empresa.

    /*------------------------------------------------*/
    
    //Constructor de la empresa.
    
	public Empresa(String cuit, String nombre, double capacidadMaxDepositos) {
		this.cuit = cuit;
		this.nombre = nombre;
		this.capacidadMaxDepositos = capacidadMaxDepositos;
		this.LTransportes= new HashMap<String,Transporte>();
		this.LDestinos= new LinkedList <Viaje>();
		this.depositos = new ArrayList <Deposito>();
		this.depositos.add(new Deposito (true,capacidadMaxDepositos));	//Deposito apto para refrigeracion.
		this.depositos.add(new Deposito (false,capacidadMaxDepositos));	//Deposito no apto para refrigeracion.
	}
	
	/*--------------------toString de empresa--------------------*/
	@Override
	public String toString() {
		
		StringBuilder st= new StringBuilder();
	
		st.append("\n") .append ("Detalles de la Empresa.") .append ("\n") .append (" - Su numero de CUIT es: ")
		 .append (cuit) .append ("\n") .append (" - Su razón social: ") .append (nombre) .append 
		 ("\n") .append(" - Capacidad máxima de sus depositos: ") .append (capacidadMaxDepositos)
		 .append("\n") .append (" - La flota de la empresa se compone por: ") .append("\n")
		 .append (LTransportes.toString().replace("[", "").replace("]", "").replace(",", "").replace("{", "").replace("}", "").replace("=", "--> Detalles del dominio: ")) 
		 .append ("\n") .append (" - La lista de destinos: ") .append (LDestinos.toString().replace("]", "").replace("[", "").replace(",", " ||")) .append ("\n")
		 .append (" - Sus depositos son: ") .append (depositos.toString().replace("[", "").replace("]", "").replace(",", "")) .append ("\n");
		
		return st.toString();
	}
	

	//------------------METODOS A IMPLEMENTAR--------------------//
	
	 //------------------ Agregar destinos --------------------//
	
	// Incorpora un nuevo destino y su distancia en km.
	// Es requisito previo, para poder asignar un destino a un transporte.
	// Si ya existe el destino se debe generar una excepción.
	
	public void agregarDestino (String destino,int km) {
		
		Viaje nuevoDestino= new Viaje (destino,km);	
		
		if (LDestinos.isEmpty())
			LDestinos.add(nuevoDestino);
		else {
			for (Viaje dest:LDestinos) {
				if ((dest.getDestino().equals(nuevoDestino.getDestino()))) {
					throw new RuntimeException ("El destino: "+nuevoDestino.getDestino()+"|"
							+ nuevoDestino.getKm()+"km, ya existe");
					}
				}
			LDestinos.add(nuevoDestino);
		}
	}

	//--------------------Agregar transportes ----------------------------//	
	
	// Los siguientes métodos agregan los tres tipos de transportes a 
	//la empresa, cada uno con sus atributos correspondientes. 
	// La matrícula funciona como identificador del transporte.
	
	public void agregarTrailer(String matricula, double cargaMax, 
			double capacidad, boolean tieneRefrigeracion, double costoKm, double segCarga){
	
		Transporte trailer=new CamionTrailer(matricula,cargaMax, capacidad,tieneRefrigeracion,costoKm, segCarga);
		
		LTransportes.put(matricula, trailer);	
	}
	
	public void agregarMegaTrailer(String matricula, double cargaMax, double capacidad,
			 boolean tieneRefrigeracion, double costoKm, double segCarga, double costoFijo, double costoComida){
		
		Transporte megatrailer= new MegaTrailer(matricula, cargaMax, capacidad, tieneRefrigeracion, costoKm,
				segCarga, costoFijo, costoComida);
		
		LTransportes.put(matricula, megatrailer);		
	}
	
	public void agregarFlete(String matricula, double cargaMax, double capacidad,
			 double costoKm, int cantAcompaniantes, double costoPorAcompaniante){
		
		Transporte flete= new Flete(matricula, cargaMax, capacidad,costoKm,cantAcompaniantes, 
				costoPorAcompaniante);
		
		LTransportes.put(matricula, flete);
	}
	
	//--------------------Asignacion de destinos --------------------//
	
	// Se asigna un destino a un transporte dada su matrícula
	//(el destino debe haber sido agregado previamente, con el método agregarDestino). 
	// Si el destino no está registrado, se debe generar una excepción.
	
	public void asignarDestino(String matricula, String destino)  {
		
			boolean hayDestino=false;
			
			Transporte transporte=this.LTransportes.get(matricula);
			
			if (transporte.tienePaquetes())
					throw new RuntimeException("el transporte "+transporte.tipoTransporte() +" ya tiene paquetes cargados"); 
			for (Viaje dest:LDestinos) {
				if (dest.getDestino().equals(destino)) {
				 if(transporte.asignarDestinoTransporte(dest)) {
		
					    transporte.setDestinoAsignado(destino); // asigno un destino al transporte
						hayDestino=true; 
					}
				}
			}
			if (!hayDestino)
				throw new RuntimeException("El destino ingresado "+destino+ " no existe"); 
		}
	
	//-------------------- Agregar paquetes --------------------//
	
	// Se incorpora un paquete a algún depósito de la empresa.
	// Devuelve verdadero si se pudo incorporar, es decir, 
	// Si el depósito acorde al paquete tiene suficiente espacio disponible.
	
	public boolean incorporarPaquete(String destino, double peso, double volumen,boolean necesitaRefrigeracion) {
		
		Paquete paquete = new Paquete (destino, peso, volumen, necesitaRefrigeracion);
		
		boolean sePudoIncorporar=false;
		
		for (Deposito dep : this.depositos) {
            if (dep.chequearPaquete(paquete)) {    
            	dep.agregarPaquetesAlDeposito(paquete);
            	sePudoIncorporar=true;   	
            }        	 
		}                      
		return sePudoIncorporar;
	}
	
	//--------------------Cargar un transporte --------------------//
	
	// Dado un ID de un transporte se pide cargarlo con toda la mercadería 
	//posible, de acuerdo al destino del transporte. 
	//No se debe permitir la carga si está en viaje o si no tiene asignado un destino. 
	// Utilizar el depósito acorde para cargarlo. 
	// Devuelve un double con el volumen de los paquetes subidos al transporte.
	
	public double cargarTransporte(String matricula) {
		
		Transporte transporte= LTransportes.get(matricula);
		String destAsignado= transporte.getDestinoAsignado();
		double volumenSubidos=0;
		
		if (transporte.estaEnViaje()) {// estaEnViaje=true, entonces tiene un viaje iniciado
			throw new RuntimeException("No se puede realizar la carga:"+transporte.tipoTransporte()+" ya inicio un viaje"); 
			}
		if (destAsignado==null) {
			throw new RuntimeException("El transporte:"+transporte.tipoTransporte()+" no tiene ninguna destino asignado"); 
			}		
		
		for (Deposito dep: depositos) {
				Iterator<Paquete> it = dep.Lpaquetes.iterator();
					while(it.hasNext() ) {
						Paquete p = it.next(); 
							if (p.mismoDest(destAsignado) && transporte.actoParaCarga(dep, p)) {							
							transporte.cargarPaqueteTransporte(p);
							volumenSubidos = transporte.volumenCargado(p);
							dep.setCapacidadDeposito(-p.getVol());//Vuelvo a reincoporar vol a deposito
							it.remove(); // una vez que lo encuentra, lo elimina del deposito
						}		
					}
				
			}
		return volumenSubidos;
	}
	
	//-------------------- Iniciar un viaje --------------------//	
	
	// Inicia el viaje del transporte identificado por la matrícula pasada por parámetro. 
	// En caso de no tener mercadería cargada o de ya estar en viaje se genera una excepción.
	
	public void iniciarViaje(String matricula) {
		
		Transporte transporte= LTransportes.get(matricula);
		
		String destinoAsignado= transporte.getDestinoAsignado();
		
		if (transporte.estaEnViaje()) 
			throw new RuntimeException("El transporte:"+transporte.tipoTransporte()+" ya inicio un viaje"); 		
		if(!(transporte.tienePaquetes()))
			throw new RuntimeException("El transporte:"+transporte.tipoTransporte()+" no puede iniciar el viaje, porque no tiene paquetes cargados"); 		
		if (destinoAsignado==null) {
			throw new RuntimeException("El transporte:"+transporte.tipoTransporte()+" no tiene ninguna destino asignado"); 
		}

		transporte.cambiarViaje();	// cambia estaEnViaje()=true.


	}
	
	//--------------------Finaliza un viaje --------------------//	
	
	// Finaliza el viaje del transporte identificado por la matrícula pasada por parámetro.
	// El transporte vacía su carga y blanquea su destino, para poder ser vuelto a utilizar en otro viaje. 
	// Genera excepción si no está actualmente en viaje.
	
	public void finalizarViaje(String matricula) {
		
		Transporte transporte= LTransportes.get(matricula);	
		
		if(transporte.estaEnViaje()==false) // si no esta en viaje  
			throw new RuntimeException("El transporte actualmente no se encuentra en viaje"); 
				
		transporte.actualizarDatosDelTransporte();// reincopora su carga y vacia todos sus paquetes y blanquea su destino
												  // y pasa a estar disponible para un prox viaje
	
	}
	
	//-------------------- Obtener costo de viaje --------------------//
	
	// Obtiene el costo de viaje del transporte identificado por la matrícula pasada por parametro. 
	// Genera excepción si el transporte no está en viaje.
	
	public double obtenerCostoViaje(String matricula) {
		
		Transporte transporte= LTransportes.get(matricula);
		String destinoAsignado=transporte.getDestinoAsignado();
		double cantKm=0;
		
		if(transporte.estaEnViaje()==false) 
			throw new RuntimeException("El transporte actualmente no se encuentra en viaje"+transporte.tipoTransporte()); 		
		
		for (Viaje dest:LDestinos) {
			if (dest.getDestino().equals(destinoAsignado))
				cantKm=dest.getKm();
		}
		return transporte.consultarTarifa(cantKm); 
	}
	
	//-------------------- Obtener transporte igual --------------------//	
	
	// Busca si hay algún transporte igual en tipo, destino y carga. 
	// En caso de que no se encuentre ninguno, se debe devolver null. 
	
	public String obtenerTransporteIgual(String matricula) {
		
		String matriculaIgual = null;
		Transporte trans2=LTransportes.get(matricula);

		for(String key : LTransportes.keySet()) { 	//Iteramos la lista de transporte con las keys --> matriculas.
			
			Transporte trans1=LTransportes.get(key);

			if(trans1.equals(trans2)){					//Y comparamos con el .get de la matricula que nos pasan por parametro.
				matriculaIgual = trans1.getMatricula(); //Si entra al if es porque hay un igual, entonces cargamos la matricula
				return matriculaIgual;					//del objetoTransporte en la variable a retornar.
			}										  						
			
		}
		
		System.out.println("No existe un transporte igual a " + matricula); //Si no existe un igual, se informa y se pasa la matricula.
		return null;														//Retornamos null ya que nunca se sobreescribe.
	}

}

	//-------------------- FIN CLASE EMPRESA --------------------//
	
