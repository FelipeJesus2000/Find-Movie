package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;

public class formMovie {

	private JFrame frmBuscarFilme;
	private JTextField txtMovie;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					formMovie window = new formMovie();
					window.frmBuscarFilme.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public formMovie() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBuscarFilme = new JFrame();
		frmBuscarFilme.setTitle("Buscar Filme");
		frmBuscarFilme.getContentPane().setBackground(new Color(0, 204, 255));
		frmBuscarFilme.setBounds(100, 100, 450, 300);
		frmBuscarFilme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBuscarFilme.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("BUSCAR FILME");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Impact", Font.PLAIN, 20));
		lblTitle.setBounds(126, 11, 184, 41);
		frmBuscarFilme.getContentPane().add(lblTitle);
		
		JLabel lblMovie = new JLabel("Nome do filme");
		lblMovie.setFont(new Font("Lucida Sans", Font.PLAIN, 12));
		lblMovie.setBounds(25, 73, 113, 14);
		frmBuscarFilme.getContentPane().add(lblMovie);
		
		txtMovie = new JTextField();
		txtMovie.setBounds(25, 88, 98, 22);
		frmBuscarFilme.getContentPane().add(txtMovie);
		txtMovie.setColumns(10);
		
		JButton btnSearch = new JButton("Buscar");
		
		btnSearch.setBounds(143, 88, 89, 23);
		frmBuscarFilme.getContentPane().add(btnSearch);
		
		final JLabel lblMovieName = new JLabel("");
		lblMovieName.setFont(new Font("Lucida Sans", Font.PLAIN, 15));
		lblMovieName.setBounds(25, 132, 220, 14);
		frmBuscarFilme.getContentPane().add(lblMovieName);
		
		final  JLabel lblRate = new JLabel("");
		lblRate.setFont(new Font("Lucida Sans", Font.PLAIN, 15));
		lblRate.setBounds(25, 220, 230, 30);
		frmBuscarFilme.getContentPane().add(lblRate);
		
		final  JLabel lblImage = new JLabel("Capa não encontrada");
		lblImage.setForeground(new Color(0, 0, 0));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setBounds(282, 76, 142, 174);
		frmBuscarFilme.getContentPane().add(lblImage);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 157, 230, 56);
		frmBuscarFilme.getContentPane().add(scrollPane);
		
		final JTextArea lblDesc = new JTextArea();
		lblDesc.setWrapStyleWord(true);
		lblDesc.setEditable(false);
		lblDesc.setBackground(new Color(0, 204, 255));
		lblDesc.setFont(new Font("Lucida Sans", Font.PLAIN, 15));
		lblDesc.setLineWrap(true);
		scrollPane.setViewportView(lblDesc);
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String[] movieData = Main.main(txtMovie.getText());
					

					txtMovie.setText("");
					lblMovieName.setText(movieData[0]);
					lblDesc.setText(movieData[1]);
					lblRate.setText("Avaliação: " + movieData[2]);
					String path = "https://image.tmdb.org/t/p/w500/" + movieData[3];
					URL url = new URL(path);
					Image image = ImageIO.read(url);
					image = image.getScaledInstance(142, 174, Image.SCALE_SMOOTH);
					lblImage.setIcon(new ImageIcon(image));
					Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
					lblImage.setBorder(border);
				} catch (Exception e) {
					e.printStackTrace();
					lblImage.setIcon(null);
					lblImage.setText("Capa não encontrada");
					lblMovieName.setText("");
					lblDesc.setText("");
					lblRate.setText("");

				}
			}
		});
	}
}
