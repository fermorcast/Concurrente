public class Tarea implements Runnable {
    private final int taskId;
    private int executionTime;

    public Tarea(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
        int threadValue = (int) (currentThread.getId() % 6);

        // Determinamos el tiempo de ejecución según la tarea
        switch (threadValue) {
            case 0, 2 -> this.executionTime = 500;
            case 1 -> this.executionTime = 2000;
            default -> this.executionTime = 3000;
        }

        try {
            System.out.println("Thread " + threadValue + " trying to execute task: " + this.taskId);

            // Manejo especial para cuando tenemos a los hilos 0 y 2
            if (threadValue == 0 || threadValue == 2) {
                synchronized (Scheduler.thread0and2Lock) {
                    Scheduler.concurrencyLimiter.acquire();
                    try {
                        executeTask(threadValue);
                    } finally {
                        Scheduler.concurrencyLimiter.release();
                    }
                }
            } else {
                // Semaforo cuando no tenemos ese caso
                Scheduler.concurrencyLimiter.acquire();
                try {
                    executeTask(threadValue);
                } finally {
                    Scheduler.concurrencyLimiter.release();
                }
            }
            // Excepción cuando algo sale mal
        } catch (InterruptedException e) {
            System.out.println("Task interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    //ToString de las tareas para cuando se inicial y cuando se terminan
    private void executeTask(int threadValue) throws InterruptedException {
        System.out.println("Thread " + threadValue + " started executing task: " + this.taskId);
        Thread.sleep(this.executionTime);
        System.out.println("Thread " + threadValue + " completed task: " + this.taskId +
                " (execution time: " + this.executionTime + "ms)");
    }
}
