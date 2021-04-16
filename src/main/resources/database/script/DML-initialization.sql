INSERT INTO ROLES (role) VALUES ('USER');
INSERT INTO ROLES (role) VALUES ('ADMIN');

INSERT INTO STATUSES (status) VALUES ('ACTIVE');
INSERT INTO STATUSES (status) VALUES ('BLOCK');

INSERT INTO AUTHORS (name) VALUES ('Cay S. Horstmann');
INSERT INTO AUTHORS (name) VALUES ('Joshua Bloch');
INSERT INTO AUTHORS (name) VALUES ('Herbert Schildt');
INSERT INTO AUTHORS (name) VALUES ('Bert Bates');
INSERT INTO AUTHORS (name) VALUES ('Brian Goetz');
INSERT INTO AUTHORS (name) VALUES ('Scott Oaks');
INSERT INTO AUTHORS (name) VALUES ('Eric Freeman');
INSERT INTO AUTHORS (name) VALUES ('Robert Cecil Martin');

INSERT INTO BOOKS (title, author_id, description, imageUri) VALUES ('Core Java Volume I – Fundamentals', 1, 'Core Java Volume I – Fundamentals is a Java reference book that offers a detailed explanation of various features of Core Java, including exception handling, interfaces, and lambda expressions.', 'https://i.postimg.cc/sgVMVPTn/Core-Java-Volume-I-Fundamentals.jpg');
INSERT INTO BOOKS (title, author_id, description, imageUri) VALUES ('Effective Java', 2, 'A must-have book for every Java programmer and Java aspirant, Effective Java makes up for an excellent complementary read with other Java books or learning material.', 'https://i.postimg.cc/MTjTs9GG/Effective-Java.jpg');
INSERT INTO BOOKS (title, author_id, description, imageUri) VALUES ('Java: A Beginner’s Guide', 3, 'If you are a seasoned Java programmer looking to enhance your Java knowledge, don’t be averted by the title of the book, Java: A Beginner’s Guide.', 'https://i.postimg.cc/QMhJZW7s/Java-A-Beginners-Guide.jpg');
INSERT INTO BOOKS (title, author_id, description, imageUri) VALUES ('Java - The Complete Reference', 3, 'Java - The Complete Reference is a convenient Java reference book with over 1000 pages.', 'https://i.postimg.cc/RFb7tsDV/Java-The-Complete-Reference.jpg');
INSERT INTO BOOKS (title, author_id, description, imageUri) VALUES ('Head First Java', 4, 'The most important selling points of Head First Java is its simplicity and super-effective real-life analogies that pertain to the Java programming concepts.', 'https://i.postimg.cc/5ynZ8VMQ/Head-First-Java.jpg');
INSERT INTO BOOKS (title, author_id, description, imageUri) VALUES ('Java Concurrency in Practice', 5, 'Java Concurrency in Practice is one of the best Java programming books to develop a rich understanding of concurrency and multithreading.', 'https://i.postimg.cc/kG0W1VC0/Java-Concurrency-in-Practice.jpg');
INSERT INTO BOOKS (title, author_id, description, imageUri) VALUES ('Java Performance: The Definite Guide', 6, 'Garbage collection, JVM, and performance tuning are some of the most favorable aspects of the Java programming language.', 'https://i.postimg.cc/7ZygSTm7/Java-Performance-The-Definite-Guide.jpg');
INSERT INTO BOOKS (title, author_id, description, imageUri) VALUES ('Head First Design Patterns', 7, 'Good hold on the OOP and design patterns is essential for writing immaculate Java applications.', 'https://i.postimg.cc/K8q8x6kz/Head-First-Design-Patterns.jpg');
INSERT INTO BOOKS (title, author_id, description, imageUri) VALUES ('Clean Code – A Handbook of Agile Software Craftsmanship', 8, 'As the name hints at, Clean Code is a classic Java programming book that imparts the knowledge of writing better code to its readers.', 'https://i.postimg.cc/k5vRQ1BP/Clean-Code-A-Handbook-of-Agile-Software-Craftsmanship.jpg');

INSERT INTO USERS (login, password, name, surname, email, phone, role_id, status_id) VALUES ('admin', 'admin', 'admin_name', 'admin_surname', 'admin@admin.by', '375291231231', 2, 1);
INSERT INTO USERS (login, password, name, surname, email, phone, role_id, status_id) VALUES ('user', 'user', 'user_name', 'user_surname','user@user.by', '375299879879', 1, 1);
INSERT INTO USERS (login, password, name, surname, email, phone, role_id, status_id) VALUES ('smp', 'smp', 'alherd', 'nestsiarkow', 's1mple@tut.by', '375255274953', 1, 2);