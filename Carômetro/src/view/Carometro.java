package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class Carometro extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtID;
	private JTextField txtAluno;
	private JLabel lblFoto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Carometro dialog = new Carometro();
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
	public Carometro() {
		setTitle("Carometro - Alunos(as)");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Carometro.class.getResource("/img/favicon.png")));
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 520, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("        ID :");
		lblId.setFont(new Font("Arial", Font.PLAIN, 11));
		lblId.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblId.setBounds(360, 46, 46, 14);
		getContentPane().add(lblId);
		
		JLabel lblAluno = new JLabel("Aluno\r\n\r\n :");
		lblAluno.setFont(new Font("Arial", Font.PLAIN, 11));
		lblAluno.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblAluno.setBounds(25, 46, 46, 14);
		getContentPane().add(lblAluno);
		
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setFont(new Font("Arial", Font.PLAIN, 11));
		txtID.setBounds(408, 43, 86, 20);
		getContentPane().add(txtID);
		txtID.setColumns(10);
		
		txtAluno = new JTextField();
		txtAluno.setBounds(73, 43, 306, 20);
		getContentPane().add(txtAluno);
		txtAluno.setColumns(10);
		
		lblFoto = new JLabel("");
		lblFoto.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblFoto.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblFoto.setBounds(97, 77, 256, 256);
		getContentPane().add(lblFoto);
		
		JButton btnBuscar = new JButton("Buscar Aluno");
		btnBuscar.setBounds(165, 11, 126, 23);
		getContentPane().add(btnBuscar);

	}// Fim do Construtor
}
