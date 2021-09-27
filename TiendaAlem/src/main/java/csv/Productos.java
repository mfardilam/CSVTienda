package csv;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="productos")
public class Productos {
	
	@Id
	@Column(name = "idproductos")
	private int id;
	
	@Column(name = "codigo")
	private int codigo;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "nitproveedor")
	private int nitp;
	
	@Column(name = "precio_compra")
	private double precioc;
	
	@Column(name = "ivacompra")
	private double iva;
	
	@Column(name = "precio_venta")
	private double preciov;
	
	
	public Productos() {
		super();
	}

	public Productos(int id, int codigo, String nombre, int nitp, double precioc, double iva, double preciov) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.nitp = nitp;
		this.precioc = precioc;
		this.iva = iva;
		this.preciov = preciov;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNitp() {
		return nitp;
	}

	public void setNitp(int nitp) {
		this.nitp = nitp;
	}

	public double getPrecioc() {
		return precioc;
	}

	public void setPrecioc(double precioc) {
		this.precioc = precioc;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getPreciov() {
		return preciov;
	}

	public void setPreciov(double preciov) {
		this.preciov = preciov;
	}
	
	

}
