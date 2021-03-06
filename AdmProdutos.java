//imports para criar a listagem e ler o teclado
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class AdmProdutos {

	private Scanner l;
	private boolean execute;
	public List<Temperos> temperos;
	public List<Graos> graos;
	
	//main
	public static void main(String[] args) { 
		new AdmProdutos();
	}

	//construtor
	private AdmProdutos() { 
		l = new Scanner(System.in);
		execute = true;
		temperos = new ArrayList<Temperos>();
        graos = new ArrayList<Graos>();

		System.out.println("-----BEM VINDO AO CADASTRO DE PRODUTOS-----");

		while (execute) {
			String opcao = menu();

			//verifica a tecla digitada
			if (opcao.equalsIgnoreCase("g")) { //se apertar g no menu -> cadastra um novo grao
				cadastrar_grao();
			}
			if(opcao.equalsIgnoreCase("t")){ //se apertar t no menu -> cadastra um novo tempero
				cadastrar_tempero();
			}
            else if (opcao.equalsIgnoreCase("l")) { //se apertar l no menu -> lista os temperos ou os graos 
				String opcao_listar = menu_listar();
				if(opcao_listar.equalsIgnoreCase("g")){
					listarCadastrosGraos();
				}
				else if (opcao_listar.equalsIgnoreCase("t")){
					listarCadastrosTempero();
				}
				else {
					System.out.println("\n---Opção Inválida!---\n");
				}
			}

            else if (opcao.equalsIgnoreCase("x")) { //se apertar x no menu -> sai do menu
				execute = false;
			} 
            else if (opcao.equalsIgnoreCase("b")) { //se apertar b no menu -> busca um grao ou um tempero
				String opcao_busca = menu_busca();
				if(opcao_busca.equalsIgnoreCase("g")){
					String opcao_busca_input = menu_busca_input_graos();
					String opcao_busca_output = menu_busca_output_graos();
					String input;
					if(opcao_busca_input.equalsIgnoreCase("c")){
						System.out.println("----------------");
						input = textInput("Insira o código que deseja procurar:");
						if(opcao_busca_output.equalsIgnoreCase("n")){
							buscaEspecificaGrao("codigo", "nome", input);
						}
						else if(opcao_busca_output.equalsIgnoreCase("t")){
							buscaEspecificaGrao("codigo", "tipo", input);
						}
						else if(opcao_busca_output.equalsIgnoreCase("e")){
							buscaEspecificaGrao("codigo", "estoque", input);
						}
						else if(opcao_busca_output.equalsIgnoreCase("v")){
							buscaEspecificaGrao("codigo", "validade", input);
						}
						else if(opcao_busca_output.equalsIgnoreCase("p")){
							buscaEspecificaGrao("codigo", "preco", input);
						}
					}
					else if(opcao_busca_input.equalsIgnoreCase("n")){
						System.out.println("----------------");
						input = textInput("Insira o nome que deseja procurar:");
						if(opcao_busca_output.equalsIgnoreCase("c")){
							buscaEspecificaGrao("nome", "codigo", input);
						}
						else if(opcao_busca_output.equalsIgnoreCase("t")){
							buscaEspecificaGrao("nome", "tipo", input);
						}
						else if(opcao_busca_output.equalsIgnoreCase("e")){
							buscaEspecificaGrao("nome", "estoque", input);
						}
						else if(opcao_busca_output.equalsIgnoreCase("v")){
							buscaEspecificaGrao("nome", "validade", input);
						}
						else if(opcao_busca_output.equalsIgnoreCase("p")){
							buscaEspecificaGrao("nome", "preco", input);
						}
					}
                	else {
                    	System.out.println("\n---Opção Inválida!---\n");
                	}
				}
				else if(opcao_busca.equalsIgnoreCase("t")){
					String opcao_busca_input = menu_busca_input_tempero();
					String opcao_busca_output = menu_busca_output_tempero();
					String input;
					if(opcao_busca_input.equalsIgnoreCase("c")){
						System.out.println("----------------");
						input = textInput("Insira o código que deseja procurar:");
						if(opcao_busca_output.equalsIgnoreCase("n")){
							buscaEspecificaTempero("codigo", "nome", input);
						}
						else if(opcao_busca_output.equalsIgnoreCase("t")){
							buscaEspecificaTempero("codigo", "tipo", input);
						}
						else if(opcao_busca_output.equalsIgnoreCase("p")){
							buscaEspecificaTempero("codigo", "potes", input);
						}
						else if(opcao_busca_output.equalsIgnoreCase("v")){
							buscaEspecificaTempero("codigo", "validade", input);
						}
						else if(opcao_busca_output.equalsIgnoreCase("o")){
							buscaEspecificaTempero("codigo", "preco", input);
						}
					}
					else if(opcao_busca_input.equalsIgnoreCase("n")){
						System.out.println("----------------");
						input = textInput("Insira o nome que deseja procurar:");
						if(opcao_busca_output.equalsIgnoreCase("c")){
							buscaEspecificaTempero("nome", "codigo", input);
						}
						else if(opcao_busca_output.equalsIgnoreCase("t")){
							buscaEspecificaTempero("nome", "tipo", input);
						}
						else if(opcao_busca_output.equalsIgnoreCase("p")){
							buscaEspecificaTempero("nome", "potes", input);
						}
						else if(opcao_busca_output.equalsIgnoreCase("v")){
							buscaEspecificaTempero("nome", "validade", input);
						}
						else if(opcao_busca_output.equalsIgnoreCase("o")){
							buscaEspecificaTempero("nome", "preco", input);
						}
					}
                	else {
                    	System.out.println("\n---Opção Inválida!---\n");
                	}
           		}
            	else {
					System.out.println("\n---Opção Inválida!---\n");
				}
			}
		}
	}

	private String menu() { //menu principal
		System.out.println("Selecione a opção:");
		System.out.println("G - Novo grao");
		System.out.println("T - Novo tempero");
		System.out.println("L - Listar produtos");
        System.out.println("B - Busca especifica");
		System.out.println("X - Sair");
		return l.nextLine();
	}

	private String menu_listar(){ //menu listar
		System.out.println("Deseja listar: ");
		System.out.println("G - Grao");
		System.out.println("T - Tempero");
		return l.nextLine();
	}


	private String menu_busca(){ //menu buscar grao ou tempero
		System.out.println("Deseja buscar: ");
		System.out.println("G - Grao");
		System.out.println("T - Tempero");
		return l.nextLine();
	}

    private String menu_busca_input_tempero(){ //menu buscar pelo codigo ou nome 
        System.out.println("Buscar o tempero por meio do:");
		System.out.println("C - Codigo");
		System.out.println("N - Nome");
        return l.nextLine();
    }

    private String menu_busca_output_tempero(){ //menu buscar qual informação?
        System.out.println("Informação que deseja obter do tempero: ");
		System.out.println("C - Codigo");
		System.out.println("N - Nome");
        System.out.println("T - Tipo");
        System.out.println("V - Data de Validade");
        System.out.println("O - Preco por pote");
        System.out.println("P - Número de potes");
        return l.nextLine();
    }
	
	private String menu_busca_input_graos(){ //menu buscar pelo codigo ou nome
        System.out.println("Busca o graos por meio do");
		System.out.println("C - Codigo");
		System.out.println("N - Nome");
        return l.nextLine();
    }

    private String menu_busca_output_graos(){ //menu buscar qual informação?
        System.out.println("Informação que deseja obter do grao: ");
		System.out.println("C - Codigo");
		System.out.println("N - Nome");
        System.out.println("T - Tipo");
        System.out.println("V - Data de Validade");
        System.out.println("P - Preco por quilo");
        System.out.println("E - Estoque disponível (em gramas)");
        return l.nextLine();
    }

	private void cadastrar_tempero() { //cadastro dos temperos
		boolean cadastrando = true;

		while (cadastrando) {
			System.out.println("---Cadastro de Temperos---");
			Temperos t = new Temperos(); //cria um novo objeto tempero
			t.setNome(textInput("Nome:"));
			t.setTipo(textInput("Tipo:"));
			t.setValidade(textInput("Validade:"));
			t.setNumeroDePotes(Integer.parseInt(textInput("Quantidade de potes:")));
            t.setCodigo(textInput("Código de identificação: "));
			t.setPrecoPorPote(Integer.parseInt(textInput("Preço por pote: ")));
			
			String cadastrar = textInput("Adicionar cadastro (S:Sim/N:Nao) ?");
			if (cadastrar.equalsIgnoreCase("s")) { //se digitar s cadastra o tempero
				System.out.println("---Cadastro adicionado!---");
				temperos.add(t);
			} else if (cadastrar.equalsIgnoreCase("n")){
				System.out.println("---Cadastro ignorado!---");
			} else {
				System.out.println("\n---Opção inválida---\n");
			}

			String continua = textInput("Continuar cadastrando (S:Sim/N:Nao) ?");
			if (continua.equalsIgnoreCase("N")) {
				cadastrando = false;
			} else if (continua.equalsIgnoreCase("s")){
				// se for s sai do if e volta para o inicio do while
			} else {
				System.out.println("\n---Opção inválida---\n");
				cadastrando = false;
			}
		}
	}

	private void cadastrar_grao() {
		boolean cadastrando = true;

		while (cadastrando) {
			System.out.println("---Cadastro de Graos---");
			Graos g = new Graos(); //cria um novo objeto grao
			g.setNome(textInput("Nome:"));
			g.setTipo(textInput("Tipo:"));
			g.setValidade(textInput("Validade:"));
			g.setQuantidade(Integer.parseInt(textInput("Quantidade disponível em estoque (gramas):")));
            g.setCodigo(textInput("Código de identificação: "));
			g.setPrecoKg(Integer.parseInt(textInput("Preço por quilo: ")));
			
			String cadastrar = textInput("Adicionar cadastro (S:Sim/N:Nao) ?");
			if (cadastrar.equalsIgnoreCase("s")) { //se digitar s adiciona o cadastro
				System.out.println("---Cadastro adicionado!---");
				graos.add(g);
			} else if (cadastrar.equalsIgnoreCase("n")){
				System.out.println("---Cadastro ignorado!---");
			} else {
				System.out.println("\n---Opção inválida---\n");
			}

			String continua = textInput("Continuar cadastrando (S:Sim/N:Nao) ?");
			if (continua.equalsIgnoreCase("N")) {
				cadastrando = false;
			} else if (continua.equalsIgnoreCase("s")){
				// se for s sai do if e volta para o inicio do while
			} else {
				System.out.println("\n---Opção inválida---\n");
				cadastrando = false; 
			}
		}
	}

    private void buscaEspecificaTempero(String tipo_input, String tipo_output, String input){
        String code;
        if (temperos.size() == 0) {
			System.out.println("\n---Não existem cadastros!---\n");
		} 
        else {
			for (int i = 0; i < temperos.size(); i++) {
                if(tipo_input.equals("codigo")){ 
                    code = temperos.get(i).getCodigo();
					System.out.println("-----------------------");
                    if (code.equals(input)){
                        if(tipo_output.equals("nome")){
                            System.out.println("Nome do produto com o codigo " + code + ":\n" + temperos.get(i).getNome());
                        }
                        else if(tipo_output.equals("tipo")){
                            System.out.println("Tipo do produto com o codigo " + code + ":\n" + temperos.get(i).getTipo());
                        }
                        else if(tipo_output.equals("potes")){
                            System.out.println("Numero de potes do produto com o codigo " + code + ":\n" + temperos.get(i).getNumeroDePotes());
                        }
                        else if(tipo_output.equals("validade")){
                            System.out.println("Validade do produto com o codigo " + code + ":\n"  + temperos.get(i).getValidade());
                        }
                         else if(tipo_output.equals("preco")){
                            System.out.println("Preco por pote com o codigo " + code + ":\n"  + temperos.get(i).getPrecoPorPote());
                         }
                    }
                }

                else if(tipo_input.equals("nome")){
                    code = temperos.get(i).getNome();
					System.out.println("-----------------------");
                    if (code.equals(input)){
                        if(tipo_output.equals("codigo")){
                            System.out.println("Codigo do produto com o nome " + code + ":\n" + temperos.get(i).getCodigo());
                        }
                        else if(tipo_output.equals("tipo")){
                            System.out.println("Tipo do produto com o nome " + code + ":\n" + temperos.get(i).getTipo());
                        }
                        else if(tipo_output.equals("potes")){
                            System.out.println("Numero de potes do produto com o nome " + code + ":\n" + temperos.get(i).getNumeroDePotes());
                        }
                        else if(tipo_output.equals("validade")){
                            System.out.println("Validade do produto com o nome " + code + ":\n"  + temperos.get(i).getValidade());
                        }
                         else if(tipo_output.equals("preco")){
                            System.out.println("Preco do produto por pote com o nome " + code + ":\n"  + temperos.get(i).getPrecoPorPote());
                         }
                    }

                }
        
            }
        }
    }

	private void buscaEspecificaGrao(String tipo_input, String tipo_output, String input){
        String code;
        if (graos.size() == 0) {
			System.out.println("\n---Não existem cadastros!---\n");
		} 
        else {
			for (int i = 0; i < graos.size(); i++) {
                if(tipo_input.equals("codigo")){
                    code = graos.get(i).getCodigo();
					System.out.println("-----------------------");
                    if (code.equals(input)){
                        if(tipo_output.equals("nome")){
                            System.out.println("Nome do produto com o codigo " + code + ":\n" + graos.get(i).getNome());
                        }
                        else if(tipo_output.equals("tipo")){
                            System.out.println("Tipo do produto com o codigo " + code + ":\n" + graos.get(i).getTipo());
                        }
                        else if(tipo_output.equals("estoque")){
                            System.out.println("Estoque disponivel do produto com o codigo " + code + ":\n" + graos.get(i).getQuantidade());
                        }
                        else if(tipo_output.equals("validade")){
                            System.out.println("Validade do produto com o codigo " + code + ":\n"  + graos.get(i).getValidade());
                        }
                         else if(tipo_output.equals("preco")){
                            System.out.println("Preco por quilo do produto com o codigo " + code + ":\n"  + graos.get(i).getPrecoKg());
                         }
                    }
                }

                else if(tipo_input.equals("nome")){
                    code = graos.get(i).getNome();
					System.out.println("-----------------------");
                    if (code.equals(input)){
                        if(tipo_output.equals("codigo")){
                            System.out.println("Codigo do produto com o nome " + code + ":\n" + graos.get(i).getCodigo());
                        }
                        else if(tipo_output.equals("tipo")){
                            System.out.println("Tipo do produto com o nome " + code + ":\n" + graos.get(i).getTipo());
                        }
                        else if(tipo_output.equals("estoque")){
                            System.out.println("Estoque disponivel do produto com o nome " + code + ":\n" + graos.get(i).getQuantidade());
                        }
                        else if(tipo_output.equals("validade")){
                            System.out.println("Validade do produto com o nome " + code + ":\n"  + graos.get(i).getValidade());
                        }
                         else if(tipo_output.equals("preco")){
                            System.out.println("Preco por quilo do produto com o nome " + code + ":\n"  + graos.get(i).getPrecoKg());
                         }
                    }
                }
            }
        }
    }

	private void listarCadastrosGraos() {
		if (graos.size() == 0) {
			System.out.println("\n---Não existem cadastros!---\n");
		} else {
			System.out.println("\n---Lista de Graos---\n");
			for (int i = 0; i < graos.size(); i++) {
				Graos g = graos.get(i);
				System.out.println("Nome do grão: " + g.getNome());
				System.out.println("Tipo do grão: " + g.getTipo());
				System.out.println("Data de Validade: " + g.getValidade());
				System.out.println("Quantidade disponível em estoque (gramas): " + g.getQuantidade());
				System.out.println("Código de identificação: " + g.getCodigo());
				System.out.println("Preço por quilo: " + g.getPrecoKg() + "\n");
                }
		}
        System.out.println("\n-------------\n");
	}

	private void listarCadastrosTempero() {
		if (temperos.size() == 0) {
			System.out.println("\n---Não existem cadastros!---\n");
		} else {
			System.out.println("\n---Lista de Temperos---\n");
			for (int i = 0; i < temperos.size(); i++) {
				Temperos t = temperos.get(i);
				System.out.println("Nome do Tempero: " + t.getNome());
				System.out.println("Tipo do Tempero: " + t.getTipo());
				System.out.println("Data de Validade: " + t.getValidade());
				System.out.println("Quantidade de potes: " + t.getNumeroDePotes());
				System.out.println("Código de identificação: " + t.getCodigo());
				System.out.println("Preço por pote: " + t.getPrecoPorPote() + "\n");
                }
		}
        System.out.println("\n-------------\n");
	}

	private String textInput(String label) {
		System.out.println(label);
		return l.nextLine();
	}
}
class Produtos { //classe mãe
	// atributos
	private String nome;
	private String tipo;
    private String codigo;
	private String validade;

