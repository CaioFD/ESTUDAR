package ARVORES.AVL;

public class No {
    public int elemento;
    public No esq, dir;
    public int nivel;

    //construtor
    public No(int elemento){
        this(elemento, null, null, 1);
    }

    public No(int elemento, No esq, No dir, int nivel ){
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
        this. nivel = nivel;
    }

    public static int getNivel(No no){
        return (no == null) ? 0 : no.nivel;
    }

    public void setNivel(){
        this.nivel = 1 + Math.max(getNivel(esq), getNivel(dir));
    }
}
