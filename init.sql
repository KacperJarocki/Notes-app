CREATE TABLE Url_Addresses(
            url_id serial primary key,
            url_address varchar(200) UNIQUE
);
--Table Account_type
CREATE TABLE Account_Type(
                             account_type_id SERIAL PRIMARY KEY,
                             name VARCHAR(30) NOT NULL UNIQUE,
                             number_of_notes INT NOT NULL,
                             url_edit BOOLEAN NOT NULL DEFAULT FALSE
);

--Table Accounts
CREATE TABLE Accounts(
                         account_id SERIAL PRIMARY KEY,
                         user_name VARCHAR(255) NOT NULL UNIQUE,
                         password VARCHAR(255) NOT NULL,
                         email VARCHAR(100) NOT NULL UNIQUE,
                         role VARCHAR(255) NOT NULL,
                         register_date DATE NOT NULL,
                         account_type_id INT not null ,
                         activated BOOLEAN NOT NULL DEFAULT FALSE,
                         url_activation VARCHAR(100),
                         FOREIGN KEY (account_type_id) REFERENCES Account_type(account_type_id)
);
--Table Category
CREATE TABLE Category(
                         category_id SERIAL PRIMARY KEY,
                         name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Spaces(
                  space_id SERIAL PRIMARY KEY,
                  space_creator_id INT,
                  title VARCHAR(40),
                  modification_date TIMESTAMP NOT NULL,
                  url_address_id Int,
                  FOREIGN key(space_creator_id) REFERENCES accounts(account_id),
                  FOREIGN key(url_address_id) REFERENCES url_addresses(url_id)
);

--Table Notes
CREATE TABLE Notes(
                      note_id SERIAL PRIMARY KEY,
                      title VARCHAR(40) NOT NULL,
                      content TEXT,
                      creation_date TIMESTAMP NOT NULL,
                      category_id INT,
                      account_id INT,
                      modification_date TIMESTAMP NOT NULL,
                      favorite BOOLEAN NOT NULL DEFAULT FALSE,
                      url_address_id int,
                      space_id int,
                      FOREIGN KEY (space_id) REFERENCES Spaces(space_id), 
                      FOREIGN KEY (category_id) REFERENCES category(category_id),
                      FOREIGN key (url_address_id) REFERENCES url_addresses(url_id),
                      FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);


CREATE TABLE Space_accesses(
            access_id serial primary key,
            space_id int,
            account_id int,
            read BOOLEAN NOT NULL,
            write BOOLEAN NOT NULL,
            FOREIGN KEY(space_id) REFERENCES Spaces(space_id),
            FOREIGN KEY(account_id) REFERENCES Accounts(account_id)
);
CREATE SEQUENCE confirmation_token_seq;
CREATE TABLE Confirmation_token(
                                  id INTEGER NOT NULL DEFAULT nextval('confirmation_token_seq'),
                                  token VARCHAR(100) NOT NULL,
                                  created_at TIMESTAMP NOT NULL,
                                  expires_at TIMESTAMP NOT NULL,
                                  confirmed_at TIMESTAMP,
                                  account_id INT Unique NOT NULL,
                                  FOREIGN KEY (account_id) REFERENCES Accounts(account_id)
);

CREATE TABLE Tags(
                    tag_id SERIAL PRIMARY KEY,
                    account_id INT,
                    note_id INT,
                    description VARCHAR(100) NOT NULL,
                    FOREIGN KEY (account_id) REFERENCES accounts(account_id),
                    FOREIGN KEY (note_id) REFERENCES notes(note_id)
);
--ADD FOREIGN KEYS
ALTER SEQUENCE confirmation_token_seq OWNED BY Confirmation_token.id;

INSERT INTO category (name) VALUES ('plaintext');
INSERT INTO category (name) VALUES ('javascript');
INSERT INTO category (name) VALUES ('python');
INSERT INTO category (name) VALUES ('csharp');
INSERT INTO category (name) VALUES ('java');
INSERT INTO category (name) VALUES ('markup');

INSERT INTO account_type (name, number_of_notes) VALUES ('Standardowy', 25);
INSERT INTO account_type (name, number_of_notes, url_edit) VALUES ('Premium', 2147483647, TRUE);
INSERT INTO ACCOUNTS VALUES(222,'admin','$2a$10$kul9jXRWkluxZMsBmMOwt.mVUKr0X0dVFV3Ys8SY94P4oMHLEKNgW','Admin','ADMIN','2023-04-25',2,true);
