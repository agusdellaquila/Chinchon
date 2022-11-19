import java.util.ArrayList;

public class Mesa {
    private ArrayList<Carta> mesa;

    public Mesa() {
        this.mesa = new ArrayList<>();
    }

    public ArrayList<Carta> getMesa() {
        return mesa;
    }
    
    public void agregarCartaALaMesa(Carta carta) {
        mesa.add(carta);
    }

    public Carta getUltimaCartaMesa() {
        Carta ultimaCarta = mesa.get(mesa.size() - 1);
        return ultimaCarta;
    }

    //poner una carta random en la mesa
    public void cartaInicialMesa(Mazo mazo) {
        int indice = (int) (Math.random() * mazo.getMazoLength());
        mesa.add(mazo.getMazo().get(indice));
        mazo.getMazo().remove(indice);
    }

    //quitar la ultima carta de la mesa
    public void quitarCartaDeLaMesa() {
        mesa.remove(mesa.size() - 1);
    }
}