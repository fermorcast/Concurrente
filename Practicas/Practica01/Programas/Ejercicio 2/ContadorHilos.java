/*
    Universidad Nacional Autónoma de México
    Facultad de Ciencias
    Computación Concurrente
    Práctica 1 - Ejercicio 2: Contar hilos disponibles en la computadora
    Osorio Escandón Huriel
    Huriel117@ciencias.unam.mx
    317204652
*/

public class ContadorHilos {
    public static void main(String[] args) {
        // Obtener el número de procesadores disponibles en el sistema
        int hilosDisponibles = Runtime.getRuntime().availableProcessors();
        
        // Imprimir el número de hilos disponibles
        System.out.println("Número de hilos disponibles: " + hilosDisponibles);
    }
}