package expreso_Libre;



public class Flete extends Transporte  { //Flete HEREDA de transporte.
	
	private double costoKm;
	private int cantAcompaniantes;
	private double costoPorAcompaniante;


	//Constructor de Flete.
	
	public Flete() {}
	public Flete(String matricula, double cargaMax, double capacidad,
			 double costoKm, int cantAcompaniantes, double costoPorAcompaniante) {
		super(matricula, cargaMax, capacidad,false);
		this.costoKm=costoKm;
		this.cantAcompaniantes = cantAcompaniantes;
		this.costoPorAcompaniante = costoPorAcompaniante;

	}
	
	
	/*-----------toString de Fletes---------------*/
	@Override
	public String toString() {
		StringBuilder st= new StringBuilder();
	
		st.append ("\n") .append (" * Fletes: ") .append("\n") .append ("	Tiene refrigeracion: ") .append (tieneRefrigeracion())
		.append ("\n") .append ("	Su costo por kilometro es: $") .append (costoKm) .append("\n") .append ("	Cantidad de acompañantes: ")
		.append (cantAcompaniantes) .append ("\n") .append ("	Su costo por acompañante es: $") 
		.append (costoPorAcompaniante) .append ("\n");
		
		return st.toString();
		}
	
	
	/*----------- Metodos abstractos a implementar ---------------*/
	
	@Override
	public double consultarTarifa(double cantKm) { // SOBRECARGA O SOBREESCRITURA
		return (cantKm*costoKm) + (cantAcompaniantes * costoPorAcompaniante);
	}


	@Override
	public boolean asignarDestinoTransporte(Viaje dest) {
		return dest.getKm() <150;
	}
	
	@Override
	public String tipoTransporte() {
		return "Flete";
	}
	

	//---------------------------------------------------------------- FIN CLASE FLETE ----------------------------------------------------------------//	
	
}


