package exceptions;

import model.Cliente;

public class DuplicateException extends ClientException{
	
	public DuplicateException(Cliente c) {
		super(c);
	}

	private static final long serialVersionUID = 1L;
	
	public String getMessage() {
		return "El cliente con ese CODIGO ya existe";
	}
}
