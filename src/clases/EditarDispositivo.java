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

public class EditarDispositivo extends JFrame {

	/**
	 * Launch the application.
	 */
	private JPanel contentPane;
	private JTextField txt_nombre;
	private JTextField txt_fecha;
	private JTextField txt_modelo;
	private JTextField txt_color;
	private JTextPane txtp_dañoobservaciones;
	private JComboBox cmb_estatus;
//	private JComboBox cmb_estado;
	private JLabel lblDaoReportadoY = new JLabel();
	private JLabel lblComentarioActualizacion = new JLabel();
	private int IDCliente_Update=0, IDequipo=0;
	private String user="",nom_cliente="";
	private EditarCliente ventanaEditarCliente;
	private JTextField textField;
	
	public EditarDispositivo(int ID_cliente, int ID_equipotro, EditarCliente ic) {
		ventanaEditarCliente=ic;
		ventanaEditarCliente.anadir(ID_equipotro);
		this.addWindowListener( new WindowAdapter(){
			   public void windowClosing(WindowEvent e) {
					ventanaEditarCliente.quitar(ID_equipotro);}
		  });
		IDequipo= ID_equipotro;
		IDCliente_Update = ID_cliente;
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInformacinDelCliente000 = new JLabel("");
		lblInformacinDelCliente000.setForeground(Color.WHITE);
		lblInformacinDelCliente000.setFont(new Font("Arial", Font.PLAIN, 24));
		lblInformacinDelCliente000.setBounds(10, 11, 40, 39);
		contentPane.add(lblInformacinDelCliente000);
		
		JLabel lblInformacionDelEquipo = new JLabel("Informacion del equipo");
		lblInformacionDelEquipo.setForeground(Color.WHITE);
		lblInformacionDelEquipo.setFont(new Font("Arial", Font.BOLD, 24));
		lblInformacionDelEquipo.setBounds(10, 13, 518, 37);
		contentPane.add(lblInformacionDelEquipo);
		
		JLabel lblNombreDelCliente = new JLabel("Nombre del cliente:");
		lblNombreDelCliente.setForeground(SystemColor.text);
		lblNombreDelCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombreDelCliente.setBounds(10, 64, 136, 16);
		contentPane.add(lblNombreDelCliente);
		
		txt_nombre = new JTextField();
		txt_nombre.setEditable(false);
		txt_nombre.setHorizontalAlignment(SwingConstants.CENTER);
		txt_nombre.setForeground(Color.WHITE);
		txt_nombre.setFont(new Font("Arial", Font.BOLD, 16));
		txt_nombre.setColumns(10);
		txt_nombre.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_nombre.setBackground(new Color(155, 155, 255));
		txt_nombre.setBounds(10, 86, 225, 29);
		contentPane.add(txt_nombre);
		
		JLabel lblNumeroDeSerie = new JLabel("Fecha:");
		lblNumeroDeSerie.setForeground(SystemColor.text);
		lblNumeroDeSerie.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNumeroDeSerie.setBounds(10, 257, 156, 16);
		contentPane.add(lblNumeroDeSerie);
		
		JLabel lblDireccin = new JLabel("Color:");
		lblDireccin.setForeground(SystemColor.text);
		lblDireccin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDireccin.setBounds(10, 377, 183, 16);
		contentPane.add(lblDireccin);
		
		JLabel lblDireccin_1 = new JLabel("Modelo:");
		lblDireccin_1.setForeground(SystemColor.text);
		lblDireccin_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDireccin_1.setBounds(10, 313, 88, 16);
		contentPane.add(lblDireccin_1);
		
		txt_fecha = new JTextField();
		txt_fecha.setEditable(false);
		txt_fecha.setHorizontalAlignment(SwingConstants.CENTER);
		txt_fecha.setForeground(Color.WHITE);
		txt_fecha.setFont(new Font("Arial", Font.BOLD, 16));
		txt_fecha.setColumns(10);
		txt_fecha.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_fecha.setBackground(new Color(155, 155, 255));
		txt_fecha.setBounds(10, 273, 225, 29);
		contentPane.add(txt_fecha);
		
		txt_modelo = new JTextField();
		txt_modelo.setEditable(false);
		txt_modelo.setHorizontalAlignment(SwingConstants.CENTER);
		txt_modelo.setForeground(Color.WHITE);
		txt_modelo.setFont(new Font("Arial", Font.BOLD, 16));
		txt_modelo.setColumns(10);
		txt_modelo.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_modelo.setBackground(new Color(155, 155, 255));
		txt_modelo.setBounds(10, 337, 225, 29);
		contentPane.add(txt_modelo);
		
		txt_color = new JTextField();
		txt_color.setEditable(false);
		txt_color.setHorizontalAlignment(SwingConstants.CENTER);
		txt_color.setForeground(Color.WHITE);
		txt_color.setFont(new Font("Arial", Font.BOLD, 16));
		txt_color.setColumns(10);
		txt_color.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_color.setBackground(new Color(155, 155, 255));
		txt_color.setBounds(10, 397, 225, 29);
		contentPane.add(txt_color);
		
	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(282, 137, 309, 127);
		contentPane.add(scrollPane);
		
	    txtp_dañoobservaciones = new JTextPane();
		scrollPane.setViewportView(txtp_dañoobservaciones);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("Arial", Font.BOLD, 16));
		textField.setColumns(10);
		textField.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField.setBackground(new Color(155, 155, 255));
		textField.setBounds(10, 217, 225, 29);
		contentPane.add(textField);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setForeground(SystemColor.text);
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMarca.setBounds(10, 190, 156, 16);
		contentPane.add(lblMarca);
		
