package Main;

/**
 * Стратегия для доски с клетками, сдвиг вправо

 */
public class MoveRightStrategy implements Strategy {
    /**
     * Выполняет текущую стратегию
     * @param board - доска, для которой выполняется стратегия
     */
    public void execute(Board board) {
        int rowCount = board.getRowCount();
        int columnCount = board.getColumnCount();
        Tile[][] tiles = board.getTiles();

        int j;
        for (int i = 0; i < rowCount; i++) {
            j = columnCount - 1;
            while (j > 0) {
                if (tiles[i][j].getWeight() == tiles[i][j - 1].getWeight()) {
                    int doubleWeight = tiles[i][j].getWeight() * 2;
                    board.updateScore(doubleWeight);
                    tiles[i][j].update(doubleWeight);
                    tiles[i][j - 1].update(0);
                }
                j--;
            }
        }

        for (int i = 0; i < rowCount; i++) {
            j = columnCount - 2;
            while (j >= 0) {
                if (j != columnCount - 1 && tiles[i][j + 1].getWeight() == 0 && tiles[i][j].getWeight() != 0) {
                    tiles[i][j + 1].update(tiles[i][j].getWeight());
                    tiles[i][j].update(0);
                    j++;

                    if (j != columnCount - 1 && tiles[i][j].getWeight() == tiles[i][j + 1].getWeight()) {
                        int doubleWeight = tiles[i][j].getWeight() * 2;
                        board.updateScore(doubleWeight);
                        tiles[i][j + 1].update(doubleWeight);
                        tiles[i][j].update(0);
                    }
                }
                else {
                    j--;
                }
            }

        }
    }
}
