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
                int resp = 0;
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
        protected No inserirArvore(int elemento, No i) throws Exception {
                if (i == null){
                        i = new No(elemento);
                }else if (elemento < i.elemento) {
                        i.esq = inserirArvore(elemento, i.esq);
                }else if (elemento > i.elemento) {
                        i.dir = inserirArvore(elemento, i.dir);
                }else{
                        throw new Exception ("Erro ao inserir!!");
                }
                return i;
        }

        private void inserirPai(int x, No i, No pai) throws Exception {
		if (i == null) {
			if (x < pai.elemento) {
				pai.esq = new No(x);
			} else {
				pai.dir = new No(x);
			}
		} else if (x < i.elemento) {
			inserirPai(x, i.esq, i);
		} else if (x > i.elemento) {
			inserirPai(x, i.dir, i);
		} else {
			throw new Exception("Erro ao inserirPai!");
		}
	}

        public void inserirArvore(int x) throws Exception {
		raiz = inserirArvore(x, raiz);
	}  
        
        public void inserirPai(int x) throws Exception {
		if (raiz == null) {
			raiz = new No(x);
		} else if (x < raiz.elemento) {
			inserirPai(x, raiz.esq, raiz);
		} else if (x > raiz.elemento) {
			inserirPai(x, raiz.dir, raiz);
		} else {
			throw new Exception("Erro ao inserirPai!");
		}
	}

        //=== MÉTODOS REMOVER  ===//
        protected No remover(int x, No i) throws Exception {
		if (i == null) {
			throw new Exception("Erro ao remover!");
		} else if (x < i.elemento) {
			i.esq = remover(x, i.esq);
		} else if (x > i.elemento) {
			i.dir = remover(x, i.dir);
			// Sem no a direita.
		} else if (i.dir == null) {
			i = i.esq;
			// Sem no a esquerda.
		} else if (i.esq == null) {
			i = i.dir;
			// No a esquerda e no a direita.
		} else {
			i.esq = maiorEsq(i, i.esq);
		}
		return i;
	}

        private void remover2(int x, No i, No pai) throws Exception {
		if (i == null) {
			throw new Exception("Erro ao remover2!");
		} else if (x < i.elemento) {
			remover2(x, i.esq, i);
		} else if (x > i.elemento) {
			remover2(x, i.dir, i);
		} else if (i.dir == null) {
			pai = i.esq;
		} else if (i.esq == null) {
			pai = i.dir;
		} else {
			i.esq = maiorEsq(i, i.esq);
		}
	}


        public void remover(int x) throws Exception {
		raiz = remover(x, raiz);
	}


        public void remover2(int x) throws Exception {
		if (raiz == null) {
			throw new Exception("Erro ao remover2!");
		} else if (x < raiz.elemento) {
			remover2(x, raiz.esq, raiz);
		} else if (x > raiz.elemento) {
			remover2(x, raiz.dir, raiz);
		} else if (raiz.dir == null) {
			raiz = raiz.esq;
		} else if (raiz.esq == null) {
			raiz = raiz.dir;
		} else {
			raiz.esq = maiorEsq(raiz, raiz.esq);
		}
	}

        //=== MÉTODOS GERAIS  ===//
        protected No maiorEsq(No i, No j) {
		// Encontrou o maximo da subarvore esquerda.
		if (j.dir == null) {
			i.elemento = j.elemento; // Substitui i por j.
			j = j.esq; // Substitui j por j.ESQ.

			// Existe no a direita.
		} else {
			// Caminha para direita.
			j.dir = maiorEsq(i, j.dir);
		}
		return j;
	}

        public int getMaior() {
		int resp = -1;

		if (raiz != null) {
			No i;
			for (i = raiz; i.dir != null; i = i.dir)
				;
			resp = i.elemento;
		}

		return resp;
	}

        public int getMenor() {
		int resp = -1;

		if (raiz != null) {
			No i;
			for (i = raiz; i.esq != null; i = i.esq)
				;
			resp = i.elemento;
		}

		return resp;
	}

        public int getAltura(No i, int altura) {
		if (i == null) {
			altura--;
		} else {
			int alturaEsq = getAltura(i.esq, altura + 1);
			int alturaDir = getAltura(i.dir, altura + 1);
			altura = (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
		}
		return altura;
	}

        public int getAltura() {
		return getAltura(raiz, 0);
	}

        public int getRaiz() throws Exception {
		return raiz.elemento;
	}

        public static boolean igual(ArvoreBinaria a1, ArvoreBinaria a2) {
		return igual(a1.raiz, a2.raiz);
	}

	private static boolean igual(No i1, No i2) {
		boolean resp;
		if (i1 != null && i2 != null) {
			resp = (i1.elemento == i2.elemento) && igual(i1.esq, i2.esq) && igual(i1.dir, i2.dir);
		} else if (i1 == null && i2 == null) {
			resp = true;
		} else {
			resp = false;
		}
		return resp;
	}

        public int soma() {
		return soma(raiz);
	}

	public int soma(No i) {
		int resp = 0;
		if (i != null) {
			resp = i.elemento + soma(i.esq) + soma(i.dir);
		}
		return resp;
	}

        public int quantidadePares() {
		return quantidadePares(raiz);
	}

	public int quantidadePares(No i) {
		int resp = 0;
		if (i != null) {
			resp = ((i.elemento % 2 == 0) ? 1 : 0) + quantidadePares(i.esq) + quantidadePares(i.dir);
		}
		return resp;
	}

        public boolean hasDiv11() {
		return hasDiv11(raiz);
	}

	public boolean hasDiv11(No i) {
		boolean resp = false;
		if (i != null) {
			resp = (i.elemento % 11 == 0) || hasDiv11(i.esq) || hasDiv11(i.dir);
		}
		return resp;
	}
}

