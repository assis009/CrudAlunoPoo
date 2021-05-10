package dominio;

import java.util.ArrayList;
import java.util.List;

public class AlunoControl {

	
	private List<Aluno> listaAlunos = new ArrayList<>();
	
	
	public void adicionar(Aluno a) {
		
		listaAlunos.add(a);
	}
	
	public Aluno pesquisar(String nome) {
		
		for(Aluno a: listaAlunos) {
			
			if(a.getNome().contains(nome)) {
				
				return a;
			}
			
		}
		return null;
	}
}