		JButton jButto_equipo_1 = new JButton("Equipo reparado");
		jButto_equipo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				try {	
					Connection cn =  Conexion.conectar();
					PreparedStatement pst = cn.prepareStatement(
							"UPDATE Dispositivo SET idEstado=? WHERE idDispositivo = ?");
							  pst.setInt(1, 4);///////////////////////////////////////////////////valor que indica que ya esta arreglado
							  pst.setInt(2, IDequipo);						  
					          pst.executeUpdate();
					          cn.close();
					          JOptionPane.showMessageDialog(null, "Modificacion exitosa");
					         int valor= Integer.valueOf(JOptionPane.showInputDialog("escribe el valor de la reparacion"));
					         Principal.valor[0]=Principal.valor[0]+valor;
					          limpiar();
					          actualizar();
					          ventanaEditarCliente.actualizar();
					          ventanaEditarCliente.quitar(IDequipo);
					          dispose();     
					          System.out.println(Principal.valor[0]);
					}
					catch(SQLException e0)
					{
						System.out.print("error en base de datos informacion de cliente " + e0);
						JOptionPane.showMessageDialog(null, "Error en la base de datos ed");
					}	
							
				
			}
		});
		jButto_equipo_1.setFocusPainted(false);
		jButto_equipo_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		jButto_equipo_1.setBounds(343, 394, 175, 39);
		contentPane.add(jButto_equipo_1);
		
		lblDaoReportadoY = new JLabel("Da\u00F1o reportado y observaciones:");
		lblDaoReportadoY.setForeground(SystemColor.text);
		lblDaoReportadoY.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDaoReportadoY.setBounds(282, 117, 294, 16);
		contentPane.add(lblDaoReportadoY);
		
		JLabel lblFechaDeIngreso_1 = new JLabel("Estatus:");
		lblFechaDeIngreso_1.setForeground(SystemColor.text);
		lblFechaDeIngreso_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFechaDeIngreso_1.setBounds(10, 126, 136, 16);
		contentPane.add(lblFechaDeIngreso_1);
		
		cmb_estatus = new JComboBox();
		cmb_estatus.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"})); //cambiar despues ///////////////////////////////////////////
		cmb_estatus.setBounds(10, 150, 130, 29);
		contentPane.add(cmb_estatus);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ImageIcon wallpaper = new ImageIcon(EditarDispositivo.class.getResource("/images/imagen1.jpg"));
		setIconImage(Toolkit.getDefaultToolkit().getImage(EditarDispositivo.class.getResource("/images/icon.png")));	
		this.repaint();	
		
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("SELECT nombre from Cliente where idCliente = ?");
			pst.setInt(1, IDCliente_Update);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()){
				setTitle(" Informacion del cliente " + rs.getString("nombre"));
				txt_nombre.setText(rs.getString("nombre"));
				nom_cliente= rs.getString("nombre");
				lblInformacionDelEquipo.setText("Informacion del equipo del cliente" + nom_cliente);
			}
			cn.close();
			
		} catch (SQLException e0) {
			System.out.print("error en bd de informacion equipos: " + e0);
			JOptionPane.showMessageDialog(null, "Error al mostrar la informacion");
		
		}
		
		actualizar();
		
		JButton jButto_equipo = new JButton("Actualizar equipo");
		jButto_equipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				
				String marca, modelo, color,idEstado,observaciones;		
				
				idEstado = cmb_estatus.getSelectedItem().toString();
				marca = textField.getText().trim();
				modelo= txt_modelo.getText().trim();
				color= txt_color.getText().trim();		
				observaciones= txtp_dañoobservaciones.getText().trim();
				if (color.equals("")||idEstado.equals("")||marca.equals("")||marca.equals("")||observaciones.equals("")||
						modelo.equals("")){
					JOptionPane.showMessageDialog(null, "No se pueden dejar campos vacios");
				} else {
					try {	
					Connection cn =  Conexion.conectar();
					PreparedStatement pst = cn.prepareStatement(
							"UPDATE Dispositivo SET marca=?, modelo=?, color=?, idEstado=?, observaciones=? WHERE idDispositivo = ?");
							  pst.setString(1, marca);
							  pst.setString(2, modelo);
							  pst.setString(3, color);
							  pst.setString(4, idEstado);
							  pst.setString(5, observaciones);
							  pst.setInt(6, IDequipo);						  
					          pst.executeUpdate();
					          cn.close();
					          JOptionPane.showMessageDialog(null, "Modificacion exitosa");
					          limpiar();
					          actualizar();
					          ventanaEditarCliente.actualizar();
					          ventanaEditarCliente.quitar(IDequipo);
					          dispose();     
					}
					catch(SQLException e0)
					{
						System.out.print("error en base de datos informacion de cliente " + e0);
						JOptionPane.showMessageDialog(null, "Error en la base de datos ed");
					}	
			}
			}
		});
		jButto_equipo.setFocusPainted(false);
		jButto_equipo.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		jButto_equipo.setBounds(343, 334, 175, 39);
		contentPane.add(jButto_equipo);
		
		JLabel jLabel_Wallpaper = new JLabel("");
		jLabel_Wallpaper.setForeground(Color.WHITE);
		jLabel_Wallpaper.setBounds(0, 0, 664, 501);	
		Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(), 
				jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		jLabel_Wallpaper.setIcon(icono);
		contentPane.add(jLabel_Wallpaper);
		
	}
	

	
	public void actualizar()
	{
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("SELECT * from Dispositivo where idDispositivo = ?");
			pst.setInt(1, IDequipo);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()){
				cmb_estatus.setSelectedItem(rs.getString("idEstado"));
				textField.setText(rs.getString("marca"));
				txt_modelo.setText(rs.getString("modelo"));
				txt_color.setText(rs.getString("color"));
				txt_fecha.setText(rs.getString("fechaRegistro"));
				txtp_dañoobservaciones.setText(rs.getString("observaciones"));
			}
			cn.close();
			
		} catch (SQLException e) {
			System.out.print("error en bd de informacion dispositivo: " + e);
			JOptionPane.showMessageDialog(null, "Error al mostrar la informacion");
		
		}
	}
	
	public void limpiar()
	{
		txt_modelo.setText("");
		txt_nombre.setText("");
		txt_fecha.setText("");
		txtp_dañoobservaciones.setText("");
	}
	

	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}
