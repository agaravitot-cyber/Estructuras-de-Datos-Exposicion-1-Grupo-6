public class Main {
    public static void main(String[] args) {
        simulacionCola();
        simulacionBFS();
    }

    public static void simulacionCola() {

        Cola ejemplo = new Cola();

        System.out.println(ejemplo.frente());

        ejemplo.encolar(10);
        ejemplo.encolar(false);
        ejemplo.encolar("Hola Mundo!!");
        ejemplo.encolar("Soy un Nodo");

        System.out.println(ejemplo.getTamano());

        System.out.println(ejemplo.frente());
        ejemplo.imprimir();
        while (!ejemplo.estaVacia()) {
            ejemplo.desencolar();
        }

        System.out.println(ejemplo.getTamano());
    }

    public static void simulacionBFS() {
        int[][] laberinto = {
                { 0, 0, 1, 0, 0 },
                { 1, 0, 1, 0, 1 },
                { 0, 0, 0, 0, 0 },
                { 0, 1, 1, 1, 0 },
                { 0, 0, 0, 1, 0 }
        };
        System.out.println("Pasos mínimos: " +
                caminoMasCorto(laberinto, 0, 0, 4, 4));
    }

    public static int caminoMasCorto(int[][] lab, int fi, int ci, int ff, int cf) {
        int[][] dist = new int[lab.length][lab[0].length];
        for (int[] fila : dist)
            java.util.Arrays.fill(fila, -1);

        int[] df = { -1, 1, 0, 0 };
        int[] dc = { 0, 0, -1, 1 };

        Cola cola = new Cola();
        cola.encolar(new int[] { fi, ci });
        dist[fi][ci] = 0;

        while (!cola.estaVacia()) {
            int[] act = (int[]) cola.desencolar();
            if (act[0] == ff && act[1] == cf)
                return dist[ff][cf];

            for (int k = 0; k < 4; k++) {
                int f = act[0] + df[k];
                int c = act[1] + dc[k];
                if (f >= 0 && f < lab.length && c >= 0 && c < lab[0].length
                        && lab[f][c] == 0 && dist[f][c] == -1) {
                    dist[f][c] = dist[act[0]][act[1]] + 1;
                    cola.encolar(new int[] { f, c });
                }
            }
        }
        return -1;
    }
}

class Nodo {
    Object valor;
    Nodo siguiente;

    public Nodo(Object valor) {
        this.valor = valor;
        this.siguiente = null;
    }
}

class Cola {
    private Nodo inicio;
    private Nodo fin;
    private int tamano;

    public Cola() {
        this.inicio = null;
        this.fin = null;
    }

    public boolean estaVacia() {
        return inicio == null;
    }

    public void encolar(Object valor) {
        Nodo tmp = new Nodo(valor);
        if (estaVacia()) {
            inicio = tmp;
            fin = tmp;
        } else {
            fin.siguiente = tmp;
            fin = tmp;
        }
        tamano++;
    }

    public Object desencolar() {
        if (estaVacia())
            return null;
        Object valor = inicio.valor;
        inicio = inicio.siguiente;
        if (estaVacia()) {
            fin = null;
        }
        tamano--;
        return valor;
    }

    public Object frente() {
        if (estaVacia())
            return "No hay nada!!";
        return inicio.valor;
    }

    public void imprimir() {
        Nodo tmp = inicio;
        while (tmp != null) {
            System.out.print("\"" + tmp.valor + "\" ");
            tmp = tmp.siguiente;
        }
        System.out.println();
    }

    public int getTamano() {
        return this.tamano;
    }
}