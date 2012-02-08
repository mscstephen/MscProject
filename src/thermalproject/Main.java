package thermalproject;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		DataCenter data = new DataCenter();
		/*Scanner scan=new Scanner(System.in);
		System.out.print("enter cooling plan for rack A(0-3): ");
		int a=scan.nextInt();
		System.out.print("enter cooling plan for rack B(0-3): ");
		int b=scan.nextInt();
		System.out.print("enter cooling plan for rack C(0-3): ");
		int c=scan.nextInt();
		System.out.print("enter cooling plan for rack D(0-3): ");
		int d=scan.nextInt();
		 *
		 */
		/*try{
		ReadWithScanner scanned=new ReadWithScanner("test.txt");
		data.coolingPlan=scanned.processLineByLine();
		}
		catch(Exception e)
		{System.out.println(e);
		System.out.println("a");}
		 */
		String outputFilename = "thermalmap.txt";
		String futureOutputname = "futurethermal.txt";
		try {
			FileWriter writer = new FileWriter(outputFilename);
			FileWriter futurewriter = new FileWriter(futureOutputname);
			PrintWriter pw = new PrintWriter(writer);
			PrintWriter pwf = new PrintWriter(futurewriter);
			System.out.println("a");
			DataCenter future = new DataCenter();
			data.runArray(1);
			data.updateCooling(data.futureCooling);
			ArrayList f;
			System.out.println("b");
			f = data.getTempArray();
			for (Object q : f) {
				pw.println(q);

			}
			writer.close();
			future = data;
			future.predictArray(300);
			f = future.getTempArray();
			for (Object q : f) {
				pwf.println(q);
			}

			System.out.println("c");
			futurewriter.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
