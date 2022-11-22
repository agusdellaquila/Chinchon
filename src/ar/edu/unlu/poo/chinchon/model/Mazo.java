package ar.edu.unlu.poo.chinchon.model;

import java.util.ArrayList;

public class Mazo {
    private ArrayList<Carta> cartas;

    public Mazo() {
        this.cartas = new ArrayList<>();
    }

    //getters
    public ArrayList<Carta> getMazo() {
        return cartas;
    }

    public int getMazoLength() {
        return cartas.size();
    }
    
    //setters
    public void agregarCarta(Carta carta) {
        cartas.add(carta);
    }

    //metodos
    public void generarMazo() {
        for (Carta.Palo palo : Carta.Palo.values()) {
            for (int i = 1; i <= 12; i++) {
                cartas.add(new Carta(i, palo));
            }
        }
    }

    public Carta getRandomCarta() {
        int indice = (int) (Math.random() * cartas.size());
        Carta carta = cartas.get(indice);
        cartas.remove(indice);
        return carta;
    }

    public void ordenarMazo() {
        for (int j = 0; j < cartas.size() - 1; j++) {
            if (cartas.get(j).getNumero() > cartas.get(j + 1).getNumero()) {
                Carta cartaAux = cartas.get(j);
                cartas.set(j, cartas.get(j + 1));
                cartas.set(j + 1, cartaAux);
            } else if (cartas.get(j).getNumero() == cartas.get(j + 1).getNumero()) {
                if (cartas.get(j).getPalo().ordinal() > cartas.get(j + 1).getPalo().ordinal()) {
                    Carta cartaAux = cartas.get(j);
                    cartas.set(j, cartas.get(j + 1));
                    cartas.set(j + 1, cartaAux);
                }
            }
        }
    }

    public void mezclarMazo() {
        ArrayList<Carta> cartasMezcladas = new ArrayList<>();
        while (cartas.size() > 0) {
            int indice = (int) (Math.random() * cartas.size());
            cartasMezcladas.add(cartas.get(indice));
            cartas.remove(indice);
        }
        cartas = cartasMezcladas;
    }

    public void repartirMazo(Jugador jugador) {
        for (int i = 1; i <= 7; i++) {
            jugador.getMano().agregarCarta(cartas.get(0));
            cartas.remove(0);
        }
    }

    public void descombinarTodasLasCartas() {
        for (Carta carta : cartas) {
            carta.descombinarCarta();
        }
    }
}
