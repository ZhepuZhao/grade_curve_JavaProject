package a1jedi;

import java.util.Scanner;

public class A1Jedi {

	// Do not change the main method.	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		process(s);

	}

	public static void process(Scanner s) {
		// Put your code here.
		int assignmentNumber = s.nextInt(); // get the number of assignments
		int[] assignMaxPoint = getEachAssignmentMaxPoint(assignmentNumber, s); // get the assignment point total available
		int maxPaticptPoint = s.nextInt(); // get total participation points available
		int stuNumber = s.nextInt(); // get the number of students
		
		double[] assignmentGrade = new double[stuNumber];
		double[] particptGrade = new double[stuNumber];
		double[] midtermExam = new double[stuNumber];
		double[] finalExam = new double[stuNumber];
		double[] midtermNormScore = new double[stuNumber];
		double[] finalNormScore = new double[stuNumber];
		double[] midtermCurvedScore = new double[stuNumber];
		double[] finalCurvedScore = new double[stuNumber];
		int[] count = new int[11];
		String[] letterGrade = {"A ", "A-", "B+", "B ", "B-", "C+", "C "
				, "C-", "D+", "D ", "F "};
		String firstName = ""; 
		String lastName = "";
		double midtermAvg = 0.0;
		double finalAvg = 0.0;
		double midtermStdDev = 0.0;
		double finalStdDev = 0.0;
		
		// calculate the assignment grade and participation grade before WA for each student
		for (int i = 0; i < stuNumber; i++) {
			firstName = s.next();
			lastName = s.next();
			double particptInitialPoint = s.nextDouble();

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
		}
		
		// mid-term and final Average of all the students
		midtermAvg = midtermAvg(stuNumber, midtermExam);
		finalAvg = finalAvg(stuNumber, finalExam);

		// mid-term and final Standard Deviation of all the students
		midtermStdDev = midtermStdDev(stuNumber, midtermExam, midtermAvg);
		finalStdDev = finalStdDev(stuNumber, finalExam, finalAvg);
		
		// mid-term and final Normalized Score of all the students
		midtermNormScore = midtermNormScore(stuNumber, midtermExam, midtermAvg, midtermStdDev);
		finalNormScore = finalNormScore(stuNumber, finalExam, finalAvg, finalStdDev);
		
		// mid-term and final Curved Score of all the students
		midtermCurvedScore = midtermCurvedScore(stuNumber, midtermNormScore);
		finalCurvedScore = finalCurvedScore(stuNumber, finalNormScore);

		// WA grade and grade count
		count = gradeCount (stuNumber, assignmentGrade, particptGrade, midtermCurvedScore, finalCurvedScore);
		
		// Output results
		printResults(letterGrade, count);
		
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
	public static double midtermAvg (int stuNumber, double[] midtermExam) {
		double midtermAvg;
		double midtermSum = 0;
		for (int j = 0; j < stuNumber; j++) {
			midtermSum += midtermExam[j];
		}
		midtermAvg = midtermSum / stuNumber;
		return midtermAvg;
	}
	public static double finalAvg (int stuNumber, double[] finalExam) {
		double finalAvg;
		double finalSum = 0;
		for (int j = 0; j < stuNumber; j++) {
			finalSum += finalExam[j];
		}
		finalAvg = finalSum / stuNumber;
		return finalAvg;
	}
	public static double midtermStdDev (int stuNumber, double[] midtermExam, double midtermAvg) {
		double midtermSquareSum = 0;
		double midtermStdDev;
		for (int k = 0; k < stuNumber; k++) {
			midtermSquareSum += (midtermExam[k] - midtermAvg) * (midtermExam[k] - midtermAvg);
		}
		midtermStdDev = Math.sqrt(midtermSquareSum / stuNumber);
		return midtermStdDev;
	}
	public static double finalStdDev (int stuNumber, double[] finalExam, double finalAvg) {
		double finalSquareSum = 0;
		double finalStdDev;
		for (int k = 0; k < stuNumber; k++) {
			finalSquareSum += (finalExam[k] - finalAvg) * (finalExam[k] - finalAvg);
		}
		finalStdDev = Math.sqrt(finalSquareSum / stuNumber);
		return finalStdDev;
	}
	public static double[] midtermNormScore (int stuNumber, double[] midtermExam, double midtermAvg, double midtermStdDev) {
		double[] midtermNormScore = new double[stuNumber];
		for (int p = 0; p < stuNumber; p++) {
			midtermNormScore[p] = (midtermExam[p] - midtermAvg) / midtermStdDev;
		}
		return midtermNormScore;
	}
	public static double[] finalNormScore (int stuNumber, double[] finalExam, double finalAvg, double finalStdDev) {
		double[] finalNormScore = new double[stuNumber];
		for (int p = 0; p < stuNumber; p++) {
			finalNormScore[p] = (finalExam[p] - finalAvg) / finalStdDev;
		}
		return finalNormScore;
	}
	public static double[] midtermCurvedScore (int stuNumber, double[] midtermNormScore) {
		double[] midtermCurvedScore = new double[stuNumber];
		for (int i = 0; i < stuNumber; i++) {
			if (midtermNormScore[i] >= 2.0) {
				midtermCurvedScore[i] = 100;
			} else if (midtermNormScore[i] >= 1.0) {
				midtermCurvedScore[i] = ((midtermNormScore[i] - 1.0) / (2.0 - 1.0)) * 
						(100.0 - 94.0) + 94.0; 
			} else if (midtermNormScore[i] >= 0.0) {
				midtermCurvedScore[i] = ((midtermNormScore[i] - 0.0) / (1.0 - 0.0)) * 
						(94.0 - 85.0) + 85.0;
			} else if (midtermNormScore[i] >= (-1.0)) {
				midtermCurvedScore[i] = ((midtermNormScore[i] - (-1.0)) / (0.0 - (-1.0))) * 
						(85.0 - 75.0) + 75.0;
			} else if (midtermNormScore[i] >= (-1.5)) {
				midtermCurvedScore[i] = ((midtermNormScore[i] - (-1.5)) / ((-1.0) - (-1.5))) * 
						(75.0 - 65.0) + 65.0;
			} else if (midtermNormScore[i] >= (-2.0)) {
				midtermCurvedScore[i] = ((midtermNormScore[i] - (-2.0)) / ((-1.5) - (-2.0))) * 
						(65.0 - 55.0) + 55.0;
			} else if (midtermNormScore[i] >= (-3.0)) {
				midtermCurvedScore[i] = ((midtermNormScore[i] - (-3.0)) / ((-2.0) - (-3.0))) * 
						(55.0 - 30.0) + 30.0;
			} else if (midtermNormScore[i] >= (-4.0)) {
				midtermCurvedScore[i] = ((midtermNormScore[i] - (-4.0)) / ((-3.0) - (-4.0))) * 
						(30.0 - 0.0) + 0.0;
			} else {
				midtermCurvedScore[i] = 0.0;
			}
		}
		return midtermCurvedScore;
	}
	public static double[] finalCurvedScore (int stuNumber, double[] finalNormScore) {
		double[] finalCurvedScore = new double[stuNumber];

		for (int i = 0; i < stuNumber; i++) {
			
			if (finalNormScore[i] >= 2.0) {
				finalCurvedScore[i] = 100;
			} else if (finalNormScore[i] >= 1.0) {
				finalCurvedScore[i] = ((finalNormScore[i] - 1.0) / (2.0 - 1.0)) * 
						(100.0 - 94.0) + 94.0; 
			} else if (finalNormScore[i] >= 0.0) {
				finalCurvedScore[i] = ((finalNormScore[i] - 0.0) / (1.0 - 0.0)) * 
						(94.0 - 85.0) + 85.0;
			} else if (finalNormScore[i] >= (-1.0)) {
				finalCurvedScore[i] = ((finalNormScore[i] - (-1.0)) / (0.0 - (-1.0))) * 
						(85.0 - 75.0) + 75.0;
			} else if (finalNormScore[i] >= (-1.5)) {
				finalCurvedScore[i] = ((finalNormScore[i] - (-1.5)) / ((-1.0) - (-1.5))) * 
						(75.0 - 65.0) + 65.0;
			} else if (finalNormScore[i] >= (-2.0)) {
				finalCurvedScore[i] = ((finalNormScore[i] - (-2.0)) / ((-1.5) - (-2.0))) * 
						(65.0 - 55.0) + 55.0;
			} else if (finalNormScore[i] >= (-3.0)) {
				finalCurvedScore[i] = ((finalNormScore[i] - (-3.0)) / ((-2.0) - (-3.0))) * 
						(55.0 - 30.0) + 30.0;
			} else if (finalNormScore[i] >= (-4.0)) {
				finalCurvedScore[i] = ((finalNormScore[i] - (-4.0)) / ((-3.0) - (-4.0))) * 
						(30.0 - 0.0) + 0.0;
			} else {
				finalCurvedScore[i] = 0.0;
			}
		}
		return finalCurvedScore;
	}
	public static int[] gradeCount (int stuNumber, double[] assignmentGrade, double[] particptGrade, 
			double[] midtermCurvedScore, double[] finalCurvedScore) {
		double grade;
		int[] count = new int[11];
		for (int j = 0; j < stuNumber; j++) {
			grade = assignmentGrade[j] * 0.4 + particptGrade[j] * 0.15 + midtermCurvedScore[j] *
					0.2 + finalCurvedScore[j] * 0.25;
			if (grade >= 94) {
				count[0]++;
			} else if (grade >= 90) {
				count[1]++;
			} else if (grade >= 86) {
				count[2]++;
			} else if (grade >= 83) {
				count[3]++;
			} else if (grade >= 80) {
				count[4]++;
			} else if (grade >= 76) {
				count[5]++;
			} else if (grade >= 73) {
				count[6]++;
			} else if (grade >= 70) {
				count[7]++;
			} else if (grade >= 65) {
				count[8]++;
			} else if (grade >= 60) {
				count[9]++;
			} else {
				count[10]++;
			}
		}
		return count;
	}
	public static void printResults (String[] letterGrade, int[] count) {
		for (int i=0; i<11;i++) {
			System.out.println(letterGrade[i] +": "+ count[i]);
		}
	}
}
