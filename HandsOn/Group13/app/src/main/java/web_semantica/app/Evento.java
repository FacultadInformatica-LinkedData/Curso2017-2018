package web_semantica.app;

public class Evento {
	private String objeto;
	private String titulo;
	private String diasSemana;
	private String inicioEvento;
	private String finEvento;
	private String urlEvento;
	private String urlInstalacion;
	private String nombreInstalacion;
	
	public Evento (String objeto) {
		this.objeto = objeto;
		this.diasSemana = "";
	}

	public String getObjeto() {
		return objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDiasSemana() {
		return diasSemana;
	}

	public void setDiasSemana(String diasSemana) {
		this.diasSemana = diasSemana;
	}

	public String getInicioEvento() {
		return inicioEvento;
	}

	public void setInicioEvento(String inicioEvento) {
		this.inicioEvento = inicioEvento;
	}

	public String getFinEvento() {
		return finEvento;
	}

	public void setFinEvento(String finEvento) {
		this.finEvento = finEvento;
	}

	public String getUrlEvento() {
		return urlEvento;
	}

	public void setUrlEvento(String urlEvento) {
		this.urlEvento = urlEvento;
	}

	public String getUrlInstalacion() {
		return urlInstalacion;
	}

	public void setUrlInstalacion(String urlInstalacion) {
		this.urlInstalacion = urlInstalacion;
	}

	public String getNombreInstalacion() {
		return nombreInstalacion;
	}

	public void setNombreInstalacion(String nombreInstalacion) {
		this.nombreInstalacion = nombreInstalacion;
	}

	@Override
	public String toString() {
		return "Evento [objeto=" + objeto + ", titulo=" + titulo + ", diasSemana=" + diasSemana + ", inicioEvento="
				+ inicioEvento + ", finEvento=" + finEvento + ", urlEvento=" + urlEvento + ", urlInstalacion="
				+ urlInstalacion + ", nombreInstalacion=" + nombreInstalacion + "]";
	}
	
	
}
