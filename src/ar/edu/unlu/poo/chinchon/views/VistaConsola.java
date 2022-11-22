package ar.edu.unlu.poo.chinchon.views;

import java.util.ArrayList;
import java.util.Scanner;

import ar.edu.unlu.poo.chinchon.model.Jugador;
import ar.edu.unlu.poo.chinchon.model.Mano;
import ar.edu.unlu.poo.chinchon.model.Mesa;
import ar.edu.unlu.poo.chinchon.model.Carta;


public class VistaConsola implements IVista{
    static Scanner sc;

    public String mostrarMenuJuegoOSalgo() {
        sc = new Scanner(System.in);
        System.out.println("1. Jugar");
        System.out.println("2. Salir");
        String opcion = sc.next();
        return opcion;
    }

    public void mostrarMesa(Mesa mesa) {
        System.out.println("\u001B[35m" + "----------------Mesa----------------" + "\u001B[0m");
        System.out.println("La carta en mesa es: ");
        Carta ultimaCarta = mesa.getUltimaCartaMesa();
        mostrarCarta(ultimaCarta);
    }

    public void mostrarJugadores(ArrayList<Jugador> jugadores) {
        System.out.println("----------------Jugadores----------------");
        for (Jugador jugador : jugadores) {
            System.out.println("\u001B[33m" + jugador.getNombre() + "\u001B[0m");
        }
    }

    public void mostrarCarta(Carta carta) {
        // System.out.println("[" + carta.getNumero() + " " + carta.getPalo() + " " + carta.combinacionToString(carta.getCombinacion()) + "\u001B[0m" + "]");
        // System.out.print("\u001B[0m");
        System.out.println("┌───────┐");
        System.out.println("│       │");
        if (carta.getNumero() > 9) {
            System.out.println("│ " + carta.getNumero() + "    │");
        } else {
            System.out.println("│ " + carta.getNumero() + "     │");
        }
        if (carta.getPalo() == Carta.Palo.ORO) {
            System.out.println("│ ORO   │");
        } else if (carta.getPalo() == Carta.Palo.COPA) {
            System.out.println("│ COPA  │");
        } else if (carta.getPalo() == Carta.Palo.ESPADA) {
            System.out.println("│ESPADA │");
        } else if (carta.getPalo() == Carta.Palo.BASTO) {
            System.out.println("│ BASTO │");
        }
        System.out.println("│       │");
        System.out.println("└───────┘");
    }

    public String mostrarCartaString(Carta carta) {
        String cartaString = "";
        cartaString = "[" + carta.getNumero() + " " + carta.getPalo() + " " + carta.combinacionToString(carta.getCombinacion()) + "\u001B[0m" + "]";
        return cartaString;
    }
    
    public void mostrarTurnoDe(Jugador jugador) {
        System.out.println("\u001B[36m" +"----------------------------------");
        System.out.println("Turno de " + jugador.getNombre());
        System.out.println("----------------------------------" + "\u001B[0m");  
    }

    public void mostrarMano(Jugador jugadorActual, Mano mano) {
        System.out.println("\u001B[33m" + "----------------Mano de " + jugadorActual.getNombre() + "----------------" + "\u001B[0m");
        String manoString = "";
        for (Carta carta : mano.getMano()) {
            manoString += mostrarCartaString(carta);
        }
        System.out.println(manoString);
        System.out.println("\u001B[33m" + "-------------------------------------------" + "\u001B[0m");
    }

    public String mostrarOpcionesDeLevantado() {
        System.out.println("De donde desea levantar una carta?");
        System.out.println("1. Agarrar carta del mazo");
        System.out.println("2. Agarrar carta de la mesa");
        return sc.next();        
    }

    public void mostrarMensajeManoActualizada(Jugador jugadorActual) {
        System.out.println(jugadorActual.getNombre() + ",Tu mano quedo asi: ");
    }

    public String mostrarMenuBucleDeCombinaciones() {
        System.out.println("1. Hacer combinaciones");
        System.out.println("2. No deseo combinar");
        return sc.next();
    }

