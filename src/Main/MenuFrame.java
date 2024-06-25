package Main;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Создает главный фрейм

 */
public class MenuFrame extends JFrame implements MyFrame {
    private int width;
    private int height;

    private JPanel mainPanel;
    private JPanel buttonPanel;

    private MyFrameDirector myFrameDirector;

    /**
     * Создает фрейм
     */
    @Override
    public void createFrame() {
        width = 800;
        height = 800;

        initComponents();

        setVisible(true);
        setSize(new Dimension(width, height));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Инициализирует компоненты
     */
    public void initComponents() {
        mainPanel = new JPanel();
        buttonPanel = new JPanel();
        myFrameDirector = new MyFrameDirector();
    }

    /**
     * Добавляет кнопки к фрейму
     */
    @Override
    public void addButtons() {
        buttonPanel.setLayout(new GridLayout(2, 2, 50, 50));
        buttonPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
        buttonPanel.setBackground(Color.decode("#1f1f1f"));

        mainPanel.add(buttonPanel);

        addButton4x4();
        addButton5x5();
        addButton6x6();
        addButton7x7();
    }

    /**
     * Добавляет кнопку для создания доски 4 на 4
     */
    public void addButton4x4() {
        JButton button4x4 = new JButton("4x4");
        button4x4.setBackground(Color.decode("#9b6df7"));
        AbstractBorder brdr = new TextBubbleBorder(Color.BLACK,0,40,0);
        button4x4.setBorder(brdr);
        button4x4.setFont(new Font("Serif", Font.BOLD, 32));
        button4x4.setForeground(Color.WHITE);
        buttonPanel.add(button4x4);
        button4x4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BoardFrame boardFrame = new BoardFrame();
                myFrameDirector.makeFrame(boardFrame, 4, 4);
            }
        });
    }

    /**
     * Добавляет кнопку для создания доски 5 на 5
     */
    public void addButton5x5() {
        JButton button5x5 = new JButton("5x5");
        button5x5.setBackground(Color.decode("#9b6df7"));
        AbstractBorder brdr = new TextBubbleBorder(Color.BLACK,0,40,0);
        button5x5.setBorder(brdr);
        button5x5.setFont(new Font("Serif", Font.BOLD, 32));
        button5x5.setForeground(Color.WHITE);
        buttonPanel.add(button5x5);
        button5x5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BoardFrame boardFrame = new BoardFrame();
                myFrameDirector.makeFrame(boardFrame, 5, 5);
            }
        });
    }

    /**
     * Добавляет кнопку для создания доски 6 на 6
     */
    public void addButton6x6() {
        JButton button6x6 = new JButton("6x6");
        button6x6.setBackground(Color.decode("#9b6df7"));
        button6x6.setFont(new Font("Serif", Font.BOLD, 32));
        button6x6.setForeground(Color.WHITE);
        AbstractBorder brdr = new TextBubbleBorder(Color.BLACK,0,40,0);
        button6x6.setBorder(brdr);
        buttonPanel.add(button6x6);
        button6x6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BoardFrame boardFrame = new BoardFrame();
                myFrameDirector.makeFrame(boardFrame, 6, 6);
            }
        });
    }

    /**
     * Добавляет кнопку для создания доски 7 на 7
     */
    public void addButton7x7() {
        JButton button7x7 = new JButton("7x7");
        button7x7.setBackground(Color.decode("#9b6df7"));
        button7x7.setFont(new Font("Serif", Font.BOLD, 32));
        button7x7.setForeground(Color.WHITE);
        AbstractBorder brdr = new TextBubbleBorder(Color.BLACK,0,40,0);
        button7x7.setBorder(brdr);
        buttonPanel.add(button7x7);
        button7x7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BoardFrame boardFrame = new BoardFrame();
                myFrameDirector.makeFrame(boardFrame, 7, 7);
            }
        });
    }

    /**
     * Добавляет графические элементы к фрейму
     */
    @Override
    public void addGraphics() {
        addMainPanel();
        addTitle();
    }

    /**
     * Добавляет доску к фрейму
     * @param rowCount - количество строк
     * @param columnCount - количество столбцов
     */
    @Override
    public void addBoard(int rowCount, int columnCount) {};

    /**
     * Добавляет главную панель
     */
    private void addMainPanel() {
        add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.decode("#1f1f1f"));
        mainPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
    }

    /**
     * Добавляет заголовок
     */
    private void addTitle() {
        JLabel title1 = new JLabel("2048");
        title1.setForeground(Color.WHITE);
        title1.setFont(new Font("Serif", Font.BOLD, 48));
        title1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title2 = new JLabel("The game");
        title2.setForeground(Color.WHITE);
        title2.setFont(new Font("Serif", Font.PLAIN, 40));
        title2.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(title1);
        mainPanel.add(title2);
    }
}
