package Model;

import Services.Get;

public class User{
    private String login,pass,fullName;
    private int userId;
    private int admin;

    public User() {
    }

    public User(int id,String login, String pass, String nome, int admin) {
        this.login = login;
        this.pass = pass;
        this.fullName = nome;
        this.userId = id;
        this.admin = admin;
    }

    public User(boolean monta){
        if(monta){
            System.out.println("Categoria do usuário:");
            System.out.println("1 - ADMIN");
            System.out.println("2 - COMUN");
            System.out.print("Categoria escolhida: ");
            int escolha = Get.inteiro();
            
            if(escolha == 1 || escolha == 2){
                
                if(escolha == 1){
                    this.setAdmin(1);
                }else{
                    this.setAdmin(0);
                }
                
                System.out.print("Digite o login: ");
                this.setLogin(Get.string());
                System.out.print("Senha: ");
                this.setPass(Get.string());
                System.out.print("Nome Completo: ");
                this.setFullName(Get.string());
            }
        }
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }
    

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
