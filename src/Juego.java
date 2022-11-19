import java.util.ArrayList;
import java.util.Scanner;

public class Juego {
    private Mazo mazo;
    private ArrayList<Jugador> jugadores; //2 a 4 jugadores
    private Mesa mesa;
    static Scanner sc;

    public Juego() {
        this.mazo = new Mazo();
        this.jugadores = new ArrayList<>();
        this.mesa = new Mesa();
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Mazo getMazo() {
        return mazo;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public Jugador revisarTurno(ArrayList<Jugador> jugadores) {
        Jugador turnoJugador = null;
        for (Jugador jugador : jugadores) {
            if (jugador.getEsTurno() == true) {
                turnoJugador = jugador;
            }
        }
        return turnoJugador;
    }

    public Jugador revisarNoTurno(ArrayList<Jugador> jugadores) {
        Jugador noTurnoJugador = null;
        for (Jugador jugador : jugadores) {
            if (jugador.getEsTurno() == false) {
                noTurnoJugador = jugador;
            }
        }
        return noTurnoJugador;
    }

    public void iniciarJugadores(ArrayList<jugadores> jugadores, ArrayList<String> nombresDeJugadores) {
        //recibe array de string con nombres y los inicia
        Boolean inicia = false;
        String nombre = sc.next();
        if (i == 1) {
            inicia = true;
        }
        jugadores.add(new Jugador(nombre, inicia));
    }

    public void iniciarMazo() {
        mazo.generarMazo();
        mazo.mezclarMazo();
        for (Jugador jugador : jugadores) {
            mazo.repartirMazo(jugador);
        }
    }

    public void iniciarMesa() {
        mesa.cartaInicialMesa(mazo);
    }
}

// while (jugadores.size() > 1) {
//     for (Jugador jugador : jugadores) {
//         if (jugador.getEsTurno()) {
//             jugador.jugarCarta(mesa);
//             if (jugador.getMano().getManoLength() == 0) {
//                 jugador.setEsGanador(true);
//                 jugadores.remove(jugador);
//             }
//         }
//     }
// }