/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {

	private final List<Vertex<L>> vertices = new ArrayList<>();

	// Abstraction function:
	// 添加不重复的顶点；设置边的信息，进行添加、修改及删除边的操作；
	// 删除一个顶点及连接该顶点的所有边；获得全部顶点；获得给定顶点的起点；
	// 获得给定顶点的终点；打印图的信息
	// Representation invariant:
	// 存储所有顶点信息的List，其中顶点无重复
	// Safety from rep exposure:
	// 成员变量使用final类型。需要返回信息时使用防御性复制

	// constructor
	public ConcreteVerticesGraph() {

	}

	// checkRep
	private void checkRep() {
		int size = vertices.size();
		for (int i = 0; i < size - 1; i++)
			for (int j = i + 1; j < size; j++)
				assert (!vertices.get(i).equals(vertices.get(j)));
	}

	@Override
	public boolean add(L vertex) {
		// throw new RuntimeException("not implemented");
		for (Vertex<L> tmp : vertices) {
			if (tmp.getName().equals(vertex)) {
				// System.out.println("Vertex existed");
				checkRep();
				return false;
			}
		}
		Vertex<L> ver = new Vertex<L>(vertex);
		vertices.add(ver);
		checkRep();
		return true;
	}

	@Override
	public int set(L source, L target, int weight) {
		// throw new RuntimeException("not implemented");
		if (weight < 0) {
			checkRep();
			return -1;
		}
		boolean src = false;
		boolean tar = false;
		int reweight = 0;
		for (Vertex<L> tmp : vertices) {
			if (src & tar) {
				break;
			}
			if (!src & tmp.getName().equals(source)) {
				reweight = tmp.setTargets(target, weight);
				src = true;
			}
			if (!tar & tmp.getName().equals(target)) {
				reweight = tmp.setSources(source, weight);
				tar = true;
			}
		}
		if (!src) {
			Vertex<L> srcVex = new Vertex<L>(source);
			reweight = srcVex.setTargets(target, weight);
			vertices.add(srcVex);
		}
		if (!tar) {
			Vertex<L> tarVex = new Vertex<L>(target);
			reweight = tarVex.setSources(source, weight);
			vertices.add(tarVex);
		}
		checkRep();
		return reweight;
	}

	@Override
	public boolean remove(L vertex) {
		boolean status = false;
		Iterator<Vertex<L>> iterator = vertices.iterator();
		while (iterator.hasNext()) {
			Vertex<L> tmp = iterator.next();
			if (tmp.getName().equals(vertex)) {
				status = true;
				iterator.remove();
				break;
			}
		}
		if (status) {
			for (Vertex<L> tmp : vertices) {
				if (tmp.getSources().containsKey(vertex)) {
					tmp.setSources(vertex, 0);
				}
				if (tmp.getTargets().containsKey(vertex)) {
					tmp.setTargets(vertex, 0);
				}
			}
		}
		checkRep();
		return status;
	}

	@Override
	public Set<L> vertices() {
		// throw new RuntimeException("not implemented");
		Set<L> str = new HashSet<>();
		for (Vertex<L> tmp : vertices) {
			str.add(tmp.getName());
		}
		checkRep();
		return str;
	}

	@Override
	public Map<L, Integer> sources(L target) {
		// throw new RuntimeException("not implemented");
		Map<L, Integer> src = new HashMap<>();
		for (Vertex<L> tmp : vertices) {
			if (tmp.getName().equals(target)) {
				src.putAll(tmp.getSources());
			}
		}
		checkRep();
		return src;
	}

	@Override
	public Map<L, Integer> targets(L source) {
		// throw new RuntimeException("not implemented");
		Map<L, Integer> tar = new HashMap<>();
		for (Vertex<L> tmp : vertices) {
			if (tmp.getName().equals(source)) {
				tar.putAll(tmp.getTargets());
			}
		}
		checkRep();
		return tar;
	}

	// toString()
	@Override
	public String toString() {
		// return "ConcreteVerticesGraph [vertices=" + vertices + "]";
		String re = "ConcreteVerticesGraph [vertices=\n";
		for (Vertex<L> tmp : vertices) {
			re += tmp + "\n";
		}
		re += "]\n";
		return re;
	}
}

