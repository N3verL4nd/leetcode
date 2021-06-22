package test.eventbus;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author liguanghui02
 * @date 2021/6/22
 */
@Data
@AllArgsConstructor
public class OrderMessage {
    private String content;
}
