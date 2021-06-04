/*
给你两个非空的链表表示两个非负的整数它们每位数字都是按照逆序的方式存储的，
并且每个节点只能存储一位数字。请你将两个数相加，并以相同形式返回一个表示和的链表。
*/


public class 两数相加 {

    private static int count = 0;
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1.val == 0 && l1.next == null){
            return l2;
        }

        if(l1.next != null && l2.next != null){
            l1.val = l1.val + l2.val + count;
            if(l1.val>9){
                count = 1;
                l1.val -=10;
            }else
                count = 0;
            addTwoNumbers(l1.next,l2.next);
        }
        if(l1.next != null && l2.next == null){
            l1.val+=count+l2.val;
            count = 0;
            if(l1.val>9){
                l1.val -=10;
                l2.next = new ListNode();
                l2.next.val = 1;
                l2.next.next = null;
                addTwoNumbers(l1.next,l2.next);
            }
            else
                return l1;
        }
        if(l1.next == null && l2.next != null){
            l1.val +=count+l2.val;
            count = 0;
            if(l1.val>9){
                l1.val -=10;
                l1.next = new ListNode();
                l1.next.val=1;
                l1.next.next = null;
                addTwoNumbers(l1.next,l2.next);
            }
            else{
                l1.next = l2.next;
                return l1;
            }
        }
        if(l1.next == null && l2.next ==null){
            l1.val = l1.val +l2.val+count;
            if(l1.val>9){
                l1.val -=10;
                l1.next = new ListNode(1);
            }
            return l1;
        }
        return l1;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(9,new ListNode(9,new ListNode(9
        ,new ListNode(9,new ListNode(9,new ListNode(9,new ListNode(9)))))));
        ListNode l2 = new ListNode(9,new ListNode(9,new ListNode(9,new ListNode(9))));
        ListNode l3 = addTwoNumbers(l1,l2);
        while (true){
            if(l3==null)break;
            System.out.print(l3.val);
            l3 = l3.next;
        }
    }
}
class ListNode {
    Integer val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
