/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicahibernate;

import java.util.Iterator;
import java.util.Scanner;
import modelos.Faccion;
import modelos.Hermandad;
import modelos.Jugador;
import modelos.Personaje;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Jaime
 */
public class PracticaHibernate {
    static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    static Session session = sessionFactory.openSession();
    static Scanner leer = new Scanner(System.in);
    
    public static void menu(){
        System.out.println("1.Ver faccion.");
        System.out.println("2.Ver jugador.");
        System.out.println("3.Ver hermandad.");
        System.out.println("4.Ver personaje.");
        System.out.println("5.Añadir faccion.");
        System.out.println("6.Añadir jugador.");
        System.out.println("7.Añadir hermandad.");
        System.out.println("8.Añadir personaje.");
        System.out.println("9.Borrar faccion.");
        System.out.println("10.Borrar jugador.");
        System.out.println("11.Borrar hermandad.");
        System.out.println("12.Borrar personaje.");
        System.out.println("13.Modificar faccion.");
        System.out.println("14.Modificar jugador.");
        System.out.println("15.Modificar hermandad.");
        System.out.println("16.Modificar personaje.");
        System.out.println("17.Salir");
        System.out.print("Elija una opción: ");
    }
    
    /****************************************Faccion************************************/
    
    public static void imprimirFacciones(){
        Query query = session.createQuery("from Faccion");
        Iterator it = query.iterate();
        while(it.hasNext()){
            Faccion faccion = (Faccion) it.next();
            System.out.println("ID faccion: " + faccion.getId());
            System.out.println("Nombre faccion: " + faccion.getNombre());
            System.out.println("Número de jugadores de la faccion: " + faccion.getNumeroJugadores());
        }
    }
    
    public static Faccion datosFaccion(){
        String nombre, basura;
        int numeroJugadores = 0;
        Faccion nuevaFaccion;
        
        System.out.print("Introduce el nombre de la nueva faccion: ");
        basura = leer.nextLine();
        nombre = leer.nextLine();
        
        nuevaFaccion = new Faccion(nombre, numeroJugadores);
        
        return nuevaFaccion;
    }
    
    public static void insertarFaccion(){
        session.beginTransaction();
        session.save(datosFaccion());
        session.getTransaction().commit();
    }
    
    public static Faccion cambiarDatosFaccion(){
        Faccion faccion = new Faccion();
        String nuevoNombre, basura;
        
        imprimirFacciones();
        System.out.print("Introduzca la id de la faccion que quiere modificar: ");
        faccion = (Faccion) session.load(Faccion.class, leer.nextInt());
        System.out.print("Introduce el nuevo nombre de la faccion: ");
        basura = leer.nextLine();
        nuevoNombre = leer.nextLine();
        faccion.setNombre(nuevoNombre);
        
        return faccion;
    }
    
    public static void modificarFaccion(){
        session.beginTransaction();
        session.update(cambiarDatosFaccion());
        session.getTransaction().commit();
    }
    
    public static Faccion buscarFaccion(){
        Faccion faccion = new Faccion();
        
        imprimirFacciones();
        System.out.print("Introduzca la id de la faccion: ");
        faccion = (Faccion) session.load(Faccion.class, leer.nextInt());
        
        return faccion;
    }
    
    public static void eliminarFaccion(){
        session.beginTransaction();
        session.delete(buscarFaccion());
        session.getTransaction().commit();
    }
    
    /****************************************Jugador************************************/
    
    public static void imprimirJugadores(){
        Query query = session.createQuery("from Jugador");
        Iterator it = query.iterate();
        while(it.hasNext()){
            Jugador jugador = (Jugador) it.next();
            System.out.println("ID jugador: " + jugador.getId());
            System.out.println("Nombre jugador: " + jugador.getNombre());
            System.out.println("Número de personajes del jugador: " + jugador.getCantidadPersonajes());
            System.out.println("Faccion a la que pertenece: " + jugador.getFaccion().getNombre());
        }
    }
    
