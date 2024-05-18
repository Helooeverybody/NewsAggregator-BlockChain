package controller;

import api.TrendAPIService;
import gui.trend.panel.ContentTrendPanel;
import gui.trend.TrendFrame;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class TrendController implements TrendButtonListener {
    private final TrendFrame trendFrame;
    private final TrendAPIService apiService;
    public TrendController(TrendFrame trendFrame, TrendAPIService apiService) {
        this.trendFrame = trendFrame;
        this.apiService = apiService;
        trendFrame.setSelectionListener(a -> {
            boolean checkCondition = trendFrame.checConditionHeaderTrend();
            if (checkCondition) {
                List<String> trendList = trendFrame.getTrendKeyword();
                System.out.println("Conditions met. Keywords: " + trendList);
                ContentTrendPanel contentTrendPanel = new ContentTrendPanel();
                contentTrendPanel.showLoadingAnimation();
                trendFrame.setContentTrend(contentTrendPanel);
                new Thread(() -> {
                    try {
                        List<String> trendResult = this.apiService.function(trendList);
                        SwingUtilities.invokeLater(() -> {
                               contentTrendPanel.displayImageFromBase64(trendResult.getFirst());
                               contentTrendPanel.hideLoadingAnimation();
                               trendFrame.setContentTrend(contentTrendPanel);
                        });
                    } catch (IOException | InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }).start();
            } else {
            }
        });
    }
    @Override
    public void onTrendButtonClick() {
        trendFrame.setVisible(true);
    }


}
