/**
 * 反转链表 —— 迭代法与递归法
 */
public class ReverseLinkedList {

    /** 链表节点 */
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 迭代法反转链表
     * 时间复杂度 O(n)，空间复杂度 O(1)
     *
     * 思路：用 prev 记录已反转部分的头，cur 遍历原链表，
     * 每步先保存 next，再把 cur.next 指向 prev，最后 prev 和 cur 一起前移。
     */
    public static ListNode reverseIterative(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next; // 先保存下一个节点
            cur.next = prev;          // 当前节点反向指向前一个节点
            prev = cur;               // prev 前移
            cur = next;               // cur 前移
        }
        return prev; // 循环结束时 prev 就是新链表的头
    }

    /**
     * 递归法反转链表
     * 时间复杂度 O(n)，空间复杂度 O(n)（递归调用栈）
     */
    public static ListNode reverseRecursive(ListNode head) {
        // 空链表或只有一个节点，直接返回
        if (head == null || head.next == null) {
            return head;
        }
        // 先反转后面的部分，newHead 是整条链表反转后的头
        ListNode newHead = reverseRecursive(head.next);
        // 让后一个节点的 next 指回自己
        head.next.next = head;
        // 断开自己原来的 next，避免形成环
        head.next = null;
        return newHead;
    }

    /** 由数组构造链表 */
    public static ListNode build(int[] values) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int v : values) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    /** 打印链表 */
    public static void print(ListNode head) {
        StringBuilder sb = new StringBuilder();
        for (ListNode p = head; p != null; p = p.next) {
            sb.append(p.val);
            if (p.next != null) {
                sb.append(" -> ");
            }
        }
        System.out.println(sb.length() == 0 ? "[]" : sb.toString());
    }

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5};

        System.out.print("原链表:     ");
        print(build(data));

        System.out.print("迭代法反转: ");
        print(reverseIterative(build(data)));

        System.out.print("递归法反转: ");
        print(reverseRecursive(build(data)));

        // 边界情况
        System.out.print("空链表:     ");
        print(reverseIterative(null));
        System.out.print("单节点:     ");
        print(reverseIterative(build(new int[]{42})));
    }
}
