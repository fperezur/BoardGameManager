package m6_streams.logic;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import m6_streams.models.Author;
import m6_streams.models.BoardGame;
import m6_streams.models.Difficulty;
import m6_streams.models.Genre;

public class BoardGameManager {

    private final List<BoardGame> boardgames = new ArrayList<>();//Lista para utilizar en los streams
    private final List<Author> authors = new ArrayList<>();//Lista para realizar ejercicio de uso de ésta

    {//Bloque inicializador de instancias para crear los registros a trabajar.
        //Propueta ejercicio substituir esto por una llamada a BBDD(JDBC)
        authors.add(new Author("Vital", "Lacerda", LocalDate.of(1967, Month.MARCH, 21), new Locale("portugues", "Portugal")));
        authors.add(new Author("Vlaada", "Chvátil", LocalDate.of(1971, Month.JANUARY, 10), new Locale("checo", "Republica Checa")));
        authors.add(new Author("Uwe", "Rosenberg", LocalDate.of(1970, Month.MARCH, 27), new Locale("aleman", "Alemania")));
        boardgames.add(new BoardGame("The Gallerist", 4, 8.5, authors.get(0), new Genre[]{Genre.EUROGAMES, Genre.PUZZLE}, Difficulty.BRAINER));
        boardgames.add(new BoardGame("The Gallerist", 4, 8.5, authors.get(0), new Genre[]{Genre.EUROGAMES, Genre.PUZZLE}, Difficulty.BRAINER));
        boardgames.add(new BoardGame("Kanban: Driver's Edition", 5, 7.7, authors.get(0), new Genre[]{Genre.EUROGAMES}, Difficulty.BRAINER));
        boardgames.add(new BoardGame("Kanban: Driver's Edition", 5, 7.7, authors.get(0), new Genre[]{Genre.EUROGAMES}, Difficulty.BRAINER));
        boardgames.add(new BoardGame("CO2", 4, 9, authors.get(0), new Genre[]{Genre.EUROGAMES, Genre.COOP}, Difficulty.BRAINER));
        boardgames.add(new BoardGame("Dragon Keepers", 2, 4.5, authors.get(0), new Genre[]{Genre.AMERITRASH, Genre.FANTASY}, Difficulty.NORMAL));
        boardgames.add(new BoardGame("Vinhos", 6, 8.5, authors.get(0), new Genre[]{Genre.AMERITRASH}, Difficulty.HARD));
        boardgames.add(new BoardGame("Times", 3, 3.2, authors.get(2), new Genre[]{Genre.EUROGAMES, Genre.PUZZLE}, Difficulty.VERYEASY));
        boardgames.add(new BoardGame("Le Havre", 4, 6.7, authors.get(2), new Genre[]{Genre.EUROGAMES, Genre.FAMILIAR}, Difficulty.NORMAL));
        boardgames.add(new BoardGame("Agricola", 5, 9.2, authors.get(2), new Genre[]{Genre.EUROGAMES, Genre.FANTASY}, Difficulty.NORMAL));
        boardgames.add(new BoardGame("A Feast for Odin", 4, 7, authors.get(2), new Genre[]{Genre.EUROGAMES, Genre.PUZZLE}, Difficulty.HARD));
        boardgames.add(new BoardGame("Patchwork", 2, 8.8, authors.get(2), new Genre[]{Genre.FAMILIAR, Genre.PUZZLE}, Difficulty.EASY));
        boardgames.add(new BoardGame("Dungeons Lords", 6, 4, authors.get(1), new Genre[]{Genre.EUROGAMES, Genre.AMERITRASH}, Difficulty.BRAINER));
        boardgames.add(new BoardGame("Through the Ages: A Story of Civilization", 4, 10, authors.get(1), new Genre[]{Genre.EUROGAMES, Genre.FANTASY}, Difficulty.BRAINER));
        boardgames.add(new BoardGame("Galaxy Trucker", 4, 5.8, authors.get(1), new Genre[]{Genre.COOP, Genre.FAMILIAR}, Difficulty.VERYEASY));
        boardgames.add(new BoardGame("Space Alert", 7, 8, authors.get(1), new Genre[]{Genre.COOP, Genre.PUZZLE}, Difficulty.NORMAL));
        boardgames.add(new BoardGame("Codenames", 8, 7.6, authors.get(1), new Genre[]{Genre.AMERITRASH, Genre.FAMILIAR}, Difficulty.EASY));

    }

