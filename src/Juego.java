import java.util.ArrayList;
import java.util.Scanner;

public class Juego {
    private Mazo mazo;
    private ArrayList<Jugador> jugadores; //2 a 4 jugadores
    private Mesa mesa;
    private int cantidadJugadores;
    private int puntajeMaximo;
    // private int cantidadCartasPorJugador;
    // private int cantidadCartasPorMesa;
    // private int cantidadCartasPorMazo;
    // private int cantidadCartasPorRonda;
    // private int cantidadCartasPorMano;
    static Scanner sc;

    public Juego() {
        this.mazo = new Mazo();
        this.jugadores = new ArrayList<>();
        this.mesa = new Mesa();
        this.cantidadJugadores = 2;
        this.puntajeMaximo = 100;
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

    public int getCantidadJugadores() {
        return cantidadJugadores;
    }

    public int getPuntajeMaximo() {
        return puntajeMaximo;
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

    public void iniciarJugadores(ArrayList<String> nombresDeJugadores) {
        for (String nombre : nombresDeJugadores) {
            if (jugadores.isEmpty()) {
                jugadores.add(new Jugador(nombre, true));
            } else {
                jugadores.add(new Jugador(nombre, false));
            }
        }
    }

    public void iniciarMazo() {
        mazo.generarMazo();
    }

    public void mezclarYRepartirMazo() {
        mazo.mezclarMazo();
        for (Jugador jugador : jugadores) {
            mazo.repartirMazo(jugador);
        }
    }

    public void iniciarMesa() {
        mesa.cartaInicialMesa(mazo);
    }

    public Boolean revisarGanador(ArrayList<Jugador> jugadores) {
        for (Jugador jugador : jugadores) {
            if (jugador.getPuntos() >= this.getPuntajeMaximo()) {
                jugador.setEsPerdedor(true);
                return true;
            }
        }
        return false;
    }

    public Jugador obtenerGanador() {
        Jugador ganador = null;
        for (Jugador jugador : jugadores) {
            if (jugador.getEsPerdedor() != true) {
                ganador = jugador;
            }
        }
        return ganador;
    }

    public Boolean revisarSiCorta(Jugador jugadorActual) {
        Boolean corta = false;
        Mano mano = jugadorActual.getMano();
       
        ArrayList<Carta> noCombinaciones = mano.getNoCombinaciones();

        //si el tamanio es <= 1 y si su primer elemento es menor que 5
        if (noCombinaciones.size() <= 1) {
            if (noCombinaciones.size() == 0) {
                corta = true;
            } else {
                if (noCombinaciones.get(0).getNumero() < 5) {
                    corta = true;
                }
            }
        }

        return corta;
    }
}