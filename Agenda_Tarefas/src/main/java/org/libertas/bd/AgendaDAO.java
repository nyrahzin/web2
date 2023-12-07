package org.libertas.bd;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;


public class AgendaDAO {
	public void inserir(Agenda a) {
		Conexao con = new Conexao();
		try {
			String sql = "INSERT INTO agenda"
					+ " (tarefa, descricao, data_tarefa, status_tarefa, squad_tarefa ) VALUES (?, ?, ?, ?, ?) ";
			PreparedStatement prep = con.getConexao().prepareStatement(sql);
			prep.setString(1, a.getTarefa());
			prep.setString(2, a.getDescricao());
			prep.setString(3, a.getData_tarefa());
			prep.setString(4, a.getStatus_tarefa());
			prep.setString(5, a.getSquad_tarefa());
			prep.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.desconectar();
	}
	public void alterar(Agenda a) {
		Conexao con = new Conexao();
		try {
			String sql = "UPDATE agenda"
					+ " SET tarefa = ?, descricao = ?, data_tarefa = ?, status_tarefa = ?, squad_tarefa = ?  "
					+ " WHERE id = ? ";
			PreparedStatement prep = con.getConexao().prepareStatement(sql);
			prep.setString(1, a.getTarefa());
			prep.setString(2, a.getDescricao());
			prep.setString(3, a.getData_tarefa());
			prep.setString(4, a.getStatus_tarefa());
			prep.setString(5, a.getSquad_tarefa());
			prep.setInt(6, a.getId());
			prep.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.desconectar();
		
	}
	
	public void excluir(Agenda a) {
		Conexao con = new Conexao();
		try {
			String sql = " DELETE FROM agenda "
					+ " WHERE id = ? ";
			PreparedStatement prep = con.getConexao().prepareStatement(sql);
			prep.setInt(1, a.getId());
			
			prep.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.desconectar();
		
	}

	public Agenda consultar(int id) {

		Agenda a = new Agenda();
		Conexao con = new Conexao();
		try {
			String sql = "SELECT * FROM agenda WHERE id = " + id;
			Statement sta = con.getConexao().createStatement();
			ResultSet res = sta.executeQuery(sql);
			while (res.next()) {
				a.setId(res.getInt("id"));
				a.setTarefa(res.getString("tarefa"));
				a.setDescrição(res.getString("descricao"));
				a.setData_tarefa(res.getString("data_tarefa"));
				a.setStatus_tarefa(res.getString("status_tarefa"));
				a.setSquad_tarefa(res.getString("squad_tarefa"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.desconectar();
		return a;
	}
		
	public List<Agenda> listar(){
		List<Agenda> lista = new LinkedList<Agenda>();
			Conexao con = new Conexao();
		
		try {
			String sql = "SELECT * FROM agenda ORDER BY id";
			Statement sta = con.getConexao().createStatement();
			ResultSet res = sta.executeQuery(sql);
			while (res.next()) {
				Agenda a = new Agenda();
				a.setId(res.getInt("id"));
				a.setTarefa(res.getString("tarefa"));
				a.setDescrição(res.getString("descricao"));
				a.setData_tarefa(res.getString("data_tarefa"));
				a.setStatus_tarefa(res.getString("status_tarefa"));
				a.setSquad_tarefa(res.getString("squad_tarefa"));
				
				lista.add(a);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.desconectar();
		return lista;
	}
}