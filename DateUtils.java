package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	//获取 8 位 时间   yyyyMMdd
	public static String getTime8() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String now = sdf.format(date);
		return now;
	}

	//获取 14 位 时间   yyyyMMddHHmmss
	public static String getTime14() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String now = sdf.format(date);
		return now;
	}

	// 剩余时间 xx 天 00:00:00
	public static String getShengYuTime(String str) throws ParseException {
		String ShengYu = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Date now = new Date();
		Date date = df.parse(str);
		long l = now.getTime() - date.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		if (day == 0 && hour != 0) {
			hour = -hour;
			min = -min;
			ShengYu = ifLength2(hour) + " : " + ifLength2(min) + " : 00";
		} else if (day == 0 && hour == 0) {
			min = -min;
			ShengYu = ifLength2(min) + "分钟";
		} else {
			day = -day;
			hour = -hour;
			min = -min;
			ShengYu = "" + day + "天 " + ifLength2(hour) + ":" + ifLength2(min);
		}
		return ShengYu;
	}

	//  xx 前
	public static String DatetoString(String str) throws ParseException {
		String mailTime = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdf16 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = sdf.parse(str);
		long time = date.getTime();
		long ctime = new Date().getTime();
		long jtime = ctime - time;
		long s = jtime / 1000;
		if (s > 0 && s < 60) {
			mailTime = "刚刚";
		} else if (s >= 60 && s < (60 * 60)) {
			mailTime = s / 60 + "分钟前";
		} else if (s >= (60 * 60) && s < (60 * 60 * 24)) {
			mailTime = s / (60 * 60) + "小时前";
		} else if (s >= (60 * 60 * 24) && s < (60 * 60 * 24 * 30)) {
			mailTime = s / (60 * 60 * 24) + "天前";
		} else if (s > (60 * 60 * 24 * 30) && s < (60 * 60 * 24 * 30 * 12)) {
			mailTime = sdf16.format(date);
		} else if (s > (60 * 60 * 24 * 30 * 12)) {
			mailTime = sdf16.format(date);
		}
		return mailTime;
	}

	// yyyyMMddHHmmss 转 yyyy年MM月dd日 上午HH:mm
	public static String timeFormat(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String formatTime = "";
		try {
			Date date = sdf.parse(time);
			sdf = new SimpleDateFormat("yyyy年MM月dd日 上午HH:mm");
			formatTime = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return formatTime;
	}

	// yyyy/MM/dd HH:mm:ss 转  yyyyMMddHHmmss
	public static String stringFormat(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String formatTime = "";
		try {
			Date date = sdf.parse(time);
			sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			formatTime = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return formatTime;
	}

	public static String convertDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}

	// yyyyMMdd  转  yyyy-MM-dd
	public static String noticeTime8(String send_time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = sdf.parse(send_time);
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	// yyyy-MM-dd  转  yyyyMMdd
	public static String noticeString8(String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(time);
		sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}

	// yyyyMMddHHmmss  转  yyyy-MM-dd HH:mm
	public static String noticeTime(String send_time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = sdf.parse(send_time);
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(date);
	}

	public static String messageTime(String send_time) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = sdf.parse(send_time);
			sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Date().toLocaleString();

	}

	// 参数为14位数字
	public static String voteTimeToString(String stime, String etime) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			long sdate = sdf.parse(stime).getTime();
			long edate = sdf.parse(etime).getTime();
			long cdate = new Date().getTime();
			if (cdate >= edate) {
				return "已结束";
			} else if (cdate < edate && cdate >= sdate) {
				return "进行中";
			} else if (cdate < sdate) {
				return "未开始";
			} else {
				return "禁用";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String parseDate(String str, String mod) {
		String y = str.substring(0, 4);
		String M = str.substring(4, 6);
		String d = str.substring(6, 8);
		String h = str.substring(8, 10);
		String m = str.substring(10, 12);
		String s = str.substring(12);
		mod = mod.replace("yyyy", y);
		mod = mod.replace("MM", M);
		mod = mod.replace("dd", d);
		mod = mod.replace("HH", h);
		mod = mod.replace("mm", m);
		mod = mod.replace("ss", s);
		return mod;
	}

	//判断数字是否小于10   小于10 + 0
	public static String ifLength2(long l) {
		if (l < 10) {
			return "0" + String.valueOf(l);
		} else {
			return String.valueOf(l);
		}
	}
}