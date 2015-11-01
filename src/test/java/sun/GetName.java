package sun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;

import com.lehecai.conf.LotteryType;
import com.lehecai.conf.TerminalType;
import com.lehecai.pagemodel.OperateLogModel;

public class GetName {
	public static String getNameById(){
		LotteryType lotteryType = LotteryType.getItem(20);
		return lotteryType.getName();
	}
	public static String getTerminalIdName(){
		TerminalType terminalType = TerminalType.getItem(9);
		/*Set<Integer> kset = SystemConfig.catemap.keySet();
		for(int ks:kset){
			if(cateid==SystemConfig.catemap.get(ks).cateid){
				return SystemConfig.catemap.get(ks);
			}
		}*/
		return terminalType.getName();
	}
	public static void t4(){
		JSONArray lotteryType = null;
	}
	public static void main(String[] args) {
//		System.out.println(getTerminalIdName());
		test3();
	}
	public static void test3(){
		List<OperateLogModel> lst = new ArrayList<OperateLogModel>();
		OperateLogModel o1 = new OperateLogModel();
		o1.setAnalyName("t1");
		o1.setAnalyNum(23);
		o1.setAnalyAveTime(22);
		o1.setAnalyAllTime(34);
		OperateLogModel o2 = new OperateLogModel();
		o2.setAnalyName("t2");
		o2.setAnalyNum(5353);
		o2.setAnalyAveTime(3);
		o2.setAnalyAllTime(144);
		OperateLogModel o3 = new OperateLogModel();
		o3.setAnalyName("t3");
		o3.setAnalyNum(4443);
		o3.setAnalyAveTime(212);
		o3.setAnalyAllTime(414);
		OperateLogModel o4 = new OperateLogModel();
		o4.setAnalyName("t4");
		o4.setAnalyNum(1112);
		o4.setAnalyAveTime(2);
		o4.setAnalyAllTime(323);
		lst.add(o1);
		lst.add(o2);
		lst.add(o3);
		lst.add(o4);
		sortByBeanComparator(lst,"analyAllTime");
		for(int i=0;i<lst.size();i++){
			System.out.println(lst.get(i).getAnalyName()+";"+lst.get(i).getAnalyAveTime());
		}
	}
	public static List<OperateLogModel> sortByBeanComparator(List<OperateLogModel> list,String sortName){
		Comparator comparator = ComparableComparator.getInstance();
	    comparator = ComparatorUtils.nullLowComparator(comparator);   
	    comparator = ComparatorUtils.reversedComparator(comparator);
	    List<BeanComparator> comparas = new ArrayList<BeanComparator>();
	    comparas.add(new BeanComparator(sortName, comparator));
	    ComparatorChain comparatorChain = new ComparatorChain(comparas);
	    Collections.sort(list,comparatorChain);
	    return list;
	}
}
