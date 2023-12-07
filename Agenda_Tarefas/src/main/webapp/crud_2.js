var valores = [];
var idpessoa = 0;

function novo(){
    var form = document.getElementById("formulario");
    var lista = document.getElementById("lista");

    //mostra o formulário 
    form.style.display = "block";
    //esconde lista 
    lista.style.display = "none";

    //lista os inputs
    id = 0;
    var tarefa = document.getElementById("tarefa");
    var descricao = document.getElementById("descricao");
    var data_tarefa = document.getElementById("data_tarefa");
    var status_tarefa = document.getElementById("status_tarefa");
    var squad_tarefa = document.getElementById("squad_tarefa");
    tarefa.value = "";
    descricao.value = "";
    data_tarefa.value = "";
    status_tarefa.value = "";
    squad_tarefa.value = "";
    
    //joga o foco no 1º campo:
    tarefa.focus();
}

function alterar(i){
    var form = document.getElementById("formulario");
    var lista = document.getElementById("lista");

    //mostra o formulário 
    form.style.display = "block";
    //esconde lista 
    lista.style.display = "none";

    //lista os inputs
    id = valores[i].id;
    var tarefa = document.getElementById("tarefa");
    var descricao = document.getElementById("descricao");
    var data_tarefa = document.getElementById("data_tarefa");
    var status_tarefa = document.getElementById("status_tarefa");
    var squad_tarefa = document.getElementById("squad_tarefa");
    tarefa.value = valores[i].tarefa;
    descricao.value = valores[i].descricao;
    data_tarefa.value = valores[i].data_tarefa;
    status_tarefa.value = valores[i].status_tarefa;
  	squad_tarefa.value = valores[i].squad_tarefa;
    
    //joga o foco no 1º campo:
    tarefa.focus();
}

function salvar(){
	//valida campos obrigarotios
	if(document.getElementById("tarefa").value  == ""){
		alert("campo Tarefa é obrigaratório!");
		return;
	}
	
    //pega os dados digitados pelo usuário e monta um objeto
    var a  = {
		id: id,
		tarefa:  document.getElementById("tarefa").value,
		descricao: document.getElementById("descricao").value,
		data_tarefa: document.getElementById("data_tarefa").value,
		status_tarefa: document.getElementById("status_tarefa").value,
		squad_tarefa: document.getElementById("squad_tarefa").value
	};
   
   	//define se o método sera para inserir ou alterar
   	if (id == 0) {
		   metodo = "POST";
	   } else {
		   metodo = "PUT";
	   }
   
	//envia os dados para o servidor
	mostraLoading("aguarde, salvando dados...")
	fetch("http://localhost:8080/Agenda_Tarefas/Agenda",
		{method: metodo,
	    body: JSON.stringify(a)
		}
	).then(resp => resp.json())
	.then(function (Retorno){
		escondeLoading();
		alert(Retorno.mensagem);
		
		var form = document.getElementById("formulario");
    	var lista = document.getElementById("lista");

    	//escondeo o formulário 
    	form.style.display = "tarefa";
    	//mostra a lista 
    	lista.style.display = "block";
    	
    	// recarrega lista
    	listar();
    	
	});
    
}
function excluir(i) {
  id = valores[i].id;

  // Envia os dados para o servidor
  mostraLoading("Aguarde, excluindo...");
  fetch("http://localhost:8080/Agenda_Tarefas/agenda/" + id, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((resp) => resp.json())
    .then(function (retorno) {
      escondeLoading();
      alert(retorno.mensagem);

      var form = document.getElementById("formulario");
      var lista = document.getElementById("lista");

      // Esconde o formulário
      form.style.display = "none";
      // Mostra a lista
      lista.style.display = "block";

      // Recarrega a lista
      listar();
    });
}

function cancelar(){
    var form = document.getElementById("formulario");
    var lista = document.getElementById("lista");

    //escondeo o formulário 
    form.style.display = "none";
    //mostra a lista 
    lista.style.display = "block";
}

function listar(){
	var lista = document.getElementById("dados");
    //limpa a lista
    lista.innerHTML = "<tr><td colspan>aguarde, carregando...</td></tr>";
	
    fetch ("http://localhost:8080/Agenda_Tarefas/Agenda")
    .then(resp => resp.json())
    .then(dados => mostrar(dados));
}

function mostrar(dados){
	valores = dados;
    var lista = document.getElementById("dados");
    //limpa a lista
    lista.innerHTML ="";
    //percoorre a lista 
    for (var i in dados){
        lista.innerHTML += "<tr>"
                        + "<td>" + dados[i].id + "</td>"
                        + "<td>" + dados[i].tarefa + "</td>"
                        + "<td>" + dados[i].descricao + "</td>"
                        + "<td>" + dados[i].data_tarefa + "</td>"
                        + "<td>" + dados[i].status_tarefa + "</td>"
                        + "<td>" + dados[i].squad_tarefa + "</td>"
                        + "<td> <input type='button' value='A' onclick='alterar("+i+")'/></td>"
                        + "<td> <input type='button' value='X' onclick='excluir("+i+")'/>"
                        +"</td>"
                        + "</tr>";
                        
    }
}

function mostraLoading(msg){
	var loa = document.getElementById("loading");
    var con = document.getElementById("conteudo");
    loa.style.display = "block";
	con.style.display = "none";
	loa.innerHTML = msg;
}

function escondeLoading(){
	var loa = document.getElementById("loading");
    var con = document.getElementById("conteudo");
    loa.style.display = "none";
	con.style.display = "block";
	
}
listar();/**
 * 
 * 
 */