	// construtor
	public Produtos (){ 
	}
	
	//cadastra a instância do objeto 
	public String getNome(){ 
		return nome;
	}
	
	void setNome(String nome){
		this.nome = nome; 
	}
	
	public String getTipo(){
		return tipo;
	}
	
	void setTipo(String tipo){
		this.tipo = tipo; 
	}
	
	public String getValidade(){
		return validade;
	}

	void setValidade(String validade){
		this.validade = validade; 
	}
	
	public String getCodigo(){
		return codigo;
	}
	
	void setCodigo(String codigo){
		this.codigo = codigo;
	}
	
}
class Temperos extends Produtos{ //classe filha de produtos 
    // atributos
    private int numeroDePotes;
    private int precoPorPote;

	//construtor
    public Temperos(){ 
    }

	public int getPrecoPorPote(){
        return precoPorPote;
    }
    
    void setPrecoPorPote(int precoPorPote){
        this.precoPorPote = precoPorPote;
    }

    public int getNumeroDePotes(){
        return numeroDePotes;
    }
    
    void setNumeroDePotes(int numeroDePotes){
        this.numeroDePotes = numeroDePotes;
    }
}

class Graos extends Produtos{ //classe filha de produtos 
	private float quantidade;
	private int precoKg;

	//construtor
	public Graos(){
    } 

	public int getPrecoKg(){
		return precoKg;
	}
	
	void setPrecoKg(int precoKg){
		this.precoKg = precoKg;
	}

	public float getQuantidade(){
		return quantidade;
	}
	
	void setQuantidade(float quantidade){
		this.quantidade = quantidade; 
	}


}

