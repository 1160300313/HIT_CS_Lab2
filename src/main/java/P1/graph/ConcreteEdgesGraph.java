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
public class ConcreteEdgesGraph<L> implements Graph<L> {

	private final Set<L> vertices = new HashSet<>();
	private final List<Edge<L>> edges = new ArrayList<>();

	// Abstraction function:
	// 添加结点；通过权的值判断对边的操作：添加、修改或删除；删除一个结点及连接它的所有边；
	// 获得全部结点；获得给定结点的全部起点；获得给定结点的全部终点；打印图的信息
	// Representation invariant:
	// 使用Set存储顶点信息，保证没有重复的顶点。
	// 根据用户添加边时对weight的要求选择合适的操作，最终存储在图中的edge的weight均为大于0的int类型。
	// Safety from rep exposure:
	// 类的两个成员变量都是final类型的，无法改变指向。
	// 需要返回时使用防御性拷贝

	// constructor
	public ConcreteEdgesGraph() {

	}

	// checkRep
	private void checkRep() {
		for (Edge<L> tmp : edges) {
			assert (vertices.contains(tmp.getSource()));
			assert (vertices.contains(tmp.getTarget()));
			assert (tmp.getWeight() > 0);
		}
	}

	@Override
	public boolean add(L vertex) {
		// throw new RuntimeException("not implemented");
		if (vertices.contains(vertex)) {
			// System.out.println("Exist!");
			return false;
		} else {
			vertices.add(vertex);
			// System.out.println("Add vertex successfully");
			checkRep();
			return true;
		}
	}

	@Override
	public int set(L source, L target, int weight) {
		// throw new RuntimeException("not implemented");
		if (weight < 0) {
			// System.out.println("Weight illegal");
			return -1;
		}
		Edge<L> modEdge = new Edge<L>(source, target, weight);
		int forWeight = 0;
		if (weight == 0) {// delete
			for (Edge<L> tmp : edges) {
				if (tmp.equals(modEdge)) {
					forWeight = tmp.getWeight();
					edges.remove(tmp);
					// System.out.println("Delete edge successfully");
					checkRep();
					return forWeight;
				}
			}
			// System.out.println("Edge not exist");
			checkRep();
			return 0;
		} else {// modify
			for (Edge<L> tmp : edges) {
				if (tmp.equals(modEdge)) {
					edges.remove(tmp);
					forWeight = tmp.getWeight();
					edges.add(new Edge<L>(source, target, weight));
					// System.out.println("Modify edge successfully");
					checkRep();
					return forWeight;
				}
			}
			// add
			vertices.add(source);
			vertices.add(target);
			edges.add(new Edge<L>(source, target, weight));
			// System.out.println("Add edge");
			checkRep();
			return 0;
		}
	}

	@Override
	public boolean remove(L vertex) {
		// throw new RuntimeException("not implemented");
		if (vertices.contains(vertex)) {
			vertices.remove(vertex);
			Iterator<Edge<L>> iterator = edges.iterator();
			// for (Edge tmp : edges) {
			// if (tmp.contain(vertex)) {
			// System.out.println("Remove related edge");
			// edges.remove(tmp);
			// }
			// }
			while (iterator.hasNext()) {
				if (iterator.next().contain(vertex)) {
					// System.out.println("Remove related edge");
					iterator.remove();
				}
			}
			// System.out.println("Remove vertex successfully");
			checkRep();
			return true;
		} else {
			// System.out.println("Vertex not exist");
			checkRep();
			return false;
		}
	}

	@Override
	public Set<L> vertices() {
		// throw new RuntimeException("not implemented");
		Set<L> newSet = new HashSet<>();
		newSet.addAll(vertices);
		checkRep();
		return newSet;
	}

	@Override
	public Map<L, Integer> sources(L target) {
		// throw new RuntimeException("not implemented");
		Map<L, Integer> srcMap = new HashMap<>();
		for (Edge<L> tmp : edges) {
			if (tmp.containTarget(target)) {
				srcMap.put(tmp.getSource(), tmp.getWeight());
			}
		}
		checkRep();
		return srcMap;
	}

	@Override
	public Map<L, Integer> targets(L source) {
		// throw new RuntimeException("not implemented");
		Map<L, Integer> tarMap = new HashMap<>();
		for (Edge<L> tmp : edges) {
			if (tmp.containSource(source)) {
				tarMap.put(tmp.getTarget(), tmp.getWeight());
			}
		}
		checkRep();
		return tarMap;
	}

	// toString()
	@Override
	public String toString() {
		checkRep();
		return "vertices=\n" + vertices + "\nedges=\n" + edges + "\n";
	}
}

/**
 * specification Immutable. This class is internal to the rep of
 * ConcreteEdgesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Edge<L> {

	// fields
	private final L source;
	private final L target;
	private final int weight;
	// Abstraction function:
	// 获得起点名；获得终点名；获得权重；判断本边是否包含给定的顶点；判断给定的起点是否正确；
	// 判断给定的终点是否正确；打印边的信息
	// Representation invariant:
	// L类型的source和target，int类型的weight，weight大于或等于0，weight为0的边不在图中存储，仅在特殊操作时使用
	// Safety from rep exposure:
	// 所有成员变量都是final类型且本身不可变，无法获取变量的内存地址

	// constructor
	public Edge(L source, L target, int weight) {
		this.source = source;
		this.target = target;
		this.weight = weight;
		checkRep();
	}

	// checkRep
	private void checkRep() {
		assert (weight >= 0);
	}

	// methods
	public L getSource() {
		return source;
	}

	public L getTarget() {
		return target;
	}

	public int getWeight() {
		return weight;
	}

	/**
	 * 判断该边是否包含给定的顶点（边的起点或终点）
	 * 
	 * @param vertex
	 *            顶点名
	 * @return 包含则返回true
	 */
	public boolean contain(L vertex) {
		return source.equals(vertex) | target.equals(vertex);
	}

	/**
	 * 判断该边的起点是否为给定顶点
	 * 
	 * @param vertex
	 *            顶点名
	 * @return 是则返回true
	 */
	public boolean containSource(L vertex) {
		return source.equals(vertex);
	}

	/**
	 * 判断该边的终点是否为给定顶点
	 * 
	 * @param vertex
	 *            顶点名
	 * @return 是则返回true
	 */
	public boolean containTarget(L vertex) {
		return target.equals(vertex);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge<?> other = (Edge<?>) obj;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (target == null) {
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		// if (weight != other.weight)
		// return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		result = prime * result + weight;
		return result;
	}

	// toString()
	@Override
	public String toString() {
		return "Source:" + source + "\tTarget:" + target + "\tWeight:" + weight + "\n";
	}
}
