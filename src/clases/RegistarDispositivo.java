package clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import lib.Conexion;
import java.awt.SystemColor;

public class RegistarDispositivo extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistarDispositivo frame = new RegistarDispositivo(new EditarCliente(0, new GestionarClientes()),0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JPanel contentPane;
	private JTextField txt_nombre;
	private JTextField txt_modelo;
	private JTextField txt_color;
	private int IDcliente_update=0;
	private String user="",nom_cliente="";
	public static int actualizar;
	private JTextField textField;
	public RegistarDispositivo(EditarCliente gu, int IDcliente) {	
		actualizar++;
		this.addWindowListener( new WindowAdapter()
		 {
			   public void windowClosing(WindowEvent e)
			    {
				  actualizar=0;
			     }
			  });
		IDcliente_update= IDcliente;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInformacinDelCliente = new JLabel("Informaci\u00F3n del cliente");
		lblInformacinDelCliente.setForeground(Color.WHITE);
		lblInformacinDelCliente.setFont(new Font("Arial", Font.PLAIN, 24));
		lblInformacinDelCliente.setBounds(10, 11, 309, 29);
		contentPane.add(lblInformacinDelCliente);
		
		JLabel lblNombreDelCliente = new JLabel("Nombre del cliente:");
		lblNombreDelCliente.setForeground(SystemColor.text);
		lblNombreDelCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombreDelCliente.setBounds(10, 108, 125, 16);
		contentPane.add(lblNombreDelCliente);
		
		txt_nombre = new JTextField();
		txt_nombre.setEditable(false);
		txt_nombre.setHorizontalAlignment(SwingConstants.CENTER);
		txt_nombre.setForeground(Color.WHITE);
		txt_nombre.setFont(new Font("Arial", Font.BOLD, 16));
		txt_nombre.setColumns(10);
		txt_nombre.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_nombre.setBackground(new Color(155, 155, 255));
		txt_nombre.setBounds(10, 130, 225, 29);
		contentPane.add(txt_nombre);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setForeground(SystemColor.text);
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMarca.setBounds(10, 170, 67, 16);
		contentPane.add(lblMarca);
		
		textField = new JTextField();
		textField.setText("");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("Arial", Font.BOLD, 16));
		textField.setColumns(10);
		textField.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField.setBackground(new Color(155, 155, 255));
		textField.setBounds(10, 197, 225, 29);
		contentPane.add(textField);
		
		JLabel lblNumeroDeSerie = new JLabel("Color:");
		lblNumeroDeSerie.setForeground(SystemColor.text);
		lblNumeroDeSerie.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNumeroDeSerie.setBounds(10, 290, 183, 16);
		contentPane.add(lblNumeroDeSerie);
		
		JLabel lblDireccin_1 = new JLabel("Modelo:");
		lblDireccin_1.setForeground(SystemColor.text);
		lblDireccin_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDireccin_1.setBounds(10, 226, 88, 16);
		contentPane.add(lblDireccin_1);
		
		txt_modelo = new JTextField();
		txt_modelo.setHorizontalAlignment(SwingConstants.CENTER);
		txt_modelo.setForeground(Color.WHITE);
		txt_modelo.setFont(new Font("Arial", Font.BOLD, 16));
		txt_modelo.setColumns(10);
		txt_modelo.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_modelo.setBackground(new Color(155, 155, 255));
		txt_modelo.setBounds(10, 250, 225, 29);
		contentPane.add(txt_modelo);
		
		txt_color = new JTextField();
		txt_color.setHorizontalAlignment(SwingConstants.CENTER);
		txt_color.setForeground(Color.WHITE);
		txt_color.setFont(new Font("Arial", Font.BOLD, 16));
		txt_color.setColumns(10);
		txt_color.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_color.setBackground(new Color(155, 155, 255));
		txt_color.setBounds(10, 310, 225, 29);
		contentPane.add(txt_color);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(285, 73, 309, 233);
		contentPane.add(scrollPane);
		
		JTextPane txtp_descripcion = new JTextPane();
		scrollPane.setViewportView(txtp_descripcion);
		
		JLabel lblDescripcionDelEquipo = new JLabel("Observaciones:");
		lblDescripcionDelEquipo.setForeground(SystemColor.window);
		lblDescripcionDelEquipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescripcionDelEquipo.setBounds(285, 46, 175, 16);
		contentPane.add(lblDescripcionDelEquipo);
		


		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ImageIcon wallpaper = new ImageIcon(EditarDispositivo.class.getResource("/images/imagen1.jpg"));
		setIconImage(Toolkit.getDefaultToolkit().getImage(EditarDispositivo.class.getResource("/images/icon.png")));
		this.repaint();
		try {
			Connection cn =  Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("SELECT nombre from Cliente where idCliente = ?");
					  pst.setInt(1, IDcliente_update);
			            ResultSet rs = pst.executeQuery();
			            if(rs.next()) {
			            	nom_cliente= rs.getString("nombre");
			            }
		} catch (SQLException e) {
			System.out.print("error en base de datos registro de equipos 1" + e);
			JOptionPane.showMessageDialog(null, "Error en la base de datos rd");
		}	
		setTitle("cliente: " + nom_cliente);
		txt_nombre.setText(nom_cliente);		
		
		JButton jButto_registrar = new JButton("Registrar Equipo");
		jButto_registrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				int permisos_cmb,validacion =0;
				String marca, modelo, color,idEstado,observaciones, fechaRegistro, dia_ingreso, mes_ingreso, anio_ingreso;
				idEstado = "1";
				color = txt_color.getText().trim();
				observaciones = txtp_descripcion.getText().trim();
				modelo = txt_modelo.getText().trim();
				marca= textField.getText().trim();		
				Calendar calendar = Calendar.getInstance();				
				dia_ingreso = Integer.toString(calendar.get(Calendar.DATE));
				mes_ingreso = Integer.toString((1+calendar.get(Calendar.MONTH)));
				anio_ingreso = Integer.toString(calendar.get(Calendar.YEAR));					
				fechaRegistro=anio_ingreso+"-"+(mes_ingreso)+"-"+dia_ingreso;
			if (modelo.equals("")||observaciones.equals("")||color.equals("")){
				validacion++;
				JOptionPane.showMessageDialog(null, "No se pueden dejar campos vacios");
				
			} else {
				try {
					Connection cn1 =  Conexion.conectar();
					PreparedStatement pst1 = cn1.prepareStatement(
							"INSERT into Dispositivo values (?,?,?,?,?,?,?,?)");          //////////////////////////revisar el orden
					  pst1.setInt(1, 0);
					  pst1.setString(2, marca);
					  pst1.setString(3, modelo);
					  pst1.setString(4, color);
					  pst1.setString(5, observaciones);
					  pst1.setString(6, fechaRegistro);
					  pst1.setInt(7, IDcliente_update);
					  pst1.setString(8, idEstado);
			          pst1.executeUpdate();
			          cn1.close();
			          JOptionPane.showMessageDialog(null, "registro exitoso");
			          gu.actualizar();
			          dispose();				
					}				
				catch (SQLException e1) {
					System.out.print("error en base de datos registro de equpos 2" + e1);
					JOptionPane.showMessageDialog(null, "Error en la base de datos ");
				}	
			}			
			}			
		});
		jButto_registrar.setFocusPainted(false);
		jButto_registrar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		jButto_registrar.setBounds(419, 331, 175, 39);
		contentPane.add(jButto_registrar);
		
		JLabel jLabel_Wallpaper = new JLabel("");
		jLabel_Wallpaper.setForeground(Color.WHITE);
		jLabel_Wallpaper.setBounds(0, 0, 624, 421);
		contentPane.add(jLabel_Wallpaper);
		Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(), 
				jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		jLabel_Wallpaper.setIcon(icono); 
		lblInformacinDelCliente.setText("");
		}
}
