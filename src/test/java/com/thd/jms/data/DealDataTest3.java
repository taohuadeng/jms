package com.thd.jms.data;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.*;

public class DealDataTest3 {
    public static void main(String[] args) throws IOException, BiffException {
        Workbook workbook = null;
        InputStream inputStream = new FileInputStream("F:/1.xls");
        workbook = Workbook.getWorkbook(inputStream);
        Sheet sheet = workbook.getSheet(0);
        int rsColumns = sheet.getColumns();
        int rsRows = sheet.getRows();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        Set<String> set = new HashSet<String>();
        System.out.println(rsRows);
        for (int i = 0; i < rsRows; i++) {

            Cell cell0 = sheet.getCell(0, i);
            String projectId = cell0.getContents();
            Cell cell1 = sheet.getCell(1, i);
            String paperId = cell1.getContents();
            String sql ="'"+paperId+"',";

            if ("ZB201503".equals(projectId)) {
                set.add(sql);
            }
        }

        Iterator iterator = set.iterator();
        File file = new File("F:/1.sql");
        FileWriter fw = null;
        BufferedWriter writer = null;
        try {
            fw = new FileWriter(file);
            writer = new BufferedWriter(fw);
            while (iterator.hasNext()) {
                writer.write(iterator.next().toString());
                writer.newLine();//换行
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(writer);
            IOUtils.closeQuietly(fw);
        }
    }

}

