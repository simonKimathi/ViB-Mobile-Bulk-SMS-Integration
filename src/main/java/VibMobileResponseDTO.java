import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class VibMobileResponseDTO {
    public String action;
    public VibMobileResponseData data;
}