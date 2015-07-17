//package com.bcgtgjyb.myweather.tool;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import android.content.Context;
//import android.util.Log;
//
//import com.bcgtgjyb.myweather.model.WeatherDB;
//
//import net.sourceforge.pinyin4j.PinyinHelper;
//import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
//import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
//import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
//import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
//
//public class ChineseChange {
//	private WeatherDB weatherDB;
//	
//	public ChineseChange() {
//	}
//	public ChineseChange(Context context) {
//		weatherDB=WeatherDB.getInstence(context);
//			new Thread(new Runnable() {
//			public void run() {
//				
//				List list=weatherDB.getTown();
//				List pinyin =new ArrayList<String>();
//				int i=1;
//				for(Object obj:list){
//					String py=cn2Spell(obj.toString());
//					weatherDB.savePinyin(py,i+"");
//					Log.i("ChineseChange", i+"***"+py);
//					pinyin.add(py);
//					i++;
//				}
//				
//			}
//		}).start();
//		
//	}
//	
//	
//	 public static String cn2Spell(String chinese) { 
//         StringBuffer pybf = new StringBuffer(); 
//         char[] arr = chinese.toCharArray(); 
//         HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat(); 
//         defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE); 
//         defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE); 
//         for (int i = 0; i < arr.length; i++) { 
//                 if (arr[i] > 128) { 
//                         try { 
//                                 pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat)[0]); 
//                         } catch (BadHanyuPinyinOutputFormatCombination e) { 
//                                 e.printStackTrace(); 
//                         } 
//                 } else { 
//                         pybf.append(arr[i]); 
//                 } 
//         } 
//         return pybf.toString(); 
// }
//}
