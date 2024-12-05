// Seletores e variáveis globais
const form = document.getElementById('alunoForm');
const alunoList = document.getElementById('alunoList');
let alunoIdEdicao = null; // Variável para armazenar o id do aluno em edição

// Modal elements
const modal = document.getElementById("editModal");
const closeModal = document.querySelector(".close");
const editForm = document.getElementById("editAlunoForm");

// Função para validar o telefone com uma expressão regular
function validarTelefone(telefone) {
    // Expressão regular para validar números de telefone
    const regex = /^\(?\d{2}\)?\s?\d{4,5}-?\d{4}$/;

    return regex.test(telefone);
}

// Evento de envio do formulário principal para adicionar um aluno
form.addEventListener('submit', async (event) => {
    event.preventDefault();

    const user = document.getElementById('user').value;
    const telefone = document.getElementById('telefone').value;
    const email = document.getElementById('email').value;
    const imagemUrl = document.getElementById('imagemUrl').value;

    // Verifica se o telefone é válido
    if (!validarTelefone(telefone)) {
        alert("Por favor, insira um número de telefone válido.");
        return;
    }

    const aluno = { user, telefone, email, imagemUrl };

    try {
        // Verifica se estamos adicionando ou editando
        if (alunoIdEdicao) {
            alert('Finalize a edição atual antes de adicionar um novo aluno!');
        } else {
            // Requisição POST para adicionar aluno
            const response = await fetch('https://institutofalcao1.azurewebsites.net/a/pi', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(aluno)
            });

            if (response.ok) {
                await listarAlunos(); // Atualiza a lista de alunos
                form.reset(); // Limpa o formulário após adicionar
            } else {
                alert('Erro ao adicionar aluno');
            }
        }
    } catch (error) {
        console.error('Erro:', error);
    }
});

// Evento de envio do formulário de edição
editForm.addEventListener('submit', async (event) => {
    event.preventDefault();

    const user = document.getElementById('editUser').value;
    const telefone = document.getElementById('editTelefone').value;
    const email = document.getElementById('editEmail').value;
    const imagemUrl = document.getElementById('editImagemUrl').value;

    // Verifica se o telefone é válido
    if (!validarTelefone(telefone)) {
        alert("Por favor, insira um número de telefone válido.");
        return;
    }

    const aluno = { user, telefone, email, imagemUrl };

    try {
        // Requisição PUT para atualizar aluno
        const response = await fetch(`https://institutofalcao1.azurewebsites.net/a/${alunoIdEdicao}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(aluno)
        });

        if (response.ok) {
            await listarAlunos(); // Atualiza a lista de alunos
            resetModalFields(); // Limpa os campos do modal
            modal.style.display = "none"; // Fecha o modal
            alunoIdEdicao = null; // Reseta o ID de edição
        } else {
            alert('Erro ao salvar alterações');
        }
    } catch (error) {
        console.error('Erro:', error);
    }
});

// Função para listar alunos
async function listarAlunos() {
    try {
        const response = await fetch('https://institutofalcao1.azurewebsites.net/a');
        const alunos = await response.json();

        alunoList.innerHTML = ''; // Limpa a lista antes de atualizar
        alunos.forEach(aluno => {
            const li = document.createElement('li');
            li.innerHTML = `
                <img src="${aluno.imagemUrl}" alt="${aluno.user}">
                <div class="info">
                    <strong>${aluno.user}</strong>
                    <span>${aluno.telefone}</span>
                    <span>${aluno.email}</span>
                </div>
                <div class="actions">
                    <button onclick="editarAluno(${aluno.id})">Editar</button>
                    <a href="#" onclick="deletarAluno(${aluno.id})">Deletar</a>
                </div>
            `;
            alunoList.appendChild(li);
        });
    } catch (error) {
        console.error('Erro:', error);
    }
}

// Função para carregar dados no modal e habilitar a edição
function editarAluno(id) {
    alunoIdEdicao = id; // Define o ID do aluno que será editado
    fetch(`https://institutofalcao1.azurewebsites.net/a/${id}`)
        .then(response => response.json())
        .then(aluno => {
            // Preenche o modal com os dados do aluno
            document.getElementById('editUser').value = aluno.user;
            document.getElementById('editTelefone').value = aluno.telefone;
            document.getElementById('editEmail').value = aluno.email;
            document.getElementById('editImagemUrl').value = aluno.imagemUrl;

            // Exibe o modal
            modal.style.display = "block";
        })
        .catch(error => console.error('Erro ao buscar dados do aluno:', error));
}

function abrirModal() {
    const modal = document.getElementById("editModal");
    modal.style.display = "flex";  // Torna o modal visível e centralizado
}

// Evento para fechar o modal
closeModal.onclick = function() {
    modal.style.display = "none";
    resetModalFields(); // Limpa os campos do modal
    alunoIdEdicao = null; // Reseta o ID de edição
};

// Fecha o modal ao clicar fora dele
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
        resetModalFields(); // Limpa os campos do modal
        alunoIdEdicao = null; // Reseta o ID de edição
    }
};

// Função para deletar um aluno
async function deletarAluno(id) {
    if (confirm('Tem certeza que deseja deletar este aluno?')) {
        try {
            const response = await fetch(`https://institutofalcao1.azurewebsites.net/a/${id}`, {
                method: 'DELETE'
            });

            if (response.ok) {
                await listarAlunos(); // Atualiza a lista após deletar
            } else {
                alert('Erro ao deletar aluno');
            }
        } catch (error) {
            console.error('Erro:', error);
        }
    }
}

// Função para limpar os campos do modal
function resetModalFields() {
    document.getElementById('editUser').value = '';
    document.getElementById('editTelefone').value = '';
    document.getElementById('editEmail').value = '';
    document.getElementById('editImagemUrl').value = '';
}

// Carrega a lista de alunos ao iniciar a página
listarAlunos();
