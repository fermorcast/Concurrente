/*
 * Universidad Nacional Autónoma de México
 * Facultad de Ciencias
 * Computación Concurrente
 * 
 * Programa 10 (DeterminanteConcurrente): Programa para obtener el determinante de una matriz de 3x3
 * Práctica 1 - Ej 5: Implementa el programa Determinante concurrente de forma secuencial.
 */

// package unam.fc.concurrent.practica1;

public class DCsecuencial {
    
    // Matriz de prueba 3x3
    static int matriz_prueba[][] = { { 1, 2, 2 }, { 1, 0, -2 }, { 3, -1, 1 } };
    
    /**
     * Método "determinanteMatriz3x3" que calcula el determinante de una matriz 3x3 secuencialmente
     * Usa la regla de Sarrus
     * @param matriz Matriz de 3x3
     * @return Determinante de la matriz
     */
    public static int determinanteMatriz3x3(int[][] matriz) {
        int determinante = (matriz[0][0] * matriz[1][1] * matriz[2][2]) + 
                           (matriz[1][0] * matriz[2][1] * matriz[0][2]) + 
                           (matriz[2][0] * matriz[0][1] * matriz[1][2]) - 
                           (matriz[2][0] * matriz[1][1] * matriz[0][2]) - 
                           (matriz[1][0] * matriz[0][1] * matriz[2][2]) - 
                           (matriz[0][0] * matriz[2][1] * matriz[1][2]);
        return determinante;
    }
    
    public static void main(String[] args) {
        // Inicia una medición de tiempo
        long startTime = System.nanoTime();
        
        // Se calcula el determinante
        int determinante = determinanteMatriz3x3(matriz_prueba);
        
        // Finaliza la medición de tiempo
        long endTime = System.nanoTime();
        
        System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " ns");
        System.out.println("Determinante: " + determinante);
    }
}