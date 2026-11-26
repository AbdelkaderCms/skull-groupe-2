package fr.miage.skull.utils;

import javafx.concurrent.Task;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

public final class Background {
    public static <T> void run(Callable<T> call, Consumer<T> ok, Consumer<Throwable> fail) {
        Task<T> task = new Task<>() {
            protected T call() throws Exception {
                return call.call();
            }
        };
        task.setOnSucceeded(e -> ok.accept(task.getValue()));
        task.setOnFailed(e -> fail.accept(task.getException()));
        Thread thread = new Thread(task, "skull-api");
        thread.setDaemon(true);
        thread.start();
    }
}
