package banco;

import java.sql.Connection;

public class TestaConexao {

	public static void main(String[] args) {
		ConexaoMysql conexao = ConexaoMysql.getInstancia();
        Connection con = conexao.conecta();

        if (con != null) {
            System.out.println("Conexão realizada com sucesso:\n" + con);
            conexao.fechaConexao(con);
        } else {
            System.out.println("Nãoo foi possivel realizar a conexão com o banco de dados.");
        }

	}

}
