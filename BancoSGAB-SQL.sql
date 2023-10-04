CREATE TABLE Usuario (
    cpf VARCHAR(11) NOT NULL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    senha VARCHAR(50) NOT NULL,
    email VARCHAR(75) NOT NULL
);

CREATE TABLE Telefone (
    idTelefone Serial PRIMARY KEY,
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

CREATE TABLE LivroEmprestado(
    idEmprestado Serial PRIMARY KEY,
    idLivro int NOT NULL,
    FOREIGN KEY(idLivro) REFERENCES Livro(idLivro)
);

CREATE TABLE DataEmprestimo(
    idDataEmprestimo Serial PRIMARY KEY,
    DataEmp Date NOT NULL
);

CREATE TABLE DataPrevista(
    idDataPrevista Serial PRIMARY KEY,
    DataPre Date NOT NULL
);

CREATE TABLE DataDevolucao(
    idDataDevolucao Serial PRIMARY KEY,
    DataDev Date NOT NULL
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
    quantLivros int NOT NULL,
    quantDisponivel int NOT NULL
);

CREATE TABLE Emprestimo (
    cpf VARCHAR(11) NOT NULL,
    idEmprestado int NOT NULL,
    idDataEmprestimo int NOT NULL,
    idDataPrevista int NOT NULL,
    idDataDevolucao int NOT NULL,
    FOREIGN KEY(idEmprestado) REFERENCES LivroEmprestado(idEmprestado),
    FOREIGN KEY(idDataEmprestimo) REFERENCES DataEmprestimo(idDataEmprestimo),
    FOREIGN KEY(idDataPrevista) REFERENCES DataPrevista(idDataPrevista),
    FOREIGN KEY(idDataDevolucao) REFERENCES DataDevolucao(idDataDevolucao)
)
