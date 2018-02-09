/**
 * Created by Самойлов Аркадий on 06.02.2018.
 */
public class MyClass2 implements Iplugin {
    @Override
    public int execute(String[] args) {
        if(args[0].equals("hello"))
           return 9824374;
        else return 9824374;
    }

    @Override
    public String getPluginName() {
        return null;
    }
	
	
}
