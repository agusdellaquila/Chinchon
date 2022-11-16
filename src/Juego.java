import java.util.ArrayList;
import java.util.Scanner;

public class Juego {
    private Mazo mazo;
    private ArrayList<Jugador> jugadores; //2 a 4 jugadores
    private Mesa mesa;
    static Scanner sc;

    public Juego() {
        this.mazo = new Mazo();
        this.jugadores = new ArrayList<>();
        this.mesa = new Mesa();
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    //----------------------------------------------------------

    public void jugar(Juego juego) {
        juego.iniciarJugadores();
        juego.iniciarMazo();
        juego.mostrarJugadores();
        juego.iniciarMesa();
        Boolean partida = false;  
        sc = new Scanner(System.in); 
        while (!partida) {
                juego.mostrarMesa();

                //turnos
                Jugador jugadorActual = juego.revisarTurno(jugadores);

                //Turno de
                System.out.println("\u001B[36m" +"----------------------------------");
                System.out.println("Turno de " + jugadorActual.getNombre());
                System.out.println("----------------------------------" + "\u001B[0m");  
                
                //ordenar mano
                jugadorActual.getMano().ordenarPorPaloYNumero(jugadorActual.getMano());
                
                //mostrar su mano inicial
                jugadorActual.getMano().mostrarMano(jugadorActual);

                //opciones de levantado
                System.out.println("De donde desea levantar una carta?");
                System.out.println("1. Agarrar carta del mazo");
                System.out.println("2. Agarrar carta de la mesa");
                int opcion = sc.nextInt();

                if (opcion == 1) {
                    jugadorActual.agarrarCartaDelMazo(mazo);
                } else if (opcion == 2) {
                    jugadorActual.agarrarCartaDeLaMesa(mesa);
                } else {
                    System.out.println("Opcion invalida");
                }

                //mostrar su mano con carta extra
                System.out.println(jugadorActual.getNombre() + ",Tu mano quedo asi: ");
                jugadorActual.getMano().mostrarMano(jugadorActual);

                //revisar si tiene combinaciones
                //mover a while
                System.out.println(jugadorActual.getNombre() + ", Desea agregar combinacion? ");
                System.out.println("1. Escalera de 3");
                System.out.println("2. Numeros iguales de 3");
                System.out.println("3. Agregar extra a combinacion de escalera existente");
                System.out.println("4. Agregar extra a combinacion de numeros iguales existente");
                System.out.println("5. No deseo combinar");
                System.out.println("----------------------------------");
                int combinaciones = sc.nextInt();

                if (combinaciones == 1) {
                    System.out.println("Ingrese carta/s para formar Escalera");
                    System.out.println("----------Ingrese las cartas a combinar----------");

                    Carta carta1 = inputCarta(jugadorActual);
                    Carta carta2 = inputCarta(jugadorActual);
                    Carta carta3 = inputCarta(jugadorActual);

                    Boolean escalera = jugadorActual.getMano().esEscalera(carta1, carta2, carta3);

                    if (escalera) {
                        jugadorActual.getMano().agregarCombinacioneEscalera(carta1, carta2, carta3);
                    } else {
                        System.out.println("No es escalera");
                    }
                } else if (combinaciones == 2) {
                    System.out.println("Ingrese carta/s para formar Numeros iguales");
                    System.out.println("----------Ingrese las cartas a combinar----------");

                    Carta carta1 = inputCarta(jugadorActual);
                    Carta carta2 = inputCarta(jugadorActual);
                    Carta carta3 = inputCarta(jugadorActual);

                    Boolean numerosIguales = jugadorActual.getMano().esNumerosIguales(carta1, carta2, carta3);

                    if (numerosIguales) {
                        jugadorActual.getMano().agregarCombinacionNumerosIguales(carta1, carta2, carta3);
                    } else {
                        System.out.println("No es numeros iguales");
                    }
                } else if (combinaciones == 3) {
                    System.out.println("Ingrese la carta extra para agregar a la combinacion de escalera");

                    Carta carta = inputCarta(jugadorActual);
                    ArrayList<Carta> combinacionesEscalera = jugadorActual.getMano().getCombinacionesEscalera();
                    jugadorActual.getMano().cartaExtraEscalera(combinacionesEscalera, carta);

                } else if (combinaciones == 4) {
                    System.out.println("Ingrese la carta extra para agregar a la combinacion de numeros iguales");

                    Carta carta = inputCarta(jugadorActual);
                    ArrayList<Carta> combinacionesNumeros = jugadorActual.getMano().getCombinacionesNumerosIguales();
                    jugadorActual.getMano().cartaExtraEscalera(combinacionesNumeros, carta);
                } else if (combinaciones == 5) {
                    System.out.println("No desea combinar");
                } else {
                    System.out.println("Opcion invalida");
                }

                //mostrar su mano para que vea cual tirar
                jugadorActual.getMano().mostrarMano(jugadorActual);

                //jugarla
                System.out.println("----------DEJE UNA CARTA EN LA MESA----------");
                Carta cartaPorJugar = inputCarta(jugadorActual);
                jugadorActual.getMano().jugarCarta(cartaPorJugar);
                mesa.agregarCartaALaMesa(cartaPorJugar);

                //revisar si se descombino
                jugadorActual.getMano().revisarSiSeDescombino(jugadorActual.getMano());
                
                //mostrar como queda su mano
                jugadorActual.getMano().mostrarMano(jugadorActual);

                //cambio de turnos
                Jugador jugadorSiguiente = juego.revisarNoTurno(jugadores);
                jugadorActual.setEsTurno(false);
                jugadorSiguiente.setEsTurno(true);
                
                
                //terminar partida
                System.out.println("Pulse 1 para seguir, 0 para salir");
                opcion = sc.nextInt();
                if (opcion == 0) {
                    partida = true;
                }
        }
    }

    public Carta inputCarta(Jugador jugadorActual) {
        sc = new Scanner(System.in);
        System.out.println("Ingrese el numero y el palo (dejando una coma entremedio: N,P) de la carta deseada: ");
        String carta = sc.next();
        //parse carta
        carta.trim();
        String[] cartaParse = carta.split(",");
        int numero = Integer.parseInt(cartaParse[0]);
        Carta.Palo palo = Carta.Palo.valueOf(cartaParse[1].toUpperCase());
        Carta cartaPorJugar = jugadorActual.getMano().buscarCartaEnMano(numero, palo);
        //validar q exista
        return cartaPorJugar;
    }

    public Jugador revisarTurno(ArrayList<Jugador> jugadores) {
        Jugador turnoJugador = null;
        for (Jugador jugador : jugadores) {
            if (jugador.getEsTurno() == true) {
                turnoJugador = jugador;
            }
        }
        return turnoJugador;
    }

    public Jugador revisarNoTurno(ArrayList<Jugador> jugadores) {
        Jugador noTurnoJugador = null;
        for (Jugador jugador : jugadores) {
            if (jugador.getEsTurno() == false) {
                noTurnoJugador = jugador;
            }
        }
        return noTurnoJugador;
    }

    public void iniciarJugadores() {
        sc = new Scanner(System.in);
        System.out.println("El juego sera de 2 (DOS) Jugadores.");
        int cantidadJugadores = 2;
        if (cantidadJugadores >= 2 && cantidadJugadores <= 4) {
            for (int i = 1; i <= cantidadJugadores; i++) {
                System.out.println("Ingrese el nombre del jugador " + (i));
                Boolean inicia = false;
                String nombre = sc.next();
                if (i == 1) {
                    inicia = true;
                }
                jugadores.add(new Jugador(nombre, inicia));
            }
        } else {
            System.out.println("Cantidad de jugadores invalida");
        }
    }

    public void iniciarMazo() {
        mazo.generarMazo();
        mazo.mezclarMazo();
        for (Jugador jugador : jugadores) {
            mazo.repartirMazo(jugador);
        }
    }

    public void iniciarMesa() {
        mesa.cartaInicialMesa(mazo);
    }

    public void mostrarMesa() {
        System.out.println("\u001B[35m" + "----------------Mesa----------------" + "\u001B[0m");
        System.out.println("La carta en mesa es: ");
        mesa.getUltimaCartaMesa();
    }

    public void mostrarJugadores() {
        System.out.println("----------------Jugadores----------------");
        for (Jugador jugador : jugadores) {
            System.out.println("\u001B[33m" + jugador.getNombre() + "\u001B[0m");
        }
    }
}

// while (jugadores.size() > 1) {
//     for (Jugador jugador : jugadores) {
//         if (jugador.getEsTurno()) {
//             jugador.jugarCarta(mesa);
//             if (jugador.getMano().getManoLength() == 0) {
//                 jugador.setEsGanador(true);
//                 jugadores.remove(jugador);
//             }
//         }
//     }
// }


//---------------JUGAR-----------------
// public void jugar() {
    // mazo.generarMazo();
    // mazo.mezclarMazo();
    // Jugador jugador1 = new Jugador("Jugador 1", true);
    // Jugador jugador2 = new Jugador("Jugador 2", false);
    // jugadores.add(jugador1);
    // jugadores.add(jugador2);
    // mazo.repartirMazo(jugador1);
    // mazo.repartirMazo(jugador2);
    // mesa.cartaInicialMesa(mazo);
    //mostrar jugadores
    // System.out.println("-----------------------------PERFIL-----------------------------");
    // for (Jugador jugador : jugadores) {
    //     System.out.println(jugador.getNombre());
    //     System.out.println("Turno:");
    //     System.out.println(jugador.getEsTurno());
    //     System.out.println("Puntos");
    //     System.out.println(jugador.getPuntos());
    //     System.out.println("Ganador");
    //     System.out.println(jugador.getEsGanador());
    // }
    //mostrar mazo
    // System.out.println("-----------------------------MAZO-----------------------------");
    // System.out.println("Cartas en el mazo: " + mazo.getMazoLength());
    //for getcartaslength mostrar carta
    // for (int i = 0; i < mazo.getMazoLength(); i++) {
    //     System.out.println(mazo.getMazo().get(i).getNumero()  + " " + mazo.getMazo().get(i).getPalo());
    // }
    //mostrar manos
//     System.out.println("-----------------------------MANOS-----------------------------");
//     System.out.println("Jugador 1:");
//     jugador1.getMano().mostrarMano();
//     System.out.println("Jugador 2:");
//     jugador2.getMano().mostrarMano();
//     System.out.println("-----------------------------MESA-----------------------------");
//     System.out.println("Carta Inicial en mesa:");
//     mesa.getUltimaCartaMesa();
//     System.out.println("-----------------------------LEVANTAR-----------------------------");
//     System.out.println("Jugador 1:");
//     System.out.println("¿Desea levantar una carta del mazo(1) o de la mesa(2)?");
//     //input from scaner selection 1 or 2
//     Scanner sc = new Scanner(System.in);
//     int seleccion = sc.nextInt();
//     if (seleccion == 1) {
//         jugador1.agarrarCartaDelMazo(mazo);
//         jugador1.getMano().mostrarMano();
//     } else if (seleccion == 2) {
//         jugador1.agarrarCartaDeLaMesa(mesa);
//         jugador1.getMano().mostrarMano();
//     }
//     System.out.println("-----------------------------MESA-----------------------------");
//     System.out.println("Carta ACTUAL en mesa:");
//     mesa.getUltimaCartaMesa();
//     System.out.println("-----------------------------LEVANTAR-----------------------------");
//     System.out.println("Jugador 1:");
//     System.out.println("Que carta desea jugar?");
//     System.out.println("Ingrese el numero de la carta que desea jugar");
//     int numero = sc.nextInt();
//     System.out.println("Ingrese el palo de la carta que desea jugar");
//     String p = sc.next(); //validar
//     Carta.Palo palo = Carta.Palo.valueOf(p);
//     Carta cartaPorJugar = jugador1.getMano().buscarCartaEnMano(numero, palo);
//     jugador1.getMano().jugarCarta(cartaPorJugar);
//     mesa.agregarCartaALaMesa(cartaPorJugar);      
//     jugador1.getMano().mostrarMano();
//     System.out.println("-----------------------------MESA-----------------------------");
//     System.out.println("Carta ACTUAL en mesa:");
//     mesa.getUltimaCartaMesa();


//     sc.close();
// }