/**
 * Created by Самойлов Аркадий on 06.02.2018.
 */
public class MyClass implements Iplugin {
    @Override
    public int execute(String[] args) {
        if(args[0].equals("hello"))
           return 12345;
        else return 54321;
    }

    @Override
    public String getPluginName() {
        return null;
    }
	
	
}
