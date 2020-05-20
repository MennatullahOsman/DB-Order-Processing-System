package application;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;


public class Trial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 DBConnector db = DBConnector.getInstance();
		 try {
			db.validate("emanrafik", "123456");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		 ArrayList<String> test = new ArrayList<>();
//		 test.add("0123456789");
//		 test.add("1123456789");
//		 db.bookSearch("ti", "", "", 0, 0, "");

		// Reports reports = new Reports();
		// reports.totalSales();

//		try {
//			// Compile jrxml file.
//			JasperReport jasperReport = JasperCompileManager
//					.compileReport("src/application/TotalSales.jrxml");
//
//			// Parameters for report
//			Map<String, Object> parameters = new HashMap<String, Object>();
//
//			// DataSource
//			// This is simple example, no database.
//			// then using empty datasource.
//			JRDataSource dataSource = new JREmptyDataSource();
//
//			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//
//			// Make sure the output directory exists.
//			File outDir = new File("jasperoutput");
//			outDir.mkdirs();
//
//			// Export to PDF.
//			JasperExportManager.exportReportToPdfFile(jasperPrint, "jasperoutput/StyledTextReport.pdf");
//		} catch (JRException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		System.out.println("Done!");
	}

}
