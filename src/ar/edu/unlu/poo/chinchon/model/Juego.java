package ar.edu.unlu.poo.chinchon.model;

import java.util.ArrayList;

public class Juego {
    private Mazo mazo;
    private ArrayList<Jugador> jugadores;
    private Mesa mesa;
    private int cantidadJugadores;
    private int puntajeMaximo;

    public Juego() {
        this.mazo = new Mazo();
        this.jugadores = new ArrayList<>();
        this.mesa = new Mesa();
        this.cantidadJugadores = 2;
        this.puntajeMaximo = 100;
    }

    //getters
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
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

    //setters
    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    //metodos
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
    
    public void iniciarMesa() {
        mesa.cartaInicialMesa(mazo);
    }
    
    public void mezclarYRepartirMazo() {
        mazo.mezclarMazo();
        for (Jugador jugador : jugadores) {
            mazo.repartirMazo(jugador);
        }
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

    public Boolean revisarGanador(ArrayList<Jugador> jugadores) {
        for (Jugador jugador : jugadores) {
            if (jugador.getEstado() == Jugador.Estado.GANADOR) {
                return true;
            }
            if (jugador.getPuntos() >= this.getPuntajeMaximo()) {
                jugador.setEstado(Jugador.Estado.PERDEDOR);
                return true;
            }
        }
        return false;
    }

    public Boolean revisarSiCorta(Jugador jugadorActual) {
        Boolean corta = false;
        Mano mano = jugadorActual.getMano();
       
        ArrayList<Carta> noCombinaciones = mano.getNoCombinaciones();

        //si el size de las que no combino, es <= 1 y si su valor es menor que 5, puede cortar con una no combinada
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

    public Jugador obtenerGanador() {
        for (Jugador jugador : jugadores) {
            if (jugador.getEstado() == Jugador.Estado.GANADOR) {
                return jugador;
            }
        }
        return null;
    }

    //validaciones
    public Boolean validarMenuJuegoOSalgo(String opcion) {
        if (opcion.equals("1") || opcion.equals("2")) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean validarMenuOpcionesDeLevantado(String opcion) {
        if (opcion.equals("1") || opcion.equals("2")) {
            return true;
        }
        return false;
    }

    public Boolean validarMenuBucleDeCombinaciones(String opcion) {
        if (opcion.equals("2")) 
            return true;
        return false;
    }

    public Boolean validarMenuOpcionCortar(String opcion) {
        if (opcion.equals("0")) { 
            return true;
        }
        return false;
    }

    public Boolean validarMenuCantidadDeCartasPorCombinar(int numero) {
        if (numero >= 3 && numero <= 7) {
            return true;
        }
        return false;
    }

    public Carta validarIngresoCarta(Jugador jugadorActual, String carta) {
        //Boolean valida = false;
        if (carta.length() == 2 || carta.length() == 3) {
            String numeroString = "";
            String paloString = "";
            if (carta.length() == 3) {
                numeroString = carta.substring(0, 2);
                paloString = carta.substring(2, 3).toUpperCase();
            } else if (carta.length() == 2) {
                numeroString = carta.substring(0, 1);
                paloString = carta.substring(1, 2).toUpperCase();
            }

            int numero = 100;
            try {
                numero = Integer.parseInt(numeroString);

                if (numero <= 12) {
                    if (paloString.equals("O")) {
                        paloString = "ORO";
                    } else if (paloString.equals("C")) {
                        paloString = "COPA";
                    } else if (paloString.equals("E")) {
                        paloString = "ESPADA";
                    } else if (paloString.equals("B")) {
                        paloString = "BASTO";
                    } else {
                        return null;
                    }
                    Carta.Palo palo = Carta.Palo.valueOf(paloString);

                    return jugadorActual.getMano().buscarCartaEnMano(numero, palo);
                } else {
                    return null;
                }                
            } catch (NumberFormatException e) {
                return null;
            }
        } else {
            return null;
        }
    }
}