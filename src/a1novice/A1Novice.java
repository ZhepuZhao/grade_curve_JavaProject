package a1novice;

import java.util.Scanner;

public class A1Novice {

	// Do not change the main method.	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		process(s);
	}
	
	/*
	 * input: number of students, first name, last name, assignment grade,
	 * participation grade, mid-term exam grade and final exam grade
	 * 
	 * output: modified name with letter grade
	 */
	public static void process(Scanner s) {
		// Put your code here.

		// get number of students
		int stuNumber = s.nextInt(); 
		
		String firstName = ""; 
		String lastName = "";
		
		for (int i = 0; i < stuNumber; i++) {
			
			// get first name
			firstName = s.next();
			//get last name
			lastName = s.next();

			// get assignment grade
			double assignGrade = getAssignmentGrade(s);
			
			// get participation grade
			double particptGrade = getParticptGrade(s);
			
			// get mid-term grade
			double midtermExamGrade = getMidtermGrade(s);
			
			// get final grade
			double finalExamGrade = getFinalGrade(s);

			//Calculate the final grade
			calculateAndPrintGrade(firstName, lastName, assignGrade, particptGrade, 
					midtermExamGrade, finalExamGrade);
		}
	}	


	public static double getAssignmentGrade (Scanner s) {
		double assignPoint;
		assignPoint = s.nextDouble();
		
		return assignPoint;
	}
	public static double getParticptGrade(Scanner s) {
		double particptGrade;
		particptGrade = s.nextDouble();
		
		return particptGrade;
	}
	public static double getMidtermGrade(Scanner s) {
		double midtermExamGrade;
		midtermExamGrade = s.nextDouble();
		return midtermExamGrade;
	}
	public static double getFinalGrade(Scanner s) {
		double finalExamGrade;
		finalExamGrade = s.nextDouble();
		return finalExamGrade;
	}
	public static void calculateAndPrintGrade (String firstName, String lastName, double assignmentGrade, double particptGrade, 
			double midtermExam, double finalExam) {
		double grade;
		String  letterGrade;
		grade = assignmentGrade * 0.4 + particptGrade * 0.15 +
				midtermExam * 0.2 + finalExam * 0.25;
		// determined the letter grade
		if (grade >= 94) {
			letterGrade = "A";
		} else if (grade >= 90) {
			letterGrade = "A-";
		} else if (grade >= 86) {
			letterGrade = "B+";
		} else if (grade >= 83) {
			letterGrade ="B";
		} else if (grade >= 80) {
			letterGrade ="B-";
		} else if (grade >= 76) {
			letterGrade ="C+";
		} else if (grade >= 73) {
			letterGrade ="C";
		} else if (grade >= 70) {
			letterGrade ="C-";
		} else if (grade >= 65) {
			letterGrade ="D+";
		} else if (grade >= 60) {
			letterGrade ="D";
		} else {
			letterGrade ="F";
		}		
		System.out.println(firstName.charAt(0) + "." + " " + lastName +
				" " + letterGrade);
	}
	
}
