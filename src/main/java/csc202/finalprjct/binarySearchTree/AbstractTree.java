package csc202.finalprjct.binarySearchTree;

public abstract class AbstractTree<E>
        implements Tree<E> {
    /** Inorder traversal from the root*/
    public void inorder() {
    }
    /** Postorder traversal from the root */
    public void postorder() {
    }
    /** Preorder traversal from the root */
    public void preorder() {
    }
    /** Return true if the tree is empty */
    public boolean isEmpty() {
        return getSize() == 0;
    }
}