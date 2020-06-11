package chen.write;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * 向excel中写入数据
 * */
public class Demo2 {
    public static void main(String[] args) throws FileNotFoundException {
        //1.创建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        //2.创建共作表
        XSSFSheet sheet = workbook.createSheet(" 工作表");

        for (int i = 1; i <=9 ; i++) {
            //3.创建行
            XSSFRow row = sheet.createRow(i-1);
            for (int j = 1; j <=i ; j++) {
                //4.创建单元格
                row.createCell(j-1).setCellValue(j+"*"+i+"="+j*i);
            }
        }
        //判断该文件是否存在
        existsDelete("F:\\hello","hello1.xlsx");
        //输出流
        FileOutputStream outputStream = new FileOutputStream("F:\\hello\\hello1.xlsx");
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("文件创建成功");
        try {
            outputStream.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //判断某个文件是否再某个文件夹内,如果存在就删除.
    public static void existsDelete(String dirPath,String fileName) {
        File pathFile = new File(dirPath);
        if(!pathFile.exists() || pathFile.isFile()) {
            return;
        }
        for(File file:pathFile.listFiles()) {
            if(file.isFile() && fileName.equals(file.getName())) {
                file.delete();
                break;
            }
        }
    }
}