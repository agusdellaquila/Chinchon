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
        System.out.println(" ");
        System.out.println("\033[4;33m___________________________JUEGO___________________________\u001B[0m");
        System.out.println(" ");
        System.out.println("Bienvenidos al <\033[4;31mChinchon\u001B[0m>");
        System.out.println("\033[1;37m1.\033[0m Jugar");
        System.out.println("\033[1;37m2.\033[0m Salir");
        String opcion = sc.next();
        return opcion;
    }

    public ArrayList<String> mostrarMensajeCantidadJugadores(int cantidadJugadores) {
        ArrayList<String> nombresDeJugadores = new ArrayList<String>();

        sc = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        System.out.println(" ");
        System.out.println("El juego sera de " + cantidadJugadores + " Jugadores.");

        for (int i = 1; i <= cantidadJugadores; i++) {
            System.out.println("--Ingrese el nombre del jugador " + (i) +":");
            String nombre = sc.next();
            nombresDeJugadores.add(nombre);
        }

        return nombresDeJugadores;
    }

    public void mostrarMesa(Mesa mesa) {
        System.out.println("\033[4;35m___________________________Mesa___________________________\u001B[0m");
        System.out.println(" ");
        System.out.println("La carta en mesa es: ");
        Carta ultimaCarta = mesa.getUltimaCartaMesa();
        mostrarCarta(ultimaCarta);
    }

    public void mostrarJugadores(ArrayList<Jugador> jugadores) {
        System.out.println("\033[4;32m_________________________Jugadores_________________________\u001B[0m");
        System.out.println(" ");
        for (Jugador jugador : jugadores) {
            System.out.println("\033[1;34m -\u001B[0m" + jugador.getNombre());
        }
    }

    public void mostrarCarta(Carta carta) {
        System.out.println("┌───────┐");
        System.out.println("│       │");
        if (carta.getNumero() > 9) {
            System.out.println("│ " + "\033[1;37m" + carta.getNumero() + "\u001B[0m" + "    │");
        } else {
            System.out.println("│ " + "\033[1;37m" + carta.getNumero() + "\u001B[0m" + "     │");
        }
        if (carta.getPalo() == Carta.Palo.ORO) {
            System.out.println("│ " + "\033[1;33m" + "ORO" + "\u001B[0m" + "   │");
        } else if (carta.getPalo() == Carta.Palo.COPA) {
            System.out.println("│ " + "\033[1;36m" + "COPA" + "\u001B[0m" + "  │");
        } else if (carta.getPalo() == Carta.Palo.ESPADA) {
            System.out.println("│" + "\033[1;35m" + "ESPADA" + "\u001B[0m" + " │");
        } else if (carta.getPalo() == Carta.Palo.BASTO) {
            System.out.println("│ " + "\033[1;32m" + "BASTO" + "\u001B[0m" + " │");
        }
        System.out.println("│       │");
        System.out.println("└───────┘");
    }

    public String mostrarCartaString(Carta carta) {
        String cartaString = "";
        if (carta.getPalo() == Carta.Palo.ORO) {
            cartaString = "[\033[1;37m" + carta.getNumero() + " \033[1;33m" + carta.getPalo() + "\u001B[0m " + carta.combinacionToString(carta.getCombinacion()) + "\u001B[0m" + "]";
        } else if (carta.getPalo() == Carta.Palo.COPA) {
            cartaString = "[\033[1;37m" + carta.getNumero() + " \033[1;36m" + carta.getPalo() + "\u001B[0m " + carta.combinacionToString(carta.getCombinacion()) + "\u001B[0m" + "]";
        } else if (carta.getPalo() == Carta.Palo.ESPADA) {
            cartaString = "[\033[1;37m" + carta.getNumero() + " \033[1;35m" + carta.getPalo() + "\u001B[0m " + carta.combinacionToString(carta.getCombinacion()) + "\u001B[0m" + "]";
        } else if (carta.getPalo() == Carta.Palo.BASTO) {
            cartaString = "[\033[1;37m" + carta.getNumero() + " \033[1;32m" + carta.getPalo() + "\u001B[0m " + carta.combinacionToString(carta.getCombinacion()) + "\u001B[0m" + "]";
        }
        return cartaString;
    }
    
    public void mostrarTurnoDe(Jugador jugador) {
        System.out.println(" ");
        System.out.println("_________________________________________________");
        System.out.println(" ");
        System.out.println("Turno de \033[1;34m" + jugador.getNombre() + "\u001B[0m");
        System.out.println("_________________________________________________");
        System.out.println(" ");
    }

    public void mostrarMano(Jugador jugadorActual, Mano mano, String titulo) {
        System.out.println("\033[4;34m_________________________" + titulo + " " + jugadorActual.getNombre() + "_________________________\u001B[0m");
        String manoString = "";
        System.out.println("\u001B[33m" + "--------------------------------------------------------------------------------------" + "\u001B[0m");
        for (Carta carta : mano.getMano()) {
            manoString += mostrarCartaString(carta);
        }
        System.out.println(manoString);
        System.out.println("\u001B[33m" + "--------------------------------------------------------------------------------------" + "\u001B[0m");
    }

    public String mostrarOpcionesDeLevantado() {
        System.out.println(" ");
        System.out.println("\033[4;37mDe donde desea levantar una carta?\033[0m");
        System.out.println("\033[1;37m1.\033[0m Agarrar carta del mazo");
        System.out.println("\033[1;37m2.\033[0m Agarrar carta de la mesa");
        return sc.next();        
    }

    public String mostrarMenuBucleDeCombinaciones() {
        System.out.println("\033[4;37mDesea hacer alguna combinacion?\033[0m");
        System.out.println("\033[1;37m1.\033[0m Hacer combinaciones");
        System.out.println("\033[1;37m2.\033[0m No deseo combinar");
        return sc.next();
    }

    public String mostrarMenuCombinaciones(Jugador jugadorActual) {
        sc = new Scanner(System.in);
        System.out.println("\033[4;37m" + jugadorActual.getNombre() + ", que tipo de combinacion?\033[0m");
        System.out.println("\033[1;37m1.\033[0m Escalera");
        System.out.println("\033[1;37m2.\033[0m Numeros iguales");
        System.out.println("\033[1;37mCualquier otra tecla:\033[0m Terminar combinaciones");
        System.out.println("----------------------------------");
        return sc.next();
    }

    public void mostrarMensajeCombinacionEscalera() {
        System.out.println("\033[4;34m_________________________Ingrese la/s carta/s para formar Escalera (de menor a mayor)_________________________\u001B[0m");
        System.out.println(" ");
    }

    public void mostrarMensajeCombinacionNumerosIguales() {
        System.out.println("\033[4;34m_________________________Ingrese la/s carta/s para formar Numeros Iguales_________________________\u001B[0m");
        System.out.println(" ");
    }

    public void mostrarMensajeNoCombinaciones() {
        System.out.println("\033[1;31mNo\033[0m se formaron combinaciones");
    }

    public void mostrarMensajeNoEsEscalera() {
        System.out.println("\033[1;31mNo\033[0m es escalera");
    }

    public void mostrarMensajeNoEsNumerosIguales() {
        System.out.println("\033[1;31mNo\033[0m es numeros iguales");
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
        System.out.println("Ingrese el numero de la \033[1;37mCANTIDAD DE CARTAS\033[0m que desea combinar: ");
        while (!sc.hasNextInt()) {
            System.out.println("Ingrese un numero valido");
            sc.next();
        }
        int opcionElegida = sc.nextInt();
        return opcionElegida;
    }
    
    public void opcionInvalida() {
        System.out.println("Opcion \033[1;31minvalida\033[0m");
    }

    public void mostrarMensajeDejeUnaCarta() {
        System.out.println("\033[4;34m_________________________Deje una carta en la mesa_________________________\u001B[0m");
        System.out.println(" ");
    }

    public String mostrarMenuCortar() {
        sc = new Scanner(System.in);
        System.out.println("\033[4;34m_________________________Tiene la opcion cortar_________________________\u001B[0m");
        System.out.println(" ");
        System.out.println("\033[1;37m0.\033[0m Para cortar");
        System.out.println("\033[1;37mX.\033[0m Cualquier tecla para pasar de turno\033[0m");
        return sc.next();
    }

    public void mostrarMensajeInfoPerder() {
        System.out.println("El jugador que llegue a 100 puntos, \033[1;31mpierde\033[0m");
        System.out.println(" ");
    }

    public void mostrarPuntajes(ArrayList<Jugador> jugadores) {
        System.out.println("Puntajes:");
        for (Jugador jugador : jugadores) {
            System.out.println(jugador.getNombre() + ": " + jugador.getPuntos() + " \033[1;32mpuntos\033[0m");
        }
    }

    public void mostrarGanador(Jugador jugador) {
        System.out.println("\033[4;32m________________________________________________________________\u001B[0m");
        System.out.println(" ");
        System.out.println("\u001B[32m" + "!!!FELICITACIONES!!!" + "\u001B[0m");
        System.out.println("El ganador es: " + "\u001B[32m" + jugador.getNombre() + "\u001B[0m");
        System.out.println("\033[4;32m________________________________________________________________\u001B[0m");

        System.out.println(" ");
    }

    public void mostrarMensajeNoHayCartasEnElMazo() {
        System.out.println("\033[4;31mNo hay mas cartas en el mazo\033[0m");
    }

    public void mostrarMensajeNuevaRonda() {
        System.out.println("\033[4;32m_________________________Nueva ronda_________________________\u001B[0m");
        System.out.println(" ");
    }
}