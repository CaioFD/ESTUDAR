package Unidade04.Fila;


//FILA DINAMICA

public class Fila {
    private Celula primeiro;
    private Celula ultimo;

    //CONSTRUTOR que ira criar uma fila vazia
    public Fila(){
        primeiro = new Celula();
        ultimo = primeiro;
    }

    //metodo para inserir na celula
    //FIRST IN FIRST OUT
    public void InserirCelula(int x){
        ultimo.prox =  new Celula();
        ultimo = ultimo.prox;
    }

    //metodo para remover da celula
    //FIRST IN FIRST OUT
    public int RemoverCelula() throws Exception{
        if (primeiro == ultimo) {
            throw new Exception("ERRO ao remover!");
        }

        Celula tmp = primeiro;
        primeiro = primeiro.prox;
        int resp = primeiro.elemento;
        tmp.prox = null;
        tmp = null;

        return resp;
    }

    public void MostrarCelula(){
        System.out.println("[ ");
        for(Celula i = primeiro.prox; i != null; i = i.prox){
            System.out.println(i.elemento + " ");
        }
        System.out.println(" ]");
    }
}
