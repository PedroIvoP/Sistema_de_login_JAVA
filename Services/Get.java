package Services;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Get {
    public static int inteiro(){
        
        int valor = 0;

        while(0==0){
            try{
                Scanner get = new Scanner(System.in);
                
                valor = get.nextInt();
                break;
            }catch (InputMismatchException e){
                System.err.println("\nApenas números inteiros");
                System.out.println("NÃO USE CASAS DECIMAIS");
                System.out.println("Ex: 451278521");
                System.out.print("Tente novamente: ");
            }
        }
        return valor;
    }

    public static String string (){
        Scanner get = new Scanner(System.in);
        return get.nextLine();
    }
}
