import java.util.ArrayList;
import java.util.Scanner;

public class Vista {
    static Scanner sc;
    
    //REVISAR QUE NO HAYA NADA DE LOGICA ACA

    public Vista() {

    }

    public int mostrarMenuJuegoOSalgo() {
        sc = new Scanner(System.in);
        System.out.println("1. Jugar");
        System.out.println("2. Salir");
        int opcion = sc.nextInt();
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
        System.out.println("[" + carta.getNumero() + " " + carta.getPalo() + " " + carta.combinacionToString(carta.getCombinacion()) + "\u001B[0m" + "]");
        System.out.print("\u001B[0m");
        // System.out.println("┌───────┐");
        // System.out.println("│       │");
        // if (numero > 9) {
        //     System.out.println("│ " + numero + "    │");
        // } else {
        //     System.out.println("│ " + numero + "     │");
        // }
        // if (palo == Carta.Palo.ORO) {
        //     System.out.println("│ ORO   │");
        // } else if (palo == Carta.Palo.COPA) {
        //     System.out.println("│ COPA  │");
        // } else if (palo == Carta.Palo.ESPADA) {
        //     System.out.println("│ESPADA │");
        // } else if (palo == Carta.Palo.BASTO) {
        //     System.out.println("│ BASTO │");
        // }
        // System.out.println("│       │");
        // System.out.println("└───────┘");
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

    public int mostrarOpcionesDeLevantado() {
        System.out.println("De donde desea levantar una carta?");
        System.out.println("1. Agarrar carta del mazo");
        System.out.println("2. Agarrar carta de la mesa");
        return sc.nextInt();        
    }

    public void mostrarMensajeManoActualizada(Jugador jugadorActual) {
        System.out.println(jugadorActual.getNombre() + ",Tu mano quedo asi: ");
    }

    public int mostrarMenuBucleDeCombinaciones() {
        System.out.println("1. Hacer combinaciones");
        System.out.println("2. No deseo combinar");
        return sc.nextInt();
    }

    public int mostrarMenuCombinaciones(Jugador jugadorActual) {
        sc = new Scanner(System.in);
        System.out.println(jugadorActual.getNombre() + ", Desea agregar combinaciones? ");
        System.out.println("1. Escalera");
        System.out.println("2. Numeros iguales");
        System.out.println("3. Agregar extra a combinacion de escalera existente");
        System.out.println("4. Agregar extra a combinacion de numeros iguales existente");
        System.out.println("Cualquier otro numero: Terminar combinaciones");
        System.out.println("----------------------------------");
        return sc.nextInt();
    }

    public void mostrarMensajeCombinacionEscalera() {
        System.out.println("Ingrese carta/s para formar Escalera");
        System.out.println("----------Ingrese las cartas a combinar----------");
    }

    public void mostrarMensajeCombinacionNumerosIguales() {
        System.out.println("Ingrese carta/s para formar Numeros iguales");
        System.out.println("----------Ingrese las cartas a combinar----------");
    }

    public void mostrarMensajeNoEsEscalera() {
        System.out.println("No es escalera");
    }

    public void mostrarMensajeNoEsNumerosIguales() {
        System.out.println("No es numeros iguales");
    }

    public Carta inputCarta(Jugador jugadorActual) {
        sc = new Scanner(System.in);
        System.out.println("Ingrese el numero y el palo (dejando una coma entremedio: N,P) de la carta deseada: ");
        String carta = sc.next();
        //parse carta
        carta.trim();
        String[] cartaParse = carta.split(",");
        int numero = Integer.parseInt(cartaParse[0]);
        Carta.Palo palo = Carta.Palo.valueOf(cartaParse[1].toUpperCase());
        Carta cartaPorJugar = jugadorActual.getMano().buscarCartaEnMano(numero, palo);
        //validar q exista
        return cartaPorJugar;
    }

    public int inputNumeroDeCartas() {
        sc = new Scanner(System.in);
        System.out.println("Ingrese el numero de la cantidad de cartas que desea combinar: ");
        return sc.nextInt();
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

    public int mostrarMenuCortar() {
        sc = new Scanner(System.in);
        System.out.println("----Tiene la opcion cortar----");
        System.out.println("Pulse 0 para cortar, o cualquier otro numero para pasar de turno");
        return sc.nextInt();
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
        System.out.println("\u001B[32m" + "!!!felicitaciones!!!" + "\u001B[0m");
        System.out.println("El ganador es: " + "\u001B[32m" + jugador.getNombre() + "\u001B[0m");
    }

    public void mostrarMensajeNuevaRonda() {
        System.out.println("---------------Nueva ronda---------------");
    }
}