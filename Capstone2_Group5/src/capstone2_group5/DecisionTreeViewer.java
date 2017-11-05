/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;

import java.util.ArrayList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

/**
 *
 * @author Cameron
 */
public class DecisionTreeViewer {
    private static Stage stage;
    private static Parent root;
    private static TreeItem<DecisionTreeNode> treeRoot;
//    private static DecisionTreeNode decisionRoot;
    
    
    public static void start(){
        stage = new Stage();
        root = new TreeView();
        stage.setScene(new Scene(root));
        populateTree(DecisionTree.getRoot(), treeRoot);
    }
    
    public static void populateTree(DecisionTreeNode decisionNode, TreeItem<DecisionTreeNode> treeNode){
        if(decisionNode != null){
        treeRoot = new TreeItem(DecisionTree.getRoot());
//            ArrayList<DecisionTreeNode> decisionChildren = decisionRoot.getChildren();
        }
    }
}
