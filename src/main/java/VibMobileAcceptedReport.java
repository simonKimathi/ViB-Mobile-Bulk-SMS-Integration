import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class VibMobileAcceptedReport {
    public int statuscode;
    public String statusmessage;
    public String messageid;
    public double recipient;
}
