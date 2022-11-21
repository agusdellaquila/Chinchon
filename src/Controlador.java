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
        Juego juego = new Juego();
        juego.iniciarMazo();
        Mazo mazo = juego.getMazo();
        
        Mesa mesa = juego.getMesa();   
        
        ArrayList<String> nombresDeJugadores = vista.mostrarMensajeCantidadJugadores(juego.getCantidadJugadores());
        juego.iniciarJugadores(nombresDeJugadores);
        ArrayList<Jugador> jugadores = juego.getJugadores();
        vista.mostrarJugadores(jugadores);

        Boolean partida = false;
        Boolean ganador = false;  
        while (!partida) {
            //descombina todas de la ronda pasada
            mazo.descombinarTodasLasCartas();

            vista.mostrarMensajeNuevaRonda();
            this.ronda(juego, mesa, mazo, jugadores);
            for (Jugador jugador : jugadores) {
                int puntajeDeRonda = jugador.getMano().obtenerPuntaje();
                jugador.setPuntos(puntajeDeRonda);

                jugador.getMano().vaciarMano(mazo);
            }

            ganador = juego.revisarGanador(jugadores);
            if (ganador) {
                Jugador jugadorGanador = juego.obtenerGanador();
                vista.mostrarGanador(jugadorGanador);
                vista.mostrarPuntajes(jugadores);
                partida = true;
            }
            // vista.mostrarGanador(null);
            //reestablecer a 0 las manos de los jugadores
        }
    }

    public void ronda(Juego juego, Mesa mesa, Mazo mazo,  ArrayList<Jugador> jugadores) {
        juego.iniciarMesa();
        juego.mezclarYRepartirMazo(); //reparte las manos de los jugadores
        vista.mostrarPuntajes(jugadores);
        
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
                int opcionLevantado = 0;

                while (opcionLevantado != 1 && opcionLevantado != 2) {
                    opcionLevantado = vista.mostrarOpcionesDeLevantado();
                    if (opcionLevantado == 1) {
                        jugadorActual.agarrarCartaDelMazo(mazo);
                    } else if (opcionLevantado == 2) {
                        jugadorActual.agarrarCartaDeLaMesa(mesa);
                    } else {
                        vista.opcionInvalida();
                    }
                }

                //mostrar su mano con carta extra
                vista.mostrarMensajeManoActualizada(jugadorActual);
                vista.mostrarMano(jugadorActual, mano);

                //revisar si tiene combinaciones
                //mover a while
                int bucleCombinaciones = 0;

                while (bucleCombinaciones != 2) {
                    bucleCombinaciones = vista.mostrarMenuBucleDeCombinaciones();

                    if (bucleCombinaciones == 1) {
                        int combinaciones = vista.mostrarMenuCombinaciones(jugadorActual);
        
                        if (combinaciones == 1) {
                            vista.mostrarMensajeCombinacionEscalera();
        
                            ArrayList<Carta> cartasPorCombinar = new ArrayList<Carta>();
                            int cantidadDeCartasPorCombinar = vista.inputNumeroDeCartas();
                            
                            if (cantidadDeCartasPorCombinar >= 3) {
                                for (int i = 0; i < cantidadDeCartasPorCombinar; i++) {
                                    cartasPorCombinar.add(vista.inputCarta(jugadorActual));
                                }
                                
                                Boolean escalera = mano.combinacionEscalera(cartasPorCombinar);
            
                                if (escalera) {
                                    mano.setCombinacionesEscalera(cartasPorCombinar);
                                } else {
                                    vista.mostrarMensajeNoEsEscalera();
                                }
                            } else {
                                vista.mostrarMensajeNoEsEscalera();
                            }
                        } else if (combinaciones == 2) {
                            vista.mostrarMensajeCombinacionNumerosIguales();
        
                            ArrayList<Carta> cartasPorCombinar = new ArrayList<Carta>();
                            int cantidadDeCartasPorCombinar = vista.inputNumeroDeCartas();
                            
                            if (cantidadDeCartasPorCombinar >= 3 && cantidadDeCartasPorCombinar <= 4) {
                                for (int i = 0; i < cantidadDeCartasPorCombinar; i++) {
                                    cartasPorCombinar.add(vista.inputCarta(jugadorActual));
                                }
                                
                                Boolean numerosIguales = mano.combinacionNumerosIguales(cartasPorCombinar);
            
                                if (numerosIguales) {
                                    mano.setCombinacionesEscalera(cartasPorCombinar);
                                } else {
                                    vista.mostrarMensajeNoEsNumerosIguales();
                                }
                            } else {
                                vista.mostrarMensajeNoEsNumerosIguales();
                            }
                        } else if (combinaciones == 3) {
                            vista.mostrarMensajeIngreseCarta();
        
                            Carta carta = vista.inputCarta(jugadorActual);
                            ArrayList<ArrayList<Carta>> combinacionesEscalera = mano.getCombinacionesEscalera();
                            mano.cartaExtraEscalera(combinacionesEscalera, carta);
        
                        } else if (combinaciones == 4) {
                            vista.mostrarMensajeIngreseCarta();
        
                            Carta carta = vista.inputCarta(jugadorActual);
                            ArrayList<ArrayList<Carta>> combinacionesNumeros = mano.getCombinacionesNumerosIguales();
                            mano.cartaExtraNumerosIguales(combinacionesNumeros, carta);
                        } else {
                            bucleCombinaciones = 2;
                        }
                    }
                }
                

                //ordenar mano
                mano.ordenarPorPaloYNumero(mano);

                //mostrar su mano para que vea cual tirar
                vista.mostrarMano(jugadorActual, mano);

                //jugarla
                vista.mostrarMensajeDejeUnaCarta();
                Carta cartaPorJugar = vista.inputCarta(jugadorActual);
                mano.jugarCarta(cartaPorJugar);
                mesa.agregarCartaALaMesa(cartaPorJugar);

                //revisar si se descombino
                mano.revisarSiSeDescombino(mano);

                //ordenar mano
                mano.ordenarPorPaloYNumero(mano);
                
                //mostrar como queda su mano
                vista.mostrarMano(jugadorActual, mano);

                //revisa si tiene la opcion de cortar
                Boolean corta = juego.revisarSiCorta(jugadorActual);
                if (corta) {
                    //pregunta si desea cortar
                    int opcionCortar = vista.mostrarMenuCortar();
                    if (opcionCortar == 0) {
                        mesa.vaciarMesa(mazo);
                        ronda = true;
                    }
                }

                //cambio de turnos
                Jugador jugadorSiguiente = juego.revisarNoTurno(jugadores);
                jugadorActual.setEsTurno(false);
                jugadorSiguiente.setEsTurno(true);

        }
    }
}
