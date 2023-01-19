package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.DAO;

public class Cadastro extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtAluno;
	private JLabel lblFoto;

	// Criar um Objeto (Global) para obter o fluxo de bytes(Imagem)
	// FileInputStream -> Classe modelo responsavel pela entrada de dados binarios
	private FileInputStream fis;
	// Criar uma variavel global para armazenar o tamanho em bytes da imagem
	private int tamanho;

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

	DAO dao = new DAO();

	private void selecionarFoto() {

		// JFileChooser -> Classe modelo que gera um explorador de arquivo
		JFileChooser jfc = new JFileChooser();
		// a linha abaixo modifica o titulo do explorador de arquivos
		jfc.setDialogTitle("Selecionar Arquivo");
		// a linha abaixo cria um filtro para escolher determinados tipos de arquivo
		jfc.setFileFilter(new FileNameExtensionFilter("Arquivo de Imagens(*.PNG,*.JPG,*.JPEG", "png", "jpg", "jpeg"));
		// showOpenDialog(this) -> abre o explorador de arquivos
		// int resultado -> saber se o usuario selecionou um arquivo
		int resultado = jfc.showOpenDialog(this);
		// se o usuario escolher uma opcao, setar a JLabel
		if (resultado == JFileChooser.APPROVE_OPTION) {
			// tratamento de excecao
			try {
				// a linha abaixo "pega" o arquivo
				fis = new FileInputStream(jfc.getSelectedFile());
				// a linha abaixo obtem o tamanho do arquivo
				tamanho = (int) jfc.getSelectedFile().length();
				// convertendo o arquivo e setando a largura e altura para preencher a JLabel/
				// SCALE.SMOOTH (Melhor resolucao possivel)
				Image foto = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(lblFoto.getWidth(),
						lblFoto.getHeight(), Image.SCALE_SMOOTH);
				// setar a JLabel
				lblFoto.setIcon(new ImageIcon(foto));
				lblFoto.updateUI();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}