    //Metodo para contar el total de elementos en la coleccion
    public long countBoardGames() {
        return boardgames
                .stream()
                .count();//Cuenta todos los juegos disponibles
    }
    public long countBoardGames(Stream<BoardGame> sbg) {
        return sbg
                .count();//Cuenta todos los juegos disponibles
    }

    //Metodo para contar el total de juegos sin repetir en la coleccion
    public long countDistinctBoardGames() {
        return boardgames
                .stream()
                .distinct()//metodo intermedio en la linea que quita duplicados
                .count();
                
    }

    //Metodo que nos imprime <lim> de elementos que recibimos como paramatros
    //Si superamos el limite de la coleccion, nos lo presenta todos
    //Si recibimos un numero negativo como limite, lanza una excepcion: IlegalArgumentException(RuntimeException)
    //Si recibimos un cero como limite: no provoca ninguna Excepcion y no imprime nada
    //El orden de los metodos intermedios influye en el resultado, probar con cambiar distinct antes y despues de limit
    public void showNBoardGames(long lim) {
        boardgames
                .stream()
                .distinct()
                .limit(lim)//Limita el numero de elementos del stream al numero pasado por argumento.  Si el numero es
                //superior al numero de elementos existentes muestra todos los elementos.
                //Si pasamos lim negativo provoca IllegalArgumentException
                .forEach(bg -> System.out.println(bg.toString()));
    }

    //Sobrecargamos el metodo anterior añadiendo otro long
    public void showNBoardGames(long lim, long skp) {
        boardgames
                .stream()
                .distinct()
                .skip(skp)//Se salta n elementos, donde n es el numero de elementos que estamos saltando
                //Si pasamos skp negativo provoca IllegalArgumentException
                .limit(lim)
                .forEach(bg -> System.out.println(bg.toString()));
    }

    public boolean isOverTheNote(double note) {
        return boardgames
                .stream()
                //Devuelve true si cualquiera de los elementos cumple con la condicion
                //Funciona en modo cortocircuito.
                .anyMatch(bg -> bg.getRating() > note);

    }

    public boolean isOverThePlayers(int players, int skp) {
        return boardgames
                .stream()
                .distinct()
                .skip(skp)
                 //Devuelve true si todos los elementos cumplen con la condicion
                //Funciona en modo cortocircuito.            
                .allMatch(bg -> bg.getNumbersOfPlayers() > players);

    }

    public boolean isOverThePlayers(int players) {
        return boardgames
                .stream()
                .distinct()
                //ES como un for-each, peek es un metodo intermedio que nos devuelve otro stream
                //Funciona en modo lazy
                // .peek((BoardGame boardGame)-> System.out.println(boardGame.toString()))
                .peek(System.out::println)
                .allMatch(bg -> bg.getNumbersOfPlayers() > players);

    }

    public String findByAuthor(String name) {
        System.out.println("Resultados del autor " + name + ":");
        print(getDistinctBoardGames()
                //Hace un filtro en base a una condicion.
                .filter(bg -> bg.getAuthor().getName().equals(name)));
        return "";

    }

    public void print(Stream<?> st) {
        st.forEach(ele -> System.out.println(ele));

    }

    public Stream<BoardGame> getDistinctBoardGames() {
        return boardgames
                .stream()
                .distinct(); //metodo intermedio en la linea
    }

    public String findOneBGPass() {
        return "Juego de mesa aprobado: " + getDistinctBoardGames()
                .filter(bg -> bg.getRating() >= 5)
                //Convierte el stream en modo paralelo aumentando la eficiencia a cambio de un comportamiento
                //de naturaleza estocastica, es decir, los resultados son impredecibles(sometido al azar)
                .parallel()
                //Devuelve cualquiera de los resultados.
                .findAny();
    }

    public String findByTitle(String name) throws FileNotFoundException {
        return "Juego encontrado: " + getDistinctBoardGames()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                //Devuelve el primero de los resultados encapsulado en un Optional.
                .findFirst()
                //Desempaqueta el Optional si no esta empty
                //Si esta empty lanza la excepcion especificada en el argumento.
                .orElseThrow(() -> new FileNotFoundException()).toString();

    }

    public String showBetterValue(Comparator<BoardGame> comp) {
        return "Juego mejor valorado: " + getDistinctBoardGames()
                //Atencion truco para convertir en un casteo de double a int para comparar con los decimales

                .max(comp);
    }

