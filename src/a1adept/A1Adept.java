package a1adept;

import java.util.Scanner;

public class A1Adept {
	
	// Do not change the main method.	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		process(s);
		
	}
	
	public static void process(Scanner s) {
		// Put your code here.
		int assignmentNumber = s.nextInt(); // get the number of assignment
		int[] assignMaxPoint = getEachAssignmentMaxPoint(assignmentNumber, s); // get the assignment point total available
		int maxPaticptPoint = s.nextInt(); // get total participation points available
		int stuNumber = s.nextInt(); // get the number of students
		
		double[] assignmentGrade = new double[stuNumber];
		double[] particptGrade = new double[stuNumber];
		double[] midtermExam = new double[stuNumber];
		double[] finalExam = new double[stuNumber];

		String firstName = ""; 
		String lastName = "";
		
		// calculate the assignment grade and participation grade before WA for each student
		for (int i = 0; i < stuNumber; i++) {
			firstName = s.next(); // get the first name
			lastName = s.next(); // get the last name
			double particptInitialPoint = s.nextDouble(); // get the original participation point

			// get assignment points
			double[] assignPoint = getEachAssignmentPoint(assignmentNumber, s);
			
			// get the raw mid-term grade
			midtermExam[i] = s.nextDouble(); 
			
			// get the raw final grade
			finalExam[i] = s.nextDouble(); 
			
			// calculate assignment grade before WA
			double assignPointSum = sumAssign(assignmentNumber, assignPoint);
			int assignMaxPointSum = sumAssignMax(assignmentNumber, assignMaxPoint);
			assignmentGrade[i] = assignmentGrade(assignPointSum, assignMaxPointSum);
			
			// calculate participation grade before WA
			particptGrade[i] =  particptGrade(particptInitialPoint, maxPaticptPoint);
			
			// calculate and print the final grade
			calculateAndPrintGrade(firstName, lastName, assignmentGrade[i], particptGrade[i], 
					midtermExam[i], finalExam[i]);
		}

	}

	public static int[] getEachAssignmentMaxPoint (int num, Scanner s) {
		// total points each assignment is worth
		int[] assignMaxPoint = new int[num];
		for (int i = 0; i < assignMaxPoint.length; i++) {
			assignMaxPoint[i] = s.nextInt();
		} 
		return assignMaxPoint;
	}
	public static double[] getEachAssignmentPoint (int num, Scanner s) {
		double[] assignPoint = new double[num];
		for (int i = 0; i < num; i++) {
			assignPoint[i] = s.nextDouble();
		}
		return assignPoint;
	}
	public static double sumAssign (int num, double[] assignPoint) {
		double assignPointSum = 0.0;

		for (int k = 0; k < num; k++) {
			assignPointSum += assignPoint[k];
		}
		return assignPointSum;
	}
	public static int sumAssignMax (int num, int[] assignMaxPoint) {
		int assignMaxPointSum = 0;
		for (int k = 0; k < num; k++) {
			assignMaxPointSum += assignMaxPoint[k];
		}
		return assignMaxPointSum;
	}
	public static double assignmentGrade(double assignPointSum, int assignMaxPointSum) {
		double assignmentGrade;
		assignmentGrade = (assignPointSum / assignMaxPointSum) *  100;
		return assignmentGrade;
	}  
	public static double particptGrade(double particptInitialPoint, int maxPaticptPoint) {
		if (100*(particptInitialPoint/(maxPaticptPoint * 0.8)) >= 100) {
			return 100;		
		} else {
			return 100*(particptInitialPoint/(maxPaticptPoint * 0.8));
		}
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
