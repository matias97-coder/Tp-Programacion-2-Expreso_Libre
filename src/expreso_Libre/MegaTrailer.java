package expreso_Libre;


public class MegaTrailer extends Transporte { //MegaTrailer HEREDA de transporte.

	private double costoKm;
	private double segCarga;
	private double costoFijo;
	private double costoComida;


	//Constructor de Flete.
	
	public MegaTrailer() {}
	public MegaTrailer(String matricula, double cargaMax, double capacidad, boolean tieneRefrigeracion, double costoKm,
			double segCarga, double costoFijo, double costoComida) {
		
		super(matricula, cargaMax, capacidad,tieneRefrigeracion);
		this.costoKm=costoKm;
		this.segCarga = segCarga;
		this.costoFijo = costoFijo;
		this.costoComida = costoComida;

	}

	/*-----------toString de MegaTrailer---------------*/
	@Override
	public String toString() {
		StringBuilder st= new StringBuilder();
	
		st.append ("\n") .append(" * MegaTrailer: ") .append ("\n") .append ("	Tiene refrigeracion: ") .append (tieneRefrigeracion()) 
		.append ("\n") .append ("	Su costo por kilometro es: $") .append (costoKm) .append ("\n") .append ("	Tiene seguro de carga: $")
		.append (segCarga) .append  ("\n") .append ("	Su costo fijo es de: $") .append (costoFijo) .append  ("\n") .append ("	Su costo de comida es: $") .append (costoComida)
		 .append  ("\n");
		
		
		
		return st.toString();
	}

	
	/*----------- Metodos abstractos a implementar ---------------*/
	
	@Override
	public  double consultarTarifa (double cantKm) { // SOBRECARGA O SOBREESCRITURA
		return cantKm*costoKm+segCarga + costoComida +costoFijo;
	}
	


	@Override
	public boolean asignarDestinoTransporte(Viaje dest) {
		return dest.getKm()>500;
	}
	

	
	
	@Override
	public String tipoTransporte() {
		return "Mega Trailer";
	}
		
}

	//---------------------------------------------------------------- FIN CLASE MEGATRAILER ----------------------------------------------------------------//	
	

