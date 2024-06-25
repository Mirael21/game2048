package Main;

/**
 * Отвечает за сохранение состояния таблицы

 */
public class BoardSaver {
    private BoardSaveFile saveFile;
    private Board board;

    /**
     * Конструктор с параметрами
     * @param board - доска, в которой нужно отвечать за сохранение
     */
    public BoardSaver(Board board) {
        this.board = board;
    }

    /**
     * Сохраняет состояние доски
     */
    public void save() {
        saveFile = board.createSaveFile();
    }

    /**
     * Отменяет последний ход
     */
    public void undo() {
        if (saveFile != null) {
            saveFile.restore();
        }
    }
}
