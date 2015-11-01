package sun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "{\"logType\":\"split\",\"lotteryType\":52,\"planTotal\":2,\"planSuccessCount\":2,\"costTime\":397}";
		String str1 = "2015-08-06 15:40:32,162 - [常规彩种拆票处理线程] - {\"logType\":\"split\",\"lotteryType\":52,\"planTotal\":2,\"planSuccessCount\":2,\"costTime\":397}";
		t5(str1);
		//		t2(str);
//		t3();
	}
	public static void t5(String temp){
		String dateTemp = temp.substring(0,10);
		System.out.println(dateTemp);
	}
	public static void t4(){
		int a = 1;
		System.out.println(++a);
	}
	public static void t3(){
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		int arr[]={42,31,55,31,42,53,42};
		for(int i = 0;i<arr.length;i++){
			int a = 0;
			int num = arr[i];
			if(map.containsKey(num)){
				a = map.get(num);
				System.out.println(a);
				map.put(num, ++a);
			}else{
				map.put(num, 1);
			}
			System.out.println(map);
		}
	}
	public static void t2(String jsonString){
		String value1 = "";
		int value2 = 0;
		int value3 = 0;
		JSONObject myJsonObject = JSONObject.fromObject(jsonString);
		value1 = myJsonObject.getString("logType");
		value2 = myJsonObject.getInt("lotteryType");
		value3 = myJsonObject.getInt("planTotal");
		System.out.println("value1="+value1+";value2="+value2+";value3="+value3);
	}
	public static String[] split(String str, char delim) {
        
        if(str == null) return null;
        if(str.trim().equals("")) return new String[0];
        
        int count = 1;
        char c[] = str.toCharArray();
        for(int i = 0; i < c.length; i++) {
            if(c[i] == delim) {
                count++;
            }
        }

        String s[] = new String[count];
        int index = 0;
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < c.length; i++) {
            if(c[i] == delim) {
                s[index] = sb.toString();
                sb = new StringBuffer();
                index ++;
            } else {
                sb.append(c[i]);
            }
        }
        s[index] = sb.toString();
        return s;
    }
}
