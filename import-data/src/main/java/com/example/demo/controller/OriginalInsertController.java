package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Original;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@Controller
@RequestMapping("/original")
public class OriginalInsertController {


	@GetMapping("/insert")
	public String insert() throws IOException {

		CsvMapper mapper = new CsvMapper();
		// ヘッダあり、タブ区切り
		CsvSchema schema = mapper.schemaFor(Original.class).withHeader().withColumnSeparator('\t');

		Path path = Paths.get("document/train.tsv");
		try (BufferedReader br = Files.newBufferedReader(path)) {

			MappingIterator<Original> it = mapper.readerFor(Original.class).with(schema).readValues(br);

			// TSVファイルを全行まとめて読み込む場合
			List<Original> originalList = it.readAll();
			System.out.println("読み込み完了");


			try {
				Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mercari", "postgres", "postgresz");
				String sql = "INSERT INTO original (id,name,condition_id,category_name,brand,price,shipping,description) values(?,?,?,?,?,?,?,?)";
				PreparedStatement statement = connection.prepareStatement(sql);
				int cnt = 0;
				int listCnt = originalList.size();
				for (Original original : originalList) {
					statement.setInt(1, Integer.parseInt(original.getTrain_id()));
					statement.setString(2, original.getName());
					statement.setInt(3, Integer.parseInt(original.getItem_condition_id()));
					statement.setString(4, original.getCategory_name());
					statement.setString(5, original.getBrand_name());
					statement.setDouble(6, Double.parseDouble(original.getPrice()));
					statement.setInt(7, Integer.parseInt(original.getShipping()));
					statement.setString(8, original.getItem_description());
					statement.addBatch();
					cnt++;
					if (cnt % 5000 == 0 || cnt == listCnt) {
						statement.executeBatch();
						System.out.println(cnt+"件終了");
					}
				}
				statement.close();
				connection.commit();
				connection.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "sample";
	}
	
}


// -- original
// create table original (
// id integer not null
// , name character varying(255)
// , condition_id integer
// , category_name character varying(255)
// , brand character varying(255)
// , price double precision
// , shipping integer
// , description text
// , constraint original_PKC primary key (id)
// ) ;
