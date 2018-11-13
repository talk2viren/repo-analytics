package com.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.conf.Constants.DELIMITER;
import static com.conf.Constants.FILE_LOCATION;

public class FileDAO {
    /**
     * Read input files
     *
     * @param fileName
     * @return
     */
    public static List<String[]> fileReader(String fileName) {

        List<String[]> dataList = new ArrayList<String[]>();

        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader(FILE_LOCATION + fileName));
            String str;
            while ((str = reader.readLine()) != null) {
                String[] ar = str.split(DELIMITER);

                /*New String array to have smaller case values*/
                String[] returnStr = new String[ar.length];

                int i = 0;
                for (String user : ar) {
                    returnStr[i] = user.toLowerCase();
                    i++;
                }
                dataList.add(returnStr);
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    /**
     * Write output to file
     *
     * @param fileName
     * @param dataArray
     */
    public static void fileWriter(List<String[]> dataArray, String fileName) {

        try (Writer writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(FILE_LOCATION + fileName), "utf-8"))) {

            /*To avoid delimiter before first value*/
            boolean delimiterFlag = false;

            for (String[] dataRow : dataArray) {

                for (String data : dataRow) {

                    if (delimiterFlag) {
                        writer.write(DELIMITER);
                    } else {
                        delimiterFlag = true;
                    }

                    writer.write(data);

                }

                ((BufferedWriter) writer).newLine();
                delimiterFlag = false;

            }

            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
