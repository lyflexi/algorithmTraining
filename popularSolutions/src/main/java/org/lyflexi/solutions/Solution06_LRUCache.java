package org.lyflexi.solutions;


import org.lyflexi.solutions.common.NodeLRU;

import java.util.HashMap;

/**
 * @Author: ly
 * @Date: 2024/2/9 23:10
 */
/*
* 146. LRU 缓存:
* 思路：对于链表的增删改查操作，时间复杂度分析如下
* - 单链表：增删改查由于都需要定位元素，也就是查询元素所以时间复杂度都是On
* - 双向链表：因为双向链表节点都具备前驱指针，所以删除操作时间复杂度可以降为01，所以本题的链表结构是双向链表
*
* 此外,题目要求：
* get的时间复杂度也为O1，所以必须使用hashmap，作为读取缓存
*
*
*
*
*
*
请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
实现 LRUCache 类：
LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
*
* 示例输入:
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
输出
[null, null, null, 1, null, -1, null, -1, 3, 4]
解释
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // 缓存是 {1=1}
lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
lRUCache.get(1);    // 返回 1
lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
lRUCache.get(2);    // 返回 -1 (未找到)
lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
lRUCache.get(1);    // 返回 -1 (未找到)
lRUCache.get(3);    // 返回 3
lRUCache.get(4);    // 返回 4
* */
public class Solution06_LRUCache {


    public static void main(String[] args) {

        Solution06_LRUCache solution06LruCache = new Solution06_LRUCache(3);
        solution06LruCache.put(1,1);
        solution06LruCache.put(2,2);
        solution06LruCache.put(3,3);
        solution06LruCache.put(4,4);
        solution06LruCache.get(4);
        solution06LruCache.get(3);
        solution06LruCache.get(2);
        solution06LruCache.get(1);
        solution06LruCache.put(5,5);

        solution06LruCache.get(1);
        solution06LruCache.get(2);
        solution06LruCache.get(3);
        solution06LruCache.get(4);
        solution06LruCache.get(5);

    }

    private NodeLRU dummy;
    private int capacity;
    private int count;
    HashMap<Integer,NodeLRU> mapToNode = new HashMap<>();


    public Solution06_LRUCache(int capacity) {
        dummy = new NodeLRU(0, 0);
        dummy.next = dummy;//初始化傀儡节点的后继指向自身
        dummy.pre = dummy;//初始化傀儡节点的前驱指向自身
        this.capacity = capacity;
    }

    public int get(int key) {

        NodeLRU cache = mapToNode.get(key);
        if (cache!=null){
            moveToTail(cache);
            return cache.getValue();
        }
        //缓存为空
        return -1;
    }

    public void put(int key, int value) {
        NodeLRU cache = mapToNode.get(key);
        if (cache!=null){
            cache.setValue(value);
            mapToNode.put(key,cache);
            moveToTail(cache);
            return;
        }
        //缓存为null
        count++;
        if (count>capacity){
            //淘汰策略
            mapToNode.remove(dummy.next.getKey());
            delHead();
            count--;
        }

        NodeLRU node = new NodeLRU(key, value);
        addToTail(node);
        mapToNode.put(key,node);
    }




    private void moveToTail(NodeLRU node) {

        //删除当前节点
        node.pre.next = node.next;
        node.next.pre = node.pre;


        //尾部新增
        addToTail(node);

    }
    private void addToTail(NodeLRU node) {
        //先安置新节点自身的前驱与后继指针
        node.next = dummy;
        node.pre = dummy.pre;

        dummy.pre.next = node;
        dummy.pre = node;
    }

    private void delHead() {
        dummy.next.next.pre = dummy;
        dummy.next = dummy.next.next;
    }

}