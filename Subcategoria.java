public class Subcategoria extends Categoria {
	
	private Categoria categoriaBase;
	
	public void setCategoriaBase(Categoria categoriaBase) {
		this.categoriaBase = categoriaBase;
	}

	public Categoria getCategoriaBase() {
		return categoriaBase;
	}

	private String nomeSubcategoria;

	public Subcategoria( String nomeSubcategoria, Categoria categoriaBuscada){
		this.nomeSubcategoria = nomeSubcategoria;
		this.categoriaBase = categoriaBuscada;
	}

	public String getNomeSubcategoria(){
		return this.nomeSubcategoria;
	}

	public void setNomeCategoria(String nomeSubcategoria){
		this.nomeSubcategoria = nomeSubcategoria;
	}

    public Subcategoria(String nomeSubcategoria) {
		this.nomeSubcategoria = nomeSubcategoria;
	}
}