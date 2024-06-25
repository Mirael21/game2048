package Main;
/**
 * Представляет собой доску с клетками, на которой происходят все игровые действий

 */

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {
    private int width;
    private int height;

    private int border;
    private int tileWidth;

    private Tile[][] tiles;

    private int rowCount;
    private int columnCount;

    private int score;
    private JLabel scoreLabel;

    private BoardSaver boardSaver;

    /**
     * Создает доску соответствующего размера
     * @param rowCount - количество строк
     * @param columnCount - количество столбцов
     */
    public Board(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;

        defineSize();

        tiles = initNewTiles(rowCount, columnCount);
        setTileSize();
        addTilesToBoard();

        initScoreLabel();

        setPreferredSize(new Dimension(width, height));

        AbstractBorder brdr = new TextBubbleBorder(Color.BLACK,0,40,40);
        setBorder(brdr);

        setBackground(Color.decode("#292828"));
        setLayout(new FlowLayout(FlowLayout.LEADING, border, border));

        generateNewTile();

        boardSaver = new BoardSaver(this);
    }

    /**
     * Подбирает размер доски, клеток и промежутка между клетками в зависимости от кол-ва клеток
     */
    public void defineSize() {
        width = 600;
        height = 600;

        if (rowCount == 4 && columnCount == 4) {
            tileWidth = 124;
            border = 4;
        } else if (rowCount == 5 && columnCount == 5) {
            tileWidth = 98;
            border = 4;
        } else if (rowCount == 6 && columnCount == 6) {
            tileWidth = 84;
            border = 2;
        } else if (rowCount == 7 && columnCount == 7) {
            tileWidth = 72;
            border = 2;
        }
    }

    /**
     * Создает сохраненение состояния доски
     * @return новый файл сохранения
     */
    public BoardSaveFile createSaveFile() {
        return new BoardSaveFile(this, tiles, rowCount, columnCount, score);
    }

    /**
     * Начинает новую игру
     */
    public void newGame() {
        resetScore();
        reset(tiles);
        generateNewTile();
        boardSaver.save();
    }

    /**
     * Заменяет вес клеток из массива tiles данными из другого массива
     * @param src - источник данных
     */
    public void update(Tile[][] src) {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                tiles[i][j].update(src[i][j].getWeight());
            }
        }
    }

    /**
     * Заменяет вес клеток в одном массиве данными из другого массива
     * @param src - источник данных
     * @param dest - массив, в котором заменяются данные
     */
    public void update(Tile[][] src, Tile[][] dest) {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                dest[i][j].update(src[i][j].getWeight());
            }
        }
    }

    /**
     * Заменяет вес клеток в одном массиве нулями
     * @param tiles - массив, в котором заменяются данные
     */
    public void reset(Tile[][] tiles) {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                tiles[i][j].update(0);
            }
        }
    }

    /**
     * Устанавливает размер клеток в массиве tiles
     */
    public void setTileSize() {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
//                tiles[i][j].width = tileWidth;
                tiles[i][j].setPreferredSize(new Dimension(tileWidth, tileWidth));
            }
        }
    }

    /**
     * Добавляет к текущей доске массив клеток tiles
     */
    public void addTilesToBoard() {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                add(tiles[i][j]);
            }
        }

    }

    /**
     * Инициализирует двумерный массив клеток tiles
     * @param rowCount - количество строк
     * @param columnCount - количество столбцов
     * @return инициализированный двумерный массив клеток
     */
    public Tile[][] initNewTiles(int rowCount, int columnCount) {
        Tile[][] tiles = new Tile[rowCount][columnCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                tiles[i][j] = new Tile();
            }
        }

        return tiles;
    }

    /**
     * Рандомно енерирует новую клетку на свободном месте
     */
    public void generateNewTile() {
        ArrayList<Tile> emptyTiles = new ArrayList<Tile>();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (tiles[i][j].getWeight() == 0)
                    emptyTiles.add(tiles[i][j]);
            }
        }
        int newTileIdx = (int) (Math.random() * emptyTiles.size()); //определяем место появления новой клетки
        int newTileNumber = ((int) (Math.random() * 2)) == 0? 2 : 4; //определяем будет 2 или 4 (50 на 50)
        emptyTiles.get(newTileIdx).update(newTileNumber);
    }

    /**
     * Возвращает ссылку на двумерный массив клеток
     * @return ссылку на двумерный массив клеток
     */
    public Tile[][] getTiles() {
        return tiles;
    }

    /**
     * Инициализирует счет и ярлык со счетом
     */
    public void initScoreLabel() {
        score = 0;
        scoreLabel = new JLabel("Score: " + Integer.toString(score));
        scoreLabel.setFont(new Font("Serif", Font.BOLD, 24));
        scoreLabel.setForeground(Color.WHITE);
    }

    /**
     * Увеличивает счет на передаваемую велину и обновляет ярлык со счетом
     * @param num - величина, на которую увеличивается счет
     */
    public void updateScore(int num) {
        score += num;
        scoreLabel.setText("Score: " + Integer.toString(score));
    }

    /**
     * Обнуляет счет
     */
    public void resetScore() {
        score = 0;
        scoreLabel.setText("Score: " + Integer.toString(0));
    }

    /**
     * Возвращает счет
     * @return счет
     */
    public int getScore() {
        return score;
    }

    /**
     * Устанавливает счет
     * @param score - счет
     */
    public void setScore(int score) {
        this.score = score;
        scoreLabel.setText("Score: " + Integer.toString(score));
    }

    /**
     * Возвращает ссылку на ярлык со счетом
     * @return ссылку на ярлык со счетом
     */
    public JLabel getScoreLabel() {
        return scoreLabel;
    }

    /**
     * Возвращает количество строк
     * @return количество строк
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * Устанавливает количество строк
     * @param rowCount - количество строк
     */
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    /**
     * Возвращает количество столбцов
     * @return количество столбцов
     */
    public int getColumnCount() {
        return columnCount;
    }

    /**
     * Устанавливает количество столбцов
     * @param columnCount - количество столбцов
     */
    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    /**
     * Возвращает ширину доски
     * @return ширину доски
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * Устанавливает ширину доски
     * @param width - ширину доски
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Возвращает высоту доски
     * @return высоту доски
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * Устанавливает высоту доски
     * @param height - высоту доски
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Возвращает ссылку на экземпляр класса, отвечающего за сохранение доски
     * @return ссылку на экземпляр класса, отвечающего за сохранение доски
     */
    public BoardSaver getBoardSaver() {
        return boardSaver;
    }
}
