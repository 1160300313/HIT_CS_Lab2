package P3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import P1.graph.Graph;

/**
 * 
 * @author EvaneScencE
 *
 */
public interface RoutePlannerBuilder {
	/**
	 * 根据文件路径读取文件，创建一个新的RoutePlanner。
	 * 
	 * @param filename
	 *            文件名
	 * @param maxWaitLimit
	 *            最长等待时间
	 * @return 一个新的RoutePlanner
	 */
	public RoutePlanner build(String filename, int maxWaitLimit);
}

class BusRoutePlannerBuilder implements RoutePlannerBuilder {

	@Override
	public RoutePlanner build(String filename, int maxWaitLimit) {
		Graph<Stop> graph = Graph.empty();
		// String path = "sec/P3/in-hw2.txt";
		try {
			File file = new File(filename);
			BufferedReader bReader = new BufferedReader(new FileReader(file));
			int nextBlockStops;
			String tmp = null;
			while ((tmp = bReader.readLine()) != null) {
				String[] thisLine = tmp.split(",");
				for (int i = 0; i < thisLine.length; i++) {
					//System.out.println(thisLine[i]);
				}
				// if (thisLine.length == 2) {// 读取到一块的开头
				nextBlockStops = Integer.valueOf(thisLine[1]);
				//System.out.println(nextBlockStops);
				int thisTime = 0, foreTime = 0;
				Stop foreStop = null;
				Stop thisStop = null;
				for (int i = 0; i < nextBlockStops; i++) {
					tmp = bReader.readLine();
					thisLine = tmp.split(",");
					for (int m = 0; m < thisLine.length; m++) {
						//System.out.println(thisLine[m]);
					}
					thisTime = Integer.parseInt(thisLine[3]);
					// Stop stop = new BusStop(thisLine[0],
					// Double.parseDouble(thisLine[1]),
					// Double.parseDouble(thisLine[2]), thisTime);
					thisStop = new BusStop(thisLine[0], Double.parseDouble(thisLine[1]),
							Double.parseDouble(thisLine[2]), thisTime);

					graph.add(thisStop);
					if (i > 0) {
						int gap = thisTime - foreTime;
						graph.set(foreStop, thisStop, gap);
					}
					foreTime = thisTime;
					foreStop = new BusStop(thisLine[0], Double.parseDouble(thisLine[1]),
							Double.parseDouble(thisLine[2]), foreTime);
				}
				// }
			}
			bReader.close();
		} catch (IOException e) {
			throw new RuntimeException("DO NOTHING!!!");
		}
		//System.out.println(graph);
		RoutePlanner rPlanner = new BusRoutePlanner(graph, maxWaitLimit);
		return rPlanner;
	}

}