CREATE TABLE tutor (
    id_tutor BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(60) NOT NULL,
    cpf VARCHAR(45) NOT NULL UNIQUE,
    create_time DATETIME NOT NULL
);


CREATE TABLE especie (
    id_especie BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(25) NOT NULL UNIQUE
);

CREATE TABLE veterinario (
    id_veterinario BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(32) NOT NULL UNIQUE,
    crmv VARCHAR(255) NOT NULL UNIQUE,
    nascimento DATE,
    endereco VARCHAR(255),
    telefone VARCHAR(255)
);

CREATE TABLE animal (
    id_animal BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    raca VARCHAR(255),
    genero VARCHAR(32),
    nascimento DATE,
    peso DOUBLE,
    create_time DATETIME NOT NULL,
    tutor_id_tutor BIGINT NOT NULL,
    especie_id_especie BIGINT NOT NULL,
    CONSTRAINT fk_animal_tutor FOREIGN KEY (tutor_id_tutor) REFERENCES tutor(id_tutor),
    CONSTRAINT fk_animal_especie FOREIGN KEY (especie_id_especie) REFERENCES especie(id_especie)
);

CREATE TABLE consulta (
    id_consulta BIGINT AUTO_INCREMENT PRIMARY KEY,
    data_hora_consulta DATETIME NOT NULL,
    create_time DATETIME NOT NULL,
    animal_id_animal BIGINT NOT NULL,
    veterinario_id_veterinario BIGINT NOT NULL,
    CONSTRAINT fk_consulta_animal FOREIGN KEY (animal_id_animal) REFERENCES animal(id_animal),
    CONSTRAINT fk_consulta_veterinario FOREIGN KEY (veterinario_id_veterinario) REFERENCES veterinario(id_veterinario)
);