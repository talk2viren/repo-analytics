package com.report;

import com.dao.FileDAO;
import com.util.RepoUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.conf.Constants.DUPLICATE_REMOVED_FILE;
import static com.conf.Constants.FINAL_REPORT_FILE;
import static com.conf.Constants.ROW_DATA_FILE;
import static com.util.RepoUtil.getValidUser;

public class RemoveDup {

    public static void main(String[] args) {

        RemoveDup removeDup = new RemoveDup();
//        removeDup.removeDuplicatesFromRowFile();
        removeDup.consolidatedReport();

    }

    private void removeDuplicatesFromRowFile() {

        /* Loads row data from file */
        List<String[]> rowDataArrayList = new ArrayList<String[]>();

        List<String[]> rowFileData = FileDAO.fileReader(ROW_DATA_FILE);

        for (String[] dataRow : rowFileData) {

            dataRow[0] = getValidUser(dataRow[0]);

            rowDataArrayList.add(dataRow);
        }

        FileDAO.fileWriter(rowDataArrayList, DUPLICATE_REMOVED_FILE);

    }

    private void consolidatedReport() {

        List<String[]> rowFileData = FileDAO.fileReader(DUPLICATE_REMOVED_FILE);

        Map<String, String[]> dataMap = new HashMap<String, String[]>();

        for (String[] str : rowFileData) {

            if (dataMap.containsKey(str[0])) {
                dataMap.put(str[0], RepoUtil.addArrayValues(str,dataMap.get(str[0])));
            } else {
                dataMap.put(str[0], str);
            }
        }

        List<String[]> outDataList= new ArrayList<String[]>(dataMap.values());

        FileDAO.fileWriter(outDataList, FINAL_REPORT_FILE);

    }

}
