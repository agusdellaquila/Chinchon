public class Main {
    public static void main(String[] args) {
        Vista vista = new Vista();
        Juego juego = new Juego();
        Controlador controlador = new Controlador(vista, juego);

        controlador.cicloDeJuego();
        // presentador.cicloDeJuego();
    }
}