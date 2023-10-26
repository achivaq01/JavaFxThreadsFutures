package com.project;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class Controller1 implements Initializable {

    @FXML
    private ImageView img1;
    // Add more ImageView declarations for img2, img3, ..., img24

    private List<Integer> loadedImageIndices;

    @FXML
    private ProgressBar pBar;
    @FXML
    private Label loading;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadedImageIndices = new ArrayList<>();
    }

    @FXML
    private void animateToView0(ActionEvent event) {
        UtilsViews.setViewAnimating("View0");
    }

    @FXML
    private void loadImage() {
        System.out.println("Loading images...");
        loading.setVisible(true);
        pBar.setProgress(0);

        Task<Void> loadingTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                LoadImage loadImage = new LoadImage();
                Random random = new Random();
                int numberOfImages = 24;

                while (loadedImageIndices.size() < numberOfImages && !isCancelled()) {
                    try {
                        int loadingTime = (random.nextInt(10) + 1) * 500;
                        Thread.sleep(loadingTime);

                        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/image.png")));

                        int randomIndex;
                        do {
                            randomIndex = random.nextInt(numberOfImages) + 1;
                        } while (loadedImageIndices.contains(randomIndex));

                        loadedImageIndices.add(randomIndex);
                        updateProgress(loadedImageIndices.size(), numberOfImages);

                        int finalRandomIndex = randomIndex;
                        Platform.runLater(() -> displayImage(image, finalRandomIndex));

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
                return null;
            }
        };

        loadingTask.setOnSucceeded(event -> loading.setVisible(false));

        Thread loadingThread = new Thread(loadingTask);
        loadingThread.setDaemon(true);
        loadingThread.start();
    }

    private void displayImage(Image image, int index) {
        // Depending on the index, set the corresponding ImageView with the loaded image
        switch (index) {
            case 1:
                img1.setImage(image);
                break;
            // Add more cases for img2, img3, ..., img24
        }
    }
    @FXML
    private void stopLoading(){

    }
}
