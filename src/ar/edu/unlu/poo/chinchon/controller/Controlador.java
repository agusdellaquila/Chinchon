package ar.edu.unlu.poo.chinchon.controller;

import java.util.ArrayList;

import ar.edu.unlu.poo.chinchon.model.Juego;
import ar.edu.unlu.poo.chinchon.model.Jugador;
import ar.edu.unlu.poo.chinchon.model.Mano;
import ar.edu.unlu.poo.chinchon.model.Mazo;
import ar.edu.unlu.poo.chinchon.model.Mesa;
import ar.edu.unlu.poo.chinchon.model.Carta;
import ar.edu.unlu.poo.chinchon.views.VistaConsola;

public class Controlador {
    VistaConsola vista = null;
    Juego juego = null;

    public Controlador(VistaConsola vista, Juego juego) {
        this.vista = vista;
        this.juego = juego;

        this.cicloDeJuego();
    }

    public void cicloDeJuego() {
        Boolean finDeJuego = false; 

        while (!finDeJuego) {
            String opcion = vista.mostrarMenuJuegoOSalgo();
            while (!juego.validarMenuJuegoOSalgo(opcion)) {
                opcion = vista.mostrarMenuJuegoOSalgo();
                vista.opcionInvalida();
            }
            if (opcion.equals("1")) {
                this.partida();
            } else if (opcion.equals("2")) {
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
        //reinicia el puntaje a 0 en cada partida
        for (Jugador jugador : jugadores) {
            jugador.setPuntos(0);
        }
        vista.mostrarJugadores(jugadores);

        Boolean partida = false;
        Boolean ganador = false;  

        while (!partida) {
            //descombina todas las cartas de la ronda pasada
            mazo.descombinarTodasLasCartas();

            vista.mostrarMensajeNuevaRonda();

            this.ronda(juego, mesa, mazo, jugadores);
            
            //finalizada la ronda, se cuentan los puntos y se vacian las manos
            for (Jugador jugador : jugadores) {
                jugador.getMano().setearPuntaje(jugador);
                jugador.getMano().vaciarMano(mazo);
            }

            //se revisa si algun jugador llego a 100 puntos
            ganador = juego.revisarGanador(jugadores);
            if (ganador) {
                Jugador jugadorGanador = juego.obtenerGanador();
                vista.mostrarGanador(jugadorGanador);
                vista.mostrarPuntajes(jugadores);
                partida = true;
            }
        }
    }

    public void ronda(Juego juego, Mesa mesa, Mazo mazo,  ArrayList<Jugador> jugadores) {
        juego.iniciarMesa();
        //reparte las manos de los jugadores
        juego.mezclarYRepartirMazo();
        vista.mostrarPuntajes(jugadores);
        
        Boolean ronda = false;  
        while (!ronda) {
                vista.mostrarMesa(mesa);

                //turnos
                Jugador jugadorActual = juego.revisarTurno(jugadores);
                Mano mano = jugadorActual.getMano();

                //Turno de actual
                vista.mostrarTurnoDe(jugadorActual);
                
                //ordenar mano
                mano.ordenarPorPaloYNumero(mano);
                
                //mostrar su mano inicial
                vista.mostrarMano(jugadorActual, mano);

                //opciones de levantado
                String opcionLevantado = "0";

                while (!juego.validarMenuOpcionesDeLevantado(opcionLevantado)) {
                    opcionLevantado = vista.mostrarOpcionesDeLevantado();
                    while (!juego.validarMenuOpcionesDeLevantado(opcionLevantado)) {
                        vista.opcionInvalida();
                        opcionLevantado = vista.mostrarOpcionesDeLevantado();
                    }
                    if (opcionLevantado.equals("1")) {
                        Boolean siguenHabiendoCartas = jugadorActual.agarrarCartaDelMazo(mazo);
                        if (!siguenHabiendoCartas) {
                            vista.mostrarMensajeNoHayCartasEnElMazo();
                            opcionLevantado = "0";
                        }
                    } else if (opcionLevantado.equals("2")) {
                        jugadorActual.agarrarCartaDeLaMesa(mesa);
                    } else {
                        vista.opcionInvalida();
                    }
                }

                //mostrar su mano con carta levantada
                vista.mostrarMensajeManoActualizada(jugadorActual);
                vista.mostrarMano(jugadorActual, mano);

                //revisar si tiene combinaciones
                String bucleCombinaciones = "0";

                while (!juego.validarMenuBucleDeCombinaciones(bucleCombinaciones)) {
                    bucleCombinaciones = vista.mostrarMenuBucleDeCombinaciones();

                    if (bucleCombinaciones.equals("1")) {
                        String combinaciones = vista.mostrarMenuCombinaciones(jugadorActual);
        
                        if (combinaciones.equals("1")) {
                            vista.mostrarMensajeCombinacionEscalera();
        
                            ArrayList<Carta> cartasPorCombinar = new ArrayList<Carta>();
                            int cantidadDeCartasPorCombinar = vista.inputNumeroDeCartas();
                            
                            while(!juego.validarMenuCantidadDeCartasPorCombinar(cantidadDeCartasPorCombinar)) {
                                cantidadDeCartasPorCombinar = vista.inputNumeroDeCartas();
                            }

                            for (int i = 0; i < cantidadDeCartasPorCombinar; i++) {
                                Carta cartaValida = this.inputCarta(jugadorActual);
                                cartasPorCombinar.add(cartaValida);
                            }
                            
                            Boolean escalera = mano.combinacionEscalera(cartasPorCombinar);
        
                            if (escalera) {
                                mano.setCombinacionesEscalera(cartasPorCombinar);
                            } else {
                                vista.mostrarMensajeNoEsEscalera();
                            }

                        } else if (combinaciones.equals("2")) {
                            vista.mostrarMensajeCombinacionNumerosIguales();
        
                            ArrayList<Carta> cartasPorCombinar = new ArrayList<Carta>();
                            int cantidadDeCartasPorCombinar = vista.inputNumeroDeCartas();
                            
                            if (cantidadDeCartasPorCombinar >= 3 && cantidadDeCartasPorCombinar <= 4) {
                                for (int i = 0; i < cantidadDeCartasPorCombinar; i++) {
                                    Carta cartaValida = this.inputCarta(jugadorActual);
                                    cartasPorCombinar.add(cartaValida);
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
                        } else {
                            bucleCombinaciones = "2";
                        }
                    } else if (bucleCombinaciones.equals("2")) {
                        vista.mostrarMensajeNoCombinaciones();
                        bucleCombinaciones = "2";
                    } else {
                        vista.opcionInvalida();
                    }
                }

                //ordenar mano
                mano.ordenarPorPaloYNumero(mano);

                //mostrar su mano para que vea cual tirar
                vista.mostrarMano(jugadorActual, mano);

                //dejar su carta extra para quedarse con 7
                vista.mostrarMensajeDejeUnaCarta();

                Carta cartaValida = this.inputCarta(jugadorActual);

                mano.jugarCarta(cartaValida);
                mesa.agregarCartaALaMesa(cartaValida);

                //revisa si al haber dejado la carta en la mesa, se deshizo una combinacion existente
                mano.revisarSiSeDescombino(mano);

                //ordenar mano
                mano.ordenarPorPaloYNumero(mano);
                
                //mostrar como queda su mano al final del turno
                vista.mostrarMano(jugadorActual, mano);

                //revisa si tiene la opcion de cortar
                Boolean corta = juego.revisarSiCorta(jugadorActual);
                if (corta) {
                    //pregunta si desea cortar
                    String opcionCortar = vista.mostrarMenuCortar();
                    if (juego.validarMenuOpcionCortar(opcionCortar)) {
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

    public Carta inputCarta(Jugador jugadorActual) {
        String cartaPorJugar = vista.inputCarta(jugadorActual);

        Carta cartaValida = juego.validarIngresoCarta(jugadorActual, cartaPorJugar);

        while (cartaValida == null) {
            cartaPorJugar = vista.inputCarta(jugadorActual);
            cartaValida = juego.validarIngresoCarta(jugadorActual, cartaPorJugar);
        }

        return cartaValida;
    }
}
