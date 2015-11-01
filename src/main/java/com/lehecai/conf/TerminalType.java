/**
 * 
 */
package com.lehecai.conf;

import org.apache.log4j.Logger;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Sunshow
 * 终端类型
 */
public class TerminalType extends IntegerBeanLabelItem {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TerminalType.class);

	private static final long serialVersionUID = 3103388582198018355L;

	
	private static List<TerminalType> items = new ArrayList<TerminalType>();
    private static List<TerminalType> queryItems = new ArrayList<TerminalType>();
	
	protected TerminalType(String name, int value, boolean queryOnly) {
		super(TerminalType.class.getName(), name, value);

        queryItems.add(this);
        if (!queryOnly) {
            items.add(this);
        }
	}

    protected TerminalType(String name, int value) {
        this(name, value, false);
    }
	
	public static TerminalType getItem(int value){
		try {
			return (TerminalType)TerminalType.getResult(TerminalType.class.getName(), value);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	public static List<TerminalType> getItems() {
		return items;
	}

    public static List<TerminalType> getItemsForQuery() {
        return queryItems;
	}
    public static final TerminalType hp_sh_terminalId  = new TerminalType("恒朋（上海）", 1);
    public static final TerminalType hp_cq_terminalId  = new TerminalType("恒朋（重庆）", 7);
    public static final TerminalType sdtc_master_terminalId  = new TerminalType("山东掌中奕（主）", 2);
    public static final TerminalType sdtc_standby_terminalId  = new TerminalType("山东掌中奕（备）", 3);
    public static final TerminalType sdtc_general_terminalId  = new TerminalType("山东掌中奕常规", 14);
    public static final TerminalType sdtc_jingcai_terminalId  = new TerminalType("山东掌中奕竞彩", 25);
    public static final TerminalType dyj_terminalId  = new TerminalType("大赢家", 4);
    public static final TerminalType lssh_terminalId  = new TerminalType("乐事胜豪1", 8);
    public static final TerminalType lssh_general_terminalId  = new TerminalType("乐事胜豪常规彩种", 23);
    public static final TerminalType lssh_leased_terminalId  = new TerminalType("乐事胜豪", 24);
    public static final TerminalType hzh_terminalId  = new TerminalType("海中实业", 9);
    public static final TerminalType hzh_backup_terminalId  = new TerminalType("海中实业",26);
    public static final TerminalType zhcw_terminalId  = new TerminalType("中彩网", 10);
    public static final TerminalType aicaipiao_push_terminalId  = new TerminalType("爱彩票push终端", 11);
    public static final TerminalType jxfc_terminalId  = new TerminalType("江西福彩", 13);
    public static final TerminalType jxfc2_terminalId  = new TerminalType("江西福彩2号终端", 49);
    public static final TerminalType jxtc_terminalId  = new TerminalType("江西体彩", 50);
    public static final TerminalType shjht_terminalId  = new TerminalType("上海锦贺添", 45);
    public static final TerminalType shjht_jx_terminalId  = new TerminalType("上海锦贺添（江西）", 46);
    public static final TerminalType bozhong_terminalId  = new TerminalType("博众", 15);
    public static final TerminalType bozhong2_terminalId  = new TerminalType("博众2号终端", 59);
    public static final TerminalType lsshdpt_terminalId  = new TerminalType("乐事胜豪大平台", 60);
    public static final TerminalType bozhong_ws_terminalId  = new TerminalType("博众ws终端", 61);
    public static final TerminalType bozhong_general_ws_terminalId  = new TerminalType("博众常规彩ws终端", 62);
    public static final TerminalType jncb_terminalId  = new TerminalType("济南彩博", 63);
    public static final TerminalType kminfo2_terminalId  = new TerminalType("凯米信息2号终端", 64);
    public static final TerminalType luckyLottery_new_terminalId  = new TerminalType("山东宏彩new终端", 65);
    public static final TerminalType joyveb_terminalId  = new TerminalType("畅想互联", 16);
    public static final TerminalType joyveb_bj_terminalId  = new TerminalType("畅想互联北京", 17);
    public static final TerminalType joyveb_bj_ssq_terminalId  = new TerminalType("畅想互联北京ssq终端", 29);
    public static final TerminalType joyveb_bj_ws_terminalId  = new TerminalType("畅想互联北京webService终端", 30);
    public static final TerminalType bozhong_tc_terminalId  = new TerminalType("博众体彩", 18);
    public static final TerminalType caitong_terminalId  = new TerminalType("彩通", 19);
    public static final TerminalType ruicai_terminalId  = new TerminalType("瑞彩", 20);
    public static final TerminalType yingcai_terminalId  = new TerminalType("盈彩", 21);
    public static final TerminalType cqlottery_terminalId  = new TerminalType("重庆乐透", 22);
    public static final TerminalType goucai8_terminalId  = new TerminalType("购彩8", 27);
    public static final TerminalType palmLottery_terminalId  = new TerminalType("掌中彩", 28);
    public static final TerminalType lssh2012store1_terminalId  = new TerminalType("乐事胜豪2012第1终端", 31);
    public static final TerminalType lssh2012store2_terminalId  = new TerminalType("乐事胜豪2012第2终端", 32);
    public static final TerminalType lssh2012store3_terminalId  = new TerminalType("乐事胜豪2012第3终端", 33);
    public static final TerminalType lssh2012store4_terminalId  = new TerminalType("乐事胜豪2012第4终端", 34);
    public static final TerminalType lssh2012store5_terminalId  = new TerminalType("乐事胜豪2012第5终端", 35);
    public static final TerminalType lssh2012store6_terminalId  = new TerminalType("乐事胜豪2012第6终端", 36);
    public static final TerminalType lssh2012store7_terminalId  = new TerminalType("乐事胜豪2012第7终端", 37);
    public static final TerminalType lssh2012store8_terminalId  = new TerminalType("乐事胜豪2012第8终端", 38);
    public static final TerminalType lssh2012store9_terminalId  = new TerminalType("乐事胜豪2012第9终端", 39);
    public static final TerminalType lssh2012store10_terminalId  = new TerminalType("乐事胜豪2012第10终端", 40);
    public static final TerminalType lssh2012store11_terminalId  = new TerminalType("乐事胜豪2012第11终端", 56);
    public static final TerminalType lssh2012store12_terminalId  = new TerminalType("乐事胜豪2012第12终端", 57);
    public static final TerminalType lssh2012store13_terminalId  = new TerminalType("乐事胜豪2012第13终端", 58);
    public static final TerminalType duLun_terminalId  = new TerminalType("杜伦", 41);
    public static final TerminalType zhx_terminalId  = new TerminalType("中湘", 42);
    public static final TerminalType goucai8_hi_terminalId  = new TerminalType("购彩吧（海南）", 43);
    public static final TerminalType lecai_terminalId  = new TerminalType("乐彩", 44);
    public static final TerminalType lecai_sd_terminalId  = new TerminalType("乐彩(山东)", 69);
    public static final TerminalType yxqm_terminalId  = new TerminalType("银溪齐美", 70);
    public static final TerminalType kminfo_terminalId  = new TerminalType("凯米信息", 47);
    public static final TerminalType bozhong_jc_terminalId  = new TerminalType("博众竞彩", 48);
    public static final TerminalType luckyLottery_terminalId  = new TerminalType("山东宏彩", 51);
    public static final TerminalType cqLottery2013_terminalId  = new TerminalType("重庆乐透2013", 52);
    public static final TerminalType ruicai2013_terminalId  = new TerminalType("瑞彩2013", 53);
    public static final TerminalType thcai_terminalId  = new TerminalType("泰和彩", 54);
    public static final TerminalType tainets_terminalId  = new TerminalType("山东泰网", 55);
    public static final TerminalType cqLottery2013_hlj_terminalId  = new TerminalType("重庆乐透2013黑龙江", 66);
    public static final TerminalType lyht_terminalId  = new TerminalType("联移合通", 67);
    public static final TerminalType cqLottery2013_jc_terminalId  = new TerminalType("重庆乐透2013竞彩", 68);
    public static final TerminalType xmac_terminalId  = new TerminalType("厦门爱彩", 71);
	// 虚拟出票接口
	public static final TerminalType T_VIRTUAL_ALL_SUCCESS = new TerminalType("虚拟出票（成功）", 101);
	public static final TerminalType T_VIRTUAL_ALL_FAILURE = new TerminalType("虚拟出票（失败）", 102);
	public static final TerminalType T_VIRTUAL_ALL_RANDOM = new TerminalType("虚拟出票（随机）", 103);
}
