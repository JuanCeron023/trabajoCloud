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

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import lib.Conexion;

import java.awt.SystemColor;

public class Principal extends JFrame {

	private JPanel contentPane;
	public static int[] valor={0};
	private GestionarClientes gC;
	private Grafica gR;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Principal frame = new Principal();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Principal() {

			setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/images/icon.png")));
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 650, 430);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblBackgroundVectorCreated = new JLabel("Background vector created by freepik, icons by macrovector");
			lblBackgroundVectorCreated.setForeground(SystemColor.controlHighlight);
			lblBackgroundVectorCreated.setBounds(173, 376, 308, 14);
			contentPane.add(lblBackgroundVectorCreated);
			
			JButton jButton_VerGrafica = new JButton("");
			jButton_VerGrafica.setFocusPainted(false);
			jButton_VerGrafica.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {			
					if(Grafica.actualizar==0){
						gR  = new Grafica();
						gR.crear();
						}
				}
			});
			jButton_VerGrafica.setIcon(new ImageIcon(Principal.class.getResource("/images/im9.png")));
			jButton_VerGrafica.setBounds(254, 139, 120, 100);
			contentPane.add(jButton_VerGrafica);
			
			JButton jButton_GestionarUsuarios = new JButton("");
			jButton_GestionarUsuarios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {				
						if((GestionarClientes.actualizar==0)){
					   gC= new GestionarClientes(); ////////////////////////////////////////////////////////////////////////
					   gC.setVisible(true); ////////////////////////////////////////////////////////////////////////
						}
				else{
					gC.setVisible(true); ////////////////////////////////////////////////////////////////////////
					gC.toFront(); ////////////////////////////////////////////////////////////////////////
					gC.requestFocus(); ////////////////////////////////////////////////////////////////////////
					}
				}
			});
			jButton_GestionarUsuarios.setFocusPainted(false);
			jButton_GestionarUsuarios.setIcon(new ImageIcon(Principal.class.getResource("/images/im8.png")));
			jButton_GestionarUsuarios.setBounds(49, 139, 120, 100);
			contentPane.add(jButton_GestionarUsuarios);
			


			JButton jButton_GenerarReporte = new JButton("");
			jButton_GenerarReporte.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				imprimirReporte  imprimir= new imprimirReporte();
				JOptionPane.showMessageDialog(null, "El dinero recaudado hasta el momento es: "+ valor[0] + "$, un reporte con los dispositivos almacenados y los clientes a sido creado en su escritorio");
				}
			});
			jButton_GenerarReporte.setFocusPainted(false);
			ImageIcon icon1 = new ImageIcon(Principal.class.getResource("/images/im10.png"));
			
			JLabel lblYDispositivos = new JLabel("y dispositivos");
			lblYDispositivos.setForeground(Color.WHITE);
			lblYDispositivos.setFont(new Font("Arial", Font.BOLD, 13));
			lblYDispositivos.setBounds(59, 250, 120, 34);
			contentPane.add(lblYDispositivos);
			
			JLabel lblCambiarContrasea = new JLabel("Cambiar contrase\u00F1a");
			lblCambiarContrasea.setForeground(Color.WHITE);
			lblCambiarContrasea.setFont(new Font("Arial", Font.BOLD, 13));
			lblCambiarContrasea.setBounds(454, 349, 142, 14);
			contentPane.add(lblCambiarContrasea);
			jButton_GenerarReporte.setIcon(new ImageIcon(icon1.getImage().getScaledInstance(90, 
					100, Image.SCALE_SMOOTH)));
			jButton_GenerarReporte.setBounds(442, 139, 120, 100);
			contentPane.add(jButton_GenerarReporte);
			
			JButton jButton_GestionarUsuarios_1 = new JButton("");
			jButton_GestionarUsuarios_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cambioContrasena cambio = new cambioContrasena(1); ////porque hay 1 usuario
					cambio.setVisible(true);
				}
			});
			jButton_GestionarUsuarios_1.setFocusPainted(false);
			jButton_GestionarUsuarios_1.setIcon(new ImageIcon(Principal.class.getResource("/images/im100.png")));
			jButton_GestionarUsuarios_1.setBounds(477, 296, 72, 48);
			contentPane.add(jButton_GestionarUsuarios_1);
			
			JLabel lblInformacionUsuario = new JLabel("Gestionar clientes");
			lblInformacionUsuario.setForeground(SystemColor.window);
			lblInformacionUsuario.setFont(new Font("Arial", Font.BOLD, 13));
			lblInformacionUsuario.setBounds(49, 236, 120, 34);
			contentPane.add(lblInformacionUsuario);
			
			JLabel lblGrafica = new JLabel("Ver grafica de estados");
			lblGrafica.setForeground(SystemColor.window);
			lblGrafica.setFont(new Font("Arial", Font.BOLD, 13));
		 
			lblGrafica.setBounds(243, 238, 142, 28);
			contentPane.add(lblGrafica);
			
			JLabel lblReporte = new JLabel("Generar Reporte");
			lblReporte.setForeground(SystemColor.text);
			lblReporte.setFont(new Font("Arial", Font.BOLD, 13));
			lblReporte.setBounds(442, 245, 120, 14);
			contentPane.add(lblReporte);
			
			JLabel jLabel_Wallpaper = new JLabel("");
			jLabel_Wallpaper.setBounds(0, 0, 644, 401);
			contentPane.add(jLabel_Wallpaper);
		
		
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		ImageIcon wallpaper = new ImageIcon(Principal.class.getResource("/images/imagen1.jpg"));
		Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(), 
												jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		jLabel_Wallpaper.setIcon(icono);
		
		JLabel lblInformacionUsuario_1 = new JLabel("Gestionar usuarios");
		lblInformacionUsuario_1.setForeground(Color.WHITE);
		lblInformacionUsuario_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblInformacionUsuario_1.setBounds(59, 250, 120, 34);
		contentPane.add(lblInformacionUsuario_1);
		this.repaint();

		
	}

	

}
