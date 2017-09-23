/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;

/**
 *
 * @author Cameron
 */
public class TestSuite {
    public static void run(){
        try{
            //DegreeRange tests
            RadianRange range = new RadianRange(0f, (float)(Math.PI));
            //expect any result to be positive
            assert range.contains(0f) == true:"Full range expected to contain 0";
            assert range.contains((float)(359*(Math.PI/180))) == true:"Full range expected to contain 359";
            assert range.contains((float)(-5*(Math.PI/180))) == true:"Full range expected to contain -5";
            assert range.contains((float)(360*(Math.PI/180))) == true:"Full range expected to contain 360";

            RadianRange halfRange = new RadianRange(0f, (float)(90*(Math.PI/180)));
            assert halfRange.contains((float)(0*(Math.PI/180))) == true:"270 to 90 expected to contain 0";
            assert halfRange.contains((float)(90*(Math.PI/180))) == true:"270 to 90 expected to contain 90";
            assert halfRange.contains((float)(91*(Math.PI/180))) == false:"270 to 90 not expected to contain 91";
            assert halfRange.contains((float)(270*(Math.PI/180))) == true:"270 to 90 expected to contain 270";
            assert halfRange.contains((float)(269*(Math.PI/180))) == false:"270 to 90 not expected to contain 269";
            assert halfRange.contains((float)(-5*(Math.PI/180))) == true:"270 to 90 (-90 to 90) expected to contain -5";
            assert halfRange.contains((float)(-90*(Math.PI/180))) == true:"270 to 90 (-90 to 90) expected to contain -90";
            assert halfRange.contains((float)(-91*(Math.PI/180))) == false:"270 to 90 (-90 to 90) not expected to contain -91";

            RadianRange smallestRange = new RadianRange((float)(50*(Math.PI/180)), 0f);
            assert smallestRange.contains((float)(50*(Math.PI/180))) == true:"50 to 50 expected to contain 50";
            assert smallestRange.contains((float)(49*(Math.PI/180))) == false:"50 to 50 not expected to contain 49";
            assert smallestRange.contains((float)(51*(Math.PI/180))) == false:"50 to 50 not expected to contain 51";
            assert smallestRange.contains((float)((360 + 50)*(Math.PI/180))) == true:"50 to 50 expected to contain 360 + 50 (one full circle + 50)";
            assert smallestRange.contains((float)((50 - 360)*(Math.PI/180))) == true:"50 to 50 expected to contain 50 - 360 (50 minus a full circle)";
            assert smallestRange.contains((float)((50 - 400)*(Math.PI/180))) == false:"50 to 50 not expected to contain 50 - 400";
            
            //DistanceRange tests
            DistanceRange distRange = new DistanceRange(0f, (float)(50*(Math.PI/180)));
            assert distRange.contains((float)(-1*(Math.PI/180))) == false:"0 - 50 not expected to contain -1";
            assert distRange.contains((float)(0*(Math.PI/180))) == true:"0 - 50 expected to contain 0";
            assert distRange.contains((float)(5*(Math.PI/180))) == true:"0 - 50 expected to contain 5";
            assert distRange.contains((float)(50*(Math.PI/180))) == true: "0 - 50 expected to contain 50";
            assert distRange.contains((float)(51*(Math.PI/180))) == false: "0 - 50 not expected to contain 51";
            
            DistanceRange distRange2 = new DistanceRange(5f, 3f);
            assert distRange2.contains(1f) == false:"2 - 8 not expected to contain 1";
            assert distRange2.contains(2f) == true:"2 - 8 expected to contain 2";
            assert distRange2.contains(8f) == true:"2 - 8 expected to contain 8";
            assert distRange2.contains(9f) == false:"2 - 8 not expected to contain 9";
            assert distRange2.contains(-5000f) == false:"2 - 8 not expected to contain -5000";
            assert distRange2.contains(5000f) == false:"2 - 8 not expected to contain 5000";
                    
                    
                    
                    
                    
                    
                    
                    
        } catch (Exception e){
            Debugger.print(e.getMessage());
        }
    }
}
