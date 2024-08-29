package archived;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")    //This whole configuration will be registered only with "test" profile
public class ApplicationConfig {

    @Bean       //@Bean will create an object configuration and store it into the spring IOC,
    //The object config will be injected into the required slot by dependency injection when needed.

    @Qualifier("bean1")
    //Qualifier is required when there are 2 or more beans(object) of the same class instantiated,
    //Otherwise spring will not know which bean to inject.


    public MyFirstClass myFirstBean() {
        return new MyFirstClass("First Bean");
    }

    @Bean
    //@Qualifier("bean2")
    public MyFirstClass mySecondBean() {
        return new MyFirstClass("Second Bean");
    }

    @Bean
    //@Primary is another option to resolve the problem mentioned above with the qualifier case,
    //spring will give the bean of @Primary higher priority in dependency injection.

    //@Qualifier("bean2")
    public MyFirstClass myThirdBean() {
        return new MyFirstClass("Third Bean");
    }

}
