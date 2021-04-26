package agenda.modelo;

/**
 * @version 1.0,
 * @author Jhon Vera, Diana Peralta, Adrian Vitoria
 * Representacion de un conctacto de la agenda
 */ 

public abstract class Contacto {
	
	/**
	 * Atributos
	 */
	
	/*Variables*/
	
	private String nombre;
	private String apellidos;
	private String telefono;
	private String email;
	
	/*Constructor*/
	
	/**
     * A partir de los argumentos recibidos
     * inicializa los atributos de forma adecuada
     * Todos los argumento recibidos son correctos (no hay incoherencias)
     */
	
	public Contacto(String nombre, String apellidos, String telefono,
			String email) {
		this.nombre = nombre.toUpperCase();
		this.apellidos = apellidos.toUpperCase();
		this.telefono = telefono;
		this.email = email.toLowerCase();
	}
	
	/* Metodos */
	
	/**
	 * Accesor para el nombre del contacto
	 * @return nombre inicializado en el constructor
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Accesor para los apellidos del contacto
	 * @return apellidos inicializado en el constructor
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Accesor para el telefono del contacto
	 * @return telefono inicializado en el constructor
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Accesor para el email del contacto
	 * @return email inicializado en el constructor
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Mutador para el nombre del contacto
	 * @param nombre 
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Mutador para el nombre del contacto
	 * @param apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	/**
	 * Mutador para el nombre del contacto
	 * @param telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	/**
	 * Mutador para el nombre del contacto
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public int hashCode() {
		return nombre.hashCode() + email.hashCode() + telefono.hashCode() * 11;
	}
	
	/**
	 * Metodo que obtiene del atributo apellidos la primera letra 
	 * @return primera letra del atributo apellidos
	 */
	public char getPrimeraLetra() {
		char letra = getApellidos().charAt(0);
		return letra;
	}
	
	/**
	 * Compara dos contactos, para saber si son iguales
	 * @param objeto que se va a comparar
	 * @return true si los dos conatctos pertenecen a la misma 
	 * clase, tienen el mismo nombre, apellidos y email. En otro caso devuelve false 
	 */
	public boolean equals(Object obj) {
		if(hashCode() == obj.hashCode()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Compara dos contactos por la varible apellidos
	 * @param el contacto con que se va a comparar
	 * @return 1 -> si el hashcode del apellido es mayor que el apellidos del contacto como parametro
	 * -1 -> si el hashcode del apellido es menor que que el apellidos del contacto como parametro
	 * 0 -> si la variable apellidos es igual que el apellidos del contacto como parametro
	 */
	public int compareTo(Contacto c) {
		if(apellidos.hashCode() < c.apellidos.hashCode()) {
			return -1;
		}
		if(apellidos.equalsIgnoreCase(c.apellidos) ) {
			return 0;
		}
		return 1;
	}
	
	/**
	 * Compara dos contactos por la varible nombre
	 * @param el contacto con que se va a comparar
	 * @return 1 -> si el hashcode del nombre es mayor que el nombre del contacto como parametro
	 * -1 -> si el hashcode del nombre es menor que el nombre del contacto como parametro
	 * 0 -> si la variable nombre es igual que el nombre del contacto como parametro
	 */
	public int compareTo2(Contacto c) {
		if(nombre.hashCode() < c.nombre.hashCode()) {
			return -1;
		}
		if(nombre.equalsIgnoreCase(c.nombre)) {
			return 0;
		}
		return 1;
	}
	
	/**
	 * Accesor abstracto que deben implimentar todas las clases hijos
	 *@return firma de cada contacto 
	 */
	public abstract String getFirmaEmail();
	
	/**
	 * Representacion textual del contacto 
	 * @return String representacion textual del contacto
	 */
	public String toString() {
		if(this.getClass().equals(Personal.class)) {
			return apellidos + ", " + nombre + " (" + Personal.class.getSimpleName().toUpperCase() + ")\n" +
					"Tfno: " + telefono + " | email: " + email + "\n";
		}
		return apellidos + ", " + nombre + " (" + Profesional.class.getSimpleName().toUpperCase() + ")\n" +
		"Tfno: " + telefono + " | email: " + email + "\n";
	}
	
	
}
