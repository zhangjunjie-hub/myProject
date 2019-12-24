package mqtest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderSuccessMessage {
    private String msgCode;
    private String msgContent;
    private String msgStatus;


}
