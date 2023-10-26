package com.project;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Controller0 {

    @FXML
    private Button pButton1, pButton2, pButton3;
    @FXML
    private AnchorPane container;
    @FXML
    private Label percentatge0, percentatge1, percentatge2;
    
    private ExecutorService executor0 = Executors.newFixedThreadPool(1);
    private ExecutorService executor1 = Executors.newFixedThreadPool(1);
    private ExecutorService executor2 = Executors.newFixedThreadPool(1);


    @FXML
    private void animateToView1(ActionEvent event) {
        UtilsViews.setViewAnimating("View1");
    }

    @FXML
    private void runTask0() {

        backgroundTask(0, executor0, percentatge0);
        //backgroundTask(1, executor0, percentatge0);
    }
    @FXML
    private void runTask1() {
        backgroundTask(0, executor1, percentatge1);
        //backgroundTask(1, executor1, percentatge1);
    }
    @FXML
    private void runTask2(){
        backgroundTask(0, executor2, percentatge2);
        //backgroundTask(1, executor2, percentatge2);
    }

    private void backgroundTask(int index, ExecutorService executor, Label label) {
        // Executar la tasca
        executor.submit(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    final int currentValue = i;
    
                    if (index == 0) {
                        // Actualitzar el Label en el fil d'aplicació de l'UI
                        Platform.runLater(() -> {
                            label.setText(String.valueOf(currentValue) + "%");
                        });
                        Thread.sleep(100);

                    }

                    if (index == 1) {
                        // Actualitzar el Label en el fil d'aplicació de l'UI
                        Platform.runLater(() -> {
                            label.setText(String.valueOf(currentValue) + "%");
                        });
                        Thread.sleep(40);
                    }

                    System.out.println("Updating label: " + index + ", Value: " + currentValue);
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

}