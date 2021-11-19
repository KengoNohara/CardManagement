package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
	@Autowired
	CardRepository repository;

	String Name = "名前";
	String Factory = "社名";
	String State = "役職";
	String Relation = "区分";

	// findAllメソッド
	public List<CardEntity> findAllByOrderByFactory() {
		List<CardEntity> list = repository.findAllByOrderByFactory();
		return (list);
	}

	public List<CardEntity> findAllByOrderById() {
		List<CardEntity> list = repository.findAllByOrderById();
		return (list);
	}

	// toSortメソッド
	public List<CardEntity> toSort(String value, String target, String sort, String key) {
		List<CardEntity> list = null;
		if (value.equals(Name)) {
			if (target.equals("登録順") && sort.equals("昇順")) {
				list = repository.SortIdAsc("%" + key + "%");
			} else if (target.equals("登録順") && sort.equals("降順")) {
				list = repository.SortIdDesc("%" + key + "%");
			} else if (target.equals("社名") && sort.equals("昇順")) {
				list = repository.SortFactoryAsc("%" + key + "%");
			} else if (target.equals("社名") && sort.equals("降順")) {
				list = repository.SortFactoryDesc("%" + key + "%");
			} else if (target.equals("日付") && sort.equals("昇順")) {
				list = repository.SortDateAsc("%" + key + "%");
			} else if (target.equals("日付") && sort.equals("降順")) {
				list = repository.SortDateDesc("%" + key + "%");
			}
		} else if (value.equals(Factory)) {
			if (target.equals("登録順") && sort.equals("昇順")) {
				list = repository.FactorySortIdAsc("%" + key + "%");
			} else if (target.equals("登録順") && sort.equals("降順")) {
				list = repository.FactorySortIdDesc("%" + key + "%");
			} else if (target.equals("社名") && sort.equals("昇順")) {
				list = repository.FactorySortFactoryAsc("%" + key + "%");
			} else if (target.equals("社名") && sort.equals("降順")) {
				list = repository.FactorySortFactoryDesc("%" + key + "%");
			} else if (target.equals("日付") && sort.equals("昇順")) {
				list = repository.FactorySortDateAsc("%" + key + "%");
			} else if (target.equals("日付") && sort.equals("降順")) {
				list = repository.FactorySortDateDesc("%" + key + "%");
			}
		} else if (value.equals(State)) {
			if (target.equals("登録順") && sort.equals("昇順")) {
				list = repository.StateSortIdAsc("%" + key + "%");
			} else if (target.equals("登録順") && sort.equals("降順")) {
				list = repository.StateSortIdDesc("%" + key + "%");
			} else if (target.equals("社名") && sort.equals("昇順")) {
				list = repository.StateSortFactoryAsc("%" + key + "%");
			} else if (target.equals("社名") && sort.equals("降順")) {
				list = repository.StateSortFactoryDesc("%" + key + "%");
			} else if (target.equals("日付") && sort.equals("昇順")) {
				list = repository.StateSortDateAsc("%" + key + "%");
			} else if (target.equals("日付") && sort.equals("降順")) {
				list = repository.StateSortDateDesc("%" + key + "%");
			}
		} else if (value.equals(Relation)) {
			if (target.equals("登録順") && sort.equals("昇順")) {
				list = repository.RelationSortIdAsc("%" + key + "%");
			} else if (target.equals("登録順") && sort.equals("降順")) {
				list = repository.RelationSortIdDesc("%" + key + "%");
			} else if (target.equals("社名") && sort.equals("昇順")) {
				list = repository.RelationSortFactoryAsc("%" + key + "%");
			} else if (target.equals("社名") && sort.equals("降順")) {
				list = repository.RelationSortFactoryDesc("%" + key + "%");
			} else if (target.equals("日付") && sort.equals("昇順")) {
				list = repository.RelationSortDateAsc("%" + key + "%");
			} else if (target.equals("日付") && sort.equals("降順")) {
				list = repository.RelationSortDateDesc("%" + key + "%");
			}
		}
		return (list);
	}

	// Sortメソッド
	public List<CardEntity> Sort(String target, String sort, String key) {
		List<CardEntity> list = null;
		if (target.equals("登録順") && sort.equals("昇順")) {
			list = repository.findAllByOrderById();
		} else if (target.equals("登録順") && sort.equals("降順")) {
			list = repository.findAllByOrderByIdDesc();
		} else if (target.equals("社名") && sort.equals("昇順")) {
			list = repository.findAllByOrderByFactory();
		} else if (target.equals("社名") && sort.equals("降順")) {
			list = repository.findAllByOrderByFactoryDesc();
		} else if (target.equals("日付") && sort.equals("昇順")) {
			list = repository.findAllByOrderByDate();
		} else if (target.equals("日付") && sort.equals("降順")) {
			list = repository.findAllByOrderByDateDesc();
		}
		return (list);
	}

	public List<CardEntity> Sort(String target, String sort) {
		List<CardEntity> list = null;
		if (target.equals("登録順") && sort.equals("昇順")) {
			list = repository.findAllByOrderById();
		} else if (target.equals("登録順") && sort.equals("降順")) {
			list = repository.findAllByOrderByIdDesc();
		} else if (target.equals("社名") && sort.equals("昇順")) {
			list = repository.findAllByOrderByFactory();
		} else if (target.equals("社名") && sort.equals("降順")) {
			list = repository.findAllByOrderByFactoryDesc();
		} else if (target.equals("日付") && sort.equals("昇順")) {
			list = repository.findAllByOrderByDate();
		} else if (target.equals("日付") && sort.equals("降順")) {
			list = repository.findAllByOrderByDateDesc();
		}
		return (list);
	}

	// toSearchメソッド
	public List<CardEntity> toSearch(String value, String key) {
		List<CardEntity> list = null;
		if (value.equals(Name)) {
			list = repository.findByNameLike("%" + key + "%");
		} else if (value.equals(Factory)) {
			list = repository.findByFactoryLike("%" + key + "%");
		} else if (value.equals(State)) {
			list = repository.findByStateLike("%" + key + "%");
		} else if (value.equals(Relation)) {
			list = repository.findByRelationLike("%" + key + "%");
		}
		return (list);
	}

	public List<CardEntity> findByIdInOrderById(List<Integer> idList) {
		List<CardEntity> list = repository.findByIdInOrderById(idList);
		return (list);
	}

	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	public Optional<CardEntity> findById(int id) {
		Optional<CardEntity> detail = repository.findById(id);
		return (detail);
	}

	public void saveAndFlush(CardEntity entity) {
		repository.saveAndFlush(entity);
	}

	public List<CountFactoryClass> CountFactoryClass() {
		List<CountFactoryClass> counts = new ArrayList<>();
		Integer[] count = repository.FindFactoryAndCount();
		String[] factory = repository.FindFactory();
		String[] name = repository.FindName();
		String[] relation=repository.FindRelation();
		String[] state=repository.FindState();
		Integer[] id=repository.FindId();
		int countup2 = 0;
		for (int x = 0; x < factory.length; x++) {// ここは確定＿変えるのは↓の記述
			counts.add(new CountFactoryClass(factory[x], count[x], name[countup2],relation[countup2],state[countup2],id[countup2]));// ここを増やす
			countup2++;
			int countup = count[x];
			if (countup >= 2) {
				for (; countup >= 2; countup--) {
					counts.add(new CountFactoryClass(name[countup2],relation[countup2],state[countup2],id[countup2]));//個々も増やす
					countup2++;
				}
			}
		}
		return (counts);
	}
}
