/**
 * 
 */
package P3;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import P1.graph.Graph;

/**
 * @author EvaneScencE
 *
 */
public interface RoutePlanner {
	/**
	 * 
	 * @param search
	 * @return
	 */
	public List<Stop> findStopBySubstring(String search);

	/**
	 * 使用邻接表上的Dijkstra算法计算给定的两个站点之间的最短路径
	 * 
	 * @param src
	 *            起点公交站
	 * @param dest
	 *            终点公交站
	 * @param time
	 *            时间
	 * @return 存在则返回一个Itinerary的路径，不存在则返回null
	 */
	public Itinerary computeRoute(Stop src, Stop dest, int time);
}

class BusRoutePlanner implements RoutePlanner {
	private Graph<Stop> graph = Graph.empty();
	private int maxWaitTime;

	public BusRoutePlanner(Graph<Stop> graph, int maxWaitTime) {
		super();
		this.graph = graph;
		this.maxWaitTime = maxWaitTime;
	}

	/**
	 * @return the graph
	 */
	public Graph<Stop> getGraph() {
		return graph;
	}

	@Override
	public List<Stop> findStopBySubstring(String search) {
		List<Stop> finded = new ArrayList<>();
		Set<Stop> vers = graph.vertices();
		String bigSearch = search.toUpperCase();
		for (Stop tmp : vers) {
			String thisName = tmp.getName().toUpperCase();
			if (thisName.contains(bigSearch)) {
				finded.add(tmp);
			}
		}
		return finded;
	}

	@Override
	public Itinerary computeRoute(Stop src, Stop dest, int time) {
		final int inf = 60 * 60 * 24;
		Set<Stop> vers = graph.vertices();
		if (time > inf | !vers.contains(src) | !vers.contains(dest) | src.equals(dest)) {
			return null;
		}
		int verNums = vers.size();
		Stop[] verList = new Stop[verNums];
		int[] distances = new int[verNums]; // 距离
		boolean[] visits = new boolean[verNums];
		int[] prev = new int[verNums];
		Stop[] paths = new Stop[verNums];

		Set<Stop> dij = new LinkedHashSet<>();
		int srcIndex = 0, dstIndex = 0;
		int tmp = 0;
		for (Stop t : vers) {
			verList[tmp] = t;
			if (t.getName().equals(src.getName())) {
				srcIndex = tmp;
				dij.add(t);
			}
			if (t.getName().equals(dest.getName())) {
				dstIndex = tmp;
			}
			tmp++;
		}
		Map<Stop, Integer> stops = graph.targets(src);
		for (int i = 0; i < verNums; i++) {
			distances[i] = stops.containsKey(verList[i]) ? stops.get(verList[i]) : inf;
			visits[i] = false;
			prev[i] = srcIndex;
		}
		// distances[srcIndex] = 0;
		visits[srcIndex] = true;
		for (int i = 0; i < verNums; i++) {
			System.out.println(distances[i]);
		}

		while (!visits[dstIndex]) {
			int min = inf;
			int path = srcIndex;
			for (int i = 0; i < verNums; i++) {
				if (!visits[i] & distances[i] < min) {
					min = distances[i];
					path = i;
				}
			}
			visits[path] = true;
			for (int i = 0; i < verNums; i++) {
				if (!visits[i]) {
					distances[i] = Math.min(distances[i],
							distances[path] + graph.targets(verList[path]).get(verList[i]));
					if (distances[i] == distances[path] + graph.targets(verList[path]).get(verList[i])) {
						prev[i] = path;
					}
				}
			}
		}
		paths[0] = src;
		int num = 1;
		int p = srcIndex;
		while (p != dstIndex) {
			paths[num] = verList[prev[p]];
			p = prev[p];
			num++;
		}
		Stop[] newOne = new Stop[num];
		newOne[0] = src;
		for (int i = num - 1; i > 0; i--) {
			newOne[num - i] = paths[i];
		}
		if (paths[0].getStopTime() - time < maxWaitTime) ;
			Itinerary itinerary = new Itinerary(verList.toString(), paths[0].getStopTime(),
					paths[num - 1].getStopTime(), newOne);
		return itinerary;
	}
}
