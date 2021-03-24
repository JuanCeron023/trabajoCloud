package clases;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.*;



import lib.Conexion;

public class imprimirReporte {

			public imprimirReporte() {

					Document documento = new Document();
					try {
						String ruta= System.getProperty("user.home");
						PdfWriter.getInstance(documento,new FileOutputStream(ruta + "/Desktop/" + "_dispositivos.pdf"));
						
						com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance(imprimirReporte.class.getResource("/images/logo3.png"));
						header.scaleToFit(650,1000);
						header.setAlignment(Chunk.ALIGN_CENTER);		
						documento.open();
						documento.add(header);							
						try {
							Connection cn0 = Conexion.conectar();
							PreparedStatement pst0 = cn0.prepareStatement("select idCliente from Cliente");
							ResultSet rs0 = pst0.executeQuery();
							while(rs0.next())
							{{
							int ID_0= Integer.parseInt(rs0.getString("idCliente"));
							Paragraph parrafo = new Paragraph();
							parrafo.setAlignment(Paragraph.ALIGN_CENTER);
							parrafo.add("Informacion del cliente \n \n");
							parrafo.setFont(FontFactory.getFont("Tahoma",14, Font.BOLD,new BaseColor(10,50,50)));
							documento.add(parrafo);		
							PdfPTable tablaCliente = new PdfPTable(6);
							tablaCliente.addCell("ID");
							tablaCliente.addCell("Nombre");
							tablaCliente.addCell("email");
							tablaCliente.addCell("Telefono");
							tablaCliente.addCell("Direccion");			
							tablaCliente.addCell("Cedula");		
							try {							
								Connection cn =  Conexion.conectar();
								PreparedStatement pst = cn.prepareStatement(
										"select idCliente, nombre, email, telefono, direccion, cedula from Cliente where idCliente = ?");
										  pst.setInt(1, ID_0);
								          int h=0;
								          ResultSet rs = pst.executeQuery();
								        	  while(rs.next()) {
								        		  tablaCliente.addCell(rs.getString(1));
								        		  tablaCliente.addCell(rs.getString(2));
								        		  tablaCliente.addCell(rs.getString(3));
								        		  tablaCliente.addCell(rs.getString(4));
								        		  tablaCliente.addCell(rs.getString(5));
								        		  tablaCliente.addCell(rs.getString(6));
								        		   h=1;
								        	  }
								         if(h==1)  {
								        	 documento.add(tablaCliente); }	         
	
										
										PdfPTable tablaEquipos = new PdfPTable(7);
										tablaEquipos.addCell("Id");
										tablaEquipos.addCell("Marca");
										tablaEquipos.addCell("Modelo");
										tablaEquipos.addCell("Color");
										tablaEquipos.addCell("idEstado");
										tablaEquipos.addCell("Observaciones");
										tablaEquipos.addCell("Fecha registro");								
										try {
											Connection cn1=  Conexion.conectar();
											 int h1=0;
											PreparedStatement pst1 = cn1.prepareStatement(
													"SELECT idDispositivo,marca,modelo,color,idEstado,observaciones, fechaRegistro from Dispositivo where idCliente = ?");
													  pst1.setInt(1, ID_0);
													    ResultSet rs1 = pst1.executeQuery();
													    if(rs1.next())  {
													    	if(h1==0)
													    	{
													    		com.itextpdf.text.Font azul = new com.itextpdf.text.Font(FontFamily.HELVETICA, 12, com.itextpdf.text.Font.NORMAL, BaseColor.BLUE);
																Chunk azulText = new Chunk("Equipos registrados ", azul);
														     	Paragraph parrafo2 = new Paragraph();
														     	parrafo2.add("\n \n");
														     	parrafo2.add(azulText);
														     	parrafo2.add("\n \n ");
														     	parrafo2.setAlignment(Chunk.ALIGN_CENTER);
														     	parrafo2.setFont(FontFactory.getFont("Tahoma",15, Font.BOLD,BaseColor.ORANGE));	
															   documento.add(parrafo2);
																   h1=1;
													    	}
													   do  {
														    tablaEquipos.addCell(rs1.getString(1));
														    tablaEquipos.addCell(rs1.getString(2));
													    	tablaEquipos.addCell(rs1.getString(3));
													    	tablaEquipos.addCell(rs1.getString(4));
													    	tablaEquipos.addCell(rs1.getString(5));
													    	tablaEquipos.addCell(rs1.getString(6));
													    	tablaEquipos.addCell(rs1.getString(7));
													   }   while(rs1.next()); 
													   if(h1==1)
													   {
											        	 documento.add(tablaEquipos);
													   }
													   }
													    else
													    {
															com.itextpdf.text.Font red = new com.itextpdf.text.Font(FontFamily.HELVETICA, 12, com.itextpdf.text.Font.NORMAL, BaseColor.RED);
															Chunk redText = new Chunk("No hay Equipos registrados ", red);
													     	Paragraph parrafo2 = new Paragraph();
													     	parrafo2.add("\n \n");
													     	parrafo2.add(redText);
													     	parrafo2.add("\n \n ");
													     	parrafo2.setAlignment(Chunk.ALIGN_CENTER);
													     	parrafo2.setFont(FontFactory.getFont("Tahoma",15, Font.BOLD,BaseColor.ORANGE));	
														   documento.add(parrafo2);
													    }
													    cn1.close();
										}
										catch(SQLException e0){
											System.out.print("error en base de datos informacion pdf 1 " + e0);
											JOptionPane.showMessageDialog(null, "Error en la base de datos reporte");
										}
										cn.close();
								     	Paragraph parrafo3 = new Paragraph();
										parrafo3.setAlignment(Paragraph.ALIGN_CENTER);
										parrafo3.add("\n \n");
										parrafo3.setFont(FontFactory.getFont("Tahoma",14, Font.BOLD,BaseColor.ORANGE));
										documento.add(parrafo3);
										LineSeparator line = new LineSeparator();
										line.setLineColor(new BaseColor(100,100,100));
										line.setLineWidth(5);
										documento.add(line);
								}
								catch(SQLException e0)	{
									System.out.print("error en base de datos informacion pdf 2 " + e0);
									JOptionPane.showMessageDialog(null, "Error en la base de datos a");
								}
							}}
							cn0.close();
						}
						catch(SQLException e3)	{
							System.out.print("error en base de datos informacion de  pdf 3 " + e3);
							JOptionPane.showMessageDialog(null, "Error en la base de datos b");
						}
						documento.close();
						JOptionPane.showMessageDialog(null, "se creo el documento");
					} catch (DocumentException | IOException e0) {
						System.out.print("error hacer el pdf " + e0);
						JOptionPane.showMessageDialog(null, "Error en hacer el pdf c");
					}
				}
	}