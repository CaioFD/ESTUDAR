package Unidade04.Pilha;

public class PilhaPrincipal {
    public static void main(String[] args) {
        try{
            int x1,x2,x3;
            System.out.println(" ---- PILHA FLEXIVEL ----");
			Pilha pilha = new Pilha();

            pilha.InserirPILHA(0);
			pilha.InserirPILHA(1);
			pilha.InserirPILHA(2);
			pilha.InserirPILHA(3);
			pilha.InserirPILHA(4);
			pilha.InserirPILHA(5);

            System.out.println("Mostrar pos insercoes: (0,1,2,3,4,5)");
            //pilha.MostrarPILHA();

           // x1 = pilha.RemoverPILHA();
           // x2 = pilha.RemoverPILHA();
           // x3 = pilha.RemoverPILHA();

            //System.out.print("Apos as remocoes (" + x1 + "," + x2 + "," + x3 + "): ");
			//pilha.MostrarCelula();

        }catch(Exception e){
            System.out.println(e.getMessage());
        }   
    }
}
