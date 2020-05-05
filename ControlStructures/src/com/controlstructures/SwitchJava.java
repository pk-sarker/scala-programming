package com.controlstructures;

public class SwitchJava {
    public static void main(String[] args) {
        int i = 3;
        switch (i){
            case 1:
            case 2:
            case 3:
                System.out.println(">> One or Two or Three");
                break;
            case 4:
                System.out.println(">> Four");
                break;
        }
    }
}
