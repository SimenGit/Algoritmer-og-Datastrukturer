package oving_04.oppg2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import static javax.swing.JOptionPane.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import static javax.swing.JOptionPane.*;

public class BinaryTree {
    private class Node {
        String data;
        Node left;
        Node right;

        Node(String data) {
            this.data = data;
        }
    }

    private Node root;

    public void insert(String data) {
        root = insert(root, data);
    }

    private Node insert(Node x, String data) {
        if (x == null) {
            return new Node(data);
        }
        if (data.length() < x.data.length()) {
            x.left = insert(x.left, data);
        } else {
            x.right = insert(x.right, data);
        }
        return x;
    }

    public void printLineByLine() {
        printLineByLine(root);
    }

    private void printLineByLine(Node root) {
        final Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        if (root == null) {
            queue.add(new Node("||"));
            return;
        }


        int space = 64;

        while (space > 4) {
            final int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node x = queue.remove();
                for(int k = 0; k < space; k++) {
                    System.out.print(" ");
                }
                System.out.print(x.data + " ");
                for(int k = 0; k < space; k++) {
                    System.out.print(" ");
                }

                if (x.left != null) {
                    queue.add(x.left);
                }
                if (x.right != null) {
                    queue.add(x.right);
                }

            }
            // new level
            System.out.println();
            space = space / 2;
        }
    }


    public static void main(String[] args) {

        final BinaryTree tree = new BinaryTree();

        tree.insert("abb");
        tree.insert("bb");
        tree.insert("bbbb");
        tree.insert("dvv");
        tree.insert("f");
        tree.insert("gv");
        tree.insert("hololololololo");
        tree.printLineByLine();
    }
}