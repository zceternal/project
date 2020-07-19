package com.sankai.inside.crm.web.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.context.support.StaticApplicationContext;
import org.springframework.stereotype.Component;

import com.sankai.inside.crm.entity.CustomerList;
import com.sankai.inside.crm.entity.ImportExcelEntity;
import com.sankai.inside.crm.entity.ServiceResult;
import com.sankai.inside.crm.web.ueditor.define.State;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;



@Component
public class ExcelTool {
	/*
	 * 导入Excel
	 * */
	
	 public static ServiceResult<List<ImportExcelEntity>> getAllByExcel(String filePhth){
	        List<ImportExcelEntity> list=new ArrayList<ImportExcelEntity>();
	        ImportExcelEntity entity=null;
	        try {
	        	File file=new File(filePhth);
	        	if (!file.exists()) {
					return new ServiceResult("文件不存在");
				}
	            Workbook rwb=Workbook.getWorkbook(file);
	            Sheet rs=rwb.getSheet(0);//或者rwb.getSheet(0)
	            int clos=rs.getColumns();//得到所有的列
	            int rows=rs.getRows();//得到所有的行

	            for (int i = 1; i < rows; i++) {
	            	entity=new ImportExcelEntity();
	                for (int j = 0; j < 7; j++) {
	                    //第一个是列数，第二个是行数
	                	entity.setCustomerName(rs.getCell(j, i).getContents());//默认最左边编号也算一列 所以这里得++j
	                	
	                    entity.setCustomerAbbreviation(rs.getCell(++j, i).getContents());
	                    
	                    entity.setAddress(rs.getCell(++j, i).getContents());
	                    
	                    entity.setContactNmae(rs.getCell(++j, i).getContents());
	                    
	                    entity.setSex(rs.getCell(++j, i).getContents());
	                    
	                    entity.setPhone(rs.getCell(++j, i).getContents());
	                    
	                    entity.setCreateTime(rs.getCell(++j, i).getContents());
	                    
	                }
	                
	                list.add(entity);
	            }
	            rwb.close();
	            
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	        	return new ServiceResult("请检查文件是否符合规范");
	        } 
	        return new ServiceResult(list);
	        
	    }
	 
	 public static String ExportExcel(String fileName,List<CustomerList> list)
	 {
		 WritableWorkbook wwb;
         FileOutputStream fos;
         try {    
             fos = new FileOutputStream(fileName);
             wwb = Workbook.createWorkbook(fos);
             WritableSheet ws = wwb.createSheet("客户列表", 10);        // 创建一个工作表

            //    设置单元格的文字格式
            WritableFont wf = new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false,
                     UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
             WritableCellFormat wcf = new WritableCellFormat(wf);
             wcf.setVerticalAlignment(VerticalAlignment.CENTRE); 
             wcf.setAlignment(Alignment.CENTRE); 
             ws.setRowView(1, 500);

            //    填充数据的内容
             int i = 0;
             ws.addCell(new Label(1,0,"客户全称",wcf));
         	ws.addCell(new Label(2,0,"客户简称",wcf));
				ws.addCell(new Label(3,0,"沟通状态",wcf));
				ws.addCell(new Label(4,0,"客户类型",wcf));
				ws.addCell(new Label(5,0,"客户网址",wcf));
				ws.addCell(new Label(6,0,"客户电话",wcf));
				ws.addCell(new Label(7,0,"客户联系人",wcf));
				ws.addCell(new Label(8,0,"联系人电话",wcf));
				
             for (CustomerList item: list) {
            	ws.addCell(new Label(1,i+1,item.getName(),wcf));
            	ws.addCell(new Label(2,i+1,item.getShortName(),wcf));
				ws.addCell(new Label(3,i+1,item.getStatusName(),wcf));
				ws.addCell(new Label(4,i+1,item.getTypeName(),wcf));
				ws.addCell(new Label(5,i+1,item.getUrl(),wcf));
				ws.addCell(new Label(6,i+1,item.getPhone(),wcf));
				ws.addCell(new Label(7,i+1,item.getContactName(),wcf));
				ws.addCell(new Label(8,i+1,item.getContactPhone(),wcf));
				i++;
			}
            wwb.write();
            wwb.close();
            return "1";
        } catch (IOException e){return e.getMessage();
         } catch (RowsExceededException e){return e.getMessage();
         } catch (WriteException e){return e.getMessage();}
	 }

}
