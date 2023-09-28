CREATE TABLE Usuario (
    idusuario Serial PRIMARY KEY,
    cpf VARCHAR(11) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    telefone VARCHAR(11) NOT NULL,
    senha VARCHAR(50) NOT NULL,
    email VARCHAR(75) NOT NULL
);

CREATE TABLE EnderecoUsuario (
    idusuario Serial PRIMARY KEY,
    Rua VARCHAR(50) NOT NULL,
    Numero INT NOT NULL,
    Cep VARCHAR(8) NOT NULL,
    Uf VARCHAR(2) NOT NULL,
    Cidade VARCHAR(50) NOT NULL,
    FOREIGN KEY (idusuario) REFERENCES Usuario(idusuario)
);

CREATE TABLE UsuarioAdm (
    idusuario Serial PRIMARY KEY,
    FOREIGN KEY (idusuario) REFERENCES Usuario(idusuario)
);

CREATE TABLE UsuarioCliente (
    idusuario Serial PRIMARY KEY,
    livrosEmprestados VARCHAR(3) NOT NULL, 
    FOREIGN KEY (idusuario) REFERENCES Usuario(idusuario)
);

CREATE TABLE Livro (
    idlivro Serial PRIMARY KEY,
    Titulo VARCHAR(50) NOT NULL,
    Genero VARCHAR(20) NOT NULL,
    Autor VARCHAR(255) NOT NULL,
    DataPublicacao DATE NOT NULL,
    Edicao VARCHAR(50) NOT NULL,
    Editora VARCHAR(50) NOT NULL,
    ISBN VARCHAR(13) NOT NULL, 
    LivroAcervo Bool NOT NULL,
    LivroDisponivel Bool NOT NULL
);

CREATE TABLE Emprestimo (
    idusuario Serial,
    idlivro Serial,
    DataEmprestimo DATE NOT NULL,
    FOREIGN KEY (idusuario) REFERENCES Usuario(idusuario),
    FOREIGN KEY (idlivro) REFERENCES Livro(idlivro)
)
