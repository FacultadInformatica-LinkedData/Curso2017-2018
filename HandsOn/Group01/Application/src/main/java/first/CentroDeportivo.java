package first;



public class CentroDeportivo {
    public String nombre;
    public String calle;
    public String cp;


    public CentroDeportivo(){

    }

    public CentroDeportivo (String nombre, String calle, String cp, String barrio) {
        this.nombre = nombre;
        this.calle = calle;
        this.cp = cp;
        this.barrio = barrio;
    }

    public String getNombre () {
        return nombre;
    }

    public void setNombre (String nombre) {
        this.nombre = nombre;
    }

    public String getCalle () {
        return calle;
    }

    public void setCalle (String calle) {
        this.calle = calle;
    }

    public String getCp () {
        return cp;
    }

    public void setCp (String cp) {
        this.cp = cp;
    }

    public String getBarrio () {
        return barrio;
    }

    public void setBarrio (String barrio) {
        this.barrio = barrio;
    }

    public String barrio;





}
