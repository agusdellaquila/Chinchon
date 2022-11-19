import java.util.ArrayList;

public class Mano {
    //array de cartas
    private ArrayList<Carta> mano;
    //array de combinaciones
    // private ArrayList<Carta> combinaciones;

    public Mano() {
        mano = new ArrayList<>();
    }

    public ArrayList<Carta> getMano() {
        return mano;
    }

    public void setMano(ArrayList<Carta> mano) {
        this.mano = mano;
    }

    //jugarCarta
    public void jugarCarta(Carta cartaPorJugar) {
        cartaPorJugar.setCombinacion(Carta.Combinacion.NO_COMBINADA);
        mano.remove(cartaPorJugar);
    }

    public void revisarSiSeDescombino(Mano mano) {
        ArrayList<Carta> combinacionesEscalera = getCombinacionesEscalera();
        ArrayList<Carta> combinacionesNumerosIguales = getCombinacionesNumerosIguales();

        //Si el largo de combinacionesEscalera.size < 3, poner en NO_COMBINADA
        if (combinacionesEscalera.size() < 3) {
            for (Carta carta : combinacionesEscalera) {
                carta.setCombinacion(Carta.Combinacion.NO_COMBINADA);
            }
        }
        //Si el largo de combinacionesNumerosIguales.size < 3, poner en NO_COMBINADA
        if (combinacionesNumerosIguales.size() < 3) {
            for (Carta carta : combinacionesNumerosIguales) {
                carta.setCombinacion(Carta.Combinacion.NO_COMBINADA);
            }
        }
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

    //recibe 3 cartas y se fija si hay una escalera de numero y si son del mismo palo
    public boolean esEscalera(Carta carta1, Carta carta2, Carta carta3) { //se debe pasar ordenada?
        if (carta1.getNumero() == carta2.getNumero() - 1 && carta2.getNumero() == carta3.getNumero() - 1) {
            if (carta1.getPalo() == carta2.getPalo() && carta2.getPalo() == carta3.getPalo()) {
                return true;
            }
        }
        return false;
    }

    public void agregarCombinacioneEscalera(Carta carta1, Carta carta2, Carta carta3) {
        carta1.setCombinacion(Carta.Combinacion.ESCALERA);
        carta2.setCombinacion(Carta.Combinacion.ESCALERA);
        carta3.setCombinacion(Carta.Combinacion.ESCALERA);
    }

    public ArrayList<Carta> getCombinacionesEscalera () {
        ArrayList<Carta> combinacionesEscalera = new ArrayList<>();
        for (Carta carta : mano) {
            if (carta.getCombinacion() == Carta.Combinacion.ESCALERA) {
                combinacionesEscalera.add(carta);
            }
        }
        return combinacionesEscalera;
    }
    
    public void cartaExtraEscalera(ArrayList<Carta> combinacion, Carta cartaExtra) {
        for (Carta carta : combinacion) {
            if (carta.getNumero() == cartaExtra.getNumero() - 1 || carta.getNumero() == cartaExtra.getNumero() + 1) {
                if (carta.getPalo() == cartaExtra.getPalo()) {
                    cartaExtra.setCombinacion(Carta.Combinacion.ESCALERA);
                }
            }
        }
    }

    public ArrayList<Carta> getCombinacionesNumerosIguales () {
        ArrayList<Carta> combinacionesNumeros = new ArrayList<>();
        for (Carta carta : mano) {
            if (carta.getCombinacion() == Carta.Combinacion.NUMEROS_IGUALES) {
                combinacionesNumeros.add(carta);
            }
        }
        return combinacionesNumeros;
    }

    public void cartaExtraNumerosIguales(ArrayList<Carta> combinacion, Carta cartaExtra) {
        for (Carta carta : combinacion) {
            if (carta.getNumero() == cartaExtra.getNumero()) {
                if (carta.getPalo() != cartaExtra.getPalo()) {
                    cartaExtra.setCombinacion(Carta.Combinacion.NUMEROS_IGUALES);
                }
            }
        }
    }

    //recibe 3 cartas y se fija si son del mismo numero
    public boolean esNumerosIguales(Carta carta1, Carta carta2, Carta carta3) {
        if (carta1.getNumero() == carta2.getNumero() && carta2.getNumero() == carta3.getNumero()) {
            return true;
        }
        return false;
    }

    public void agregarCombinacionNumerosIguales(Carta carta1, Carta carta2, Carta carta3) {
        carta1.setCombinacion(Carta.Combinacion.NUMEROS_IGUALES);
        carta2.setCombinacion(Carta.Combinacion.NUMEROS_IGUALES);
        carta3.setCombinacion(Carta.Combinacion.NUMEROS_IGUALES);
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

    // public void ordenarPorPalo() {
    //     ArrayList<Carta> cartasOrdenadas = new ArrayList<>();
    //     while (cartas.size() > 0) {
    //         int indice = 0;
    //         for (int i = 0; i < cartas.size(); i++) {
    //             if (cartas.get(i).getPalo().ordinal() < cartas.get(indice).getPalo().ordinal()) {
    //                 indice = i;
    //             }
    //         }
    //         cartasOrdenadas.add(cartas.get(indice));
    //         cartas.remove(indice);
    //     }
    //     cartas = cartasOrdenadas;
    // }

    // public void ordenarPorNumero() {
    //     ArrayList<Carta> cartasOrdenadas = new ArrayList<>();
    //     while (cartas.size() > 0) {
    //         int indice = 0;
    //         for (int i = 0; i < cartas.size(); i++) {
    //             if (cartas.get(i).getNumero() < cartas.get(indice).getNumero()) {
    //                 indice = i;
    //             }
    //         }
    //         cartasOrdenadas.add(cartas.get(indice));
    //         cartas.remove(indice);
    //     }
    //     cartas = cartasOrdenadas;
    // }

    // public void ordenarPorNumeroYPalo() {
    //     ArrayList<Carta> cartasOrdenadas = new ArrayList<>();
    //     while (cartas.size() > 0) {
    //         int indice = 0;
    //         for (int i = 0; i < cartas.size(); i++) {
    //             if (cartas.get(i).getNumero() < cartas.get(indice).getNumero()) {
    //                 indice = i;
    //             } else if (cartas.get(i).getNumero() == cartas.get(indice).getNumero()) {
    //                 if (cartas.get(i).getPalo().ordinal() < cartas.get(indice).getPalo().ordinal()) {
    //                     indice = i;
    //                 }
    //             }
    //         }
    //         cartasOrdenadas.add(cartas.get(indice));
    //         cartas.remove(indice);
    //     }
    //     cartas = cartasOrdenadas;
    // }
}