    public static Jugador datosJugador(){
        String nombre, basura;
        Faccion faccion = new Faccion();
        int numeroPersonajes = 0;
        Jugador nuevoJugador;
        
        System.out.print("Introduce el nombre del nuevo jugador: ");
        basura = leer.nextLine();
        nombre = leer.nextLine();
        faccion = buscarFaccion();
        
        nuevoJugador = new Jugador(faccion, nombre, numeroPersonajes);
        faccion.setNumeroJugadores(faccion.getNumeroJugadores() + 1);
        
        return nuevoJugador;
    }
    
    public static void insertarJugador(){
        session.beginTransaction();
        session.save(datosJugador());
        session.getTransaction().commit();
    }
    
    public static Jugador cambiarDatosJugador(){
        Jugador jugador = new Jugador();
        String nuevoNombre, basura;
        
        imprimirJugadores();
        System.out.print("Introduzca la id del jugador que quiere modificar: ");
        jugador = (Jugador) session.load(Jugador.class, leer.nextInt());
        System.out.print("Introduce el nuevo nombre del jugador: ");
        basura = leer.nextLine();
        nuevoNombre = leer.nextLine();
        jugador.setNombre(nuevoNombre);
        
        return jugador;
    }
    
    public static void modificarJugador(){
        session.beginTransaction();
        session.update(cambiarDatosJugador());
        session.getTransaction().commit();
    }
    
    public static Jugador buscarJugador(){
        Jugador jugador = new Jugador();
        
        imprimirJugadores();
        System.out.print("Introduzca la id del jugador: ");
        jugador = (Jugador) session.load(Jugador.class, leer.nextInt());
        
        return jugador;
    }
    
    public static void eliminarJugador(){
        Jugador jugador = new Jugador();
        session.beginTransaction();
        jugador = buscarJugador();
        jugador.getFaccion().setNumeroJugadores(jugador.getFaccion().getNumeroJugadores() - 1);
        session.delete(jugador);
        session.getTransaction().commit();
    }
    
    /****************************************Personaje************************************/
    
    public static void imprimirPersonajes(){
        Query query = session.createQuery("from Personaje");
        Iterator it = query.iterate();
        while(it.hasNext()){
            Personaje personaje = (Personaje) it.next();
            System.out.println("ID personaje: " + personaje.getId());
            System.out.println("Nombre personaje: " + personaje.getNombre());
            System.out.println("Nivel del personaje: " + personaje.getNivel());
            System.out.println("Jugador al que pertenece: " + personaje.getJugador().getNombre());
            System.out.println("Hermandad a la que pertenece: " + personaje.getHermandad().getNombre());
        }
    }
    
    public static Personaje datosPersonaje(){
        String nombre, basura;
        Jugador jugador = new Jugador();
        Hermandad hermandad = new Hermandad();
        int numeroJugadores = 0, nivel = 1;
        Personaje nuevoPersonaje;
        
        System.out.print("Introduce el nombre del nuevo jugador: ");
        basura = leer.nextLine();
        nombre = leer.nextLine();
        jugador = buscarJugador();
        hermandad = buscarHermandad();
        
        nuevoPersonaje = new Personaje(hermandad, jugador, nombre, nivel);
        jugador.setCantidadPersonajes(jugador.getCantidadPersonajes() + 1);
        hermandad.setCantidadPersonajes(hermandad.getCantidadPersonajes() + 1);
        
        return nuevoPersonaje;
    }
    
    public static void insertarPersonaje(){
        session.beginTransaction();
        session.save(datosPersonaje());
        session.getTransaction().commit();
    }
    
    public static Personaje cambiarDatosPersonaje(){
        Personaje personaje = new Personaje();
        String nuevoNombre, basura;
        
        imprimirPersonajes();
        System.out.print("Introduzca el id del personaje que quiere modificar: ");
        personaje = (Personaje) session.load(Personaje.class, leer.nextInt());
        System.out.print("Introduce el nuevo nombre del personaje: ");
        basura = leer.nextLine();
        nuevoNombre = leer.nextLine();
        personaje.setNombre(nuevoNombre);
        
        return personaje;
    }
    
    public static void modificarPersonaje(){
        session.beginTransaction();
        session.update(cambiarDatosPersonaje());
        session.getTransaction().commit();
    }
    
