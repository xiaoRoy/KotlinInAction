package com.learn.kotlininaction.chap4.createinstance

class DelegationCollection<T> : Collection<T> {

    private val innerList = arrayListOf<T>()

    override val size: Int = innerList.size

    override fun contains(element: T) = innerList.contains(element)

    override fun containsAll(elements: Collection<T>): Boolean = innerList.containsAll(elements)

    override fun isEmpty(): Boolean {
        return innerList.isEmpty()
    }

    override fun iterator() = innerList.iterator()
}

class DelegationByCollection<T>(innerList: Collection<T> = ArrayList()) : Collection<T> by innerList

class CountingSet<T>
constructor(val innerSet: MutableCollection<T> = HashSet<T>()) : MutableCollection<T> by innerSet {

    var objectAdded = 0

    override fun add(element: T): Boolean {
        objectAdded ++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectAdded += elements.size
        return innerSet.addAll(elements)
    }
}
