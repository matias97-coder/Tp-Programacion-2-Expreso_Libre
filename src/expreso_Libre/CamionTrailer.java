package expreso_Libre;



public class CamionTrailer extends Transporte  { //Camion trailer HEREDA de transporte.
	

	private double segCarga;
	private double costoKm;

	
	/*-----------toString de CamionTrailer---------------*/
	@Override
	public String toString() {
		
		StringBuilder st= new StringBuilder();
		
		st.append ("\n") .append (" * Camion Trailer: ") .append ("\n") .append ("	Tiene refrigeracion: ")
		.append (tieneRefrigeracion()) .append ("\n") .append ("	Su seguro de carga es:$") .append (segCarga)
		.append ("\n") .append ("	Su costo por kilometro es:$") .append (costoKm) .append("\n");

		
		return st.toString();
	}
	
	//Constructor del CamionTrailer.
	
	public CamionTrailer(String matricula, double cargaMax, double capacidad, boolean tieneRefrigeracion,double costoKm,
		double segCarga) {
		super(matricula, cargaMax, capacidad,tieneRefrigeracion);
		this.costoKm=costoKm;
		this.segCarga = segCarga;

	}
	
	
	/*----------- Metodos abstractos a implementar ---------------*/
	



	@Override
	public double consultarTarifa(double cantKm) { // SOBRECARGA O SOBREESCRITURA
		return cantKm*costoKm+segCarga;
	}


	
	@Override
	public boolean asignarDestinoTransporte(Viaje dest) {
		return  dest.getKm()<500;
	}
	
	
	@Override
	public String tipoTransporte() {
		return "Camion Trailer";
	}
	
	
}	

	//---------------------------------------------------------------- FIN CLASE CAMIONTRAILER ----------------------------------------------------------------//


