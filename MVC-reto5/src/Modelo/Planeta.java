
package Modelo;

/**
 *
 * @author David Tellez
 */
public class Planeta {
    private int codigo;
    private String nombre;
    private int poblacion;
    private float nivelOxigeno;
    private boolean habitabilidad;

    public Planeta() {
    }

    public Planeta(int codigo, String nombre, int poblacion, float nivelOxigeno, boolean habitabilidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.nivelOxigeno = nivelOxigeno;
        this.habitabilidad = habitabilidad;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    public float getNivelOxigeno() {
        return nivelOxigeno;
    }

    public void setNivelOxigeno(float nivelOxigeno) {
        this.nivelOxigeno = nivelOxigeno;
    }

    public boolean isHabitabilidad() {
        return habitabilidad;
    }

    public void setHabitabilidad(boolean habitabilidad) {
        this.habitabilidad = habitabilidad;
    }
    
}
