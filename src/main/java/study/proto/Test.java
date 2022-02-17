package study.proto;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.Arrays;

/**
 * @author N3verL4nd
 * @date 2022/2/15
 */
public class Test {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        PersonModel.Person person = PersonModel.Person.newBuilder().setAge(10).setName("lgh").build();
        System.out.println(person);

        byte[] buf = person.toByteArray();
        System.out.println(Arrays.toString(buf));

        System.out.println(PersonModel.Person.parseFrom(buf));
    }
}
