package Main;

/**
 * Интерфейс для конструирования фреймов

 */

public interface MyFrame {
    /**
     * Создает фрейм и настраивает его
     */
    void createFrame();

    /**
     * Добавляет кнопки на фрейм
     */
    void addButtons();

    /**
     * Добавляет графические элементы на фрейм
     */
    void addGraphics();

    /**
     * Добавляет доску с клетками на фрейм
     * @param rowCount - количество строк
     * @param columnCount - количество столбцов
     */
    void addBoard(int rowCount, int columnCount);
}
