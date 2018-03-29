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
	// ��ӽ�㣻ͨ��Ȩ��ֵ�ж϶ԱߵĲ�������ӡ��޸Ļ�ɾ����ɾ��һ����㼰�����������бߣ�
	// ���ȫ����㣻��ø�������ȫ����㣻��ø�������ȫ���յ㣻��ӡͼ����Ϣ
	// Representation invariant:
	// ʹ��Set�洢������Ϣ����֤û���ظ��Ķ��㡣
	// �����û���ӱ�ʱ��weight��Ҫ��ѡ����ʵĲ��������մ洢��ͼ�е�edge��weight��Ϊ����0��int���͡�
	// Safety from rep exposure:
	// ���������Ա��������final���͵ģ��޷��ı�ָ��
	// ��Ҫ����ʱʹ�÷����Կ���

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
	// ��������������յ��������Ȩ�أ��жϱ����Ƿ���������Ķ��㣻�жϸ���������Ƿ���ȷ��
	// �жϸ������յ��Ƿ���ȷ����ӡ�ߵ���Ϣ
	// Representation invariant:
	// L���͵�source��target��int���͵�weight��weight���ڻ����0��weightΪ0�ı߲���ͼ�д洢�������������ʱʹ��
	// Safety from rep exposure:
	// ���г�Ա��������final�����ұ����ɱ䣬�޷���ȡ�������ڴ��ַ

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
	 * �жϸñ��Ƿ���������Ķ��㣨�ߵ������յ㣩
	 * 
	 * @param vertex
	 *            ������
	 * @return �����򷵻�true
	 */
	public boolean contain(L vertex) {
		return source.equals(vertex) | target.equals(vertex);
	}

	/**
	 * �жϸñߵ�����Ƿ�Ϊ��������
	 * 
	 * @param vertex
	 *            ������
	 * @return ���򷵻�true
	 */
	public boolean containSource(L vertex) {
		return source.equals(vertex);
	}

	/**
	 * �жϸñߵ��յ��Ƿ�Ϊ��������
	 * 
	 * @param vertex
	 *            ������
	 * @return ���򷵻�true
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