    public String mostrarMenuCombinaciones(Jugador jugadorActual) {
        sc = new Scanner(System.in);
        System.out.println(jugadorActual.getNombre() + ", Desea agregar combinaciones? ");
        System.out.println("1. Escalera");
        System.out.println("2. Numeros iguales");
        System.out.println("Cualquier otra tecla: Terminar combinaciones");
        System.out.println("----------------------------------");
        return sc.next();
    }

    public void mostrarMensajeCombinacionEscalera() {
        System.out.println("Ingrese carta/s para formar Escalera");
        System.out.println("----------Ingrese las cartas a combinar----------");
    }

    public void mostrarMensajeCombinacionNumerosIguales() {
        System.out.println("Ingrese carta/s para formar Numeros iguales");
        System.out.println("----------Ingrese las cartas a combinar----------");
    }

    public void mostrarMensajeNoCombinaciones() {
        System.out.println("No se formaron combinaciones");
    }

    public void mostrarMensajeNoEsEscalera() {
        System.out.println("No es escalera");
    }

    public void mostrarMensajeNoEsNumerosIguales() {
        System.out.println("No es numeros iguales");
    }

    public String inputCarta(Jugador jugadorActual) {
        sc = new Scanner(System.in);
        System.out.println("Ingrese el NUMERO y la INICIAL del palo todo junto sin espacios (NP): de la carta deseada: ");
        String carta = sc.next();
        carta.trim();
        return carta;
    }

    public int inputNumeroDeCartas() {
        sc = new Scanner(System.in);
        System.out.println("Ingrese el numero de la CANTIDAD DE CARTAS que desea combinar: ");
        while (!sc.hasNextInt()) {
            System.out.println("Ingrese un numero valido");
            sc.next();
        }
        int opcionElegida = sc.nextInt();
        return opcionElegida;
    }
    
    public void opcionInvalida() {
        System.out.println("Opcion invalida");
    }

    public void mostrarMensajeIngreseCarta() {
        System.out.println("Ingrese la carta extra para agregar a la combinacion");
    }

    public void mostrarMensajeDejeUnaCarta() {
        System.out.println("----------DEJE UNA CARTA EN LA MESA----------");
    }

    public String mostrarMenuCortar() {
        sc = new Scanner(System.in);
        System.out.println("----Tiene la opcion cortar----");
        System.out.println("Ingrese 0 para cortar, o cualquier otro numero para pasar de turno");
        return sc.next();
    }

    public ArrayList<String> mostrarMensajeCantidadJugadores(int cantidadJugadores) {
        ArrayList<String> nombresDeJugadores = new ArrayList<String>();

        sc = new Scanner(System.in);
        System.out.println("El juego sera de " + cantidadJugadores + " Jugadores.");

        for (int i = 1; i <= cantidadJugadores; i++) {
            System.out.println("Ingrese el nombre del jugador " + (i));
            String nombre = sc.next();
            nombresDeJugadores.add(nombre);
        }

        return nombresDeJugadores;
    }

    public void mostrarPuntajes(ArrayList<Jugador> jugadores) {
        System.out.println("El jugador que llegue a 100 puntos, pierde");
        System.out.println("Puntajes:");
        for (Jugador jugador : jugadores) {
            System.out.println(jugador.getNombre() + ": " + jugador.getPuntos() + " puntos");
        }
    }

    public void mostrarGanador(Jugador jugador) {
        System.out.println("--------------------------------------");
        System.out.println("\u001B[32m" + "!!!FELICITACIONES!!!" + "\u001B[0m");
        System.out.println("El ganador es: " + "\u001B[32m" + jugador.getNombre() + "\u001B[0m");
    }

    public void mostrarMensajeNoHayCartasEnElMazo() {
        System.out.println("No hay mas cartas en el mazo");
    }

    public void mostrarMensajeNuevaRonda() {
        System.out.println("---------------Nueva ronda---------------");
    }
}