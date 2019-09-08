package com.ycu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ycu.dao.ReportDao;
import com.ycu.piereport.Option;
import com.ycu.piereport.OrderPieBean;
import com.ycu.piereport.Serie;
import com.ycu.piereport.SerieData;
import com.ycu.service.OrderPieReportService;

@Service
public class OrderPieReportServiceImpl implements OrderPieReportService{

	@Autowired
	private ReportDao reportDao;
	
	@Override
	public Option count() {
		// 1,首先，当然是构建一个返回给Echarts的Option喽。
		Option option = new Option();
		// 2.然后呢，从这个数据库中查出，每种类型多的商品的销售数量
		List<OrderPieBean> list = reportDao.selectOrderByCategory();
		// 3.填充前端需要的属性喽
		// 设置饼子的名称和数值
		Serie serie = new Serie();
		option.getSeries().add(serie);
		serie.setName("商品分类");
		serie.setType("pie");
		// 类别
		Set<String> categoryNameSet = new TreeSet<String>();
		for (OrderPieBean orderPieBean : list) {
			categoryNameSet.add(orderPieBean.getCategory());
			SerieData serieData = new SerieData(orderPieBean.getCount(), orderPieBean.getCategory());
			serie.getData().add(serieData);
		}
		// 设置分类
		option.getLegend().setData(new ArrayList<>(categoryNameSet));

		return option;
	}

	
}
