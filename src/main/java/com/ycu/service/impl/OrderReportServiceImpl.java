package com.ycu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ycu.dao.ReportDao;
import com.ycu.report.Option;
import com.ycu.report.Serie;
import com.ycu.service.OrderReportService;

@Service
public class OrderReportServiceImpl implements OrderReportService {

	@Autowired
	private ReportDao reportDao;
	
	@Override
	public Option count() {
		// 根据echarts的示例我们得知，
		// echart显示数据主要有几个特性：
		// 		1.legend
		// 		2.series
		// 		3.xAxis
		Option option = new Option();
		List<Map<String, String>> list = reportDao.countOrder();
		// 类别
		Set<String> categoryNameSet = new TreeSet<>();
		// 类别+时间为KEY，数量为VALUE
		Map<String,Long> countMap = new HashMap<String,Long>();
		for(Map<String, String> map : list) {
			categoryNameSet.add(map.get("categoryName"));
			countMap.put(map.get("categoryName") + map.get("hour"), Long.valueOf(map.get("count")));
		}
		// 设置参数中线条的分类
		option.getLegend().setData(new ArrayList<>(categoryNameSet));
		// 设置参数的X轴坐标
		List<String> hours = new ArrayList<String>();
		for(int i = 0; i <= 23; i++) {
			hours.add(String.format("%02d", i));
		}
		option.getxAxis().setData(hours);
		// 设置线条的名称和数值
		for(String categoryName : option.getLegend().getData()) {
			Serie serie = new Serie();
			option.getSeries().add(serie);
			serie.setName(categoryName);
			serie.setType("line");
			for(String hour : hours) {
				serie.getData().add(countMap.get(categoryName + hour) == null ? new Long(0) : countMap.get(categoryName + hour));
			}
		}
		return option;
	}

	@Override
	@Transactional
	public Option monthCount() {
		
		Option option = new Option();
		List<Map<String, String>> list = reportDao.monthOrder();
		
		// 1.设置Legend
		// 类别
		Set<String> categoryNameSet = new TreeSet<String>();
		// 类别+时间为KEY，数量为VALUE
		Map<String,Long> countMap = new HashMap<String,Long>();
		for(Map<String, String> map : list) {
			categoryNameSet.add(map.get("categoryName"));
			countMap.put(map.get("categoryName") + map.get("month"), Long.valueOf(map.get("count")));
		}
		// 设置参数中线条的分类
		option.getLegend().setData(new ArrayList<>(categoryNameSet));
		
		// 2.设置xAxis
		// 设置参数的X轴坐标
		List<String> months = new ArrayList<String>();
		for(int i = 1; i <= 12; i++) {
			months.add(String.format("%02d", i));
		}
		option.getxAxis().setData(months);

		// 3.设置series
		// 设置线条的名称和数值
		for(String categoryName : option.getLegend().getData()) {
			Serie serie = new Serie();
			option.getSeries().add(serie);
			serie.setName(categoryName);
			serie.setType("line");
			for(String month : months) {
				serie.getData().add(countMap.get(categoryName + month) == null ? new Long(0) : countMap.get(categoryName + month));
			}
		}
		
		return option;
	}
}