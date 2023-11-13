//Diego Pozas
//Diego Aguilera
//Renato Chavez
//Luis Lagunas
//Daniel Valdebenito
//Sudoku por Backtracking
//-------------------------------------------------------------------------------------//

import javax.swing.*;

import Nodo;

import static java.lang.System.exit;

import java.awt.*;
import java.util.Scanner;

////////////////////////     I N T E R F A Z   G R A F I C A    D E L   S U D O K U    ///////////////////////////
class SudokuGUI extends JFrame {

    private JPanel sudokuPanel;
    
    // Configurar la interfaz grafica basica
    public SudokuGUI() {
        // Configurar la ventana
        setTitle("SUDOKU POR BACKTRACKING");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //Hacer que se abra en primer plano
        setAlwaysOnTop(true);
        // Crear el panel del sudoku
        sudokuPanel = new JPanel(new GridLayout(9, 9));
        add(sudokuPanel);
       
    }

    // Actualizar la interfaz grafica con la matriz final
    public void updateSudoku(int[][] matriz, int[][] pistas) {
        // Limpiar el panel
        sudokuPanel.removeAll();
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // Crear un cuadro de texto para cada elemento de la matriz
                JTextField textField = new JTextField(Integer.toString(matriz[i][j]));
                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.setEditable(false);

                // Cambiar el tamaño de la fuente
                Font font = new Font("Arial", Font.BOLD, 20);
                textField.setFont(font);

                // Agregar marcos negritos alrededor de los cuadros 3x3
                int top = (i % 3 == 0) ? 2 : 0;
                int left = (j % 3 == 0) ? 2 : 0;
                int bottom = (i == 8) ? 2 : 1;
                int right = (j == 8) ? 2 : 1;
                // aqui se crea el borde de cada cuadro
                textField.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));

                // poner color a las PISTAS
                if (pistas[i][j] != 0) {
                    textField.setBackground(new Color(173, 216, 230));
                }

                sudokuPanel.add(textField);
            }
        }
        // Actualizar la interfaz
        revalidate();
        repaint();
    }

}

////////////////////////////////          S U D O K U                ////////////////////////////////////////

class Sudoku{
    
    static long startTime;
    static SudokuGUI sudokuGUI;  // INTERFAZ GRAFICA

    static int matriz[][]; // Crear la matriz original
    static int pistas[][] = new int[9][9]; // Crear la matriz de pistas
    
