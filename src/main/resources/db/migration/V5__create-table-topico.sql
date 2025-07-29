create table topicos(
    id bigint not null auto_increment primary key,
    titulo varchar(200) not null,
    mensaje text not null,
    fecha_creacion date not null,
    status varchar(100) not null,
    autor_id bigint not null,
    curso_id bigint not null,
    foreign key (autor_id) references usuarios(id),
    foreign key (curso_id) references cursos(id)
);