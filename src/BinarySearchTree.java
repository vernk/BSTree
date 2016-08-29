import java.util.*;
 
public class BinarySearchTree
{
  private TreeNode myRoot;
 
  public BinarySearchTree()
  {
    myRoot = null;
  }
 
  public void insert(Comparable next)
  {
  // post: next added to tree so as to preserve binary search tree
    myRoot = insertHelper(myRoot, next);
  }
 
  private TreeNode insertHelper(TreeNode root, Comparable next)  //4. What is purpose of comparable?
  {
  // pre : root points to a binary search tree
  // post: next added to tree so as to preserve binary search tree
    if (root == null)
      root = new TreeNode (next, null, null);
    else if(next.compareTo(root.getValue()) <= 0)
      root.setLeft(insertHelper(root.getLeft(), next));
    else
      root.setRight(insertHelper(root.getRight(), next));
    return root;
  }
 
  public Object find(Comparable target)
  {
    return findHelper(myRoot, target);
  }
 
  private Object findHelper(TreeNode root, Comparable target)
  {
    if (root == null)
      return null;
 
    int compareResult = target.compareTo(root.getValue());
 
    if (compareResult == 0)
      return root.getValue();
    else if (compareResult < 0)
      return findHelper(root.getLeft(), target);
    else
      return findHelper(root.getRight(), target);
  }
 
  public int countNodes()
  {
    return countNodesHelper(myRoot);
  }
 
  private int countNodesHelper(TreeNode root)
  {
    if (root == null)
      return 0;
    else
      //5. Explain the next line - why is 1 added?
      return countNodesHelper(root.getLeft()) + 1 + countNodesHelper(root.getRight());
  }
 
  public void print()
  {
  // post: prints the data fields of the tree, one per line 
    printInorder(myRoot);
  }
 
  private void printInorder(TreeNode root)
  {
  // pre : root points to a binary search tree
  // post: prints the data fields of the tree using an inorder traversal 
    if (root != null)
    {
      printInorder(root.getLeft());
      System.out.printf("%10d%10d\n",((Item)(root.getValue())).getId(), ((Item)(root.getValue())).getInv());
      printInorder(root.getRight());
    }
  }
 
  public void delete(Comparable target)
  {
  // post: deletes a node with data equal to target, if present, preserving binary search tree property
      myRoot = deleteHelper(myRoot, target);
  }
 
  private TreeNode deleteHelper(TreeNode node, Comparable target)
  {
  // pre : node points to a non-empty binary search tree
  // post: deletes a node with data equal to target, if present, preserving binary search tree property 
    if (node == null)
   {
      throw new NoSuchElementException();
    }
    else if (target.equals(node.getValue()))
    {
       return deleteTargetNode(node);  // found it so remove node
    }
    else if (target.compareTo(node.getValue()) < 0)
   {
      node.setLeft(deleteHelper(node.getLeft(), target));
      return node;
    }
    else
    {
     //target.compareTo(root.getValue()) > 0
      node.setRight(deleteHelper(node.getRight(), target));
      return node;
    }
  }
 
  private TreeNode deleteTargetNode(TreeNode target)
  {
  // pre : target points to TreeNode to be deleted
  // post: returns a reference to a subtree with the target
  //       TreeNode removed or null if the TreeNode is a leaf
    if (target.getRight() == null)
   {
      return target.getLeft();
    }
    else if (target.getLeft() == null)
   {
      return target.getRight();
    }
    else if (target.getRight().getLeft() == null)
    {
       target.setValue(target.getRight().getValue());
       target.setRight(target.getRight().getRight());
       return target;
     }
    else{ // right child has left child
       TreeNode marker = target.getRight();
       while (marker.getLeft().getLeft() != null)
         marker = marker.getLeft();
 
       target.setValue(marker.getLeft().getValue());
       marker.setLeft(marker.getLeft().getRight());
       return target;
    }
  }
}