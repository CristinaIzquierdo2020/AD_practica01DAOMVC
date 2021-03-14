package exceptions;

import model.Cliente;

public class PossibleDuplicateClientException extends ClientException{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PossibleDuplicateClientException(Cliente c) {
		super(c);
	}

	public String getMessage() {
		return "Posible duplicación de un cliente";
	}
}
