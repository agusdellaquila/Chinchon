import java.util.Scanner;

public class Presentador {
    static Scanner sc;

    public void cicloDeJuego() {
        Boolean finDeJuego = false;
        Boolean seguirJugando = false;
        while (!finDeJuego) {
            int opcion = mostrarVista();
            if (opcion == 1) {

                Juego juego = new Juego();
                juego.jugar(juego);
            } else if (opcion == 2) {
                //salir
                finDeJuego = true;
            } else {
                //opcion invalida
                System.out.println("Opcion invalida");
            }
        }
    }

    public int mostrarVista() {
        sc = new Scanner(System.in);
        System.out.println("1. Jugar");
        System.out.println("2. Salir");
        int opcion = sc.nextInt();
        return opcion;
    }

    // public void cartaInicialMesa() {
    //     mesa.add(mazo.getCarta());
    // }

    // public void getUltimaCartaMesa() {
    //     mesa.get(mesa.size() - 1).mostrarCarta();
    // }

    // public ArrayList<Carta> getMesa() {
    //     return mesa;
    // }
}
