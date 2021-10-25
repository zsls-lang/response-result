package com.zsls.common.base;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 *
 * @param <T> 实体类
 */
 public interface IBaseService<T> {
	/**
	 * 根据id查询数据
	 *
	 * @param id
	 * @return
	 */
	 T queryById(Long id) ;

	/**
	 * 查询所有数据
	 *
	 * @return
	 */
	 List<T> queryList() ;

	/**
	 * 根据条件查询一条数据，如果有多条数据会抛出异常
	 *
	 * @param record
	 * @return
	 */
	 T queryUniqueResult(T record) ;

	/**
	 * 根据条件查询数据列表
	 *
	 * @param record
	 * @return
	 */
	 List<T> queryListByCondition(T record);

	/**
	 * 分页查询
	 *
	 * @param pageNum
	 * @param pageSize
	 * @param record
	 * @return
	 */
	 default PageResult<T> queryPageListByCondition(T record, Integer pageNum, Integer pageSize){
		 Page<T> page = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> queryListByCondition(record));
		 return new PageResult<T>(page.getResult(), page.getTotal(), pageNum, pageSize);
	 }
	/**
	 * 分页查询
	 *	排序
	 * @param pageNum
	 * @param pageSize
	 * @param record
	 * @return
	 */
	default PageResult<T> queryPageListOrderByCondition(T record, Integer pageNum, Integer pageSize, String order){
		Page<T> page = PageHelper.startPage(pageNum, pageSize,order).doSelectPage(() -> queryListByCondition(record));
		return new PageResult<T>(page.getResult(), page.getTotal(), pageNum, pageSize);
	} ;

	/**
	 * 新增数据，返回成功的条数
	 *
	 * @param record
	 * @return
	 */
	 Integer save(T record);

	/**
	 * 新增多条数据，返回成功的条数
	 *
	 * @param var1
	 * @return
	 */
	 int insertList(List<T> var1);

	/**
	 * 添加返回主键
	 * 不过滤为空字段
	 * @param record
	 * @return
	 */
	int insertUseGeneratedKeys(T record);

	/**
	 * 新增数据，使用不为null的字段，返回成功的条数
	 *
	 * @param record
	 * @return
	 */
	 Integer saveSelective(T record);

	/**
	 * 修改数据，返回成功的条数
	 *
	 * @param record
	 * @return
	 */
	 Integer update(T record);

	/**
	 * 修改数据，使用不为null的字段，返回成功的条数
	 *
	 * @param record
	 * @return
	 */
	 Integer updateSelective(T record);

	/**
	 * 根据id删除数据
	 *
	 * @param id
	 * @return
	 */
	Integer deleteById(Long id);

	/**
	 * 批量删除
	 * @param clazz
	 * @param property
	 * @param values
	 * @return
	 */
	Integer deleteByIds(Class<T> clazz, String property, List<Object> values) ;

	/**
	 * 根据条件做删除
	 *
	 * @param record
	 * @return
	 */
	Integer deleteByWhere(T record) ;

	/**
	 * 分页查询
	 *
	 * @param pageNum
	 * @param pageSize
	 * @param record      查询参数
	 * @return 分页集合
	 */
	default PageInfo<T> page(T record, Integer pageNum, Integer pageSize) {
		return PageHelper.startPage(pageNum,pageSize).doSelectPageInfo(() -> queryListByCondition(record));
	}

	/**
	 * 分页查询
	 *
	 * @param pageNum
	 * @param pageSize
	 * @param orderBy
	 * @param record      查询参数
	 * @return 分页集合
	 */
	default PageInfo<T> page(T record, Integer pageNum, Integer pageSize, String orderBy) {
		return PageHelper.startPage(pageNum,pageSize,orderBy).doSelectPageInfo(() -> queryListByCondition(record));
	}


}
