/*
* При компиляции: в sourcepath указать расположение исходного кода, в classpath - расположение  исходных класс и папку Plugins
* Пример:
*   javac -sourcepath C:\Users\1\Desktop -cp C:\Users\1\Desktop;C:\Users\1\Desktop\plugins *.java
*   java -cp C:\Users\1\Desktop;C:\Users\1\Desktop\plugins Solution
* */


import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String command;
        Scanner scanner=new Scanner(System.in);
        while (true) {
            command=scanner.nextLine();
            switch (command){
                case "list_plugins":getList(); break;
                case "exit":System.exit(0);
                default: execute(command); break;
            }
        }
    }
    static void getList() {
        String s = Paths.get("").toAbsolutePath().toString()+"/plugins";
        File file = new File(s);
        if (file.exists())
            for (File item : file.listFiles())
                System.out.println(item.getName());
    }

    static void execute(String s) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<String> argPom=new ArrayList<>(Arrays.asList(s.split("\\s+")));
        if(argPom.get(0).equals("execute")&&argPom.size()>=3) {
            Class mClassObject = Class.forName(argPom.get(1));
            argPom.remove(0);
            argPom.remove(0);
            String[] arg = new String[argPom.size()];
            argPom.toArray(arg);
            try {
                Iplugin iplugin = (Iplugin) mClassObject.newInstance();
                System.out.println(iplugin.execute(arg));
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        else System.out.println("Illegal command");
    }

}
