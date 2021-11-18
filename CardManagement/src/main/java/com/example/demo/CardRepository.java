package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Integer> {

	public List<CardEntity> findByNameLike(String key);

	public List<CardEntity> findByFactoryLike(String key);
	
	public List<CardEntity> findByStateLike(String key);
	
	public List<CardEntity> findByRelationLike(String key);
	//-------------------------------------------------------
	
	public List<CardEntity> findAllByOrderById();

	public List<CardEntity> findAllByOrderByIdDesc();

	public List<CardEntity> findAllByOrderByDate();

	public List<CardEntity> findAllByOrderByDateDesc();

	public List<CardEntity> findAllByOrderByFactory();
	
	//
	public List<CardEntity> findByNameLikeOrderByIdAsc(String key);
	
	public List<CardEntity> findByNameLikeOrderByIdDesc(String key);
	
	public List<CardEntity> findByNameLikeOrderByFactoryAsc(String key);
	
	public List<CardEntity> findByNameLikeOrderByFactoryDesc(String key);
	
	public List<CardEntity> findByNameLikeOrderByDateAsc(String key);
	
	public List<CardEntity> findByNameLikeOrderByDateDesc(String key);
	
	//

	public List<CardEntity> findAllByOrderByFactoryDesc();

	public <E> List<CardEntity> findByIdInOrderById(List<E> list);//一括削除用のSQL


	//---- 名前の場合
	
	@Query("select u from CardEntity u where u.name like %:key% order by u.id asc")
	public List<CardEntity> SortIdAsc(@Param(value="key")String key);
	
	@Query("select u from CardEntity u where u.name like %:key% order by u.id desc")
	public List<CardEntity> SortIdDesc(@Param(value="key")String key);
	
	@Query("select u from CardEntity u where u.name like %:key% order by u.factory asc")
	public List<CardEntity> SortFactoryAsc(@Param(value="key")String key);
	
	@Query("select u from CardEntity u where u.name like %:key% order by u.factory desc")
	public List<CardEntity> SortFactoryDesc(@Param(value="key")String key);

	@Query("select u from CardEntity u where u.name like %:key% order by u.date asc")
	public List<CardEntity> SortDateAsc(@Param(value="key")String key);
	
	@Query("select u from CardEntity u where u.name like %:key% order by u.date desc")
	public List<CardEntity> SortDateDesc(@Param(value="key")String key);

	//社名の場合
	@Query("select u from CardEntity u where u.factory like %:key% order by u.id asc")
	public List<CardEntity> FactorySortIdAsc(@Param(value="key")String key);
	
	@Query("select u from CardEntity u where u.factory like %:key% order by u.id desc")
	public List<CardEntity> FactorySortIdDesc(@Param(value="key")String key);
	
	@Query("select u from CardEntity u where u.factory like %:key% order by u.factory asc")
	public List<CardEntity> FactorySortFactoryAsc(@Param(value="key")String key);
	
	@Query("select u from CardEntity u where u.factory like %:key% order by u.factory desc")
	public List<CardEntity> FactorySortFactoryDesc(@Param(value="key")String key);

	@Query("select u from CardEntity u where u.factory like %:key% order by u.date asc")
	public List<CardEntity> FactorySortDateAsc(@Param(value="key")String key);
	
	@Query("select u from CardEntity u where u.factory like %:key% order by u.date desc")
	public List<CardEntity> FactorySortDateDesc(@Param(value="key")String key);
	
	//役職の場合
	
	@Query("select u from CardEntity u where u.state like %:key% order by u.id asc")
	public List<CardEntity> StateSortIdAsc(@Param(value="key")String key);
	
	@Query("select u from CardEntity u where u.state like %:key% order by u.id desc")
	public List<CardEntity> StateSortIdDesc(@Param(value="key")String key);
	
	@Query("select u from CardEntity u where u.state like %:key% order by u.factory asc")
	public List<CardEntity> StateSortFactoryAsc(@Param(value="key")String key);
	
	@Query("select u from CardEntity u where u.state like %:key% order by u.factory desc")
	public List<CardEntity> StateSortFactoryDesc(@Param(value="key")String key);

	@Query("select u from CardEntity u where u.state like %:key% order by u.date asc")
	public List<CardEntity> StateSortDateAsc(@Param(value="key")String key);
	
	@Query("select u from CardEntity u where u.state like %:key% order by u.date desc")
	public List<CardEntity> StateSortDateDesc(@Param(value="key")String key);
	
	//区分の場合
	
	@Query("select u from CardEntity u where u.relation like %:key% order by u.id asc")
	public List<CardEntity> RelationSortIdAsc(@Param(value="key")String key);
	
	@Query("select u from CardEntity u where u.relation like %:key% order by u.id desc")
	public List<CardEntity> RelationSortIdDesc(@Param(value="key")String key);
	
	@Query("select u from CardEntity u where u.relation like %:key% order by u.factory asc")
	public List<CardEntity> RelationSortFactoryAsc(@Param(value="key")String key);
	
	@Query("select u from CardEntity u where u.relation like %:key% order by u.factory desc")
	public List<CardEntity> RelationSortFactoryDesc(@Param(value="key")String key);

	@Query("select u from CardEntity u where u.relation like %:key% order by u.date asc")
	public List<CardEntity> RelationSortDateAsc(@Param(value="key")String key);
	
	@Query("select u from CardEntity u where u.relation like %:key% order by u.date desc")
	public List<CardEntity> RelationSortDateDesc(@Param(value="key")String key);
	
	//------------トップページ用メソッド
	
	@Query("select u.factory from CardEntity u order by u.factory asc")
	public String[] FindAllFactory();
	
	//------------トップページ用プロトタイプ
	
	@Query("select u.factory, count(u.factory) from CardEntity u group by u.factory order by u.factory asc")
	public List<CardEntity> FindFactoryandSum();//値を返すメソッドは完成している。戻り値のことを調べる。とりまこれでOK
	//会社名とグループの数の判定結果が("社名",x)みたいな形でデータが返ってくる
}