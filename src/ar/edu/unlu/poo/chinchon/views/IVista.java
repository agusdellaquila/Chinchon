package ar.edu.unlu.poo.chinchon.views;

import java.util.ArrayList;

import ar.edu.unlu.poo.chinchon.model.Jugador;
import ar.edu.unlu.poo.chinchon.model.Mano;
import ar.edu.unlu.poo.chinchon.model.Mesa;
import ar.edu.unlu.poo.chinchon.model.Carta;

public interface IVista {
    public String mostrarMenuJuegoOSalgo();
    public void mostrarMesa(Mesa mesa);
    public void mostrarJugadores(ArrayList<Jugador> jugadores);
    public void mostrarCarta(Carta carta);
    public String mostrarCartaString(Carta carta);
    public void mostrarTurnoDe(Jugador jugador);
    public void mostrarMano(Jugador jugadorActual, Mano mano, String titulo);
    public String mostrarOpcionesDeLevantado();
    public String mostrarMenuBucleDeCombinaciones();
    public String mostrarMenuCombinaciones(Jugador jugadorActual);
    public void mostrarMensajeNoCombinaciones();
    public void mostrarMensajeCombinacionEscalera();
    public void mostrarMensajeCombinacionNumerosIguales();
    public void mostrarMensajeNoEsEscalera();
    public void mostrarMensajeNoEsNumerosIguales();
    public String inputCarta(Jugador jugadorActual);
    public int inputNumeroDeCartas();
    public void opcionInvalida();
    public void mostrarMensajeDejeUnaCarta();
    public String mostrarMenuCortar();
    public void mostrarMensajeNoHayCartasEnElMazo();
    public ArrayList<String> mostrarMensajeCantidadJugadores(int cantidadJugadores);
    public void mostrarMensajeInfoPerder();
    public void mostrarPuntajes(ArrayList<Jugador> jugadores);
    public void mostrarGanador(Jugador jugador);
    public void mostrarMensajeNuevaRonda();
}
