package com.project;

import javafx.scene.image.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class LoadImage {

    public void loadImageAsync(Consumer<Image> callBack, Consumer<Double> progressCallback, CompletableFuture<Void> stopSignal) {
        Random random = new Random();
        int numberOfImages = 24;
        List<Integer> loadedImageIndices = new ArrayList<>();

        while (loadedImageIndices.size() < numberOfImages && !stopSignal.isCancelled()) {
            try {
                int loadingTime = (random.nextInt(10) + 1) * 500;
                Thread.sleep(loadingTime);

                // Load an image (replace "/assets/image.png" with your actual image path)
                Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/image.png")));

                // Check if the loaded image index is not in the loadedImageIndices list
                int randomIndex;
                do {
                    randomIndex = random.nextInt(numberOfImages) + 1;
                } while (loadedImageIndices.contains(randomIndex));

                // Pass the loaded image to the callback and update loadedImageIndices list
                callBack.accept(image);
                loadedImageIndices.add(randomIndex);

                // Update progress bar based on loaded images
                double progress = (double) loadedImageIndices.size() / numberOfImages;
                progressCallback.accept(progress);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
