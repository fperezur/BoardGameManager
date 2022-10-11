package m6_streams.client;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import m6_streams.logic.BoardGameManager;
import m6_streams.models.BoardGame;
import m6_streams.models.Difficulty;

public class BoardGameClient {

    public static void main(String[] args) {

        var bgm = new BoardGameManager();
        //Imprimimos el total de juegos en la coleccion
        System.out.println("Numero de juegos actuales: " + bgm.countBoardGames());
        //Imprimimos el total de juegos distintos (sin repetidos), en la coleccion 
        System.out.println("Numero de juegos actuales distintos: " + bgm.countDistinctBoardGames());
        //Imprimimos 10 lineas, las que pasamos por paramatro al metodo
        bgm.showNBoardGames(10);
        espaciar(10, "=");
        //Imprimimos el metodo sobrecargado con dos parametros, limite y skip, saltarse un numero de elmentos
        bgm.showNBoardGames(5, 30);
        espaciar(20, "-");
        System.out.println("Juegos que superan la nota 3: " + bgm.isOverTheNote(3));

        espaciar(40, "Numero de jugadores: " + bgm.isOverThePlayers(1));

        espaciar(20, bgm.findByAuthor("Vlaada"));

        espaciar(40, bgm.findOneBGPass());

        try {
            espaciar(40, bgm.findByTitle("nn"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BoardGameClient.class.getName()).log(Level.SEVERE, "Juego no encontrado");
        }

        espaciar(40, bgm.showBetterValue((a, b) -> a.getNumbersOfPlayers() - b.getNumbersOfPlayers()));
        espaciar(40, bgm.showWortsValue((a, b) -> a.getNumbersOfPlayers() - b.getNumbersOfPlayers()));

        espaciar(40, bgm.showAuthors());
        espaciar(40, bgm.changeNationallity());
        espaciar(40, bgm.showAverage());
        espaciar(40, bgm.showTotalSum());
        espaciar(40, bgm.showYearOldAuthor());
        espaciar(40, bgm.showYearYoungAuthor());
        //Tiene que mostras dos estrellas, pendiente ver codificacion de caracteres
//        System.out.println("\u2605\u2606");

        List<List<BoardGame>> list = new ArrayList<>();
        list.add(bgm.listaBG("Vital"));
        list.add(bgm.listaBG("Uwe"));
        list.add(bgm.listaBG("Vlaada"));
        list.forEach(l -> l.stream().forEach(System.out::println));
//    list.stream().flatMap(cl -> cl.stream().map(a -> a))
//            .forEach(System.out::println);
        Stream<Integer> nums = Stream.of(20, 5, 8, 3, 9);
//muestra los pares y el total de éstos
//System.out.println("total: "+nums
//	.filter(n->n%2==0)
//	.peek(n->System.out.println("par: "+n))
//	.count());

//Muestra el menor(primero del stream)
//        Optional<Integer> opc = nums
//                .min((a, b) -> a - b);
//        System.out.println("El minimo es: " + opc.get());
//Muestra el mayor(ultimo del stream)        
        Optional<Integer> opc1 = nums
                .max((a, b) -> a - b);
        System.out.println("El maximo es: " + opc1.get());
                
        espaciar(40, bgm.showCatalogueTittles());
        
        espaciar(40, bgm.getToMap1().get("CO2").toString());
        espaciar(40, bgm.getToMapDificulty().get(Difficulty.HARD).toString());
        
        espaciar(40, bgm.partitionNumberOfPlayers(5).get(true).toString());
        
        espaciar(40, bgm.showInfo());
        
        espaciar(40, bgm.showAvaregeValues("eurogames"));
        espaciar(40, bgm.countBoardGames(bgm.getDistinctBoardGames()) + "");
        
        
    }
    
    

    public static void espaciar(int n) {
        System.out.println("=".repeat(n));
    }

    public static void espaciar(int n, String text) {
        System.out.println(text + "\n" + "=".repeat(n));
    }
}
