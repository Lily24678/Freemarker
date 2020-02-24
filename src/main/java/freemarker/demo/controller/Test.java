package freemarker.demo.controller;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

public class Test {
	private static Map<String, List<String>> maps = new HashMap<String, List<String>>();

	private static Map<String, Integer> coun = new HashMap<>();
	private static Map<String, List<String>> counLis = new HashMap<>();

	public static void main(String[] args) throws Exception {
		Stream<String> lines = Files.lines(Paths.get("F:/甘南线路.tsv"));
		lines.forEach((line) -> {
			String[] split = line.split("\\s+");
			if (split.length == 2) {
				add(split[1], split[0]);
			}
		});
		lines.close();
		Set<Sor> li = new TreeSet<>();
		coun.forEach((k, v) -> {
			li.add(new Sor(k, v));
		});
		for (Sor sor : li) {
			if (sor.size > 1) {
				System.out.print(sor.name + ":" + sor.size + "\t");
				// 省内top5
				getTop5Lis(sor.name);
			}
		}
	}

	private static void getTop5Lis(String name) {
		List<String> list = counLis.get(name);
		Map<String, Integer> counts = new HashMap<>();
		for (String string : list) {
			String bie = string.substring(0,2);//sss
			if (counts.get(bie) == null) {
				counts.put(bie, 1);
			} else {
				counts.put(bie, counts.get(bie) + 1);
			}
		}
		Set<Sor> li = new TreeSet<>();
		counts.forEach((k, v) -> {
			li.add(new Sor(k, v));
		});
		int i = 0;
		for (Sor sor : li) {
			i++;
			if (i == 6) {
				break;
			}
			if (sor.size > 1) {
				System.out.print(sor.name + ":" + sor.size + "\t\t");
			}
		}
		System.out.println();
	}

	public static void add(String lis, String sta) {
		List<String> list = maps.get(lis);
		if (list == null) {
			list = new ArrayList<String>();
			maps.put(lis, list);
		} else {
			for (String string : list) {
				if (string.equals(sta)) {
					// 路线结束
					String line = String.join("-", list);
					addLine(line, lis);
					list = new ArrayList<>();
					list.add(sta);
					maps.put(lis, list);
					return;
				}
			}
			list.add(sta);
		}
	}

	private static void addLine(String line, String lis) {
		Integer co = coun.get(line);
		if (co == null) {
			coun.put(line, 1);
			ArrayList<String> list = new ArrayList<>();
			list.add(lis);
			counLis.put(line, list);
		} else {
			coun.put(line, co + 1);
			counLis.get(line).add(lis);
		}
	}

	public static class Sor implements Comparable<Sor> {
		String name;
		int size;

		public Sor(String name, int size) {
			this.name = name;
			this.size = size;
		}

		@Override
		public int compareTo(Sor o) {
			return o.size - this.size;
		}

	}

}
