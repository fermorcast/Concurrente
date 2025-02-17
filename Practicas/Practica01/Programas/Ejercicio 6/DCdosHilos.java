/*
 * Universidad Nacional Autónoma de México
 * Facultad de Ciencias
 * Computación Concurrente
 * 
 * Programa 10 (DeterminanteConcurrente): Programa para obtener el determinante de una matriz de 3x3
 * Práctica 1 - Ej 6: Implementa del programa Determinante concurrente para dos hilos (en vez de seis).
 */

 public class DCdosHilos extends Thread {
    
    // Guardará el determinante calculado
    static int determinante;
    
    // Matriz de prueba 3x3
    static int matriz_prueba[][] = { { 1, 2, 2 }, { 1, 0, -2 }, { 3, -1, 1 }};
    
    // Variables para guardar las sumas parciales del determinante
    int startIdx, endIdx, partial;
    
    /**
     * DCdosHilos
     * Inicializa las variables para cada hilo.
     * @param startIdx Índice de inicio para el cálculo de la parte de la matriz
     * @param endIdx Índice de fin para el cálculo de la parte de la matriz
     */
    public DCdosHilos(int startIdx, int endIdx) {
        this.startIdx = startIdx;
        this.endIdx = endIdx;
    }
    
    /**
     * Método "determinanteMatriz3x3" que calcula el determinante de la matriz de manera concurrente con dos hilos.
     * Divide el cálculo de la regla de Sarrus en dos partes iguales.
     * @param matriz Matriz 3x3
     * @return Determinante calculado concurrentemente
     */
    public static int determinanteMatriz3x3(int[][] matriz) {
        int result = 0;
        
        // El hilo 1 maneja los primeros tres términos de la regla de Sarrus
        DCdosHilos thr1 = new DCdosHilos(0, 2);
        
        //El hilo 2 maneja los últimos tres términos de la regla de Sarrus
        DCdosHilos thr2 = new DCdosHilos(3, 5);
        
        // Se inician los hilos
        thr1.start();
        thr2.start();
        
        try {
            // Se espera a que los hilos terminen antes de seguir
            thr1.join();
            thr2.join();
        } catch (InterruptedException e) {
            System.out.println("Hubo un error en la ejecución de los hilos");
        }
        
        // Se suman los resultados parciales de los hilos
        result = thr1.partial + thr2.partial;
        
        return result;
    }
    
    /**
     * Método "run" que se ejecuta cuando el hilo se inicia.
     * Se calcula la multiplicación de los elementos de la matriz para las partes correspondientes
     */
    public void run() {
        if (startIdx == 0) {
            // Se calcula la primera parte del determinante, es decir, terminos positivos
            this.partial = (matriz_prueba[0][0] * matriz_prueba[1][1] * matriz_prueba[2][2]) + 
                           (matriz_prueba[1][0] * matriz_prueba[2][1] * matriz_prueba[0][2]) + 
                           (matriz_prueba[2][0] * matriz_prueba[0][1] * matriz_prueba[1][2]);
        } else {
            // Se calcula la segunda parte del determinante, es decir, terminos negativos
            this.partial = - (matriz_prueba[2][0] * matriz_prueba[1][1] * matriz_prueba[0][2]) - 
                           (matriz_prueba[1][0] * matriz_prueba[0][1] * matriz_prueba[2][2]) - 
                           (matriz_prueba[0][0] * matriz_prueba[2][1] * matriz_prueba[1][2]);
        }
    }

    /**
     * Método main que mide el tiempo de ejecución y calcula el determinante de la matriz.
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        // Se mide el tiempo de inicio de la ejecución del programa
        long startTime = System.nanoTime();
        
        // Se hace la llamada al metodo para calcular la determinante de la matriz
        determinante = determinanteMatriz3x3(matriz_prueba);
        
        // Se mide el tiempo de termino del programa
        long endTime = System.nanoTime();
        
        System.out.println("El programa tomó " + (endTime - startTime) + " ns, resultado: " + determinante);
    }
}