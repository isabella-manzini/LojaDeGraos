import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import static javax.swing.JOptionPane.showMessageDialog;

/**	Painel para fazer insercao de dados
*/
public class AdmProdutosInterface { 

	public List<Temperos> temperos;
	public List<Graos> graos;

	public static void main( String args[] ){         
		new AdmProdutosInterface();
	}

	private AdmProdutosInterface(){
		temperos = new ArrayList<Temperos>();
        graos = new ArrayList<Graos>();
		new JanelaPrincipal();
	}

	class PainelInicial extends Panel {

		//private Label label;
		private TextArea areaTexto;
		private Button listar;
		private Button validade;
		private Checkbox campo1;
		private Checkbox campo2;

		
		// Classe interna para receber eventos dos botoes da parte norte
		class EscutaBotaoListar implements ActionListener{
			public void actionPerformed( ActionEvent e) {
				if(campo1.getState() == false && campo2.getState() == false){
					showMessageDialog(null, "Selecione 1 checkbox (Grãos ou Temperos)");}

				else if(campo1.getState()){
					if (graos.size() == 0){
						showMessageDialog(null, "\n---Não existem cadastros!---\n");}
					else {
						areaTexto.append("\n---LISTAGEM DOS GRÃOS---\n");	
						for (int i = 0; i < graos.size(); i++) {
							Graos g = graos.get(i);
							areaTexto.append("\n- Nome: " + g.getNome() +"\n");
							areaTexto.append("- Tipo: " + g.getTipo() +"\n");	
							areaTexto.append("- Validade: " + g.getValidade() +"\n");	
							areaTexto.append("- Quantidade (kg): " + g.getQuantidade() +"\n");
							areaTexto.append("- Código: " + g.getCodigo() +"\n");	
							areaTexto.append("- Preco por kg: " + g.getPrecoKg() +"\n");
						}	
					}
				}

				else if(campo2.getState()){
					if (temperos.size() == 0){
						showMessageDialog(null, "\n---Não existem cadastros!---\n");}
					else{
						areaTexto.append("\n---LISTAGEM DOS TEMPEROS---\n");
						for (int i = 0; i < temperos.size(); i++) {
							Temperos t = temperos.get(i);
							areaTexto.append("\n- Nome: " + t.getNome() +"\n");
							areaTexto.append("- Tipo: " + t.getTipo() +"\n");	
							areaTexto.append("- Validade: " + t.getValidade() +"\n");	
							areaTexto.append("- Numero de potes: " + t.getNumeroDePotes() +"\n");
							areaTexto.append("- Código: " + t.getCodigo() +"\n");	
							areaTexto.append("- Preço por pote: " + t.getPrecoPorPote() +"\n");
						}
					}
				}		
			}	
		}

		// Classe interna para receber eventos dos botoes da parte norte
		class EscutaBotaoValidade implements ActionListener{
			public void actionPerformed( ActionEvent e) {
				String data;
				if(campo1.getState() == false && campo2.getState() == false){
					showMessageDialog(null, "Selecione 1 checkbox (Grãos ou Temperos)");}

				else if(campo1.getState()){
					areaTexto.append("\nPROMOÇÃO DE GRÃOS\n");
					for (int i = 0; i < graos.size(); i++) {
						data = graos.get(i).getValidade();
						if(data.equals("25-09-21")){
							areaTexto.append("Nome: " + graos.get(i).getNome()+"\n");
						}	
					}
				}
				
				else if(campo2.getState()){
					areaTexto.append("\nPROMOÇÃO DE TEMPEROS\n");
					for (int i = 0; i < temperos.size(); i++) {
						data = temperos.get(i).getValidade();
						if(data.equals("25-09-21")){
							areaTexto.append("Nome: " + temperos.get(i).getNome()+"\n");
						}	
					}
				}
			}
		} 
				

		
		//Classe interna para lidar com uma checkbox
		class EscutaCheckbox implements ItemListener {
			public void itemStateChanged( ItemEvent e) {
				if (campo1.getState() && campo2.getState())
					showMessageDialog(null, "Selecione apenas 1 opção");
			}
		}
		
