package modelo;
// Generated 04-mar-2018 12:45:47 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Hermandad generated by hbm2java
 */
public class Hermandad  implements java.io.Serializable {


     private Integer id;
     private String nombre;
     private int cantidadPersonajes;
     private Set personajes = new HashSet(0);

    public Hermandad() {
    }

	
    public Hermandad(String nombre, int cantidadPersonajes) {
        this.nombre = nombre;
        this.cantidadPersonajes = cantidadPersonajes;
    }
    public Hermandad(String nombre, int cantidadPersonajes, Set personajes) {
       this.nombre = nombre;
       this.cantidadPersonajes = cantidadPersonajes;
       this.personajes = personajes;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getCantidadPersonajes() {
        return this.cantidadPersonajes;
    }
    
    public void setCantidadPersonajes(int cantidadPersonajes) {
        this.cantidadPersonajes = cantidadPersonajes;
    }
    public Set getPersonajes() {
        return this.personajes;
    }
    
    public void setPersonajes(Set personajes) {
        this.personajes = personajes;
    }




}

