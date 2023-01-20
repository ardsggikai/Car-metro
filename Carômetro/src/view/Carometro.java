package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import com.mysql.cj.jdbc.Blob;

import model.DAO;

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
		setBounds(100, 100, 520, 440);
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
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBuscar.setBounds(165, 11, 126, 23);
		getContentPane().add(btnBuscar);

		JButton btnDeletar = new JButton("Deletar Aluno");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletarAluno();
			}
		});
		btnDeletar.setFont(new Font("Arial", Font.PLAIN, 11));
		btnDeletar.setBounds(183, 355, 126, 23);
		getContentPane().add(btnDeletar);

	}// Fim do Construtor

	DAO dao = new DAO();

	/**
	 * Metodo De Buscar Aluno
	 */
	private void buscar() {
		String read = "select * from alunos where nome = ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setString(1, txtAluno.getText());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				txtID.setText(rs.getString(1));
				// Ler o binário e converter para imagem
				Blob blob = (Blob) rs.getBlob(3);
				byte[] img = blob.getBytes(1, (int) blob.length());
				// "papel" que vai "imprimir" a imagem
				BufferedImage imagem = null;
				try {
					// renderizar a imagem (desenhar a foto(pixels) no "papel")
					imagem = ImageIO.read(new ByteArrayInputStream(img));
				} catch (Exception e) {
					System.out.println(e);
				}
				// setar a imagem no JLabel
				ImageIcon icone = new ImageIcon(imagem);
				Icon foto = new ImageIcon(icone.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(),
						Image.SCALE_SMOOTH));
				lblFoto.setIcon(foto);
			} else {
				JOptionPane.showMessageDialog(null, "Aluno(a) não cadastrado(a)");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}// Fim do Metodo

	/**
	 * Metodo deletarAluno
	 */

	private void deletarAluno() {

		int confirma = JOptionPane.showConfirmDialog(null, "Deseja Excluir Esse Aluno(a)", "Excluir Aluno(a)!!",
				JOptionPane.YES_NO_OPTION);

		if (confirma == JOptionPane.YES_NO_OPTION) {

			String delete = "delete from alunos where id = ?";

			try {

				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtID.getText());
				int confirmaExcluir = pst.executeUpdate();
				if (confirmaExcluir == 1) {
					JOptionPane.showMessageDialog(null, "Aluno Excluido");
					limpar();
				}

				con.close();

			} catch (Exception e) {
				System.out.println(e);

			}
		}

	}

	/**
	 * Metodo Responsavel por limpar
	 */

	private void limpar() {
		txtAluno.setText(null);
		lblFoto.setIcon(null);
	}// Fim do Metodo limpar

}