/**
 * specification Mutable. This class is internal to the rep of
 * ConcreteVerticesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Vertex<L> {

	// fields
	private L name;
	private Map<L, Integer> sources = new HashMap<>();
	private Map<L, Integer> targets = new HashMap<>();
	// Abstraction function:
	// 获得顶点名；修改顶点名；设置起点的Set；获取起点的Set；
	// 设置终点的Set；获得终点的Set；打印顶点信息
	// Representation invariant:
	// L类型的name顶点名，指向顶点的全部边的全部顶点与边的权重的Map，顶点发出的全部边指向的全部顶点与边的权重的Map，权重均大于0
	// Safety from rep exposure:
	// 返回sources和targets时使用防御性复制

	// constructor
	public Vertex(L name) {
		this.name = name;
	}

	// checkRep
	private void checkRep() {
		for (Map.Entry<L, Integer> entry : sources.entrySet()) {
			assert (entry.getValue() > 0);
		}
		for (Map.Entry<L, Integer> entry : targets.entrySet()) {
			assert (entry.getValue() > 0);
		}
	}
	// methods

	/**
	 * Get the name of this vertex
	 * 
	 * @return the name of this vertex
	 */
	public L getName() {
		checkRep();
		return name;
	}

	/**
	 * Set the name of this vertex.
	 * 
	 * @param name
	 *            String of the name.
	 */
	public void setName(L name) {
		checkRep();
		this.name = name;
	}

	/**
	 * Return a new map which equals to the
	 * 
	 * @return
	 */
	public Map<L, Integer> getSources() {
		Map<L, Integer> src = new HashMap<>();
		src.putAll(sources);
		checkRep();
		return src;
	}

	// public void setSources(Map<String, Integer> sources) {
	// this.sources = sources;
	// }

	/**
	 * Set the sources list of this vertex, there are three kinds of operation,
	 * 0 means delete, otherwise means add or modify sources according to
	 * status.
	 * 
	 * @param source
	 *            the name of the source vertex
	 * @param weight
	 *            the weight of the edge. If weight is 0, delete this edge
	 * @return return 0 means delete or add a new edge, otherwise return the
	 *         previous weight
	 */
	public int setSources(L source, int weight) {
		// System.out.println(this.name + "sources:");
		if (weight != 0) {
			if (this.sources.containsKey(source)) {
				// System.out.println("Modify:" + source + "→" + this.name);
				checkRep();
				return this.sources.put(source, weight);
			} else {
				this.sources.put(source, weight);
				// System.out.println("Add:" + source + "→" + this.name);
				checkRep();
				return 0;
			}
		} else {
			if (this.sources.containsKey(source)) {
				int wei = this.sources.get(source);
				this.sources.remove(source);
				// System.out.println("Delete:" + source + "→" + this.name);
				checkRep();
				return wei;
			} else {
				// System.out.println("No such an edge");
				checkRep();
				return 0;
			}
		}
	}

	/**
	 * 返回该顶点发出的所有边的全部终点及边的权重
	 * 
	 * @return Map，key is target vertex, and value is the weight of the edge
	 *         connecting them.
	 */
	public Map<L, Integer> getTargets() {
		// return targets;
		Map<L, Integer> tar = new HashMap<>();
		tar.putAll(targets);
		checkRep();
		return tar;
	}

	/**
	 * Set the targets list of this vertex, there are three kinds of operation,
	 * 0 means delete, otherwise means add or modify targets according to
	 * status.
	 * 
	 * @param target
	 *            the name of the target vertex
	 * @param weight
	 *            the weight of the edge. If weight is 0, delete this edge.
	 * @return return 0 means delete or add a new edge, otherwise return the
	 *         previous weight
	 */
	public int setTargets(L target, int weight) {
		// System.out.println(this.name + "targets:");
		if (weight != 0) {
			// return this.targets.put(target, weight);
			if (this.targets.containsKey(target)) {
				// System.out.println("Modify:" + this.name + "→" + target);
				checkRep();
				return this.targets.put(target, weight);
			} else {
				this.targets.put(target, weight);
				// System.out.println("Add:" + this.name + "→" + target);
				checkRep();
				return 0;
			}
		} else {
			if (this.targets.containsKey(target)) {
				int wei = this.targets.get(target);
				this.targets.remove(target);
				// System.out.println("Delete:" + this.name + "→" + target);
				checkRep();
				return wei;
			} else {
				// System.out.println("No such an edge");
				checkRep();
				return 0;
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sources == null) ? 0 : sources.hashCode());
		result = prime * result + ((targets == null) ? 0 : targets.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex<?> other = (Vertex<?>) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sources == null) {
			if (other.sources != null)
				return false;
		} else if (!sources.equals(other.sources))
			return false;
		if (targets == null) {
			if (other.targets != null)
				return false;
		} else if (!targets.equals(other.targets))
			return false;
		return true;
	}

	// toString()
	@Override
	public String toString() {
		return "Vertex [name=" + name + ", sources=" + sources + ", targets=" + targets + "]";
	}

}
