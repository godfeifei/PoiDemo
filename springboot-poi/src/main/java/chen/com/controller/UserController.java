package chen.com.controller;

import chen.com.pojo.User;
import chen.com.service.UserService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/daoru")
    @ResponseBody
    public String  daoru(){
        List<User> read = read();
        userService.add(read);
        return "OK";
    }
    @RequestMapping("/daochu")
    @ResponseBody
    public String  daochu(){
        List<User> all = userService.findAll();
        write(all);
        return "OK";
    }
    //读取文件
    public  List<User> read() {
        List<User> ulist = new ArrayList<>();
        try {
            //获取工作簿
            XSSFWorkbook sheets = new XSSFWorkbook("F:\\hello\\user.xlsx");
            //获取工作表
            XSSFSheet sheetAt = sheets.getSheetAt(0);
            //获取行的总数
            int lastRowNum = sheetAt.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                XSSFRow row = sheetAt.getRow(i);//获取行,但不要第一行
                if (row != null) {
                    List<String> list = new ArrayList<>();
                    for (Cell cell : row) {
                        if (cell != null) {
                            cell.setCellType(cell.CELL_TYPE_STRING);//将单元格的格式设置为字符串
                            String stringCellValue = cell.getStringCellValue();//获取单元格的值
                            if (stringCellValue != null && !"".equals(stringCellValue)) {
                                list.add(stringCellValue);//将单元格的值存入list中.
                            }
                        }
                    }
                    if (list.size() != 0) {
                        User user = new User(Integer.parseInt(list.get(0)), list.get(1), list.get(2), list.get(3), Integer.parseInt(list.get(4)));
                        ulist.add(user);
                    }
                }
            }
            //获取单元格
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ulist;
    }

    //导入文件
    public void write(List<User> list){
        //1.创建工作薄
        XSSFWorkbook xssfWorkbook=new XSSFWorkbook();
        //2.创建工作表
        XSSFSheet sheet = xssfWorkbook.createSheet("用户信息");
        //创建单元格的样式
        XSSFCellStyle cellStyle = xssfWorkbook.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.PINK.getIndex());//设置颜色
        cellStyle.setFillPattern(cellStyle.SOLID_FOREGROUND);//设置颜色的填充规则
        //创建字体样式
        XSSFFont font = xssfWorkbook.createFont();
        font.setFontName("黑体");//设置字体样式
        font.setColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFont(font);//将字体的样式放入到单元格的样式中
        //3.创建行
        XSSFRow row = sheet.createRow(0);
//        row.createCell(0).setCellValue("id");
//        row.createCell(1).setCellValue("name");
//        row.createCell(2).setCellValue("address");
//        row.createCell(3).setCellValue("sex");
//        row.createCell(4).setCellValue("age");
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("id");
        cell.setCellStyle(cellStyle);//将单元格的样式这只给指定的单元格
        XSSFCell cell2 = row.createCell(1);
        cell2.setCellValue("name");
        cell2.setCellStyle(cellStyle);//将单元格的样式这只给指定的单元格
        XSSFCell cell3 = row.createCell(2);
        cell3.setCellValue("address");
        cell3.setCellStyle(cellStyle);//将单元格的样式这只给指定的单元格
        XSSFCell cell4 = row.createCell(3);
        cell4.setCellValue("sex");
        cell4.setCellStyle(cellStyle);//将单元格的样式这只给指定的单元格
        XSSFCell cell5 = row.createCell(4);
        cell5.setCellValue("age");
        cell5.setCellStyle(cellStyle);//将单元格的样式这只给指定的单元格
        for (int i = 0; i <list.size() ; i++) {
            XSSFRow row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(list.get(i).getId());
            row1.createCell(1).setCellValue(list.get(i).getName());
            row1.createCell(2).setCellValue(list.get(i).getAddress());
            row1.createCell(3).setCellValue(list.get(i).getSex());
            row1.createCell(4).setCellValue(list.get(i).getAge());
        }
        try {
            existsDelete("F:\\hello","user1.xlsx");//判断文件是否存在,存在就删除
            FileOutputStream fileOutputStream=new FileOutputStream("F:\\hello\\user1.xlsx");
            xssfWorkbook.write(fileOutputStream);
            fileOutputStream.flush();//刷新
            fileOutputStream.close();
            xssfWorkbook.close();
            System.out.println("写入成功");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

    public static void main(String[] args) {
        List<Integer> list1= new CopyOnWriteArrayList<>();//Collections.synchronizedList(new ArrayList<>());//new Vector<>();//new ArrayList<>();
        Set<Integer> set=new CopyOnWriteArraySet<>();
        Map<String,Integer> map=new ConcurrentHashMap<>();
        list1.add(1);
        List<Integer> list2=list1;
        list2.add(2);
        list1=list2.subList(1, list2.size());
        System.out.println(list1);
        list1.set(0,3);
        System.out.println(list2);

        System.out.println(list1);



    }
}
