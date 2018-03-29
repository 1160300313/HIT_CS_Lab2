package P2;

import java.util.*;

import P1.graph.Graph;

public class FriendshipGraph {
	private Graph<Person> graph = Graph.empty();

	/**
	 * Add a person into the person list.
	 * 
	 * @param person
	 *            the name of the person
	 * @return 成功则返回true，失败则返回false
	 */
	public boolean addVertex(Person person) {
		if (!graph.vertices().contains(person)) {
			graph.add(person);
			return true;
		}
		return false;
	}

	/**
	 * 在两个成员之间加入一条单向边
	 * 
	 * @param srcPerson
	 *            单向边起点
	 * @param dstPerson
	 *            单向边终点
	 */
	public void addEdge(Person srcPerson, Person dstPerson) {
		// if(graph.vertices().contains(srcPerson)&graph.vertices().contains(dstPerson)){
		// graph.set(srcPerson, dstPerson, 1);
		// }
		graph.add(srcPerson);
		graph.add(dstPerson);
		graph.set(srcPerson, dstPerson, 1);
	}

	/**
	 * 计算给定两个成员间的距离，使用Dijkstra算法
	 * 
	 * @param srcPerson
	 * @param dstPerson
	 * @return the distance between two persons x (an integer which above 0) if
	 *         they're connected, otherwise return -1
	 */
	public int getDistance(Person srcPerson, Person dstPerson) {
		if (!graph.vertices().contains(srcPerson) | !graph.vertices().contains(dstPerson)) {
			// System.out.println("");
			return -1;
		}
		if (srcPerson.equals(dstPerson)) {
			return 0;
		}
		int distance = 0;
		Set<Person> vers = graph.vertices();
		Map<Person, Boolean> visits = new HashMap<>();
		for (Person tmp : vers) {
			visits.put(tmp, false);
		}
		boolean find = false;
		Set<Person> tars = new HashSet<>();
		Set<Person> srcs = new HashSet<>();
		srcs.add(srcPerson);
		visits.replace(srcPerson, true);
		while (!find) {
			tars.clear();
			distance++;
			for (Person tmp : srcs) {
				Set<Person> tmpTar = graph.targets(tmp).keySet();
				for (Person tmpt : tmpTar) {
					if (!visits.get(tmpt)) {
						tars.add(tmpt);
						visits.replace(tmpt, true);
					}
				}
			}
			if (tars.isEmpty()) {
				break;
			}
			if (tars.contains(dstPerson)) {
				find = true;
				break;
			}
			srcs.clear();
			srcs.addAll(tars);
		}
		if (find)
			return distance;
		return -1;
	}

	public static void main(String[] args) {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		/*
		 * int i, j; int members = graph.getMembers(); int[][] g =
		 * graph.getGraph(); for (i = 0; i < members; i++) { for (j = 0; j <
		 * members; j++) System.out.print(g[i][j] + "\t"); System.out.println();
		 * }
		 */
		System.out.println(graph.getDistance(rachel, ross));// should print 1
		System.out.println(graph.getDistance(rachel, ben));// should print 2
		System.out.println(graph.getDistance(rachel, rachel));// should print 0
		System.out.println(graph.getDistance(rachel, kramer));// should print -1
	}
}
