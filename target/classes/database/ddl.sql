DROP DATABASE IF EXISTS questionnaires;

CREATE DATABASE questionnaires;

USE questionnaires;

/*Creacion de Tablas*/

CREATE TABLE catalogs (
    id INT UNSIGNED AUTO_INCREMENT,
    create_at TIMESTAMP(6) NOT NULL DEFAULT (CURRENT_TIMESTAMP()),
    update_at TIMESTAMP(6) NULL ON UPDATE (CURRENT_TIMESTAMP()),
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_id_catalogs PRIMARY KEY (id)
);   

CREATE TABLE surveys (
    id INT UNSIGNED AUTO_INCREMENT,
    created_at TIMESTAMP NOT NULL DEFAULT (CURRENT_TIMESTAMP()),
    update_at TIMESTAMP(6) NULL ON UPDATE (CURRENT_TIMESTAMP()),
    description VARCHAR(255),
    name VARCHAR(255),
    CONSTRAINT pk_id_surveys PRIMARY KEY (id)
); 

CREATE TABLE chapter (
    id INT UNSIGNED AUTO_INCREMENT,
    created_at TIMESTAMP NOT NULL DEFAULT (CURRENT_TIMESTAMP()),
    survey_id INT UNSIGNED,
    update_at TIMESTAMP(6) NULL ON UPDATE (CURRENT_TIMESTAMP()),
    chapter_number VARCHAR(50),
    chapter_title VARCHAR(50),
    CONSTRAINT pk_id_chapter PRIMARY KEY(id),
    CONSTRAINT fk_survey_id_chapter FOREIGN KEY (survey_id)
    REFERENCES surveys(id)
);

CREATE TABLE questions (
    id INT UNSIGNED AUTO_INCREMENT,
    chapter_id INT UNSIGNED NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT (CURRENT_TIMESTAMP()),
    update_at TIMESTAMP(6) NULL ON UPDATE (CURRENT_TIMESTAMP()),
    question_number VARCHAR(10) NOT NULL,
    response_type VARCHAR(20),
    comment_question TEXT,
    question_text TEXT NOT NULL,
    CONSTRAINT pk_id_questions PRIMARY KEY (id),
    CONSTRAINT fk_chapter_id_questions FOREIGN KEY (chapter_id)
    REFERENCES chapter(id)
);

CREATE TABLE response_options (
    id INT UNSIGNED AUTO_INCREMENT,
    option_value INT(4) UNSIGNED,
    categorycatalog_id INT UNSIGNED,
    create_at TIMESTAMP NOT NULL DEFAULT (CURRENT_TIMESTAMP()), 
    parentresponse_id INT UNSIGNED,
    question_id INT UNSIGNED,
    update_at TIMESTAMP(6) NULL ON UPDATE (CURRENT_TIMESTAMP()),
    typecomponenthtml VARCHAR(30),
    comment_response TEXT,
    option_text TEXT,
    CONSTRAINT pk_id_response_options PRIMARY KEY (id),
    CONSTRAINT fk_question_id_response_options FOREIGN KEY (question_id)
    REFERENCES questions(id),
    CONSTRAINT fk_parentresponse_id_response_options FOREIGN KEY (parentresponse_id)
    REFERENCES response_options(id),
    CONSTRAINT fk_categoryecatalog_id_response_options FOREIGN KEY (categorycatalog_id)
    REFERENCES catalogs(id)
);

CREATE TABLE subresponse_options (
    id INT UNSIGNED AUTO_INCREMENT,
    subresponse_number INT(4) UNSIGNED,
    create_at TIMESTAMP NOT NULL DEFAULT (CURRENT_TIMESTAMP()),
    responseoptions_id INT UNSIGNED,
    update_at TIMESTAMP(6) NULL ON UPDATE (CURRENT_TIMESTAMP()),
    component_html VARCHAR(255),
    subresponse_text VARCHAR(255),
    CONSTRAINT pk_id_subresponse_options PRIMARY KEY (id),
    CONSTRAINT fk_responseoptions_id_subresponse_options FOREIGN KEY(responseoptions_id)
    REFERENCES response_options(id)
);

CREATE TABLE response_questions (
    id INT UNSIGNED AUTO_INCREMENT,
    response_id INT UNSIGNED,
    subresponse_id INT UNSIGNED,
    responsetext VARCHAR(80),
    CONSTRAINT pk_id_response_questions PRIMARY KEY (id),
    CONSTRAINT fk_response_id_response_questions FOREIGN KEY (response_id)
    REFERENCES response_options(id),
    CONSTRAINT fk_subresponse_id_subresponse_options FOREIGN KEY (subresponse_id)
    REFERENCES subresponse_options(id)
);
