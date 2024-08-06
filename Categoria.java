import java.util.ArrayList;
import java.util.List;

public class Categoria {
	private String nomeCategoria;
	private List<Subcategoria> listaSubcategorias = new ArrayList<>();

	public Categoria(String nomeCategoria){
		this.nomeCategoria = nomeCategoria;
	}

	public Categoria(){

	}

	public String getNomeCategoria(){
		return this.nomeCategoria;
	}
	
	public void setNomeCategoria(String nomeCategoria){
		this.nomeCategoria = nomeCategoria;
	}

	public void adicionarSubcategoria(Subcategoria subcategoria){
        	listaSubcategorias.add(subcategoria);
        	System.out.println("Subcategoria adicionada com sucesso!");
    }
}