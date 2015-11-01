package sun;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.lehecai.conf.LogContent;
import com.lehecai.pagemodel.Json;
import com.lehecai.pagemodel.OperateLogModel;
import com.lehecai.pagemodel.Pie;
import com.lehecai.util.JsonUtil;

public class JsonTest {

	/** 
	 * @Title: main 
	 * @Description: TODO
	 * @param args 
	 * void
	 * @author sunqp
	 */
	public static void main(String[] args) {
		t5();
	}
	public static void t5(){
		String str = "{\"1\":{\"num\":20,\"pt\":20,\"pst\":20,\"tc\":54,\"time\":2465},\"4\":{\"num\":6,\"pt\":6,\"pst\":6,\"tc\":7,\"time\":457}}";
		JSONObject json = JSONObject.fromObject(str);
		Iterator<String> keys=json.keys();
		while(keys.hasNext()){
			String key = keys.next().toString();
			String _value = json.get(key).toString();
			System.out.println(key+";"+""+_value);
		}
	}
	public static void t4(String temp){
		temp = temp.substring(temp.indexOf("{"), temp.lastIndexOf("}")+1);
		JSONObject myJsonObject = JSONObject.fromObject(temp);
		String _ticketBatchId = "";
		JSONObject ticketBatchIdJson = new JSONObject();
		if(temp.contains(LogContent.ticketBatchId)){
			_ticketBatchId = myJsonObject.getString(LogContent.ticketBatchId)+"";
			if(ticketBatchIdJson.has(_ticketBatchId)){
				String _value = ticketBatchIdJson.getString(_ticketBatchId);
				int aNum = Integer.parseInt(_value.substring(1, _value.indexOf(",")));
				long _constTimeValue = Long.parseLong(_value.substring(_value.indexOf(",")+1,_value.indexOf("}")));
				_constTimeValue += myJsonObject.getInt(LogContent.costTime);
				ticketBatchIdJson.put(_ticketBatchId, "{"+(++aNum)+","+_constTimeValue+"}");
			}else
				ticketBatchIdJson.put(_ticketBatchId, "{1,"+myJsonObject.getInt(LogContent.costTime)+"}");
		}
		System.out.println(ticketBatchIdJson.get(_ticketBatchId));
	}
	
	public static void t1(){
		String str = "{\"logType\":\"allot\",\"lotteryType\":50,\"tiketCount\":1,\"costTime\":116}";
		try {
			JSONObject js = JSONObject.fromObject(str);
			JSONArray array = js.names();
			List m = JsonUtil.getList4Json(js.names().toString(),Pie.class);
			System.out.println(m);
			/*JSONObject js = JSONObject.fromObject(str);
			int a = js.getInt(LogContent.lotteryType);
			JSONArray names = js.names();
			Object[] s = names.toArray();
			System.out.println(s);*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List t2(String jsonString){
		List<Json> lst = new ArrayList<Json>();
		try {
			Map map = JsonUtil.getMap4Json(jsonString);
			Iterator it = map.keySet().iterator();
			while(it.hasNext()){
				Json j = new Json();
				String key = it.next().toString();
				j.setName(key);
				j.setValue((Integer) map.get(key));
				lst.add(j);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lst;
	}
	public static void t3(){
		String str = "{\"52\":\"{3,942}\",\"560\":\"{6,1400}\",\"51\":\"{2,384}\"}";
		try {
			Map map = JsonUtil.getMap4Json(str);
			Iterator it = map.keySet().iterator();
			while(it.hasNext()){
				OperateLogModel u = new OperateLogModel();
				String key = it.next().toString();
				String _value = map.get(key).toString();
				String _planNum = _value.substring(1, _value.indexOf(","));
				String _planAllTime = _value.substring(_value.indexOf(",")+1,_value.indexOf("}"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
