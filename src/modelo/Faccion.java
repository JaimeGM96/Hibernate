package modelo;
// Generated 04-mar-2018 12:45:47 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Faccion generated by hbm2java
 */
public class Faccion  implements java.io.Serializable {


     private Integer id;
     private String nombre;
     private int numeroJugadores;
     private Set jugadors = new HashSet(0);

    public Faccion() {
    }

	
    public Faccion(String nombre, int numeroJugadores) {
        this.nombre = nombre;
        this.numeroJugadores = numeroJugadores;
    }
    public Faccion(String nombre, int numeroJugadores, Set jugadors) {
       this.nombre = nombre;
       this.numeroJugadores = numeroJugadores;
       this.jugadors = jugadors;
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
    public int getNumeroJugadores() {
        return this.numeroJugadores;
    }
    
    public void setNumeroJugadores(int numeroJugadores) {
        this.numeroJugadores = numeroJugadores;
    }
    public Set getJugadors() {
        return this.jugadors;
    }
    
    public void setJugadors(Set jugadors) {
        this.jugadors = jugadors;
    }




}


