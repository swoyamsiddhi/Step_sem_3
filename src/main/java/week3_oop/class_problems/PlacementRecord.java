package week3_oop.class_problems;

import java.util.Scanner;

class PlacementRecord {
    String studentName;
    String company;
    double packageLpa;

    PlacementRecord(String studentName, String company, double packageLpa) {
        this.studentName = studentName;
        this.company = company;
        this.packageLpa = packageLpa;
    }

    void printRecord() {
        System.out.println(studentName + " -> " + company + " @ " + packageLpa + " LPA");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        PlacementRecord[] records = new PlacementRecord[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details of student " + (i + 1));

            System.out.print("Student name: ");
            String name = sc.nextLine();

            System.out.print("Company: ");
            String company = sc.nextLine();

            System.out.print("Package LPA: ");
            double packageLpa = sc.nextDouble();
            sc.nextLine();

            records[i] = new PlacementRecord(name, company, packageLpa);
        }

        System.out.println("\nPlacement Records:");
        for (int i = 0; i < n; i++) {
            records[i].printRecord();
        }

        sc.close();
    }
}
