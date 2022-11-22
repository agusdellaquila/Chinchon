import ar.edu.unlu.poo.chinchon.controller.Controlador;
import ar.edu.unlu.poo.chinchon.model.Juego;
import ar.edu.unlu.poo.chinchon.views.VistaConsola;

public class Main {
    public static void main(String[] args) {
        VistaConsola vista = new VistaConsola();
        Juego juego = new Juego();
        Controlador controlador = new Controlador(vista, juego);

        controlador.cicloDeJuego();
    }
}