package m6_streams.models;

public enum Difficulty {
//Difucultad del juego
    VERYEASY(1), EASY(2), NORMAL(3), HARD(4), BRAINER(5);
    int index;

    private Difficulty(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

}
