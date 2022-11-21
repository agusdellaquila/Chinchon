import java.util.ArrayList;

public class Mano {
    //array de cartas
    private ArrayList<Carta> mano;
    //array de combinaciones
    private ArrayList<ArrayList<Carta>> combinacionesEscalera;
    private ArrayList<ArrayList<Carta>> combinacionesNumerosIguales;

    public Mano() {
        mano = new ArrayList<>();
        combinacionesEscalera = new ArrayList<>();
        combinacionesNumerosIguales = new ArrayList<>();
    }

    public ArrayList<Carta> getMano() {
        return mano;
    }

    public ArrayList<ArrayList<Carta>> getCombinacionesEscalera() {
        return combinacionesEscalera;
    }

    public ArrayList<ArrayList<Carta>> getCombinacionesNumerosIguales() {
        return combinacionesNumerosIguales;
    }

    public void setMano(ArrayList<Carta> mano) {
        this.mano = mano;
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

    //jugarCarta
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
                    carta.setCombinacion(Carta.Combinacion.NO_COMBINADA);
                }
            }
        }
        //Si el largo de combinacionesNumerosIguales.size < 3, poner en NO_COMBINADA
        for (ArrayList<Carta> combinacion : combinacionesNumerosIguales) {
            if (combinacion.size() < 3) {
                for (Carta carta : combinacion) {
                    carta.setCombinacion(Carta.Combinacion.NO_COMBINADA);
                }
            }
        }
    }

    public void descombinarCarta(Carta carta) {
        carta.setCombinacion(Carta.Combinacion.NO_COMBINADA);
    }

    //buscar carta en mano y retornarla VALIDAR QUE EXISTA
    public Carta buscarCartaEnMano(int numero, Carta.Palo palo) {
        for (Carta carta : mano) {
            if (carta.getNumero() == numero && carta.getPalo() == palo) {
                return carta;
            }
        }
        return null;
    }

    public void agregarCarta(Carta carta) {
        mano.add(carta);
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

    // public ArrayList<Carta> getCombinacionesEscalera () {
    //     ArrayList<Carta> combinacionesEscalera = new ArrayList<>();
    //     for (Carta carta : mano) {
    //         if (carta.getCombinacion() == Carta.Combinacion.ESCALERA) {
    //             combinacionesEscalera.add(carta);
    //         }
    //     }
    //     return combinacionesEscalera;
    // }
    
    public void cartaExtraEscalera(ArrayList<ArrayList<Carta>> combinaciones, Carta cartaExtra) {
        for (ArrayList<Carta> combinacion : combinaciones) {
            for (Carta carta : combinacion) {
                if (carta.getNumero() == cartaExtra.getNumero() - 1 || carta.getNumero() == cartaExtra.getNumero() + 1) {
                    if (carta.getPalo() == cartaExtra.getPalo()) {
                        cartaExtra.setCombinacion(Carta.Combinacion.ESCALERA);
                    }
                }
            }
        }
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

    // public ArrayList<Carta> getCombinacionesNumerosIguales () {
    //     ArrayList<Carta> combinacionesNumeros = new ArrayList<>();
    //     for (Carta carta : mano) {
    //         if (carta.getCombinacion() == Carta.Combinacion.NUMEROS_IGUALES) {
    //             combinacionesNumeros.add(carta);
    //         }
    //     }
    //     return combinacionesNumeros;
    // }

    public void cartaExtraNumerosIguales(ArrayList<ArrayList<Carta>> combinaciones, Carta cartaExtra) {
        for (ArrayList<Carta> combinacion : combinaciones) {
            for (Carta carta : combinacion) {
                if (carta.getNumero() == cartaExtra.getNumero()) {
                    cartaExtra.setCombinacion(Carta.Combinacion.NUMEROS_IGUALES);
                }
            }
        }
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

    public int obtenerPuntaje() {
        int puntaje = 0;
        //si son dos grupos!!!
        if ((getCombinacionesEscalera().size() == 3 && getCombinacionesNumerosIguales().size() == 4) || (getCombinacionesEscalera().size() == 4 && getCombinacionesNumerosIguales().size() == 3)) {
            puntaje = -10;
        } else if (this.esChinchon(this)) {
            puntaje = -1000; //gana directamente
        } else {
            for (Carta carta : mano) {
                if (carta.getCombinacion() == Carta.Combinacion.NO_COMBINADA) {
                    puntaje += carta.getNumero();
                }
            }
        }
        return puntaje;
    }
}
