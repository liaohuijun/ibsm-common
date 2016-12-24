package com.hm.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author shishun.wang
 * @date 下午12:02:44 2016年11月18日
 * @version 1.0
 * @describe
 */
public class ExecelUtil {

	private ExecelUtil() {
	}

	public static class Export {

		private Export() {
		}

		public static Workbook initWorkbook(FileInputStream fileInputStream) throws IOException {
			if (CommonUtil.isEmpty(fileInputStream)) {
				try {
					return new XSSFWorkbook();
				} catch (Exception e) {
					return new HSSFWorkbook();
				}
			} else {
				try {
					return new XSSFWorkbook(fileInputStream);
				} catch (Exception e) {
					return new HSSFWorkbook(fileInputStream);
				}
			}
		}

		public static File clone(Sheet cloneSheet, Integer cloneRowIndex, Map<String, Object> data,
				SheetWriterDataHandler handler) throws IOException {
			Workbook tempWorkbook = null;
			try {
				tempWorkbook = initWorkbook(null);
				Sheet createSheet = tempWorkbook.createSheet("第一页");

				for (int rowNum = 0; rowNum < cloneRowIndex; rowNum++) {
					Row createRow = createSheet.createRow(rowNum);
					cloneSheet.getRow(rowNum).forEach(cell -> {
						if (CommonUtil.isNotEmpty(cell)) {
							String cellStl = cell.toString().trim();
							Cell createCell = createRow.createCell(cell.getColumnIndex());
							if (CommonUtil.isNotEmpty(data.get(cellStl))) {
								createCell.setCellValue(data.get(cellStl) + "");
							} else {
								createCell.setCellValue(cellStl);
							}
						}
					});
				}

				handler.write(createSheet, cloneRowIndex);

				File tempFile = File.createTempFile(UUID.randomUUID().toString(), ".xlsx");
				tempFile.deleteOnExit();
				tempWorkbook.write(new FileOutputStream(tempFile));

				return tempFile;
			} finally {
				if (null != tempWorkbook)
					tempWorkbook.close();
			}
		}

		@FunctionalInterface
		public interface SheetWriterDataHandler {

			void write(Sheet excelSheet, Integer cloneRowIndex);

		}
	}
}
