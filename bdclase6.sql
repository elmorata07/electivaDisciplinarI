create database control_estudiantes;

use control_estudiantes;

create table estudiante(
    id_estudiante int not null auto_increment,
    nombre varchar(45) not null,
    apellido varchar(45) not null,
    email varchar(45) not null,
    telefono varchar(45) not null,
    edad double DEFAULT null,
    primary key(id_estudiante)
);

INSERT INTO `estudiante` VALUES (1,'Diana ','Martinez','dmart106@ibero.edu.co','3044363105',31),(4,'paola','gomez','paogomez@gmail.com','3045698748',25),(5,'pablo','murillo','pmurillo@gmail.com','3129568723',19),(9,'lina','Luna','lluna@hotmail.com','3109632569',29);
