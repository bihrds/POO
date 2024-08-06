public class Pessoa {
	private int idCliente;
	private String nomeCliente;

	public Pessoa(int idCliente, String nomeCliente){
		this.nomeCliente = nomeCliente;
		this.idCliente = idCliente;
	}

	public String getNomeCliente(){
		return this.nomeCliente;
	}

	public int getIdCliente(){
		return this.idCliente;
	}
}