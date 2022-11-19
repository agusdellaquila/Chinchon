import java.util.ArrayList;

public class Controlador {
    Vista vista = null;
    Juego juego = null;

    public Controlador(Vista vista, Juego juego) {
        this.vista = vista;
        this.juego = juego;

        this.cicloDeJuego();
    }

    public void cicloDeJuego() {
        Boolean finDeJuego = false; //mover a juego
        //Boolean seguirJugando = false;

        while (!finDeJuego) {
            int opcion = vista.mostrarMenuJuegoOSalgo();
            if (opcion == 1) {
                this.partida();
            } else if (opcion == 2) {
                //salir
                finDeJuego = true;
            } else {
                vista.opcionInvalida();
            }
        }
    }

    public void partida() {
        //atributos?
        Juego juego = new Juego();
        Mesa mesa = juego.getMesa();
        ArrayList<Jugador> jugadores = juego.getJugadores();
        Mazo mazo = juego.getMazo();
        int cantidadJugadores = 2;

        vista.mostrarMensajeCantidadJugadores(cantidadJugadores);
        juego.iniciarJugadores();
        juego.iniciarMazo();
        vista.mostrarJugadores(jugadores);
        juego.iniciarMesa();

        this.ronda(juego, mesa, mazo, jugadores);
    }

    public void ronda(Juego juego, Mesa mesa, Mazo mazo,  ArrayList<Jugador> jugadores) {
        Boolean ronda = false;  

        while (!ronda) {
                vista.mostrarMesa(mesa);

                //turnos
                Jugador jugadorActual = juego.revisarTurno(jugadores);
                Mano mano = jugadorActual.getMano();

                //Turno de
                vista.mostrarTurnoDe(jugadorActual);
                
                //ordenar mano
                mano.ordenarPorPaloYNumero(mano);
                
                //mostrar su mano inicial
                vista.mostrarMano(jugadorActual, mano);

                //opciones de levantado
                int opcionLevantado = vista.mostrarMenuJuegoOSalgo();

                if (opcionLevantado == 1) {
                    jugadorActual.agarrarCartaDelMazo(mazo);
                } else if (opcionLevantado == 2) {
                    jugadorActual.agarrarCartaDeLaMesa(mesa);
                } else {
                    vista.opcionInvalida();
                }
                

                //mostrar su mano con carta extra
                vista.mostrarMensajeManoActualizada(jugadorActual);
                vista.mostrarMano(jugadorActual, mano);

                //revisar si tiene combinaciones
                //mover a while
                int combinaciones = vista.mostrarMenuCombinaciones(jugadorActual);

                if (combinaciones == 1) {
                    vista.mostrarMensajeCombinacionEscalera();

                    Carta carta1 = vista.inputCarta(jugadorActual);
                    Carta carta2 = vista.inputCarta(jugadorActual);
                    Carta carta3 = vista.inputCarta(jugadorActual);

                    Boolean escalera = mano.esEscalera(carta1, carta2, carta3);

                    if (escalera) {
                        mano.agregarCombinacioneEscalera(carta1, carta2, carta3);
                    } else {
                        vista.mostrarMensajeNoEsEscalera();
                    }
                } else if (combinaciones == 2) {
                    vista.mostrarMensajeCombinacionNumerosIguales();

                    Carta carta1 = vista.inputCarta(jugadorActual);
                    Carta carta2 = vista.inputCarta(jugadorActual);
                    Carta carta3 = vista.inputCarta(jugadorActual);

                    Boolean numerosIguales = mano.esNumerosIguales(carta1, carta2, carta3);

                    if (numerosIguales) {
                        mano.agregarCombinacionNumerosIguales(carta1, carta2, carta3);
                    } else {
                        vista.mostrarMensajeNoEsNumerosIguales();
                    }
                } else if (combinaciones == 3) {
                    vista.mostrarMensajeIngreseCarta();

                    Carta carta = vista.inputCarta(jugadorActual);
                    ArrayList<Carta> combinacionesEscalera = mano.getCombinacionesEscalera();
                    mano.cartaExtraEscalera(combinacionesEscalera, carta);

                } else if (combinaciones == 4) {
                    vista.mostrarMensajeIngreseCarta();

                    Carta carta = vista.inputCarta(jugadorActual);
                    ArrayList<Carta> combinacionesNumeros = mano.getCombinacionesNumerosIguales();
                    mano.cartaExtraEscalera(combinacionesNumeros, carta);
                } else if (combinaciones == 5) {
                    //no hacer nada
                } else {
                    vista.opcionInvalida();
                }

                //mostrar su mano para que vea cual tirar
                vista.mostrarMano(jugadorActual, mano);

                //jugarla
                vista.mostrarMensajeDejeUnaCarta();
                Carta cartaPorJugar = vista.inputCarta(jugadorActual);
                mano.jugarCarta(cartaPorJugar);
                mesa.agregarCartaALaMesa(cartaPorJugar);

                //revisar si se descombino
                mano.revisarSiSeDescombino(mano);
                
                //mostrar como queda su mano
                vista.mostrarMano(jugadorActual, mano);

                //cambio de turnos
                Jugador jugadorSiguiente = juego.revisarNoTurno(jugadores);
                jugadorActual.setEsTurno(false);
                jugadorSiguiente.setEsTurno(true);
                
                
                //terminar ronda
                int seguir = vista.mostrarMensajeOpcionesSeguir();
                if (seguir == 0) {
                    ronda = true;
                }
        }
    }
}
