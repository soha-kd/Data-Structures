import java.util.Scanner;

public class SearchBinaryTree {
    //Represent a node of binary tree
    public static class Node{
        int[] data;
        // int data;
        Node left;
        Node right;

        public Node(int[] data){
            //Assign data to the new node, set left and right children to null
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    //Represent the root of binary tree
    public static Node root;

    public static boolean flag = false;

    public SearchBinaryTree(){
        Node root = null;
    }
    //searchNode() will search for the particular node in the binary tree
    public static void searchNode(Node temp, int[] value){
        //Check whether tree is empty
        if(root == null){
            System.out.println("Tree is empty");
        }
        else{
            //If value is found in the given binary tree then, set the flag to true
            if(temp.data[0] == value[0] && temp.data[1] == value[1]){
                flag = true;
                return;
            }
            //Search in left subtree
            if(!flag && temp.left != null){
                searchNode(temp.left, value);
            }
            //Search in right subtree
            if(!flag && temp.right != null){
                searchNode(temp.right, value);
            }
        }
    }


    public static void main(String[] args) {

        SearchBinaryTree bt = new SearchBinaryTree();
        //Add nodes to the binary tree
        bt.root = new Node(new int[]{18, 29});
        bt.root.left = new Node(new int[]{3, 6});
        bt.root.right = new Node(new int[]{8, 5});
        bt.root.left.left = new Node(new int[]{2, 12});
        bt.root.right.left = new Node(new int[]{6, 28});
        bt.root.right.right = new Node(new int[]{21, 25});
        //Search for node 5 in the binary tree
        searchNode(bt.root, new int[]{21, 25});
        if(flag)
            System.out.println("Element is present in the binary tree");
        else
            System.out.println("Element is not present in the binary tree");
    }
}