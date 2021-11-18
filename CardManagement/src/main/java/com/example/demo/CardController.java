package com.example.demo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CardController {
	String key;
	String value;

	private static final String FILE_SEPARATE = File.separator;
	private static final String USER_DIR = System.getProperty("user.dir");
	private static final String IMAGE_PATH = "src\\main\\resources\\static\\img";
	private static final String UPLOAD_PATH = USER_DIR + FILE_SEPARATE + IMAGE_PATH;

	@Autowired
	CardRepository repository;

	@Autowired
	CardService service;

	@RequestMapping("/")
	public ModelAndView index(ModelAndView mv) {
		mv.addObject("list", service.getFindAll());
		mv.addObject("title", " 社別名刺一覧画面");
		setPulldown(mv);
//		mv.addObject("map",service.FindFactory()); //_この時点でダメ見たい
//		Map型のvalueが2つの値が入っているのが原因のよう
		mv.setViewName("index");
		return mv;
	}

	@RequestMapping("/write") // 追加画面の表示
	public ModelAndView write(ModelAndView mv) {
		mv.addObject("form", new CardEntity()); // 未定要素挿入フィールド//
		mv.addObject("title", "追加");
		mv.setViewName("write");
		return mv;// writeメソッドを後で変える
	}

	@RequestMapping("/insert")
	@Transactional(readOnly = false)
	public ModelAndView insert(@ModelAttribute("form") @Validated CardEntity entity, BindingResult result,
			ModelAndView mv) {
		if (result.hasErrors()) {
			mv.setViewName("write");
			mv.addObject("title", entity.getId() == null ? "追加" : "更新");
			return mv;
		} // 追加と表示するか更新と表示するか(画面に)

		if (entity.getId() != null && entity.getFile().isEmpty()) {
			Optional<CardEntity> detail = service.findById(entity.getId());// serviceメソッド入れた
			// ↑だけよくわからない
			entity.setPath(detail.get().getPath());
		} else {
			entity.setPath(entity.getFile().getOriginalFilename());
		}
		service.saveAndFlush(entity);

		if (!entity.getFile().isEmpty()) {
			uploadFile(entity.getFile());
		}
		return new ModelAndView("redirect:/");

	}

	private void uploadFile(MultipartFile file) {
		String filename = file.getOriginalFilename();
		try (BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(UPLOAD_PATH + FILE_SEPARATE + filename))) {
			byte[] bytes = file.getBytes();
			bos.write(bytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/list")
	public ModelAndView list(ModelAndView mv) {
		mv.addObject("list", service.getFindAll());
		setPulldown(mv);
		mv.addObject("title", "一覧");
		mv.addObject("form", new CardEntity());
		mv.setViewName("list");
		return mv;
	}

	@RequestMapping("/detail/{id}")
	public ModelAndView detail(@PathVariable Integer id, ModelAndView mv) {
		return set(mv, id, "詳細", "detail");
	}

	public ModelAndView set(ModelAndView mv, int id, String title, String page) {
		Optional<CardEntity> detail = service.findById(id);
		if (!detail.isPresent()) {
			return new ModelAndView("redirect:/");
		}
		mv.addObject("form", detail.get());
		mv.addObject("title", title);
		mv.setViewName(page);
		return mv;
	}

	@RequestMapping("/delete/{id}")
	public ModelAndView deleteid(@PathVariable Integer id, ModelAndView mv) {
		return set(mv, id, "削除", "detail");
	}

	@RequestMapping("/delete")
	@Transactional(readOnly = false)
	public ModelAndView delete(@RequestParam("id") Integer id) {
		service.deleteById(id);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping("/update/{id}")
	public ModelAndView update(@PathVariable Integer id, ModelAndView mv) {
		return set(mv, id, "更新", "write");
	}

	@RequestMapping("/search")
	public ModelAndView search(ModelAndView mv) {
		mv.setViewName("search");
		mv.addObject("title", "検索");
		mv.addObject("value", "名前");
		return mv;
	}

	@RequestMapping("/tosearch") // 検索結果画面表示
	public ModelAndView tosearch(@RequestParam("value") String value, @RequestParam("key") String key,
			ModelAndView mv) {
		mv.setViewName("search");
		this.value = value;
		this.key = key;
		mv.addObject("list", service.toSearch(value, key));
		mv.addObject("title", "検索結果表示");
		mv.addObject("hiddenrock", "pass");// searchテンプレートでもテーブル表示させる
		mv.addObject("value", value);
		mv.addObject("keys", key);
		mv.addObject("form", new CardEntity());
		setPulldown(mv);
		return mv;
	}

	// ここからリポジトリをいじる
	@RequestMapping("/tosort")
	public ModelAndView tosort(@RequestParam("target") String target, @RequestParam("sort") String sort,
			ModelAndView mv) {
		mv.setViewName("search");
		mv.addObject("value", value);
		mv.addObject("list", service.toSort(value, target, sort, key));
		setPulldown(mv);
		mv.addObject("selectedTarget", target);
		mv.addObject("selectedSort", sort);
		mv.addObject("title", "並べ替え");
		mv.addObject("keys", key);
		mv.addObject("hiddenrock", "pass");// searchテンプレートでもテーブル表示させる
		return mv;
	}

	// *********************** 一覧のSortメソッド *****************************
	@RequestMapping("/sort")
	public ModelAndView sort(@RequestParam("target") String target, @RequestParam("sort") String sort,
			ModelAndView mv) {
		mv.setViewName("list");
		mv.addObject("list", service.Sort(target, sort));
		setPulldown(mv);
		mv.addObject("form", new CardEntity());
		mv.addObject("selectedTarget", target);
		mv.addObject("selectedSort", sort);
		mv.addObject("title", "並べ替え");
		mv.addObject("hiddenrock", "pass");// searchテンプレートでもテーブル表示させる
		return mv;
	}

	private List<Pulldown> getTargetItem() {
		List<Pulldown> list = new ArrayList<>();
		list.add(new Pulldown("登録順"));
		list.add(new Pulldown("日付"));
		list.add(new Pulldown("社名"));
		return list;
	}

	private List<Pulldown> getSortItem() {
		List<Pulldown> list = new ArrayList<>();
		list.add(new Pulldown("昇順"));
		list.add(new Pulldown("降順"));
		return list;
	}

	private void setPulldown(ModelAndView mv) {
		mv.addObject("target", getTargetItem());
		mv.addObject("sort", getSortItem());
	}

	// -----------------------------------一括削除のメソッド

	@RequestMapping("/bulkdelete")
	public ModelAndView BulkDelete(@ModelAttribute("form") CardEntity entity, ModelAndView mv) {
		if (entity.getImputMultiCheck().length == 0) {
			mv.setViewName("redirect:/list");
			return mv;
		}
		List<Integer> idList = Arrays.asList(entity.getImputMultiCheck());
		mv.addObject("list", service.findByIdInOrderById(idList));
		mv.addObject("form", entity);
		mv.setViewName("deleteConf");
		return mv;
	}

	@RequestMapping("/bulkremove")
	@Transactional(readOnly = false)
	public ModelAndView Bulkremove(@ModelAttribute("form") CardEntity entity) {
		for (Integer id : entity.getImputMultiCheck()) {
			service.deleteById(id);
		}
		return new ModelAndView("redirect:/list");
	}
}