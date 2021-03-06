/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.example;

import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.Policy;
import net.jodah.failsafe.RetryPolicy;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;

public class Demo {

    public static void main(String[] args) {
        CompletableFuture<String> future = computeSomethingWithRetries();
        future.whenComplete((r, t) -> {
            if (t instanceof CancellationException) {
                System.out.println("Failsafe's CompletableFuture was cancelled");
            }
        });
        future.cancel(true);
    }

    static CompletableFuture<String> computeSomethingWithRetries() {
        Policy<String> retryPolicy = new RetryPolicy<String>()
                .handle(RuntimeException.class)
                .withMaxAttempts(-1);

        return Failsafe.with(retryPolicy).getStageAsync(Demo::computeSomething);
    }

    static CompletableFuture<String> computeSomething() {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.whenComplete((r, t) -> {
            if (t instanceof CancellationException) {
                System.out.println("Inner CompletableFuture was cancelled");
            }
        });
        return future;
    }
}
