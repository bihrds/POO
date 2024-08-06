import java.util.ArrayList;
import java.util.List;

public class Pedido {
	private List<Produto> listaProdutos;
	private int idPedido;

	public Pedido(){
		listaProdutos = new ArrayList<>();
	}

	public Pedido(int idPedido){
		this.idPedido= idPedido;
		listaProdutos = new ArrayList<>();
	}

	public int getIdPedido(){
		return this.idPedido;
	}

	public void setIdPedido(int idPedido){
		this.idPedido= idPedido;
	}

	public void adicionarProduto(Produto produto, int qtdVendido){
		produto.setQtdVendido(qtdVendido);
		listaProdutos.add(produto);
	}

	public void removerProduto(Produto produto){
		listaProdutos.remove(produto);
	}

	public void verTodosProdutos(){
		if (listaProdutos == null) {
			System.out.println("Não há produtos no carrinho");
		}
		for (Produto produto : listaProdutos) {
			System.out.println("Produto: " +produto.getNomeProduto()+ " Preço: R$ " +produto.getPreco());
		}
	}

	public float getValorTotal(){
		float valor = 0;
		for (Produto produto : listaProdutos) {
			produto.getPreco();
			valor = valor + produto.getPreco(); 
		}
		return valor;
	}

	public void limparPedido(){
		listaProdutos.clear();
	}

	public boolean verificarRepeticao(Produto produto) {
		if (listaProdutos.contains(produto)) {
			return true;
		}
		else return false;
	}

	public List<Produto> retornarLista(){
		return this.listaProdutos;
	}
}