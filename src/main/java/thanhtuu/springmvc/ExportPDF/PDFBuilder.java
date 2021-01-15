package thanhtuu.springmvc.ExportPDF;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.Element;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import thanhtuu.springmvc.ExportPDF.AbstractITextPdfView;
import thanhtuu.springmvc.Service.ExamsService;
import thanhtuu.springmvc.Service.TeacherService;
import thanhtuu.springmvc.Temporary.Exam.ListExam;

public class PDFBuilder extends AbstractITextPdfView {
	@Autowired
	TeacherService teacherService;
	@Autowired
	ExamsService examsService;
	@Override
	public void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ExportExam exportExam  = (ExportExam) model.get("exportExam");
		ExportExam exportExam2  = (ExportExam) model.get("exportExam2");
		ListExam  listExam = null;
		ListExam  listExam2 = null;
		if(exportExam!=null){
		listExam = exportExam.listExam;
		}
	   if(exportExam2!=null){
		listExam2 = exportExam2.listExam;
		}
	
		String url = request.getRequestURL().toString();
		String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath();
		String path = baseURL + "/resources/fonts/vuArial.ttf";
		FontFactory.register(path);
		
		//"C:\\Users\\HennessyVox\\Downloads\\Compressed\\vuArial.ttf"
		//Font fontHeader = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		BaseFont base = BaseFont.createFont(path, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		  Font fontHeader = new Font(base, 10, Font.NORMAL);
		  Font fontHeader2 = new Font(base, 10, Font.UNDERLINE);
		  Font fontContent = new Font(base, 11, Font.NORMAL);
		  Font fontAnswent = new Font(base, 11, Font.NORMAL, BaseColor.RED);
		  Font fontTitle = new Font(base, 12, Font.BOLD);
		  Font fontNumber = new Font(base, 11, Font.BOLD);
		//Font fontHeader = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
	    String SchoolHeader = "TRƯỜNG ĐẠI HỌC BÁCH KHOA TP HCM";
		
