import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class test {
    public static void main(String[] args) {
//        String xml="<response>\n" +
//                "   <action>sendmessage</action>\n" +
//                "   <data>\n" +
//                "      <acceptreport>\n" +
//                "         <statuscode>0</statuscode>\n" +
//                "         <statusmessage>Message accepted for delivery</statusmessage>\n" +
//                "         <messageid>ERFAV23D</messageid>\n" +
//                "         <recipient>06203105366</recipient>\n" +
//                "      </acceptreport>\n" +
//                "   </data>\n" +
//                "</response>";
//        try {
//            VibMobileResponseDTO unmarshall = unMarshallXML(xml);
//            System.out.println(unmarshall.getData().getAcceptreport().getStatuscode());
//
//        } catch (JAXBException | IOException e) {
//            e.printStackTrace();
//        }
        VibMobileResponseDTO vibMobileResponseDTO = sendSMS();
        System.out.println("After Mapping:::::::::::"+vibMobileResponseDTO.toString());

    }
    public static VibMobileResponseDTO unMarshallXML(String xml) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(VibMobileResponseDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StreamSource streamSource = new StreamSource(new StringReader(xml));
        return unmarshaller.unmarshal(streamSource, VibMobileResponseDTO.class).getValue();

    }
    public static VibMobileResponseDTO sendSMS( ){

        try {
            OkHttpClient client = new OkHttpClient();

            String url= "http://**********:****/api" + "?action=sendmessage" +
                    "&username=" + "**********" +
                    "&password=" + "***************" +
                    "&recipient=" + URLEncoder.encode("************", StandardCharsets.UTF_8) +
                    "&messagetype=SMS:TEXT" +
                    "&messagedata=" + URLEncoder.encode("ViB Mobile Test SMS",StandardCharsets.UTF_8) +
                    "&originator=" + URLEncoder.encode("*******",StandardCharsets.UTF_8);

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            Response response = client.newCall(request).execute();

            assert response.body() != null;

            String responseString = response.body().string();
            System.out.println("server response is::::::::::::::"+responseString);
            return unMarshallXML(responseString);


        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return null;

    }
}
