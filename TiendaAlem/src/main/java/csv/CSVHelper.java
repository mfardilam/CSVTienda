package csv;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;


public class CSVHelper {
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "id", "codigo", "nombre", "nit_prov","precio_compra","ivacompra","precio_venta"};

  public static boolean hasCSVFormat(MultipartFile file) {//Se revisa si el formato es CSV
    if (TYPE.equals(file.getContentType())
    		|| file.getContentType().equals("application/vnd.ms-excel")) {
      return true;
    }

    return false;
  }

  //Original
  
  public static List<Productos> csvProductos(InputStream is) { //Metodo para leer el CSV
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {//Cada linea en forma de registro

      List<Productos> productosList = new ArrayList<>();
      Iterable<CSVRecord> csvRecords = csvParser.getRecords();//Se optiene el valor de cada registro
      List<String> list = getListFromIterator(((List<CSVRecord>) csvRecords).get(0).iterator());
      System.out.println("Lista cosa 1: "+list.get(0));
      System.out.println("Records: "+csvRecords);
      for (CSVRecord csvRecord : csvRecords) {
    	  System.out.println("estoy haciendo parser de CSV");
    	  Productos prod = new Productos(    			  
			  Integer.parseInt(csvRecord.get("id")),
			  Integer.parseInt(csvRecord.get("codigo")),
			  csvRecord.get("nombre"),
			  Integer.parseInt(csvRecord.get("nit_prov")),
			  Double.parseDouble(csvRecord.get("precio_compra")),
			  Double.parseDouble(csvRecord.get("ivacompra")),
			  Double.parseDouble(csvRecord.get("precio_venta"))
            );
    	  System.out.println("lista: "+prod);
    	  productosList.add(prod);
      }

      return productosList;
      
    } catch (IOException e) {
      throw new RuntimeException("Fallo el analisis del archivo CSV: " + e.getMessage());
    }
  }

  
  //prueba1
  
  /*
  public static List<Productos> csvProductos(InputStream is) throws IOException{
	  List<Productos> listaProductos = new ArrayList<Productos>();
	  CSVParser parser = CSVParser.parse(is, Charset.defaultCharset(), CSVFormat.DEFAULT.withHeader());
	  
	  for(CSVRecord record: parser) {
		  System.out.println("estoy haciendo parser de CSV");
		  System.out.println("Esto recibo: "+record);
		  //Trim para quitar espacios en blanco antes del dato 
		  int id = Integer.parseInt(record.get("id").trim());
		  int cod = Integer.parseInt(record.get("codigo").trim());
		  String nombre = record.get("nombre").trim();
		  int nitpro = Integer.parseInt(record.get("nit_prov").trim());
		  double pc = Double.parseDouble(record.get("precio_compra").trim());
		  double iva = Double.parseDouble(record.get("ivacompra").trim());
		  double pv = Double.parseDouble(record.get("precio_venta").trim());
		  
		  Productos prod = new Productos(id,cod,nombre,nitpro,pc,iva,pv);
		  listaProductos.add(prod);
	  }
	  System.out.println(listaProductos);
	  return listaProductos;
  }*/
  
 
  //Prueba 2

  /*public static List<Productos> csvProductos(File file) throws IOException{
	  CSVParser parser = new CSVParser(new FileReader(file), CSVFormat.DEFAULT);
	  List<CSVRecord> list = parser.getRecords();
	  for (CSVRecord record : list) {
	      String[] arr = new String[record.size()];
	      int i = 0;
	      for (String str : record) {
	          arr[i++] = str;
	      }	    
	  System.out.println(list);
	  List<Productos> lista1 = new ArrayList<Productos>();
	  
	  }return lista1;
  }*/
  
  
  
  private static List<String> getListFromIterator(Iterator<String> iterator) {
	  
	  List<String> list = new ArrayList<>();
	  
      // Add each element of iterator to the List
      iterator.forEachRemaining(list::add);

      // Return the List
      return list;
}

public static ByteArrayInputStream toCSV(List<Productos> productosList) {// Para poner datos de la BD al CSV
    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
      for (Productos prod : productosList) {
        List<String> data = Arrays.asList(
        		String.valueOf(prod.getId()),
        		String.valueOf(prod.getCodigo()),
        		prod.getNombre(),
        		String.valueOf(prod.getNitp()),
        		String.valueOf(prod.getPrecioc()),
        		String.valueOf(prod.getIva()),
        		String.valueOf(prod.getPreciov())
        		
            );

        csvPrinter.printRecord(data);
      }

      csvPrinter.flush();
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("Fallo la importación del archivo CSV: " + e.getMessage());
    }
  }

private void add(CSVRecord csvrecord1) {
}
}
