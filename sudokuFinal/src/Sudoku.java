import static java.lang.System.exit;

public class Sudoku {

    static long startTime;

    static int matriz[][] = {
        {0,9,0,/**/0,0,2,/**/0,0,0},
        {0,0,0,/**/7,0,0,/**/0,8,0},
        {0,5,4,/**/0,3,0,/**/7,0,0},

        {6,0,0,/**/0,0,0,/**/0,0,0},
        {0,0,0,/**/0,0,1,/**/0,0,2},
        {0,7,3,/**/0,5,0,/**/8,0,0},

        {9,0,0,/**/0,0,0,/**/4,0,0},
        {8,0,0,/**/0,6,0,/**/0,0,0},
        {0,4,6,/**/0,0,5,/**/0,1,0},
    };

    int n = matriz.length;

    // Tamaño de cada cuadrante
    int tamanoCuadrante = n;

    public Sudoku() {
        Nodo cabeza = new Nodo(0, 0, matriz);
        startTime = System.currentTimeMillis();
        backtracking(cabeza);
    }

    public static void main(String[] args) throws Exception {
        new Sudoku(); // Crear una instancia de Sudoku para ejecutar el constructor
    }

    static void backtracking(Nodo nodo) {
        if (reject(nodo)) return;
        if (accept(nodo)) {
            ImprimirSudoku(nodo.getMatriz());
            long endTime = System.currentTimeMillis();
            System.out.println("Tiempo de ejecucion: " + (endTime - startTime) + " milisegundos");
            exit(0);
        }
    
        Nodo siguienteNodo = first(nodo);
        while (siguienteNodo != null) {
            backtracking(siguienteNodo);
            siguienteNodo = next(siguienteNodo);
        }
    }

    // Método para imprimir una matriz
    private static void imprimirMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean accept(Nodo nodo) {
        // Implementar lógica de aceptación basada en las reglas del Sudoku
        int[][] matriz = nodo.getMatriz();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (esValido(matriz, i, j, num)) {
                            matriz[i][j] = num;
                            Nodo nuevoNodo = new Nodo(i, j, matriz);
                            if (accept(nuevoNodo)) {
                                return true;
                            }
                            matriz[i][j] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean esValido(int[][] matriz, int fila, int columna, int num) {
        // Verificar si el número es válido en la fila, columna y cuadrante
        return !enFila(matriz, fila, num) &&
               !enColumna(matriz, columna, num) &&
               !enCuadrante(matriz, fila - fila % 3, columna - columna % 3, num);
    }

    private static boolean enFila(int[][] matriz, int fila, int num) {
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[fila][i] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean enColumna(int[][] matriz, int columna, int num) {
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i][columna] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean enCuadrante(int[][] matriz, int filaInicio, int columnaInicio, int num) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matriz[filaInicio + i][columnaInicio + j] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean reject(Nodo nodo) {
        // Implementar lógica de rechazo si es necesario
        return false;
    }

    private static Nodo first(Nodo nodo) {
        // Implementar lógica para encontrar la próxima celda vacía
        int fila = nodo.getX();
        int columna = nodo.getY();

        while (fila < nodo.getMatriz().length && nodo.getMatriz()[fila][columna] != 0) {
            columna++;
            if (columna == nodo.getMatriz()[0].length) {
                columna = 0;
                fila++;
            }
        }

        if (fila == nodo.getMatriz().length) {
            return null; // No hay más celdas vacías
        }

        nodo.setX(fila);
        nodo.setY(columna);

        return nodo;
    }

    private static Nodo next(Nodo nodo) {
        // Implementar lógica para encontrar la siguiente celda vacía
        int fila = nodo.getX();
        int columna = nodo.getY();

        columna++;
        if (columna == nodo.getMatriz()[0].length) {
            columna = 0;
            fila++;
        }

        while (fila < nodo.getMatriz().length && nodo.getMatriz()[fila][columna] != 0) {
            fila++;
            if (fila == nodo.getMatriz().length) {
                fila = 0;
                columna++;
            }
        }

        if (columna == nodo.getMatriz()[0].length) {
            return null; // No hay más celdas vacías
        }

        nodo.setX(fila);
        nodo.setY(columna);

        return nodo;
    }

    static void ImprimirSudoku(int[][] matriz) {
        // Método para imprimir la matriz del Sudoku
        System.out.println("Sudoku Resuelto:");
        imprimirMatriz(matriz);
    }
}
