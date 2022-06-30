package expreso_Libre;

public class Paquete {

	private String destino;
	private double volumen;
	private double peso;
	private boolean necesitaRefrigeracion;
	
	//Constructor de Paquetes.
	
	public Paquete(String destino, double peso, double vol, boolean necesitaRefrigeracion) {
		this.destino = destino;
		this.peso = peso;
		this.volumen = vol;
		this.necesitaRefrigeracion = necesitaRefrigeracion;
	}
	
	
	/*----------- Metodos ---------------*/
	
	public String getDestino() {
        return this.destino;
    	}
	
	public double getVol() {
		return this.volumen;
	    }
	
	 public double getPeso() {
		 return this.peso;
	    }

	 public boolean necesitaRefrigeracion() {
		 return this.necesitaRefrigeracion;
	    }
	 
	 public boolean mismoDest(String dest) {
		 return getDestino().equals(dest);
	 }


	@Override
	public boolean equals(Object obj) {

		if (obj == null)
			return false;
		Paquete otro = (Paquete) obj;
		return this.getDestino().equals(otro.getDestino()) && this.getVol()==otro.getVol() &&
				this.getPeso()==otro.getPeso() && this.necesitaRefrigeracion()==otro.necesitaRefrigeracion;
	}
	
	//---------------------------------------------------------------- FIN CLASE PAQUETES ----------------------------------------------------------------//	
	
}