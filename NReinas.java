public class NReinas {
    public static boolean Reject(int[] x, int l) {
        for (int i = 0; i < l; i++)
            if ((x[i] == x[l] || x[i] == (x[l] + (l - i)) || x[i] == (x[l] + (i - l)))) return true;
        return false;
    }

    public static boolean First(int[] x, int l) {
        if (l < x.length - 1) {
            x[l + 1] = 1;
            return true;
        }
        return false;
    }

    public static boolean Next(int[] x, int l) {
        if (x[l] < x.length) {
            x[l] = x[l] + 1;
            return true;
        }
        return false;
    }

    public static void backtracking(int[] x, int l) {
        if (Reject(x, l)) return;
        if (l >= x.length - 1) output(x);
        if (First(x, l)) {
            do {
                backtracking(x, l + 1);
            } while (Next(x, l + 1));
        }
    }

    public static void output(int[] x) {
        for (int i = 0; i < x.length; i++) {
            System.out.print(x[i] + " ");
        }
        System.out.println();
        System.exit(0);
    }

    public static void main(String[] args) {
        int N = 8; // Puedes cambiar este valor según tus necesidades

        int[] x = new int[N];
        for (int i = 0; i < N; i++) x[i] = 0;
        backtracking(x, -1);
        System.out.println("No existe solución.");
    }
}
