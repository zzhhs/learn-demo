package com.example.demo;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;


public class test {

//    // 返回Docx中需要替换的特殊字符，没有重复项
//    // 推荐传入正则表达式参数"\\$\\{[^{}]+\\}"
//    public ArrayList<String> getReplaceElementsInWord(String filePath,
//                                                      String regex) {
//        String[] p = filePath.split("\\.");
//        if (p.length > 0) {// 判断文件有无扩展名
//            // 比较文件扩展名
//            if (p[p.length - 1].equalsIgnoreCase("doc")) {
//                ArrayList<String> al = new ArrayList<>();
//                File file = new File(filePath);
//                HWPFDocument document = null;
//                try {
//                    InputStream is = new FileInputStream(file);
//                    document = new HWPFDocument(is);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Range range = document.getRange();
//                String rangeText = range.text();
//                CharSequence cs = rangeText.subSequence(0, rangeText.length());
//                Pattern pattern = Pattern.compile(regex);
//                Matcher matcher = pattern.matcher(cs);
//                int startPosition = 0;
//                while (matcher.find(startPosition)) {
//                    if (!al.contains(matcher.group())) {
//                        al.add(matcher.group());
//                    }
//                    startPosition = matcher.end();
//                }
//                return al;
//            } else if (p[p.length - 1].equalsIgnoreCase("docx")) {
//                ArrayList<String> al = new ArrayList<>();
//                XWPFDocument document = null;
//                try {
//                    document = new XWPFDocument(
//                            POIXMLDocument.openPackage(filePath));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                // 遍历段落
//                Iterator<XWPFParagraph> itPara = document
//                        .getParagraphsIterator();
//                while (itPara.hasNext()) {
//                    XWPFParagraph paragraph = (XWPFParagraph) itPara.next();
//                    String paragraphString = paragraph.getText();
//                    CharSequence cs = paragraphString.subSequence(0,
//                            paragraphString.length());
//                    Pattern pattern = Pattern.compile(regex);
//                    Matcher matcher = pattern.matcher(cs);
//                    int startPosition = 0;
//                    while (matcher.find(startPosition)) {
//                        if (!al.contains(matcher.group())) {
//                            al.add(matcher.group());
//                        }
//                        startPosition = matcher.end();
//                    }
//                }
//                // 遍历表
//                Iterator<XWPFTable> itTable = document.getTablesIterator();
//                while (itTable.hasNext()) {
//                    XWPFTable table = (XWPFTable) itTable.next();
//                    int rcount = table.getNumberOfRows();
//                    for (int i = 0; i < rcount; i++) {
//                        XWPFTableRow row = table.getRow(i);
//                        List<XWPFTableCell> cells = row.getTableCells();
//                        for (XWPFTableCell cell : cells) {
//                            String cellText = "";
//                            cellText = cell.getText();
//                            CharSequence cs = cellText.subSequence(0,
//                                    cellText.length());
//                            Pattern pattern = Pattern.compile(regex);
//                            Matcher matcher = pattern.matcher(cs);
//                            int startPosition = 0;
//                            while (matcher.find(startPosition)) {
//                                if (!al.contains(matcher.group())) {
//                                    al.add(matcher.group());
//                                }
//                                startPosition = matcher.end();
//                            }
//                        }
//                    }
//                }
//                return al;
//            } else {
//                return null;
//            }
//        } else {
//            return null;
//        }
//    }

    // 替换word中需要替换的特殊字符
    public static InputStream replaceAndGenerateWord(String srcPath,Map<String, String> map) {
        // 比较文件扩展名
        try {
            XWPFDocument document = new XWPFDocument(
                    POIXMLDocument.openPackage(srcPath));
            // 替换段落中的指定文字
            Iterator<XWPFParagraph> itPara = document
                    .getParagraphsIterator();
            while (itPara.hasNext()) {
                XWPFParagraph paragraph = (XWPFParagraph) itPara.next();
                List<XWPFRun> runs = paragraph.getRuns();
                for (int i = 0; i < runs.size(); i++) {
                    String oneparaString = runs.get(i).getText(
                            runs.get(i).getTextPosition());
                    for (Map.Entry<String, String> entry : map
                            .entrySet()) {
                        oneparaString = oneparaString.replace(
                                entry.getKey(), entry.getValue());
                    }
                    runs.get(i).setText(oneparaString, 0);
                }
            }
            // 替换表格中的指定文字
            Iterator<XWPFTable> itTable = document.getTablesIterator();
            while (itTable.hasNext()) {
                XWPFTable table = (XWPFTable) itTable.next();
                int rcount = table.getNumberOfRows();
                for (int i = 0; i < rcount; i++) {
                    XWPFTableRow row = table.getRow(i);
                    List<XWPFTableCell> cells = row.getTableCells();
                    for (XWPFTableCell cell : cells) {
                        String cellTextString = cell.getText();
                        for (Entry<String, String> e : map.entrySet()) {
                            if (cellTextString.contains(e.getKey()))
                                cellTextString = cellTextString
                                        .replace(e.getKey(),
                                                e.getValue());
                        }
                        cell.removeParagraph(0);
                        cell.setText(cellTextString);
                    }
                }
            }
            //转换为InputStream
            ByteArrayOutputStream bos = null;
            try {
                bos =  new ByteArrayOutputStream();
                document.write(bos);
                byte[] array = bos.toByteArray();
                InputStream result = new ByteArrayInputStream(array);
                return result;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }finally {
                if(bos != null){
                    bos.flush();
                    bos.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String filepathString = "C:\\Users\\sjkg\\Desktop\\123.docx";
        String destpathString = "C:\\Users\\sjkg\\Desktop\\123456.docx";
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "王五王五啊王力宏的辣味回答侯卫东拉网");
        map.put("3", "王五王五啊王力味回答侯卫东拉网");
        System.out.println(replaceAndGenerateWord(filepathString, map));
    }
}
