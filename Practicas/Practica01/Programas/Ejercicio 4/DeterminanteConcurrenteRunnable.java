public class DeterminanteConcurrenteRunnable implements Runnable {
    static int determinante;
    static int n = 3;
    static int matriz_prueba[][] = { { 1, 2, 2 }, { 1, 0, -2 }, { 3, -1, 1 }};
    int n1, n2, n3, partial;
    
    public DeterminanteConcurrenteRunnable(int n1, int n2, int n3) {
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
    }
    
    public static int determinanteMatriz3x3(int matriz[][], int n) {
        int result = 0;
        DeterminanteConcurrenteRunnable res1 = new DeterminanteConcurrenteRunnable(matriz[0][0], matriz[1][1], matriz[2][2]);
        DeterminanteConcurrenteRunnable res2 = new DeterminanteConcurrenteRunnable(matriz[1][0], matriz[2][1], matriz[0][2]);
        DeterminanteConcurrenteRunnable res3 = new DeterminanteConcurrenteRunnable(matriz[2][0], matriz[0][1], matriz[1][2]);
        DeterminanteConcurrenteRunnable res4 = new DeterminanteConcurrenteRunnable(matriz[2][0], matriz[1][1], matriz[0][2]);
        DeterminanteConcurrenteRunnable res5 = new DeterminanteConcurrenteRunnable(matriz[1][0], matriz[0][1], matriz[2][2]);
        DeterminanteConcurrenteRunnable res6 = new DeterminanteConcurrenteRunnable(matriz[0][0], matriz[2][1], matriz[1][2]);


        Thread thr1 = new Thread(res1);
        Thread thr2 = new Thread(res2);
        Thread thr3 = new Thread(res3);
        Thread thr4 = new Thread(res4);
        Thread thr5 = new Thread(res5);
        Thread thr6 = new Thread(res6);

        thr1.start();
        thr2.start();
        thr3.start();
        thr4.start();
        thr5.start();
        thr6.start();

        try {
            thr1.join();
            thr2.join();
            thr3.join();
            thr4.join();
            thr5.join();
            thr6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        result = res1.partial + res2.partial + res3.partial - res4.partial - res5.partial - res6.partial;

        return result;
    }
    
    @Override
    public void run() {
        this.partial = this.n1 * this.n2 * this.n3;
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        determinante = determinanteMatriz3x3(matriz_prueba, n);
        long endTime = System.nanoTime();
        System.out.println("Program took " +
                (endTime - startTime) + "ns, result: " + determinante);
    }
}