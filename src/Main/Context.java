package Main;

/**
 * Контекст, который отвечает за управление стратегиями
 */
public class Context {
    private Strategy strategy;
    private Board board;
    private BoardSaver boardSaver;

    /**
     * Устанавливает соответствующую стратегию
     * @param strategy - стратегию, которую нужно установить
     * @param board - для какой доски
     */
    public void setStrategy(Strategy strategy, Board board) {
        this.strategy = strategy;
        this.board = board;
        boardSaver = board.getBoardSaver();
    }

    /**
     * Выполняет стратегию
     */
    public void executeStrategy() {
        boardSaver.save();
        strategy.execute(board);
        board.generateNewTile();
    }
}
