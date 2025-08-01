create table respuestas(
    id bigint not null auto_increment primary key,
    mensaje text not null,
    fecha_creacion date not null,
    solucion boolean default false,
    topico_id bigint not null,
    autor_id bigint not null,
    foreign key (topico_id) references topicos(id),
    foreign key (autor_id) references usuarios(id)


);
