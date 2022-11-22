package ar.edu.unlu.poo.chinchon.model;

public class Carta {
    public enum Palo {ORO, COPA, ESPADA, BASTO}
    enum Combinacion {CHINCHON, ESCALERA, NUMEROS_IGUALES, NO_COMBINADA}
    private Palo palo;
    private int numero;
    private Combinacion combinacion;


    public Carta(int numero, Palo palo) {
        this.palo = palo;
        this.numero = numero;
        this.combinacion = Combinacion.NO_COMBINADA;
    }
    
    //getters
    public Palo getPalo() {
        return palo;
    }

    public int getNumero() {
        return numero;
    }

    public Combinacion getCombinacion() {
        return combinacion;
    }

    //setters
    public void setCombinacion(Combinacion combinacion) {
        this.combinacion = combinacion;
    }

    public void descombinarCarta() {
        this.combinacion = Combinacion.NO_COMBINADA;
    }

    //metodos
    public String combinacionToString(Combinacion combinacion) {
        switch (combinacion) {
            case ESCALERA:
                return "\u001B[32m" + "C Esc";
            case NUMEROS_IGUALES:
                return "\u001B[32m" + "C N°s";
            case NO_COMBINADA:
                return "\u001B[31m" + "NC";
            default:
                return "Error en el tipo de combinacion";
        }
    }
}