package com.report;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvClass {

//	Set debug print flag
	static boolean printFlag = false;

	private static String filteredData = "/WORKSPACE/PEARSON/repo/GITHUB/virendra/git-analytics/duplicate-removed.txt";
    private static String consolatedReport = "/WORKSPACE/PEARSON/repo/GITHUB/virendra/git-analytics/consolatedReport.txt";

	public static void main(String[] args) {

        List<String> myList = new ArrayList<String>();
        myList.add("1");
        myList.add("2");
        myList.add("3");
        myList.add("4");
        myList.add("5");

        for(String val:myList){
            if (val == ""){
                myList.add(2,"15");
            }
        }

        for (String myVal:myList){
            System.out.println(myVal);
        }



//        riportGenerator();
	}

    private static void riportGenerator() {
        System.out.println("Main method");

//		# printf "User,insertions,deletions,files,commits,lines changed"

        try {
            BufferedReader in = new BufferedReader(
                    new FileReader(filteredData));

            String str;
            Map<String, String[]> dataMap = new HashMap<String, String[]>();

            while ((str = in.readLine()) != null) {
                String[] ar = str.split(":");

                if (dataMap.containsKey(ar[0])) {
                    String[] ar1 = dataMap.get(ar[0]);
                    dataMap.put(ar[0], addArray(ar1, ar));
                } else {
                    dataMap.put(ar[0], ar);
                }
            }

//			Writing to file

            try (Writer writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(consolatedReport), "utf-8"))) {

                for (Map.Entry<String, String[]> entry : dataMap.entrySet()) {
                    String entryVal[] = entry.getValue();

                    printArray(entryVal, "Test Point2", printFlag);

                    writer.write(entryVal[0]+" : "+entryVal[1]+" : "+entryVal[2]+" : "+entryVal[3]+" : "+entryVal[4]+" : "+entryVal[5]+"\n");

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            in.close();

        } catch (IOException e) {
            System.out.println("File Read Error");
        }
    }

    //	Add Array Values
	private static String[] addArray(String[] firstArray, String[] secondArray) {
		int arrayCounter = 0;
		String[] returnArray = new String[7];

		for (String arg : firstArray) {
			try {
				returnArray[arrayCounter] = Integer
						.toString(Integer.parseInt(arg) + Integer.parseInt(secondArray[arrayCounter]));
				arrayCounter++;
			} catch (Exception e) {

				returnArray[arrayCounter] = arg;
				arrayCounter++;

				/* To bypass user details in array index zero */
				continue;
			}
		}

		printArray(returnArray, "Return Array", printFlag);

		return returnArray;
	}

	private static void printArray(String ar[], String message, boolean printFlag) {

		if (printFlag) {
			System.out.println("<<<===== " + message + " =======>>>");
			System.out.println(ar[0] + " : " + ar[1] + " : " + ar[2] + " : " + ar[3] + " : " + ar[4] + " : " + ar[5]);
		}

	}

}
