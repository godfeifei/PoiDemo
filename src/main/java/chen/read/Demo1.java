package chen.read;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

/*
* 使用poi读取excel文件
* */
public class Demo1 {
    public static void main(String[] args) throws IOException {
        //1.获取工作簿
        XSSFWorkbook sheets = new XSSFWorkbook("F:\\hello\\hello.xlsx");
        //2.获取工作表  getSheetAt(0)根据工作表的位置  getSheet("")根据工作表的名字
        XSSFSheet sheetAt = sheets.getSheetAt(0);
        //3.获取行  方式一
//        for (Row cells : sheetAt) {
//            //4.获取单元格
//            for (Cell cell : cells) {
//                //获取单元格的内容
//                String stringCellValue = cell.getStringCellValue();
//                System.out.println(stringCellValue);
//            }
//        }
        //方式二  开始索引  结束索引
        int lastRowNum = sheetAt.getLastRowNum();//获取该工作表共有几行
        for (int i = 0; i <=lastRowNum; i++) {
            XSSFRow row = sheetAt.getRow(i);//获取第i行的数据
            if(row!=null){
                short lastCellNum = row.getLastCellNum();//获取i行由几个单元格
                for (int j = 0; j <=lastCellNum ; j++) {
                    XSSFCell cell = row.getCell(j);//获取第j个单元格
                    if(cell!=null){
                        String stringCellValue = cell.getStringCellValue();//获取第j个单元格的内容
                        System.out.println(stringCellValue);
                    }
                }
            }
        }


        //释放资源
        sheets.close();
    }
}
