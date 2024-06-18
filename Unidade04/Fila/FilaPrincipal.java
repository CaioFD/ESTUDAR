package Unidade04.Fila;

public class FilaPrincipal {
    public static void main( String[] args ) throws Exception{
        System.out.println("---- Fila Flexivel ----");

        Fila fila = new Fila();
        int x1,x2,x3;

        fila.InserirCelula(1);
        fila.InserirCelula(3);
        fila.InserirCelula(5);
        fila.InserirCelula(7);

        System.out.println("Mostrar pos insercoes: (1,3,5,7,) ");
        fila.MostrarCelula();

        x1 = fila.RemoverCelula();
        x2 = fila.RemoverCelula();
        System.out.println("Fila apos duas remocoes (" + x1 + ", " + x2 + " )");
        fila.MostrarCelula();

        fila.InserirCelula(9);
        fila.InserirCelula(11);
        System.out.println("Fila apos duas insercoes (9,11)");

        x1 = fila.RemoverCelula();
        x2 = fila.RemoverCelula();
        x3 = fila.RemoverCelula();
        System.out.println("Fila apos tres remocoes (" + x1 + ", " + x2 + ", " + x3 + " )");
        fila.MostrarCelula();

        fila.InserirCelula(13);
        fila.InserirCelula(15);
        System.out.println("Fila apos duas insercoes (13,15)");

        x1 = fila.RemoverCelula();
        x2 = fila.RemoverCelula();
        System.out.println("Fila apos duas remocoes (" + x1 + ", " + x2 + " )");

        
    }
}
