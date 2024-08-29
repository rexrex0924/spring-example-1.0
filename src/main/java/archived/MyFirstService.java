package archived;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MyFirstService {

    private final MyFirstClass myfirstclass;

    @Value("${my.custom.property}")
    private String customProperty;

    @Value("${my.custom.property.int}")
    private Integer customPropertyInt;

    public Integer getCustomPropertyInt() {
        return customPropertyInt;
    }

    public String getCustomProperty() {
        return customProperty;
    }

    //Constructor Injection
    public MyFirstService(@Qualifier("bean1") MyFirstClass myfirstclass) {
        this.myfirstclass = myfirstclass;
    }

    /* This is Method Injection/ Setter Injection if method name is setxxxxx
    public void injectDependencies(MyFirstClass myfirstclass){
        this.myfirstclass = myfirstclass;
    }
    */

    public String message(){
        return "The dependency is saying: " + myfirstclass.sayHello();
    }




}
