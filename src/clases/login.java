package clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import lib.Conexion;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField txt_usuario;
	private JPasswordField txt_contrasena;
	public static String usuario = "";
	private String contrasena = "";
	private login loginventana;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		loginventana=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setTitle("Acceso al sistema");
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblVectorDeFondo = new JLabel("Vector de Fondo creado por freepik");
		lblVectorDeFondo.setForeground(UIManager.getColor("Button.background"));
		lblVectorDeFondo.setFont(new Font("Sylfaen", Font.PLAIN, 12));
		lblVectorDeFondo.setBounds(114, 507, 191, 14);
		contentPane.add(lblVectorDeFondo);
		
		JLabel lblBienvenido = new JLabel("Bienvenido");
		lblBienvenido.setForeground(Color.DARK_GRAY);
		lblBienvenido.setFont(new Font("Berlin Sans FB", Font.BOLD | Font.ITALIC, 32));
		lblBienvenido.setBounds(114, 235, 230, 49);
		contentPane.add(lblBienvenido);
		
		JLabel jLabel_Logo = new JLabel("");
		jLabel_Logo.setBounds(-20, -89, 445, 366);
		contentPane.add(jLabel_Logo);
		
		txt_usuario = new JTextField();
		txt_usuario.setHorizontalAlignment(SwingConstants.CENTER);
		txt_usuario.setFont(new Font("Arial", Font.PLAIN, 18));
		txt_usuario.setBackground(new Color(153, 153, 204));
		txt_usuario.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_usuario.setForeground(Color.WHITE);
		txt_usuario.setBounds(95, 330, 210, 25);
		contentPane.add(txt_usuario);
		txt_usuario.setColumns(10);
		
		txt_contrasena = new JPasswordField();
		txt_contrasena.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_contrasena.setHorizontalAlignment(SwingConstants.CENTER);
		txt_contrasena.setBackground(new Color(153, 153, 204));
		txt_contrasena.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_contrasena.setForeground(Color.WHITE);
		txt_contrasena.setBounds(128, 375, 150, 25);
		contentPane.add(txt_contrasena);
		
		JButton jButton_Acceder = new JButton("Acceder");
		jButton_Acceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				usuario = txt_usuario.getText().trim();
				contrasena = txt_contrasena.getText().trim();
				if (!usuario.equals("") || !contrasena.equals("")) {
					try {
						Connection cn = Conexion.conectar();
						PreparedStatement pst = cn.prepareStatement(
								"SELECT idUsuario FROM Usuario WHERE usuario = ? AND contrasena = ?");
						  pst.setString(1, usuario);
				            pst.setString(2, contrasena);
				            ResultSet rs = pst.executeQuery();
				            if (rs.next()) {
				            	loginventana.setVisible(false);
								new Principal().setVisible(true);
							} else {
							JOptionPane.showMessageDialog(null, "Error al inciar sesion datos incorrectos");
							txt_usuario.setText("");
							txt_contrasena.setText("");
							}
				            cn.close();
					} catch (SQLException e) {
						System.out.print("error al acceder: " + e);
						JOptionPane.showMessageDialog(null, "Error al inciar sesion");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Se debe rellenar los campos.");
				}
			}
		});
		jButton_Acceder.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		jButton_Acceder.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jButton_Acceder.setBackground(Color.WHITE);
		jButton_Acceder.setBounds(150, 426, 115, 30);
		contentPane.add(jButton_Acceder);
		
		txt_contrasena.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                  jButton_Acceder.doClick();
            }});
		txt_usuario.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	txt_contrasena.requestFocusInWindow();
            }});
		JLabel jLabel_Wallpaper = new JLabel("");
		jLabel_Wallpaper.setBounds(0, 0, 394, 521);
		contentPane.add(jLabel_Wallpaper);
		ImageIcon wallpaper = new ImageIcon(login.class.getResource("/images/fondo1.png"));
		Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(), 
													jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		jLabel_Wallpaper.setIcon(icono);
		this.repaint();
	
		ImageIcon wallpaper_logo = new ImageIcon(login.class.getResource("/images/logo3.png"));
		Icon icono_logo = new ImageIcon(wallpaper_logo.getImage().getScaledInstance(jLabel_Logo.getWidth(), 
											jLabel_Logo.getHeight(), Image.SCALE_DEFAULT));
		jLabel_Logo.setIcon(icono_logo);
		this.repaint();
		}
}
