package Main;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;

/**
 * Представляют собой клетку

 */
public class Tile extends JPanel {
    private Color tileColor;
    private Color textColor;
    private Font tileFont;

    private int weight;

    private JLabel weightLabel;

    /**
     * Конструктор без параметров, который создает пустую клетку
     */
    public Tile() {
        this(0);
    }

    /**
     * Конструктор с параметрами, который создает клетку с заданным весом
     * @param weight - вес клетки
     */
    public Tile(int weight) {
        this.weight = weight;

        weightLabel= new JLabel(String.valueOf(weight), SwingConstants.CENTER);
        weightLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.setLayout(new BorderLayout());

        AbstractBorder brdr = new TextBubbleBorder(Color.BLACK,0,40,0);
        this.setBorder(brdr);

        add(weightLabel, BorderLayout.CENTER);

        tileFont = new Font("Serif", Font.BOLD, 20);

        update(weight);
    }

    /**
     * Заменяет вес клетки соответствующим числом и изменяет вид клетки в соответствие с весом
     * @param num - число, которым заменяется текущий вес
     */
    public void update(int num) {
        weight = num;
        weightLabel.setText(num == 0? " " : String.valueOf(weight));
        defineColor(num);
    }

    /**
     * Определяет цвет клетки в зависимости от веса
     * @param weight - вес
     */
    public void defineColor(int weight) {
        switch (weight) {
            case 0:
                tileColor = Color.decode("#4d4c4c");
                textColor = Color.BLACK;
                break;
            case 2:
                tileColor = Color.decode("#eec7fc");
                textColor = Color.BLACK;
                break;
            case 4:
                tileColor = Color.decode("#b295f5");
                textColor = Color.WHITE;
                break;
            case 8:
                tileColor = Color.decode("#8b82ed");
                textColor = Color.WHITE;
                break;
            case 16:
                tileColor = Color.decode("#6240f5");
                textColor = Color.WHITE;
                break;
            case 32:
                tileColor = Color.decode("#6893f7");
                textColor = Color.WHITE;
                break;
            case 64:
                tileColor = Color.decode("#70c3fa");
                textColor = Color.WHITE;
                break;
            case 128:
                tileColor = Color.decode("#43d8e6");
                textColor = Color.WHITE;
                break;
            case 256:
                tileColor = Color.decode("#32c79a");
                textColor = Color.WHITE;
                break;
            case 512:
                tileColor = Color.decode("#00b064");
                textColor = Color.WHITE;
                break;
            case 1024:
                tileColor = Color.decode("#00fc65");
                textColor = Color.WHITE;
                break;
            case 2048:
                tileColor = Color.decode("#90db39");
                textColor = Color.WHITE;
                break;
        }
        setBackground(tileColor);
        weightLabel.setForeground(textColor);
        weightLabel.setFont(tileFont);

    }

    /**
     * Возвращает вес клетки
     * @return вес клетки
     */
    public int getWeight() {
        return weight;
    }

}
