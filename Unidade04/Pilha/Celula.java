package Unidade04.Pilha;

class Celula{ 
    public int elemento; // aqui sera os elemenotos inseridos na celula
    public Celula prox; // Ira apontar para a PROXIMA celula


    //CONSTRUTOR DA CLASSE
    public Celula(){
        this(0);
    }

    public Celula(int elemenoto){
        this.elemento = elemenoto;
        this.prox = null;
    }
}