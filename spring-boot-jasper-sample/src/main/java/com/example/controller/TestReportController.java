package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRCsvDataSource;

@RestController
public class TestReportController {

	@RequestMapping(value = "/download")
	public String download(HttpServletResponse response) throws JRException, IOException {
		File reportFile = new FileSystemResource("src/main/resources/static/reports/TestReport.jasper").getFile();
		JRCsvDataSource dataSource = new JRCsvDataSource(new FileSystemResource("src/main/resources/static/csv/holiday.csv").getFile());
	    dataSource.setUseFirstRowAsHeader(true);
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
}
