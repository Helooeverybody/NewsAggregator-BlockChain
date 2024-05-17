package controller;

import dto.TrendButtonListener;
import gui.trend.panel.ContentTrendPanel;
import gui.trend.TrendFrame;
import searchengine.APIService;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class TrendController extends BaseController implements TrendButtonListener {
    private final TrendFrame trendFrame;
    private final APIService apiService;
    public TrendController(TrendFrame trendFrame, APIService apiService) {
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
                        String trendResult = this.apiService.trend(trendList);
                        SwingUtilities.invokeLater(() -> {
                               contentTrendPanel.displayImageFromBase64(trendResult);
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