		PainelInicial() {	
			// Define o layout manager deste painel
			GridLayout gl = new GridLayout(3,2);
			this.setLayout( gl );

			Panel texto = new Panel();
			texto.setLayout( new GridLayout(1,1) );

			// Cria uma area para colocar texto
			areaTexto = new TextArea("                                                                                                             ---LOJA GRANEIRA--- \n");
			areaTexto.setEditable(false);
			this.add( areaTexto );

			//Cadastra painel dos botoes
			Panel botoesPanel = new Panel();
			botoesPanel.setLayout( new GridLayout(1,2) );

			listar = new Button("Listar");
			botoesPanel.add( listar );
			EscutaBotaoListar eb = new EscutaBotaoListar();
			listar.addActionListener(eb);

			validade = new Button("Promoção");
			botoesPanel.add( validade );
			EscutaBotaoValidade eb2 = new EscutaBotaoValidade();
			validade.addActionListener(eb2);
			
			//Cadastra painel das checkbox
			Panel checkboxPanel = new Panel();
			checkboxPanel.setLayout( new GridLayout(1,2) );

			//checkbox.add( new Label("Graos:") );
			campo1 = new Checkbox("Graos", false);
			EscutaCheckbox gr = new EscutaCheckbox();
			campo1.addItemListener( gr );

			checkboxPanel.add( campo1 );

			//checkbox.add( new Label("Temperos:") );
			campo2 = new Checkbox("Temperos", false);
			EscutaCheckbox tp = new EscutaCheckbox();
			campo2.addItemListener( tp );
			checkboxPanel.add( campo2 );
		
			this.add( checkboxPanel);
			this.add( botoesPanel);
		}

	}

	class PainelCadastro extends Panel {

		private Button botaoCadastrar;
		private Checkbox campo1;
		private Checkbox campo2;
		private TextField campo3;
		private TextField campo4;
		private TextField campo5;
		private TextField campo6;
		private TextField campo7;
		private TextField campo8;

			//Classe interna para lidar com uma checkbox
		class EscutaCheckbox implements ItemListener {
			int produto;
			public void itemStateChanged( ItemEvent e) {
				if (campo1.getState() && campo2.getState()){
					showMessageDialog(null, "Selecione apenas 1 opção");}
			}
		}

		class EscutaBotaoCadastrar implements ActionListener{
			public void actionPerformed( ActionEvent e) {
				if(campo1.getState() == false && campo2.getState() == false){
					showMessageDialog(null, "Selecione apenas 1 checkbox (Grãos ou Temperos");}

				else if(campo1.getState()){
					Graos g = new Graos();
					g.setNome(campo3.getText());
					g.setTipo(campo4.getText());
					g.setCodigo(campo5.getText());
					g.setValidade(campo6.getText());
					g.setPrecoKg(Integer.parseInt(campo7.getText()));
					g.setQuantidade(Integer.parseInt(campo8.getText()));
					graos.add(g);
					showMessageDialog(null, "Cadastro do grão " + g.getNome() + " realizado com sucesso");}

				else if(campo2.getState()){
					Temperos t = new Temperos();
					t.setNome(campo3.getText());
					t.setTipo(campo4.getText());
					t.setCodigo(campo5.getText());
					t.setValidade(campo6.getText());
					t.setPrecoPorPote(Integer.parseInt(campo7.getText()));
					t.setNumeroDePotes(Integer.parseInt(campo8.getText()));
					temperos.add(t);
					showMessageDialog(null, "Cadastro do Tempero " + campo3.getText() + " realizado com sucesso");}
				//chamar o metodo de listagem
			}
		} 


