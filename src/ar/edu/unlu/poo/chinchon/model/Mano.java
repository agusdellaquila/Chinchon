package ar.edu.unlu.poo.chinchon.model;

import java.util.ArrayList;

// import ar.edu.unlu.poo.chinchon.model.Carta.Combinacion;

public class Mano {
    private ArrayList<Carta> mano;
    //combinaciones
    private ArrayList<ArrayList<Carta>> combinacionesEscalera;
    private ArrayList<ArrayList<Carta>> combinacionesNumerosIguales;

    public Mano() {
        mano = new ArrayList<>();
        combinacionesEscalera = new ArrayList<>();
        combinacionesNumerosIguales = new ArrayList<>();
    }

    //getters
    public ArrayList<Carta> getMano() {
        return mano;
    }

    public ArrayList<ArrayList<Carta>> getCombinacionesEscalera() {
        return combinacionesEscalera;
    }

    public ArrayList<ArrayList<Carta>> getCombinacionesNumerosIguales() {
        return combinacionesNumerosIguales;
    }

    public ArrayList<Carta> getNoCombinaciones() {
        ArrayList<Carta> noCombinaciones = new ArrayList<>();
        for (Carta carta : mano) {
            if (carta.getCombinacion() == Carta.Combinacion.NO_COMBINADA) {
                noCombinaciones.add(carta);
            }
        }
        return noCombinaciones;
    }

    //setters
    public void setMano(ArrayList<Carta> mano) {
        this.mano = mano;
    }

    public void agregarCarta(Carta carta) {
        mano.add(carta);
    }

    public void setCombinacionesEscalera(ArrayList<Carta> combinacion) {
        for (Carta carta : combinacion) {
            carta.setCombinacion(Carta.Combinacion.ESCALERA);
        }
        this.combinacionesEscalera.add(combinacion);
    }

    public void setCombinacionesNumerosIguales(ArrayList<Carta> combinacion) {
        for (Carta carta : combinacion) {
            carta.setCombinacion(Carta.Combinacion.NUMEROS_IGUALES);
        }
        this.combinacionesNumerosIguales.add(combinacion);
    }

    public void descombinarCarta(Carta carta) {
        carta.setCombinacion(Carta.Combinacion.NO_COMBINADA);
    }

    //metodos
    public void jugarCarta(Carta cartaPorJugar) {
        if (cartaPorJugar.getCombinacion() == Carta.Combinacion.ESCALERA) {
            for (ArrayList<Carta> combinacion : combinacionesEscalera) {
                if (combinacion.contains(cartaPorJugar)) {
                    combinacion.remove(cartaPorJugar);
                }
            }
        }
        if (cartaPorJugar.getCombinacion() == Carta.Combinacion.NUMEROS_IGUALES) {
            for (ArrayList<Carta> combinacion : combinacionesNumerosIguales) {
                if (combinacion.contains(cartaPorJugar)) {
                    combinacion.remove(cartaPorJugar);
                }
            }
        }
        cartaPorJugar.setCombinacion(Carta.Combinacion.NO_COMBINADA);
        mano.remove(cartaPorJugar);
    }

    public void revisarSiSeDescombino(Mano mano) {
        ArrayList<ArrayList<Carta>> combinacionesEscalera = getCombinacionesEscalera();
        ArrayList<ArrayList<Carta>> combinacionesNumerosIguales = getCombinacionesNumerosIguales();

        //Si el largo de combinacionesEscalera.size < 3, poner en NO_COMBINADA
        for (ArrayList<Carta> combinacion : combinacionesEscalera) {
            if (combinacion.size() < 3) {
                for (Carta carta : combinacion) {
                    this.descombinarCarta(carta);
                }
            }
        }
        //Si el largo de combinacionesNumerosIguales.size < 3, poner en NO_COMBINADA
        for (ArrayList<Carta> combinacion : combinacionesNumerosIguales) {
            if (combinacion.size() < 3) {
                for (Carta carta : combinacion) {
                    this.descombinarCarta(carta);
                }
            }
        }
    }

