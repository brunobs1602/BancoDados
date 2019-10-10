package visao;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.UsuarioDao;
import modelo.Usuario;
import java.awt.Color;

public class SelectUsuarioGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	
	Usuario usuario;
	UsuarioDao usuarioDAO;
	
	DefaultTableModel tabela;
	List<Usuario> listaUsuarios;
	
	static SelectUsuarioGUI frame;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 frame = new SelectUsuarioGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SelectUsuarioGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 473);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(30, 144, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRelaoDeUsuarios = new JLabel("Rela\u00E7\u00E3o de Usuarios Cadastrado");
		lblRelaoDeUsuarios.setFont(new Font("Arial", Font.BOLD, 20));
		lblRelaoDeUsuarios.setBounds(144, 11, 328, 39);
		contentPane.add(lblRelaoDeUsuarios);
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		
		table.setModel(new DefaultTableModel( new Object [] [] {
		},
		new String [] {
				"id", "Nome", "E-Mail"
		}
		));
		
		
		table.setBounds(10, 54, 570, 327);
		contentPane.add(table);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setBackground(Color.WHITE);
		btnNovo.setIcon(new ImageIcon("C:\\Users\\bruno\\Downloads\\Icones\\icons8-adicionar-usu\u00E1rio-masculino-16.png"));
		btnNovo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			InsereDados telaCadastro = new InsereDados();
			telaCadastro.setLocationRelativeTo(null);
			telaCadastro.setVisible(true);
			frame.setVisible(false);
			telaCadastro.setJanelaAtiva(frame);
			}
		});
		btnNovo.setBounds(10, 400, 89, 23);
		contentPane.add(btnNovo);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon("C:\\Users\\bruno\\Downloads\\Icones\\icons8-fechar-janela-16.png"));
		btnExcluir.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int linha = table.getSelectedRow();
					int id = (int) table.getValueAt(linha, 0);
					
					usuarioDAO.excluir(id);
					atualizarTabela();
					JOptionPane.showMessageDialog(null, "Registro Excluido com sucesso");
					
				}catch (Exception ex) {
					System.out.println(ex.getMessage());
					
					JOptionPane.showMessageDialog(null, "É necessario selecionar um registro para exclusão");
					
				}
			}
		});
		btnExcluir.setBounds(472, 400, 108, 23);
		contentPane.add(btnExcluir);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int linha = table.getSelectedRow();
					int id = (int) table.getValueAt(linha, 0);


					FormUpdate telaAtualizacao = new FormUpdate();
					telaAtualizacao.setLocationRelativeTo(null);
					telaAtualizacao.setVisible(true);
					telaAtualizacao.preencherCampos(id);
					frame.setVisible(false);
					telaAtualizacao.setJanelaAtiva(frame);
					} catch (Exception ex) {
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "É necessário selecionar um registro para atualização!", "Erro", JOptionPane.ERROR_MESSAGE);
					}
					}
					});
				
		
		btnEditar.setIcon(new ImageIcon(SelectUsuarioGUI.class.getResource("/com/sun/javafx/scene/web/skin/Paste_16x16_JFX.png")));
		btnEditar.setBounds(360, 400, 89, 23);
		contentPane.add(btnEditar);
		
		usuario = new Usuario();
		usuarioDAO = new UsuarioDao();
		tabela = (DefaultTableModel) table.getModel();
		atualizarTabela();
	}
	public void atualizarTabela() {
		listaUsuarios = usuarioDAO.select();
		montaTabela();
		
	}
	
	public void montaTabela() {
		Object[] coluna = new Object[3];
		limpaTabela();
		
		for (Usuario usuarioTMP : listaUsuarios) {
			coluna[0] = usuarioTMP.getId();
			coluna[1] = usuarioTMP.getNome();
			coluna[2] = usuarioTMP.getEmail();
			tabela.addRow(coluna);
		}
	}
	
	private void limpaTabela() {
		while (tabela.getRowCount() > 0) {
		for (int i = 0; i < tabela.getRowCount(); i++ ) {
		tabela.removeRow(i);
	}
	}	
}
	
}
