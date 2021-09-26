package csv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;




//@CrossOrigin("http://localhost:8080") //para poder llamar API
@RestController //Vuelve la clase controlador
@RequestMapping("/api/csv") //Para mapear los metodos específicos cuando un API la consume
public class CSVController {
	
	@Autowired
	  CSVService fileService;

	  @PostMapping("/upload") //Post Request
	  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		  //Para cargar el archico
		  String message = "";
		    System.out.println("entre a cargar");
		    if (CSVHelper.hasCSVFormat(file)) {
		    	System.out.println("Es un CSV");
		      try {		    	
		        fileService.save(file);
		        System.out.println("Se cargo");
		        message = "Carga exitosa: " + file.getOriginalFilename();
		        
		        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
		                .path("/api/csv/download/")
		                .path(file.getOriginalFilename())
		                .toUriString();
		        System.out.println("Mensaje antes del return");
		        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message,fileDownloadUri));
		      } catch (Exception e) {
		    	  System.out.println("No se pudo cargar");
		        message = "No se pudo cargar: " + file.getOriginalFilename() + "!";
		        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message,""));
		      }
		    }

		    System.out.println("Mensaje Es otro formatooo");
		    message = "Por favor carge un CSV!";
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message,""));
		  }

	  @GetMapping("/productos")  //GET request
	  public ResponseEntity<List<Productos>> obtenerProductos() {
		  //Obtener los productos listados
		  try {
		      List<Productos> tutorials = fileService.getAll();

		      if (tutorials.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }

		      return new ResponseEntity<>(tutorials, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		  }
	  @GetMapping("/download/{fileName:.+}")
	  public ResponseEntity<Resource> archivo(@PathVariable String fileName) {
		  //Descargar el archivo CSV
	    InputStreamResource file = new InputStreamResource(fileService.load());

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
	        .contentType(MediaType.parseMediaType("application/csv"))
	        .body(file);
	  }
	}
	