    static int flag1 = 0;
    public static void main(String[] args) {
        // Crear la interfaz grafica y que se vea
        sudokuGUI = new SudokuGUI();

        matriz = pedirMatriz();   // OBTENER LA MATRIZ DESDE LA ENTRADA

        // Copiar la matriz original en la matriz de pistas
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                pistas[i][j] = matriz[i][j];
            }
        }

        Nodo cabeza = new Nodo(0, 0);
        startTime = System.currentTimeMillis(); // Tiempo de inicio
        backtracking(cabeza);
        if(flag1 == 0){
            System.out.println("Sudoku invalido. ");
            exit(0);
        }
    }

    // obtener la matriz por consola 
    private static int[][] pedirMatriz() {

        int[][] matriz = new int[9][9]; 
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingresa la matriz Sudoku (fila por fila):");
        System.out.println("Cada casilla está separada por espacios, y cada línea por enters.");
        System.out.println("Si la casilla está vacía, ingresa un 0.");

        for (int i = 0; i < 9; i++) {
        
            while (true) {

                System.out.print("Ingresa la fila " + (i+1) + ": ");
                if (scanner.hasNextLine()) {
                    String linea = scanner.nextLine();
                    String[] elementos = linea.split(" ");  // separar los elementos de la linea
                    if (elementos.length == 9 && linea.matches("(\\d\\s){8}\\d")) {
                        for (int j = 0; j < 9; j++) {
                            matriz[i][j] = Integer.parseInt(elementos[j]);
                        }
                        break;
                    } else {
                        System.out.println("Error: debe ingresar una fila válida de 9 enteros.");
                    }
                }

            }
    
        }

        scanner.close();
        return matriz; // La matriz se retorna con las pistas

    }

    static void backtracking(Nodo nodo) {

        if (reject(nodo)) return; // Agregue el false para saber si funciono o no
        if (accept(nodo)) {

            long endTime = System.currentTimeMillis(); // Tiempo de fin
            sudokuGUI.setVisible(true); // Mostrar la interfaz grafica
            sudokuGUI.updateSudoku(matriz, pistas); // Actualizar la interfaz grafica con la matriz final
            System.out.println("Tiempo de ejecucion: " + (endTime - startTime) + " milisegundos");
            flag1 = 1;
            //exit(0); // TERMINAR EL PROGRAMA

        }


        // Si no se acepta ni se rechaza, se sigue buscando
        nodo.siguiente = first(nodo);

        while (nodo.siguiente != null) { // Mientras no sea el final de la matriz
            backtracking(nodo.siguiente);
            nodo.siguiente = next(nodo.siguiente);
        }

    }

    static boolean reject(Nodo nodo){

        if(nodo.posicion.i == 0 && nodo.posicion.j == 0 && matriz[nodo.posicion.i][nodo.posicion.j] == 0){
            return false;
        }
        if(matriz[nodo.posicion.i][nodo.posicion.j] == 0){
            return true;                                                   //Espacio vacio, no se debe rechazar
        }else{             
            
            //Numero a revisar
            int revisar = matriz[nodo.posicion.i][nodo.posicion.j];  
    
            //revisar fila
            for(int j=0;j<9;j++){
                if(j!=nodo.posicion.j){
                    if(matriz[nodo.posicion.i][j] == revisar){
                        return true;
                    }
                }
            }

            //revisar columna
            for(int i=0;i<9;i++){
                if(i != nodo.posicion.i){
                    if(matriz[i][nodo.posicion.j] == revisar){
                        return true;
                    }
                }
            }

            //revisar cuadrante
            int x = nodo.posicion.i/3;
            int y = nodo.posicion.j/3;

            for(int i=x*3;i<x*3+3;i++){
                for(int j=y*3;j<y*3+3;j++){

                    if(i != nodo.posicion.i && j != nodo.posicion.j){
                        if(matriz[i][j] == revisar){
                            return true;
                        }
                    }

                }
            }
            return false;
        }

    }

    static boolean accept(Nodo nodo){
        //Revisa si la matriz esta completa
        int flag = 0;
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                while(flag==0){
                    i=nodo.posicion.i;
                    j=nodo.posicion.j;
                    flag=1;
                }
                if (matriz[i][j] == 0) {
                    return false;
                }
            }
        }

        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                int revisar = matriz[i][j];
                //revisar fila
                for(int k=0;k<9;k++){
                    if(k!=j){
                        if(matriz[i][k] == revisar){
                            long endTime = System.currentTimeMillis();
                            System.out.println("Sudoku invalido. ");
                            System.out.println("Tiempo de ejecucion: " + (endTime - startTime) + " milisegundos");
                            exit(0);
                        }
                    }
                }
                //revisar columna
                for(int k=0;k<9;k++){
                    if(k != i){
                        if(matriz[k][j] == revisar){
                            long endTime = System.currentTimeMillis();
                            System.out.println("Sudoku invalido. ");
                            System.out.println("Tiempo de ejecucion: " + (endTime - startTime) + " milisegundos");
                            exit(0);
                        }
                    }
                }
                //revisar cuadrante
                int x = i/3;
                int y = j/3;
                for(int k=x*3;k<x*3+3;k++){
                    for(int l=y*3;l<y*3+3;l++){
                        if(k != i && l != j){
                            if(matriz[k][l] == revisar){
                                long endTime = System.currentTimeMillis();
                                System.out.println("Sudoku invalido. ");
                                System.out.println("Tiempo de ejecucion: " + (endTime - startTime) + " milisegundos");
                                exit(0);
                            }
                        }
                    }
                }   
            }
        }

        return true;

    }
    
    static Nodo first(Nodo nodo){

        //Revisa si el espacio vacio es el primero de la matriz
        //Si es el primero, se busca el primer espacio vacio de la matriz
        //Si no es el primero, se busca el siguiente espacio vacio de la matriz

        if(nodo.posicion.j==8){ //Final de la fila
            if(nodo.posicion.i==8){ //Final de la matriz
                return null;
            } else { //No es final de la matriz
                for(int i=nodo.posicion.i;i<9;i++){
                    for(int j=0;j<9;j++){
                        if(matriz[i][j]==0){
                            return new Nodo(i,j);
                        }
                    }
                }
            }
        } else { //No es final de la fila
            if(matriz[nodo.posicion.i][nodo.posicion.j]==0){ //Mismo espacio vacio
                matriz[nodo.posicion.i][nodo.posicion.j] =+1;
                return new Nodo(nodo.posicion.i,nodo.posicion.j);
            }
            if(matriz[nodo.posicion.i][nodo.posicion.j+1]==0){ //Siguiente espacio vacio
                return new Nodo(nodo.posicion.i,nodo.posicion.j+1);
            }else{ //Siguiente espacio no es vacio
                for(int i=nodo.posicion.j;i<9;i++){ //Busca el siguiente espacio vacio de la fila
                    if(matriz[nodo.posicion.i][i]==0){
                        return new Nodo(nodo.posicion.i,i);
                    }
                }
                //En caso de no encontrar un espacio vacio en la fila, se busca en el resto de la matriz.
                for(int i=nodo.posicion.i;i<9;i++){
                    for(int j=0;j<9;j++){
                        if(matriz[i][j]==0){
                            return new Nodo(i,j);
                        }
                    }
                }
            }
        }
        return null;
    }

    static Nodo next(Nodo nodo){
        if(matriz[nodo.posicion.i][nodo.posicion.j] == 9){
            matriz[nodo.posicion.i][nodo.posicion.j] = 0;
            return null;
        }else{
            matriz[nodo.posicion.i][nodo.posicion.j] = matriz[nodo.posicion.i][nodo.posicion.j] + 1;
        }
        return nodo;
    }
                /* 
                static void ImprimirSudoku() {
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            if (pistas[i][j] != 0) {
                                System.out.print("[" + pistas[i][j] + "]");
                            } else {
                                System.out.print("[*" + pistas[i][j] + "]");
                            }
                        }
                        System.out.println();
                    }
                }
                */
}

class Nodo{
    Nodo siguiente;
    Pair posicion;

    public Nodo(int i, int j){
        this.posicion = new Pair(i,j);
        this.siguiente = null;
    }
}

class Pair{
    int i;
    int j;

    public Pair(int i, int j){
        this.i = i;
        this.j = j;
    }
}