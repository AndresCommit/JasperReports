package t5informesjasper;

import java.util.Scanner;
import java.lang.System.Logger;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import javax.swing.*;
import net.sf.jasperreports.view.JasperViewer;
 
 
/**
*
* @author Tarde
*/
public class T5InformesJasper {
    public static void main(String[] args) throws JRException{
   try {
            String encabezado = JOptionPane.showInputDialog(null, "Ingrese el titulo", "Parámetro encabezado", JOptionPane.QUESTION_MESSAGE);
            String ruta = "C:\\Users\\Tarde\\JaspersoftWorkspace\\MyReports\\Blank_A4.jrxml";
            JasperReport reporte = JasperCompileManager.compileReport(ruta);
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("ParameterEncabezado", encabezado);
            java.sql.Connection conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/fabrica?serverTimezone=Europe/Madrid", "root", "");
            JasperPrint print = JasperFillManager.fillReport(reporte, parametros, conn);
            JasperExportManager.exportReportToPdfFile(print, "informeParam.pdf");
            System.out.println("Informe generado");
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        
       Scanner entrada= new Scanner (System.in);
        System.out.println("Escribe el tipo de Informe que deseas realizar:\n informeTelefonos\nlistadoArticulos\nlistadoEmails\nlistadoPedidos\nmySQL");
        String informe = entrada.nextLine();
        System.out.println("Escribe el nombre del archivo PDF a generar:");
        String nombreInforme=entrada.nextLine();
        String path ="C:\\Users\\Tarde\\Documents\\NetBeansProjects\\T5InformesJasper\\src\\t5informesjasper\\" + informe + ".jrxml";    
        try {
            JasperReport reporte = JasperCompileManager.compileReport(path);
            java.sql.Connection conn = java.sql.DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/fabrica?serverTimezone=UTC",
            "root",
            ""
            );
            JasperPrint print = JasperFillManager.fillReport(reporte,null, conn);
            JasperExportManager.exportReportToPdfFile(print,nombreInforme+".pdf");
            JasperViewer.viewReport(print,false);
            System.out.println("Informe hecho");
            


    }   catch (JRException ex) {
            java.util.logging.Logger.getLogger(T5InformesJasper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(T5InformesJasper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}