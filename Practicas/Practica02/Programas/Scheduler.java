import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Scheduler {

    // Definimos la cantidad de tareas que son capaces de entrar a la vez
    static Semaphore concurrencyLimiter = new Semaphore(3);

    // Hacemos un candado para los hilos 0 y 2 que no pueden acceder a la vez
    static final Object thread0and2Lock = new Object();

    // Método main
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(6);

        for (int i = 0; i < 26; i++) {
            executorService.execute(new Tarea(i));
        }

        executorService.shutdown();
    }
}
