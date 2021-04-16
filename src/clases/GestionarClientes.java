package clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import lib.Conexion;
import javax.swing.JButton;
import java.awt.SystemColor;

public class GestionarClientes extends JFrame {


	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GestionarClientes frame = new GestionarClientes();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	private JPanel contentPane;
	public static int ID_ClienteUpdate;
	public static int actualizar =0;
	private DefaultTableModel model = new DefaultTableModel();
	private GestionarClientes estaVentana; 
	private RegistrarCliente registrarCliente; 
	private EditarCliente editarcliente; ///ventana de informacion
	private ArrayList<Integer> actualz = new ArrayList<Integer>();  /////lista de los id de los clientes para abrir varias ventanas
	private HashMap<Integer, EditarCliente> listaClientesVentanaID = new HashMap<Integer, EditarCliente>(); /////guarda la ventana de un   cliente para no crear otra si ya existe
	private JTable jTable_Clientes = new JTable(){
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;}};

	public GestionarClientes() {
		actualizar++;
		this.addWindowListener( new WindowAdapter() {
			   public void windowClosing(WindowEvent e) {
				  actualizar=0;
			     }
			  });
		estaVentana= this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton jButton_Anadir = new JButton("");
		jButton_Anadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
					if((RegistrarCliente.actualizar==0)){
				   registrarCliente = new RegistrarCliente(estaVentana); ////////////////////////////////////////////////////////////////////////
				   registrarCliente.setVisible(true); ////////////////////////////////////////////////////////////////////////
					}
				else{
					registrarCliente.setVisible(true); ////////////////////////////////////////////////////////////////////////
					registrarCliente.toFront(); ////////////////////////////////////////////////////////////////////////
					registrarCliente.requestFocus(); ////////////////////////////////////////////////////////////////////////
				}
			}
		});
		
		JLabel lblRegistrarNuevoCliente = new JLabel("Registrar nuevo cliente");
		lblRegistrarNuevoCliente.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblRegistrarNuevoCliente.setForeground(SystemColor.window);
		lblRegistrarNuevoCliente.setBounds(509, 224, 134, 14);
		contentPane.add(lblRegistrarNuevoCliente);
		jButton_Anadir.setFocusPainted(false);
		jButton_Anadir.setIcon(new ImageIcon(Principal.class.getResource("/images/im11.png")));
		jButton_Anadir.setBounds(499, 113, 120, 100);
		contentPane.add(jButton_Anadir);
		
		JLabel lblClientesRegistrados = new JLabel("Clientes Registrados");
		lblClientesRegistrados.setForeground(Color.WHITE);
		lblClientesRegistrados.setFont(new Font("Arial", Font.BOLD, 24));
		lblClientesRegistrados.setBounds(217, 10, 272, 35);
		contentPane.add(lblClientesRegistrados);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 65, 459, 193);
		contentPane.add(scrollPane);
		
		JLabel jLabel_Wallpaper = new JLabel("");
		jLabel_Wallpaper.setBounds(0, 0, 624, 301);
		contentPane.add(jLabel_Wallpaper);
		
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		ImageIcon wallpaper = new ImageIcon(GestionarClientes.class.getResource("/images/imagen1.jpg"));
		Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(), 
												jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		jLabel_Wallpaper.setIcon(icono);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestionarClientes.class.getResource("/images/icon.png")));
		this.repaint();
		
		actualizar();
		scrollPane.setViewportView(jTable_Clientes);	
		jTable_Clientes.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e)
		{
			int fila_point = jTable_Clientes.rowAtPoint(e.getPoint());
			int columna_point =0;
			if(fila_point > -1){	
				ID_ClienteUpdate = (int) model.getValueAt(fila_point, columna_point);
			if((!actualz.contains(ID_ClienteUpdate)))	{
				editarcliente= new EditarCliente(ID_ClienteUpdate,estaVentana);
				editarcliente.setVisible(true);
				listaClientesVentanaID.put(ID_ClienteUpdate,editarcliente);
			}
		else	{
			EditarCliente informacion_cliente2;
			informacion_cliente2 = listaClientesVentanaID.get(ID_ClienteUpdate);
			informacion_cliente2.setVisible(true);
			informacion_cliente2.toFront();
			informacion_cliente2.requestFocus();}}
		}
		});
	}
	public void anadir(int dato){
	actualz.add(dato);
	}public void quitar(int dato){
		  for (int i = 0; i < actualz.size(); i++) {
				if(actualz.get(i)== dato) {	
					actualz.remove(i);listaClientesVentanaID.remove(dato);}}
		  }	
	
	
	public void actualizar()
	{
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("SELECT idCliente, nombre, direccion, email, telefono,cedula FROM Cliente");
			ResultSet rs = pst.executeQuery();
			while (model.getRowCount()>0) {
				model.removeRow(0);
	          }
			   while (model.getColumnCount()!=0)  {                
		        model.setColumnCount(0);
		      }
			model.addColumn(" ");
			model.addColumn("Nombre");
			model.addColumn("Direccion");
			model.addColumn("Email");
			model.addColumn("Telefono");
			model.addColumn("Cedula");
			
			while(rs.next()){
				Object[] fila = new Object[6];
				for (int i = 0; i < 6; i++) {
					fila[i] = rs.getObject(i+1);
				}
				model.addRow(fila);
			}
			cn.close();
			jTable_Clientes.setModel(model);
			jTable_Clientes.getColumnModel().getColumn(0).setPreferredWidth(5);
			jTable_Clientes.getTableHeader().setResizingAllowed(false);
			jTable_Clientes.getTableHeader().setReorderingAllowed(false);
			this.repaint();
			this.setVisible(false);
			this.setVisible(true);
		} catch (SQLException e) {
			System.out.print("error en bd de  clientes: " + e);
			JOptionPane.showMessageDialog(null, "Error al mostrar la informacion");
		
		}
	}
}
