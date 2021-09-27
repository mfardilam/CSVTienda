package csv;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class productosDAO {
	
	public void registrarProductos(Productos prod) {
		Conexion con= new Conexion();
		try {
			Statement estatuto = con.getConex().createStatement();
			estatuto.executeUpdate("INSERT INTO productos values('"+prod.getId()+"','"+prod.getCodigo()+"','"
					+prod.getNombre()+"','"+prod.getNitp()+"','"+prod.getPrecioc()+"','"+prod.getIva()+"','"
							+prod.getPreciov()+"')");
			estatuto.close();
			con.desconectar();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	/*
	public ArrayList<Productos> consultarProveedor(int nit){
		ArrayList<Productos> misProductos = new ArrayList<Productos>();
		Conexion con = new Conexion();
		try {
			PreparedStatement consulta = con.getConex().prepareStatement("SELECT * FROM productos WHERE codigo=?");
			consulta.setInt(1, nit);
			ResultSet res = consulta.executeQuery();
			if (res.next()) {
				Productos prod = new Productos();
				prod.setId(Integer.parseInt(res.getString("idproductos")));
				prod.setCodigo(Integer.parseInt(res.getString("codigo")));
				prod.setNombre(res.getString("nombre"));
				prod.setNitp(Integer.parseInt(res.getString("nitproveedor")));
				prod.setPrecioc(Double.parseDouble("precio_compra"));
				prod.setIva(Double.parseDouble("ivacompra"));
				prod.setPreciov(Double.parseDouble("precio_venta"));
				
				misProductos.add(prod);
			}
			res.close();
			consulta.close();
			con.desconectar();
		}catch(Exception e) {
			System.out.println(e);
		}
		return misProductos;
	}
	
	public ArrayList<Productos> listaProveedores(){
		ArrayList<Productos> misProductos = new ArrayList<Productos>();
		Conexion con = new Conexion();
		try {
			PreparedStatement consulta = con.getConex().prepareStatement("SELECT * FROM productos");
			ResultSet res = consulta.executeQuery();//Entender seleccion
			while(res.next()) {
				Productos prod = new Productos();
				prod.setId(Integer.parseInt(res.getString("idproductos")));
				prod.setCodigo(Integer.parseInt(res.getString("codigo")));
				prod.setNombre(res.getString("nombre"));
				prod.setNitp(Integer.parseInt(res.getString("nitproveedor")));
				prod.setPrecioc(Double.parseDouble("precio_compra"));
				prod.setIva(Double.parseDouble("ivacompra"));
				prod.setPreciov(Double.parseDouble("precio_venta"));
				
			}res.close();
			consulta.close();
			con.desconectar();
				
			}catch(Exception e) {
				System.out.println(e);
		}
		
		return misProductos;
	}
	//Modificar estas dos:
	public void modificarProveedor(String nit, String nombre, String direccion,String tel, String ciudad){       
       String safe = "SET SQL_SAFE_UPDATES = 0";      
       String consulta = "UPDATE proveedores SET nombre_proveedor='"+nombre+"',direccion='"+direccion+"'"
       		+ ",telefono='"+tel+"',ciudad='"+ciudad+"' WHERE nit='"+nit+"'";
       Conexion con = new Conexion();
       try { 	    
            Statement aux = con.getConex().createStatement();
            aux.executeQuery(safe);
            Statement declaracion = con.getConex().createStatement();            
            declaracion.executeUpdate(consulta);
          
                            
        }catch (Exception e) {
        	System.out.println(e);
        }finally {
        	con.desconectar();
        }
    }
    
    public void eliminarProveedor(String nit){
    	Conexion con = new Conexion();
        String consulta = "DELETE FROM proveedores WHERE nit='"+nit+"'";
        try {
            Statement declaracion = con.getConex().createStatement();
            declaracion.executeUpdate(consulta);            
        } catch (Exception e) {
        	System.out.println(e);
        }finally{            
            con.desconectar();
        }       
    }  
*/

}
