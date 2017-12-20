package ec.com.monkey.servlet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.FileResolver;
import net.sf.jasperreports.engine.util.LocalJasperReportsContext;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {

	private static final long serialVersionUID = 8795000477609113780L;

	public static final String SUB_REPORT_PREFIX = "/WEB-INF/classes";
	public static final String REPORT_PREFIX = "/report/";
	public static final String REPORT_SUFFIX = ".jasper";
	public static final String REPORT_DEFINITION_SUFFIX = ".jrxml";
	public static final String OBJETO_REPORTE = "facturacion_reportes";
	public static final String OBJETO_SUB_REPORTE = "facturacion_subReportes";
	public static final String TIPO_REPORTE = "TIPO_REPORTE";
	public static final String NOMBRE_REPORTE = "NOMBRE_REPORTE";
	public static final String OBJETO_DATASOURCE = "facturacion_datasource";

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			JRDataSource datasource = (JRDataSource) request.getSession().getAttribute(OBJETO_DATASOURCE);
			String reporte = request.getParameter("reporte");
			Map<String, Object> args = (HashMap<String, Object>) request.getSession().getAttribute(OBJETO_REPORTE);
			if (args == null) {
				args = new HashMap<String, Object>();
			}
			if (args.containsKey(NOMBRE_REPORTE)) {
				reporte = (String) args.get(NOMBRE_REPORTE);
				args.remove(NOMBRE_REPORTE);
			}
			JasperReport report = compileReport(reporte);

			compilarSubReportes(request);

			String url = obtenerPathCompleto(request);
			
			byte[] result = JasperRunManager.runReportToPdf(report, args, datasource);
			response.setContentType("application/pdf");
			response.setContentLength(result.length);
			response.setHeader("Content-Disposition", "inline;filename=" + reporte + ".pdf;");
			response.getOutputStream().write(result);
			
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	private JasperReport compileReport(String reportName) throws JRException {
		JasperReport report = null;
		if (null != getClass().getClassLoader().getResource(REPORT_PREFIX + reportName + REPORT_DEFINITION_SUFFIX)) {
			report = JasperCompileManager.compileReport(getClass().getClassLoader()
					.getResourceAsStream(REPORT_PREFIX + reportName + REPORT_DEFINITION_SUFFIX));
		}
		return report;
	}

	/**
	 * 
	 * <b> Permite generar un archivo jasper a partir del .jrxml.</b> <b> Creado
	 * por</b>: Ronny, <b>Fecha:</b> 16/09/2014
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	private File compilarReporte(File file) throws Exception {
		JasperDesign design = JRXmlLoader.load(file.getPath());
		File fileTmp = new File(file.getParent() + File.separator + file.getName().replaceAll(".jrxml", ".jasper"));
		fileTmp.createNewFile();
		OutputStream out = new FileOutputStream(fileTmp);
		JasperCompileManager.compileReportToStream(design, out);
		out.close();
		return fileTmp;
	}

	/**
	 * 
	 * <b> Permite compilar subreportes en caso de tenerlos.</b> <b> Creado
	 * por</b>: Ronny, <b>Fecha:</b> 16/09/2014
	 * 
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	private void compilarSubReportes(HttpServletRequest request) {
		String path = "";
		Map<String, Object> subReportes = (HashMap<String, Object>) request.getSession()
				.getAttribute(OBJETO_SUB_REPORTE);
		if (subReportes != null) {
			for (Object clave : subReportes.keySet()) {
				path = SUB_REPORT_PREFIX + REPORT_PREFIX + clave + REPORT_DEFINITION_SUFFIX;
				String path2 = this.getServletContext().getRealPath(path);
				File file = new File(path2);
				try {
					file.createNewFile();
					compilarReporte(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private LocalJasperReportsContext getReportContext(final File file) {
		FileResolver resolver = new FileResolver() {
			@Override
			public File resolveFile(String filename) {
				return new File(file.getParent() + File.separator + filename);
			}
		};

		LocalJasperReportsContext ctx = new LocalJasperReportsContext(DefaultJasperReportsContext.getInstance());
		ctx.setClassLoader(getClass().getClassLoader());
		ctx.setFileResolver(resolver);
		return ctx;
	}

	/**
	 * 
	 * <b> Incluir documentacion de metodo.</b> <b> Creado por</b>: Ronny,
	 * <b>Fecha:</b> 16/10/2014
	 * 
	 * @param conn
	 *            Conexion con el dataSource
	 * @param out
	 *            salida de bytes
	 * @param clave
	 *            Nombre del Reporte
	 * @param jasper
	 *            Jasper Generado
	 * @param parametros
	 *            Parametros que recibe el reporte
	 */
	public void escribirXls(ByteArrayOutputStream out, String clave, JasperReport jasper,
			Map<String, Object> parametros) {
		try {
			String path = SUB_REPORT_PREFIX + REPORT_PREFIX + clave + REPORT_DEFINITION_SUFFIX;
			String path2 = this.getServletContext().getRealPath(path);
			File file = new File(path2);
			file.createNewFile();
			LocalJasperReportsContext ctx = getReportContext(file);

			JasperReport jasperReport = jasper;
			JasperFillManager fillmgr = JasperFillManager.getInstance(ctx);

			JasperPrint jp = fillmgr.fill(jasperReport, parametros, new JREmptyDataSource());
			JRXlsExporter exporter = new JRXlsExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
			exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER, Boolean.FALSE);
			exporter.exportReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String obtenerPathCompleto(HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String contextPath = request.getContextPath();
		return url.substring(0, url.indexOf(contextPath)) + contextPath;
	}

}
