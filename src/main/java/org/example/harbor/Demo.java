package org.example.harbor;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        Harbor harbor = new Harbor(200, 2);
        Control control = new Control(harbor);
////////////////////////////////////////////////////////////////////////////////////////////////////
        //interaction with the user
        //for correct work, replace the comments in the method "input" in class "UserInput"
//        UserInterface ui = new UserInterface(harbor, control);
//        ui.running();
////////////////////////////////////////////////////////////////////////////////////////////////////
        //random choice
        PreDemo preDemo = new PreDemo(harbor, control);
        preDemo.running();
    }
}
