public interface Piece {


    @Override
    boolean equals(Object obj);

    @Override
    String toString();

    int expand();

    boolean isBlank();

    void setBlank();

    void init();
}
