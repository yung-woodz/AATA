#include <iostream>
#include <windows.h>
#include <conio.h>

#define RESET SetConsoleTextAttribute(hConsole, 7);
#define COLOR(color) SetConsoleTextAttribute(hConsole, color);

using namespace std;

void crearMatriz(int matriz[9][9]) {
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            matriz[i][j] = ' ';
        }
    }
}

void recorrer(int matriz[9][9]) {
    
    HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
    char tecla;

    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            COLOR(4); // Cambia el color del corchete actual a rojo
            cout << "[";
            RESET;
            tecla = _getch();
            matriz[i][j] = tecla;
            cout << static_cast<char>(matriz[i][j]);
            COLOR(4);
            cout << "]";
            
        }
        RESET;
        cout << endl;
    }
}

int main() {
    int matriz[9][9];

    crearMatriz(matriz);

    cout << "Asigna valores a la matriz:" << endl;
    recorrer(matriz);

    return 0;
}
