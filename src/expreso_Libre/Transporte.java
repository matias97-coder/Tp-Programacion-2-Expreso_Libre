package expreso_Libre;

import java.util.LinkedList;

public abstract class Transporte {	//Clase abstracta 
	
	private String matricula;
	private double cargaMax;
	private double capacidad;
	private boolean enViaje=false;
	private LinkedList<Paquete> paquetesTransporte;
	private double kgCargados=0;
	private String destinoAsignado=null;
	private boolean tieneRefri;
	//Constructor de transporte.
	
	public Transporte() {}
	public Transporte(String matricula, double cargaMax, double capacidad,boolean tieneRefri) {
		this.matricula = matricula;
		this.cargaMax = cargaMax;
		this.capacidad = capacidad;
		this.tieneRefri=tieneRefri;
		paquetesTransporte= new LinkedList<Paquete>();
	}	
		
	/*-----------toString de Transportes---------------*/
	
	@Override
	public String toString() {
		
		StringBuilder st= new StringBuilder();

		
		st.append ("\n").append(" Dominio: ") .append(matricula) .append (", cargaMax=") .append (cargaMax ).append (", capacidad=") 
		.append (capacidad).append(", enViaje=") .append (enViaje);
		
		return st.toString();
		
		
	}
	
	/*----------- Metodos ---------------*/
	
	public void setCapacidad(double capacidad) {
		this.capacidad -= capacidad;
	}

	public double getCargaMax() {
		return cargaMax;
	}

	public double getCapacidad() {
		return capacidad;
	}

	public void setCargaMax(double cargaMax) {
		this.cargaMax-= cargaMax;
	}
	
	public String getMatricula() {
        return matricula;
    }
	

	
	public boolean estaEnViaje() { 
		return enViaje;
	}
	
	public void cambiarViaje() {
		if (enViaje==false) {
			enViaje=true;
		}
		else {
			enViaje=false;
		}
	}


	public void actualizarDatosDelTransporte() {
		setCapacidad(-(obtenerVolCompletoPaquetes())); // se vuelve a reincoprar el vol
		setCargaMax(-(obtenerPesoCompletoPaquetes())); // se vuelve a reincoprar el peso
		vaciarCarga();//se blanquea toda la lista de paquetes del transporte
		cambiarViaje();	// cambiarViaje → estaEnVIaje()= false, esta disp para un prox viaje
		setDestinoAsignado(null);// blanquea su destino
	}
	
	
	public String getDestinoAsignado() {
		return destinoAsignado;
	}

	public void setDestinoAsignado(String dest) {
		destinoAsignado= dest;
	}
	
	public boolean tieneRefrigeracion() {
	  return this.tieneRefri;
	}
	 
	/*----------- Metodos abstractos que obliga a implementar ---------------*/

	public abstract boolean asignarDestinoTransporte(Viaje dest);
	
	public abstract double consultarTarifa(double cantKm);
    
	public abstract String tipoTransporte();
	
	public boolean actoParaCarga(Deposito dep,Paquete p){			
		boolean refri=dep.getRefrigferacion()==tieneRefrigeracion();
		boolean tieneEspacioCarga=superoCargaMax(p);

		return  refri && tieneEspacioCarga ;
	}

	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj instanceof Transporte == false)
			return false;
		Transporte otro = (Transporte) obj;
		return  this.tieneMismoPaquete(otro)
				&& this.getDestinoAsignado().equals(otro.getDestinoAsignado())
				&& this.tipoTransporte().equals(otro.tipoTransporte())
				&& this.tieneRefrigeracion()==otro.tieneRefrigeracion();
				
	}
	
/*-------------------------METODO RELACIONADOS A LOS PAQUETES--------------------------------*/
	
	
	
	 public void cargarPaqueteTransporte(Paquete paquete) {
		paquetesTransporte.add(paquete);
		this.setCapacidad(paquete.getVol()); 				//Se le resta el vol actual del trasporte - paquete.
		this.setCargaMax(paquete.getPeso()); 				//Se le resta el peso actual del transporte.
	}
	

	public boolean tienePaquetes() {
		return paquetesTransporte.size()>0;
	}

	public void vaciarCarga() {
		paquetesTransporte.clear();
	}
	

	public double obtenerPesoCompletoPaquetes() {
		double pesoTot=0;
		for (Paquete paquete:paquetesTransporte) {
			 pesoTot+=paquete.getPeso();
		}		
		 return pesoTot;
	}

	public double obtenerVolCompletoPaquetes() {	
	double volTot=0;		
		for (Paquete paquete:paquetesTransporte) {
			volTot+=paquete.getVol();
		}	
		 return volTot;	
	}
	
	private boolean superoCargaMax(Paquete p) {
		return p.getPeso()< this.getCargaMax() && p.getVol()<this.getCapacidad();
		
	}
	
	private boolean tieneMismoPaquete(Transporte trans2) {
		boolean todosIguales=true;
		for (Paquete paq2:trans2.paquetesTransporte){
			todosIguales= todosIguales && tieneUnPaqueteIgual(paq2);	
		}	
		return todosIguales;
	}
	
	private boolean tieneUnPaqueteIgual(Paquete paq2) {// el paq2 corresponde al transporte 2
		boolean unoIgual=false;
		for (Paquete paq1:paquetesTransporte) {// Estoy recorriendo el transporte 1 
			unoIgual= unoIgual || paq1.equals(paq2);		
			}
		
		return unoIgual;
	}

	public double volumenCargado (Paquete p) {
		this.kgCargados+=p.getVol();
		return this.kgCargados;
	}
	
	/*------------------------- FIN METODOS RELACIONADOS A LOS PAQUETES--------------------------------*/
	
	
		
	
}
