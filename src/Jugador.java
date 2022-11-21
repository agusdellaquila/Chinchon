public class Jugador {
    private String nombre;
    private boolean esTurno;
    private Mano mano;
    private int puntos;
    private boolean esPerdedor;

    public Jugador(String nombre, boolean esTurno) {
        this.nombre = nombre;
        this.esTurno = esTurno;
        this.mano = new Mano();
        this.puntos = 0;
        this.esPerdedor = false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getEsTurno() {
        return esTurno;
    }

    public void setEsTurno(boolean esTurno) {
        this.esTurno = esTurno;
    }

    public Mano getMano() {
        return mano;
    }
    
    public void setMano(Mano mano) {
        this.mano = mano;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public boolean getEsPerdedor() {
        return esPerdedor;
    }

    public void setEsPerdedor(boolean esPerdedor) {
        this.esPerdedor = esPerdedor;
    }

     //agarrar carta del mazo
    public void agarrarCartaDelMazo(Mazo mazo) {
        mano.agregarCarta(mazo.getRandomCarta());
    }

    //agarrar carta de la mesa
    public void agarrarCartaDeLaMesa(Mesa mesa) {
        mano.agregarCarta(mesa.getMesa().get(mesa.getMesa().size() - 1));
        mesa.quitarCartaDeLaMesa();
        if (mesa.getMesa().size() < 1) {
            mesa.agregarCartaALaMesa(new Carta(0, Carta.Palo.COPA)); 
            //0 COPA SIGNIFICA QUE NO HAY CARTAS EN LA MESA, CAMBIARLO POR UNA CARTA QUE NO SE PUEDA JUGAR
        }
    }
}