/**
 * DOIDONA
 */
class Doidona {
	int T1[];
	int T3[];

	ArvoreBinaria arvore1;
	ArvoreBinaria arvore2;

	Lista lista;

	public Doidona(){
		T1 = new int[100];
		T3 = new int[100];

		for(int i = 0; i<100; i++){
			T1[i] = -1;
			T3[i] = -1;
		}
		arvore1 = new ArvoreBinaria();
		arvore2 = new ArvoreBinaria();
		lista = new Lista();
	}
	int HashT1(int elemento){
		return elemento%100;
	}
	int HashT2(int elemento){
		return elemento%3;
	}
	int HashT3(int elemento){
		return elemento%100;
	}
		public int reh(int elemento) {
			return ++elemento % 100;
		}

	void InserirDoidona(int elemento){
		if (T1[HashT1(elemento)] == -1) {
			System.out.println("Sem conflito na T1!");
			T1[HashT1(elemento)] = elemento;
		}else if (HashT2(elemento) == 0) {
			System.out.println("Conflito na T1!");
			if (T3[HashT3(elemento)] == -1) {
				System.out.println("Sem conflito na T3!");
				T3[HashT3(elemento)] = elemento;
			}
			else{
			System.out.println("ReHash!");
				if (T3[reh(elemento)] == -1) {
					T3[reh(elemento)] = elemento;
				}else{
					System.out.println("Falha no reHash, vamos para a arvore!");
					arvore1.InserirDoidona(elemento);
				}
			}
		}else if(HashT2(elemento) == 1){
			System.out.println("Conflito na T1! Vamos para a Lista");
			lista.inserirFIm(elemento);
		}else if(HashT2(elemento) == 2){
			System.out.println("Conflito na T1! Vamos para a Arvore2");
            arvore2.InserirDoidona(elemento);
		}else{
			System.out.println("ERRO!!!!");
		}
	}

	boolean pesquisar(int elemento){
        boolean resp = false;
        if(T1[HashT1(elemento)] == elemento){
            resp = true;
        } else if(HashT2(elemento) == 0){
            if(T3[HashT3(elemento)] == elemento){
                resp = true;
            }else if(T3[HashT3(elemento)+1]) == elemento{
		resp = true;
	}
	    else{
                resp = arvore1.pesquisar(elemento);
            }
        } else if(HashT2(elemento) == 1){
            resp = lista.pesquisar(elemento);
        } else if(HashT2(elemento) == 2){
            resp = arvore2.pesquisar(elemento);
        } else{
            System.out.println("Não encontrado!");
        }
        return resp;
    }

	void mostrar(){
        System.out.println("\n\nT1");
        for(int i = 0;i<100; i++){
            if(T1[i] != -1){
                System.out.println(i+" - "+T1[i]);
            }
        }
        System.out.println("\n\nT3");
        for(int j = 0; j<100; j++){
            if(T3[j] != -1){
                System.out.println(j+" - "+T3[j]);
            }
        }
        System.out.println("Arvore do T3: ");
        arvore1.caminharCentral();
        System.out.println("Lista: ");
        lista.mostrar();
        System.out.println("\n\nUltima arvore: ");
        arvore2.caminharCentral();
    }

	public class exDoidonaTAD {
		public static void main(String[] args) throws Exception{
			Doidona tabela = new Doidona();
			int[] numeros = {105, 205, 305, 110, 210, 310, 115, 215, 315, 120};
			for(int i:numeros){
				System.out.println("\n\nInserindo: "+i);
				tabela.InserirDoidona(i);
				System.out.println("\n\nEstrutura da doidona: ");
				tabela.mostrar();
				System.out.println("\n");
			}
		}
	}
}