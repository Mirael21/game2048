package Main;

/**
 * Представляет собой файл сохранения доски

 */
public class BoardSaveFile {
    private Board board;
    private Tile[][] tiles;
    private int rowCount;
    private int columnCount;
    private int score;

    /**
     * Конструктор с параметрами
     * @param board - доска, для которой создается сохранение
     * @param tiles - ссылка на массив клеток
     * @param rowCount - количество строк
     * @param columnCount - количество столбцов
     * @param score - текущий счет
     */
    public BoardSaveFile(Board board, Tile[][] tiles, int rowCount, int columnCount, int score) {
        this.board = board;

        this.score = score;

        this.tiles = board.initNewTiles(rowCount, columnCount);
        board.update(tiles, this.tiles);

        this.rowCount = rowCount;
        this.columnCount = columnCount;
    }

    /**
     * Восстанавливает доску до текущего состояния
     */
    public void restore() {
        board.setRowCount(rowCount);
        board.setColumnCount(columnCount);
        board.update(tiles);
        board.setScore(score);
    }
}
