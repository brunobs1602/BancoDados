package visao;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import dao.UsuarioDao;
import modelo.Usuario;
import javax.swing.ImageIcon;
import java.awt.Color;



public class InsereDados extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField TXTNOME;
	private JTextField TXTEMAIL;
	SelectUsuarioGUI janela;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsereDados frame = new InsereDados();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void limpaCampos() {
		TXTNOME.setText("");
		TXTEMAIL.setText("");
	}

	public void setJanelaAtiva(SelectUsuarioGUI frame){
        janela = frame;
    }
	private void fechar() {
		limpaCampos();
        janela.setVisible(true);
        janela.atualizarTabela();
        this.dispose();
	}
	
	public Boolean VerificaCampos() {
		if(TXTNOME.getText().equals("") || TXTEMAIL.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Aviso", JOptionPane.WARNING_MESSAGE);
			return false;
			
		}else {
			return true;
		}
	}
	/**
	 * Create the frame.
	 */
	public InsereDados() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(30, 144, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblNome.setBounds(10, 67, 46, 14);
		contentPane.add(lblNome);
		
		TXTNOME = new JTextField();
		TXTNOME.setBounds(66, 64, 296, 20);
		contentPane.add(TXTNOME);
		TXTNOME.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblEmail.setBounds(10, 108, 46, 14);
		contentPane.add(lblEmail);
		
		TXTEMAIL = new JTextField();
		TXTEMAIL.setBounds(66, 106, 296, 20);
		contentPane.add(TXTEMAIL);
		TXTEMAIL.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setIcon(new ImageIcon("C:\\Users\\bruno\\Downloads\\Icones\\icons8-adicionar-usu\u00E1rio-masculino-16.png"));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario usuario = new Usuario();
				UsuarioDao usuarioDAO = new UsuarioDao();
				
				usuario.setNome(TXTNOME.getText());
				usuario.setEmail(TXTEMAIL.getText());
				if(VerificaCampos()) {
					usuarioDAO.insert(usuario);
					JOptionPane.showMessageDialog(null, "Registro inserido com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				}
				limpaCampos();
			}
		});
		btnCadastrar.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		btnCadastrar.setBounds(10, 227, 121, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setIcon(new ImageIcon("C:\\Users\\bruno\\Downloads\\Icones\\icons8-fechar-janela-16.png"));
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		btnFechar.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		btnFechar.setBounds(322, 227, 102, 23);
		contentPane.add(btnFechar);
		
		JLabel lblCadastroDeUsuarios = new JLabel("Cadastro de Usuarios");
		lblCadastroDeUsuarios.setFont(new Font("Arial", Font.BOLD, 16));
		lblCadastroDeUsuarios.setBounds(131, 11, 172, 14);
		contentPane.add(lblCadastroDeUsuarios);
	}
}
