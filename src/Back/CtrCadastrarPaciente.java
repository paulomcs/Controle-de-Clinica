package Back;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author andre_lima
 */
public class CtrCadastrarPaciente {
        private static Connection  c;

    public CtrCadastrarPaciente(){
        c = Conexao.getConnection();
    }



    //public static void exibirTabelaCompleta() throws SQLException { //exibe no console a tabela banco.perfil completa
    //    Statement st = c.createStatement();
    //    String sql = "select * from banco.perfil";
    //    
    //    ResultSet rs = st.executeQuery(sql);
    //    System.out.println("___Exibindo_Tabela___");
    //    System.out.println("|  id  |    valor   |");
    //    while (rs.next()){
    //        System.out.format("| %4s | %10s |\n", rs.getString(1), rs.getString(2));
    //    }
    //    System.out.println("_____________________");
    //}

    public static boolean inserirPacienteNaTabela(String cpf, String nome, String telefone){  //insere um valor na tabela
        new CtrCadastrarPaciente();
        Statement st;
            try {
                st = c.createStatement();
                String sql = "insert into hospital.paciente (cpf, nome, telefone) values (\'" + cpf + "\',\'" + nome + "\',\'" + telefone + "\')";
                st.execute(sql);
                return true;
            } catch (SQLException ex) {
                return false;
            }


    }
    
    public static PerfilPaciente verificarPacienteNaTabela(String cpf){
        new CtrCadastrarPaciente();
        PerfilPaciente pp = new PerfilPaciente();
        
        Statement st;
            try{
                st = c.createStatement();
                String sql = "select paciente.nome, paciente.cpf, paciente.telefone from hospital.paciente where paciente.cpf = \'"+ cpf+"\';";
                System.out.println(sql);
                ResultSet dadosPaciente = st.executeQuery(sql);
//                System.out.println(dadosPaciente.toString());
                dadosPaciente.next();
                pp.nome = dadosPaciente.getString("nome");
                pp.telefone = dadosPaciente.getString("telefone");
                pp.cpf = dadosPaciente.getString("cpf");
                
//                System.out.println(pp.nome);
//                System.out.println(pp.telefone);
//                System.out.println(pp.cpf);
                return pp;
            }catch (SQLException ex) {
                return null;
            }
            
    }

    //public static void atualizarValorNaTabela(int id, int valor) throws SQLException {  //atualiza um valor na tabela
    //    Statement st = c.createStatement();
    //    String sql = "update banco.perfil set valor = " + valor + "where id = " + id;
    //    st.execute(sql);
    //}



    public static void main(String[] args) throws SQLException {

    }
}