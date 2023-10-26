package com.project;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;

public class Controller0 {

    @FXML
    private Button pButton1, pButton2, pButton3;
    @FXML
    private AnchorPane container;
    @FXML
    private Label percentatge0, percentatge1, percentatge2;

    @FXML
    private ProgressBar pBar1, pBar2, pBar3;

    private final ExecutorService executor = Executors.newFixedThreadPool(3);
    private boolean isRunningTask0 = false, isRunningTask1 = false, isRunningTask2 = false;

    @FXML
    private void animateToView1(ActionEvent event) {
        UtilsViews.setViewAnimating("View1");
    }

    @FXML
    private void runTask0() {
        setRunningTask0();
        backgroundTask(0, percentatge0);
        //backgroundTask(1, executor0, percentatge0);
    }
    @FXML
    private void runTask1() {
        setRunningTask1();
        backgroundTask(1, percentatge1);
        //backgroundTask(1, executor1, percentatge1);
    }
    @FXML
    private void runTask2(){
        setRunningTask2();
        backgroundTask(2, percentatge2);
        //backgroundTask(1, executor2, percentatge2);
    }

    private void backgroundTask(int index, Label label) {
        // Executar la tasca
        executor.submit(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    final int currentValue = i;

                    Platform.runLater(() -> {
                        label.setText("Tasca " + (index + 1) + ", " + String.valueOf(currentValue) + "%");
                    });

                    if (index == 0) {
                        if(!isRunningTask0) {
                            return;
                        }

                        Thread.sleep(1000);
                        pBar1.setProgress((double) i / 100);
                    }

                    if (index == 1) {
                        if(!isRunningTask1) {
                            return;
                        }

                        i = i + ((int) (Math.random() * 4) + 2);

                        Thread.sleep((int) (Math.random() * 5000) + 3000);

                        if (i >= 100) {
                            pBar2.setProgress(1);
                        } else {
                            pBar2.setProgress((double) i / 100);
                        }
                    }

                    if (index == 2) {
                        if(!isRunningTask2) {
                            return;
                        }

                        i = i + ((int) (Math.random() * 6) + 4);

                        Thread.sleep((int) (Math.random() * 8000) + 3000);

                        if (i >= 100) {
                            pBar3.setProgress(1);
                        } else {
                            pBar3.setProgress((double) i / 100);
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }
    
    // Aquesta funció la cridaries quan vulguis tancar l'executor (per exemple, quan tanquis la teva aplicació)
    public void stopExecutor(ExecutorService executor) {
        executor.shutdown();
    }

    public void setRunningTask0() {
        isRunningTask0 = !isRunningTask0;

        if(isRunningTask0) {
            pButton1.setText("Aturar");
        } else {
            pButton1.setText("Iniciar");
        }
    }

    public void setRunningTask1() {
        isRunningTask1 = !isRunningTask1;

        if(isRunningTask1) {
            pButton2.setText("Aturar");
        } else {
            pButton2.setText("Iniciar");
        }
    }

    public void setRunningTask2() {
        isRunningTask2 = !isRunningTask2;

        if(isRunningTask2) {
            pButton3.setText("Aturar");
        } else {
            pButton3.setText("Iniciar");
        }
    }
}