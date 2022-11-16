import java.util.ArrayList;

public interface IVista {
    public ArrayList<Carta> mesa = new ArrayList<>();
    
    public ArrayList<Carta> getMesa();
    public void cartaInicialMesa();
    public void getUltimaCartaMesa();
}
