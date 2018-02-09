
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    static String modulePath;
    public static void main(String[] args){

         modulePath = args[0]+"\\plugins\\";
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
        File dir = new File(modulePath);
        String[] modules = dir.list();
        for (String module: modules)
            System.out.println(module.split(".class")[0]);
    }

    static void execute(String s){
        ArrayList<String> argPom=new ArrayList<>(Arrays.asList(s.split("\\s+")));

        if(argPom.get(0).equals("execute")&&argPom.size()>=3) {
            File dir = new File(modulePath);
            ArrayList<String>pom=new ArrayList<>(Arrays.asList(dir.list()));
            if(!pom.contains(argPom.get(1)+".class")){
                System.out.println("Class not exists");
                return;
            }

            ModuleLoader loader = new ModuleLoader(modulePath, ClassLoader.getSystemClassLoader());
            String moduleName = argPom.get(1);
            Class clazz = null;
            try {
                clazz = loader.loadClass(moduleName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            argPom.remove(0);
            argPom.remove(0);
            String[] arg = new String[argPom.size()];
            argPom.toArray(arg);

            try {
                Iplugin execute = null;
                try {
                    execute = (Iplugin) clazz.newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                System.out.println(execute.execute(arg));
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        else System.out.println("Illegal command");
    }

}
