import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AdvancedThreads {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        try {
            // Start work in the background. supplyAsync is for work that returns a value.
            CompletableFuture<String> userFuture = CompletableFuture
                    .supplyAsync(() -> loadUser(7), executor);

            // thenApply transforms a value; thenAccept consumes it; thenRun needs no value.
            CompletableFuture<String> greetingFuture = userFuture
                    .thenApply(user -> "Hello, " + user.toUpperCase())
                    .thenApplyAsync(greeting -> greeting + "!", executor);

            greetingFuture.thenAccept(System.out::println).join();
            greetingFuture.thenRun(() -> System.out.println("Greeting is ready."))
                    .join();

            // thenCompose chains work that itself returns a future (avoids Future<Future<T>>).
            CompletableFuture<String> orderFuture = userFuture
                    .thenCompose(user -> loadOrders(user, executor));
            System.out.println("Orders: " + orderFuture.join());

            // thenCombine runs independent futures together and combines both results.
            CompletableFuture<Integer> priceFuture = CompletableFuture
                    .supplyAsync(() -> 25, executor);
            CompletableFuture<Integer> quantityFuture = CompletableFuture
                    .supplyAsync(() -> 3, executor);
            CompletableFuture<Integer> totalFuture = priceFuture.thenCombine(
                    quantityFuture, (price, quantity) -> price * quantity);
            System.out.println("Total: " + totalFuture.join());

            // allOf waits for every future; join each one to collect its result.
            CompletableFuture<Void> allDone = CompletableFuture.allOf(
                    userFuture, orderFuture, totalFuture);
            allDone.join();
            System.out.println("All requested data is ready.");

            // exceptionally provides a fallback. handle can inspect success or failure.
            CompletableFuture<String> recovered = CompletableFuture
                    .supplyAsync(() -> {
                        throw new IllegalStateException("Service unavailable");
                    }, executor)
                    .exceptionally(error -> "Fallback value: " + rootMessage(error));
            System.out.println(recovered.join());

            CompletableFuture<String> handled = CompletableFuture
                    .<String>supplyAsync(() -> {
                        throw new IllegalArgumentException("Bad input");
                    }, executor)
                    .handle((value, error) -> error == null
                            ? "Result: " + value
                            : "Handled: " + rootMessage(error));
            System.out.println(handled.join());

            // whenComplete observes the outcome without replacing it; orTimeout fails
            // the future if it takes too long. completedFuture is already finished.
            CompletableFuture<String> observed = CompletableFuture
                    .completedFuture("cached result")
                    .whenComplete((value, error) ->
                            System.out.println("Observed: " + value));
            System.out.println(observed.join());

            CompletableFuture<String> slowFuture = CompletableFuture
                    .supplyAsync(() -> pauseAndReturn("late result", 500), executor)
                    .orTimeout(100, TimeUnit.MILLISECONDS)
                    .exceptionally(error -> "Timeout fallback");
            System.out.println(slowFuture.join());

            // join() throws unchecked CompletionException; get() instead requires
            // handling InterruptedException and ExecutionException.
        } finally {
            executor.shutdown();
        }
    }

    private static String loadUser(int id) {
        return "user-" + id;
    }

    private static CompletableFuture<String> loadOrders(
            String user, ExecutorService executor) {
        return CompletableFuture.supplyAsync(() -> user + "'s orders: [book, pen]", executor);
    }

    private static String pauseAndReturn(String value, long milliseconds) {
        try {
            Thread.sleep(milliseconds);
            return value;
        } catch (InterruptedException error) {
            Thread.currentThread().interrupt();
            throw new CompletionException(error);
        }
    }

    private static String rootMessage(Throwable error) {
        Throwable cause = error;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }
        return cause.getMessage();
    }
}