		PainelCadastro(){
			// Define o layout manager deste painel
			GridLayout gl = new GridLayout(3,2);
			this.setLayout( gl );

			Panel checkboxPanel = new Panel();
			checkboxPanel.setLayout( new GridLayout(1,2) );

			//checkbox.add( new Label("Graos:") );
			campo1 = new Checkbox("Graos", false);
			EscutaCheckbox gr = new EscutaCheckbox();
			campo1.addItemListener( gr );
			checkboxPanel.add( campo1 );

			//checkbox.add( new Label("Temperos:") );
			campo2 = new Checkbox("Temperos", false);
			EscutaCheckbox tp = new EscutaCheckbox();
			campo2.addItemListener( tp );
			checkboxPanel.add( campo2 );

			Panel caixaPanel = new Panel();
			caixaPanel.setLayout( new GridLayout(7,2) );

			caixaPanel.add( new Label("Nome:") );
			campo3 = new TextField();
			caixaPanel.add( campo3 );

			caixaPanel.add( new Label("Tipo:") );
			campo4 = new TextField();
			caixaPanel.add( campo4 );

			caixaPanel.add( new Label("Código:") );
			campo5 = new TextField();
			caixaPanel.add( campo5 );

			caixaPanel.add( new Label("Data de Validade:") );
			campo6 = new TextField();
			caixaPanel.add( campo6 );

			caixaPanel.add( new Label("Preço (por pote/ por Kg):") );
			campo7 = new TextField();
			caixaPanel.add( campo7 );

			caixaPanel.add( new Label("Quantidade a ser cadastrada:") );
			campo8 = new TextField();
			caixaPanel.add( campo8 );

			Panel botaoPanel = new Panel();
			botaoPanel.setLayout( new GridLayout(1,2) );

			botaoCadastrar = new Button("Cadastrar");
			botaoPanel.add( botaoCadastrar );
			EscutaBotaoCadastrar eb2 = new EscutaBotaoCadastrar();
			botaoCadastrar.addActionListener(eb2);

			this.add( checkboxPanel);
			this.add( caixaPanel);
			this.add( botaoPanel);

		}
	}

	class PainelBuscar extends Panel {

		private Checkbox campo1;
		private Checkbox campo2;
		private Choice campo3;
		private Choice campo4;
		private Button botaoBuscar;	
		private TextField campo_input;

		class EscutaCheckbox implements ItemListener {
			public void itemStateChanged( ItemEvent e) {
				if (campo1.getState() && campo2.getState()){
					showMessageDialog(null, "Selecione apenas 1 opção");}
			}
		}

