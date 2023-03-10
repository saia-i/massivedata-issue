package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.domain.Item;
import com.example.demo.form.InsertItemForm;
import com.example.demo.repository.ItemRepository;

@SpringBootTest
public class ItemInsertServiceTest {

	@MockBean
	private ItemRepository itemRepository;

	@Autowired
	private ItemInsertService insertService;

	@Test
	@DisplayName("itemをインサートするテスト")
	public void insertItemTest() {
		Item item = new Item();

		doReturn(1).when(itemRepository).insertNewItem(item);

		InsertItemForm form = new InsertItemForm();
		form.setName("name");
		form.setBrand("brand");
		form.setConditionId(1);
		form.setSmallName("100");
		form.setPrice("9.9");

		int result = insertService.insertItem(form);
		assertEquals(1,result);

	}

}
