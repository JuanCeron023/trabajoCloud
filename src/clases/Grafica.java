package clases;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lib.Conexion;
import javafx.scene.chart.*;
import javafx.scene.Group;

public class Grafica {
public static int actualizar=0;
	
    private static void initAndShowGUI() {
    	actualizar=1;
        JFrame frame = new JFrame("Grafico de estatus");
        final JFXPanel fxPanel = new JFXPanel();
        frame.add(fxPanel);
        frame.setSize(520, 450);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.repaint();
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Grafica.class.getResource("/images/icon.png")));
        frame.addWindowListener( new WindowAdapter() {
			   public void windowClosing(WindowEvent e) {
				   frame.setVisible(false);actualizar=0;
		}});
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(fxPanel);
            }});
        }
    
    private static void initFX(JFXPanel fxPanel) {
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }
    
    private static Scene createScene() {
    	HashMap<Integer, Integer> marcas_hash = new HashMap<Integer, Integer>();
		Set<Integer> Estatus_set = new HashSet<Integer>();
		int porcentaje=0;
		int valor_temporal=0;
    	try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("SELECT idEstado from Dispositivo");
			ResultSet rs = pst.executeQuery();		
			while(rs.next()){
				valor_temporal = Integer.valueOf((rs.getString("idEstado")));
				if(!Estatus_set.add(valor_temporal)){
					int tem=0;
					tem = marcas_hash.get(valor_temporal) +1;
					marcas_hash.put(valor_temporal, (tem));
					}
				else {marcas_hash.put(valor_temporal, (1));}}
			cn.close();}
    		catch (SQLException e) {
			System.out.print("error en bd de graficos: " + e);
			JOptionPane.showMessageDialog(null, "Error al mostrar la informacion");
		}
        Scene scene = new Scene(new Group());
            ObservableList<PieChart.Data> marcas =FXCollections.observableArrayList();
        	for (int numero : Estatus_set)  {
        		try {
					porcentaje+=marcas_hash.get(numero);
					Connection cn1 = Conexion.conectar();
					PreparedStatement pst1 = cn1.prepareStatement("SELECT nombreEstado from Estado WHERE idEstado=?");
					pst1.setInt(1, numero);
					ResultSet rs1 = pst1.executeQuery();	
					String temporal="";
					if(rs1.next())
					{
						 temporal=rs1.getString(1);
						System.out.println(temporal);
					}
					marcas.add(new PieChart.Data(temporal,marcas_hash.get(numero)));
				} catch (SQLException e) {
					e.getMessage();
				}
    		}
            final PieChart chart = new PieChart(marcas);
            int porcentajes=porcentaje;
            chart.setTitle("Estatus");
            marcas.forEach(data ->
                    data.nameProperty().bind(Bindings.concat( data.getName(), ": ", data.pieValueProperty().getValue().intValue(), ""   )) );
            ((Group) scene.getRoot()).getChildren().add(chart);
            return (scene);
     }
    
    public void h(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initAndShowGUI(); }});
    }
}


/**
int tem=0;
tem = marcas_hash.get(valor_temporal) +1;
marcas_hash.put(valor_temporal, (tem));
PreparedStatement pst1 = cn.prepareStatement("SELECT nombreEstado from estado WHERE idEstado=?");
pst1.setInt(1, valor_temporal);
ResultSet rs1 = pst1.executeQuery();		
if(rs1.next())
{
	String temporal=rs1.getString(1);
	System.out.println(temporal);
	nombres_hash.put(temporal, (tem));
}
}
else {marcas_hash.put(valor_temporal, (1));
PreparedStatement pst1 = cn.prepareStatement("SELECT nombreEstado from estado WHERE idEstado=?");
pst1.setInt(1, valor_temporal);
ResultSet rs1 = pst1.executeQuery();		
if(rs1.next())
{
String temporal=rs1.getString(1);
System.out.println(temporal);
nombres_hash.put(temporal, (1));
}
}}
*/