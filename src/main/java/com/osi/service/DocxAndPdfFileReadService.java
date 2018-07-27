package com.osi.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.osi.domain.EmpDetails;
import com.osi.repository.EmployeeDetailsRepository;

@Service
public class DocxAndPdfFileReadService {
	
	@Autowired
	EmployeeDetailsRepository employeeDetailsRepository;

	public void uploadFile(MultipartFile multipartFile) throws IOException {
		String contentType = multipartFile.getContentType();
		if (contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
			List<String> detailsList = readDocxFile(multipartFile);
			storeEmployeeDetails(detailsList);
		}else {
			List<String> detailsList = readPdfFile(multipartFile);
			storeEmployeeDetails(detailsList);
		} 
	}

	private List<String> readDocxFile(MultipartFile multipartFile) throws IOException {
		XWPFDocument docx = new XWPFDocument(multipartFile.getInputStream());

		XWPFWordExtractor we = new XWPFWordExtractor(docx);

		List<XWPFParagraph> paragraphs = docx.getParagraphs();
		List<String> detailsList = new ArrayList<String>();
		for (int i = 0; i <= 4; i++) {
			String data = paragraphs.get(i).getText().split(":")[1];
			detailsList.add(data);
		}
		return detailsList;
	}

	private List<String> readPdfFile(MultipartFile multipartFile) throws InvalidPasswordException, IOException {
		PDDocument document = PDDocument.load(multipartFile.getInputStream());
		PDFTextStripper pdfStripper = new PDFTextStripper();
		List<String> detailsList = new ArrayList<String>();
		for (int i = 0; i <= 4; i++) {
			//String data = paragraphs.get(i).getText().split(":")[1];
			String data = pdfStripper.getText(document).split(":")[1];
			detailsList.add(data);
		}
		return detailsList;
	}
	private void storeEmployeeDetails(List<String> detailsList) {
		EmpDetails empDetails = new EmpDetails();
		empDetails.setName(detailsList.get(0));
		empDetails.setDob(detailsList.get(1));
		empDetails.setTotal_Experience(detailsList.get(2));
		empDetails.setRelevant_Experience(detailsList.get(3));
		empDetails.setQualification(detailsList.get(4));
		employeeDetailsRepository.save(empDetails);
	}
}