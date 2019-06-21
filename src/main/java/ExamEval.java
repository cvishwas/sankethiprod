package com.loanapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExamEval {
	
	   public static void main(String[] args) {
	        String correctAnswers = "C:\\work\\test\\test.txt";
	        
	        // Method #1 - Read all lines as a Stream
	        ArrayList<String> correctAnswersList = fileStreamUsingFiles(correctAnswers);
	        
	        File workingDirectory = new File("C:\\\\work\\\\test\\answers");
			File[] txtFiles = workingDirectory.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String filename) {
					return filename.endsWith(".txt");
				}
			});

			for(File txtFl:txtFiles) {
				ArrayList<String> x = fileStreamUsingFiles(txtFl.getAbsolutePath());
				int correctAnsTot = calculateCorrectAnswers(correctAnswersList,x);
				System.out.println(txtFl.getName() +"="+correctAnsTot);
			}
//	        System.out.println();
//	 
//	        // Method #2 - Read file with a filter
//	        filterFileData(fName);
//	 
//	        System.out.println();
//	 
//	        // Method #3 - In Java8, 'BufferedReader' has the 'lines()' method which returns the file content as a Stream
//	        fileStreamUsingBufferedReader(fName);
	    }
	 
	   private static int calculateCorrectAnswers(ArrayList<String> correctAnswersList,ArrayList<String> studentsAnswersList) {
		   int tot = 0;
		   int indCorr = 0;
		   int stCorr = 0;
		   for(String correct:correctAnswersList) {
			   indCorr++;
			   for(String student:studentsAnswersList) {
				   stCorr++;
				   if(indCorr == stCorr) {
					   if(correct.equalsIgnoreCase(student)) {
						   tot++;
						   stCorr = 0;
						   break;
					   }
				   }
				   
			   }
		   }
		   return tot;
	   }
	    // Method #1
	    private static ArrayList<String> fileStreamUsingFiles(String fileName) {
	    	List<String> list = new ArrayList<>();
	    	ArrayList<String> answersList = new ArrayList<String>();
	        try(Stream<String> lines = Files.lines(Paths.get(fileName))) {
	        	list = lines.map(String::toUpperCase).collect(Collectors.toList());
	            for(String str:list) {
	            	StringTokenizer st = new StringTokenizer(str,".");
	            	st.nextToken();
	            	answersList.add(st.nextToken());
	            }

	            lines.close();
	        } catch(IOException io) {
	            io.printStackTrace();
	        }
	        return answersList;
	    }
	 
	    // Method #2
	    private static void filterFileData(String fileName) {
	    	Stream<String> lines = null;
	        try {
	            lines = Files.lines(Paths.get(fileName)).filter(line -> line.startsWith("s"));
	            System.out.println("<!-----Filtering the file data using Java8 filtering-----!>");
	            lines.forEach(System.out :: println);
//	            lines.close();
	        } catch(IOException io) {
	            io.printStackTrace();
	        }finally {
	        	lines.close();
	        }
	    }
	 
	    // Method #3
	    private static void fileStreamUsingBufferedReader(String fileName) {
	    	Stream <String> lines = null;
	        try {
	            BufferedReader br = Files.newBufferedReader(Paths.get(fileName));
	            lines = br.lines().map(str -> str.toUpperCase());
	            System.out.println("<!-----Read all lines by using BufferedReader-----!>");
	            lines.forEach(System.out::println);
//	            lines.close();
	        } catch (IOException io) {
	            io.printStackTrace();
	        }finally {
	        	lines.close();
	        }
	    }
	}	
	


