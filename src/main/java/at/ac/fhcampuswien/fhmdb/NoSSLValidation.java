package at.ac.fhcampuswien.fhmdb;

import javax.net.ssl.*;
import java.security.cert.CertificateException;



public class NoSSLValidation {

    public static void disable() {
        try {
            // Erstellen eines Trust-Managers, der alle Zertifikate akzeptiert
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        public void checkClientTrusted(
                                java.security.cert.X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(
                                java.security.cert.X509Certificate[] certs, String authType) {
                        }
                    }
            };

            // Erstellen eines SSLContexts, der den Trust-Manager verwendet
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());

            // Setzen des SSLContexts als Standard-SSL-Kontext
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Setzen eines Hostname-Verifiers, der alle Hostnamen akzeptiert
            HostnameVerifier allHostsValid = (hostname, session) -> true;
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
