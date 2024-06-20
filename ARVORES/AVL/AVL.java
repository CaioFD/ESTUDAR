package ARVORES.AVL;

public class AVL {
    public No raiz;//raiz da arvore

    public AVL(){
        raiz = null;
    }

    public boolean pesquisar(int elemento){
        return pesquisar(elemento, raiz);
    }

    public boolean pesquisar(int elemento, No i){
        boolean resp = false; 
            if (i == null) {
                resp = false;
            }else if (elemento == i.elemento) {
                resp = true;
            }else if (elemento < i.elemento) {
                pesquisar(elemento, i.esq);
            }else{
                pesquisar(elemento, i.dir);
            }
        return resp;
    }

    private void caminharCentral(No i){
        if (i != null) {
            caminharCentral(i.esq);
            System.out.print(i.elemento + " ");
            caminharCentral(i.dir);
        }
    }

    public void caminharCentral(){
        System.out.println("[");
        caminharCentral(raiz);
        System.out.println("]");
    }

    private void caminharPre(No i){
        if (i != null){
            System.out.println(i.elemento + "(fator " + (No.getNivel(i.dir) - No.getNivel(i.esq)) + ") ");
            caminharPre(i.esq);
            caminharPre(i.dir);
        }
    }

    public void caminharPre(){
        System.out.println("[");
        caminharPre(raiz);
        System.out.println("]");
    }

    private void caminharPos(No i){
        if (i != null) {
            caminharPos(i.esq);
            caminharPos(i.dir);
            System.out.println(i.elemento + " ");
        }
    }

    public void caminharPOs(){
        System.out.println("[");
        caminharPos(raiz);
        System.out.println("]");
    }

    public void inserir(int elemento) throws Exception{
        raiz = inserir(elemento, raiz);
    }

    private No inserir(int elemento, No i) throws Exception{
        if (i == null){
            i = new No(elemento);
        }else if (elemento < i.elemento){
            i.esq = inserir(elemento, i.esq);
        }else if (elemento > i.elemento) {
            i.dir = inserir(elemento, i.dir);
        }else{
            throw new Exception("Erro ao inserir!");
        }
        return balancear(i);
    }

    public void remover(int elemento) throws Exception{
        raiz = remover(elemento, raiz);
    }

    private No remover(int elemento, No i)throws Exception{
        if (i == null) {
            throw new Exception("Erro ao remover!");
        }else if (elemento < i.elemento) {
            i.esq = remover(elemento, i.esq);
        }else if(elemento > i.elemento){
            i.dir = remover(elemento, i.dir);
        }else if(i.dir == null){
            i = i.esq;
        }else if(i.esq == null){
            i = i.dir;
        }else{
            i.esq = maiorEsq(i, i.esq);
        }
        return balancear(i);
    }


    No maiorEsq (No i, No j){
        if (j.dir == null) {
            i.elemento = j.elemento;// Substitui i por j.
            j = j.esq;// Substitui j por j.ESQ.
        }else{
            j.dir = maiorEsq(i, j.dir);
        }
        return j;
    }

    private No balancear(No no) throws Exception {
		if (no != null) {
			int fator = No.getNivel(no.dir) - No.getNivel(no.esq);
			// Se balanceada
			if (Math.abs(fator) <= 1) {
				no.setNivel();
			// Se desbalanceada para a direita
			} else if (fator == 2) {
				int fatorFilhoDir = No.getNivel(no.dir.dir) - No.getNivel(no.dir.esq);
				// Se o filho a direita tambem estiver desbalanceado
				if (fatorFilhoDir == -1) {
					no.dir = rotacionarDir(no.dir);
				}
				no = rotacionarEsq(no);
			// Se desbalanceada para a esquerda
			} else if (fator == -2) {
				int fatorFilhoEsq = No.getNivel(no.esq.dir) - No.getNivel(no.esq.esq);
				// Se o filho a esquerda tambem estiver desbalanceado
				if (fatorFilhoEsq == 1) {
					no.esq = rotacionarEsq(no.esq);
				}
				no = rotacionarDir(no);
			} else {
				throw new Exception(
						"Erro no No(" + no.elemento + ") com fator de balanceamento (" + fator + ") invalido!");
			}
		}
		return no;
	}

    private No rotacionarDir(No no) {
		System.out.println("Rotacionar DIR(" + no.elemento + ")");
		No noEsq = no.esq;
		No noEsqDir = noEsq.dir;

		noEsq.dir = no;
		no.esq = noEsqDir;
		no.setNivel(); // Atualizar o nivel do no
		noEsq.setNivel(); // Atualizar o nivel do noEsq

		return noEsq;
	}

	private No rotacionarEsq(No no) {
		System.out.println("Rotacionar ESQ(" + no.elemento + ")");
		No noDir = no.dir;
		No noDirEsq = noDir.esq;

		noDir.esq = no;
		no.dir = noDirEsq;

		no.setNivel(); // Atualizar o nivel do no
		noDir.setNivel(); // Atualizar o nivel do noDir
		return noDir;
	}

    
}
