package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Представляет собой фрейм с доской

 */
public class BoardFrame extends JFrame implements MyFrame, KeyListener {
    private Board board;
    private BoardSaver boardSaver;

    private int width;
    private int height;

    private JPanel mainPanel;
    private SpringLayout layout;

    private JButton undoButton;
    private JButton newGameButton;

    private Context context;

    /**
     * Создает фрейм и настраивает его
     */
    @Override
    public void createFrame() {
        width = 800;
        height = 800;

        initComponents();

        setVisible(true);
        setSize(new Dimension(width, height));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        addKeyListener(this);
    }

    /**
     * Инициализирует компоненты
     */
    private void initComponents() {
        context = new Context();
        layout = new SpringLayout();
        mainPanel = new JPanel();
    }

    /**
     * Добавляет кнопки к фрейму
     */
    @Override
    public void addButtons() {
        addUndoButton();
        addNewGameButton();
    }

    /**
     * Добавляет кнопку отмены к фрейму
     */
    private void addUndoButton() {
        undoButton = new JButton("Undo");
        undoButton.setBackground(Color.WHITE);
        undoButton.setForeground(Color.decode("#232324"));
        undoButton.setFont(new Font("Serif", Font.BOLD, 14));
        layout.putConstraint(SpringLayout.EAST, undoButton, -100, SpringLayout.EAST, mainPanel);
        layout.putConstraint(SpringLayout.NORTH, undoButton, 40, SpringLayout.NORTH, mainPanel);
        mainPanel.add(undoButton);

        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.getBoardSaver().undo();
            }
        });

        undoButton.setFocusable(false);
    }

    /**
     * Добавляет кнопку новой игры к фрейму
     */
    private void addNewGameButton() {
        newGameButton = new JButton("New game");
        newGameButton.setBackground(Color.WHITE);
        newGameButton.setForeground(Color.decode("#232324"));
        newGameButton.setFont(new Font("Serif", Font.BOLD, 14));
        layout.putConstraint(SpringLayout.EAST, newGameButton, -20, SpringLayout.WEST, undoButton);
        layout.putConstraint(SpringLayout.NORTH, newGameButton, 40, SpringLayout.NORTH, mainPanel);
        mainPanel.add(newGameButton);

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.newGame();
            }
        });

        newGameButton.setFocusable(false);
    }

    /**
     * Добавляет графические элементы к фрейму
     */
    @Override
    public void addGraphics() {
        addMainPanel();
        addScoreLabel();
    }

    /**
     * Добавляет главную панель к фрейму
     */
    private void addMainPanel() {
        mainPanel.setLayout(layout);
        mainPanel.setBackground(Color.decode("#1f1f1f"));
        add(mainPanel);
    }

    /**
     * Добавляет доску к фрейму
     * @param rowCount - количество строк
     * @param columnCount - количество столбцов
     */
    @Override
    public void addBoard(int rowCount, int columnCount) {
        board = new Board(rowCount, columnCount);
        boardSaver = board.getBoardSaver();

        layout.putConstraint(SpringLayout.WEST, board, 100, SpringLayout.WEST, mainPanel);
        layout.putConstraint(SpringLayout.NORTH, board, 100, SpringLayout.NORTH, mainPanel);
        mainPanel.add(board);
    }

    /**
     * Добавляет ярлык со счетом
     */
    private void addScoreLabel() {
        mainPanel.add(board.getScoreLabel());
        layout.putConstraint(SpringLayout.WEST, board.getScoreLabel(), 100, SpringLayout.WEST, mainPanel);
        layout.putConstraint(SpringLayout.NORTH, board.getScoreLabel(), 40, SpringLayout.NORTH, mainPanel);
    }

    /**
     * Обрабатывает нажатие кнопки на клавиатуре
     * @param e - событие нажатия на кнопку
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Обрабатывает нажатие кнопки на клавиатуре
     * @param e - событие нажатия на кнопку
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            context.setStrategy(new MoveUpStrategy(), board);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            context.setStrategy(new MoveDownStrategy(), board);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            context.setStrategy(new MoveLeftStrategy(), board);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            context.setStrategy(new MoveRightStrategy(), board);
        }
        context.executeStrategy();
    }

    /**
     * Обрабатывает нажатие кнопки на клавиатуре
     * @param e - событие нажатия на кнопку
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
