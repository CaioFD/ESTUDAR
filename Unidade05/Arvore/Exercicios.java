class NO {
    int elemento;
    NO esq;
    NO dir;

    public NO() {
        this(0);
    }

    public NO(int elemento) {
        this.elemento = elemento;
        this.esq = null;
        this.dir = null;
    }
} // end class NO

class Celula {
    int elemento;
    Celula prox;
    NO raiz;

    public Celula() {
        this(0);
    }

    public Celula(int elemento) {
        this.elemento = elemento;
        this.prox = null;
        this.raiz = null;
    }
} // end class Celula

class Lista {
    private Celula primeiro;
    private Celula ultimo;

    Lista() {
        Celula j = new Celula();
        primeiro = j;
        ultimo = primeiro;
        for (int i = 0; i < 10; i++) {
            j.prox = new Celula(i);
            ultimo = j.prox;
        }
    }

    public void mostrar() {
        System.out.print("{");
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            System.out.print( " " + i.elemento + " ");
        }
        System.out.println("}");
    }

    public void inserir(int elemento) {
        for (Celula i = primeiro.prox; i != ultimo; i = i.prox) {
            if (i.elemento == (elemento / 10)) {
                inserirNo(elemento, i.raiz);
            }
        }
    }

    private void inserirNo(int elemento, NO i) {
        if (i == null) {
            i = new NO(elemento);
        } else if (elemento < i.elemento) {
            inserirNo(elemento, i.esq);
        } else if (elemento > i.elemento) {
            inserirNo(elemento, i.dir);
        } else {
            //
            System.err.println("ERRO");
        }
    }
} // end class Lista

public class Exercicios {
    public static void main(String[] args) {
        Lista lista = new Lista();
        lista.mostrar();
    }
}
