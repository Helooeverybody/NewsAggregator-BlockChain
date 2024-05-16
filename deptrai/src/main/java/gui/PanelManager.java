package gui;
import javax.swing.*;
import java.awt.*;

public class PanelManager extends JFrame {

    private JPanel contentPane;
    private CardLayout cardLayout;

    public PanelManager() {
        setTitle("Multi-Page Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);

        cardLayout = new CardLayout();
        contentPane = new JPanel(cardLayout);

        JPanel page2 = new HeaderPanel(); // Create HeaderPanel directly
        JPanel page3 = new HeaderTrend();
        add(page2,BorderLayout.NORTH);
        //add(page3);

    }

    // Method to show a specific page
    private void showPage(String pageName) {
        cardLayout.show(contentPane, pageName);
        contentPane.revalidate();
        contentPane.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new PanelManager();
            //JPanel page2 = new HeaderPanel();
            //frame.add(page2,BorderLayout.NORTH);
            frame.setVisible(true);
            //frame.remove();
        });
    }
}
