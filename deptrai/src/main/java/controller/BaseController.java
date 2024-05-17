package controller;

import dto.ApiService;
import searchengine.APIService;

import javax.swing.*;

public abstract class BaseController {
    protected void executeInBackground(Runnable backgroundTask, Runnable uiTask) {
        new Thread(() -> {
            backgroundTask.run();
            SwingUtilities.invokeLater(uiTask);
        }).start();
    }

}
