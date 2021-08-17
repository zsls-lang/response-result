package com.zsls.common.utils;

import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import com.zsls.common.base.PageResult;

import java.util.ArrayList;
import java.util.List;

public class PageUtils {

	public static <T> PageResult getPageResult(List<T> list, Integer pageNum, Integer pageSize) {
		PageResult result = new PageResult(new ArrayList<T>(), 0l, pageNum, pageSize);
		if (CollectionUtils.isNotEmpty(list)) {
			int total = list.size();
			int fromIndex = (pageNum - 1) * pageSize;
			int toIndex = fromIndex + pageSize;
			toIndex = (toIndex > total) ? total : toIndex;
			if (fromIndex <= total) {
				result = new PageResult(list.subList(fromIndex, toIndex), (long) total, pageNum, pageSize);
			} else {
				result = new PageResult(new ArrayList<T>(), (long) total, pageNum, pageSize);
			}
		}

		return result;
	}
}