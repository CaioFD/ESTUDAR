package Unidade04.Pilha;

public class Pilha {
    private Celula topo;

    class Celula{ 
        public int elemento; // aqui sera os elemenotos inseridos na celula
        public Celula prox; // Ira apontar para a PROXIMA celula
    
    
        //CONSTRUTOR DA CLASSE
        public Celula(){
            this(0);
        }
    
        public Celula(int elemento){
            this.elemento = elemento;
            this.prox = null;
        }
    }
    //CONSTRUTOR que ira criar uma pilha vazia
    public Pilha(){
        topo = null;
    }

    public void InserirPILHA(int elemento){
        Celula tmp = new Celula(elemento);
        tmp.prox = topo;
        topo = tmp;
        tmp = null;
    }

    public int RemoverPILHA(int elemento) throws Exception{
        if (topo == null) {
            throw new Exception("Erro!"); 
        }//end if
        int resp = 0;
        resp = topo.elemento;
        Celula tmp = topo;
        topo = topo.prox;
        tmp.prox = null;
        tmp = null;
        return resp;
    }





}
