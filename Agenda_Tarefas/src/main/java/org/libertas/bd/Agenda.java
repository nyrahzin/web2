package org.libertas.bd;

public class Agenda {
	private int id;
	private String tarefa;
	private String data_tarefa;
	private String status_tarefa;
	private String squad_tarefa;
	private String descricao;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTarefa() {
		return tarefa;
	}
	public void setTarefa(String tarefa) {
		this.tarefa = tarefa;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescrição(String descricao) {
		this.descricao = descricao;
	}
	public String getData_tarefa() {
		return data_tarefa;
	}
	public void setData_tarefa(String data_tarefa) {
		this.data_tarefa = data_tarefa;
	}
	public String getStatus_tarefa() {
		return status_tarefa;
	}
	public void setStatus_tarefa(String status_tarefa) {
		this.status_tarefa = status_tarefa;
	}
	public String getSquad_tarefa() {
		return squad_tarefa;
	}
	public void setSquad_tarefa(String squad_tarefa) {
		this.squad_tarefa = squad_tarefa;
	}

	
	

}