		class EscutaBotao implements ActionListener{
			public void actionPerformed( ActionEvent e) {
				String code;
				String type_input = campo3.getSelectedItem();
				String type_output = campo4.getSelectedItem();
				String text_input = campo_input.getText();

				if (campo1.getState() && campo2.getState()){
					showMessageDialog(null, "Selecione apenas 1 opção");}

				else if( campo1.getState() ){
					for (int i = 0; i < graos.size(); i++) {
						if(type_input.equals("Código")){
							code = graos.get(i).getCodigo();
							if (code.equals(text_input)){
								if(type_output.equals("Nome")){
									showMessageDialog(null, "O nome do produto com o codigo " + code + " é:  " + graos.get(i).getNome());
								}
								else if(type_output.equals("Tipo")){
									showMessageDialog(null, "O tipo do produto com o codigo " + code + " é:  " + graos.get(i).getTipo());
								}
								else if(type_output.equals("Quantidade")){
									showMessageDialog(null, "O estoque disponivel do produto com o codigo " + code + " é:  " + graos.get(i).getQuantidade());
								}
								else if(type_output.equals("Data de validade")){
									showMessageDialog(null, "A data de validade do produto com o codigo " + code + " é:  " + graos.get(i).getValidade());
								}
								 else if(type_output.equals("Preço")){
									showMessageDialog(null, "O preço por quilo do produto com o codigo " + code + " é: "  + graos.get(i).getPrecoKg());
								 }
							}
						}

						else if(type_input.equals("Nome")){
							code = graos.get(i).getNome();
							if (code.equals(text_input)){
								if(type_output.equals("Código")){
									showMessageDialog(null, "O código do produto com nome " + code + " é:  " + graos.get(i).getCodigo());
								}
								else if(type_output.equals("Tipo")){
									showMessageDialog(null, "O tipo do produto com nome " + code + " é: " + graos.get(i).getTipo());
								}
								else if(type_output.equals("Quantidade")){
									showMessageDialog(null, "O estoque disponivel do produto com nome " + code + " é:  " + graos.get(i).getQuantidade());
								}
								else if(type_output.equals("Data de validade")){
									showMessageDialog(null, "A data de validade do produto com nome " + code + " é:  " + graos.get(i).getValidade());
								}
								 else if(type_output.equals("Preço")){
									showMessageDialog(null, "O preço por quilo do produto com nome " + code + " é:  " + graos.get(i).getPrecoKg());
								 }
							}
						}
					}
				}	

				else if ( campo2.getState() ){
					for (int j = 0; j < temperos.size(); j++) {
						if(type_input.equals("Código")){
							code = temperos.get(j).getCodigo();
							if (code.equals(text_input)){
								if(type_output.equals("Nome")){
									showMessageDialog(null, "O nome do produto com o codigo " + code + " é: " + temperos.get(j).getNome());
								}
								else if(type_output.equals("Tipo")){
									showMessageDialog(null, "O tipo do produto com o codigo " + code + " é: " + temperos.get(j).getTipo());
								}
								else if(type_output.equals("Quantidade")){
									showMessageDialog(null, "O estoque disponivel do produto com o codigo " + code + " é: " + temperos.get(j).getNumeroDePotes());
								}
								else if(type_output.equals("Data de validade")){
									showMessageDialog(null, "A data de validade do produto com o codigo " + code + " é: " + temperos.get(j).getValidade());
								}
								 else if(type_output.equals("Preço")){
									showMessageDialog(null, "O preço por quilo do produto com o codigo " + code + " é: " + temperos.get(j).getPrecoPorPote());
								 }
							}
						}

						if(type_input.equals("Nome")){
							code = temperos.get(j).getNome();
							if (code.equals(text_input)){
								if(type_output.equals("Código")){
									showMessageDialog(null, "O código do produto com nome " + code + " é: " + temperos.get(j).getCodigo());
								}
								else if(type_output.equals("Tipo")){
									showMessageDialog(null, "O tipo do produto com nome " + code + " é: " + temperos.get(j).getTipo());
								}
								else if(type_output.equals("Quantidade")){
									showMessageDialog(null, "O estoque disponivel do produto com nome " + code + " é: " + temperos.get(j).getNumeroDePotes());
								}
								else if(type_output.equals("Data de validade")){
									showMessageDialog(null, "A data de validade do produto com nome " + code + " é: " + temperos.get(j).getValidade());
								}
								 else if(type_output.equals("Preço")){
									showMessageDialog(null, "O preço por quilo do produto com nome " + code + " é: " + temperos.get(j).getPrecoPorPote());
								 }
							}
						}
					}
				}
			}
		}

		public PainelBuscar() {
			// Define o layout manager geral
			GridLayout gl = new GridLayout(4,2);
			this.setLayout( gl );

			Panel checkboxPanel = new Panel();
			checkboxPanel.setLayout( new GridLayout(1,2) );

			//checkbox.add( new Label("Graos:") );
			campo1 = new Checkbox("Graos", false);
			EscutaCheckbox gr = new EscutaCheckbox();
			campo1.addItemListener( gr );
			checkboxPanel.add( campo1 );

			//checkbox.add( new Label("Temperos:") );
			campo2 = new Checkbox("Temperos", false);
			EscutaCheckbox tp = new EscutaCheckbox();
			campo2.addItemListener( tp );
			checkboxPanel.add( campo2 );

			Panel escolha = new Panel();
			escolha.setLayout( new GridLayout(1,2));

			escolha.add( new Label("Deseja buscar por meio do:") );
			campo3 = new Choice();
			campo3.addItem("Nome");
			campo3.addItem("Código");
			escolha.add(campo3);

			campo_input = new TextField();
			escolha.add( campo_input );
			
			Panel caixaPanel = new Panel();
			caixaPanel.setLayout( new GridLayout(1,1));

			caixaPanel.add( new Label("Deseja buscar qual informação:") );
			campo4 = new Choice();
			campo4.addItem("Nome");
			campo4.addItem("Código");
			campo4.addItem("Tipo");
			campo4.addItem("Data de validade");
			campo4.addItem("Preço");
			campo4.addItem("Quantidade");
			caixaPanel.add(campo4);

			Panel botaoPanel = new Panel();
			botaoPanel.setLayout( new GridLayout(1,1) );

			botaoBuscar = new Button("Buscar");
			botaoPanel.add( botaoBuscar );
			EscutaBotao eb2 = new EscutaBotao();
			botaoBuscar.addActionListener(eb2);

			this.add( checkboxPanel);
			this.add( escolha);
			this.add( caixaPanel);
			this.add( botaoPanel);

		}
	}

