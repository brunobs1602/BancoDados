package visao;

import java.awt.Color;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.UsuarioDao;
import modelo.Usuario;

public class FormUpdate extends JFrame {

	Usuario usuario = new Usuario();
	UsuarioDao usuarioDAO = new UsuarioDao();
	List<Usuario>listaUsuarios;

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
					FormUpdate frame = new FormUpdate();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void preencherCampos(int id) {
		usuario.setId(id);
		listaUsuarios = usuarioDAO.selectById(id);
		
		for(Usuario user : listaUsuarios) {
		TXTNOME.setText(user.getNome());
		TXTEMAIL.setText(user.getEmail());
		}
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
	public void limpaCampos() {
		TXTNOME.setText("");
		TXTEMAIL.setText("");
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
	public FormUpdate() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(30, 144, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCa = new JLabel("Altera\u00E7\u00E3o de Usuarios");
		lblCa.setFont(new Font("Arial", Font.BOLD, 16));
		lblCa.setBounds(134, 11, 171, 14);
		contentPane.add(lblCa);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(10, 63, 55, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblEmail.setBounds(10, 105, 46, 14);
		contentPane.add(lblEmail);
		
		TXTNOME = new JTextField();
		TXTNOME.setBackground(Color.WHITE);
		TXTNOME.setBounds(75, 61, 298, 20);
		contentPane.add(TXTNOME);
		TXTNOME.setColumns(10);
		
		TXTEMAIL = new JTextField();
		TXTEMAIL.setBounds(75, 103, 298, 20);
		contentPane.add(TXTEMAIL);
		TXTEMAIL.setColumns(10);
		
		JButton btnCadastrar = new JButton("Gravar");
		btnCadastrar.setIcon(new ImageIcon("C:\\Users\\bruno\\Downloads\\Icones\\icons8-cd-16.png"));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuario.setNome(TXTNOME.getText());
				usuario.setEmail(TXTEMAIL.getText());
				
				if(VerificaCampos()) {
					usuarioDAO.update(usuario);
					JOptionPane.showMessageDialog(null, "Registro alterado com sucesso");
				}
			}
		});
		btnCadastrar.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		btnCadastrar.setBounds(10, 227, 99, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		btnFechar.setIcon(new ImageIcon("C:\\Users\\bruno\\Downloads\\Icones\\icons8-fechar-janela-16.png"));
		btnFechar.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		btnFechar.setBounds(325, 228, 99, 23);
		contentPane.add(btnFechar);
	}
}
