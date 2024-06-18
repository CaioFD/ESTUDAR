package pilha;

public class pilha {

    public void inserirInicio(int elemento){
        Celula tmp = new Celula(elemento);
        tmp.prox = topo;
        topo = tmp;
        tmp = null;
    }
}
