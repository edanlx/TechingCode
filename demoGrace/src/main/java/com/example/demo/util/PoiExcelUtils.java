package com.example.demo.util;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;
import org.springframework.util.ReflectionUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.IntStream;

/**
 * https://blog.csdn.net/dreamofheart1/article/details/101295762
 *
 * @author 876651109@qq.com
 * @date 2021/3/8 2:44 下午
 */
public class PoiExcelUtils {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = null;
        XSSFWorkbook workbook = null;
        try {
            String filePath = "/Users/yooee/Documents/test2.xlsx";
            File file = new File(filePath);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            workbook = new XSSFWorkbook(bufferedInputStream);
            IOUtils.closeQuietly(bufferedInputStream);
            fileOutputStream = new FileOutputStream(file);
            Sheet sheet = workbook.getSheetAt(0);
//            comment(sheet.getRow(2).getCell(0), "我是comm", workbook);
//            valid(sheet.getRow(2).getCell(0), new String[]{"1", "2", "3"});
            // valid(sheet.getRow(2).getCell(0), new String[]{"1", "2", "3"});
            removeRowCustom(new HashSet<Integer>() {{
                add(6);
                add(8);
                add(9);
                add(10);
                add(12);
                add(13);
                add(14);
                add(15);
                add(16);
                add(17);
                add(18);
                add(19);
                add(20);
                add(21);
                add(22);
            }}, sheet);
            // valid(sheet.getRow(2).getCell(0), new String[]{"1", "2", "3"});
            workbook.write(fileOutputStream);
            fileOutputStream.flush();
        } finally {
            IOUtils.closeQuietly(fileOutputStream);
            IOUtils.closeQuietly(workbook);
        }
    }

    public static void simpleRemoveRow(Sheet sheet, List<Integer> rows) {
        Collections.reverse(rows);
        rows.forEach(s -> removeRow(sheet, s));
        delValidations(sheet, new HashSet<>(rows));
    }

    private static void delValidations(Sheet sheet, Set<Integer> delList) {
        Map<Integer, Map<Integer, String[]>> validations = getValidations(sheet);
        delValidations(sheet);
        int rowsNum = sheet.getPhysicalNumberOfRows();
        int removeRows = 0;
        for (int i = 0; i <= rowsNum; i++) {
            if (delList.contains(i)) {
                removeRows++;
                continue;
            }
            if (removeRows == 0) {
                continue;
            }
            if (sheet.getRow(i) == null) {
                continue;
            }
            int targetRowNum = i - removeRows;
            Map<Integer, String[]> integerMap = validations.get(i);
            if (integerMap == null) {
                continue;
            }
            for (Map.Entry<Integer, String[]> entry : integerMap.entrySet()) {
                valid(sheet.getRow(targetRowNum).getCell(entry.getKey()), entry.getValue());
            }
        }
    }


    public static void removeRow(Sheet sheet, int rowIndex) {
        int lastRowNum = sheet.getLastRowNum();
        if (rowIndex >= 0 && rowIndex < lastRowNum) {
            //将行号为rowIndex+1一直到行号为lastRowNum的单元格全部上移一行，以便删除rowIndex行
            sheet.shiftRows(rowIndex + 1, lastRowNum, -1, true, false);
        } else if (rowIndex == lastRowNum) {
            Row removingRow = sheet.getRow(rowIndex);
            if (removingRow != null) {
                sheet.removeRow(removingRow);
            }

        }
    }

    public static void removeRowCustom(Set<Integer> rows, Sheet sheet) {
        int rowsNum = sheet.getPhysicalNumberOfRows();
        Map<Integer, Map<Integer, String[]>> validations = getValidations(sheet);
        delValidations(sheet);
        int removeRows = 0;
        for (int i = 0; i <= rowsNum; i++) {
            if (rows.contains(i)) {
                sheet.removeRow(sheet.getRow(i));
                removeRows++;
                continue;
            }
            if (sheet.getRow(i) == null) {
                continue;
            }
            if(removeRows == 0){
                copyRow(sheet, sheet.getRow(i), sheet.getRow(i), true, validations);
            } else {
                copyRow(sheet, sheet.getRow(i), sheet.createRow(i - removeRows), true, validations);
            }
            if (removeRows == 0) {
                continue;
            }
            sheet.removeRow(sheet.getRow(i));
        }
    }


    public static void copyRow(Sheet sheet, Row sourceRow, Row distRow, boolean isCopyValue, Map<Integer, Map<Integer, String[]>> validations) {
        //设置高度
        // distRow.setHeightInPoints(sourceRow.getHeightInPoints());
        //复制合并的单元格
        if (sourceRow.getRowNum() != distRow.getRowNum()) {
            for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
                CellRangeAddress sourceRange = sheet.getMergedRegion(i);
                if (sourceRange.getFirstRow() == sourceRow.getRowNum()) {
                    CellRangeAddress distRange = new CellRangeAddress(distRow.getRowNum(),
                            distRow.getRowNum() + (sourceRange.getLastRow() - sourceRange.getFirstRow()),
                            sourceRange.getFirstColumn(), sourceRange.getLastColumn());
                    sheet.addMergedRegion(distRange);
                }
            }
        }
        //复制单元格
        for (int i = 0; i < sourceRow.getLastCellNum(); i++) {
            Cell soreCell = sourceRow.getCell(i);
            if (soreCell == null) {
                soreCell = sourceRow.createCell(i);
            }
            if (sourceRow.getRowNum() != distRow.getRowNum()) {
                copyCell(soreCell, distRow.createCell(i), isCopyValue);
            }
            if (validations == null) {
               continue;
            }
            Map<Integer, String[]> rowMap = validations.get(soreCell.getRowIndex());
            if (rowMap == null) {
                continue;
            }
            String[] validStr = rowMap.get(soreCell.getColumnIndex());
            if (validStr == null) {
                continue;
            }
            valid(distRow.getCell(i), validStr);
        }
    }

    public static void copyCell(Cell sourceCell, Cell distCell, boolean isCopyValue) {
        distCell.setCellStyle(sourceCell.getCellStyle());
        // 评论
        if (sourceCell.getCellComment() != null) {
            distCell.setCellComment(sourceCell.getCellComment());
        }
        // 数据格式
        CellType cellType = sourceCell.getCellType();
        // 复制数据
        if (isCopyValue) {
            switch (cellType) {
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(sourceCell)) {
                        distCell.setCellValue(sourceCell.getDateCellValue());
                    } else {
                        distCell.setCellValue(sourceCell.getNumericCellValue());
                    }
                    break;
                case STRING:
                    distCell.setCellValue(sourceCell.getRichStringCellValue());
                    break;
                case BOOLEAN:
                    distCell.setCellValue(sourceCell.getBooleanCellValue());
                    break;
                case ERROR:
                    distCell.setCellValue(sourceCell.getErrorCellValue());
                    break;
                case FORMULA:
                    distCell.setCellValue(sourceCell.getCellFormula());
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 设置全边框
     *
     * @param cellStyle
     * @param borderStyle
     * @param indexedColors
     * @author 876651109@qq.com
     * @date 2021/3/5 4:04 下午
     */
    public static void border(CellStyle cellStyle, BorderStyle borderStyle, IndexedColors indexedColors) {
        cellStyle.setBorderBottom(borderStyle);
        cellStyle.setBorderLeft(borderStyle);
        cellStyle.setBorderRight(borderStyle);
        cellStyle.setBorderTop(borderStyle);
        cellStyle.setBottomBorderColor(indexedColors.getIndex());
        cellStyle.setLeftBorderColor(indexedColors.getIndex());
        cellStyle.setRightBorderColor(indexedColors.getIndex());
        cellStyle.setLeftBorderColor(indexedColors.getIndex());
    }

    /**
     * 设置超链接
     *
     * @param cell
     * @param url
     * @param workbook
     * @author 876651109@qq.com
     * @date 2021/3/5 4:07 下午
     */
    public static void hyp(Cell cell, String url, Workbook workbook) {
        Hyperlink hyperlink = workbook.getCreationHelper().createHyperlink(HyperlinkType.DOCUMENT);
        hyperlink.setAddress(url);
        cell.setHyperlink(hyperlink);
    }

    /**
     * 设置批注
     *
     * @param cell
     * @param text
     * @param workbook
     * @author 876651109@qq.com
     * @date 2021/3/5 4:10 下午
     */
    public static void comment(Cell cell, String text, Workbook workbook) {
        cell.removeCellComment();
        Comment cellComment = cell.getSheet().createDrawingPatriarch().createCellComment(workbook.getCreationHelper().createClientAnchor());
        cellComment.setColumn(cell.getColumnIndex());
        cellComment.setRow(cell.getRowIndex());
        cellComment.setString(workbook.getCreationHelper().createRichTextString(text));
    }

    /**
     * 设置下拉
     *
     * @param cell
     * @param data
     * @author 876651109@qq.com
     * @date 2021/3/5 4:11 下午
     */
    public static void valid(Cell cell, String[] data) {
        DataValidationHelper dataValidationHelper = cell.getSheet().getDataValidationHelper();
        DataValidationConstraint explicitListConstraint = dataValidationHelper.createExplicitListConstraint(data);
        CellRangeAddressList cellRangeAddressList = new CellRangeAddressList(cell.getRowIndex(), cell.getRowIndex(), cell.getColumnIndex(), cell.getColumnIndex());
        DataValidation validation = dataValidationHelper.createValidation(explicitListConstraint, cellRangeAddressList);
        validation.setSuppressDropDownArrow(true);
        validation.setShowErrorBox(true);
        cell.getSheet().addValidationData(validation);
    }

    /**
     * <row-col>
     *
     * @param sheet
     * @return {@link Map<Integer, Map<Integer, String[]>>}
     * @author 876651109@qq.com
     * @date 2021/3/8 2:09 下午
     */
    public static Map<Integer, Map<Integer, String[]>> getValidations(Sheet sheet) {
        Map<Integer, Map<Integer, String[]>> result = new HashMap<>(8);
        List<? extends DataValidation> dataValidationList = sheet.getDataValidations();
        if (dataValidationList == null || dataValidationList.size() == 0) {
            return result;
        }
        for (DataValidation dataValidation : dataValidationList) {
            CellRangeAddressList regions = dataValidation.getRegions();
            for (int i = 0; i < regions.countRanges(); i++) {
                CellRangeAddress cellRangeAddress = regions.getCellRangeAddress(i);
                for (int row = cellRangeAddress.getFirstRow(); row <= cellRangeAddress.getLastRow(); row++) {
                    Map<Integer, String[]> colMap = result.getOrDefault(cellRangeAddress.getFirstRow(), new HashMap<>());
                    IntStream.rangeClosed(cellRangeAddress.getFirstColumn(), cellRangeAddress.getLastColumn()).forEach(s ->
                            colMap.put(s, dataValidation.getValidationConstraint().getExplicitListValues()));
                    result.put(cellRangeAddress.getFirstRow(), colMap);
                }
            }
        }
        return result;
    }

    public static void delValidations(Sheet sheet) {
        try {
            Field field = ReflectionUtils.findField(sheet.getClass(), "worksheet");
            ReflectionUtils.makeAccessible(field);
            CTWorksheet worksheet = (CTWorksheet) field.get(sheet);
            Node domNode = worksheet.getDomNode();
            NodeList childNodes = domNode.getChildNodes();
            int size = childNodes.getLength();

            for (int i = 0; i < size; i++) {
                Node childNode = childNodes.item(i);
                // if node name is dataValidations
                if ("dataValidations".equals(childNode.getNodeName())) {
                    // remove childnode from worksheet
                    domNode.removeChild(childNode);
                    break;
                }
            }

        } catch (Exception e) {
            // TODO
        }
    }
}
