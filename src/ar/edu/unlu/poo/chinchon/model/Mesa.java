package ar.edu.unlu.poo.chinchon.model;

import java.util.ArrayList;

public class Mesa {
    private ArrayList<Carta> mesa;

    public Mesa() {
        this.mesa = new ArrayList<>();
    }

    //getters
    public ArrayList<Carta> getMesa() {
        return mesa;
    }
    
    public Carta getUltimaCartaMesa() {
        Carta ultimaCarta = mesa.get(mesa.size() - 1);
        return ultimaCarta;
    }
    
    //setters
    public void agregarCartaALaMesa(Carta carta) {
        mesa.add(carta);
    }

    //metodos
    public void cartaInicialMesa(Mazo mazo) {
        int indice = (int) (Math.random() * mazo.getMazoLength());
        mesa.add(mazo.getMazo().get(indice));
        mazo.getMazo().remove(indice);
    }

    public void quitarCartaDeLaMesa() {
        mesa.remove(mesa.size() - 1);
    }

    public void vaciarMesa(Mazo mazo) {
        for (Carta carta : mesa) {
            mazo.agregarCarta(carta);
        }
        mesa.clear();
    }
}