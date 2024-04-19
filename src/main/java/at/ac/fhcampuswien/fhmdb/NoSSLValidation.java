package at.ac.fhcampuswien.fhmdb;

import javax.net.ssl.*;
import java.security.cert.CertificateException;



public class NoSSLValidation {

    public static void disable() {
        try {
            // create trustmanager which accepts all certificates
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

            // create SSL context, which uses trustmanager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());

            // set SSL context as Standard context
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // set hostname verifier which accepts all hostnames
            HostnameVerifier allHostsValid = (hostname, session) -> true;
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
