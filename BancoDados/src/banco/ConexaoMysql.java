package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoMysql {
	private String url;     // Parametros utilizados para conexao.
    private Connection con; // Identificador de conexao.
    private String usuario; // Usuario do banco de dados.
    private String senha;   // Senha do usuario do banco de dados
    private static ConexaoMysql instancia; // Instancia da conexao

    // Construtor
    // Assim que um objeto conexao e instanciado, 
    // o driver para conexao com o MySQL e carregado.
    public ConexaoMysql() {
    	// String de conexao
        this.url = "jdbc:mysql://localhost:3306";
        this.usuario = "";   // Informe o usuario e senha
        this.senha = ""; // do seu banco de dados

        // Carrega o driver para conexao com banco
        try {
            //conexao com o driver jdbc do mysql
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Realiza a conexao com o banco
        try {
            this.con = DriverManager.getConnection(this.url ="jdbc:mysql://localhost:3306/liguagem?useSSL=false&serverTimezone=UTC",this.usuario = "bruno", this.senha = "2015Apis92");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static ConexaoMysql getInstancia() {
        if (instancia == null) {
            instancia = new ConexaoMysql();
        }
        return instancia;
    }

    // Metodo responsavel por realizar a conexao
    public Connection conecta() {
        return this.con;
    }
    
    /*
	 * Metodo fecha conexao: Responsavel por encerrar uma conexao
	 * - Encerra a conexao
	 * - Encerra o statement: Instrucao SQL
	 * - Encerra o ResultSet: Retorno de um select
     */
    public void fechaConexao(Connection con) {
        fechaConexao(con, null, null);
    }

    public void fechaConexao(Connection con, Statement stmt) {
        fechaConexao(con, stmt, null);
    }

    public void fechaConexao(Connection con, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }

            if (stmt != null) {
                stmt.close();
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}