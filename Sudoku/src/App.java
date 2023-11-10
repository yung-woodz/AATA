import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
        Scanner scanner = new Scanner(System.in);
        clear();
        requestNumbers(scanner, sudoku);
    }

    public static void displaySudoku(Sudoku sudoku, int a, int b) {
        String rojo = "\u001B[31m";
        String reset = "\u001B[0m";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == a && j == b) {
                    System.out.print(rojo + "[" + reset + " " + rojo + "]" + reset);
                } else if (sudoku.getNumber(i, j) == 0)
                    System.out.print("[ ]");
                else
                    System.out.print("[" + sudoku.getNumber(i, j) + "]");
            }
            System.out.println();
        }
    }

    public static void requestNumbers(Scanner scanner, Sudoku sudoku) {
        String rojo = "\u001B[31m";
        String reset = "\u001B[0m";
        int number = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                clear();
                displaySudoku(sudoku, i, j);
                do {
                    System.out.print("Ingrese un numero en " + rojo + "[ ]" + reset + ": ");
                    String input = scanner.nextLine();
                    if (input.isEmpty())
                        number = 0;
                    else {
                        try {
                            number = Integer.parseInt(input);
                        } catch (NumberFormatException e) {
                            System.out.print("Número inválido");
                        }
                    }

                    if (!numberIsValid(number)) {
                        clear();
                        displaySudoku(sudoku, i, j);
                    }

                } while (!numberIsValid(number));
                sudoku.setNumber(i, j, number);
            }
        }
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static boolean numberIsValid(int number) {
        if (number >= 0 && number <= 9)
            return true;
        else
            return false;
    }
}
