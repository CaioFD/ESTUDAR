package Unidade04.ListaSimples;

public class Celula {
    int elemento;
    Celula prox;

    //construtor vazio
    public Celula(){
        this(0);
    }

    public Celula(int elemento){
        this.elemento = elemento;
        this.prox = null;
    }
}