	class PainelExcluir extends Panel {

        /* private Button excluir; */
        private Checkbox campo1;
        private Checkbox campo2;
        private TextField campo_excluir;
        private Choice campo3;
        private String text_excluir;
        private Button botao_excluir;
        private String type_excluir;


        class EscutaCheckbox implements ItemListener { 
            public void itemStateChanged( ItemEvent e) {
                if (campo1.getState() && campo2.getState()){      
                showMessageDialog(null, "Selecione apenas 1 opção");}  
            }
        }

        class EscutaBotao implements ActionListener{
            public void actionPerformed( ActionEvent e) {
                type_excluir = campo3.getSelectedItem();
                text_excluir = campo_excluir.getText();

                if (campo1.getState() && campo2.getState()){
                    showMessageDialog(null, "Selecione apenas 1 opção");
                
                }

                else if( campo1.getState() ){
                    if (campo3.getSelectedItem().equals("Nome")){
                        for(int i = 0; i < graos.size(); i++){
                            if(graos.get(i).getNome().equals(text_excluir)){
                                graos.remove(i);
                            }
                        }
                    }

                    else if(campo3.getSelectedItem().equals("Código")){
                        for(int i = 0; i < graos.size(); i++){
                            if(graos.get(i).getCodigo().equals(text_excluir)){
                                graos.remove(i);
                            }
                        }
                    }
				showMessageDialog(null, "Excluiu o grão com " + type_excluir + " " + text_excluir + " com sucesso");             
                }

                else if ( campo2.getState() ){
                    if (campo3.getSelectedItem().equals("Nome")){
                        for(int i = 0; i < temperos.size(); i++){
                            if(temperos.get(i).getNome().equals(text_excluir)){
                                temperos.remove(i);
                            }
                        }
                    }

                    else if(campo3.getSelectedItem().equals("Código")){
                        for(int i = 0; i < temperos.size(); i++){
                            if(temperos.get(i).getCodigo().equals(text_excluir)){
                                temperos.remove(i);
                            }
                        }
                    } 
				showMessageDialog(null, "Excluiu o tempero com " + type_excluir + " " + text_excluir + "  com sucesso");
                }
            
            }
        }


        public PainelExcluir() {
            // Define o layout manager geral
            GridLayout gl = new GridLayout(3,2);
            this.setLayout( gl );

            Panel checkboxPanel = new Panel();
            checkboxPanel.setLayout( new GridLayout(1,2) );

            //checkbox.add( new Label("Graos:") );
            campo1 = new Checkbox("Graos", false);
            EscutaCheckbox gr = new EscutaCheckbox();
            campo1.addItemListener( gr );
            checkboxPanel.add( campo1 );

            //checkbox.add( new Label("Temperos:") );
            campo2 = new Checkbox("Temperos", false);
            EscutaCheckbox tp = new EscutaCheckbox();
            campo2.addItemListener( tp );
            checkboxPanel.add( campo2 );

            Panel excluirPanel = new Panel();
            excluirPanel.setLayout( new GridLayout(1,2) );

            excluirPanel.add( new Label("Deseja exluir por meio do:") );
            campo3 = new Choice();
            campo3.addItem("Nome");
            campo3.addItem("Código");
            excluirPanel.add(campo3);

            campo_excluir = new TextField();
            excluirPanel.add( campo_excluir );

            Panel botaoPanel = new Panel();
            botaoPanel.setLayout( new GridLayout(1,1) );

            botao_excluir = new Button("Excluir");
            botaoPanel.add( botao_excluir );
            EscutaBotao eb2 = new EscutaBotao();
            botao_excluir.addActionListener(eb2);

            this.add( checkboxPanel);
            this.add( excluirPanel);
            this.add( botaoPanel);

        }
    }

