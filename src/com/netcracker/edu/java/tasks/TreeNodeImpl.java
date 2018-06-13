package com.netcracker.edu.java.tasks;

import java.util.*;

/**
 * Created by Templerock on 13.09.2017.
 */
public class TreeNodeImpl implements TreeNode {

    private TreeNode parent;
    private List<TreeNode> children = new ArrayList<TreeNode>();
    private boolean exp;
    private Object data;

    public TreeNodeImpl(){
    }


    @Override
    public TreeNode getParent() {
        return this.parent;
    }

    @Override
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    @Override
    public TreeNode getRoot() {
        TreeNode temp;
        if (this.getParent() == null) {
            return null;
        }   else{
            temp = this;
            while (temp.getParent() != null){
                temp = temp.getParent();
            }
        }
        return temp;
    }

    @Override
    public boolean isLeaf() {
        if (this.getChildCount() == 0){
            return true;
        }   else {
            return false;
        }
    }

    @Override
    public int getChildCount() {
        return this.children.size();
    }

    @Override
    public Iterator<TreeNode> getChildrenIterator() {
        return this.children.iterator();
    }

    @Override
    public void addChild(TreeNode child) {
            this.children.add(child);
            child.setParent(this);
    }

    @Override
    public boolean removeChild(TreeNode child) {
        if (child == null) {
            return false;
        } else {
            if (this.children.remove(child)) {
                child.setParent(null);
                this.children.remove(child);
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean isExpanded() {
        return this.exp;
    }

    @Override
    public void setExpanded(boolean expanded) {
        this.exp = expanded;
    }

    @Override
    public Object getData() {
        if (this.data == null){
            return null;
        }   else return this.data;
    }

    @Override
    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String getTreePath() {
        if (this.getParent()!= null) {
            if (this.getData() == null) {
                return this.getParent().getTreePath() + "->" + "empty";
            } else {
                return this.getParent().getTreePath() + "->" + this.getData().toString();
            }
        }
        return this.getData().toString();
    }


    @Override
    public TreeNode findParent(Object data) {
        TreeNode inter = this;
        while (inter != null) {
            if (inter.getData() == null) {
                if (data == null) {
                    return inter;
                }
            } else if (inter.getData().equals(data)) {
                return inter;
            }
            inter = inter.getParent();
        }
        return null;
    }

    @Override
    public TreeNode findChild(Object data) {
        if (this.children == null) {
            return null;
        }
        Iterator<TreeNode> iter = getChildrenIterator();
        TreeNode current, other;
        while (iter.hasNext()) {
            current = iter.next();
            if (current.getData() == null) {
                if (data == null) {
                    return current;
                }
            } else if (current.getData().equals(data)) {
                return current;
            }
            other = current.findChild(data);
            if (other == null) {
                continue;
            }
            return other;
        }
        return null;
    }
}
