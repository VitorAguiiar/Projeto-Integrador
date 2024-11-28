// URL base da API
const apiUrl = 'http://localhost:8080/api';  // Substitua pelo URL correto se necessário

// Carregar todos os alunos na tabela
function loadAlunos() {
    fetch(`${apiUrl}/buscaraluno`)
        .then((response) => response.json())
        .then((data) => {
            const tableBody = document.getElementById('alunoTableBody');
            tableBody.innerHTML = ''; // Limpa a tabela antes de popular com novos dados

            data.forEach((aluno) => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${aluno.nome}</td>
                    <td>${aluno.telefone}</td>
                    <td>${aluno.email}</td>
                    <td><img src="${aluno.imagemUrl}" alt="Foto" style="max-width: 50px; height: auto;"></td>
                    <td><button onclick="editarAluno(${aluno.id})">Editar</button></td>
                    <td><button onclick="deletarAluno(${aluno.id})">Excluir</button></td>
                `;
                tableBody.appendChild(tr);
            });
        })
        .catch((error) => console.error('Erro ao carregar alunos:', error));
}

// Salvar um novo aluno
document.getElementById('modalForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const nome = document.getElementById('m-nome').value;
    const telefone = document.getElementById('m-Telefone').value;
    const email = document.getElementById('m-Email').value;
    const fotoInput = document.getElementById('m-foto');
    const foto = fotoInput.files[0];

    const aluno = {
        nome: nome,
        telefone: telefone,
        email: email
    };

    // Enviar os dados do aluno para a API
    const formData = new FormData();
    formData.append('nome', aluno.nome);
    formData.append('telefone', aluno.telefone);
    formData.append('email', aluno.email);
    
    if (foto) {
        formData.append('file', foto);
    }

    fetch(`${apiUrl}/criaraluno`, {
        method: 'POST',
        body: formData
    })
        .then((response) => response.json())
        .then((data) => {
            alert('Aluno criado com sucesso!');
            closeModal();
            loadAlunos(); // Recarrega a lista de alunos
        })
        .catch((error) => {
            console.error('Erro ao criar aluno:', error);
            alert('Erro ao criar aluno!');
        });
});

// Deletar um aluno
function deletarAluno(id) {
    if (confirm('Tem certeza que deseja excluir este aluno?')) {
        fetch(`${apiUrl}/deletaraluno/${id}`, {
            method: 'DELETE'
        })
            .then(() => {
                alert('Aluno excluído com sucesso!');
                loadAlunos(); // Recarrega a lista de alunos
            })
            .catch((error) => {
                console.error('Erro ao excluir aluno:', error);
                alert('Erro ao excluir aluno!');
            });
    }
}

// Editar aluno
function editarAluno(id) {
    fetch(`${apiUrl}/buscaraluno/${id}`)
        .then((response) => response.json())
        .then((data) => {
            document.getElementById('m-nome').value = data.nome;
            document.getElementById('m-Telefone').value = data.telefone;
            document.getElementById('m-Email').value = data.email;

            // Exibe a imagem atual, se houver
            if (data.imagemUrl) {
                const photoPreview = document.getElementById('photoPreview');
                photoPreview.style.display = 'block';
                photoPreview.src = data.imagemUrl;
            }

            // Alterar o form para um modo de edição
            const btnSalvar = document.getElementById('btnSalvar');
            btnSalvar.textContent = 'Atualizar';

            // Substitui o evento do submit para a edição
            document.getElementById('modalForm').onsubmit = function (event) {
                event.preventDefault();

                const nome = document.getElementById('m-nome').value;
                const telefone = document.getElementById('m-Telefone').value;
                const email = document.getElementById('m-Email').value;
                const fotoInput = document.getElementById('m-foto');
                const foto = fotoInput.files[0];

                const alunoAtualizado = {
                    nome: nome,
                    telefone: telefone,
                    email: email
                };

                const formData = new FormData();
                formData.append('nome', alunoAtualizado.nome);
                formData.append('telefone', alunoAtualizado.telefone);
                formData.append('email', alunoAtualizado.email);
                if (foto) {
                    formData.append('file', foto);
                }

                fetch(`${apiUrl}/editaraluno/${id}`, {
                    method: 'PUT',
                    body: formData
                })
                    .then((response) => response.json())
                    .then(() => {
                        alert('Aluno atualizado com sucesso!');
                        closeModal();
                        loadAlunos(); // Recarrega a lista de alunos
                    })
                    .catch((error) => {
                        console.error('Erro ao atualizar aluno:', error);
                        alert('Erro ao atualizar aluno!');
                    });
            };

            openModal(); // Abre o modal em modo de edição
        })
        .catch((error) => {
            console.error('Erro ao buscar aluno para edição:', error);
            alert('Erro ao carregar dados do aluno!');
        });
}

// Abrir o modal
function openModal() {
    document.getElementById('modalContainer').style.display = 'flex';
}

// Fechar o modal
function closeModal() {
    document.getElementById('modalContainer').style.display = 'none';
    // Limpar o formulário e a visualização da foto
    document.getElementById('modalForm').reset();
    document.getElementById('photoPreview').style.display = 'none';
}

// Pré-visualizar a imagem antes de enviar
function previewImage(event) {
    const photoPreview = document.getElementById('photoPreview');
    photoPreview.style.display = 'block';
    photoPreview.src = URL.createObjectURL(event.target.files[0]);
}

// Inicializar a lista de alunos ao carregar a página
window.onload = loadAlunos;
