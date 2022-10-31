/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Back;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author andre_lima
 */
public class CtrCadastrarConsulta {
    private static Connection  c;
    public CtrCadastrarConsulta(){
        c = Conexao.getConnection();
    }
    
    public static boolean inserirConsultaNaTabela(String dia, int mes, String ano, String crm, String cpf){
        new CtrCadastrarConsulta();
        Statement st;
            try {
               st = c.createStatement();
               String sql =  "insert into hospital.consulta (dataRealizacao, cpfPaciente , medicoCRM ) values ( \'"+ ano + "-"+ mes +"-"+ dia +"\'," + cpf + "," + crm +");";
               System.out.println(sql);
               st.execute(sql);
               return true;
            } catch (SQLException ex) {
                return false;
        }
    }
    public static ArrayList<PerfilConsulta> exibirConsultasNaTabela(){
        new CtrCadastrarConsulta();
        Statement st;
            try {
                st = c.createStatement();
                String sql =  "select * from hospital.consulta;";
                ResultSet dadosConsulta = st.executeQuery(sql);
                ArrayList<PerfilConsulta> listaConsulta = new ArrayList<PerfilConsulta>();
                while(dadosConsulta.next()){
                    PerfilConsulta pc = new PerfilConsulta();
                    pc.cpfPaciente = dadosConsulta.getString("cpfPaciente");
                    pc.idConsulta = dadosConsulta.getString("idConsulta");
                    pc.dataRealizacao = dadosConsulta.getString("dataRealizacao");
                    pc.medicoCRM = dadosConsulta.getString("medicoCRM");
                    listaConsulta.add(pc);
                    
                }
                System.out.println(listaConsulta.size());
                for(int i = 0; i< listaConsulta.size();i++){
                    System.out.println(listaConsulta.get(i).cpfPaciente);
                }
                
                return listaConsulta;
            } catch (SQLException ex) {
              
                return null;
            }
        
    }
}