    public static Personaje buscarPersonaje(){
        Personaje personaje = new Personaje();
        
        imprimirPersonajes();
        System.out.print("Introduzca el id del personaje: ");
        personaje = (Personaje) session.load(Personaje.class, leer.nextInt());
        
        return personaje;
    }
    
    public static void eliminarPersonaje(){
        Personaje personaje = new Personaje();
        session.beginTransaction();
        personaje = buscarPersonaje();
        personaje.getJugador().setCantidadPersonajes(personaje.getJugador().getCantidadPersonajes() - 1);
        personaje.getHermandad().setCantidadPersonajes(personaje.getHermandad().getCantidadPersonajes() - 1);
        session.delete(personaje);
        session.getTransaction().commit();
    }
    
    /****************************************Hermandad************************************/
    
    public static void imprimirHermandad(){
        Query query = session.createQuery("from Hermandad");
        Iterator it = query.iterate();
        while(it.hasNext()){
            Hermandad hermandad = (Hermandad) it.next();
            System.out.println("ID hermandad: " + hermandad.getId());
            System.out.println("Nombre hermandad: " + hermandad.getNombre());
            System.out.println("Número de personajes de la hermandad: " + hermandad.getCantidadPersonajes());
        }
    }
    
    public static Hermandad datosHermandad(){
        String nombre, basura;
        int numeroPersonajes = 0;
        Hermandad nuevaHermandad;
        
        System.out.print("Introduce el nombre de la nueva hermandad: ");
        basura = leer.nextLine();
        nombre = leer.nextLine();
        
        nuevaHermandad = new Hermandad(nombre, numeroPersonajes);
        
        return nuevaHermandad;
    }
    
    public static void insertarHermandad(){
        session.beginTransaction();
        session.save(datosHermandad());
        session.getTransaction().commit();
    }
    
    public static Hermandad cambiarDatosHermandad(){
        Hermandad hermandad = new Hermandad();
        String nuevoNombre, basura;
        
        imprimirHermandad();
        System.out.print("Introduzca el id de la hermandad que quiere modificar: ");
        hermandad = (Hermandad) session.load(Hermandad.class, leer.nextInt());
        System.out.print("Introduce el nuevo nombre de la hermandad: ");
        basura = leer.nextLine();
        nuevoNombre = leer.nextLine();
        hermandad.setNombre(nuevoNombre);
        
        return hermandad;
    }
    
    public static void modificarHermandad(){
        session.beginTransaction();
        session.update(cambiarDatosHermandad());
        session.getTransaction().commit();
    }
    
    public static Hermandad buscarHermandad(){
        Hermandad hermandad = new Hermandad();
        
        imprimirHermandad();
        System.out.print("Introduzca la id de la hermandad: ");
        hermandad = (Hermandad) session.load(Hermandad.class, leer.nextInt());
        
        return hermandad;
    }
    
    public static void eliminarHermandad(){
        session.beginTransaction();
        session.delete(buscarHermandad());
        session.getTransaction().commit();
    }
    
    public static void main(String[] args) {
        int opcion = 0;
        
        while(opcion != 17){
            menu();
            opcion = leer.nextInt();
            switch(opcion){
                case 1:
                    imprimirFacciones();
                break;
                case 2:
                    imprimirJugadores();
                break;
                case 3:
                    imprimirHermandad();
                break;
                case 4:
                    imprimirPersonajes();
                break;
                case 5:
                    insertarFaccion();
                break;
                case 6:
                    insertarJugador();
                break;
                case 7:
                    insertarHermandad();
                break;
                case 8:
                    insertarPersonaje();
                break;
                case 9:
                    eliminarFaccion();
                break;
                case 10:
                    eliminarJugador();
                break;
                case 11:
                    eliminarHermandad();
                break;
                case 12:
                    eliminarPersonaje();
                break;
                case 13:
                    modificarFaccion();
                break;
                case 14:
                    modificarJugador();
                break;
                case 15:
                    modificarHermandad();
                break;
                case 16:
                    modificarPersonaje();
                break;
                case 17:
                    System.out.println("Saliendo...");
                    sessionFactory.close();
                break;
                default:
                    System.out.println("Opción incorrecta.");
                break;
                
            }
        }
    }   
}
