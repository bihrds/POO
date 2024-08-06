import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {
	private List<Pedido> listaPedidosRealizados;
	
	public Cliente (int idCliente, String nomeCliente){
		super(idCliente, nomeCliente);
		listaPedidosRealizados = new ArrayList<>();
	}

	public Pedido getPedido(int idPedido){
		for (Pedido pedido : listaPedidosRealizados) {
			if (pedido.getIdPedido() == idPedido) {
				return pedido;
			}
		}
		return null;
	}
	
	public void adicionarPedido(Pedido pedido, int idPedido){
		pedido.setIdPedido(idPedido);
		listaPedidosRealizados.add(pedido);
	}

	public void removerPedido (Pedido pedido){
		listaPedidosRealizados.remove(pedido);
	}

	public void limparPedidos(){
		listaPedidosRealizados.clear();
	}

	public void exibirTodosPedidos(){
		for (Pedido pedido : listaPedidosRealizados) {
			System.out.println("Pedido com ID " +pedido.getIdPedido()+ " possui os produtos: ");
			pedido.verTodosProdutos();
		}
	}

	public List<Pedido> retornarLista(){
		return this.listaPedidosRealizados;
	}		
}