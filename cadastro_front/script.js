// Obter os elementos
const inputImagem = document.getElementById('imagem');
const caixaImagem = document.querySelector('.caixa.imagem'); // Seleciona a div com as classes 'caixa' e 'imagem'

// Adicionar um event listener para o evento 'change'
inputImagem.addEventListener('change', function() {
  // Limpar a caixa de imagem antes de adicionar uma nova imagem
  caixaImagem.innerHTML = '';

  // Verificar se um arquivo foi selecionado
  if (this.files && this.files[0]) {
    const reader = new FileReader();

    reader.onload = function(e) {
      const img = document.createElement('img');
      img.src = e.target.result; 1 
      caixaImagem.appendChild(img);
    };

    reader.readAsDataURL(this.files[0]);
  }
});