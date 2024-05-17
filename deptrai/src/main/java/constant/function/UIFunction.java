package constant.function;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class UIFunction {
    public static ImageIcon getScaledIcon(String path, int height){
        ImageIcon originalIcon = new ImageIcon(path);
        Image originalImage = originalIcon.getImage();
        int desiredHeight = height;
        int originalWidth = originalImage.getWidth(null);
        int originalHeight = originalImage.getHeight(null);
        int scaledWidth = (int) ((double) originalWidth / originalHeight * desiredHeight);
        Image scaledImage = originalImage.getScaledInstance(scaledWidth, desiredHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        return scaledIcon;
    }
    public static JPanel getLabelWithImage(ImageIcon imageIcon, JLabel textLable){
        JLabel imageLabel = new JLabel(imageIcon);
        JPanel result = new JPanel();
        result.setLayout(new BorderLayout());
        result.add(imageLabel, BorderLayout.WEST);
        result.add(textLable, BorderLayout.CENTER);
        return result;

    }
    public static JTextArea getTextArea(String text, int rows, int columns, Border border){
        JTextArea area = new JTextArea(rows, columns);
        area.setText(text);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(border);
        return area;
    }
    public static JComboBox<String> createComboBox(String[] options, int width) {
        JComboBox<String> comboBox = new JComboBox<>(options);
        customizeComboBox(comboBox,width);
        return comboBox;
    }
    public static JComboBox<Integer> createComboBox(Integer[] options, int width) {
        JComboBox<Integer> comboBox = new JComboBox<>(options);
        customizeComboBox(comboBox,width);
        return comboBox;
    }
    public static JComboBox<Integer> createComboBox(Integer[]options) {
        JComboBox<Integer> comboBox = new JComboBox<>(options);
        comboBox.setPreferredSize(new Dimension(60, 25)); // Set preferred size
        comboBox.setFont(new Font("Arial", Font.BOLD, 12)); // Set font
        comboBox.setForeground(Color.WHITE); // Set font color
        comboBox.setBackground(new Color(21, 192, 192)); // Set background color
        return comboBox;
    }
    public static void customizeComboBox(JComboBox<?> comboBox, int width) {
        comboBox.setPreferredSize(new Dimension(width, 25));
        comboBox.setFont(new Font("Arial", Font.BOLD, 12));
        comboBox.setForeground(Color.WHITE);
        comboBox.setBackground(new Color(21, 192, 192));
    }

    public static JPanel createPanel(String label, JComboBox<?> monthComboBox, JComboBox<?> yearComboBox) {
        JPanel panel = new JPanel(new FlowLayout());
        JLabel jLabel = new JLabel(label);
        panel.add(jLabel);
        panel.add(monthComboBox);
        panel.add(yearComboBox);
        return panel;
    }
    public static JPanel createPanel(JComboBox<Integer> comboBox, String labelText) {
        JPanel panel = new JPanel(new FlowLayout());
        JLabel label = new JLabel(labelText);
        panel.add(label);
        panel.add(comboBox);
        return panel;
    }
}
