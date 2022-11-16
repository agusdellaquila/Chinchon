public class Carta {
    enum Palo {ORO, COPA, ESPADA, BASTO} //PODRA NO SER PRIVADO?//podra estar definido en otro lado?
    enum Combinacion {ESCALERA, NUMEROS_IGUALES, NO_COMBINADA} //PODRA NO SER PRIVADO?//podra estar definido en otro lado?
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

    public void setCombinacion(Combinacion combinacion) {
        this.combinacion = combinacion;
    }

    public String mostrarCartaString(Carta carta) {
        String cartaString = "";
        cartaString = "[" + this.numero + " " + this.palo + " " + combinacionToString(this.combinacion) + "\u001B[0m" + "]";
        return cartaString;
    }

    public void mostrarCarta(Carta carta) {
        System.out.println("[" + this.numero + " " + this.palo + " " + combinacionToString(this.combinacion) + "\u001B[0m" + "]");
        System.out.print("\u001B[0m");
        // System.out.println("┌───────┐");
        // System.out.println("│       │");
        // if (numero > 9) {
        //     System.out.println("│ " + numero + "    │");
        // } else {
        //     System.out.println("│ " + numero + "     │");
        // }
        // if (palo == Carta.Palo.ORO) {
        //     System.out.println("│ ORO   │");
        // } else if (palo == Carta.Palo.COPA) {
        //     System.out.println("│ COPA  │");
        // } else if (palo == Carta.Palo.ESPADA) {
        //     System.out.println("│ESPADA │");
        // } else if (palo == Carta.Palo.BASTO) {
        //     System.out.println("│ BASTO │");
        // }
        // System.out.println("│       │");
        // System.out.println("└───────┘");
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