package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.demo.domain.Big;
import com.example.demo.domain.Item;
import com.example.demo.domain.Middle;
import com.example.demo.domain.Small;
import com.example.demo.form.SearchItemForm;
import com.example.demo.service.ShowItemService;

/**
 * 商品情報を操作するコントローラー.
 * 
 * @author inagakisaia
 *
 */
@Controller
@RequestMapping("/")
@SessionAttributes(types = SearchItemForm.class)
public class ShowItemController {

	@Autowired
	private ShowItemService showItemService;

	@ModelAttribute("searchItemForm")
	public SearchItemForm setUpSearchItemForm() {
		return new SearchItemForm();
	}

	@Autowired
	private HttpSession session;

	/**
	 * トップページの表示を行います.
	 * 
	 * @return 商品一覧画面
	 */
	@GetMapping("")
	public String showTopPage(SearchItemForm form, Model model, SessionStatus sessionStatus) {
		sessionStatus.setComplete();

		session.removeAttribute("form");
		session.removeAttribute("brandName");

		List<Big> bigList = showItemService.findBigAll();
		model.addAttribute("bigList", bigList);

		// 総件数を取得後、総ページ数を算出
		int count = showItemService.count();
		int pageCnt = (count - 1) / 30 + 1;
		session.setAttribute("pageCnt", pageCnt);
		session.setAttribute("nowPage", 1);

		// 最初のページを表示するためのitemListを取得
		List<Item> itemList = showItemService.showList(0);
		model.addAttribute("itemList", itemList);

		return "list";
	}

	/**
	 * 指定のページ数の商品一覧画面に遷移します.
	 * 
	 * @param form  検索フォームの情報を受け取るフォーム
	 * @param brand ブランド名
	 * @param page  ページ数
	 * @return 商品一覧画面
	 */
	@RequestMapping("/toPage")
	public String toPage(@ModelAttribute("searchItemForm") SearchItemForm form, String name, String brand, int page,
			Model model) {

		session.setAttribute("nowPage", page);

		// 表示するitemListの始まりの値
		int firstId = (page - 1) * 30;

		List<Item> itemList = new ArrayList<>();
		List<Big> bigList = showItemService.findBigAll();
		model.addAttribute("bigList", bigList);
		List<Middle> middleList = new ArrayList<>();
		List<Small> smallList = new ArrayList<>();

		if (form.getName() == null && form.getBigId() == null && form.getBrand() == null) {

			if (session.getAttribute("brandName") != null) {
				itemList = showItemService.showItemByBrand(session.getAttribute("brandName").toString(), firstId);
			} else {
				itemList = showItemService.showList(firstId);
			}
		} else {
			if (form.getName() == null) {
				form.setName("");
			}
			if (form.getBrand() == null) {
				form.setBrand("");
			}
			if (form.getBigId().equals("0")) {
				itemList = showItemService.showItemByNameAndBrand(form.getName(), form.getBrand(), firstId);

			} else if (form.getMiddleId().equals("0")) {
				itemList = showItemService.showItemOfBigCategory(Integer.parseInt(form.getBigId()), form.getName(),
						form.getBrand(), firstId);

			} else if (form.getCategoryId().equals("0")) {
				itemList = showItemService.showItemOfMiddleCategory(Integer.parseInt(form.getMiddleId()),
						form.getName(), form.getBrand(), firstId);
				middleList = showItemService.showMiddleListByParent(Integer.parseInt(form.getBigId()));
				model.addAttribute("middleList", middleList);

			} else {
				itemList = showItemService.showItemOfSmallCategory(Integer.parseInt(form.getCategoryId()),
						form.getName(), form.getBrand(), firstId);
				middleList = showItemService.showMiddleListByParent(Integer.parseInt(form.getBigId()));
				model.addAttribute("middleList", middleList);
				smallList = showItemService.showSmallListByParent(Integer.parseInt(form.getMiddleId()));
				model.addAttribute("smallList", smallList);

			}
		}
		model.addAttribute("itemList", itemList);

		return "list";
	}

	/**
	 * 検索された商品情報から、商品一覧画面の１ページ目を表示します.
	 * 
	 * @param form      入力された情報を受け取るフォーム
	 * @param brandName ブランド名
	 * @return 商品一覧画面
	 */
	@RequestMapping("/search")
	public String searchItemList(@ModelAttribute("searchItemForm") SearchItemForm form, String brandName, Model model) {

		List<Item> itemList = new ArrayList<>();
		List<Middle> middleList = new ArrayList<>();
		List<Small> smallList = new ArrayList<>();
		int count = 0;

		if (brandName != null) {
			itemList = showItemService.showItemByBrand(brandName, 0);
			count = showItemService.countByBrand(brandName);
			session.setAttribute("brandName", brandName);
		} else {
			if (form.getName() == null) {
				form.setName("");
			}
			if (form.getBrand() == null) {
				form.setBrand("");
			}
			if (form.getBigId().equals("0")) {
				itemList = showItemService.showItemByNameAndBrand(form.getName(), form.getBrand(), 0);
				count = showItemService.countItemByNameAndBrand(form.getName(), form.getBrand());

			} else if (form.getMiddleId().equals("0") || form.getMiddleId() == null) {
				itemList = showItemService.showItemOfBigCategory(Integer.parseInt(form.getBigId()), form.getName(),
						form.getBrand(), 0);
				count = showItemService.countItemByBigCategory(Integer.parseInt(form.getBigId()), form.getName(),
						form.getBrand());

			} else if (form.getCategoryId().equals("0") || form.getCategoryId() == null) {
				itemList = showItemService.showItemOfMiddleCategory(Integer.parseInt(form.getMiddleId()),
						form.getName(), form.getBrand(), 0);
				count = showItemService.countItemByMiddleCategory(Integer.parseInt(form.getMiddleId()), form.getName(),
						form.getBrand());
				middleList = showItemService.showMiddleListByParent(Integer.parseInt(form.getBigId()));
				model.addAttribute("middleList", middleList);

			} else {
				itemList = showItemService.showItemOfSmallCategory(Integer.parseInt(form.getCategoryId()),
						form.getName(), form.getBrand(), 0);
				count = showItemService.countItemBySmallCategory(Integer.parseInt(form.getCategoryId()), form.getName(),
						form.getBrand());
				middleList = showItemService.showMiddleListByParent(Integer.parseInt(form.getBigId()));
				model.addAttribute("middleList", middleList);
				smallList = showItemService.showSmallListByParent(Integer.parseInt(form.getMiddleId()));
				model.addAttribute("smallList", smallList);
			}
			session.setAttribute("form", form);
		}
		List<Big> bigList = showItemService.findBigAll();
		model.addAttribute("bigList", bigList);
		model.addAttribute("itemList", itemList);
		session.setAttribute("nowPage", 1);
		int pageCnt = (count - 1) / 30 + 1;
		session.setAttribute("pageCnt", pageCnt);

		return "list";
	}

}