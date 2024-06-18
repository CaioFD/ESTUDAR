package Unidade04.ListaSimples;

public class Lista {
    private Celula primeiro;
    private Celula ultimo;

    class Celula {
        public int elemento;
        public Celula prox;
    
        public Celula(){
            this(0);
        }
    
        public Celula(int elemento){
            this.elemento = elemento;
            this.prox = null;
        }
    }
    // ======= METODOS ========/
    public int tamanho(){
        int tamanho = 0;
        for(Celula i = primeiro; i != ultimo; i = i.prox){
            tamanho++;
        }
        return tamanho;
    }

    public void mostrar(){
        System.out.println("[ ");
        for(Celula i = primeiro.prox; i != null; i = i.prox ){
            System.out.println(i.elemento);
        }
        System.out.println("[ ");
    }
    // ====== METODOS INSERIR ========/ 

    public void inserirInicio(int elemento){
        Celula tmp = new Celula(elemento);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        }//end if
    }// end inserirINICIO

    public void inserirFIM(int elemento){
        ultimo.prox = new Celula(elemento);
        ultimo = ultimo.prox;
    }// end inserirFIM

    public void INSERIR( int elemento, int pos) throws Exception{
        int tam = tamanho();
        if (pos < 0 || pos > tam) {
            throw new Exception("Erro ao inserir! posicao invalida");
        }

        if (pos == 0) {
            inserirInicio(elemento);
        }else if (pos == tam) {
            inserirFIM(elemento);
        }else{

            Celula i = primeiro;

            for(int j = 0; j <pos; j++){
                i = i.prox;
            }//end for

            Celula tmp = new Celula(elemento);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = null;
            i = null;
        }// end if
    }//end INSERIR


    // ====== METODOS REMOVER ========/ 

    public int removerInicio() throws Exception{
        int resp = 0;
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover! nao existe numero para ser removido.");
        }
        Celula tmp = primeiro;
        primeiro = primeiro.prox; 
        resp = primeiro.elemento;
        tmp.prox = null;
        tmp = null;

        return resp;
    }//end remover inicio

    public int removerFIM() throws Exception{
        int resp = 0;
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover! nao existe numero para ser removido.");
        }
        Celula i;
        for(i = primeiro; i != ultimo; i = i.prox);
        resp = ultimo.elemento;
        ultimo = i;
        i.prox = null;

        return resp;
    }//end remover Fim

    public int REMOVER (int elemento, int pos) throws Exception{
        int resp = 0;
        int tam = tamanho();

        if (pos < 0 || pos > tam) {
            throw new Exception("Erro ao remover!");
        }else if (pos == 0) {
            resp = removerInicio();
        }else if(pos == tam){
            resp = removerFIM();
        }else{
            Celula i = primeiro;
            for(int j = 0; j < pos; j++ , i = i.prox);
            Celula tmp = i.prox;
            resp = tmp.elemento;
            i.prox = tmp.prox;
            tmp.prox = null;
            tmp = i = null;
        }//end if
        return resp;
    }//end remover


}// end Class


