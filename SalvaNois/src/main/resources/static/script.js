const form = document.getElementById('produtoForm');
const produtosList = document.getElementById('produtosList');

form.addEventListener('submit', async (event) => {
    event.preventDefault();

    const nome = document.getElementById('nome').value;
    const imagemUrl = document.getElementById('imagemUrl').value;

    const produto = { nome, imagemUrl };

    try {
        const response = await fetch('http://localhost:8080/produtos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(produto)
        });

        if (response.ok) {
            await listarProdutos(); // Atualiza a lista após adicionar
            form.reset(); // Limpa o formulário
        } else {
            alert('Erro ao adicionar produto');
        }
    } catch (error) {
        console.error('Erro:', error);
    }
});

// Função para listar produtos
async function listarProdutos() {
    try {
        const response = await fetch('http://localhost:8080/produtos');
        const produtos = await response.json();

        produtosList.innerHTML = ''; // Limpa a lista existente
        produtos.forEach(produto => {
            const li = document.createElement('li');
            li.innerHTML = `
                <strong>${produto.nome}</strong><br>
                <img src="${produto.imagemUrl}" alt="${produto.nome}"><br>
                <a href="#" onclick="deletarProduto(${produto.id})">Deletar</a>
            `;
            produtosList.appendChild(li);
        });
    } catch (error) {
        console.error('Erro:', error);
    }
}

// Função para deletar um produto
async function deletarProduto(id) {
    if (confirm('Tem certeza que deseja deletar este produto?')) {
        try {
            const response = await fetch(`http://localhost:8080/produtos/${id}`, {
                method: 'DELETE'
            });

            if (response.ok) {
                await listarProdutos(); // Atualiza a lista após deletar
            } else {
                alert('Erro ao deletar produto');
            }
        } catch (error) {
            console.error('Erro:', error);
        }
    }
}

// Carrega os produtos ao iniciar
listarProdutos();
