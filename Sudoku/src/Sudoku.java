class Sudoku {
    private int[][] sudoku;

    public Sudoku() {
        sudoku = new int[9][9];
    }

    public void setNumber(int y, int x, int number) {
        sudoku[y][x] = number;
    }

    public int getNumber(int y, int x) {
        return sudoku[y][x];
    }
}