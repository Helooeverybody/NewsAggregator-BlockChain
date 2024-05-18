package gui.trend;

import gui.search.panel.HeaderSearchPanel;
import gui.trend.panel.ContentTrendPanel;
import gui.trend.panel.HeaderTrendPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class TrendFrame extends JFrame {
    private HeaderTrendPanel headerTrendPanel;
    private ContentTrendPanel contentTrendPanel;
    private HeaderSearchPanel headerSearchPanel;

    public TrendFrame() {
        setTitle("Trend Display");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        headerTrendPanel = new HeaderTrendPanel();
        add(headerTrendPanel, BorderLayout.NORTH);
    }

    public void setContentTrend(ContentTrendPanel contentTrendPanel) {
        if (this.contentTrendPanel != null) {
            remove(this.contentTrendPanel);
        }
        this.contentTrendPanel = contentTrendPanel;
        add(this.contentTrendPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public HeaderTrendPanel getHeaderTrend() {
        return this.headerTrendPanel;
    }
    public ContentTrendPanel getContentTrend(){ return this.contentTrendPanel;}
    public HeaderSearchPanel getHeaderPanel(){return this.headerSearchPanel;}
    public boolean checConditionHeaderTrend(){
        return this.headerTrendPanel.checkConditions(this);
    }
    public List<String> getTrendKeyword(){
        return this.headerTrendPanel.getTrendKeyword();
    }
    public void setSelectionListener(ActionListener listener){
        this.headerTrendPanel.setSelectionListener(listener);
    }
}
