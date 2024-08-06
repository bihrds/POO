public class Produto {
	private int codigoProduto;
	private String nomeProduto;
	private float precoProduto;
	private Categoria categoriaProduto;
	private int qtdEstoque;
	private int qtdVendido;


	public int getQtdVendido() {
		return qtdVendido;
	}

	public void setQtdVendido(int qtdVendido) {
		this.qtdVendido = qtdVendido;
	}

	public Produto (){

	}

	public Produto(int codigoProduto, String nomeProduto, float precoProduto, Categoria categoriaProduto){
		this.codigoProduto = codigoProduto;
		this.nomeProduto = nomeProduto;
		this.precoProduto = precoProduto;
		this.categoriaProduto = categoriaProduto;
	}

	public int getQtdEstoque(){
		return qtdEstoque;
	}

	public void setQtdEstoque(int qtdEstoque){
		this.qtdEstoque= qtdEstoque;
	}

	public int getCodigoProduto(){
		return codigoProduto;
	}

	public Categoria getCategoriaProduto(){
		return categoriaProduto;
	}

	public String getNomeProduto(){
		return this.nomeProduto;
	}

	public float getPreco(){
		return precoProduto;
	}
}