	    // Get faulty info.
	
	    
		try {
			
			 if(listExam!=null){
				 System.out.println("=============================================================");
				 int CoutExam = 1;
			    String FacultyHeader = exportExam.getFaulty().toUpperCase();
			    	
			   /// String FacultyHeader = "KHOA KHOA HỌC VÀ KỸ THUẬT MÁY TÍNH";
			    String InfoStudentHeader1 = "Họ Tên : ..........................";
			    String InfoStudentHeader2 =   "MSSV : ..........................";
			    PdfPTable table = new PdfPTable(new float[] { 75, 25 });
			    table.setWidthPercentage(100);
			  
			    table.addCell(getCell(new Paragraph(SchoolHeader,fontHeader), PdfPCell.ALIGN_LEFT));
			    
			    table.addCell(getCell(new Paragraph(InfoStudentHeader1,fontHeader), PdfPCell.ALIGN_RIGHT));
			    table.addCell(getCell(new Paragraph(FacultyHeader,fontHeader2), PdfPCell.ALIGN_LEFT));
			    table.addCell(getCell(new Paragraph(InfoStudentHeader2,fontHeader), PdfPCell.ALIGN_RIGHT));
			    table.setSpacingAfter(10);
			    doc.add(table);
			    
			    // ADD title
			    //Font fontTitle = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			    //String titleExam = "ĐỀ KIỂM TRA CUỐI KÌ 2017";
			   // System.out.println(exportExam.getExamName().toUpperCase());
			    String titleExam = exportExam.getExamName().toUpperCase();
			   
			    Paragraph pHeader = new Paragraph(titleExam,fontTitle);
			    pHeader.setAlignment(Paragraph.ALIGN_CENTER);
			    doc.add(pHeader);
			    
			    String SubjectExam = "Môn Thi : " +exportExam.getSubjectName();
			    String CodeExam = "Mã đề : " + exportExam.getCode();
			    String timeExam = "Thời gian : " + exportExam.getTime();
			    Font fontSubject = new Font(base, 10, Font.NORMAL);
			    Font fontExam = new Font(base, 10, Font.NORMAL);
			    
			    PdfPTable table2 = new PdfPTable(2);
			    table2.setWidthPercentage(80);
			    //System.out.println(Charset.forName("UTF-8").encode(SchoolHeader));

			    table2.addCell(getCell(new Paragraph(SubjectExam,fontSubject), PdfPCell.ALIGN_LEFT));
			   
			    table2.addCell(getCell(new Paragraph(timeExam,fontExam), PdfPCell.ALIGN_RIGHT));
			    table2.addCell(getCell(new Paragraph(CodeExam,fontExam), PdfPCell.ALIGN_LEFT));
			    table2.addCell(getCell(new Paragraph("",fontHeader), PdfPCell.ALIGN_RIGHT));
			    table2.setSpacingBefore(5);
			    doc.add(table2);
			    
			    String noteExam = exportExam.getNoteExam();
			    Font fontNote = new Font(base, 8, Font.ITALIC);
			    
			    Paragraph pNote = new Paragraph(noteExam, fontNote);
			    pNote.setAlignment(Paragraph.ALIGN_MIDDLE);
			    doc.add(pNote);
			    PdfPTable table3 = new PdfPTable(1);
			    table3.setSpacingBefore(5);
			    table3.setWidthPercentage(80);
			    Font finalExam = new Font(base, 10, Font.NORMAL);
			    table3.addCell(getCell(new Paragraph("*****",finalExam), PdfPCell.ALIGN_CENTER));
			    doc.add(table3);
				 
			   for(int i = 0; i < listExam.blockRootQuestion.size() ; i++){
				   System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				   
				   List listBlock = new List(List.UNORDERED);
				   listBlock.setAutoindent(false);
				  // listBlock.setSymbolIndent(20);	
				   listBlock.add(new ListItem(new Paragraph(listExam.blockRootQuestion.get(i).rootQuestionBlock.getContent(),fontContent)));
			       System.out.println("########### Block NAME " +listExam.blockRootQuestion.get(i).rootQuestionBlock.getContent());
				   
				   doc.add(listBlock);
			      
			       
		        	System.out.println("########### SIZE QUESTION " +listExam.blockRootQuestion.get(i).questionList.size());
                     
			        for(int j = 0 ; j < listExam.blockRootQuestion.get(i).questionList.size(); j++){	
			        	System.out.println("########### QUESTION NAME " +listExam.blockRootQuestion.get(i).questionList.get(j).question.getContent());
						
			        	 List listQuestion = new List(List.UNORDERED);
					       listQuestion.setListSymbol("");
			        	
			        	//SingleQuestion questionPDF = null;
			        	//questionPDF = listExam.blockRootQuestion.get(i).questionList.get(j);
			        	StringBuilder sb = new StringBuilder();
						sb.append("Câu ");
						sb.append(CoutExam);
						sb.append(". ");
						String strI = sb.toString();
			        	//strI = new Paragraph(strI, fontNumber);
			        	//listQuestion.setPostSymbol(strI);
			        	System.out.println(strI);
			              listQuestion.add(new ListItem(new Paragraph(strI + listExam.blockRootQuestion.get(i).questionList.get(j).question.getContent(), fontContent)));
			              listQuestion.setIndentationLeft(10);
			              doc.add(listQuestion);
			              List listAnswerPDF = new List(List.ORDERED, List.ALPHABETICAL);
			              for(int k = 0; k < listExam.blockRootQuestion.get(i).questionList.get(j).answerList.size(); k++){
					      System.out.println("###########  Answer NAME " +listExam.blockRootQuestion.get(i).questionList.get(j).answerList.get(k).getContent());
			            	  listAnswerPDF.add(new ListItem(new Paragraph(listExam.blockRootQuestion.get(i).questionList.get(j).answerList.get(k).getContent(), fontContent)));        	 
			              }
			              listAnswerPDF.setIndentationLeft(25);
			              doc.add(listAnswerPDF);
			              CoutExam++;
			        }
			        doc.add( Chunk.NEWLINE );   
			   }
			 //  doc.close();
			   listExam =null;
			 }
			 /*
			  * Export Answer
			  */
			else if(listExam2!=null){
				 int CoutExam = 1;
				 System.out.println("#######################################################");
				   String FacultyHeader = exportExam2.getFaulty().toUpperCase();
			    	
				   /// String FacultyHeader = "KHOA KHOA HỌC VÀ KỸ THUẬT MÁY TÍNH";
				    String InfoStudentHeader1 = "Họ Tên : ..........................";
				    String InfoStudentHeader2 =   "MSSV : ..........................";
				    PdfPTable table = new PdfPTable(new float[] { 75, 25 });
				    table.setWidthPercentage(100);
				  
				    table.addCell(getCell(new Paragraph(SchoolHeader,fontHeader), PdfPCell.ALIGN_LEFT));
				    
				    table.addCell(getCell(new Paragraph(InfoStudentHeader1,fontHeader), PdfPCell.ALIGN_RIGHT));
				    table.addCell(getCell(new Paragraph(FacultyHeader,fontHeader2), PdfPCell.ALIGN_LEFT));
				    table.addCell(getCell(new Paragraph(InfoStudentHeader2,fontHeader), PdfPCell.ALIGN_RIGHT));
				    table.setSpacingAfter(10);
				    doc.add(table);
				    
				    // ADD title
				    //Font fontTitle = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
				    //String titleExam = "ĐỀ KIỂM TRA CUỐI KÌ 2017";
				   // System.out.println(exportExam.getExamName().toUpperCase());
				    String titleExam = exportExam2.getExamName().toUpperCase();
				   
				    Paragraph pHeader = new Paragraph(titleExam,fontTitle);
				    pHeader.setAlignment(Paragraph.ALIGN_CENTER);
				    doc.add(pHeader);
				    
				    String SubjectExam = "Môn Thi : " +exportExam2.getSubjectName();
				    String CodeExam = "Mã đề : " + exportExam2.getCode();
				    String timeExam = "Thời gian : " + exportExam2.getTime();
				    Font fontSubject = new Font(base, 10, Font.NORMAL);
				    Font fontExam = new Font(base, 10, Font.NORMAL);
				    
				    PdfPTable table2 = new PdfPTable(2);
				    table2.setWidthPercentage(80);
				    //System.out.println(Charset.forName("UTF-8").encode(SchoolHeader));

				    table2.addCell(getCell(new Paragraph(SubjectExam,fontSubject), PdfPCell.ALIGN_LEFT));
				   
				    table2.addCell(getCell(new Paragraph(timeExam,fontExam), PdfPCell.ALIGN_RIGHT));
				    table2.addCell(getCell(new Paragraph(CodeExam,fontExam), PdfPCell.ALIGN_LEFT));
				    table2.addCell(getCell(new Paragraph("",fontHeader), PdfPCell.ALIGN_RIGHT));
				    table2.setSpacingBefore(5);
				    doc.add(table2);
				    
				    String noteExam = exportExam2.getNoteExam();
				    Font fontNote = new Font(base, 8, Font.ITALIC);
				    
				    Paragraph pNote = new Paragraph(noteExam, fontNote);
				    pNote.setAlignment(Paragraph.ALIGN_MIDDLE);
				    doc.add(pNote);
				    PdfPTable table3 = new PdfPTable(1);
				    table3.setSpacingBefore(5);
				    table3.setWidthPercentage(80);
				    Font finalExam = new Font(base, 10, Font.NORMAL);
				    table3.addCell(getCell(new Paragraph("*****",finalExam), PdfPCell.ALIGN_CENTER));
				    doc.add(table3);
					 
					 
				   for(int i = 0; i < listExam2.blockRootQuestion.size() ; i++){
					   System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					   
					   List listBlock = new List(List.UNORDERED);
					   listBlock.setAutoindent(false);
					  // listBlock.setSymbolIndent(20);	
					   listBlock.add(new ListItem(new Paragraph(listExam2.blockRootQuestion.get(i).rootQuestionBlock.getContent(),fontContent)));
				       System.out.println("########### Block NAME " +listExam2.blockRootQuestion.get(i).rootQuestionBlock.getContent());
					   
					   doc.add(listBlock);
				      
				       
			        	System.out.println("########### SIZE QUESTION " +listExam2.blockRootQuestion.get(i).questionList.size());
	                     
				        for(int j = 0 ; j < listExam2.blockRootQuestion.get(i).questionList.size(); j++){	
				        	System.out.println("########### QUESTION NAME " +listExam2.blockRootQuestion.get(i).questionList.get(j).question.getContent());
							
				        	 List listQuestion = new List(List.UNORDERED);
						       listQuestion.setListSymbol("");
				        	
				        	//SingleQuestion questionPDF = null;
				        	//questionPDF = listExam.blockRootQuestion.get(i).questionList.get(j);
				        	StringBuilder sb = new StringBuilder();
							sb.append("Câu ");
							sb.append(CoutExam);
							sb.append(". ");
							String strI = sb.toString();
				        	//strI = new Paragraph(strI, fontNumber);
				        	//listQuestion.setPostSymbol(strI);
				        	System.out.println(strI);
				              listQuestion.add(new ListItem(new Paragraph(strI + listExam2.blockRootQuestion.get(i).questionList.get(j).question.getContent(), fontContent)));
				              listQuestion.setIndentationLeft(10);
				              doc.add(listQuestion);
				              List listAnswerPDF = new List(List.ORDERED, List.ALPHABETICAL);
				              for(int k = 0; k < listExam2.blockRootQuestion.get(i).questionList.get(j).answerList.size(); k++){
						      System.out.println("###########  Answer NAME " +listExam2.blockRootQuestion.get(i).questionList.get(j).answerList.get(k).getContent());
						      if(listExam2.blockRootQuestion.get(i).questionList.get(j).answerList.get(k).getIssolution())
				            	  listAnswerPDF.add(new ListItem(new Paragraph(listExam2.blockRootQuestion.get(i).questionList.get(j).answerList.get(k).getContent(), fontAnswent)));
				            	  else 
					            	listAnswerPDF.add(new ListItem(new Paragraph(listExam2.blockRootQuestion.get(i).questionList.get(j).answerList.get(k).getContent(), fontContent)));
				              }
				              listAnswerPDF.setIndentationLeft(25);
				              doc.add(listAnswerPDF);
				              CoutExam++;
				        }
				        doc.add( Chunk.NEWLINE );
				   }
				   doc.close();
				   listExam2=null;
			 }
			 model.clear();
			 doc.close();
			 exportExam = null;
			// exportExam2 = null;
		} catch (Exception e) {
			System.out.println("ERROR "+ e.getMessage());
			e.printStackTrace();
		}

	}
	public PdfPCell getCell(Paragraph h, int alignment) {
	    PdfPCell cell = new PdfPCell(new Phrase(h));
	    cell.setPadding(0);
	    cell.setHorizontalAlignment(alignment);
	    cell.setBorder(PdfPCell.NO_BORDER);
	    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    return cell;
	}
}
