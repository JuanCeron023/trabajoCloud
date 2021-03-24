package clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.sql.*;
import lib.Conexion;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.SystemColor;


public class cambioContrasena extends JFrame {

	private JPanel contentPane;
	private String user="",user_update="";
	private JPasswordField txt_Password;
	private JPasswordField txt_PasswordConfirmar;
	private int id;
	private JPasswordField txt_passwordC;
	private int ID_=0;

	public cambioContrasena(int id0) {
		ID_=id0;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCambioDePassword = new JLabel("Cambio de password");
		lblCambioDePassword.setForeground(Color.WHITE);
		lblCambioDePassword.setFont(new Font("Arial", Font.BOLD, 24));
		lblCambioDePassword.setBounds(36, 11, 285, 35);
		contentPane.add(lblCambioDePassword);
		
		JLabel lblPasswordActual = new JLabel("Password Actual:");
		lblPasswordActual.setForeground(SystemColor.text);
		lblPasswordActual.setFont(new Font("Arial", Font.BOLD, 13));
		lblPasswordActual.setBounds(10, 46, 132, 23);
		contentPane.add(lblPasswordActual);
		
		JLabel lblPasswordNueva = new JLabel("Password Nueva");
		lblPasswordNueva.setForeground(SystemColor.text);
		lblPasswordNueva.setFont(new Font("Arial", Font.BOLD, 13));
		lblPasswordNueva.setBounds(10, 109, 150, 23);
		contentPane.add(lblPasswordNueva);
		
		JButton jButton_Actualizar = new JButton("Cambiar Contrase\u00F1a");
		jButton_Actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				String contrasena, confirmar_password, passwordc;
				contrasena = txt_Password.getText().trim();
				confirmar_password = txt_PasswordConfirmar.getText().trim();
				passwordc = txt_passwordC.getText().trim();
				
			if (contrasena.equalsIgnoreCase("")||confirmar_password.equalsIgnoreCase("")
					||passwordc.equalsIgnoreCase("")||!(passwordc.equals(confirmar_password))) {
				JOptionPane.showMessageDialog(null, "la contraseña y su confirmación no coinciden o existen campos vacios");
			} else {
				
				try {		
					Connection cn = Conexion.conectar();
					id= ID_;
					PreparedStatement pst = cn.prepareStatement("SELECT usuario from usuario where idUsuario = ? AND contrasena = ?");
					pst.setInt(1, id);
					pst.setString(2, contrasena);
					ResultSet rs = pst.executeQuery();
					if(rs.next())
					{
						try {
						cn.close();
						Connection cn1 =  Conexion.conectar();
						PreparedStatement pst1 = cn1.prepareStatement(
								"UPDATE usuario SET contrasena=? where idUsuario=?");
								  pst1.setString(1, confirmar_password);
								  pst1.setInt(2, id);						  
						          pst1.executeUpdate();
						          cn1.close();
						          JOptionPane.showMessageDialog(null, "Modificacion exitosa");
						          dispose();
						}
						catch(SQLException e3)	{
							System.out.print("error en base de datos restaurar password " + e3);
								JOptionPane.showMessageDialog(null, "Error en la base de datos contrasena");
						}
					}
					else{
						  cn.close();
						  JOptionPane.showMessageDialog(null, "la contraseña actual es incorrecta");     
					}
				} catch (SQLException e2) {
					System.out.print("error en base de datos restaurar password " + e2);
					JOptionPane.showMessageDialog(null, "Error en la base de datos contrasena");
				}	
			}	
			}
		});
		jButton_Actualizar.setForeground(Color.BLACK);
		jButton_Actualizar.setFont(new Font("Arial Narrow", Font.BOLD, 13));
		jButton_Actualizar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		jButton_Actualizar.setBounds(223, 170, 113, 44);
		contentPane.add(jButton_Actualizar);
		
		txt_Password = new JPasswordField();
		txt_Password.setHorizontalAlignment(SwingConstants.CENTER);
		txt_Password.setForeground(Color.WHITE);
		txt_Password.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_Password.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_Password.setBackground(new Color(51, 153, 255));
		txt_Password.setBounds(10, 69, 150, 25);
		contentPane.add(txt_Password);
		
		txt_PasswordConfirmar = new JPasswordField();
		txt_PasswordConfirmar.setHorizontalAlignment(SwingConstants.CENTER);
		txt_PasswordConfirmar.setForeground(Color.WHITE);
		txt_PasswordConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_PasswordConfirmar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_PasswordConfirmar.setBackground(new Color(51, 153, 255));
		txt_PasswordConfirmar.setBounds(10, 134, 150, 25);
		contentPane.add(txt_PasswordConfirmar);
		
		txt_passwordC = new JPasswordField();
		txt_passwordC.setHorizontalAlignment(SwingConstants.CENTER);
		txt_passwordC.setForeground(Color.WHITE);
		txt_passwordC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_passwordC.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_passwordC.setBackground(new Color(51, 153, 255));
		txt_passwordC.setBounds(10, 183, 150, 25);
		contentPane.add(txt_passwordC);
		
		JLabel lblConfirmar = new JLabel("Confirmar");
		lblConfirmar.setForeground(SystemColor.text);
		lblConfirmar.setFont(new Font("Arial", Font.BOLD, 13));
		lblConfirmar.setBounds(10, 159, 150, 23);
		contentPane.add(lblConfirmar);
		
		JLabel jLabel_Wallpaper = new JLabel("");
		jLabel_Wallpaper.setBounds(0, 0, 354, 231);
		contentPane.add(jLabel_Wallpaper);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ImageIcon wallpaper = new ImageIcon(cambioContrasena.class.getResource("/images/imagen1.jpg"));
		Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(), 
				jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		jLabel_Wallpaper.setIcon(icono);
		setIconImage(Toolkit.getDefaultToolkit().getImage(cambioContrasena.class.getResource("/images/icon.png")));
		this.repaint();
	}
}

