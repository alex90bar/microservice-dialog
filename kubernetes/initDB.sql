create
    user "microservice-dialog";
alter
    user "microservice-dialog" with PASSWORD 'microservice-dialog';
create schema "dialog";
alter
    schema "dialog" owner to "microservice-dialog";