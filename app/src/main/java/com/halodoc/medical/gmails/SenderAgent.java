package com.halodoc.medical.gmails;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SenderAgent extends AsyncTask<Void, Void, Void> {

    private String mail;
    private String subject;
    private String message;

    private Context context;
    private Session session;

    private ProgressDialog progressDialog;

    public SenderAgent(String mail, String subject, String message, Context context) {
        this.mail = mail;
        this.subject = subject;
        this.message = message;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, "Please wait. . .", "", false);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("supriyanto6543@gmail.com", "akubocahsonggo90");
            }
        });

        try{

            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress("supriyanto6543@gmail.com"));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("supriyanto150@gmail.com"));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(String.valueOf(message));
            Transport.send(mimeMessage);

        }catch (MessagingException m){
            m.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        progressDialog.dismiss();
        //new CheckoutActivity().sendCart(context);
        //new CheckoutActivity().pushNotify(context);
    }
}
