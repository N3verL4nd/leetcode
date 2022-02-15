package study.proto;

/**
 * @author N3verL4nd
 * @date 2022/2/15
 */
public class Test {
    public static void main(String[] args) {
        PersonModel.Person person = PersonModel.Person.newBuilder().setAge(10).setName("lgh").build();
        System.out.println(person);
    }
}