    public String showWortsValue(Comparator<BoardGame> comp) {
        return "Juego peor valorado: " + getDistinctBoardGames()
                //Atencion truco para convertir en un casteo para comparar con los decimales
                .min(comp);
    }

    public String showBetterAndWortsValue(boolean isBetter) {
        Comparator<BoardGame> comp = ((a, b) -> (int) (a.getRating() * 100) - (int) (b.getRating() * 100));
        return (isBetter) ? showBetterValue(comp) : showWortsValue(comp);

    }

    public String showAuthors() {
        Stream<Author> sAuthors = getDistinctBoardGames().map((BoardGame bg) -> bg.getAuthor());
        print(sAuthors);
        return "";
    }

    public String changeNationallity() {
        Stream<Author> sAuthors = getDistinctBoardGames()
                //                .filter(bg -> bg.getAuthor().getNationallity().equalsIgnoreCase("Alemania"))
                .map((BoardGame bg) -> {
                    if (bg.getAuthor().getNationallity().equalsIgnoreCase("Alemania")) {
                        bg.getAuthor().setNationallity(Locale.FRANCE);
                    }
                    return bg.getAuthor();
                })
                .distinct();
        print(sAuthors);
        return "";
    }

    public String showAverage() {
        return "La media de valoracion de los juegos es: " + String.format("%.2f", getDistinctBoardGames()
                .mapToDouble((BoardGame bg) -> bg.getRating())
                .average().getAsDouble());
    }

    public String showTotalSum() {
        return "La suma de valoracion de los juegos es: " + String.format("%.2f", getDistinctBoardGames()
                .mapToDouble((BoardGame bg) -> bg.getRating())
                .sum());

    }

    public String showYearYoungAuthor() {
        return "El año del autor más joven es: " + getDistinctBoardGames()
                .mapToInt((BoardGame bg) -> bg.getAuthor().getDateOfBird().getYear())
                .max().getAsInt();

    }

    public String showYearOldAuthor() {
        return "El año del autor mas viejo es: " + getDistinctBoardGames()
                .mapToInt((BoardGame bg) -> bg.getAuthor().getDateOfBird().getYear())
                .min().getAsInt();

    }

    public List<BoardGame> listaBG(String name) {
        return getDistinctBoardGames()
                .filter(bg -> bg.getAuthor().getName().equals(name))
                .collect(Collectors.toList());
    }

    public String showCatalogueTittles() {
        return "Catalogo de juegos disponibles: " + getDistinctBoardGames()
                .map((BoardGame bg) -> bg.getName())
                .reduce((String bg1, String bg2) -> "\t" + bg1 + "\n\t" + bg2)
                .get();
    }

    public Map<String, List<BoardGame>> getToMap() {
        return getDistinctBoardGames()
                .collect(Collectors.groupingBy(bg -> bg.getAuthor().getCompleteName()));
    }

    //NO FUNCIONA
//    public Map<String, String> getToMap1(){
//        return getDistinctBoardGames()
//                .collect(Collectors.toMap(k -> k.getAuthor().getCompleteName(), v -> v.getName()));
//    }
    //SI FUCIONA
    public Map<String, Double> getToMap1() {
        return getDistinctBoardGames()
                .collect(Collectors.toMap(k -> k.getName(), v -> v.getRating()));
    }

    public Map<Author, List<BoardGame>> getToMap2() {
        return getDistinctBoardGames()
                .collect(Collectors.groupingBy(bg -> bg.getAuthor()));
    }

    public Map<Difficulty, List<BoardGame>> getToMapDificulty() {
        return getDistinctBoardGames()
                .collect(Collectors.groupingBy(bg -> bg.getDifficulty()));
    }

    public Map<Boolean, List<BoardGame>> partitionNumberOfPlayers(int numOfPlayers) {
        return getDistinctBoardGames()
                .collect(Collectors.partitioningBy((BoardGame bg) -> bg.getNumbersOfPlayers() >= numOfPlayers));
    }
    
    public String showInfo(){
        return getDistinctBoardGames()
                .map(mp -> mp.getName() + " " + Arrays.toString(mp.getGenres()))
                .collect(Collectors.joining("\n"));
    }
    
    public String showAvaregeValues(String genre){
        return "La media de los juegos del genero " + genre + " :" + getDistinctBoardGames()
                
            .filter(sv -> Arrays.asList(sv.getGenres()).stream().anyMatch(g -> g.name().equals(genre.toUpperCase())))
                .collect(Collectors.averagingDouble((BoardGame sd) -> sd.getRating()));
        
    }

}
