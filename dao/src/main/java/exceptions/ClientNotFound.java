package exceptions;

public class ClientNotFound extends Exception{

	private static final long serialVersionUID = 1L;
	
	
	public String getMessage() {
		return "El cliente que buscas no existe en la base de datos";
	}
}

