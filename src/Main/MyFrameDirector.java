package Main;

/**
 * Отвечает за построение фрейма

 */
public class MyFrameDirector {
    /**
     * Строит фрейм без доски
     * @param frame - ссылка на соответствующий фрейм
     */
    public void makeFrame(MyFrame frame) {
        frame.createFrame();
        frame.addGraphics();
        frame.addButtons();
    }

    /**
     * Строит фрейм с доской
     * @param frame - ссылка на соответствующий фрейм
     * @param rowCount - количество строк
     * @param columnCount - количество столбцов
     */
    public void makeFrame(MyFrame frame, int rowCount, int columnCount) {
        frame.createFrame();
        frame.addBoard(rowCount, columnCount);
        frame.addGraphics();
        frame.addButtons();
    }
}
