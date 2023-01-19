package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.border.LineBorder;

import model.DAO;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cadastro extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtAluno;
	private JLabel lblFoto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro dialog = new Cadastro();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Cadastro() {
		setTitle("Carômetro - Cadastrar Aluno(a)");
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cadastro.class.getResource("/img/favicon.png")));
		setBounds(100, 100, 470, 420);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblAluno = new JLabel("Aluno :");
		lblAluno.setFont(new Font("Arial", Font.PLAIN, 11));
		lblAluno.setBounds(31, 30, 46, 14);
		getContentPane().add(lblAluno);
		
		txtAluno = new JTextField();
		txtAluno.setFont(new Font("Arial", Font.PLAIN, 11));
		txtAluno.setBounds(74, 28, 300, 20);
		getContentPane().add(txtAluno);
		txtAluno.setColumns(10);
		
		JButton btnSelecionar = new JButton("Selecionar Foto");
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarFoto();
			}
		});
		btnSelecionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSelecionar.setFont(new Font("Arial", Font.PLAIN, 11));
		btnSelecionar.setBounds(155, 59, 122, 23);
		getContentPane().add(btnSelecionar);
		
		lblFoto = new JLabel("");
		lblFoto.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblFoto.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblFoto.setBounds(91, 93, 256, 256);
		getContentPane().add(lblFoto);
		
		JButton btnSalvar = new JButton("");
		btnSalvar.setIcon(new ImageIcon(Cadastro.class.getResource("/img/save.png")));
		btnSalvar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalvar.setBounds(367, 268, 64, 64);
		getContentPane().add(btnSalvar);

	}// Fim do Construtor
	
	DAO dao = new DAO ();
	
	private void selecionarFoto() {
		
		//JFileChooser -> Classe modelo que gera um explorador de arquivo
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Selecionar Arquivo");
		// showOpenDialog(this) -> abre o explorador de arquivos
		// int resultado -> saber se o usuario selecionou um arquivo
		int resultado = jfc.showOpenDialog(this);
	}
}
