package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
				setarData();

			}
		});

		setTitle("Carômetro");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/img/favicon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 250);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(-1, 166, 385, 45);
		contentPane.add(panel);
		panel.setLayout(null);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Main.class.getResource("/img/dboff.png")));
		lblStatus.setFont(new Font("Arial", Font.PLAIN, 11));
		lblStatus.setBounds(333, 7, 32, 32);
		panel.add(lblStatus);

		lblHoras = new JLabel("");
		lblHoras.setFont(new Font("Arial", Font.PLAIN, 11));
		lblHoras.setBounds(10, 11, 271, 14);
		panel.add(lblHoras);

		JButton btnFoto = new JButton("");
		btnFoto.setIcon(new ImageIcon(Main.class.getResource("/img/foto.png")));
		btnFoto.setFont(new Font("Arial", Font.PLAIN, 11));
		btnFoto.setBounds(170, 35, 104, 104);
		contentPane.add(btnFoto);

		JButton btnSave = new JButton("");
		btnSave.setIcon(new ImageIcon(Main.class.getResource("/img/save.png")));
		btnSave.setFont(new Font("Arial", Font.PLAIN, 11));
		btnSave.setBounds(310, 75, 64, 64);
		contentPane.add(btnSave);

		JButton btnStudents = new JButton("");
		btnStudents.setIcon(new ImageIcon(Main.class.getResource("/img/students.png")));
		btnStudents.setFont(new Font("Arial", Font.PLAIN, 11));
		btnStudents.setBounds(10, 11, 128, 128);
		contentPane.add(btnStudents);
	}// Fim do Construtor

	DAO dao = new DAO();
	private JLabel lblHoras;

	/**
	 * Metodo responsavel por verificar o status da conexão com o banco
	 */
	private void status() {
		// System.out.println("Teste - Janela Ativada");
		// uso da classe connection (JDBC) para estabelecer a conexão
		try {
			Connection con = dao.conectar();
			if (con == null) {
				System.out.println("Erro de Conexão");
				lblStatus.setIcon(new ImageIcon(Main.class.getResource("/img/dboff.png")));
			} else {
				System.out.println("Banco Conectado!");
				lblStatus.setIcon(new ImageIcon(Main.class.getResource("/img/dbon.png")));
			}
			// Nunca esquecer de encerrar a conexão
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	} // Fim do Status

	/**
	 * Comeco do setarData
	 */

	private void setarData() {
		Date data = new Date();
		DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
		lblHoras.setText(formatador.format(data));
	}// Fim do setarData

}// Fim do Codigo
