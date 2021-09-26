package csv;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CSVService {
  @Autowired
  ProductosRepository repository;

  
  
  public void save(MultipartFile file) {//Guardar el CSV en la BD
    try {
    	System.out.println("estoy intentando leer el archivo - services");
      List<Productos> productos = CSVHelper.csvProductos(file.getInputStream());
      repository.saveAll(productos);
      System.out.println("este: "+productos.get(1));
      System.out.println("lei el archivo services");
    } catch (IOException e) {
    	System.out.println("Flayo re duro");
      throw new RuntimeException("Fallo en el proceso de guardar el archivo: " + e.getMessage());
    }
  }
  
  
 


  public ByteArrayInputStream load() {//lee el archivo en la DB y retorna lo leido en un ByteArrayInputStream
    List<Productos> prod = repository.findAll();

    ByteArrayInputStream in = CSVHelper.toCSV(prod);
    return in;
  }

  
  
  public List<Productos> getAll() {//Lee el archivo de la BD y lo retorn en forma de lista de registros
    return repository.findAll();
  }
  
  
}
