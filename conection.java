import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conection {
    public static void main(String[] args) throws SQLException {
// Defina as informações de conexão
        String url = "jdbc:mysql://localhost:3306"; // URL de conexão com o
        String user = "root"; // Nome do usuário
        String password = null; // Senha do banco de dados
// Conectar ao banco de dados
        try (Connection conexao = DriverManager.getConnection(url, user, null))
        {

            System.out.println("Conexão realizada com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
