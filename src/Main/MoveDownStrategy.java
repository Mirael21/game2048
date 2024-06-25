package Main;

/**
 * Стратегия для доски с клетками, сдвиг вниз

 */
public class MoveDownStrategy implements Strategy {
    /**
     * Выполняет текущую стратегию
     * @param board - доска, для которой выполняется стратегия
     */
    public void execute(Board board) {
        int rowCount = board.getRowCount();
        int columnCount = board.getColumnCount();
        Tile[][] tiles = board.getTiles();

        int i;
        for (int j = 0; j < columnCount; j++) {
            i = rowCount - 1;
            while (i > 0) {
                if (tiles[i][j].getWeight() == tiles[i - 1][j].getWeight()) {
                    int doubleWeight = tiles[i][j].getWeight() * 2;
                    board.updateScore(doubleWeight);
                    tiles[i][j].update(doubleWeight);
                    tiles[i - 1][j].update(0);
                }
                i--;
            }
        }

        for (int j = 0; j < columnCount; j++) {
            i = rowCount - 2;
            while (i >= 0) {
                if (i != rowCount - 1 && tiles[i + 1][j].getWeight() == 0 && tiles[i][j].getWeight() != 0) {
                    tiles[i + 1][j].update(tiles[i][j].getWeight());
                    tiles[i][j].update(0);
                    i++;

                    if (i != rowCount - 1 && tiles[i][j].getWeight() == tiles[i + 1][j].getWeight()) {
                        int doubleWeight = tiles[i][j].getWeight() * 2;
                        board.updateScore(doubleWeight);
                        tiles[i + 1][j].update(doubleWeight);
                        tiles[i][j].update(0);
                    }

                }
                else {
                    i--;
                }
            }
        }

    }
}
