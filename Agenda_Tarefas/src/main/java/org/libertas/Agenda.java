package org.libertas;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.libertas.bd.AgendaDAO;

import com.google.gson.Gson;


public class Agenda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Agenda() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		//out.println("Executando método GET");
		AgendaDAO adao = new AgendaDAO();
		List<org.libertas.bd.Agenda> lista = adao.listar();
		Gson gson = new Gson();
		out.print(gson.toJson(lista));
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		try {
		//PEGA O BOBY DA REQUISIÇÃO CONTENDO OS DADOS PARA SER INSERIDO 
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) !=null) {
			sb.append(line);
		}
			String boby = sb.toString();
			
			//TRANSFORMA O PARAÊMETRO DO BODY EM OBJETO JSON
			Gson gson = new Gson();
			org.libertas.bd.Agenda a = gson.fromJson(boby, org.libertas.bd.Agenda.class);
			
			//INSERE OBJETO NO BANCO DE DADOS
			AgendaDAO pdao = new AgendaDAO();
			pdao.inserir(a);
			
			Retorno r = new Retorno(true, "registro inserido com sucesso");
			out.print(gson.toJson(r));
			
	 } catch (Exception e) {
		 e.printStackTrace();
		 Gson gson = new Gson();
		 Retorno r = new Retorno(false, e.getMessage());
			out.print(gson.toJson(r));
		}
}
		
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			//PEGA O BOBY DA REQUISIÇÃO CONTENDO OS DADOS PARA SER INSERIDO 
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) !=null) {
				sb.append(line);
			}
				String boby = sb.toString();
				
				//TRANSFORMA O PARAÊMETRO DO BODY EM OBJETO JSON
				Gson gson = new Gson();
				org.libertas.bd.Agenda a = gson.fromJson(boby, org.libertas.bd.Agenda.class);
				
				//INSERE OBJETO NO BANCO DE DADOS
				AgendaDAO pdao = new AgendaDAO();
				pdao.alterar(a);
				
				Retorno r = new Retorno(true, "REGISTRO ALTERADO COM SUCESSO");
				out.print(gson.toJson(r));
				
		 } catch (Exception e) {
			 e.printStackTrace();
			 Gson gson = new Gson();
			 Retorno r = new Retorno(false, e.getMessage());
			 out.print(gson.toJson(r));out.print(e.getMessage());
			}
	}
	
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
	    try {
	        // Pega o id passando por parâmetro
	        String idParam = request.getParameter("id");

	        if (idParam == null || idParam.isEmpty()) {
	            // Se o parâmetro não estiver presente, tenta obter da URI
	            String id = request.getRequestURI();
	            id = id.substring(id.lastIndexOf("/") + 1);
	            idParam = id;
	        }

	        // Converte o ID para inteiro
	        int id = Integer.parseInt(idParam);

	        AgendaDAO adao = new AgendaDAO();
	        org.libertas.bd.Agenda a = new org.libertas.bd.Agenda();
	        a.setId(id);
	        adao.excluir(a);

	        Retorno r = new Retorno(true, "registro excluído com sucesso");
	        Gson gson = new Gson();
	        out.print(gson.toJson(r));
	    } catch (Exception e) {
	        e.printStackTrace();
	        Gson gson = new Gson();
	        Retorno r = new Retorno(false, e.getMessage());
	        out.print(gson.toJson(r));
	    }
	}

		
		
		
		
}


