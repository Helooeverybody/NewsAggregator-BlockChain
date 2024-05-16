package gui;

import javax.swing.*;
import java.awt.*;

public class TrendFrame extends JFrame {
    private HeaderTrend headerTrend;
    private ContentTrend contentTrend;

    public TrendFrame() {
        setTitle("Trend Display");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Use BorderLayout to place components more effectively
        setLayout(new BorderLayout());

        headerTrend = new HeaderTrend();
        add(headerTrend, BorderLayout.NORTH);
    }

    public void setContentTrend(ContentTrend contentTrend) {
        // Remove existing contentTrend if any
        if (this.contentTrend != null) {
            remove(this.contentTrend);
        }

        // Add the new contentTrend
        this.contentTrend = contentTrend;
        add(this.contentTrend, BorderLayout.CENTER);

        // Revalidate and repaint to update the UI
        revalidate();
        repaint();
    }

    public HeaderTrend getHeaderTrend() {
        return this.headerTrend;
    }
}
