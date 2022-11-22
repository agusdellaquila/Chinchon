package ar.edu.unlu.poo.chinchon.model;

public class Jugador {
    public enum Estado {JUGANDO, GANADOR, PERDEDOR};
    private String nombre;
    private boolean esTurno;
    private Mano mano;
    private int puntos;
    private Estado estado;

    public Jugador(String nombre, boolean esTurno) {
        this.nombre = nombre;
        this.esTurno = esTurno;
        this.mano = new Mano();
        this.puntos = 0;
        this.estado = Estado.JUGANDO;
    }

    //getters
    public String getNombre() {
        return nombre;
    }
    
    public boolean getEsTurno() {
        return esTurno;
    }
    
    public Mano getMano() {
        return mano;
    }

    public int getPuntos() {
        return puntos;
    }

    public Estado getEstado() {
        return estado;
    }
    
    //setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEsTurno(boolean esTurno) {
        this.esTurno = esTurno;
    }
    
    public void setMano(Mano mano) {
        this.mano = mano;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    //metodos
    public Boolean agarrarCartaDelMazo(Mazo mazo) {
        if (mazo.getMazo().size() >= 1) {
            mano.agregarCarta(mazo.getRandomCarta());
        } else {
            return false;
        }
        return true;
    }

    public void agarrarCartaDeLaMesa(Mesa mesa) {
        mano.agregarCarta(mesa.getMesa().get(mesa.getMesa().size() - 1));
        mesa.quitarCartaDeLaMesa();
    }
}
