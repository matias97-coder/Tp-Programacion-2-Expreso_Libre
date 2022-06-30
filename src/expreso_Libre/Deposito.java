package expreso_Libre;

import java.util.LinkedList;

public class Deposito {
	
	private boolean conRefrigeracion;
	private double capacidadDeposito;
	public LinkedList<Paquete> Lpaquetes;	
	
	//Constructor de los depositos.
	public Deposito(boolean conRefrigeracion,double capacidadDeposito) {	
		this.conRefrigeracion = conRefrigeracion;
		this.capacidadDeposito = capacidadDeposito;
		this.Lpaquetes= new LinkedList <Paquete>();
	}
	
	/*-----------toString de depositos---------------*/
	@Override
	public String toString() {
		StringBuilder st= new StringBuilder();
		st.append ("\n").append ("		Deposito con capacidad de refrigeracion: ") .append (conRefrigeracion) 
		.append (" 	||") .append ("Capacidad máxima del mismo: ") .append (capacidadDeposito);
		
		
		return st.toString();
	}

	/*----------- Metodos ---------------*/
	
	public boolean getRefrigferacion() {
		return this.conRefrigeracion;
	}
	
	public double getCapacidadDeposito() {
		return this.capacidadDeposito;
	}
	
	public double setCapacidadDeposito(double vol) {
		this.capacidadDeposito=this.capacidadDeposito-vol;
		return this.capacidadDeposito;
	}
	
	public void agregarPaquetesAlDeposito(Paquete paquete) {
		if (this.capacidadDeposito < paquete.getVol()) { 								//Si la capacidad actual del depo no nos permite incorporarlo
			throw new RuntimeException("No se pudo agregar por falta de capacidad");
        }
		Lpaquetes.add(paquete);	
		setCapacidadDeposito(paquete.getVol()); 			
    }
	
	public boolean chequearPaquete(Paquete paquete) {
		return this.getRefrigferacion() == paquete.necesitaRefrigeracion() && paquete.getVol()<this.getCapacidadDeposito();
	}
	


}	
	//---------------------------------------------------------------- FIN CLASE DEPOSITOS ----------------------------------------------------------------//	

