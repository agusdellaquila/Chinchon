public class Carta {
    enum Palo {ORO, COPA, ESPADA, BASTO}
    enum Combinacion {CHINCHON, ESCALERA, NUMEROS_IGUALES, NO_COMBINADA}
    private Palo palo;
    private int numero;
    private Combinacion combinacion;


    public Carta(int numero, Palo palo) {
        this.palo = palo;
        this.numero = numero;
        this.combinacion = Combinacion.NO_COMBINADA;
    }

    public Palo getPalo() {
        return palo;
    }

    public int getNumero() {
        return numero;
    }

    public Combinacion getCombinacion() {
        return combinacion;
    }

    //combinacion to string
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

    public void descombinarCarta() {
        this.combinacion = Combinacion.NO_COMBINADA;
    }

    public void setCombinacion(Combinacion combinacion) {
        this.combinacion = combinacion;
    }
    
    //return Palo from string
    // public static Palo getPaloFromString(String palo) {
    //     switch (palo) {
    //         case "ORO":
    //             return Palo.ORO;
    //         case "COPA":
    //             return Palo.COPA;
    //         case "ESPADA":
    //             return Palo.ESPADA;
    //         case "BASTO":
    //             return Palo.BASTO;
    //         default:
    //             return null;
    //     }
    // }
}