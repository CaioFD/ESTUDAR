package EstruturaDoidona;

/**
 * ExDoidona
 */
class Celula {
	public int elemento; // Elemento inserido na celula.
	public Celula prox; // Aponta a celula prox.

	public Celula() {
		this(0);
	}

	public Celula(int elemento) {
                this.elemento = elemento;
                this.prox = null;
	}
}


class Lista {
	private Celula primeiro;
	private Celula ultimo;

	public Lista() {
		primeiro = new Celula();
		ultimo = primeiro;
	}


        //===== MÉTODOS DE INSERIR ====//
        public void inserirInicio(int elemento){
                Celula tmp = new Celula(elemento);
                tmp.prox = primeiro.prox;
                primeiro.prox = tmp;
                if (primeiro == ultimo) {
                        ultimo = tmp;
                }
                tmp = null;
        }

        public void inserirFIm(int elemento){
                ultimo.prox = new Celula(elemento);
                ultimo = ultimo.prox;
        }

        public void inserir(int elemento, int pos) throws Exception{
                int tamanho = tamanho();
                if (pos < 0 || pos > tamanho) {
                        throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
                }else if (pos == 0) {
                        inserirInicio(elemento);
                }else if (pos == tamanho) {
                        inserirFIm(elemento);
                }else{
                        Celula i = primeiro;
                        for(int j = 0; j < pos; j++){
                                i = i.prox;
                        } 
                Celula tmp = new Celula(elemento);
                tmp.prox = i.prox;
                i.prox = tmp;
                tmp = i = null;
                }
        }


        //===== MÉTODOS DE REMOVER ====//
        public int removerInicio() throws Exception{
                if(primeiro == ultimo){
                        throw new Exception("Erro ao remover (vazia)!");
                }
                Celula tmp = primeiro;
                primeiro = primeiro.prox;
                int resp = primeiro.elemento;
                tmp.prox = null;
                tmp = null;
                return resp;
        }

        public int removerFim() throws Exception{
                if(primeiro == ultimo){
                        throw new Exception("Erro ao remover (vazia)!");
                }

                Celula i;
                for(i = primeiro; i.prox != ultimo; i = i.prox);
                
                int resp = ultimo.elemento;
                ultimo = i;
                i = ultimo.prox = null;
                return resp;
        }

        public int remover(int pos) throws Exception{
                int resp;
                int tamanho = tamanho();

                if(primeiro == ultimo){
                        throw new Exception("Erro ao remover (vazia)!");
                }else if(pos < 0 || pos > tamanho){
                        throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
                }else if(pos == 0){
                        removerInicio();
                }else if(pos == tamanho){
                        removerFim();
                }else{
                        Celula i = primeiro;
                        for(int j = 0; j < pos; j++){
                                i = i.prox; 
                        }
                        Celula tmp = i.prox;
                        resp = tmp.elemento;
                        i.prox = tmp.prox;
                        tmp.prox = null;
                        i = tmp = null;
                }
                return resp;
        }


        //==== MÉTODOS GERAIS====//
        public int tamanho() {
                int tamanho = 0; 
                for(Celula i = primeiro; i != ultimo; i = i.prox, tamanho++);
                return tamanho;
        }

        public void mostrar() {
		System.out.print("[ ");
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
			System.out.print(i.elemento + " ");
		}
		System.out.println("] ");
	}

        public boolean pesquisar(int x) {
		boolean resp = false;
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
                if(i.elemento == x){
                        resp = true;
                        i = ultimo;
                }
		}
		return resp;
	}
}


class No {
	public int elemento; // Conteudo do no.
	public No esq, dir; // Filhos da esq e dir.
	public int nivel; // Numero de niveis abaixo do no

        public No(int elemento) {
		this(elemento, null, null, 1);
	}
        
        public No(int elemento, No esq, No dir, int nivel) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
		this.nivel = nivel;
	}

        public void setNivel() {
		this.nivel = 1 + Math.max(getNivel(esq), getNivel(dir));
	}

        public static int getNivel(No no) {
		return (no == null) ? 0 : no.nivel;
	}

}

class ArvoreBinaria {
	protected No raiz; // Raiz da arvore.

        public ArvoreBinaria() {
		raiz = null;
	}

        public boolean pesquisar(int x) {
		return pesquisar(x, raiz);
	}

        private boolean pesquisar (int elemento, No i){
                boolean resp;
                if (i == null) {
                        resp = false;
                }else if(elemento == i.elemento){
                        resp = true;
                }else if(elemento < i.elemento){
                        resp = pesquisar(elemento, i.esq);
                }else{
                        resp = pesquisar(elemento, i.dir);
                }
                return resp;
        }

        //=== MÉTODOS DE CAMINHAR ===//
        private void caminharCentral(No i) {
		if (i != null) {
			caminharCentral(i.esq); // Elementos da esquerda.
			System.out.print(i.elemento + " "); // Conteudo do no.
			caminharCentral(i.dir); // Elementos da direita.
		}
	} 

        private void caminharPre(No i) {
		if (i != null) {
			System.out.print(i.elemento + " "); // Conteudo do no.
			caminharPre(i.esq); // Elementos da esquerda.
			caminharPre(i.dir); // Elementos da direita.
		}
	}

        private void caminharPos(No i) {
		if (i != null) {
			caminharPos(i.esq); // Elementos da esquerda.
			caminharPos(i.dir); // Elementos da direita.
			System.out.print(i.elemento + " "); // Conteudo do no.
		}
	}

        public void caminharCentral() {
		System.out.print("[ ");
		caminharCentral(raiz);
		System.out.println("]");
	}

        public void caminharPre() {
		System.out.print("[ ");
		caminharPre(raiz);
		System.out.println("]");
	}

        public void caminharPos() {
		System.out.print("[ ");
		caminharPos(raiz);
		System.out.println("]");
	}


        //=== MÉTODOS INSERIR ===//
        protected No inserir(int elemento, No i) throws Exception {
                if (i == null){
                        i = new No(elemento);
                }else if (elemento < i.elemento) {
                        i.esq = inserir(elemento, i.esq);
                }else if (elemento > i.elemento) {
                        i.dir = inserir(elemento, i.dir);
                }else{
                        throw new Exception ("Erro ao inserir!!");
                }

                return i;
        }


        public void inserir(int x) throws Exception {
		raiz = inserir(x, raiz);
	}
}