    //buscar carta en mano y retornarla
    //VALIDAR
    public Carta buscarCartaEnMano(int numero, Carta.Palo palo) {
        for (Carta carta : mano) {
            if (carta.getNumero() == numero && carta.getPalo() == palo) {
                return carta;
            }
        }
        return null;
    }

    public Boolean esChinchon(Mano mano) {
        mano.ordenarPorPaloYNumero(mano);
        //para ver si es chinchon, me fijo si las 7 cartas son escalera
        Boolean chinchon = this.combinacionEscalera(mano.getMano());
        return chinchon;
    }

    public Boolean combinacionEscalera(ArrayList<Carta> cartasPorCombinar) {
        if (cartasPorCombinar.size() >= 3) {
            Boolean escalera = true;
            Carta cartaInicial = cartasPorCombinar.get(0);
            Carta.Palo palo = cartaInicial.getPalo();
            for (int i = 1; i < cartasPorCombinar.size(); i++) {
                if (cartasPorCombinar.get(i).getPalo() != palo) {
                    escalera = false;
                }
                if (cartasPorCombinar.get(i).getNumero() != cartaInicial.getNumero() + 1) {
                    escalera = false;
                }
                cartaInicial = cartasPorCombinar.get(i);
            }
            return escalera;
        }
        return false;
    }
    
    public Boolean combinacionNumerosIguales(ArrayList<Carta> cartasPorCombinar) {
        if (cartasPorCombinar.size() <= 4) {
            Boolean numerosIguales = true;
            Carta cartaInicial = cartasPorCombinar.get(0);
            for (Carta carta : cartasPorCombinar) {
                if (carta.getNumero() != cartaInicial.getNumero()) {
                    numerosIguales = false;
                    cartaInicial = carta;
                }
            }
            return numerosIguales;
        }
        return false;
    }
    
    public void ordenarPorPaloYNumero(Mano mano) {
        ArrayList<Carta> manoOrdenada = new ArrayList<>();
        for (Carta.Palo palo : Carta.Palo.values()) {
            for (int i = 1; i <= 12; i++) {
                for (Carta carta : mano.getMano()) {
                    if (carta.getNumero() == i && carta.getPalo() == palo) {
                        manoOrdenada.add(carta);
                    }
                }
            }
        }
        mano.setMano(manoOrdenada);
    }

    public void vaciarMano(Mazo mazo) {
        for (Carta carta : mano) {
            mazo.agregarCarta(carta);
        }
        mano.clear();
    }

    public void setearPuntaje(Jugador jugador) {
        int lengthCombinaciones = this.getCombinacionesEscalera().size() + this.getCombinacionesNumerosIguales().size();
        Boolean c3 = false;
        Boolean c4 = false;
        if (lengthCombinaciones == 2) {
            for (ArrayList<Carta> combinacion : this.getCombinacionesEscalera()) {
                if (combinacion.size() == 3) {
                    c3 = true;
                }
                if (combinacion.size() == 4) {
                    c4 = true;
                }
            }
            for (ArrayList<Carta> combinacion : this.getCombinacionesNumerosIguales()) {
                if (combinacion.size() == 3) {
                    c3 = true;
                }
                if (combinacion.size() == 4) {
                    c4 = true;
                }
            }
        }
        if (c3 && c4) {
            jugador.setPuntos(jugador.getPuntos() - 10);
        } else if (this.esChinchon(this)) {
            jugador.setEstado(Jugador.Estado.GANADOR);
        } else {
            for (Carta carta : mano) {
                if (carta.getCombinacion() == Carta.Combinacion.NO_COMBINADA) {
                    jugador.setPuntos(jugador.getPuntos() + carta.getNumero());
                }
            }
        }
    }
}