	/** Classe com a interface grafica de usuario
	*/
	class JanelaPrincipal extends Frame {

		// Parte sul
		Label labelSul;

		// Parte norte
		private Button botao1;
		private Button botao2;
		private Button botao3;
		private Button botao4;

		// Parte central
		private CardLayout layoutCentral;
		private Panel painelCentral;

		// Classe interna para receber eventos associados com a janela
		class EscutaJanela extends WindowAdapter{
			public void windowClosing(WindowEvent e){
				System.out.println("janela foi fechada, salvando dados ...");
				System.exit(0);            
				}    	
			}


		// Classe interna para receber eventos dos botoes da parte norte
		class EscutaBotao implements ActionListener{
			public void actionPerformed( ActionEvent e) {
			
				if( e.getSource() == botao1 ) {
					layoutCentral.show( painelCentral, "painel inicial" );
					labelSul.setText("Painel inicial selecionado");
				} else if( e.getSource() == botao2 ) {
					layoutCentral.show( painelCentral, "painel cadastro" );
					labelSul.setText("Painel cadastro selecionado");
				} else if( e.getSource() == botao3 ) {
					layoutCentral.show( painelCentral, "painel buscar" );
					labelSul.setText("Painel lista selecionado");
				} else if (e.getSource() == botao4){
					layoutCentral.show( painelCentral, "painel excluir" );
					labelSul.setText("Painel exluir selecionado");
				}
				else {
					System.out.println("Evento de quem ??\n");
				}
			}
		}



		// Inicializa o frame
		public JanelaPrincipal() {

			System.out.println("inicio de JanelaPrincipal");

			// Define o layout manager geral
			BorderLayout border = new BorderLayout();
			border.setHgap(6);
			border.setVgap(6);
			this.setLayout(border);


			// Cria um Label para ocupar a parte sul
			labelSul = new Label();
			labelSul.setFont( new Font("Monospaced", Font.PLAIN, 14) );
			labelSul.setAlignment(Label.CENTER);
			labelSul.setForeground(Color.green);
			labelSul.setText("Parte de baixo");


			// Cria um Panel de botoes para ocupar a parte norte 
			Panel painelNorte = new Panel();
			painelNorte.setLayout( new GridLayout(1,4) );

			EscutaBotao eb = new EscutaBotao();

			botao1 = new Button("Inicio");
			painelNorte.add( botao1 );
			botao1.addActionListener(eb);
		
			botao2 = new Button("Cadastro");
			painelNorte.add( botao2 );
			botao2.addActionListener(eb);

			botao3 = new Button("Buscar");
			painelNorte.add( botao3 );
			botao3.addActionListener(eb);

			botao4 = new Button("Excluir");
			painelNorte.add( botao4 );
			botao4.addActionListener(eb);


			// Cria um Panel com varios cartoes para ocupar a parte central

			layoutCentral = new CardLayout();
			painelCentral = new Panel(layoutCentral);
			
			PainelInicial PainelInicial = new PainelInicial();
			painelCentral.add(PainelInicial, "painel inicial" );

			PainelCadastro painelCadastro = new PainelCadastro();
			painelCentral.add(painelCadastro, "painel cadastro" );

			PainelBuscar painelBuscar = new PainelBuscar();
			painelCentral.add(painelBuscar, "painel buscar");

			PainelExcluir painelExcluir = new PainelExcluir();
			painelCentral.add(painelExcluir, "painel excluir");

			layoutCentral.show( painelCentral, "painel inicial" );




			//	Coloca os componentes na janela
			this.add( painelNorte, BorderLayout.NORTH);
			this.add( labelSul, BorderLayout.SOUTH);
			this.add( painelCentral, BorderLayout.CENTER);

			layoutCentral.show( painelCentral, "painel inicial" );



			// Para pegar o fechamento da janela
			EscutaJanela ej = new EscutaJanela();
			this.addWindowListener(ej);


			//	Ultimos detalhes da janela
			setTitle( "Loja de graos" );
			this.pack();
			this.setVisible(true);
			System.out.println("mostrou janela");
			}


		// Informa o tamanho preferido
		public Dimension getPreferredSize() {
			return new Dimension(800, 500);
			}
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
