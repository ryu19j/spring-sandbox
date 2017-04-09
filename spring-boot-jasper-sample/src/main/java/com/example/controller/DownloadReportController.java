package com.example.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Holiday;
import com.example.service.HolidayService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@RestController
@RequestMapping("download")
public class DownloadReportController {

	@Autowired
	ResourceLoader resourceLoader;

	@Autowired
	HolidayService holidayService;

	@RequestMapping(value = "/pdf")
	public String downloadPdf(HttpServletResponse response) throws JRException, IOException {
		Resource resource = resourceLoader.getResource("classpath:static/reports/TestReport.jasper");
		File reportFile = resource.getFile();

		List<Holiday> holidayList = holidayService.getHoliday();
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(holidayList);
		Map<String, Object> parameter = new HashMap<>();
		byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameter, dataSource);

		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		ServletOutputStream out = response.getOutputStream();
		out.write(bytes);
		out.flush();
		out.close();
		return null;
	}

	@RequestMapping(value = "/xlsx")
	public String downloadXlsx(HttpServletResponse response) throws JRException, IOException {
		List<Holiday> holidayList = holidayService.getHoliday();
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(holidayList);

		Resource resource = resourceLoader.getResource("classpath:static/reports/TestReport.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(resource.getFile().getPath(), new HashMap<>(),
				dataSource);

		ByteArrayOutputStream os = new ByteArrayOutputStream();

		JRXlsxExporter exporter = new JRXlsxExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		File outputFile = new File("excelTest.xlsx");
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
		SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
		configuration.setDetectCellType(true);
		configuration.setCollapseRowSpan(false);
		exporter.setConfiguration(configuration);
		exporter.exportReport();

		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=excelTest.xlsx");

		response.getOutputStream().write(os.toByteArray());
		response.getOutputStream().flush();
		response.getOutputStream().close();
		response.flushBuffer();

		return null;
	}
}
