public interface Piece {


    @Override
    boolean equals(Object obj);

    @Override
    String toString();

    int expand();

    boolean isBlank();

    void setBlank();

    /**
     * 初始化棋子为最小棋子
     */
    void init();
}
