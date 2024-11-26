const modal = document.querySelector('.modal-container')
const tbody = document.querySelector('tbody')
const sNome = document.querySelector('#m-Nome')
const selectedFile = document.querySelector('#m-selectedFile')
const sEmail = document.querySelector('#m-Email')
const sTelefone = document.querySelector('#m-Telefone')
const btnSalvar = document.querySelector('#btnSalvar')

let selectedImageUrl = "";  // Variável para armazenar a URL da imagem selecionada
let editingId = null;  // Usado para verificar se estamos editando ou criando um aluno

const API_URL = "http://localhost:8080/api";

// Abre o modal para edição ou criação
function openModal(edit = false, index = 0) {
  modal.classList.add("active");

  modal.onclick = (e) => {
    if (e.target.className.indexOf("modal-container") !== -1) {
      modal.classList.remove("active");
    }
  };

  if (edit) {
    const aluno = aluno[index];
    sNome.value = aluno.nome;
    sEmail.value = aluno.email;
    sTelefone.value = aluno.telefone;
    selectedFile.value = aluno.imagemUrl || "";
  } else {
    sNome.value = "";
    sEmail.value = "";
    sTelefone.value = "";
    selectedFile.value = "";
  }
}

// Função para selecionar uma imagem e garantir o status correto
//function selectImage(img) {
//  document.querySelectorAll(".image-selection img").forEach((image) => {
//  image.classList.remove("selected");
 // });

 // img.classList.add("selected");
 // selectedFile = img.src;
 // console.log("Imagem selecionada: ", selectedFile);
//}

// Insere um item na tabela
function insertItem(item, index) {
  const tr = document.createElement("tr");

  tr.innerHTML = `
    <td>
      <div class="image-wrapper">
        <img src="${item.selectedFile || ""}" alt="Imagem" style="width: 50px; height: auto;">
        <span class="status">${imageStatusMap[item.selectedFile] || "Desconhecido"}</span>
      </div>
    </td>
    <td>${item.id}</td>
    <td>${item.nome}</td>
    <td>${item.email}</td>
    <td>${item.telefone}</td>
    <td>${item.selectedFile}</td>
    <td class="acao">
      <button onclick="editItem(${index})"><i class='bx bx-edit'></i></button>
    </td>
    <td class="acao">
      <button onclick="deleteItem(${index})"><i class='bx bx-trash'></i></button>
    </td>
  `;
  tbody.appendChild(tr);
}

// Edita um item
function editItem(index) {
  openModal(true, index);
}

// Deleta um item
function deleteItem(index) {
  const AlunoId = itens[index].id;
  deletarAluno(AlunoId).then(() => loadItens());
}

// Manipula o evento de salvar
btnSalvar.onclick = async (e) => {
  e.preventDefault();

  if (!sNome.value || !sEmail.value || !sTelefone.value || !selectedFile.value) {
    alert("Preencha todos os campos!");
    return;
  }

  const aluno = {
    nome: sNome.value,
    email: sEmail.value,
    telefone: sTelefone.value,
    selectedFile: selectedFile.value,
  };

  if (editingId !== null) {  // Se editingId não for null, significa que estamos editando um aluno
    await atualizaraluno(editingId, aluno);
  } else {
    await criaraluno(aluno);
  }

  modal.classList.remove("active");
  editingId = null;  // Resetar a variável para criar novos alunos
  selectedImageUrl = "";  // Limpar imagem selecionada
  loadItens();  // Atualizar a lista de alunos
};

// Função para carregar itens do backend
async function loadItens() {
  try {
    const response = await fetch(`${API_URL}/aluno`);
    if (response.ok) {
      itens = await response.json();
      tbody.innerHTML = "";
      itens.forEach((item, index) => insertItem(item, index));
    } else {
      console.error("Erro ao carregar alunos:", response.statusText);
    }
  } catch (error) {
    console.error("Erro ao listar alunos:", error);
  }
}

// Função para criar aluno
async function criaraluno(aluno) {
  try {
    const response = await fetch(`${API_URL}/criaraluno`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(aluno),
    });

    if (response.ok) {
      loadItens();  // Atualiza a lista de alunos após criação
    } else {
      console.error("Erro ao criar aluno:", response.statusText);
    }
  } catch (error) {
    console.error("Erro ao criar aluno:", error);
  }
}

// Função para atualizar aluno
async function atualizaraluno(id, aluno) {
  try {
    const response = await fetch(`${API_URL}/atualizaraluno/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(aluno),
    });

    if (response.ok) {
      loadItens();  // Atualiza a lista de alunos após atualização
    } else {
      console.error("Erro ao atualizar aluno:", response.statusText);
    }
  } catch (error) {
    console.error("Erro ao atualizar aluno:", error);
  }
}

// Função para deletar aluno
async function deletaraluno(id) {
  try {
    const response = await fetch(`${API_URL}/${id}`, {
      method: "DELETE",
    });

    if (response.ok) {
      loadItens();  // Atualiza a lista de alunos após exclusão
    } else {
      console.error("Erro ao deletar aluno:", response.statusText);
    }
  } catch (error) {
    console.error("Erro ao deletar aluno:", error);
  }
}

// Carrega os alunos ao inicializar a página
loadItens();


