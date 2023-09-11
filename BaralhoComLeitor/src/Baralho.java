
import java.util.Collections; 
import java.util.Iterator;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader; 

@SuppressWarnings("serial")
public class Baralho extends LinkedList<Carta>{
	private String tema;

	public Baralho(String tema) {
		this.setTema(tema);
	}

	public void carregar() {
		
	    String nomeDoArquivo = "";
	    
		switch (this.tema) {
			case "Aviões":
				nomeDoArquivo = "aviões.csv";
				break;
			case "Carros":
				nomeDoArquivo = "carros.csv";
				break;
			case "Heróis":
				nomeDoArquivo = "herois.csv";
				break;
			case "Dinossauros":
				nomeDoArquivo = "dinossauros.csv";
				break;
		}
		
		InputStream fluxo = null;
		InputStreamReader leitor = null;
		BufferedReader leitorComBuffer = null;
		
	    try {  
		    fluxo = new FileInputStream(nomeDoArquivo);
	    	leitor = new InputStreamReader(fluxo);
	    	leitorComBuffer = new BufferedReader(leitor);  
	    	
			String linha = "";  
		    
		 
		    linha = leitorComBuffer.readLine(); 
		    linha = leitorComBuffer.readLine();
		    while ( linha != null ) { 
			    String[] dadosDaCarta = linha.split(","); 
			    this.add(new Carta(dadosDaCarta[0], dadosDaCarta[1]));
			    linha = leitorComBuffer.readLine(); 
		    }  
		    
	    } catch (FileNotFoundException fnfe) {  
	    	fnfe.printStackTrace();  
	    } catch (IOException ioe) {  
	    	ioe.printStackTrace();  
	    } finally {
	    	try {
	    		leitorComBuffer.close();
	    		leitor.close();
	    		fluxo.close();
	    	} catch (IOException ioe) {
	    		ioe.printStackTrace();
	    	}
	    }
	}
	

	public void embaralhar() {
		Collections.shuffle(this);
	}
	

	public void distribuir(Jogador[] jogadores) {
		int jogador = 0; 
		

		Iterator<Carta> iterador = this.iterator();
		
	
		while ( iterador.hasNext() ) {
			

			if (jogadores[jogador].getMonte() == null)
				jogadores[jogador].setMonte( new Baralho(this.tema) );
			

			jogadores[jogador].getMonte().add(iterador.next());
			iterador.remove();


		
			jogador = (jogador + 1) % jogadores.length;  
		}
	}
	
	public Carta pegarDoTopo() {
		if ( this.peekLast() != null )
			return this.pollLast();
		
		System.out.println("O monte não tem mais cartas!");
		return null;
	}
	

	public void listarCartas() {
		System.out.println(this);
	}
	

	public String getTema() {
		return tema;
	}

	private void setTema(String tema) {
		this.tema = tema;
	}
	
}
