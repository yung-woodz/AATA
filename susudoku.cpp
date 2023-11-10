#include <iostream>
#include <windows.h>
#include <math.h>
#include <conio.h>
#include <sys/time.h>

#define RESET SetConsoleTextAttribute(hConsole, 7);
#define COLOR(color) SetConsoleTextAttribute(hConsole, color);

using namespace std;

long obtenerTiempo(){
    struct timeval inicio;
    gettimeofday(&inicio, NULL);
    return inicio.tv_sec*1000000+inicio.tv_usec;
}

class Sudoku {
public:
    int getNumber(int i, int j) {
        return board[i][j];
    }

    void setNumber(int i, int j, int number) {
        board[i][j] = number;
    }

private:
    int board[9][9];
};

void crearMatriz(Sudoku& sudoku) {
    int matriz[9][9] = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            sudoku.setNumber(i, j, matriz[i][j]);
        }
    }
}

void displaySudoku(Sudoku sudoku, int a, int b) {
    HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);

    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            if (i == a && j == b) {
                COLOR(4);
            }
            int number = sudoku.getNumber(i, j);
            if (number == 0) {
                cout << "[ ]";
            } else {
                cout << "[" << number << "]";
            }
            RESET;
        }
        cout << endl;
    }
}

void requestNumbers(Sudoku& sudoku) {
    int currentRow = 0;
    int currentCol = 0;
    int tecla;

    while (true) {
        system("cls");
        displaySudoku(sudoku, currentRow, currentCol);
        tecla = _getch();

        if (tecla == 27)
            break;
        else if (tecla >= '1' && tecla <= '9') {
            int number = tecla - '0';
            sudoku.setNumber(currentRow, currentCol, number);
            currentCol++;
            if (currentCol == 9) {
                currentCol = 0;
                currentRow++;
                if (currentRow == 9)
                    break;
            }
        }
    }
}

int main() {
    long inicio = obtenerTiempo();
    double tiempoEnSegundos;
    long tiempoEnMicrosegundos;

    Sudoku sudoku;

    crearMatriz(sudoku);

    cout << "Asigna valores a la matriz (presiona ESC para salir):" << endl;
    requestNumbers(sudoku);

    long final = obtenerTiempo();
    tiempoEnMicrosegundos = final - inicio;
    tiempoEnSegundos = tiempoEnMicrosegundos * pow(10, -6);
    cout << endl << endl;
    cout << "El tiempo de ejecucion en microsegundos es: " << tiempoEnMicrosegundos << endl << endl;
    cout << "EL tiempo de ejecucion en segundos es: " << tiempoEnSegundos << endl << endl;

    return 0;
}
