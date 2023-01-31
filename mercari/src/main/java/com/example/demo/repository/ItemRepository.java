package com.example.demo.repository;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.common.ItemRepositoryInterface;
import com.example.demo.domain.CategoryDetail;
import com.example.demo.domain.Item;

/**
 * 商品情報を操作するリポジトリ.
 * 
 * @author inagakisaia
 *
 */
@Repository
public class ItemRepository implements ItemRepositoryInterface{

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Item> ITEM_CATEGORY_ROW_MAPPER = (rs, i) -> {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setConditionId(rs.getInt("condition_id"));
		CategoryDetail categoryDetail = new CategoryDetail();
		item.setCategoryDetail(categoryDetail);
		categoryDetail.setSmallId(rs.getInt("small_id"));
		categoryDetail.setSmallName(rs.getString("small_name"));
		categoryDetail.setPath(rs.getString("category_path"));
		item.setBrandId(rs.getInt("brand_id"));
		item.setBrandName(rs.getString("brand_name"));
		item.setPrice(rs.getDouble("price"));
		item.setShipping(rs.getInt("shipping"));
		item.setDescription(rs.getString("description"));
		item.setCount(rs.getInt("count"));

		return item;
	};

	/**
	 * 商品情報を挿入します.
	 * 
	 * @param item 商品情報
	 * @return 採番されたID
	 */
	@Override
	public Integer insertNewItem(Item item) {
		item=Objects.requireNonNull(item);
		String sql = "INSERT INTO items (name,condition_id,category_id,brand_id,price,description) VALUES(:name,:conditionId,:categoryId,:brandId,:price,:description);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);

		// 採番されたidを取得する
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String keyColumnNames[] = { "id" };
		template.update(sql, param, keyHolder, keyColumnNames);
		Integer id = keyHolder.getKey().intValue();

		return id;
	}

	/**
	 * 商品情報を更新します.
	 * 
	 * @param item 商品情報
	 */
	@Override
	public void update(Item item) {
		item=Objects.requireNonNull(item);
		String sql = "UPDATE items SET name=:name,condition_id=:conditionId,category_id=:categoryId,brand_id=:brandId,price=:price,shipping=:shipping,description=:description WHERE id=:id;";
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		template.update(sql, param);
	}

	/**
	 * 指定された開始位置から30件の商品情報を取得します.
	 * 
	 * @param startId 開始位置
	 * @return 検索された商品情報
	 */
	@Override
	public List<Item> findPartOfContent(int startId) {
		StringBuilder sql = new StringBuilder(
				"SELECT i.id id,i.name name,i.condition_id condition_id ,i.category_id small_id,");
		sql.append("c.name small_name,c.path category_path,i.brand_id brand_id,b.name brand_name,");
		sql.append("i.price price,i.shipping shipping,i.description description,i.count count ");
		sql.append(
				"FROM (SELECT id,name,condition_id,category_id,brand_id,price,shipping,description,count(1) over() AS count ");
		sql.append("FROM items ORDER BY id DESC LIMIT 30 OFFSET :startId) i ");
		sql.append("LEFT OUTER JOIN categories c ON i.category_id=c.category_id ");
		sql.append("LEFT OUTER JOIN brands b ON i.brand_id=b.brand_id ORDER BY i.id DESC;");
		SqlParameterSource param = new MapSqlParameterSource().addValue("startId", startId);
		List<Item> itemList = template.query(sql.toString(), param, ITEM_CATEGORY_ROW_MAPPER);
		return itemList;
	}

	/**
	 * IDから商品情報を一件検索します.
	 * 
	 * @param itemId 商品ID
	 * @return 検索された商品情報
	 */
	@Override
	public Item loadJoinCategory(int itemId) {
		StringBuilder sql = new StringBuilder(
				"SELECT i.id id,i.name name,i.condition_id condition_id ,i.category_id small_id,");
		sql.append("c.name small_name,c.path category_path,i.brand_id brand_id,b.name brand_name,");
		sql.append("i.price price,i.shipping shipping,i.description description,count(1) over() AS count ");
		sql.append("FROM items i LEFT OUTER JOIN categories c ON i.category_id=c.category_id ");
		sql.append("LEFT OUTER JOIN brands b ON i.brand_id=b.brand_id WHERE i.id=:itemId;");
		SqlParameterSource param = new MapSqlParameterSource().addValue("itemId", itemId);
		Item item = template.queryForObject(sql.toString(), param, ITEM_CATEGORY_ROW_MAPPER);
		return item;
	}

	/**
	 * 渡された情報から商品情報を検索します.
	 * 
	 * @param categoryPath カテゴリのパス
	 * @param name         商品名
	 * @param brand        ブランド名
	 * @param startId      開始位置
	 * @return 検索された商品情報
	 */
	@Override
	public List<Item> search(String categoryPath, String name, String brand, int startId) {
		categoryPath=Objects.requireNonNull(categoryPath);
		name=Objects.requireNonNull(name);
		brand=Objects.requireNonNull(brand);
		StringBuilder sql = new StringBuilder(
				"SELECT i.id id,i.name name,i.condition_id condition_id,i.category_id small_id,");
		sql.append(
				"c.name small_name,c.path category_path,i.brand_id brand_id,b.name brand_name,i.price price,i.shipping shipping,");
		sql.append("i.description description,count(1) over() AS count ");
		sql.append("FROM items i LEFT OUTER JOIN categories c ON i.category_id=c.category_id ");
		sql.append("LEFT OUTER JOIN brands b ON i.brand_id=b.brand_id ");
		sql.append(
				"WHERE c.path LIKE :categoryPath AND i.name ILIKE :name AND b.name ILIKE :brand ORDER BY i.id DESC ");
		sql.append("LIMIT 30 OFFSET :startId");
		SqlParameterSource param = new MapSqlParameterSource().addValue("categoryPath", categoryPath + "%")
				.addValue("name", "%" + name + "%").addValue("brand", "%" + brand + "%").addValue("startId", startId);
		List<Item> itemList = template.query(sql.toString(), param, ITEM_CATEGORY_ROW_MAPPER);
		return itemList;
	}

	/**
	 * ブランド名から商品情報を完全一致検索し、開始位置から30件取得します.
	 * 
	 * @param brand   ブランド名
	 * @param startId 開始位置
	 * @return 検索された商品情報
	 */
	@Override
	public List<Item> findByBrand(int brandId, int startId) {
		StringBuilder sql = new StringBuilder(
				"SELECT i.id id,i.name name,i.condition_id condition_id ,i.category_id small_id,");
		sql.append("c.name small_name,c.path category_path,i.brand_id brand_id,b.name brand_name,");
		sql.append("i.price price,i.shipping shipping,i.description description,i.count count ");
		sql.append(
				"FROM (SELECT id,name,condition_id,category_id,brand_id,price,shipping,description,count(1) over() AS count ");
		sql.append("FROM items WHERE brand_id=:brandId ORDER BY id DESC LIMIT 30 OFFSET :startId) i ");
		sql.append("LEFT OUTER JOIN categories c ON i.category_id=c.category_id ");
		sql.append("LEFT OUTER JOIN brands b ON i.brand_id=b.brand_id ORDER BY i.id DESC;");
		SqlParameterSource param = new MapSqlParameterSource().addValue("startId", startId).addValue("brandId",
				brandId);
		List<Item> itemList = template.query(sql.toString(), param, ITEM_CATEGORY_ROW_MAPPER);
		return itemList;
	}

}
