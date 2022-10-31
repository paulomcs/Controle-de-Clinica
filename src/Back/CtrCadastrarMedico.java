package Back;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author andre_lima
 */
public class CtrCadastrarMedico {
    private static Connection  c;
    public CtrCadastrarMedico(){
        c = Conexao.getConnection();
    }
    
    public static boolean inserirMedicoNaTabela( String nome, String cpf, String crm, String telefone, String especialidade){
         new CtrCadastrarMedico();
         Statement st;
            try {
                st = c.createStatement();
                String sql =  "begin; insert into hospital.medico (nome, cpf , numCRM , telefone) values ( \'"+ nome + "\'," + cpf + "," + crm + "," + telefone + ");  insert into hospital.medico_especialidade (idRegistroMedico, nomeEspecialidade) values (" + crm + ",\'" + especialidade +"\');commit;";
                st.execute(sql);
                return true;
            } catch (SQLException ex) {
                return false;
            }
    }
    
    public static ArrayList<PerfilMedico> exibirMedicoNaTabela(){
        new CtrCadastrarMedico();
        Statement st;
            try {
                st = c.createStatement();
                String sql =  "select * from hospital.medico;";
                ResultSet dadosMedico = st.executeQuery(sql);
                ArrayList<PerfilMedico> listaMedicos = new ArrayList<PerfilMedico>();
                while(dadosMedico.next()){
                    PerfilMedico pm = new PerfilMedico();
                    pm.nome = dadosMedico.getString("nome");
                    pm.cpf = dadosMedico.getString("cpf");
                    pm.crm = dadosMedico.getString("numCRM");
                    pm.telefone = dadosMedico.getString("telefone");
                    listaMedicos.add(pm);
                    
                }
                System.out.println(listaMedicos.size());
                for(int i = 0; i< listaMedicos.size();i++){
                    System.out.println(listaMedicos.get(i).nome);
                }
                
                return listaMedicos;
            } catch (SQLException ex) {
              
                return null;
            }
        
    }
    
}
