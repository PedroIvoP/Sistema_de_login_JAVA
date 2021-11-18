package Services;

import ConfigDB.ConnMySQL;
import Model.User;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import java.sql.*;
import java.util.ArrayList;

public class UserController {

    public static void cadastrar() {
        User u = new User(true);

        Connection conexao = ConnMySQL.conectar();

        String sql = "INSERT INTO Users (login,pass,fullName,admin)"
                + "VALUES (?,?,?,?)";

        try {
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1, u.getLogin());
            statement.setString(2, u.getPass());
            statement.setString(3, u.getFullName());
            statement.setInt(4, u.getAdmin());

            int linhas = statement.executeUpdate();
            //sempre que for inserir, atualizar, deletar, criar
            //dropar ou alterar o banco de qualquer maneira
            //você precisa rodar excuteUpdate()

            if (linhas > 0) {
                System.out.println("\nUsuário cadastrado com Sucesso!\n");
            } else {
                System.err.println("\nUsuário não cadastrado.\n");
            }

            conexao.close();
        } //catch(MySQLIntegrityConstraintViolationException e){
        //System.out.println("\nOpção inválida, usuário não cadastrado.");
        // } Não esta aceitando, estava indo direto para o SQLException, quando inseria uma opção errada no cadastro de admin e comun
        catch (SQLException throwables) {
            System.out.println("\nOpção inválida, usuário não cadastrado.");
        }
    }

    public static void getAll() {
        String sql = "SELECT * FROM Users";
        Connection conexao = ConnMySQL.conectar();

        try {
            Statement statement = conexao.createStatement();
            ResultSet result = statement.executeQuery(sql);

            System.out.println("------USUARIOS CADASTRADOS------");
            while (result.next()) {
                User u = new User(
                        result.getInt("userid"),
                        result.getString("login"),
                        result.getString(3),//3 refere-se à senha que é o 3º campo
                        result.getString("fullName"),
                        result.getInt("admin")
                );

                System.out.println("ID: " + u.getUserId());
                System.out.println("Nome: " + u.getFullName());
                System.out.println("Login: " + u.getLogin());
                System.out.println("Pass: " + u.getPass());
                if (u.getAdmin() == 1) {
                    System.out.println("Admin: Sim");
                } else {
                    System.out.println("Admin: Nao");
                }
                System.out.println("----------------------------");
            }

            conexao.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //
    public static User login(String usuario, String senha) {
        String sql = "SELECT * FROM Users WHERE login like '" + usuario + "' AND pass = '" + senha + "'";
        Connection conexao = ConnMySQL.conectar();
        User u = null;

        try {
            Statement statement = conexao.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                u = new User(
                        result.getInt("userId"),
                        result.getString("login"),
                        result.getString("pass"),
                        result.getString("fullName"),
                        result.getInt("admin")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return u;
    }

    public static ArrayList<User> getPorLogin(String login) {
        String sql = "SELECT * FROM Users WHERE login like '" + login + "'";
        Connection conexao = ConnMySQL.conectar();
        ArrayList<User> lista = null;

        try {
            Statement statement = conexao.createStatement();
            ResultSet result = statement.executeQuery(sql);
            lista = new ArrayList<>();
            while (result.next()) {
                /* lista.add(new User(
                        result.getInt("userid"),
                        result.getString("login"),
                        result.getString(3),//3 refere-se à senha que é o 3º campo
                        result.getString("fullName")),
                        result.getInt("admin")
                );*/

                User u = new User(
                        result.getInt("userId"),
                        result.getString("login"),
                        result.getString("pass"),
                        result.getString("fullName"),
                        result.getInt("admin")
                );

                lista.add(u);
            }
            conexao.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lista;
    }

    public static boolean excluir(String user) {
        ArrayList<User> lista = UserController.getPorLogin(user);

        if (lista.size() == 0) {
            System.out.println("\nNão existem usuários com esse login\n");
            return false;
        } else {
            System.out.println("\n\nConfirme os usuários");
            System.out.println("----------------------------");
            int id = 0;
            ArrayList<Integer> ids = new ArrayList<Integer>();

            for (User u : lista) {
                System.out.println("ID: " + u.getUserId());
                System.out.println("Nome: " + u.getFullName());
                System.out.println("Login: " + u.getLogin());
                System.out.println("Senha: " + u.getPass());
                if (u.getAdmin() == 1) {
                    System.out.println("Admin: Sim");
                } else {
                    System.out.println("Admin: Nao");
                }
                System.out.println("------------------------------------------");
                ids.add(u.getUserId());
            }
            System.out.print("\nQual ID à ser excluído: nº ");
            id = Get.inteiro();

            if (ids.indexOf(id) >= 0) {
                try {
                    Connection conexao = ConnMySQL.conectar();

                    String sql = "DELETE FROM Users "
                            + "WHERE userId = ?";

                    PreparedStatement statement = conexao.prepareStatement(sql);
                    statement.setInt(1, id);

                    int linhas = statement.executeUpdate();
                    //sempre que for inserir, atualizar, deletar, criar
                    //dropar ou alterar o banco de qualquer maneira
                    //você precisa rodar excuteUpdate()

                    if (linhas > 0) {
                        System.out.println("\nUsuário excluído com Sucesso!\n");
                    } else {
                        System.err.println("\nUsuário não excluído.\n");
                        return false;
                    }
                    conexao.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            } else {
                System.out.println("\nId Inválido\n");
            }
        }
        return true;
    }
//POSSIBILITE A TROCA DE SENHAS

    public static boolean alterar(String user) {
        ArrayList<User> lista = UserController.getPorLogin(user);

        if (lista.size() == 0) {
            System.out.println("\nNão existem usuários com esse login\n");
            return false;
        } else {
            System.out.println("\n\nConfirme os usuários");
            System.out.println("----------------------------");
            int id = 0;
            ArrayList<Integer> ids = new ArrayList<Integer>();

            int i = 1;
            for (User u : lista) {
                System.out.println("ID: " + i);
                System.out.println("Nome: " + u.getFullName());
                System.out.println("Login: " + u.getLogin());
                System.out.println("Senha: " + u.getPass());
                if (u.getAdmin() == 1) {
                    System.out.println("Admin: Sim");
                } else {
                    System.out.println("Admin: Nao");
                }
                System.out.println("------------------------------------------");
                ids.add(u.getUserId());

                i++;
            }

            try {

                System.out.print("\nInforme a nova senha: ");
                String novaSenha = Get.string();

                if (lista.get(id - 1).getPass().equals(novaSenha)) {

                    System.out.println("\nSenha não alterada! A senha é a mesma que a anterior!");
                    return false;

                } else {

                    System.out.print("\nConfirme a nova senha: ");
                    String novaSenha2 = Get.string();

                    if (!(novaSenha.equals(novaSenha2))) {

                        System.out.println("\nSenha não alterada! A senha deve ser a mesma digitada anteriormente!");
                        return false;
                    } else {

                        Connection conexao = ConnMySQL.conectar();

                        String sql = "UPDATE Users SET pass = ?"
                                + "WHERE userId = ?";

                        PreparedStatement statement = conexao.prepareStatement(sql);
                        statement.setString(1, novaSenha);
                        statement.setInt(2, lista.get(id - 1).getUserId());

                        int linhas = statement.executeUpdate();
                        //sempre que for inserir, atualizar, deletar, criar
                        //dropar ou alterar o banco de qualquer maneira
                        //você precisa rodar excuteUpdate()

                        if (linhas > 0) {
                            System.out.println("\nSenha alterada com Sucesso!\n");
                        } else {
                            System.err.println("\nNenhuma senha alterada.\n");
                            return false;
                        }
                        conexao.close();
                    }
                }

            } catch (IndexOutOfBoundsException e) {
                System.out.println("\nId inválido, senha não alterada");
                return false;

            } catch (SQLException e) {
                System.out.println(e);
                return false;
            }

        }
        return true;
    }

    public static boolean alterarComun(User u) {

        try {

            System.out.print("\nInforme a nova senha: ");
            String novaSenha = Get.string();

            if (u.getPass().equals(novaSenha)) {

                System.out.println("\nSenha não alterada! A senha é a mesma que a anterior!");
                return false;

            } else {

                System.out.print("\nConfirme a nova senha: ");
                String novaSenha2 = Get.string();

                if (!(novaSenha.equals(novaSenha2))) {

                    System.out.println("\nSenha não alterada! A senha deve ser a mesma digitada anteriormente!");
                    return false;
                } else {

                    Connection conexao = ConnMySQL.conectar();

                    String sql = "UPDATE Users SET pass = ?"
                            + "WHERE userId = ?";

                    PreparedStatement statement = conexao.prepareStatement(sql);
                    statement.setString(1, novaSenha);
                    statement.setInt(2, u.getUserId());

                    int linhas = statement.executeUpdate();
                    //sempre que for inserir, atualizar, deletar, criar
                    //dropar ou alterar o banco de qualquer maneira
                    //você precisa rodar excuteUpdate()

                    if (linhas > 0) {
                        //System.out.println("\nSenha alterada com Sucesso!\n");
                    } else {
                        System.err.println("\nNenhuma senha alterada.\n");
                        return false;
                    }
                    conexao.close();
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        
        return true;
    }

}


        
