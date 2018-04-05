package com.seezoon.framework.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.seezoon.framework.common.Constants;
import com.seezoon.framework.common.entity.TreeEntity;

/**
 * tree grid 的组件需要后端排序 如部门、菜单列表
 * 
 * @author hdf 2018年4月5日
 */
public class TreeHelper<T extends TreeEntity<String>> {

	/**
	 * 默认跟节点
	 */
	public static final String DEFAULT_PARENTID = "0";

	/**
	 * eg --- --- --- ----
	 * 
	 * @param parentId
	 *            从哪里开始排
	 * @return
	 */
	public List<T> treeGridList(List<T> list, String parentId) {
		if (null == parentId) {
			parentId = DEFAULT_PARENTID;
		}
		List<T> result = new ArrayList<>();
		for (T t : list) {//
			if (parentId.equals(t.getParentId())) {// 从跟节点开始
				result.add(t);
				result.addAll(this.treeGridList(list, t.getId()));
			}
		}
		return result;
	}

	/**
	 * 处理parentid
	 * @param t
	 * @param parent
	 */
	public void setParent(T t,T parent) {
		if (StringUtils.isEmpty(t.getParentId())) {
			t.setParentId(DEFAULT_PARENTID);
			t.setParentIds(DEFAULT_PARENTID + Constants.SEPARATOR);
		} else {
			if (null != parent) {
				t.setParentId(parent.getId());
				t.setParentIds(parent.getParentIds() + parent.getId());
			}
		}
	}
}
