CREATE TABLE Usuario (
    cpf VARCHAR(11) NOT NULL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    senha VARCHAR(50) NOT NULL,
    email VARCHAR(75) NOT NULL
);

CREATE TABLE Telefone (
    cpf VARCHAR(11),
    Numero VARCHAR(11) NOT NULL,
    FOREIGN KEY (cpf) REFERENCES Usuario(cpf)
);

CREATE TABLE Adm (
    idAdm Serial PRIMARY KEY,
    cpf VARCHAR(11),
    FOREIGN KEY (cpf) REFERENCES Usuario(cpf)
);

CREATE TABLE Cliente (
    idusuario Serial PRIMARY KEY,
    cpf VARCHAR(11), 
    FOREIGN KEY (cpf) REFERENCES Usuario(cpf)
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
    cpf VARCHAR(11),
    idlivro Serial,
    DataEmprestimo Date NOT NULL,
    DataPrevita Date NOT NULL,
    DataDevolucao Date NOT NULL,
    FOREIGN KEY (cpf) REFERENCES Usuario(cpf),
    FOREIGN KEY (idlivro) REFERENCES Livro(idlivro)
)
