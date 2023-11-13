public class Nodo {
    
    private int x;
    private int y;
    private int[][] matriz;
    public Nodo siguiente;

    public Nodo(int x, int y, int[][] matriz) {
        this.x = x;
        this.y = y;
        this.matriz = matriz;
        this.siguiente = null;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}
