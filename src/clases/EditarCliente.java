package clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import lib.Conexion;

public class EditarCliente extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarCliente frame = new EditarCliente(0,new GestionarClientes());//////////////////////////////////
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
	private JPanel contentPane;
	private JTextField txt_nombre;
	private JTextField txt_email;
	private JTextField txt_telefono;
	private JTextField txt_direccion;
	private JTextField txt_cedula;
	private int IDcliente_update=0;
	public static int IDequipo=0;
	private DefaultTableModel model = new DefaultTableModel();
	private EditarCliente ventanaEditarCliente;
	private GestionarClientes ventanaGestionarClientes;
	private EditarDispositivo informacionDispositivo;
	private ArrayList<Integer> actualz = new ArrayList<Integer>();
	private HashMap<Integer, EditarDispositivo> pruebas = new HashMap<Integer, EditarDispositivo>();
	private JTable jTableEquipos= new JTable(){
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false; 
			}};

	public EditarCliente(int ID_0, GestionarClientes gc1) {

		ventanaGestionarClientes=gc1;
		ventanaGestionarClientes.anadir(ID_0);
		this.addWindowListener( new WindowAdapter(){
			   public void windowClosing(WindowEvent e) {
				   ventanaGestionarClientes.quitar(ID_0); 
				   ventanaGestionarClientes.actualizar();
		    }
		  });
		ventanaEditarCliente=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 806, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton jButton_Eliminar = new JButton("");
		jButton_Eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
					try {	
					Connection cn =  Conexion.conectar();
					PreparedStatement pst = cn.prepareStatement(
							"SELECT * FROM Dispositivo WHERE idCliente = ?");
							  pst.setInt(1, ID_0);					  
							  ResultSet rs = pst.executeQuery();
							  if(rs.next()){ //// o puede ser  if rs.getString("nombre")=null
								  JOptionPane.showMessageDialog(null, "No se puede eliminar a este cliente, pues tiene dispositivos asociados");
								}
					          else
					          {
					        	  cn.close();
					        	  cn =  Conexion.conectar();
								pst = cn.prepareStatement(
											"DELETE FROM Cliente WHERE idCliente=?");
									 pst.setInt(1, ID_0);	
									 int prueba=pst.executeUpdate();
									 if(prueba >0){
										  JOptionPane.showMessageDialog(null, "Modificación exitosa");
										}
							          else
							          {
							        	  System.out.print("error en base de datos informacion editarcliente" );
							          }
					          }
					          
					          Limpiar();
					          ventanaGestionarClientes.actualizar();
					          ventanaGestionarClientes.quitar(ID_0);
					          dispose();     
					}
					catch(SQLException e0)
					{
						System.out.print("error en base de datos informacion de cliente " + e0);
						JOptionPane.showMessageDialog(null, "Error en la base de datos ec");
					}	
			}
			
		});
		
		jButton_Eliminar.setFocusPainted(false);
		jButton_Eliminar.setIcon(new ImageIcon(Principal.class.getResource("/images/im12.png")));
		jButton_Eliminar.setBounds(466, 248, 120, 100);
		contentPane.add(jButton_Eliminar);
		
		JLabel lblInformacinDelCliente000 = new JLabel("");
		lblInformacinDelCliente000.setForeground(Color.WHITE);
		lblInformacinDelCliente000.setFont(new Font("Arial", Font.PLAIN, 24));
		lblInformacinDelCliente000.setBounds(10, 11, 40, 39);
		contentPane.add(lblInformacinDelCliente000);
		JLabel lblInformacinDelCliente = new JLabel("Informacion del cliente");
		lblInformacinDelCliente.setForeground(Color.WHITE);
		lblInformacinDelCliente.setFont(new Font("Arial", Font.BOLD, 24));
		lblInformacinDelCliente.setBounds(71, 7, 477, 37);
		contentPane.add(lblInformacinDelCliente);
		
		JLabel lblNomvbre = new JLabel("Nombre:");
		lblNomvbre.setForeground(SystemColor.window);
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
		txt_nombre.setBounds(10, 86, 225, 29);
		contentPane.add(txt_nombre);
		
		JLabel lblEmil = new JLabel("Em@il:");
		lblEmil.setForeground(SystemColor.window);
		lblEmil.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmil.setBounds(10, 117, 67, 16);
		contentPane.add(lblEmil);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setForeground(SystemColor.window);
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTelefono.setBounds(10, 170, 67, 16);
		contentPane.add(lblTelefono);
		
		JLabel lblDireccin = new JLabel("Cedula:");
		lblDireccin.setForeground(SystemColor.window);
		lblDireccin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDireccin.setBounds(10, 290, 183, 16);
		contentPane.add(lblDireccin);
		
		JLabel lblEliminarUsuario = new JLabel("Eliminar cliente");
		lblEliminarUsuario.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEliminarUsuario.setForeground(SystemColor.text);
		lblEliminarUsuario.setBounds(490, 349, 84, 14);
		contentPane.add(lblEliminarUsuario);
		
		
		
		JLabel lblDireccin_1 = new JLabel("Direcci\u00F3n:");
		lblDireccin_1.setForeground(SystemColor.window);
		lblDireccin_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDireccin_1.setBounds(10, 226, 88, 16);
		contentPane.add(lblDireccin_1);
		
		txt_email = new JTextField();
		txt_email.setHorizontalAlignment(SwingConstants.CENTER);
		txt_email.setForeground(Color.WHITE);
		txt_email.setFont(new Font("Arial", Font.BOLD, 16));
		txt_email.setColumns(10);
		txt_email.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_email.setBackground(new Color(155, 155, 255));
		txt_email.setBounds(10, 135, 225, 29);
		contentPane.add(txt_email);
		
		txt_telefono = new JTextField();
		txt_telefono.setHorizontalAlignment(SwingConstants.CENTER);
		txt_telefono.setForeground(Color.WHITE);
		txt_telefono.setFont(new Font("Arial", Font.BOLD, 16));
		txt_telefono.setColumns(10);
		txt_telefono.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_telefono.setBackground(new Color(155, 155, 255));
		txt_telefono.setBounds(10, 186, 225, 29);
		contentPane.add(txt_telefono);
		
		txt_direccion = new JTextField();
		txt_direccion.setHorizontalAlignment(SwingConstants.CENTER);
		txt_direccion.setForeground(Color.WHITE);
		txt_direccion.setFont(new Font("Arial", Font.BOLD, 16));
		txt_direccion.setColumns(10);
		txt_direccion.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_direccion.setBackground(new Color(155, 155, 255));
		txt_direccion.setBounds(10, 250, 225, 29);
		contentPane.add(txt_direccion);
		
		txt_cedula = new JTextField();
		txt_cedula.setEnabled(false);
		txt_cedula.setEditable(false);
		txt_cedula.setHorizontalAlignment(SwingConstants.CENTER);
		txt_cedula.setForeground(Color.WHITE);
		txt_cedula.setFont(new Font("Arial", Font.BOLD, 16));
		txt_cedula.setColumns(10);
		txt_cedula.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_cedula.setBackground(new Color(155, 155, 255));
		txt_cedula.setBounds(10, 310, 225, 29);
		contentPane.add(txt_cedula);
		
		JButton jButto_reparar = new JButton("Registrar dispositivo");
		jButto_reparar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		jButto_reparar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
			RegistarDispositivo registrarEquipo = new RegistarDispositivo(ventanaEditarCliente,IDcliente_update);   ///////////////////////////
			registrarEquipo.setVisible(true);
			}
		});
		jButto_reparar.setFocusPainted(false);
		jButto_reparar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		jButto_reparar.setBounds(656, 250, 123, 92);
		contentPane.add(jButto_reparar);
		
		JButton jButto_actualizar = new JButton("Actualizar cliente");
		jButto_actualizar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		jButto_actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				String nombre,email,telefono,direccion;
				nombre = txt_nombre.getText().trim();
				email = txt_email.getText().trim();
				telefono = txt_telefono.getText().trim();
				direccion = txt_direccion.getText().trim();
				String cedula="";
				if (nombre.equals("")||email.equals("")||direccion.equals("")||
						telefono.equals("")){
					JOptionPane.showMessageDialog(null, "No se pueden dejar campos vacios");
				} else {
					try {	
					Connection cn =  Conexion.conectar();
					PreparedStatement pst = cn.prepareStatement(
							"UPDATE Cliente SET nombre=?, direccion=?, email=?, telefono=?, cedula=? WHERE idCliente = ?");
							  pst.setString(1, nombre);
							  pst.setString(2, direccion);
							  pst.setString(3, email);
							  pst.setString(4, telefono);
							  pst.setString(5, cedula);
							  pst.setInt(6, ID_0);					  
					          pst.executeUpdate();
					          cn.close();
					          JOptionPane.showMessageDialog(null, "Modificacion exitosa");
					          Limpiar();
					          ventanaGestionarClientes.actualizar();
					          ventanaGestionarClientes.quitar(ID_0);
					          dispose();     
					}
					catch(SQLException e0)
					{
						System.out.print("error en base de datos informacion de cliente " + e0);
						JOptionPane.showMessageDialog(null, "Error en la base de datos ec");
					}	
			}
			}
		});
		jButto_actualizar.setFocusPainted(false);
		jButto_actualizar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		jButto_actualizar.setBounds(270, 248, 111, 100);
		contentPane.add(jButto_actualizar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(285, 56, 505, 159);
		contentPane.add(scrollPane);
		
		IDcliente_update = GestionarClientes.ID_ClienteUpdate;
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ImageIcon wallpaper = new ImageIcon(EditarCliente.class.getResource("/images/imagen1.jpg"));
		setIconImage(Toolkit.getDefaultToolkit().getImage(EditarCliente.class.getResource("/images/icon.png")));
		this.repaint();
		
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select * from Cliente where idCliente = ?");
			pst.setInt(1, IDcliente_update);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()){
				lblInformacinDelCliente.setText("Informacion del cliente " + rs.getString("nombre"));
				txt_email.setText(rs.getString("email"));
				txt_nombre.setText(rs.getString("nombre"));
				txt_direccion.setText(rs.getString("direccion"));
				txt_cedula.setText(rs.getString("cedula"));
				txt_telefono.setText(rs.getString("telefono"));
			}
			cn.close();
			
		} catch (SQLException e) {
			System.out.print("error en bd de informacion cliente: " + e);
			JOptionPane.showMessageDialog(null, "Error al mostrar la informacion");
		
		}
		actualizar();
		scrollPane.setViewportView(jTableEquipos);
		
		
		
			JLabel jLabel_Wallpaper = new JLabel("");
			jLabel_Wallpaper.setForeground(Color.WHITE);
			jLabel_Wallpaper.setBounds(0, 0, 800, 426);
			contentPane.add(jLabel_Wallpaper);
			Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(), 
					jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
			jLabel_Wallpaper.setIcon(icono);
		jTableEquipos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				int fila_point = jTableEquipos.rowAtPoint(e.getPoint());
				int columna_point =0;
				if(fila_point > -1){
					IDequipo = (int) model.getValueAt(fila_point, columna_point);
					if((!actualz.contains(IDequipo)))	{
						informacionDispositivo = new EditarDispositivo(ID_0,IDequipo,ventanaEditarCliente);  ////////////
						informacionDispositivo.setVisible(true);
						pruebas.put(IDequipo,informacionDispositivo);
					}
				else	{
					EditarDispositivo informacion_Equipo2;
					informacion_Equipo2 = pruebas.get(IDequipo);
					informacion_Equipo2.setVisible(true);
					informacion_Equipo2.toFront();
					informacion_Equipo2.requestFocus();}}}
			});
	}
	public void anadir(int dato){
	actualz.add(dato);
	}public void quitar(int dato){
		  for (int i = 0; i < actualz.size(); i++) {
				if(actualz.get(i)== dato) {	
					actualz.remove(i);pruebas.remove(dato);}}
		  }	
	public void actualizar()
	{
		try {
			Connection cn1 = Conexion.conectar();
			PreparedStatement pst1 = cn1.prepareStatement("SELECT idDispositivo,marca,modelo,color,observaciones,fechaRegistro,idEstado from Dispositivo where idCliente = ?");
			pst1.setInt(1, IDcliente_update);
			ResultSet rs1 = pst1.executeQuery();
			while (model.getRowCount()>0) {
				model.removeRow(0);
	          }
			   while (model.getColumnCount()!=0)  {                
		        model.setColumnCount(0);
		      }
			model.addColumn("");
			model.addColumn("marca");
			model.addColumn("modelo");
			model.addColumn("color");
			model.addColumn("observaciones");
			model.addColumn("fechaRegistro");
			model.addColumn("idEstado");
			while(rs1.next()){
				Object[] fila = new Object[7];
				for (int i = 0; i < 7; i++) {
					fila[i] = rs1.getObject(i+1);
				}
				model.addRow(fila);
			}
			cn1.close();
			jTableEquipos.setModel(model);
			jTableEquipos.getColumnModel().getColumn(2).setPreferredWidth(50);
			jTableEquipos.getColumnModel().getColumn(0).setMinWidth(20);
			jTableEquipos.getColumnModel().getColumn(0).setMaxWidth(20);
			jTableEquipos.getColumnModel().getColumn(0).setPreferredWidth(20);
			jTableEquipos.getColumnModel().getColumn(1).setMinWidth(50);
			jTableEquipos.getColumnModel().getColumn(1).setMaxWidth(50);
			jTableEquipos.getColumnModel().getColumn(1).setPreferredWidth(50);
			jTableEquipos.getTableHeader().setResizingAllowed(false);
			jTableEquipos.getTableHeader().setReorderingAllowed(false);		
		} catch (SQLException e) {
			System.out.print("error en bd de informacion cliente: " + e);
			JOptionPane.showMessageDialog(null, "Error al mostrar la informacion");
		}
	}
	
	public void Limpiar()
	{
		txt_direccion.setText("");
		txt_email.setText("");
		txt_nombre.setText("");
		txt_telefono.setText("");
		txt_cedula.setText("");
	}

}
