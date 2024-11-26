const apiUrl = 'http://localhost:8080/api'; // URL base da API

// Selecionando os elementos da página
const modal = document.querySelector('.modal-container'); // Modal container
const sNome = document.querySelector('#m-nome'); // Nome do campo
const sTelefone = document.querySelector('#m-Telefone'); // Telefone
const sEmail = document.querySelector('#m-Email'); // Email
const sImagem = document.querySelector('#m-selectedFile'); // Imagem do aluno
const btnSalvar = document.querySelector('#btnSalvar'); // Botão de salvar
let id = null; // Inicializa o id com null

// Função para abrir a modal, se for para editar, preenche os campos
function openModal(edit = false, alunoId = null) {
  modal.classList.add('active'); // Adiciona a classe "active" para mostrar a modal
  
  if (edit && alunoId !== null) {
    fetch(`${apiUrl}/${alunoId}`)
      .then(response => response.json())
      .then(aluno => {
        sNome.value = aluno.nome;
        sTelefone.value = aluno.telefone;
        sEmail.value = aluno.email;
        id = aluno.id; // Define o id do aluno
      })
      .catch(error => console.error('Erro ao buscar aluno:', error));
  } else {
    // Limpa os campos se for para adicionar um novo aluno
    sNome.value = '';
    sTelefone.value = '';
    sEmail.value = '';
    sImagem.value = ''; // Limpa o campo de imagem
    id = null; // Reseta o id para null
  }
}

// Função para fechar a modal
function closeModal() {
  modal.classList.remove('active');
}

// Fechar modal com o botão de fechar
document.querySelector('.btn-fechar')?.addEventListener('click', () => {
  closeModal();
});

// Função para salvar ou editar o aluno
btnSalvar.addEventListener('click', (e) => {
  e.preventDefault(); // Evita o recarregamento da página
  
  // Verifica se os campos estão vazios
  if (sNome.value.trim() === '' || sTelefone.value.trim() === '' || sEmail.value.trim() === '') {
    alert('Todos os campos são obrigatórios!');
    return;
  }

  const aluno = {
    nome: sNome.value,
    telefone: sTelefone.value,
    email: sEmail.value,
  };

  const formData = new FormData();
  formData.append('nome', aluno.nome);
  formData.append('telefone', aluno.telefone);
  formData.append('email', aluno.email);
  formData.append('imagem', sImagem.files[0]); // Adiciona a imagem ao FormData

  if (id !== null) {
    // Atualiza o aluno existente
    fetch(`${apiUrl}/${id}`, {
      method: 'PUT',
      body: formData,
    })
      .then(response => response.json())
      .then(data => {
        alert('Aluno atualizado com sucesso');
        loadItens(); // Recarrega a lista de alunos
        closeModal(); // Fecha a modal
      })
      .catch(error => {
        console.error('Erro ao atualizar aluno:', error);
        alert('Erro ao atualizar aluno');
      });
  } else {
    // Cria um novo aluno
    fetch(apiUrl, {
      method: 'POST',
      body: formData,
    })
      .then(response => response.json())
      .then(data => {
        alert('Aluno criado com sucesso');
        loadItens(); // Recarrega a lista de alunos
        closeModal(); // Fecha a modal
      })
      .catch(error => {
        console.error('Erro ao criar aluno:', error);
        alert('Erro ao criar aluno');
      });
  }
});

// Função para carregar e exibir os alunos na tabela
function loadItens() {
  fetch(apiUrl)
    .then(response => response.json())
    .then(alunos => {
      const tableBody = document.querySelector('tbody');
      tableBody.innerHTML = ''; // Limpa a tabela

      alunos.forEach(aluno => {
        const row = document.createElement('tr');
        row.innerHTML = `
          <td><img src="${aluno.imagem || 'default.jpg'}" alt="Imagem de ${aluno.nome}" class="img-table"></td>
          <td>${aluno.nome}</td>
          <td>${aluno.telefone}</td>
          <td>${aluno.email}</td>
          <td class="acao">
            <button onclick="openModal(true, ${aluno.id})">Editar</button>
          </td>
          <td class="acao">
            <button onclick="deleteItem(${aluno.id})">Excluir</button>
          </td>
        `;
        tableBody.appendChild(row);
      });
    })
    .catch(error => {
      console.error('Erro ao carregar alunos:', error);
    });
}

// Função para excluir um aluno
function deleteItem(alunoId) {
  if (confirm('Tem certeza que deseja excluir este aluno?')) {
    fetch(`${apiUrl}/${alunoId}`, {
      method: 'DELETE'
    })
      .then(() => {
        alert('Aluno excluído com sucesso');
        loadItens(); // Atualiza a tabela de alunos
      })
      .catch(error => {
        console.error('Erro ao excluir aluno:', error);
        alert('Erro ao excluir aluno');
      });
}

// Carrega os alunos ao carregar a página
loadItens();}
