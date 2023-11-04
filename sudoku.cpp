#include <iostream>
#include <windows.h>

#define RESET SetConsoleTextAttribute(hConsole, 7);

using namespace std;


void printMatriz() {

    HANDLE hConsole = GetStdHandle( STD_OUTPUT_HANDLE );
    SetConsoleTextAttribute(hConsole, 4);

    int matriz[9][9];

    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            matriz[i][j] = ' ';
        }
    }

    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            cout << "[" << static_cast<char>(matriz[i][j]) << "]" ;
        }
        cout << endl;
    }

    RESET;
}

void recorrer() {
    
}

int main() {
    printMatriz();

    cout << "asignale un numero" << endl;

    

    return 0;
}
