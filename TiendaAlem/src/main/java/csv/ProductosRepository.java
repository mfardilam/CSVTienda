package csv;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.data.repository.CrudRepository;

@Repository //Realiza operaciones CRUD
//Original

public interface ProductosRepository extends JpaRepository<Productos, Integer>{

}
/*
public interface ProductosRepository extends CrudRepository<Productos, Integer>{

}*/