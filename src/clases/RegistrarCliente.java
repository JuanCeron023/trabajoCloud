package clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import lib.Conexion;
import java.awt.SystemColor;

public class RegistrarCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txt_nombre;
	private JTextField txt_email;
	private JTextField txt_telefono;
	private JTextField txt_direccion;
	public static int actualizar;
	private JTextField textcedula1;
	private GestionarClientes ventanaGestionarClientes;
	private RegistrarCliente ventanaRegistrarCliente;

	public RegistrarCliente(GestionarClientes ventanaGestionarCliente) {
		actualizar++;
		ventanaGestionarClientes=ventanaGestionarCliente;
		ventanaRegistrarCliente=this;
		this.addWindowListener( new WindowAdapter()
		 {
			   public void windowClosing(WindowEvent e)
			    {
					System.out.println("eso");
				  actualizar=0;
				  ventanaGestionarClientes.actualizar();
			     }
			  });
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegistroDeClientes = new JLabel("Registro de Clientes");
		lblRegistroDeClientes.setForeground(SystemColor.text);
		lblRegistroDeClientes.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblRegistroDeClientes.setBounds(200, 10, 240, 29);
		contentPane.add(lblRegistroDeClientes);
		
		JLabel lblNomvbre = new JLabel("Nombre:");
		lblNomvbre.setForeground(SystemColor.text);
		lblNomvbre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNomvbre.setBounds(10, 64, 67, 16);
		contentPane.add(lblNomvbre);
		
		txt_nombre = new JTextField();
		txt_nombre.setHorizontalAlignment(SwingConstants.CENTER);
		txt_nombre.setForeground(Color.WHITE);
		txt_nombre.setFont(new Font("Arial", Font.BOLD, 16));
		txt_nombre.setColumns(10);
		txt_nombre.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_nombre.setBackground(new Color(155, 155, 255));
		txt_nombre.setBounds(10, 86, 225, 20);
		contentPane.add(txt_nombre);
		
		JLabel lblEmil = new JLabel("Em@il:");
		lblEmil.setForeground(SystemColor.text);
		lblEmil.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmil.setBounds(10, 117, 67, 16);
		contentPane.add(lblEmil);
		
		textcedula1 = new JTextField();
		textcedula1.setHorizontalAlignment(SwingConstants.CENTER);
		textcedula1.setForeground(Color.WHITE);
		textcedula1.setFont(new Font("Arial", Font.BOLD, 16));
		textcedula1.setColumns(10);
		textcedula1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		textcedula1.setBackground(new Color(155, 155, 255));
		textcedula1.setBounds(319, 86, 225, 20);
		contentPane.add(textcedula1);
		
		txt_email = new JTextField();
		txt_email.setHorizontalAlignment(SwingConstants.CENTER);
		txt_email.setForeground(Color.WHITE);
		txt_email.setFont(new Font("Arial", Font.BOLD, 16));
		txt_email.setColumns(10);
		txt_email.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_email.setBackground(new Color(155, 155, 255));
		txt_email.setBounds(10, 139, 225, 20);
		contentPane.add(txt_email);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setForeground(SystemColor.text);
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTelefono.setBounds(10, 170, 67, 16);
		contentPane.add(lblTelefono);
		
		JLabel lblCedula = new JLabel("Cedula:");
		lblCedula.setForeground(Color.WHITE);
		lblCedula.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCedula.setBounds(319, 66, 67, 16);
		contentPane.add(lblCedula);
		
		txt_telefono = new JTextField();
		txt_telefono.setHorizontalAlignment(SwingConstants.CENTER);
		txt_telefono.setForeground(Color.WHITE);
		txt_telefono.setFont(new Font("Arial", Font.BOLD, 16));
		txt_telefono.setColumns(10);
		txt_telefono.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_telefono.setBackground(new Color(155, 155, 255));
		txt_telefono.setBounds(10, 192, 225, 20);
		contentPane.add(txt_telefono);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setForeground(SystemColor.text);
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDireccion.setBounds(10, 233, 112, 16);
		contentPane.add(lblDireccion);
		
		txt_direccion = new JTextField();
		txt_direccion.setHorizontalAlignment(SwingConstants.CENTER);
		txt_direccion.setForeground(Color.WHITE);
		txt_direccion.setFont(new Font("Arial", Font.BOLD, 16));
		txt_direccion.setColumns(10);
		txt_direccion.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_direccion.setBackground(new Color(155, 155, 255));
		txt_direccion.setBounds(10, 260, 225, 20);
		contentPane.add(txt_direccion);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombre, email, telefono, direccion, cedula;
				email = txt_email.getText().trim();
				nombre = txt_nombre.getText().trim();
				telefono = txt_telefono.getText().trim();
				direccion = txt_direccion.getText().trim();
				cedula = textcedula1.getText().trim();
				
			if (email.equals("")||direccion.equals("")||nombre.equals("")||telefono.equals("")||cedula.equals("")){
				JOptionPane.showMessageDialog(null, "No se pueden dejar campos vacios");
			} else {
				try {
					Connection cn =  Conexion.conectar();
					PreparedStatement pst = cn.prepareStatement("SELECT cedula from Cliente where cedula = ?");
							  pst.setString(1, cedula);
					            ResultSet rs = pst.executeQuery();
					            if (rs.next()) {
									JOptionPane.showMessageDialog(null, "este cliente ya existe");
									cn.close();
								} else {
									try {
									cn.close();
									Connection cn1 =  Conexion.conectar();
									PreparedStatement pst1 = cn1.prepareStatement(
											"INSERT into Cliente values (?,?,?,?,?,?)");
											  pst1.setInt(1, 0);
											  pst1.setString(2, nombre);
											  pst1.setString(3, direccion);
											  pst1.setString(4, email);
											  pst1.setString(5, telefono);
											  pst1.setString(6, cedula);
									          pst1.executeUpdate();
											  limpiar(); 
									          cn1.close();
									          JOptionPane.showMessageDialog(null, "registro exitoso");
									          ventanaRegistrarCliente.dispatchEvent(new WindowEvent(ventanaRegistrarCliente, WindowEvent.WINDOW_CLOSING));
									}
									catch(SQLException e1){
										System.out.print("error en base de datos registro de clientes " + e1);
										JOptionPane.showMessageDialog(null, "Error en la base de datos rc");
									}
									}				
				} catch (SQLException e2) {
					System.out.print("error en base de datos registro de clientes " + e2);
					JOptionPane.showMessageDialog(null, "Error en la base de datos  rc ");
				}	
			}			
			}
		});
		btnNewButton.setIcon(new ImageIcon(RegistrarCliente.class.getResource("/images/im8.png")));
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton.setBounds(365, 128, 136, 129);
		contentPane.add(btnNewButton);
		
		JLabel lblRegistrarUsuario = new JLabel("Registrar usuario");
		lblRegistrarUsuario.setForeground(SystemColor.controlLtHighlight);
		lblRegistrarUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRegistrarUsuario.setBounds(385, 263, 179, 16);
		contentPane.add(lblRegistrarUsuario);
		
		JLabel jLabel_Wallpaper = new JLabel("");
		jLabel_Wallpaper.setForeground(Color.WHITE);
		jLabel_Wallpaper.setBounds(0, 0, 624, 321);
		contentPane.add(jLabel_Wallpaper);
		
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		ImageIcon wallpaper = new ImageIcon(RegistrarCliente.class.getResource("/images/imagen1.jpg"));
		Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(), 
												jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		jLabel_Wallpaper.setIcon(icono);
		this.repaint();
	}
	public void limpiar()
	{
		txt_direccion.setText("");
		txt_email.setText("");
		txt_nombre.setText("");
		txt_telefono.setText("");
		textcedula1.setText("");
	}
}



