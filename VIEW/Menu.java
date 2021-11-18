package VIEW;

import Model.User;
import Services.Get;
import Services.UserController;

public class Menu {

    public static void login(){
        while(0==0){
            System.out.println("========LOGIN==========");
            System.out.print("User: ");
            String user = Get.string();
            System.out.print("Password: ");
            String pass = Get.string();

            User u = UserController.login(user,pass);

            if(u == null){
                System.err.println("\nUsuário ou senha inválidos!\n");
            }else{
                
                inicio(u);
            }
        }
    }

    public static void inicio(User u){
        
        if(u.getAdmin() == 1){

                while(0==0){
                System.out.println("\n\n\n========SISTEMA=============");
                System.out.println("(0)SAIR");
                System.out.println("(1)Cadastrar");
                System.out.println("(2)Consultar");
                System.out.println("(3)Excluir");
                System.out.println("(4)Alterar senha");
                System.out.print("Escoha uma opção: nº ");
                int op = Get.inteiro();

                switch (op){
                    default: System.out.println("\nOpção Inválida\n");
                    case 0: System.out.println("\n Saindo do sistema..."); System.exit(0);
                    case 1: cadastrar(); break;
                    case 2: consultar(); break;
                    case 3: excluir(); break;
                    case 4: alterarSenha(); break;
                }
            }
        }else{
            
            while(0==0){
                System.out.println("\n\n\n========SISTEMA=============");
                System.out.println("(0)SAIR");
                System.out.println("(1)Alterar usuário");
                System.out.print("Escoha uma opção: nº ");
                int op = Get.inteiro();

                switch (op){
                    default: System.out.println("\nOpção Inválida\n");
                    case 0: System.out.println("\n Saindo do sistema..."); System.exit(0);
                    case 1: alterarSenhaComun(u); break;
                }
            }
        }
        
    }

    public static void cadastrar(){
        System.out.println("==========Cadastrar=============");
        System.out.println("(1)Usuários");

        System.out.print("Escolha uma opção: nº ");
        int op = Get.inteiro();

        switch (op){
            default:
                System.out.println("\nOpção inválida!");
                break;
            case 1:
                System.out.println("\nCADASTRAR USUÁRIO\n");
                UserController.cadastrar();
                break;
        }
    }

    public static void consultar(){
        System.out.println("==========Consultar=============");
        System.out.println("(1)Usuários");

        System.out.print("Escolha uma opção: nº ");
        int op = Get.inteiro();

        switch (op) {
            default:
                System.out.println("\nOpção inválida!");
                break;
            case 1:
                System.out.println("=====CADASTRO COMPLETO DE USUÁRIOS=====");
                UserController.getAll();
                System.out.println("=======================================");
                break;
        }
    }

    public static void excluir(){
        System.out.println("==========EXCLUIR=============");
        System.out.println("(1)Usuários");

        System.out.print("Escolha uma opção: nº ");
        int op = Get.inteiro();

        switch (op) {
            default:
                System.out.println("\nOpção inválida!");
                break;
            case 1:
                System.out.println("==Exlcuir Usuários==");
                System.out.print("Informe o LOGIN do usuário: ");

                if(UserController.excluir(Get.string())){
                    System.out.println("EXCLUSÃO COM SUCESSO");
                }else{
                    System.err.println("Falha na exclusão");
                }
                break;
        }
    }
    
    public static void alterarSenha(){
        System.out.println("==========ALTERAR SENHA=============");
        System.out.println("(1)Usuários");

        System.out.print("Escolha uma opção: nº ");
        int op = Get.inteiro();

        switch (op) {
            default:
                System.out.println("\nOpção inválida!");
                break;
            case 1:
                System.out.println("==Alterar Senha==");
                System.out.print("Informe o LOGIN do usuário: ");

                if(UserController.alterar(Get.string())){
                    System.out.println("ALTERADA COM SUCESSO");
                }else{
                    System.err.println("Falha ao alterar senha");
                }
                break;
        }
    }
   
    public static void alterarSenhaComun(User u){
    
        System.out.println("    --(1)TROCAR SENHA");
    
        System.out.print("Escolha uma opção: nº ");
        int op = Get.inteiro();

        switch (op) {
            default:
                System.out.println("\nOpção inválida!");
                break;
            case 1:

                if(UserController.alterarComun(u)){
                    System.out.println("ALTERADA COM SUCESSO");
                }else{
                    System.err.println("Falha ao alterar senha");
                }
                break;
        }
    }
}
